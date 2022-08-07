//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.agvmanagement.domain.model;

import eapli.base.warehousemanagement.application.AgvModelRegistry;
import eapli.base.warehousemanagement.application.SquaresRegistry;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.base.warehousemanagement.domain.model.Squares;
import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/*
Colocar acceptance criteria aqui!
 */

public class AGVBuilder implements DomainFactory<AGV> {

    private static final Logger LOGGER = LogManager.getLogger(AGVBuilder.class);
    private String identifier;
    private AGVShortDescription shortDescription;
    private AGVModel model;
    private AGVMaxWeight maxWeight;
    private AGVMaxVolume maxVolume;
    private AGVDock agvDockId;
    private Squares position;

    public AGVBuilder() {
    }

    public AGVBuilder with(final String identifier, final String shortDescription,
                           final String model, final String maxWeight, final String maxVolume, final AGVDock agvDockId) {
        this.withIdentifier(identifier);
        this.withShortDescription(shortDescription);
        this.withMaxWeight(maxWeight);
        this.withMaxVolume(maxVolume);
        this.withagvDockId(agvDockId);
        Squares squares = SquaresRegistry.squaresManagementService().registerNewSquare(Long.valueOf(agvDockId.begin().getLsquare()), Long.valueOf(agvDockId.begin().getWsquare()));
        this.position = squares;
        return this;
    }

    public AGVBuilder withIdentifier(final String identifier) {
        if (identifier.matches("^[a-zA-Z0-9_]{8}$")) {
            this.identifier = String.valueOf(identifier);
            return this;
        } else {
            throw new IllegalArgumentException("Identifier must be an alphanumeric with 8 characters.");
        }
    }

    public AGVBuilder withShortDescription(final String shortDescription) {
        int SHORT_DESCRIPTION_MAX = 30;
        if (shortDescription.length() <= SHORT_DESCRIPTION_MAX) {
            this.shortDescription = AGVShortDescription.valueOf(shortDescription);
            return this;
        } else {
            throw new IllegalArgumentException(String.format("Short Description must contain     up to %d characters", SHORT_DESCRIPTION_MAX));
        }
    }

    public AGVBuilder withMaxWeight(final String maxWeight) {
        try {
            if (Double.parseDouble(maxWeight) > 0) {
                this.maxWeight = AGVMaxWeight.valueOf(maxWeight);
                return this;
            } else {
                throw new IllegalArgumentException("Max Weight must be >0!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Max Weight must be >0!");
        }
    }

    public AGVBuilder withMaxVolume(final String maxVolume) {
        try {
            if (Double.parseDouble(maxVolume) > 0) {
                this.maxVolume = AGVMaxVolume.valueOf(maxVolume);
                return this;
            } else {
                throw new IllegalArgumentException("Max Volume must be >0!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Max Volume must be >0!");
        }
    }

    public AGVBuilder withagvDockId(final AGVDock agvDock) {
        try {
            if (agvDock != null) {
                this.agvDockId = agvDock;
                return this;
            } else {
                throw new IllegalArgumentException("No AGV dock!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("No AGV dock!");
        }
    }

    public AGV build() {
        AGV AGV;
        List<AGVModel> agvModelList = (List<AGVModel>) AgvModelRegistry.agvModelManagementService().findAgvByWeigth(this.maxWeight);
        AGVModel agvModel;
        if (!agvModelList.isEmpty()) {
            agvModel = agvModelList.get(0);
        } else {
            agvModel = AgvModelRegistry.agvModelManagementService().registerNewAgvModel(this.maxWeight);
        }

        AGV = new AGV(this.identifier, this.shortDescription, agvModel, this.maxVolume, this.agvDockId, this.position);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new AGV [{}]", this.identifier);
        }

        return AGV;
    }
}

