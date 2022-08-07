//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.model.Code;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.productmanagement.domain.model.ProductCategory;
import eapli.base.productmanagement.domain.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.domain.repositories.ProductRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAutoTxProductCategoryRepository extends JpaAutoTxRepository<ProductCategory, Code, Code> implements ProductCategoryRepository {
    public JpaAutoTxProductCategoryRepository(final TransactionalContext autoTx) {
        super(autoTx, "productId");
    }

    public JpaAutoTxProductCategoryRepository(final String puname, final Map properties) {
        super(puname, properties, "categoryCode");
    }

    public Iterable<ProductCategory> findByActive(final boolean active) {
        return this.match("e.active=:active", new Object[]{"active", active});
    }
}
