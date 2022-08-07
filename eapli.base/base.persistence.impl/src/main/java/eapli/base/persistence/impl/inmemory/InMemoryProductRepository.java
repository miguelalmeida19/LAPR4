//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.persistence.impl.inmemory;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.customermanagement.domain.model.CustomerId;
import eapli.base.customermanagement.domain.repositories.CustomerRepository;
import eapli.base.productmanagement.domain.model.*;
import eapli.base.productmanagement.domain.repositories.ProductRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryProductRepository extends InMemoryDomainRepository<Product, String> implements ProductRepository {
    public InMemoryProductRepository() {
    }

    public Iterable<Product> findByActive(final boolean active) {
        return this.match((e) -> {
            return e.isActive() == active;
        });
    }

    @Override
    public Iterable<Product> findByProductCategory(ProductCategory productCategory) {
        return this.match((e) -> {
            return e.category() == productCategory;
        });
    }

    @Override
    public Iterable<Product> findByShortDescription(Shortdescription shortdescription) {
        return this.match((e) -> {
            return e.shortDescription() == shortdescription;
        });
    }

    @Override
    public Iterable<Product> findByLongDescription(Longdescription longdescription) {
        return this.match((e) -> {
            return e.longDescription() == longdescription;
        });
    }

    @Override
    public Iterable<Product> findByTechnicalDescription(Technicaldescription technicaldescription) {
        return this.match((e) -> {
            return e.technicalDescription() == technicaldescription;
        });
    }

    @Override
    public Iterable<Product> findByBrand(Brand brand) {
        return this.match((e) -> {
            return e.brand() == brand;
        });
    }

    @Override
    public Iterable<Product> findByBrandAndProductCategory(Brand brand, ProductCategory productCategory) {
        return this.match((e) -> {
            return e.brand() == brand && e.category() == productCategory;
        });
    }
}
