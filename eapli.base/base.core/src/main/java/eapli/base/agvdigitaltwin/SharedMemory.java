package eapli.base.agvdigitaltwin;

import eapli.base.agvdigitaltwin.modules.AgvDigitalTwin;
import eapli.base.agvmanagement.domain.model.AGV;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SharedMemory {

    private static SharedMemory instance;

    private static LinkedHashMap<AGV, Thread> threads = new LinkedHashMap<>();

    public SharedMemory(){}

    public static SharedMemory getInstance() {
        if (instance == null) {
            instance = new SharedMemory();
        }
        return instance;
    }

    public LinkedHashMap<AGV, Thread> getThreads() {
        return threads;
    }

    public Thread getAgvThread(AGV agv){
        return threads.get(agv);
    }

    public void addThread(AGV agv, Thread thread){
        threads.put(agv, thread);
        //System.out.println("New Thread added!");
        //System.out.println("Currently there are " + threads.size() + " on");
    }

    public void updateAgv(AGV agv){
        Thread thread = threads.get(agv);
        threads.remove(agv);
        threads.put(agv, thread);
    }
}