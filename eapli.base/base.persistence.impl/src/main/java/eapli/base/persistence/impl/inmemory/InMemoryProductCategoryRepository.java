//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.persistence.impl.inmemory;

import eapli.base.productmanagement.domain.model.Code;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.productmanagement.domain.model.ProductCategory;
import eapli.base.productmanagement.domain.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.domain.repositories.ProductRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryProductCategoryRepository extends InMemoryDomainRepository<ProductCategory, Code> implements ProductCategoryRepository {
    public InMemoryProductCategoryRepository() {
    }

    public Iterable<ProductCategory> findByActive(final boolean active) {
        return this.match((e) -> {
            return e.isActive() == active;
        });
    }
}
