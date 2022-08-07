package eapli.base.warehousemanagement.application;

import eapli.base.agvmanagement.domain.model.AGVMaxWeight;
import eapli.base.agvmanagement.domain.model.AGVModel;
import eapli.base.agvmanagement.domain.repositories.AgvModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class AgvModelManagementService {

    private final AgvModelRepository agvModelRepository;

    @Autowired
    public AgvModelManagementService(final AgvModelRepository agvModelRepository){
        this.agvModelRepository = agvModelRepository;
    }

    @Transactional
    public AGVModel registerNewAgvModel(AGVMaxWeight agvMaxWeight){
        AGVModel agvModel = new AGVModel(agvMaxWeight);
        return this.agvModelRepository.save(agvModel);
    }

    public Iterable<AGVModel> activeAgvModels(){
        return this.agvModelRepository.findByActive(true);
    }

    public Iterable<AGVModel> deactivatedAgvs(){
        return this.agvModelRepository.findByActive(false);
    }

    public Iterable<AGVModel> allAgvModels(){
        return this.agvModelRepository.findAll();
    }

    public Optional<AGVModel> agvModelOfIdentity(final Long id){
        return this.agvModelRepository.ofIdentity(id);
    }

    public Iterable<AGVModel> findAgvByWeigth(final AGVMaxWeight agvMaxWeight){return this.agvModelRepository.findByWeigth(agvMaxWeight);}
}
