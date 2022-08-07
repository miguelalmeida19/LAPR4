package eapli.base.customermanagement.domain.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CustomerIdGenerator implements IdentifierGenerator {
    private static Logger log = LoggerFactory.getLogger(CustomerIdGenerator.class);

    public CustomerIdGenerator() {
    }

    public String generateCustomerId() {

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

        return "customer." + generatedString + "." + number;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String prefix = "Custom_String";
        Connection connection = sharedSessionContractImplementor.connection();
        System.out.println(sharedSessionContractImplementor.connection());

        return generateCustomerId();
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return IdentifierGenerator.super.supportsJdbcBatchInserts();
    }
}