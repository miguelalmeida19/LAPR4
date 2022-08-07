package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.model.*;
import eapli.base.warehousemanagement.domain.repositories.AisleRepository;
import eapli.base.warehousemanagement.domain.repositories.SquareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class SquaresManagementService {

    private final SquareRepository squareRepository;

    @Autowired
    public SquaresManagementService(final SquareRepository squareRepository){
        this.squareRepository = squareRepository;
    }

    @Transactional
    public Squares registerNewSquare(Long lsquare, Long wsquare){
        Squares squares = new Squares(lsquare, wsquare);
        return this.squareRepository.save(squares);
    }

    @Transactional
    public Squares registerNewSquare(Squares s){
        return this.squareRepository.save(s);
    }

    public Iterable<Squares> activeSquares(){
        return this.squareRepository.findByActive(true);
    }

    public Iterable<Squares> deactivatedSqures(){
        return this.squareRepository.findByActive(false);
    }

    public Iterable<Squares> allSquares(){
        return this.squareRepository.findAll();
    }

    public Optional<Squares> squaresOfIdentity(final Long id){
        return this.squareRepository.ofIdentity(id);
    }

    public SquareRepository getSquareRepository() {
        return squareRepository;
    }
}
