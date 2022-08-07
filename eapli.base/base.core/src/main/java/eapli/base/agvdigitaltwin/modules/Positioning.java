package eapli.base.agvdigitaltwin.modules;

import eapli.base.agvdigitaltwin.SharedMemory;
import eapli.base.agvmanagement.application.AgvRegistry;
import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.application.SquaresRegistry;
import eapli.base.warehousemanagement.domain.model.Squares;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

public class Positioning {
    private AGV agv;
    private int agvSpeed;

    public Positioning(AGV agv){
        this.agv = agv;
        this.agvSpeed = agv.model().maxVelocity();
    }

    public int findLimit(List<int[]> agvRoute){
        return (1000*(agvRoute.size()-1))/agvSpeed;
    }

    @Transactional
    public List<int[]> tryToMove(List<int[]> agvRoute, int time){
        if (time%1000==0){
            for (int i=0; i<agvSpeed; i++){
                int[] keys = agvRoute.get(0);
                Squares squares = new Squares((long) keys[0], (long) keys[1]);
                System.out.println("\n\n$$$$$$$$$$$$\nAGV " + agv.identity() + " moved to square " + squares);
                System.out.println("Remaining Moves: " + Arrays.deepToString(agvRoute.toArray())+"\n$$$$$$$$$$$$\n\n");
                agv.setPosition(squares);
                SharedMemory.getInstance().updateAgv(agv);
                agvRoute.remove(0);
            }
        }
        return agvRoute;
    }

    public Squares checkChangePosition(List<int[]> agvRoute, int index, int time){

        //time comes in multiples of 100ms
        //if the thread.sleep(100) has runned 3 times, time would be 300ms
        //if the time reaches agvSpeed, it means it's time to move to nextPosition
        System.out.println("\n\n---------------------------------------------");
        System.out.println("Time: " + time);
        System.out.println("AGV Speed * 1000: " + agvSpeed*1000);
        System.out.println("Full Time necessary: " + ((1000*(agvRoute.size()-1))/agvSpeed));
        System.out.println("---------------------------------------------\n\n");


        if (time%1000==0){
            return new Squares(Long.valueOf(agvRoute.get(index+(agvSpeed-1))[0]), Long.valueOf(agvRoute.get(index+((agvSpeed-1)))[1]));
        }else {
            return null;
        }
    }

    public void setAgvSpeed(int agvSpeed) {
        this.agvSpeed = agvSpeed;
    }
}
