//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.productmanagement.domain.repositories;

import eapli.base.productmanagement.domain.model.*;
import eapli.framework.domain.repositories.DomainRepository;

public interface ProductRepository extends DomainRepository<String, Product> {
    Iterable<Product> findByActive(boolean active);

    Iterable<Product> findByProductCategory(ProductCategory productCategory);

    Iterable<Product> findByShortDescription(Shortdescription shortdescription);

    Iterable<Product> findByLongDescription(Longdescription longdescription);

    Iterable<Product> findByTechnicalDescription(Technicaldescription technicaldescription);

    Iterable<Product> findByBrand(Brand brand);

    Iterable<Product> findByBrandAndProductCategory(Brand brand, ProductCategory category);
}
