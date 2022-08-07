package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.AGVBootstrapperBase;
import eapli.base.warehousemanagement.application.ListAGVDocksController;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.framework.actions.Action;

import java.util.List;

public class BackofficeAGVBootstrapper extends AGVBootstrapperBase implements Action {

    private final ListAGVDocksController listAGVDocksController= new ListAGVDocksController();

    @Override
    public boolean execute() {
        try{
            List<AGVDock> agvDockList = (List<AGVDock>) listAGVDocksController.allagvDocks();
            registerAGV("11AA22BB", "Short small robot", "XLR8","400","60",agvDockList.get(0));
            registerAGV("33CC44DD", "Big robot", "TUFF","454","600",agvDockList.get(1));
            registerAGV("77TC48DL", "Big Wall-E", "TUFF2","468","600",agvDockList.get(2));
            registerAGV("55EE66FF", "Bigger robot", "RWR6","600","700",agvDockList.get(3));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }
}
