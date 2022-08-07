package eapli.base.spomsp.servers;

import com.fasterxml.jackson.databind.ObjectMapper;
import eapli.base.agvdigitaltwin.SharedMemory;
import eapli.base.agvdigitaltwin.modules.AgvDigitalTwin;
import eapli.base.agvmanagement.application.AgvManagementService;
import eapli.base.agvmanagement.application.AgvRegistry;
import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.agvmanagement.domain.model.AGVWorkState;
import eapli.base.customermanagement.application.CustomerRegistry;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.domain.application.OrderItemRegistry;
import eapli.base.ordermanagement.domain.domain.application.OrderManagementService;
import eapli.base.ordermanagement.domain.domain.application.OrderRegistry;
import eapli.base.ordermanagement.domain.domain.model.AGVStatus;
import eapli.base.ordermanagement.domain.domain.model.OrderStatus;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.productmanagement.application.ProductCategoryRegistry;
import eapli.base.productmanagement.application.ProductRegistry;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.spomsp.SPOMSP;
import eapli.base.surveymanagement.application.SurveyAnswerRegistry;
import eapli.base.surveymanagement.application.SurveyRegistry;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.warehousemanagement.application.*;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.base.warehousemanagement.domain.model.Squares;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ServerSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;

class TcpAGVManager {
    static final String TRUSTED_STORE = "agvsServer.jks";
    static final String KEYSTORE_PASS = "forgotten";
    static ServerSocket sock;

    public static void main(String[] args) throws Exception {
        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());
        CustomerRegistry.configure(PersistenceContext.repositories().customers());
        ProductRegistry.configure(PersistenceContext.repositories().products());
        ProductCategoryRegistry.configure(PersistenceContext.repositories().productCategories());
        OrderRegistry.configure(PersistenceContext.repositories().orders());
        OrderItemRegistry.configure(PersistenceContext.repositories().orderItems());
        SquaresRegistry.configure(PersistenceContext.repositories().squares());
        WarehouseRegistry.configure(PersistenceContext.repositories().warehouses());
        AislesRegistry.configure(PersistenceContext.repositories().aisles());
        RowRegistry.configure(PersistenceContext.repositories().rows());
        AgvRegistry.configure(PersistenceContext.repositories().agvs());
        AGVDockRegistry.configure(PersistenceContext.repositories().agvDocks());
        SurveyRegistry.configure(PersistenceContext.repositories().surveys());
        SurveyAnswerRegistry.configure(PersistenceContext.repositories().surveyAnswers());
        OrderManagementService orderManagementService = OrderRegistry.orderService();
        List<ProductOrder> orderstodo = Collections.synchronizedList((List<ProductOrder>) orderManagementService.findByAgvStatus(AGVStatus.WAITING));

        //System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        //System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        //System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        //System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        ServerSocketFactory sslF = ServerSocketFactory.getDefault();
        Socket cliSock;
        try {
            sock = sslF.createServerSocket(23);
            //sock.setNeedClientAuth(true);
            System.out.println("Server socket opened");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while (true) {
            cliSock = sock.accept();
            new Thread(new TcpAGVManagerThread(cliSock, orderstodo)).start();
        }
    }
}

class TcpAGVManagerThread implements Runnable, SPOMSP {

    private Socket s;
    private final List<ProductOrder> ordersToDo;

    public TcpAGVManagerThread(Socket cli_s, List<ProductOrder> ordersToDo) {
        this.s = cli_s;
        this.ordersToDo = ordersToDo;
    }

