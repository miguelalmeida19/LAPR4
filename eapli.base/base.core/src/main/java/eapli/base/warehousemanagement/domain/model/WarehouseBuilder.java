package eapli.base.warehousemanagement.domain.model;

import eapli.base.productmanagement.domain.model.*;
import eapli.base.warehousemanagement.application.AddAislesController;
import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/*
Colocar acceptance criteria aqui!
 */

public class WarehouseBuilder implements DomainFactory<Warehouse> {

    private final AddAislesController addAislesController = new AddAislesController();

    private static final Logger LOGGER = LogManager.getLogger(WarehouseBuilder.class);
    private String warehouseid;
    private Description description;
    private Length length;
    private Width width;
    private Square square;
    private Unit unit;
    private List<Aisles> aislesList;
    private List<AGVDock> agvDocksList;

    public WarehouseBuilder() {
    }

    public WarehouseBuilder with(final String description, final Long length, final Long width, final Long square, final String unit, final List aislesList, final List agvDocksList) {
        this.withDescription(description);
        this.withLength(length);
        this.withWidth(width);
        this.withSquare(square);
        this.withUnit(unit);
        this.withAislesList(aislesList);
        this.withAGVDockList(agvDocksList);
        return this;
    }

    public WarehouseBuilder withDescription(final String description) {
        this.description = Description.valueOf(description);
        return this;
    }

    public WarehouseBuilder withLength(final Long length) {
        this.length = Length.valueOf(length);
        return this;
    }

    public WarehouseBuilder withWidth(final Long width) {
        this.width = Width.valueOf(width);
        return this;
    }

    public WarehouseBuilder withSquare(final Long square) {
        this.square = Square.valueOf(square);
        return this;
    }

    public WarehouseBuilder withUnit(final String unit) {
        this.unit = Unit.valueOf(unit);
        return this;
    }

    public WarehouseBuilder withAislesList(final List<Aisles> aislesList) {
        this.aislesList = aislesList;
        return this;
    }

    public WarehouseBuilder withAGVDockList(final List<AGVDock> agvDockList) {
        this.agvDocksList = agvDockList;
        return this;
    }


    public Warehouse build() {
        Warehouse warehouse;
        warehouse = new Warehouse(this.description, this.length, this.width, this.square, this.unit, this.aislesList, this.agvDocksList);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new warehouse [{}]", this.warehouseid);
        }

        return warehouse;
    }
}
