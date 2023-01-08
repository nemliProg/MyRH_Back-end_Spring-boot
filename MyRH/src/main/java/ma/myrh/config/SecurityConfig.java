package ma.myrh.config;

import ma.myrh.dto.EntrepriseDto;
import ma.myrh.entity.Agent;
import ma.myrh.entity.Entreprise;
import ma.myrh.security.JwtAuthenticationFilter;
import ma.myrh.security.JwtAuthorizationFilter;
import ma.myrh.service.agent.AgentService;
import ma.myrh.service.agent.AgentServiceImp;
import ma.myrh.service.entreprise.EntrepriseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private AgentService agentService;

    private EntrepriseService entrepriseService;
    private PasswordEncoder passwordEncoder;


    public SecurityConfig(AgentService agentService, EntrepriseService entrepriseService, PasswordEncoder passwordEncoder) {
        this.agentService = agentService;
        this.entrepriseService = entrepriseService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    UserDetailsService userDetailsService(){
        return email -> {
            Agent agent = agentService.getAgentByEmail(email);
            if (agent != null) {
                return new User(agent.getEmail(), agent.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("AGENT")));
            }

            EntrepriseDto entreprise = entrepriseService.getEntrepriseByEmail(email).get();
            if (entreprise != null) {
                return new User(entreprise.getEmail(), entreprise.getPassword(),Collections.singletonList(new SimpleGrantedAuthority("COMPANY")));
            }

            throw new RuntimeException("No user found for "+ email + ".");
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests( aut ->
                        aut.requestMatchers("/","/register","/company/**","/agent/**").permitAll()
                )
//                .authorizeHttpRequests(aut ->
//                        aut.requestMatchers("/agent/**").hasAuthority("AGENT")
//                )
//                .authorizeHttpRequests(aut ->
//                        aut.requestMatchers("/company/**").hasAuthority("COMPANY")
//                )
                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .addFilter(new JwtAuthenticationFilter(authenticationManager(this.authenticationProvider(this.userDetailsService()))))
                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(this.passwordEncoder);
        return authenticationProvider;
    }

}
