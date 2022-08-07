package eapli.base.ordermanagement.domain.domain.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AGVStatus {
    WAITING("Waiting for an AGV"),
    ASSOCIATED("Associated to an AGV");

    private final String status;

    AGVStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    private static final Map<String, AGVStatus> ENUM_MAP = Stream.of(AGVStatus.values())
            .collect(Collectors.toMap(Enum::toString, Function.identity()));

    public static AGVStatus of(final String name) {
        return ENUM_MAP.get(name);
    }
}
