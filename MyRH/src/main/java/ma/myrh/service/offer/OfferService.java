package ma.myrh.service.offer;

import ma.myrh.dto.OfferDto;
import ma.myrh.entity.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    OfferDto getOfferDtoById(Long id);

    Offer getOfferById(Long id);

    OfferDto saveOffer(Offer offer);

    OfferDto updateOffer(Offer offer);

    List<OfferDto> getAllOffers();

    List<Offer> getAllOffersByEntrepriseName(String name);

    List<Offer> getAllOffersByProfileLookingFor(String profile);

    List<Offer> getAllOffersByEducationalLevel(String educationalLevel);

    List<Offer> getAllOffersByCity(String city);

    List<Offer> getAllOffersBySalaryRange(Double min, Double max);
}
