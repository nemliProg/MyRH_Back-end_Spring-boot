package ma.myrh.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseVerify {

    @Size(min = 10, max = 10)
    String phone;

    @NotBlank
    String password;

    @Size(min = 6, max = 6)
    String verificationCode;
}