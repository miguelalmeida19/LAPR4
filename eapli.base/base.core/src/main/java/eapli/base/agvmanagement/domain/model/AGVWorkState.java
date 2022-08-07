package eapli.base.agvmanagement.domain.model;

import eapli.base.ordermanagement.domain.domain.model.AGVStatus;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AGVWorkState {
    AVAILABLE("Waiting for a task"),
    WORKING("Associated to an AGV");

    private final String state;

    AGVWorkState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }

    private static final Map<String, AGVWorkState> ENUM_MAP = Stream.of(AGVWorkState.values())
            .collect(Collectors.toMap(Enum::toString, Function.identity()));

    public static AGVWorkState of(final String name) {
        return ENUM_MAP.get(name);
    }
}
