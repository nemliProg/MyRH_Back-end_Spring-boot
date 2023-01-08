package ma.myrh.service.offer;

import ma.myrh.dto.OfferDto;
import ma.myrh.entity.Offer;
import ma.myrh.mapper.OfferMapper;
import ma.myrh.repo.OfferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class OfferServiceImp implements OfferService {

    private final OfferRepository offerRepository;

    private final OfferMapper offerMapper;



    public OfferServiceImp(OfferRepository offerRepository, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
    }

    @Override
    public OfferDto getOfferDtoById(Long id) {
        return offerMapper.offerToOfferDto(offerRepository.findOfferById(id));
    }

    @Override
    public Offer getOfferById(Long id) {
        return offerRepository.findOfferById(id);
    }


    @Override
    public OfferDto saveOffer(Offer offer) {
        return offerMapper.offerToOfferDto(offerRepository.save(offer));
    }

    @Override
    public OfferDto updateOffer(Offer offer) {
        return offerMapper.offerToOfferDto(offerRepository.save(offer)) ;
    }

    @Override
    public List<OfferDto> getAllOffers() {
        return offerMapper.offersToOfferDtos(offerRepository.findAll());
    }

    @Override
    public List<Offer> getAllOffersByEntrepriseName(String name) {
        return offerRepository.findAllByEntrepriseName(name);
    }

    @Override
    public List<Offer> getAllOffersByProfileLookingFor(String profile) {
        return offerRepository.findAllByProfileLookingFor(profile);
    }

    @Override
    public List<Offer> getAllOffersByEducationalLevel(String educationalLevel) {
        return offerRepository.findAllByEducationalLevel(educationalLevel);
    }

    @Override
    public List<Offer> getAllOffersByCity(String city) {
        return offerRepository.findAllByCity(city);
    }

    @Override
    public List<Offer> getAllOffersBySalaryRange(Double min, Double max) {
        return offerRepository.findAllBySalaryBetween(min, max);
    }
}
