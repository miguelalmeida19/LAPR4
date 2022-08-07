package eapli.base.spomsp.servers;

import com.fasterxml.jackson.databind.ObjectMapper;
import eapli.base.agvmanagement.application.AgvRegistry;
import eapli.base.customermanagement.application.CustomerManagementService;
import eapli.base.customermanagement.application.CustomerRegistry;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.dashboard.domain.HttpServerAgvStatus;
import eapli.base.dashboard.domain.WarehouseParts;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.domain.application.OrderItemRegistry;
import eapli.base.ordermanagement.domain.domain.application.OrderManagementService;
import eapli.base.ordermanagement.domain.domain.application.OrderRegistry;
import eapli.base.ordermanagement.domain.domain.model.OrderDto;
import eapli.base.ordermanagement.domain.domain.model.OrderStatus;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.productmanagement.application.ProductCategoryRegistry;
import eapli.base.productmanagement.application.ProductRegistry;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.productmanagement.domain.model.ProductDto;
import eapli.base.productmanagement.domain.model.ShoppingCart;
import eapli.base.productmanagement.domain.model.ShoppingCartItem;
import eapli.base.spomsp.SPOMSP;
import eapli.base.surveymanagement.application.CreateSurveyAnswerController;
import eapli.base.surveymanagement.application.SurveyAnswerRegistry;
import eapli.base.surveymanagement.application.SurveyManagementService;
import eapli.base.surveymanagement.application.SurveyRegistry;
import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.model.SurveyAnswer;
import eapli.base.surveymanagement.domain.model.SurveyDto;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.warehousemanagement.application.*;
import eapli.base.warehousemanagement.domain.model.*;
import eapli.base.warehousemanagement.domain.repositories.WarehouseRepository;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

class TcpSrvOrders {
    static final String TRUSTED_STORE = "ordersServer.jks";
    static final String KEYSTORE_PASS = "forgotten";
    static SSLServerSocket sock;
    static int serverPortProperties;

    public static void main(String[] args) throws Exception {

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());
        CustomerRegistry.configure(PersistenceContext.repositories().customers());
        ProductRegistry.configure(PersistenceContext.repositories().products());
        ProductCategoryRegistry.configure(PersistenceContext.repositories().productCategories());
        OrderRegistry.configure(PersistenceContext.repositories().orders());
        OrderItemRegistry.configure(PersistenceContext.repositories().orderItems());
        AgvModelRegistry.configure(PersistenceContext.repositories().agvModels());
        SquaresRegistry.configure(PersistenceContext.repositories().squares());
        WarehouseRegistry.configure(PersistenceContext.repositories().warehouses());
        AislesRegistry.configure(PersistenceContext.repositories().aisles());
        RowRegistry.configure(PersistenceContext.repositories().rows());
        AgvRegistry.configure(PersistenceContext.repositories().agvs());
        AGVDockRegistry.configure(PersistenceContext.repositories().agvDocks());
        SurveyRegistry.configure(PersistenceContext.repositories().surveys());
        SurveyAnswerRegistry.configure(PersistenceContext.repositories().surveyAnswers());

        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        Socket cliSock;
        try {
            sock = (SSLServerSocket) sslF.createServerSocket(22);
            sock.setNeedClientAuth(true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while (true) {
            cliSock = sock.accept();
            new Thread(new TcpSrvOrdersThread(cliSock)).start();
        }
    }
}


class TcpSrvOrdersThread implements Runnable, SPOMSP {

    private Socket s;
    private final CustomerManagementService service = CustomerRegistry.customerService();
    private final SurveyManagementService surveyManagementService = SurveyRegistry.surveyService();
    private final OrderManagementService orderManagementService = OrderRegistry.orderService();

    public TcpSrvOrdersThread(Socket cli_s) {
        this.s = cli_s;
    }

