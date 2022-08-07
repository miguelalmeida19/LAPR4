package eapli.base.dashboard.domain;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.dashboard.application.ShowDashboardController;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spomsp.SPOMSP;
import eapli.base.spomsp.services.AgvDigitalTwinService;
import eapli.base.warehousemanagement.domain.model.*;
import eapli.base.warehousemanagement.domain.repositories.WarehouseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

public class HttpServerAgvStatus extends Thread {
    static private final String BASE_FOLDER = "base.core/src/main/java/eapli/base/dashboard/domain/www";
    static final String TRUSTED_STORE = "server_http.jks";
    private final static AuthorizationService authz = AuthzRegistry.authorizationService();
    private final static String name = authz.session().get().authenticatedUser().name().toString();
    private final static String username = authz.session().get().authenticatedUser().username().toString();
    private final static String email = authz.session().get().authenticatedUser().email().toString();

    static WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouses();
    static List<Warehouse> warehouseList = (List<Warehouse>) warehouseRepository.findAll();
    static Warehouse warehouse = warehouseList.get(0);
    static ArrayList<String> agvsPositions;
    private static final AgvDigitalTwinService agvDigitalTwinService = new AgvDigitalTwinService();


    private static ShowDashboardController controller;

    static final int PORT = 55034;

    static private SSLServerSocket sock;

    public HttpServerAgvStatus() {
    }

    public void changeController(ShowDashboardController controller) {
        this.controller = controller;
    }
    static AppSettings app = Application.settings();
    static final String KEYSTORE_PASS = "forgotten";

    static final String keyStorePassProperties = app.getKeyStorePass();

    @Override
    public void run() {

        SSLSocket cliSock = null;

        // TRUSTED_STORE -> "serverHTTP.jks"
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);

        // KEYSTORE_PASS -> "forgotten"
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        try {
            SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sock = (SSLServerSocket) sslF.createServerSocket(PORT);
            System.out.println("Dashboard running on: " + "http://localhost:55034/");

        } catch (IOException ex) {
            System.out.println("Server failed to open local port " + PORT);
            System.exit(1);
        }

        while (true) {
            try {
                cliSock = (SSLSocket) sock.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpAgvStatusRequest req = new HttpAgvStatusRequest(cliSock, BASE_FOLDER);
            req.start();
        }
    }

    // DATA ACCESSED BY THREADS - LOCKING REQUIRED

    public static synchronized String getPersonalInfo() {
        //controller.metodo();
        return "<div class=\"welcome\" id=\"personalInformation\">\n" +
                "            <p>\n" +
                "                <strong>Hi " + name + "</strong>, welcome back!\n" +
                "            </p>\n" +
                "        </div>";
    }

    @Transactional
    public String refreshDashboard() {
        try {

            agvsPositions = (ArrayList<String>) askForAllAgvsPositions();

            int widthSquares = warehouse.width().value() / warehouse.square().value();
            int lengthSquares = warehouse.length().value() / warehouse.square().value();

            StringBuilder s = new StringBuilder();

            StringBuilder firstRow = buildFirstLine(lengthSquares);
            s.append(firstRow);
            int[][] matrix = buildMatrix(warehouse, widthSquares, lengthSquares);
            String[][] columnsMatrix = buildColumnsMatrix(warehouse, widthSquares, lengthSquares);
            addSquares(widthSquares, lengthSquares, s, matrix, columnsMatrix);

            return s.toString();
        } catch (NullPointerException ne) {
            System.out.println(ne.getMessage());
            return " ";
        }
    }

    private static StringBuilder buildFirstLine(int lengthSquares) {
        StringBuilder firstRow = new StringBuilder();
        firstRow.append("<div class=\"divTableRow\">\n");
        firstRow.append("<div class=\"divTableCell\">\n" +
                "                                <div class=\"indi2\">\n" +
                "                                    <div class=\"indicador\"></div>\n" +
                "                                </div>\n" +
                "                            </div>");
        for (int i = 0; i < lengthSquares; i++) {
            firstRow.append("<div class=\"divTableCell\">\n" +
                    "                                <div class=\"indi\">\n" +
                    "                                    <div class=\"indicador\">" + (i + 1) + "</div>\n" +
                    "                                </div>\n" +
                    "                            </div>");
        }
        firstRow.append("</div>");
        return firstRow;
    }

    private static String[][] buildColumnsMatrix(Warehouse warehouse, int widthSquares, int lengthSquares){

        Warehouse warehouse1 = warehouse;
        String[][] matrix = new String[widthSquares][lengthSquares];

        for (Aisles a: warehouse1.aislesList()){
            Squares depth = a.depth();
            int depthL = Integer.parseInt(depth.toString().split(" ")[1]);

            for (AisleRow r : a.rows()){
                Squares begin = r.begin();
                Squares end = r.end();

                int beginW = Integer.parseInt(begin.toString().split(" ")[0]);
                int beginL = Integer.parseInt(begin.toString().split(" ")[1]);

                int endW = Integer.parseInt(end.toString().split(" ")[0]);
                int endL = Integer.parseInt(end.toString().split(" ")[1]);

                matrix[beginL-1][beginW-1] = r.identity().toString();
                matrix[endL-1][endW-1] = r.identity().toString();


                if (Math.abs(endW-beginW)!=0){
                    int difference = Math.abs(endW-beginW);
                    int first;
                    if (endW>beginW){
                        first = beginW-1;
                    }else {
                        first = endW-1;
                    }
                    for (int o=0; o<=difference; o++){
                        matrix[beginL-1][first] = r.identity().toString();
                        first++;
                    }
                    if (endW>beginW){
                        first = beginW-1;
                    }else {
                        first = endW-1;
                    }
                    for (int o=0; o<=difference; o++){
                        matrix[depthL-1][first] = r.identity().toString();
                        first++;
                    }
                }
            }
        }
        return matrix;
    }

