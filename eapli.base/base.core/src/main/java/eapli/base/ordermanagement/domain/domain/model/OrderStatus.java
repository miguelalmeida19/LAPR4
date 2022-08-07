package eapli.base.ordermanagement.domain.domain.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum OrderStatus {
    PROCESSING("Processing"),
    PREPARED("Prepared by the AGV"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    INVALID("Invalid");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    private static final Map<String, OrderStatus> ENUM_MAP = Stream.of(OrderStatus.values())
            .collect(Collectors.toMap(Enum::toString, Function.identity()));

    public static OrderStatus of(final String name) {
        return ENUM_MAP.getOrDefault(name, INVALID);
    }
}
