package eapli.base.warehousemanagement.domain.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Random;

public class WarehouseIdGenerator implements IdentifierGenerator {
    private static Logger log = LoggerFactory.getLogger(WarehouseIdGenerator.class);

    public WarehouseIdGenerator(){
    }

    public String generateWarehouseId(){

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        Random r = new Random( System.currentTimeMillis() );
        String number = String.valueOf(10000 + r.nextInt(20000));

        return "warehouse." + generatedString + "." + number;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String prefix = "Warehouse_String";
        Connection connection = sharedSessionContractImplementor.connection();
        System.out.println(sharedSessionContractImplementor.connection());
        return generateWarehouseId();
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return IdentifierGenerator.super.supportsJdbcBatchInserts();
    }
}
