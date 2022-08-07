package eapli.base.agvmanagement.application;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.agvmanagement.domain.model.AGVBuilder;
import eapli.base.agvmanagement.domain.model.AGVWorkState;
import eapli.base.agvmanagement.domain.repositories.AGVRepository;

import eapli.base.spomsp.SPOMSP;
import eapli.base.spomsp.services.AgvDigitalTwinService;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class AgvManagementService {

    private final AGVRepository agvRepository;
    private final AgvDigitalTwinService agvDigitalTwinService = new AgvDigitalTwinService();

    @Autowired
    public AgvManagementService(final AGVRepository agvRepository) {
        this.agvRepository = agvRepository;
    }

    @Transactional
    public AGV addNewAgv(String identifier, String shortDesc, String model, String maxWeight, String maxVolume, AGVDock agvDockId) throws Exception {
        AGVBuilder agvBuilder = new AGVBuilder();
        agvBuilder.with(identifier, shortDesc,model,maxWeight, maxVolume, agvDockId);
        AGV newAGV = agvBuilder.build();
        for (AGV AGV :allAgvs()) {
            if(AGV.agvDockId().toString().equals( newAGV.agvDockId().toString()))
            {
                throw new IntegrityViolationException();
            }
        }
        AGV agv = this.agvRepository.save(newAGV);
        agvDigitalTwinService.sendMessageToServer(SPOMSP.Code.AGVCREATED.code,null);
        return agv;
    }

    public Iterable<AGV> activeAgvs() {
        return this.agvRepository.findByActive(true);
    }

    public Iterable<AGV> deactivatedAgvs() {
        return this.agvRepository.findByActive(false);
    }

    public Iterable<AGV> allAgvs() {
        return this.agvRepository.findAll();
    }

    @Transactional
    public Iterable<AGV> findByAgvWorkState(AGVWorkState agvWorkState){

        return this.agvRepository.findByAgvWorkState(agvWorkState);
    }

    @Transactional
    public Optional<AGV> agvOfIdentity(final String id) {
        return this.agvRepository.ofIdentity(id);
    }

    public AGVRepository getAgvRepository() {
        return agvRepository;
    }
}
