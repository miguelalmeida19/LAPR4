package eapli.base.ordermanagement.domain.domain.model;

import eapli.base.productmanagement.domain.model.ProductIdGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Random;

public class OrderIdGenerator implements IdentifierGenerator{
    private static Logger log = LoggerFactory.getLogger(OrderIdGenerator.class);

    public OrderIdGenerator() {
    }

    public String generateOrderId() {

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

        return "order." + generatedString + "." + number;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String prefix = "Custom_String";
        Connection connection = sharedSessionContractImplementor.connection();
        System.out.println(sharedSessionContractImplementor.connection());

        return generateOrderId();
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return IdentifierGenerator.super.supportsJdbcBatchInserts();
    }
}
