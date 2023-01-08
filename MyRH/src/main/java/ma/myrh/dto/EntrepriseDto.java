package ma.myrh.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseDto {

    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 10)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank
    @Size(min = 10, max = 10)
    private String phone;

    @NotBlank
    private String address;

    @NotBlank
    private String description;


    private String website;


    private String linkedin;

    private Boolean isVerified;

    @NotBlank
    private String profilePicture;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String verificationCode;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime timeVerificationCode;

}
