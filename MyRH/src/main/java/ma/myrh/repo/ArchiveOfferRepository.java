package ma.myrh.repo;

import ma.myrh.entity.ArchiveOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveOfferRepository extends JpaRepository<ArchiveOffer, Long> {

}
