package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.model.*;
import eapli.base.warehousemanagement.domain.repositories.AisleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class AislesManagementService {

    private final AisleRepository aisleRepository;
    private final SquaresManagementService squaresManagementService = SquaresRegistry.squaresManagementService();


    @Autowired
    public AislesManagementService(final AisleRepository aisleRepository){
        this.aisleRepository = aisleRepository;
    }

    @Transactional
    public Aisles registerNewAisle(AisleId aisleId, Squares begin, Squares end, Squares depth, Accessibility accessibility, List<AisleRow> rowsList){
        List<Squares> squares = Arrays.asList(new Squares[]{begin, end, depth});
        Aisles aisles = new Aisles(aisleId, squares, accessibility, rowsList);
        return this.aisleRepository.save(aisles);
    }

    public Iterable<Aisles> activeAisles(){
        return this.aisleRepository.findByActive(true);
    }

    public Iterable<Aisles> deactivatedAisles(){
        return this.aisleRepository.findByActive(false);
    }

    public Iterable<Aisles> allAisles(){
        return this.aisleRepository.findAll();
    }

    public Optional<Aisles> aislesOfIdentity(final AisleId id){
        return this.aisleRepository.ofIdentity(id);
    }
}
