//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.persistence.impl.jpa;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.customermanagement.domain.model.CustomerId;
import eapli.base.customermanagement.domain.repositories.CustomerRepository;
import eapli.base.productmanagement.domain.model.*;
import eapli.base.productmanagement.domain.repositories.ProductRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.Map;

public class JpaAutoTxProductRepository extends JpaAutoTxRepository<Product, String, String> implements ProductRepository {
    public JpaAutoTxProductRepository(final TransactionalContext autoTx) {
        super(autoTx, "productId");
    }

    public JpaAutoTxProductRepository(final String puname, final Map properties) {
        super(puname, properties, "productId");
    }

    public Iterable<Product> findByActive(final boolean active) {
        return this.match("e.active=:active", new Object[]{"active", active});
    }

    @Override
    public Iterable<Product> findByProductCategory(ProductCategory productCategory) {
        return this.match("e.productCategory=:productCategory", new Object[]{"productCategory", productCategory});
    }

    @Override
    public Iterable<Product> findByShortDescription(Shortdescription shortDescription) {

        return this.match("e.shortDescription=:shortDescription", new Object[]{"shortDescription", shortDescription});
    }

    @Override
    public Iterable<Product> findByLongDescription(Longdescription longDescription) {
        return this.match("e.longDescription=:longDescription", new Object[]{"longDescription", longDescription});
    }

    @Override
    public Iterable<Product> findByTechnicalDescription(Technicaldescription technicalDescription) {
        return this.match("e.technicalDescription=:technicalDescription", new Object[]{"technicalDescription", technicalDescription});
    }

    @Override
    public Iterable<Product> findByBrand(Brand brand) {
        return this.match("e.brand=:brand", new Object[]{"brand", brand});
    }

    @Override
    public Iterable<Product> findByBrandAndProductCategory(Brand brand, ProductCategory productCategory) {
        return this.match("e.brand=:brand and e.productCategory=:productCategory", new Object[]{"brand", brand, "productCategory", productCategory});
    }
}
