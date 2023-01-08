package ma.myrh;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MyRhApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRhApplication.class, args);
    }


//    @Bean @Transactional
//    CommandLineRunner commandLineRunner(AgentRepository agentRepository, EntrepriseRepository entrepriseRepository){
//        return args->{
//            Entreprise entreprise = new Entreprise();
//            entreprise.setEmail("younes@gmail.com");
//            String hashedPassword = this.passwordEncoder().encode("hello123");
//            System.out.println("hashedPassword = " + hashedPassword);
//            entreprise.setPassword(hashedPassword);
//            entreprise.setPhone("0555555555");
//            entreprise.setAddress("test");
//            entreprise.setName("test");
//            entreprise.setDescription("test description");
//            entrepriseRepository.save(entreprise);
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary() {
        Map config = new HashMap();
        config.put("cloud_name", "xxxx");
        config.put("api_key", "xxxx");
        config.put("api_secret", "xxxx");
        Cloudinary cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}
