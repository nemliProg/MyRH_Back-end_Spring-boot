package ma.myrh.repo;

import ma.myrh.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Offer findOfferById(Long id);
    List<Offer> findAllByEntrepriseName(String entrepriseName);

    List<Offer> findAllByProfileLookingFor(String profile);

    List<Offer> findAllByEducationalLevel(String educationalLevel);

    List<Offer> findAllByCity(String city);

    List<Offer> findAllBySalaryBetween(Double min, Double max);

}