    public void run() {
        InetAddress clientIP;

        try {
            clientIP = this.s.getInetAddress();
            System.out.println("==> New client connection from " + clientIP.getHostAddress() + ", port number " + this.s.getPort());

            DataInputStream sIn = new DataInputStream(this.s.getInputStream());
            DataOutputStream sOut = new DataOutputStream(this.s.getOutputStream());
            byte[] clienteMessage = sIn.readNBytes(5);
            if (clienteMessage[1] == 0) {
                System.out.println("==> Customer Test Order Successfully Received");

                //Telling the client that you understand
                System.out.println("==> Send a message to the client that it was understood");
                sendMessage(Code.ACK);

                //Read the option sent to you by Service
                Map.Entry entry = receiveAnswerEntry();
                int option = (int) entry.getKey();


                if (option >= 3) {

                    // here we read the object


                    if (option == SPOMSP.Code.SHOPPINGCART.code) {
                        sendMessage(Code.ACK);

                        ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                        ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                        ShoppingCart shoppingCart = new ShoppingCart();
                        String converted = convert(shoppingCart);
                        sendMessage(Code.GIVE_SHOPPINGCART, converted.getBytes(StandardCharsets.UTF_8));

                        System.out.println("==> Shopping Cart created Successfully!");
                        // write object
                    }

                    if (option == Code.WAREHOUSE_OBSTACLES.code) {
                        sendMessage(Code.ACK);

                        ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                        ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                        // Fazer aqui a brincadeira

                        int[][] matrix = getWarehouseObstacles();

                        String converted = convert(matrix);

                        sendMessage(Code.WAREHOUSE_OBSTACLES, converted.getBytes(StandardCharsets.UTF_8));

                        System.out.println("==> Warehouse obstacles sent Successfully!");
                    }

                    if (option == Code.ROW_SQUARES.code){

                        String rowId = (String) entry.getValue();
                        AisleRow aisleRow = PersistenceContext.repositories().rows().ofIdentity(RowsId.valueOf(rowId)).get();
                        Squares begin = aisleRow.begin();

                        entry = receiveAnswerEntry();
                        option = (int) entry.getKey();

                        sendMessage(Code.ACK);

                        ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                        ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                        if (option == Code.ROW_SQUARES.code) {
                            String begino = begin.toString();
                            System.out.println(begino);

                            String converted = convert(begino);

                            sendMessage(Code.ROW_SQUARES, converted.getBytes(StandardCharsets.UTF_8));
                            System.out.println("==> Position of product orders has been successfully collected and sent to AGV Digital Twin!");
                        }

                    }

                    if (option == Code.GIVE_SHOPPINGCART.code) {
                        LinkedHashMap linkedHashMap = (LinkedHashMap) entry.getValue();
                        ShoppingCart shoppingCart = new ShoppingCart((List<ShoppingCartItem>) linkedHashMap.get("shoppingCart"));

                        entry = receiveAnswerEntry();
                        option = (int) entry.getKey();

                        sendMessage(Code.ACK);

                        ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                        ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                        if (option == Code.ADD_TO_SHOPPINGCART.code) {
                            LinkedHashMap linkedHashMap1 = (LinkedHashMap) entry.getValue();
                            LinkedHashMap linkedHashMap2 = (LinkedHashMap) linkedHashMap1.get("productDto");
                            ShoppingCartItem result = new ShoppingCartItem((ProductDto) new ProductDto((String) linkedHashMap2.get("productId"), (String) linkedHashMap2.get("shortDescription"), (Double) linkedHashMap2.get("price")), (Integer) linkedHashMap1.get("quantity"));

                            shoppingCart.addProduct(result);
                        }

                        sendMessage(Code.GIVE_SHOPPINGCART, convert(shoppingCart).getBytes(StandardCharsets.UTF_8));

                        System.out.println("==> Product added to Shopping Cart!");
                        // escrever product
                    }

                    if (option == Code.CUSTOMER_ORDERS.code) {
                        String username = (String) entry.getValue();
                        Customer customer = service.customerUser(PersistenceContext.repositories().users().ofIdentity(Username.valueOf(username)).get());
                        List<ProductOrder> list = (List<ProductOrder>) orderManagementService.findByCustomerStatus(customer, OrderStatus.DELIVERED);
                        List<OrderDto> orderDtoList = new ArrayList<>();

                        for (ProductOrder productOrder : list) {
                            orderDtoList.add(new OrderDto(productOrder.identity().toString(), productOrder.finalPrice().toString(), productOrder.orderStatus().toString()));
                        }

                        entry = receiveAnswerEntry();
                        option = (int) entry.getKey();

                        sendMessage(Code.ACK);

                        ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                        ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                        if (option == Code.CUSTOMER_ORDERS.code) {
                            sendMessage(Code.CUSTOMER_ORDERS, convert(orderDtoList).getBytes(StandardCharsets.UTF_8));
                            System.out.println("==> Customer orders from user " + username + " have been successfully collected!");
                        }
                    }

                    if (option == Code.SURVEYS_TO_ANSWER.code) {
                        String username = (String) entry.getValue();
                        Customer customer = service.customerUser(PersistenceContext.repositories().users().ofIdentity(Username.valueOf(username)).get());
                        List<Survey> list = customer.surveys();
                        List<SurveyDto> surveyDtoList = new ArrayList<>();

                        for (Survey survey : list) {
                            surveyDtoList.add(new SurveyDto(survey.description(), survey.questionary().getBytes(StandardCharsets.UTF_8)));
                        }

                        entry = receiveAnswerEntry();
                        option = (int) entry.getKey();

                        sendMessage(Code.ACK);

                        ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                        ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                        if (option == Code.SURVEYS_TO_ANSWER.code) {
                            sendMessage(Code.SURVEYS_TO_ANSWER, convert(surveyDtoList).getBytes(StandardCharsets.UTF_8));
                            System.out.println("==> Surveys from user " + username + " have been successfully collected!");
                        }
                    }

                    if (option == Code.SAVE_SURVEY_ANSWER.code) {
                        String questionary = (String) entry.getValue();
                        Survey survey = surveyManagementService.findByQuestionary(questionary).iterator().next();

                        //******************

                        entry = receiveAnswerEntry();
                        option = (int) entry.getKey();

                        sendMessage(Code.ACK);

                        String username = (String) entry.getValue();
                        Customer customer = service.customerUser(PersistenceContext.repositories().users().ofIdentity(Username.valueOf(username)).get());

                        //******************

                        entry = receiveAnswerEntry();
                        option = (int) entry.getKey();

                        sendMessage(Code.ACK);

                        ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                        ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                        if (option == Code.SAVE_SURVEY_ANSWER.code) {
                            String answered = (String) entry.getValue();
                            CreateSurveyAnswerController surveyAnswerController = new CreateSurveyAnswerController();
                            SurveyAnswer surveyAnswer = surveyAnswerController.addSurveyAnswer(answered, survey, customer);

                            sendMessage(Code.SAVE_SURVEY_ANSWER);
                            System.out.println("==> Survey Answer have been successfully saved!");
                        }
                    }
                }

                byte[] clienteMessageEnd = sIn.readNBytes(5);
                if (clienteMessageEnd[1] == 1) {
                    System.out.println("==> End of Client Order Successfully Received");
                    //Dizer ao cliente que entendeu
                    System.out.println("==> Send message to client to close socket");
                    sendMessage(Code.ACK);
                    System.out.println("==> Client " + clientIP.getHostAddress() + ", port number: " + this.s.getPort() + " disconnected");

                } else {
                    System.out.println("==> ERROR: Erro no pacote do Cliente");
                }
            } else {
                System.out.println("==> ERROR: Erro no pacote do Cliente");
            }
        } catch (Exception e) {
            System.out.println("==> ERROR: " + e.getMessage());
        } finally {
            try {
                this.s.close();
            } catch (IOException e) {
                System.out.println("ERROR: Erro ao fechar o Socket");
            }
            System.out.println("==> INFO: Socket Closed Successfully\n\n");
        }
    }


    public List<Product> allProducts() {
        List<Product> products = new ArrayList<>();
        return products;
    }

    @Override
    public Socket getSocket() {
        return s;
    }

    private String convert(Object ob) {
        ObjectMapper obj = new ObjectMapper();
        try {
            // Converting the Java object into a JSON string
            String jsonStr = obj.writeValueAsString(ob);
            // Displaying Java object into a JSON string
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public int[][] getWarehouseObstacles(){
        List<Warehouse> warehouseList = (List<Warehouse>) PersistenceContext.repositories().warehouses().findAll();
        Warehouse warehouse = warehouseList.get(0);

        int widthSquares = warehouse.width().value() / warehouse.square().value();
        int lengthSquares = warehouse.length().value() / warehouse.square().value();

        return buildMatrix(warehouse, widthSquares, lengthSquares);
    }

    public int[][] buildMatrix(Warehouse warehouse, int widthSquares, int lengthSquares){
        WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouses();
        List<Warehouse> warehouses = (List<Warehouse>) warehouseRepository.findAll();
        Warehouse warehouse1 = warehouses.get(0);
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

        return matrix;
    }
}

