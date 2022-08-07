package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.model.*;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class UploadWarehousePlantController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AddAislesController aislesController = new AddAislesController();
    private final AddRowController addRowController = new AddRowController();
    private final WarehouseManagementService warehouseSvc = WarehouseRegistry.warehouseService();
    private final AddAGVDockController agvDockController = new AddAGVDockController();

    private final SquaresManagementService squaresManagementService = SquaresRegistry.squaresManagementService();


    public Warehouse uploadWarehousePlant() throws IOException, ParseException {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        JSONObject a = (JSONObject) jsonParser.parse(new FileReader("files/warehouse_t.json"));


        JSONObject warehouse = a;

        String warehouseDescription = (String) warehouse.get("Warehouse");

        Long length = (Long) warehouse.get("Length");

        Long width = (Long) warehouse.get("Width");
        Long square = (Long) warehouse.get("Square");
        String unit = (String) warehouse.get("Unit");

        JSONArray aislesJ = (JSONArray) warehouse.get("Aisles");

        List<Aisles> aislesList = new ArrayList<>();
        List<AGVDock> agvDockList = new ArrayList<>();


        for(Object c : aislesJ){

            JSONObject o_aisles = (JSONObject) c;
            AisleId id_a = new AisleId((Long) o_aisles.get("Id"));

            Accessibility accessibility_a = new Accessibility((String) o_aisles.get("accessibility"));


            //JSONObject objectos begin, end e depth
            //begin
            JSONObject begin_a = (JSONObject) o_aisles.get("begin");
            Long b_lsquare = (Long) begin_a.get("lsquare");

            Long b_wsquare = (Long) begin_a.get("wsquare");

            //end
            JSONObject end_a = (JSONObject) o_aisles.get("end");
            Long e_lsquare = (Long) end_a.get("lsquare");

            Long e_wsquare = (Long) end_a.get("wsquare");

            //depth
            JSONObject depth_a = (JSONObject) o_aisles.get("depth");
            Long d_lsquare = (Long) depth_a.get("lsquare");

            Long d_wsquare = (Long) depth_a.get("wsquare");

            Squares begin = squaresManagementService.registerNewSquare(b_lsquare, b_wsquare);
            Squares end = squaresManagementService.registerNewSquare(e_lsquare, e_wsquare);
            Squares depth = squaresManagementService.registerNewSquare(d_lsquare, d_wsquare);
            List<AisleRow> rowsList = new ArrayList<>();

            JSONArray rowsJ = (JSONArray) o_aisles.get("rows");
            for (Object d : rowsJ){
                JSONObject o_rows = (JSONObject) d;
                //id rows
                RowsId id_r = new RowsId(id_a + "_" + o_rows.get("Id")) ;

                //begin
                JSONObject begin_r = (JSONObject) o_rows.get("begin");
                Long r_b_lsquare = (Long) begin_r.get("lsquare");

                Long r_b_wsquare = (Long) begin_r.get("wsquare");

                //end
                JSONObject end_r = (JSONObject) o_rows.get("end");
                Long r_e_lsquare = (Long) end_r.get("lsquare");

                Long r_e_wsquare = (Long) end_r.get("wsquare");

                //shelves
                Shelf shelves = new Shelf((Long) o_rows.get("shelves")) ;

                Squares r_begin = squaresManagementService.registerNewSquare(r_b_lsquare, r_b_wsquare);
                Squares r_end = squaresManagementService.registerNewSquare(r_e_lsquare, r_e_wsquare);
                AisleRow aisleRow = addRowController.addRows(id_r, r_begin, r_end, shelves);
                rowsList.add(aisleRow);
            }

            Aisles aisle = aislesController.addAisles(id_a, begin, end, depth,accessibility_a, rowsList);
            aislesList.add(aisle);
        }

        JSONArray agvs = (JSONArray) warehouse.get("AGVDocks");

        for(Object f : agvs){
            JSONObject o_agv = (JSONObject) f;
            AGVDockId id_agv = new AGVDockId((String) o_agv.get("Id"));


            //begin
            JSONObject agv_begin = (JSONObject) o_agv.get("begin");
            Long agv_b_lsquare = (Long) agv_begin.get("lsquare");

            Long agv_b_wsquare = (Long) agv_begin.get("wsquare");

            //end
            JSONObject end_agv = (JSONObject) o_agv.get("end");
            Long agv_e_lsquare = (Long) end_agv.get("lsquare");

            Long agv_e_wsquare = (Long) end_agv.get("wsquare");

            //depth
            JSONObject depth_agv = (JSONObject) o_agv.get("depth");
            Long agv_d_lsquare = (Long) depth_agv.get("lsquare");

            Long agv_d_wsquare = (Long) depth_agv.get("wsquare");


            Accessibility accessibility_a = new Accessibility((String) o_agv.get("accessibility"));

            Squares agvD_begin = squaresManagementService.registerNewSquare(agv_b_lsquare, agv_b_wsquare);
            Squares agvD_end = squaresManagementService.registerNewSquare(agv_e_lsquare, agv_e_wsquare);
            Squares agvD_depth = squaresManagementService.registerNewSquare(agv_d_lsquare,agv_d_wsquare );

            AGVDock agvDock = agvDockController.addagvDock(id_agv, agvD_begin, agvD_end,agvD_depth, accessibility_a);
            agvDockList.add(agvDock);
        }

        System.out.println("Uploaded successfully!");

        return warehouseSvc.registerNewWarehouse(warehouseDescription, length,width, square,  unit, aislesList, agvDockList);
    }

}
