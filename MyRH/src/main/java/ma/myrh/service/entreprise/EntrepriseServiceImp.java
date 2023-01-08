package ma.myrh.service.entreprise;

import ma.myrh.dto.EntrepriseDto;
import ma.myrh.entity.Entreprise;
import ma.myrh.mapper.EntrepriseMapper;
import ma.myrh.repo.EntrepriseRepository;
import ma.myrh.service.archive_entreprise.ArchiveEntrepriseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntrepriseServiceImp implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    private final EntrepriseMapper entrepriseMapper;
    private final ArchiveEntrepriseService archiveEntrepriseService;

    public EntrepriseServiceImp(EntrepriseRepository entrepriseRepository, EntrepriseMapper entrepriseMapper, ArchiveEntrepriseService archiveEntrepriseService) {
        this.entrepriseRepository = entrepriseRepository;
        this.entrepriseMapper = entrepriseMapper;
        this.archiveEntrepriseService = archiveEntrepriseService;
    }

    @Override
    public Optional<EntrepriseDto> getEntrepriseByEmail(String email) {
        return Optional.ofNullable(entrepriseMapper.entrepriseToEntrepriseDto(entrepriseRepository.findByEmail(email).get()));
    }

    @Override
    public Optional<EntrepriseDto> getEntrepriseById(Long id) {
        return Optional.ofNullable(entrepriseMapper.entrepriseToEntrepriseDto(entrepriseRepository.findById(id).get()));
    }

    @Override
    public Optional<EntrepriseDto> getEntrepriseByPhone(String phone) {
        return Optional.ofNullable(entrepriseMapper.entrepriseToEntrepriseDto(entrepriseRepository.findByPhone(phone).get()));
    }

    @Override
    public List<EntrepriseDto> getAllEntreprises() {
        return entrepriseMapper.entreprisesToEntrepriseDtos(entrepriseRepository.findAll());
    }

    @Override
    public EntrepriseDto saveEntreprise(Entreprise entreprise) {
        return entrepriseMapper.entrepriseToEntrepriseDto(entrepriseRepository.save(entreprise));
    }

    @Override
    public EntrepriseDto updateEntreprise(Entreprise entreprise) {
        return entrepriseMapper.entrepriseToEntrepriseDto(entrepriseRepository.save(entreprise));
    }

    @Override
    public boolean archiveEntreprise(Long id, String reason) {
        return false;
    }

    @Override
    public boolean checkIfEntrepriseExistByEmail(String email) {
        return entrepriseRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean checkIfEntrepriseExistByPhone(String phone) {
        return entrepriseRepository.findByPhone(phone).isPresent();
    }

    @Override
    public boolean checkIfEntrepriseExistByName(String name) {
        return entrepriseRepository.findByName(name).isPresent();
    }

    @Override
    public boolean checkIfEntrepriseVerifiedByEmail(String email) {
        return entrepriseRepository.findByEmail(email).get().getIsVerified();
    }

    @Override
    public boolean checkIfEntrepriseArchivedByEmail(String email) {
        return archiveEntrepriseService.checkIfArchiveEntrepriseExistByEntrepriseId(
                entrepriseRepository.findByEmail(email).get().getId()
        );
    }

    @Override
    public EntrepriseDto verifyEntrepriseByEmail(String email) {
        Entreprise entreprise = entrepriseRepository.findByEmail(email).get();
        entreprise.setIsVerified(true);
        return updateEntreprise(entreprise);
    }


}