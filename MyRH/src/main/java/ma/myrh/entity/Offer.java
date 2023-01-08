package ma.myrh.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Builder
    public Offer(
            String title,
            String description,
            String profileLookingFor,
            String city,
            String educationalLevel,
            String offerPicture,
            Double salary,
            Entreprise entreprise
    ) {
        this.title = title;
        this.description = description;
        this.profileLookingFor = profileLookingFor;
        this.city = city;
        this.educationalLevel = educationalLevel;
        this.offerPicture = offerPicture;
        this.salary = salary;
        this.isAccepted = "PENDING";
        this.nbViews = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.entreprise = entreprise;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    Long id;

    @Column( nullable = false)
    String title;

    @Column( nullable = false)
    String description;


    @Column( name = "profile_looking_for", nullable = false)
    String profileLookingFor;

    @Column( nullable = false)
    String city;

    @Column( name = "educational_level" , nullable = false)
    String educationalLevel;

    @Column( name = "offer_picture")
    String offerPicture;


    Double salary;

    @Column( name = "is_accepted")
    String isAccepted;

    @Column( name = "nb_views")
    int nbViews;

    // Time Stamp
    @Column( name = "created_at")
    LocalDateTime createdAt;

    @Column( name = "updated_at")
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn( name = "entreprise_id" )
    private Entreprise entreprise;

    @OneToOne(mappedBy = "offer")
    private ArchiveOffer archiveOffer;


}
