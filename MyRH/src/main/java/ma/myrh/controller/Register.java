package ma.myrh.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import ma.myrh.dto.EntrepriseDto;
import ma.myrh.dto.EntrepriseVerify;
import ma.myrh.dto.message.ErrorMessage;
import ma.myrh.entity.Entreprise;
import ma.myrh.error.exception.entreprise.EntrepriseNotFoundException;
import ma.myrh.error.exception.entreprise.EntrepriseRegisterFailedException;
import ma.myrh.service.entreprise.EntrepriseService;
import ma.myrh.util.JwtUtil;
import ma.myrh.util.TwilioUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class Register {

    private final EntrepriseService entrepriseService;

    private final PasswordEncoder passwordEncoder;

    private final TwilioUtil twilioUtil;

    public Register(EntrepriseService entrepriseService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, TwilioUtil twilioUtil) {
        this.entrepriseService = entrepriseService;
        this.passwordEncoder = passwordEncoder;
        this.twilioUtil = twilioUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<ErrorMessage> register(@Valid @RequestBody EntrepriseDto entrepriseDto) throws EntrepriseRegisterFailedException {

        if(entrepriseService.checkIfEntrepriseExistByEmail(entrepriseDto.getEmail())){
            throw new EntrepriseRegisterFailedException("Email already exist");
        }

        if(entrepriseService.checkIfEntrepriseExistByName(entrepriseDto.getName())){
            throw new EntrepriseRegisterFailedException("Name already exist");
        }

        if(entrepriseService.checkIfEntrepriseExistByPhone(entrepriseDto.getPhone())){
            throw new EntrepriseRegisterFailedException("Phone number already exist");
        }

        Entreprise entreprise = Entreprise.builder()
                .email(entrepriseDto.getEmail())
                .password(passwordEncoder.encode(entrepriseDto.getPassword()))
                .name(entrepriseDto.getName())
                .address(entrepriseDto.getAddress())
                .description(entrepriseDto.getDescription())
                .profilePicture(entrepriseDto.getProfilePicture())
                .phone(entrepriseDto.getPhone())
                .website(entrepriseDto.getWebsite())
                .linkedin(entrepriseDto.getLinkedin())
                .verificationCode(generateCode())
                .timeVerificationCode(LocalDateTime.now().plusMinutes(3))
                .build();

        entrepriseService.saveEntreprise(entreprise);

        String phone = "+212"+entreprise.getPhone().toString().substring(1);
//        twilioUtil.sendSMS(phone, "MyRH : Your verification code is " + entreprise.getVerificationCode());
        return ResponseEntity.ok(new ErrorMessage(HttpStatus.OK, "Verify your account by providing the code sent to your phone number"));
    }

    @PostMapping("/verify")
    public ResponseEntity<HashMap<String, String>> verify(@Valid @RequestBody EntrepriseVerify entrepriseVerify, HttpServletRequest request) throws EntrepriseNotFoundException {

        // check if phone number exists
        EntrepriseDto entreprise = entrepriseService.getEntrepriseByPhone(entrepriseVerify.getPhone())
                .orElseThrow(() -> new EntrepriseNotFoundException("Phone number does not exists"));

        // check if SMS code is valid
        if(!entreprise.getVerificationCode().equals(entrepriseVerify.getVerificationCode())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            new HashMap<>(){{
                                put("status", String.valueOf(HttpStatus.BAD_REQUEST));
                                put("message", "Verification code is invalid");
                            }}
                    );
        }

        // check if SMS code is expired
        if(LocalDateTime.now().isAfter(entreprise.getTimeVerificationCode())){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(
                            new HashMap<>(){{
                                put("status", String.valueOf(HttpStatus.UNAUTHORIZED));
                                put("message", "Verification code is expired");
                            }}
                    );
        }

        // generate JWT token
        Algorithm algo1 = Algorithm.HMAC256("mySecret123");
        String jwtAccessToken = JWT.create()
                .withSubject(entreprise.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", Collections.singletonList(new SimpleGrantedAuthority("COMPANY")))
                .sign(algo1);

        return ResponseEntity.ok(
                new HashMap<>(){{
                    put("token", jwtAccessToken);
                }}
        );

    }


    private String generateCode() {
        String code = String.valueOf((int) Math.floor(Math.random() * 1000000));
        if(code.length() < 6){
            code = "0" + code;
        }
        return code;
    }


}
