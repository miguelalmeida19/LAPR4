package eapli.base.agvdigitaltwin.modules;

import eapli.base.warehousemanagement.domain.model.Squares;

public class Sensors {
    private int[] sensorSignals = new int[8];

    private Squares sensorTopLeft;
    private Squares sensorTopRight;
    private Squares sensorBottomLeft;
    private Squares sensorBottomRight;
    private Squares[] sensorTop = new Squares[2];
    private Squares[] sensorRight = new Squares[2];
    private Squares[] sensorLeft = new Squares[2];
    private Squares[] sensorBottom = new Squares[2];

    private Squares currentPosition;

    public Sensors(Squares currentPosition){
        syncSensores(currentPosition);
    }

    public void syncSensores(Squares currentPosition){
        this.currentPosition = currentPosition;
        int l = Integer.parseInt(currentPosition.getLsquare());
        int w = Integer.parseInt(currentPosition.getWsquare());
        this.sensorTopLeft = new Squares(Long.valueOf(l-1), Long.valueOf(w-1));
        this.sensorTopRight = new Squares(Long.valueOf(l+1), Long.valueOf(w-1));
        this.sensorBottomLeft = new Squares(Long.valueOf(l-1), Long.valueOf(w+1));
        this.sensorBottomRight = new Squares(Long.valueOf(l+1), Long.valueOf(w+1));
        this.sensorTop = new Squares[]{new Squares(Long.valueOf(l), Long.valueOf(w - 1)), new Squares(Long.valueOf(l), Long.valueOf(w - 2))};
        this.sensorBottom = new Squares[]{new Squares(Long.valueOf(l), Long.valueOf(w + 1)), new Squares(Long.valueOf(l), Long.valueOf(w + 2))};
        this.sensorRight = new Squares[]{new Squares(Long.valueOf(l+1), Long.valueOf(w)), new Squares(Long.valueOf(l+2), Long.valueOf(w))};
        this.sensorLeft = new Squares[]{new Squares(Long.valueOf(l-1), Long.valueOf(w)), new Squares(Long.valueOf(l-2), Long.valueOf(w))};
    }

    public int[] receiveObstacles(int[][] warehouse){
        for (int i=0; i<warehouse.length; i++){
            for (int f=0; f<warehouse[0].length; f++){
                if (warehouse[i][f]==1){
                    // it's an obstacle
                    String width = String.valueOf(i+1);
                    String length = String.valueOf(f+1);
                    if (width.equals(sensorTopLeft.getWsquare()) && length.equals(sensorTopLeft.getLsquare())){
                        sensorSignals[0]=2;
                    }
                    if(width.equals(sensorTopRight.getWsquare()) && length.equals(sensorTopRight.getLsquare())){
                        sensorSignals[2]=2;
                    }
                    if(width.equals(sensorBottomLeft.getWsquare()) && length.equals(sensorBottomLeft.getLsquare())){
                        sensorSignals[4]=2;
                    }
                    if(width.equals(sensorBottomRight.getWsquare()) && length.equals(sensorBottomRight.getLsquare())){
                        sensorSignals[6]=2;
                    }
                    if(width.equals(sensorTop[0].getWsquare())&&width.equals(sensorTop[0].getLsquare())) {
                        sensorSignals[1] = 1;
                    }
                    if(width.equals(sensorTop[1].getWsquare())&&width.equals(sensorTop[1].getLsquare())) {
                        sensorSignals[1] = 2;
                    }
                    if(width.equals(sensorRight[0].getWsquare())&&width.equals(sensorRight[0].getLsquare())) {
                        sensorSignals[3] = 1;
                    }
                    if(width.equals(sensorRight[1].getWsquare())&&width.equals(sensorRight[1].getLsquare())) {
                        sensorSignals[3] = 2;
                    }
                    if(width.equals(sensorBottom[0].getWsquare())&&width.equals(sensorBottom[0].getLsquare())) {
                        sensorSignals[5] = 1;
                    }
                    if(width.equals(sensorBottom[1].getWsquare())&&width.equals(sensorBottom[1].getLsquare())) {
                        sensorSignals[5] = 2;
                    }
                    if(width.equals(sensorLeft[0].getWsquare())&&width.equals(sensorLeft[0].getLsquare())) {
                        sensorSignals[7] = 1;
                    }
                    if(width.equals(sensorLeft[1].getWsquare())&&width.equals(sensorLeft[1].getLsquare())) {
                        sensorSignals[7] = 2;
                    }
                }

            }
        }
        return sensorSignals;
    }
}
