package ma.myrh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveOffer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @OneToOne
    @JoinColumn( name = "offer_id")
    private Offer offer;


    private String reason;

}
