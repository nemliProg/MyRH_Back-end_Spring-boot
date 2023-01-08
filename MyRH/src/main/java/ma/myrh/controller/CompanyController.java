package ma.myrh.controller;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import ma.myrh.dto.OfferDto;
import ma.myrh.entity.Offer;
import ma.myrh.repo.EntrepriseRepository;
import ma.myrh.service.entreprise.EntrepriseService;
import ma.myrh.service.offer.OfferService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/company")
public class CompanyController {


    private final OfferService offerService;

    private final EntrepriseService entrepriseService;

    private final EntrepriseRepository entrepriseRepository;

    private final Cloudinary cloudinary;

    public CompanyController(OfferService offerService, EntrepriseService entrepriseService, EntrepriseRepository entrepriseRepository, Cloudinary cloudinary) {
        this.offerService = offerService;
        this.entrepriseService = entrepriseService;
        this.entrepriseRepository = entrepriseRepository;
        this.cloudinary = cloudinary;
    }

    @GetMapping("/test")
    public String test(Principal principal){
        System.out.println(principal.getName());
        return "test";
    }


    @GetMapping("/myoffers/{id}")
    EntityModel<OfferDto> oneOffer(@PathVariable Long id) {
//        Long companyId = entrepriseService.getEntrepriseByEmail(principal.getName()).get().getId();

        OfferDto offer = offerService.getOfferDtoById(id);

//            if (!Objects.equals(companyId, offer.getEntreprise().getId())) {
//                throw new OfferNotFoundException("this Offer is not yours");
//            }

        return EntityModel.of(offer, //
                linkTo(methodOn(CompanyController.class).oneOffer(id)).withSelfRel(),
                linkTo(methodOn(CompanyController.class).allOffers()).withRel("myoffers"));
    }

    @GetMapping("/myoffers")
    CollectionModel<EntityModel<OfferDto>> allOffers() {

        List<EntityModel<OfferDto>> offers = offerService.getAllOffers().stream()
                .map(offer -> EntityModel.of(offer,
                        linkTo(methodOn(CompanyController.class).oneOffer(offer.getId())).withSelfRel(),
                        linkTo(methodOn(CompanyController.class).allOffers()).withRel("employees")))
                .collect(Collectors.toList());

        return CollectionModel.of(offers, linkTo(methodOn(CompanyController.class).allOffers()).withSelfRel());
    }

    @PostMapping("/addoffer")
    public ResponseEntity<OfferDto> addOffer(@RequestBody OfferDto offerDto, Principal principal) {
        Long companyId = entrepriseService.getEntrepriseByEmail(principal.getName()).get().getId();
        Offer offer = Offer.builder()
                .title(offerDto.getTitle())
                .description(offerDto.getDescription())
                .city(offerDto.getCity())
                .salary(offerDto.getSalary())
                .offerPicture(offerDto.getOfferPicture())
                .educationalLevel(offerDto.getEducationalLevel())
                .profileLookingFor(offerDto.getProfileLookingFor())
                .entreprise(entrepriseRepository.findById(companyId).get())
                .build();

        // upload offer picture
        try {
            Map map = cloudinary.uploader().upload("C:\\Users\\Youcode\\Downloads\\aaayyybbb.JPG", ObjectUtils.asMap("public_id", "ayoub_pic"));
            offer.setOfferPicture(map.get("url").toString());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }



        OfferDto savedOffer = offerService.saveOffer(offer);
        return ResponseEntity.status(201).body(savedOffer);
    }

    @PutMapping("/updateoffer")
    public ResponseEntity<OfferDto> updateOffer(@RequestBody OfferDto offerDto) {

        Long offerId = offerDto.getId();
        Offer offer = offerService.getOfferById(offerId);
        if (offerDto.getTitle() != null) {
            offer.setTitle(offerDto.getTitle());
        }
        if (offerDto.getDescription() != null) {
            offer.setDescription(offerDto.getDescription());
        }
        if (offerDto.getCity() != null) {
            offer.setCity(offerDto.getCity());
        }
        if (offerDto.getSalary() != null) {
            offer.setSalary(offerDto.getSalary());
        }
        if (offerDto.getOfferPicture() != null) {
            offer.setOfferPicture(offerDto.getOfferPicture());
        }
        if (offerDto.getEducationalLevel() != null) {
            offer.setEducationalLevel(offerDto.getEducationalLevel());
        }
        if (offerDto.getProfileLookingFor() != null) {
            offer.setProfileLookingFor(offerDto.getProfileLookingFor());
        }
        offer.setUpdatedAt(LocalDateTime.now());

        OfferDto updatedOffer = offerService.updateOffer(offer);
        return ResponseEntity.status(201).body(updatedOffer);
    }
}
