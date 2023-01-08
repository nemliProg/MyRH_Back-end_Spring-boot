package ma.myrh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.myrh.entity.superclass.Actor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agent extends Actor {

    @Column( name = "first_name" )
    protected String firstName;

    @Column( name = "last_name" )
    protected String lastName;

}
