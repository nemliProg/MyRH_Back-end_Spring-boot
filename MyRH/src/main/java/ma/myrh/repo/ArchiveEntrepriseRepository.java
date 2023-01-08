package ma.myrh.repo;

import ma.myrh.entity.ArchiveEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveEntrepriseRepository extends JpaRepository<ArchiveEntreprise, Long> {

    boolean existsArchiveEntrepriseByEntrepriseId(Long entrepriseId);

    void deleteArchiveEntrepriseByEntrepriseEmail(String email);



}
