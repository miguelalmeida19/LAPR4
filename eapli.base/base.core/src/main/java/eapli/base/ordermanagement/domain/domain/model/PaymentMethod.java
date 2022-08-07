package eapli.base.ordermanagement.domain.domain.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PaymentMethod {
    PAYPAL ("Paypal"),
    AMAZON_PAY("Amazon Pay"),
    GOOGLE_PAY("Google Pay"),
    APPLE_PAY("Apple Pay"),
    DIRECT_DEBIT_PAYMENT("Direct Debit Payment"),
    BANK_TRANSFER("Bank Transfer"),
    PREPAID_CARD("Prepaid Card"),
    GIFT_CARD("Gift Card"),
    DIGITAL_CURRENCIES("Digital Currencies"),
    CRYPTOCURRENCY("Cryptocurrency"),
    INVALID("Invalid");

    private final String paymentMethod;

    PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return paymentMethod;
    }

    private static final Map<String, PaymentMethod> ENUM_MAP = Stream.of(PaymentMethod.values())
            .collect(Collectors.toMap(Enum::toString, Function.identity()));

    public static PaymentMethod of(final String name) {
        return ENUM_MAP.getOrDefault(name, INVALID);
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
