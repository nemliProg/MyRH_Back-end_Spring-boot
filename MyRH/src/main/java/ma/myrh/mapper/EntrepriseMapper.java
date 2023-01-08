package ma.myrh.mapper;

import ma.myrh.dto.EntrepriseDto;
import ma.myrh.entity.Entreprise;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntrepriseMapper {

    EntrepriseMapper INSTANCE = Mappers.getMapper( EntrepriseMapper.class );

    EntrepriseDto entrepriseToEntrepriseDto(Entreprise entreprise);

    List<EntrepriseDto> entreprisesToEntrepriseDtos(List<Entreprise> entreprise);
}
