package ma.myrh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import ma.myrh.entity.superclass.Actor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entreprise extends Actor {

    @Builder
    public Entreprise(
            String phone,
            String email,
            String password,
            String profilePicture,
            String name,
            String address,
            String description,
            String website,
            String linkedin,
            String verificationCode,
            LocalDateTime timeVerificationCode
    ) {
        super(phone, email, password);
        this.profilePicture = profilePicture;
        this.name = name;
        this.address = address;
        this.description = description;
        this.website = website;
        this.linkedin = linkedin;
        this.verificationCode = verificationCode;
        this.timeVerificationCode = timeVerificationCode;
        this.isVerified = false;
    }

    @Column( name = "profile_picture" )
    String profilePicture;//

    @Column( nullable = false)
    String address;//

    @Column( nullable = false)
    String name;//

    @Column( nullable = false)
    String description;//


    String website;//


    String linkedin;//


    @Column( name = "verification_code")
    String verificationCode;

    @Column( name = "time_verification_code" )
    LocalDateTime timeVerificationCode;

    @Column( name = "is_verified", columnDefinition = "boolean default false")
    Boolean isVerified;

    @OneToMany(mappedBy = "entreprise")
    List<Offer> offers;

    @OneToOne(mappedBy = "entreprise")
    ArchiveEntreprise archiveEntreprise;

}
