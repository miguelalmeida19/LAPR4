package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.model.*;
import eapli.base.warehousemanagement.domain.repositories.AGVDockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class AGVDockManagementService {

    private final AGVDockRepository agvDockRepository;
    private final SquaresManagementService squaresManagementService = SquaresRegistry.squaresManagementService();

    @Autowired
    public AGVDockManagementService(final AGVDockRepository agvDockRepository){
        this.agvDockRepository = agvDockRepository;
    }

    @Transactional
    public AGVDock registerNewAGVDock(final AGVDockId agvDockId, final Squares begin, final Squares end, final Squares depth, final Accessibility accessibility){
        AGVDock agvDock = new AGVDock(agvDockId, begin, end,depth, accessibility);
        return this.agvDockRepository.save(agvDock);
    }

    public Iterable<AGVDock> activeAGVDocks(){
        return this.agvDockRepository.findByActive(true);
    }

    public Iterable<AGVDock> deactivatedAGVDocks(){
        return this.agvDockRepository.findByActive(false);
    }

    public Iterable<AGVDock> allAGVDocks(){
        return this.agvDockRepository.findAll();
    }

    public Optional<AGVDock> agvDockOfIdentity(final AGVDockId id){
        return this.agvDockRepository.ofIdentity(id);
    }
}