    @Transactional
    public void run() {

        InetAddress clientIP;

        try {
            clientIP = this.s.getInetAddress();
            System.out.println("==> New connection from " + clientIP.getHostAddress() + ", port number " + this.s.getPort());

            DataInputStream sIn = new DataInputStream(this.s.getInputStream());
            DataOutputStream sOut = new DataOutputStream(this.s.getOutputStream());
            byte[] clienteMessage = sIn.readNBytes(5);
            if (clienteMessage[1] == 0) {
                System.out.println("==> Message Successfully Received");

                //Telling the client that you understand
                System.out.println("==> Send a message that it was understood");
                sendMessage(Code.ACK);

                //Read the option sent to you by Service
                Map.Entry entry = receiveAnswerEntry();
                int option = (int) entry.getKey();

                if (option == Code.TASKDONE.code) {

                    System.out.println("==> An AGV has successfully done a task!");

                    String agvId = (String) entry.getValue();
                    AGV agv = AgvRegistry.agvService().agvOfIdentity(agvId).get();
                    ProductOrder productOrder = agv.order();
                    productOrder.setOrderStatus(OrderStatus.PREPARED);
                    agv.setAgvWorkState(AGVWorkState.AVAILABLE);
                    agv.setOrder(null);
                    AgvRegistry.agvService().getAgvRepository().save(agv);
                    OrderRegistry.orderService().getOrderRepository().save(productOrder);

                    try {
                        List<AGV> availableAGVs = (List<AGV>) AgvRegistry.agvService().findByAgvWorkState(AGVWorkState.AVAILABLE);
                        System.out.println("Available Agvs: " + availableAGVs);
                        System.out.println("Orders to do: " + ordersToDo.size());
                        //List<AGVDock> agvDocks = (List<AGVDock>) PersistenceContext.repositories().agvDocks();
                        // mudar posição
                        //agv.setPosition(agvDocks.get(0).begin());
                        //PersistenceContext.repositories().agvs().save(agv);
                        if (!availableAGVs.isEmpty()) {
                            synchronized (AgvRegistry.agvService().getAgvRepository()) {
                                assignTasks();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("==> No tasks available to assign for now");
                    }
                }

                if (option == Code.TASKCREATED.code) {
                    System.out.println("==> An order has been successfully created!");
                    String orderReceived = (String) entry.getValue();
                    System.out.println("Order received: " + orderReceived);
                    OrderManagementService orderManagementService = OrderRegistry.orderService();
                    ordersToDo.add(orderManagementService.orderOfIdentity(orderReceived).get());

                    List<AGV> availableAGVs = (List<AGV>) AgvRegistry.agvService().findByAgvWorkState(AGVWorkState.AVAILABLE);
                    System.out.println("Available Agvs: " + availableAGVs);
                    System.out.println("Orders to do: " + ordersToDo.size());
                    if (!availableAGVs.isEmpty()) {
                        assignTasks();
                    }
                }

                if (option == Code.FORCETASK.code) {
                    System.out.println("==> An order has been prioritized!");
                    String orderSent = (String) entry.getValue();//RECEIVE ORDER BY MSG
                    OrderManagementService orderManagementService = OrderRegistry.orderService();
                    ProductOrder po = orderManagementService.orderOfIdentity(orderSent).get();


                    synchronized (this) {
                        ProductOrder[] myArray = ordersToDo.toArray(ProductOrder[]::new);
                        for (int i = 0; i < myArray.length - 1; i++) {
                            myArray[i + 1] = myArray[i];
                        }
                        myArray[0] = po;

                        ordersToDo.clear();
                        ordersToDo.addAll(Arrays.asList(myArray));
                    }


                    List<AGV> availableAGVs = (List<AGV>) AgvRegistry.agvService().findByAgvWorkState(AGVWorkState.AVAILABLE);
                    System.out.println("Available Agvs: " + availableAGVs);
                    System.out.println("Orders to do: " + ordersToDo.size());
                    if (!availableAGVs.isEmpty()) {
                        assignTasks();
                    }
                }

                if (option == Code.AGVCREATED.code) {
                    System.out.println("==> An AGV has been created!");

                    List<AGV> availableAGVs = (List<AGV>) AgvRegistry.agvService().findByAgvWorkState(AGVWorkState.AVAILABLE);
                    System.out.println("Available Agvs: " + availableAGVs);
                    System.out.println("Orders to do: " + ordersToDo.size());
                    if (!availableAGVs.isEmpty()) {
                        assignTasks();
                    }
                }

                if (option == Code.RUNNING_AGVS.code) {

                    sendMessage(Code.ACK);

                    ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                    ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                    System.out.println("==> Simulation Engine is requesting info about other AGV's positions");
                    LinkedHashMap<AGV, Thread> map = SharedMemory.getInstance().getThreads();
                    List<String> squaresList = new ArrayList<>();
                    for (AGV agv : map.keySet()) {
                        Squares squares = agv.agvPosition();
                        squaresList.add(squares.toString());
                    }
                    ObjectMapper Obj = new ObjectMapper();
                    String jsonStr = Obj.writeValueAsString(squaresList);

                    sendMessage(Code.RUNNING_AGVS, jsonStr.getBytes(StandardCharsets.UTF_8));
                    System.out.println("==> AGV's positions collected and sent back to Control System");
                }

                if (option == Code.ALL_AGVS.code) {
                    sendMessage(Code.ACK);

                    ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                    ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                    System.out.println("==> Dashboard is requesting all agvs");
                    List<AGV> agvList = (List<AGV>) AgvRegistry.agvService().allAgvs();
                    List<String> squaresList = new ArrayList<>();
                    for (AGV agv : agvList) {
                        Squares squares = agv.agvPosition();
                        squaresList.add(squares.toString());
                    }
                    ObjectMapper Obj = new ObjectMapper();
                    String jsonStr = Obj.writeValueAsString(squaresList);

                    sendMessage(Code.ALL_AGVS, jsonStr.getBytes(StandardCharsets.UTF_8));
                    System.out.println("==> AGV's collected and sent back to Dashboard");

                }

                sendMessage(Code.ACK);

                byte[] clienteMessageEnd = sIn.readNBytes(5);
                if (clienteMessageEnd[1] == 1) {
                    System.out.println("==> End of Client message Successfully Received");
                    //Telling the client that you understand
                    System.out.println("==> Send message to client to close socket");
                    sendMessage(Code.ACK);
                    System.out.println("==> Address " + clientIP.getHostAddress() + ", port number: " + this.s.getPort() + " disconnected");

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

    @Transactional
    public synchronized void assignTasks() {
        AgvRegistry.configure(PersistenceContext.repositories().agvs());
        AgvManagementService agvManagementService = AgvRegistry.agvService();
        List<AGV> availableAGVs = (List<AGV>) agvManagementService.findByAgvWorkState(AGVWorkState.AVAILABLE);
        System.out.println("==> Trying to assign a new task...");
        try {
            if (availableAGVs.isEmpty()) {
                System.out.println("==> No AGVs available to do more tasks yet.");
            } else if (ordersToDo.isEmpty()) {
                System.out.println("==> No tasks remaining for the AGVs.");
            } else {
                //comparar datas
                int size;
                int i;
                availableAGVs = (List<AGV>) agvManagementService.findByAgvWorkState(AGVWorkState.AVAILABLE);
                for (AGV agv : availableAGVs) {
                    size = ordersToDo.size();
                    if (0 == size)
                        break;
                    else {
                        for (i = 0; i < size; i++) {
                            //System.out.println("Vou tentar sacar o total weigth");
                            //System.out.println("Total Weigth: " + ordersToDo.get(i).getTotalWeight());
                            if ((Double.parseDouble(agv.maxWeight().toString()) >= ordersToDo.get(i).getTotalWeight()) && agv.agvWorkState() == AGVWorkState.AVAILABLE) {
                                agv.setAgvWorkState(AGVWorkState.WORKING);
                                agv.setOrder(ordersToDo.get(i));
                                AgvRegistry.agvService().getAgvRepository().save(agv);
                                ProductOrder currentOrder = ordersToDo.get(i);
                                currentOrder.setAgv(agv);
                                currentOrder.setAgvStatus(AGVStatus.ASSOCIATED);
                                OrderRegistry.orderService().getOrderRepository().save(currentOrder);
                                System.out.println("==> Agv " + agv.identity() + " is now working on order " + currentOrder.identity());
                                ordersToDo.remove(i);


                                AgvDigitalTwin agvtsk = new AgvDigitalTwin(agv);
                                Thread newThread = new Thread(agvtsk);
                                SharedMemory.getInstance().addThread(agvtsk.getAgv(), newThread);
                                newThread.start();


                                break;
                            }
                        }

                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to assign a new task for now");
        }

    }


    @Override
    public Socket getSocket() {
        return s;
    }
}
