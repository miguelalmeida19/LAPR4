package eapli.base.spomsp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.ordermanagement.domain.domain.model.OrderDto;
import eapli.base.productmanagement.domain.model.ShoppingCart;
import eapli.base.productmanagement.domain.model.ShoppingCartItem;
import eapli.base.spomsp.SPOMSP;
import eapli.base.surveymanagement.domain.model.SurveyDto;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class OrdersService implements SPOMSP {

    static final AppSettings app = Application.settings();
    static final String serverIPProperties = app.getServerIpKey();
    static final int serverPortProperties = app.getServerPortKey();

    static final String keyStorePassProperties = "forgotten";
    static InetAddress serverIP;
    static SSLSocket s;
    static ShoppingCart shoppingCart = new ShoppingCart();
    private final static AuthorizationService authz = AuthzRegistry.authorizationService();

    public Object sendMessageToServer(int option, String data) throws IOException {

        boolean flag = true;
        Object returnObject = null;

        try {
            serverIP = InetAddress.getByName(serverIPProperties);
        } catch (UnknownHostException ex) {
            throw new IllegalArgumentException("[ERROR] Server problems, please try again later!");
        }


        String clientCertificate = "client" + (option - 2) + "Order";
        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", clientCertificate + ".jks");
        System.setProperty("javax.net.ssl.trustStorePassword", keyStorePassProperties);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore", clientCertificate + ".jks");
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassProperties);

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            s = (SSLSocket) sf.createSocket(serverIP, serverPortProperties);
        } catch (IOException ex) {
            throw new IllegalArgumentException("[ERROR] Server problems, please try again later!");
        }

        try {
            s.startHandshake();
            DataOutputStream sOutData = new DataOutputStream(s.getOutputStream());
            DataInputStream sInData = new DataInputStream(s.getInputStream());

            //Send a request to the server -> code: 0 (Test)
            sendMessage(Code.COMMTEST);

            //Wait for the response from the server saying that it understood the message
            int option1 = receiveAnswer();
            if (option1 == Code.ACK.code) {

                //Send the server the option and data so it knows what to return
                if (Code.valueOf(option) == Code.SHOPPINGCART) {
                    sendMessage(Code.SHOPPINGCART);
                }
                else if (Code.valueOf(option) == Code.WAREHOUSE_OBSTACLES) {
                    sendMessage(Code.WAREHOUSE_OBSTACLES);
                }
                else if (Code.valueOf(option) == Code.ROW_SQUARES){
                    sendMessage(Code.ROW_SQUARES, data.getBytes(StandardCharsets.UTF_8));
                    sendMessage(Code.ROW_SQUARES, data.getBytes(StandardCharsets.UTF_8));
                }
                else if (Code.valueOf(option) == Code.CUSTOMER_ORDERS) {
                    sendMessage(Code.CUSTOMER_ORDERS, data.getBytes(StandardCharsets.UTF_8));
                    sendMessage(Code.CUSTOMER_ORDERS, data.getBytes(StandardCharsets.UTF_8));
                }
                else if (Code.valueOf(option) == Code.SURVEYS_TO_ANSWER){
                    sendMessage(Code.SURVEYS_TO_ANSWER, data.getBytes(StandardCharsets.UTF_8));
                    sendMessage(Code.SURVEYS_TO_ANSWER, data.getBytes(StandardCharsets.UTF_8));
                }
                else if (Code.valueOf(option) == Code.SAVE_SURVEY_ANSWER){
                    sendMessage(Code.SAVE_SURVEY_ANSWER, data.getBytes(StandardCharsets.UTF_8));

                    Path path = Paths.get("survey_answered.txt");
                    Charset charset = StandardCharsets.UTF_8;

                    String content = Files.readString(path, charset);

                    String username = authz.session().get().authenticatedUser().username().toString();
                    sendMessage(Code.SAVE_SURVEY_ANSWER, username.getBytes(StandardCharsets.UTF_8));
                    option1 = receiveAnswer();
                    sendMessage(Code.SAVE_SURVEY_ANSWER, content.getBytes(StandardCharsets.UTF_8));
                    File file = new File("survey_answered.txt");
                    file.delete();
                }
                else if (Code.valueOf(option) == Code.ADD_TO_SHOPPINGCART) {
                    sendMessage(Code.GIVE_SHOPPINGCART, convert(shoppingCart).getBytes(StandardCharsets.UTF_8));
                    sendMessage(Code.ADD_TO_SHOPPINGCART, data.getBytes(StandardCharsets.UTF_8));
                }

                //Wait for the response from the server saying that it understood the message
                option1 = receiveAnswer();
                if (option1 == SPOMSP.Code.ACK.code) {

                    ObjectOutputStream sOutObject = new ObjectOutputStream(s.getOutputStream());
                    ObjectInputStream sInObject = new ObjectInputStream(s.getInputStream());

                    if (Code.valueOf(option)!=Code.CUSTOMER_ORDERS){

                        if (Code.valueOf(option)==Code.SURVEYS_TO_ANSWER){
                            returnObject = receiveAnswerObject();
                            List<LinkedHashMap> linkedHashMapList = (ArrayList) returnObject;
                            List<SurveyDto> surveyDtoList = new ArrayList<>();
                            for(LinkedHashMap l : linkedHashMapList){
                                surveyDtoList.add(new SurveyDto((String) l.get("description"), ((String) l.get("questionary")).getBytes()));
                            }
                            returnObject = surveyDtoList;
                        }
                        else if (Code.valueOf(option)==Code.SAVE_SURVEY_ANSWER){
                            receiveAnswer();
                        }
                        else if (Code.valueOf(option)==Code.WAREHOUSE_OBSTACLES){
                            returnObject = receiveAnswerObject();
                        }
                        else if (Code.valueOf(option) == Code.ROW_SQUARES){
                            returnObject = receiveAnswerObject();
                            System.out.println("RETURN OBJECT: " + returnObject);
                        }
                        else {
                            returnObject = receiveAnswerObject();
                            LinkedHashMap linkedHashMap = (LinkedHashMap) returnObject;
                            shoppingCart = new ShoppingCart((List<ShoppingCartItem>) linkedHashMap.get("shoppingCart"));
                            returnObject = shoppingCart;
                        }
                    }else {
                        returnObject = receiveAnswerObject();
                        List<LinkedHashMap> linkedHashMapList = (ArrayList) returnObject;
                        List<OrderDto> orderDtoList = new ArrayList<>();
                        for(LinkedHashMap l : linkedHashMapList){
                            orderDtoList.add(new OrderDto((String) l.get("orderId"), (String) l.get("finalPrice"), (String) l.get("orderStatus")));
                        }
                        returnObject = orderDtoList;
                    }

                    //Send an order to the server -> code: 1 (End)
                    sendMessage(Code.DISCONN);

                    option1 = receiveAnswer();
                    if (option1 == SPOMSP.Code.ACK.code) {
                        s.close();
                        flag = false;
                    } else {
                        s.close();
                        flag = false;
                    }
                } else {
                    throw new IllegalArgumentException("[ERROR] Failed during the information search, please try again later!");
                }
            } else {
                throw new IllegalArgumentException("[ERROR] Failed during the information search, please try again later!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException("[ERROR] Failed during the information search, please try again later!");
        } finally {
            if (flag) {
                s.close();
            }
        }
        return returnObject;
    }

    @Override
    public SSLSocket getSocket() {
        return s;
    }

    private String convert(Object ob){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Converting the Java object into a JSON string
            return objectMapper.writeValueAsString(ob);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}