package ma.myrh.service.archive_entreprise;

import ma.myrh.entity.ArchiveEntreprise;
import ma.myrh.repo.ArchiveEntrepriseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArchiveEntrepriseServiceImp implements ArchiveEntrepriseService {

    private final ArchiveEntrepriseRepository archiveEntrepriseRepository;

    public ArchiveEntrepriseServiceImp(ArchiveEntrepriseRepository archiveEntrepriseRepository) {
        this.archiveEntrepriseRepository = archiveEntrepriseRepository;
    }

    @Override
    public ArchiveEntreprise save(ArchiveEntreprise archiveEntreprise) {
        return archiveEntrepriseRepository.save(archiveEntreprise);
    }

    @Override
    public ArchiveEntreprise update(ArchiveEntreprise archiveEntreprise) {
        return archiveEntrepriseRepository.save(archiveEntreprise);
    }

    @Override
    public Optional<ArchiveEntreprise> getArchiveEntrepriseById(Long id) {
        return archiveEntrepriseRepository.findById(id);
    }

    @Override
    public boolean checkIfArchiveEntrepriseExistByEntrepriseId(Long id) {
        return archiveEntrepriseRepository.existsArchiveEntrepriseByEntrepriseId(id);
    }

    @Override
    public List<ArchiveEntreprise> getAllArchivedEntreprises() {
        return archiveEntrepriseRepository.findAll();
    }

    @Override
    public void deleteArchiveEntrepriseByEntrepriseEmail(String email) {
        archiveEntrepriseRepository.deleteArchiveEntrepriseByEntrepriseEmail(email);
    }
}
