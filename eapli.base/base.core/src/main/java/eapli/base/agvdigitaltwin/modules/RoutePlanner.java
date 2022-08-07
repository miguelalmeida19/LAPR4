package eapli.base.agvdigitaltwin.modules;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.spomsp.SPOMSP;
import eapli.base.spomsp.services.OrdersService;
import eapli.base.warehousemanagement.domain.model.RowsId;
import eapli.base.warehousemanagement.domain.model.Squares;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoutePlanner {
    private static final OrdersService ordersServer = new OrdersService();
    private int[][] obstacles;
    private AGV agv;
    private final List<RowsId> rowsIds = new ArrayList<>();

    public RoutePlanner(AGV agv) {
        this.agv = agv;
        ProductOrder order = agv.order();
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.product();
            RowsId aisleRow = product.getRow();
            rowsIds.add(aisleRow);
        }
    }

    @Transactional
    public List<int[]> recomputesRoute() {
        int[][] grid = obstacles;

        Squares start = agv.agvPosition();
        RowsId rowsId = rowsIds.get(0);
        System.out.println("RowId: " + rowsId.toString());
        String square = askForRowSquare(rowsId);
        int endLsquare = Integer.parseInt(square.split(" ")[0]);
        int endWsquare = Integer.parseInt(square.split(" ")[1]);


        grid[Integer.parseInt(start.getWsquare())][Integer.parseInt(start.getLsquare())] = 2;
        grid[endWsquare-1][endLsquare-1] = 3;


        List<int[]> path = ShortestPath.shortestPath(grid);

        return path;
    }

    @Transactional
    public String askForRowSquare(RowsId rowsId) {
        String string;
        try {
            // Asks OrderServer the Square from a Row where the product is
            string = (String) ordersServer.sendMessageToServer(SPOMSP.Code.ROW_SQUARES.code, rowsId.toString());
        } catch (IOException e) {
            System.out.println("askForRowSquare Exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
        System.out.println("Beginooooooo" + string);
        return string;
    }


    public void setObstacles(int[][] obstacles) {
        this.obstacles = obstacles;
    }

    public void setAgv(AGV agv) {
        this.agv = agv;
    }
}
