package ma.myrh.mapper;


import ma.myrh.dto.OfferDto;
import ma.myrh.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferMapper INSTANCE = Mappers.getMapper( OfferMapper.class );

    OfferDto offerToOfferDto(Offer offer);

    List<OfferDto> offersToOfferDtos(List<Offer> offer);

}
