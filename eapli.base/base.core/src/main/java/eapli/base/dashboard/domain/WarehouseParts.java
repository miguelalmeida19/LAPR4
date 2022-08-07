package eapli.base.dashboard.domain;

public enum WarehouseParts {
    SQUARE(0),
    AISLES(1),
    AGVDOCK(130),
    AGV(250);

    private final int part;

    WarehouseParts(int part) {
        this.part = part;
    }

    public int part() {
        return part;
    }
}
