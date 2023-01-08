package ma.myrh.controller;


import ma.myrh.dto.EntrepriseDto;
import ma.myrh.error.exception.entreprise.EntrepriseNotFoundException;
import ma.myrh.service.agent.AgentService;
import ma.myrh.service.entreprise.EntrepriseService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/agent")
public class AgentController {

    private final AgentService agentService;

    private final EntrepriseService entrepriseService;

    public AgentController(AgentService agentService, EntrepriseService entrepriseService) {
        this.agentService = agentService;
        this.entrepriseService = entrepriseService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello agent";
    }

    @GetMapping("/companies")
    CollectionModel<EntityModel<EntrepriseDto>> allCompanies() {

        List<EntityModel<EntrepriseDto>> companies = entrepriseService.getAllEntreprises().stream()
                .map(company -> {
                    try {
                        return EntityModel.of(company,
                                linkTo(methodOn(AgentController.class).oneCompany(company.getId())).withSelfRel(),
                                linkTo(methodOn(AgentController.class).allCompanies()).withRel("employees"));
                    } catch (EntrepriseNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        return CollectionModel.of(companies, linkTo(methodOn(AgentController.class).allCompanies()).withSelfRel());
    }

    @GetMapping("/companies/{id}")
    EntityModel<EntrepriseDto> oneCompany(@PathVariable Long id) throws EntrepriseNotFoundException {

        EntrepriseDto entreprise = entrepriseService.getEntrepriseById(id) //
                .orElseThrow(() -> new EntrepriseNotFoundException("Entreprise not found with id " + id));

        return EntityModel.of(entreprise, //
                linkTo(methodOn(AgentController.class).oneCompany(id)).withSelfRel(),
                linkTo(methodOn(AgentController.class).allCompanies()).withRel("employees"));
    }

}
