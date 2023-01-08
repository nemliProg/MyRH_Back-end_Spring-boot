package ma.myrh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.myrh.entity.Entreprise;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long id;

    @NotBlank
    private String title;


    @NotBlank
    @Size(min = 20 , max = 500)
    private String description;

    @NotBlank
    private String profileLookingFor;

    @NotBlank
    private String city;

    @NotBlank
    private String educationalLevel;

    @NotBlank
    private String offerPicture;

    private Double salary;

    private String isAccepted;

    private int nbViews;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    private Entreprise entreprise;

}
