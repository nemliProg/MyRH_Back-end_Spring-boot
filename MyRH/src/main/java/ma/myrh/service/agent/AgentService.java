package ma.myrh.service.agent;


import ma.myrh.entity.Agent;
import ma.myrh.entity.Entreprise;

import java.util.Optional;

public interface AgentService {

    Agent getAgentByEmail(String email);

}
