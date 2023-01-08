package ma.myrh.service.agent;


import ma.myrh.entity.Agent;
import ma.myrh.entity.Entreprise;
import ma.myrh.repo.AgentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AgentServiceImp implements AgentService {

    private final AgentRepository agentRepository;

    public AgentServiceImp(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public Agent getAgentByEmail(String email) {
        return agentRepository.findByEmail(email).orElse(null);
    }
}
