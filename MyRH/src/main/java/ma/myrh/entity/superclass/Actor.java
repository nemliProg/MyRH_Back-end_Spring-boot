package ma.myrh.entity.superclass;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class Actor {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected Long id;

    protected String phone;

    protected String email;

    protected String password;

    public Actor(String phone, String email, String password) {
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

}
