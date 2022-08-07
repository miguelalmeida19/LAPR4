package eapli.base.infrastructure.bootstrapers;

import eapli.base.agvmanagement.application.AddAgvController;
import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AGVBootstrapperBase{
        private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

        final AddAgvController addAgvController = new AddAgvController();

        public AGVBootstrapperBase(){
            super();
        }

        protected AGV registerAGV(final String identifier, final String shortDescription,
                                  final String model, final String maxWeight, final String maxVolume, final AGVDock agvDockId){

            AGV AGV = null;
            try{
                AGV = addAgvController.addAGV(identifier, shortDescription, model, maxWeight, maxVolume, agvDockId);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return AGV;
        }
}
