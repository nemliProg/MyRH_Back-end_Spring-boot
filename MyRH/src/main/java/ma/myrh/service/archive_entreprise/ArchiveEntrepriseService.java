package ma.myrh.service.archive_entreprise;

import ma.myrh.entity.ArchiveEntreprise;

import java.util.List;
import java.util.Optional;

public interface ArchiveEntrepriseService {

    ArchiveEntreprise save(ArchiveEntreprise archiveEntreprise);

    ArchiveEntreprise update(ArchiveEntreprise archiveEntreprise);

    Optional<ArchiveEntreprise> getArchiveEntrepriseById(Long id);

    boolean checkIfArchiveEntrepriseExistByEntrepriseId(Long id);

    List<ArchiveEntreprise> getAllArchivedEntreprises();

    void deleteArchiveEntrepriseByEntrepriseEmail(String email);



}