    public static int[][] buildMatrix(Warehouse warehouse, int widthSquares, int lengthSquares){
        Warehouse warehouse1 =warehouse;
        List<Aisles> aisles = warehouse1.aislesList();

        int[][] matrix = new int[widthSquares][lengthSquares];

        //first insert all aisles
        for (Aisles a: aisles){
            Squares begin = a.begin();
            Squares end = a.end();
            Squares depth = a.depth();

            int beginW = Integer.parseInt(begin.toString().split(" ")[0]);
            int beginL = Integer.parseInt(begin.toString().split(" ")[1]);

            int endW = Integer.parseInt(end.toString().split(" ")[0]);
            int endL = Integer.parseInt(end.toString().split(" ")[1]);

            int depthW = Integer.parseInt(depth.toString().split(" ")[0]);
            int depthL = Integer.parseInt(depth.toString().split(" ")[1]);

            matrix[beginL-1][beginW-1] = Integer.parseInt(a.identity().toString());
            matrix[endL-1][endW-1] = Integer.parseInt(a.identity().toString());

            if (Math.abs(endW-beginW)!=0){
                int difference = Math.abs(endW-beginW);
                int first;
                if (endW>beginW){
                    first = beginW-1;
                }else {
                    first = endW-1;
                }
                for (int o=0; o<=difference; o++){
                    matrix[beginL-1][first] = Integer.parseInt(a.identity().toString());
                    first++;
                }
                if (endW>beginW){
                    first = beginW-1;
                }else {
                    first = endW-1;
                }
                for (int o=0; o<=difference; o++){
                    matrix[depthL-1][first] = Integer.parseInt(a.identity().toString());
                    first++;
                }

            }
        }

        //insert all agv docks
        for (AGVDock agvDock : warehouse1.agvDockList()){
            Squares begin = agvDock.begin();
            Squares end = agvDock.end();

            int beginW = Integer.parseInt(begin.toString().split(" ")[0]);
            int beginL = Integer.parseInt(begin.toString().split(" ")[1]);

            int endW = Integer.parseInt(end.toString().split(" ")[0]);
            int endL = Integer.parseInt(end.toString().split(" ")[1]);

            matrix[beginL-1][beginW-1] = WarehouseParts.AGVDOCK.part();
            matrix[endL-1][endW-1] = WarehouseParts.AGVDOCK.part();
        }

        //update agvs
        for (String pos : agvsPositions){
            matrix[Integer.parseInt(pos.split(" ")[1])][Integer.parseInt(pos.split(" ")[0])] = WarehouseParts.AGV.part();
        }

        return matrix;
    }

    private static void addSquares(int widthSquares, int lengthSquares, StringBuilder s, int[][] matrix, String[][] columnsMatrix) {
        for (int j = 0; j < widthSquares; j++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<div class=\"divTableRow\">");
            stringBuilder.append("<div class=\"divTableCell\">\n" +
                    "                                <div class=\"indi\">\n" +
                    "                                    <div class=\"indicador\">" + (j + 1) + "</div>\n" +
                    "                                </div>\n" +
                    "                            </div>");
            for (int g = 0; g < lengthSquares; g++) {

                String id="";
                String text="empty";
                String text1= "";
                String text2 = "";

                if (matrix[j][g]!=0 && matrix[j][g]!=WarehouseParts.AGVDOCK.part()){
                    id = "aisle";
                    text = "A" + matrix[j][g];
                    text1 = "A" + matrix[j][g];
                    if (columnsMatrix[j][g]!=null){
                        if (Integer.parseInt(columnsMatrix[j][g].split("_")[0]) == matrix[j][g]){
                            text2 = "R" + columnsMatrix[j][g];
                        }
                    }
                }
                if (matrix[j][g]==WarehouseParts.AGVDOCK.part()){
                    id = "agvdock";
                    text="dock";
                    text1="D";
                }
                if (matrix[j][g]==WarehouseParts.AGV.part()){
                    id = "agv";
                    text="agvrobot";
                    text1="<img src='https://i.ibb.co/sJm3hPH/AGV.png\" alt=\"Group-1' style=\"width: 45px\" alt=\"\">";
                }

                else {
                    id = "empty";
                }


                stringBuilder.append("<div class=\"divTableCell\">\n" +
                        "                                <div class=\"indi1\">\n" +
                        "                                    <div class=\"indicador " + text + " " + text2 + "\" id=\"" + id + "\">" + text1 +"</div>\n" +
                        "                                </div>\n" +
                        "                            </div>");
            }
            stringBuilder.append("</div>");
            s.append(stringBuilder);
        }
    }

    @Transactional
    public Object askForAllAgvsPositions() {
        Object list;
        try {
            // Asks AGV Manager the positions of other AGV running
            list = agvDigitalTwinService.sendMessageToServer(SPOMSP.Code.RUNNING_AGVS.code, null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return list;
    }
}
