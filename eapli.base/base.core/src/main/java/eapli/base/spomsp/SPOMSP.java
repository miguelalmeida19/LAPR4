package eapli.base.spomsp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.AbstractMap;
import java.util.Map;

public interface SPOMSP {
    Socket getSocket();
    int version = 0, max_bytes = 256;

    default void sendMessage(Code code) throws IOException {
        DataOutputStream out = new DataOutputStream(getSocket().getOutputStream());
        out.writeByte(version);
        out.writeByte(code.code);
        out.writeByte(0);
        out.writeByte(0);
        out.writeByte(0);
    }

    default void sendMessage(Code code, byte[] data) throws IOException {
        DataOutputStream out = new DataOutputStream(getSocket().getOutputStream());
        out.writeByte(version);
        out.writeByte(code.code);
        int D_LENGTH = data.length;
        int D_LENGTH_1 = D_LENGTH % 256;
        int D_LENGTH_2 = D_LENGTH / 256;
        out.writeByte(D_LENGTH_1);
        out.writeByte(D_LENGTH_2);
        out.write(data);
    }

    default boolean receivesAnswer (Code answer) throws IOException {
        DataInputStream in = new DataInputStream(getSocket().getInputStream());
        in.readByte();
        Code codigo = Code.valueOf(in.readByte());
        int D_LENGTH_1 = in.readByte();
        int D_LENGTH_2 = in.readByte();

        int D_LENGTH = D_LENGTH_1 + max_bytes * D_LENGTH_2;

        if (D_LENGTH != 0) {
            for (int i = 0; i < D_LENGTH; i++)
                in.readByte();
        }
        if (codigo != answer) {
            System.out.println("Received message " + codigo + ".");
            return false;
        }

        return true;
    }

    default boolean hasData (Code answer) throws IOException{
        DataInputStream in = new DataInputStream(getSocket().getInputStream());
        in.readByte();
        Code codigo = Code.valueOf(in.readByte());
        int D_LENGTH_1 = in.readByte();
        int D_LENGTH_2 = in.readByte();

        int D_LENGTH = D_LENGTH_1 + max_bytes * D_LENGTH_2;

        if (D_LENGTH != 0) {
            for (int i = 0; i < D_LENGTH; i++)
                in.readByte();
        }
        if (codigo != answer) {
            System.out.println("Received message " + codigo + ".");
            return false;
        } else {
            byte[] bytes = in.readNBytes(D_LENGTH);
            String word = new String(bytes);
            System.out.println("==> Content of message was: " + word + ".");
        }

        return true;
    }

    default int receiveAnswer() throws IOException {
        DataInputStream in = new DataInputStream(getSocket().getInputStream());
        in.readByte();
        Code codigo = Code.valueOf(in.readByte());
        int D_LENGTH_1 = in.readByte();
        int D_LENGTH_2 = in.readByte();
        int D_LENGTH = D_LENGTH_1 + max_bytes * D_LENGTH_2;

        if (D_LENGTH!=0){
            byte[] bytes = in.readNBytes(D_LENGTH);
            System.out.println("==> Message received from " + new String(bytes));
        }else {
            in.readByte();
        }

        return codigo.code;
    }

    default Object receiveAnswerObject() throws IOException {
        Object result = null;
        try {
            DataInputStream in = new DataInputStream(getSocket().getInputStream());
            in.readByte();
            Code code = Code.valueOf(in.readByte());
            int D_LENGTH_1 = in.readByte();
            int D_LENGTH_2 = in.readByte();

            int D_LENGTH = D_LENGTH_1 + max_bytes * D_LENGTH_2;

            if (D_LENGTH!=0){
                byte[] bytes = in.readNBytes(D_LENGTH);
                String response = new String(bytes);
                ObjectMapper m = new ObjectMapper();
                result = m.readValue(response, new TypeReference<Object>() {});
                //System.out.println("==> Message received from " + new String(bytes));
            }else {
                in.readByte();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    default Map.Entry<Integer, Object> receiveAnswerEntry()throws IOException {
        DataInputStream in = new DataInputStream(getSocket().getInputStream());
        in.readByte();
        Object result = null;
        Code codigo = Code.valueOf(in.readByte());
        int D_LENGTH_1 = in.readByte();
        int D_LENGTH_2 = in.readByte();

        int D_LENGTH = D_LENGTH_1 + max_bytes * D_LENGTH_2;

        if (D_LENGTH!=0){
            byte[] bytes = in.readNBytes(D_LENGTH);
            String response = new String(bytes);
            ObjectMapper m = new ObjectMapper();
            try {
                result = m.readValue(response, new TypeReference<Object>() {});
            }catch (Exception e){
                result = response;
            }
            System.out.println("==> Message received with value " + new String(bytes));
        }else {
            in.readByte();
        }

        return new AbstractMap.SimpleEntry<>(codigo.code,result);
    }

    enum Code {
        COMMTEST(0),
        DISCONN(1),
        ACK(2),
        ALL_AGVS(3),
        SHOPPINGCART(4),
        ADD_TO_SHOPPINGCART(5),
        TASKDONE(6),
        TASKCREATED(7),
        GIVE_SHOPPINGCART(8),
        FORCETASK(9),
        AGVCREATED(10),
        CUSTOMER_ORDERS(11),
        SURVEYS_TO_ANSWER(12),
        SAVE_SURVEY_ANSWER(13),
        RUNNING_AGVS(14),
        WAREHOUSE_OBSTACLES(15),
        ROW_SQUARES(16);

        public int code;

        Code(int code) {
            this.code = code;
        }

        public static Code valueOf(int c) {
            for (Code code : Code.values())
                if (c == code.code) {
                    return code;
                }
            return null;
        }
    }
}

