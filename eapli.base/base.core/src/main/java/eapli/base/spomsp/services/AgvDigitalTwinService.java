package eapli.base.spomsp.services;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.productmanagement.domain.model.ShoppingCart;
import eapli.base.productmanagement.domain.model.ShoppingCartItem;
import eapli.base.spomsp.SPOMSP;
import org.springframework.transaction.annotation.Transactional;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AgvDigitalTwinService implements SPOMSP {

    static final AppSettings app = Application.settings2();
    static final String serverIPProperties = app.getServerIpKey();
    static final int serverPortProperties = app.getServerPortKey();

    static final String keyStorePassProperties = "forgotten";
    static InetAddress serverIP;
    static Socket s;
    @Transactional()
    public synchronized Object sendMessageToServer(int option, String data) throws IOException {

        Object returnObject = null;
        boolean flag = true;

        try{
            serverIP = InetAddress.getByName(serverIPProperties);
        } catch (UnknownHostException ex) {
            throw new IllegalArgumentException("[ERROR] Server problems, please try again later!");
        }


        String clientCertificate = "client" + (option - 2) + "agv";
        // Trust these certificates provided by servers
        //System.setProperty("javax.net.ssl.trustStore", clientCertificate + ".jks");
        //System.setProperty("javax.net.ssl.trustStorePassword", keyStorePassProperties);

        // Use this certificate and private key for client certificate when requested by the server
        //System.setProperty("javax.net.ssl.keyStore", clientCertificate + ".jks");
        //System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassProperties);

        SocketFactory sf = SocketFactory.getDefault();

        try{
            s = sf.createSocket(serverIP, serverPortProperties);
        } catch (IOException ex){
            throw new IllegalArgumentException("[ERROR] Server problems, please try again later!");

        }

        try{
            //s.startHandshake();
            DataOutputStream sOutData = new DataOutputStream(s.getOutputStream());
            DataInputStream sInData = new DataInputStream(s.getInputStream());

            //Send a request to ther server -> code: 0 (Test)
            sendMessage(Code.COMMTEST);

            //Wait for the response from the server saying that it understood the message
            int option1 = receiveAnswer();
            if (option1 == Code.ACK.code){

                if(Code.valueOf(option) == Code.TASKDONE){
                    sendMessage(Code.TASKDONE, data.getBytes(StandardCharsets.UTF_8));
                } else if(Code.valueOf(option) == Code.TASKCREATED){
                    sendMessage(Code.TASKCREATED, data.getBytes(StandardCharsets.UTF_8));
                } else if(Code.valueOf(option) == Code.FORCETASK){
                    sendMessage(Code.FORCETASK, data.getBytes(StandardCharsets.UTF_8));
                } else if(Code.valueOf(option) == Code.AGVCREATED){
                    sendMessage(Code.AGVCREATED);
                }else if (Code.valueOf(option) == Code.RUNNING_AGVS){
                    sendMessage(Code.RUNNING_AGVS);
                }else if (Code.valueOf(option) == Code.ALL_AGVS){
                    sendMessage(Code.ALL_AGVS);
                }
                //Wait for the response from the server saying that it understood the message
                option1 = receiveAnswer();
                if(option1 == Code.ACK.code){

                    if (Code.valueOf(option) == Code.RUNNING_AGVS){
                        ObjectOutputStream sOutObject = new ObjectOutputStream(s.getOutputStream());
                        ObjectInputStream sInObject = new ObjectInputStream(s.getInputStream());

                        returnObject = receiveAnswerObject();
                        ArrayList<String> linkedHashMap = (ArrayList<String>) returnObject;
                        returnObject = linkedHashMap;
                    }

                    if (Code.valueOf(option) == Code.ALL_AGVS){
                        ObjectOutputStream sOutObject = new ObjectOutputStream(s.getOutputStream());
                        ObjectInputStream sInObject = new ObjectInputStream(s.getInputStream());

                        returnObject = receiveAnswerObject();
                        ArrayList<String> linkedHashMap = (ArrayList<String>) returnObject;
                        returnObject = linkedHashMap;
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
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException("[ERROR] Failed during the information search, please try again later!");
        } finally {
            if(flag){
                s.close();
            }
        }
        return returnObject;
    }


    @Override
    public Socket getSocket(){
        return s;
    }

}
