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
public class ArchiveEntreprise {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @OneToOne
    @JoinColumn( name = "entreprise_id" )
    private Entreprise entreprise;


    private String reason;

}
