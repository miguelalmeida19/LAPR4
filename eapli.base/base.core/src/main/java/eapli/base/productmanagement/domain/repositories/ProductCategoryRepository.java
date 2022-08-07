//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.productmanagement.domain.repositories;

import eapli.base.productmanagement.domain.model.Code;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.productmanagement.domain.model.ProductCategory;
import eapli.framework.domain.repositories.DomainRepository;

public interface ProductCategoryRepository extends DomainRepository<Code, ProductCategory> {
    Iterable<ProductCategory> findByActive(boolean active);
}
