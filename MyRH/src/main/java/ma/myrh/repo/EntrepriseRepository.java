package ma.myrh.repo;


import ma.myrh.entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

    Optional<Entreprise> findByEmail(String email);

    Optional<Entreprise> findByPhone(String phone);

    Optional<Entreprise> findByName(String name);

    boolean getIsVerifiedByEmail(String email);


}
