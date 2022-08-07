//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.agvmanagement.domain.repositories;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.agvmanagement.domain.model.AGVWorkState;
import eapli.framework.domain.repositories.DomainRepository;

public interface AGVRepository extends DomainRepository<String, AGV> {
    Iterable<AGV> findByActive(boolean active);

    Iterable<AGV> findByAgvWorkState(AGVWorkState agvWorkState);

}
