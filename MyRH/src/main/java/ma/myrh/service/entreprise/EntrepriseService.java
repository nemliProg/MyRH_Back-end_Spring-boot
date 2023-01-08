package ma.myrh.service.entreprise;

import ma.myrh.dto.EntrepriseDto;
import ma.myrh.entity.Entreprise;

import java.util.List;
import java.util.Optional;

public interface EntrepriseService {

    Optional<EntrepriseDto> getEntrepriseByEmail(String email);
    Optional<EntrepriseDto> getEntrepriseById(Long id);
    Optional<EntrepriseDto> getEntrepriseByPhone(String phone);

    List<EntrepriseDto> getAllEntreprises();

    EntrepriseDto saveEntreprise(Entreprise entreprise);
    EntrepriseDto updateEntreprise(Entreprise entreprise);
    boolean archiveEntreprise(Long id, String reason);

    boolean checkIfEntrepriseExistByEmail(String email);
    boolean checkIfEntrepriseExistByPhone(String phone);

    boolean checkIfEntrepriseExistByName(String name);

    boolean checkIfEntrepriseVerifiedByEmail(String email);

    boolean checkIfEntrepriseArchivedByEmail(String email);

    EntrepriseDto verifyEntrepriseByEmail(String email);

}
