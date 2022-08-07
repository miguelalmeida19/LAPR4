package eapli.base.agvdigitaltwin.modules;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ShortestPath {
    public static void main(String[] args) {

        // start -> 2
        // end -> 3
        // obstacle -> 1
        // free -> 0

        int[][] board = {
                {2, 0, 0, 0},
                {0, 0, 1, 0},
                {1, 0, 0, 0},
                {0, 1, 3, 0}
        };


        shortestPath(board);



    }

    public static List<int[]> shortestPath(int[][] board){

        int startW=0;
        int startL=0;
        int endW=0;
        int endL=0;

        for (int i=0; i<board.length; i++){
            for (int f=0; f< board[0].length; f++){
                if (board[i][f]==2){
                    startW = i;
                    startL = f;
                }else if (board[i][f]==3){
                    endW = i;
                    endL = f;
                }
            }
        }


        ArrayList<Point> arr = new ArrayList<Point>();

        Point temp = new Point(startW, startL);
        arr = getNextNodes(board, temp, arr);

        // will print the point
        System.out.println("Current point: " + temp);

        // will point the adjacent points to temp that have a value of 1
        System.out.println("Points adjacent to current point: " + arr);

        Point start = temp;
        Point end = new Point(endW, endL);


        for (int i=0; i<board.length; i++){
            for (int f=0; f< board[0].length; f++){
                if (board[i][f]==2){
                    board[i][f]=1;
                }else if (board[i][f]==3){
                    board[i][f]=1;
                }else if (board[i][f]==0){
                    board[i][f]=1;
                }else if (board[i][f]==1){
                    board[i][f]=0;
                }
            }
        }

        System.out.println();
        System.out.println("Running BFS");
        System.out.println("From: " + start);
        System.out.println("To: " + end);
        ArrayList<Point> bestPath = bfsSearch(board, start, end);

        // prints best path from start to end
        System.out.println();
        System.out.println("Printing best path: " + bestPath);

        List<int[]> list = new ArrayList<>();
        for (Point p: bestPath){
            list.add(new int[]{p.x, p.y});
        }

        return list;
    }

    public static ArrayList<Point> bfsSearch(int[][] grid, Point start, Point end) {
        // Creates the array of visited points
        ArrayList<Point> visited = new ArrayList<Point>();

        // creates the array of paths to go through. A path is an array of sequential
        // points
        ArrayList<ArrayList<Point>> paths = new ArrayList<ArrayList<Point>>();

        // adds the initial point as the only path traversed so far
        ArrayList<Point> curPath = new ArrayList<Point>();
        curPath.add(start);
        paths.add(curPath);

        while (paths.size() > 0) {
            curPath = paths.get(0);
            //System.out.println("Current path: " + curPath);
            paths.remove(0);

            // this is the next node to check
            Point nextNode = curPath.get(curPath.size() - 1);
            //System.out.println("Current node in path: " + nextNode);

            // adds point as visited
            visited.add(nextNode);
            //System.out.println("Visited nodes: " + visited);

            // if the node to check is the end node, return the current path
            //System.out.println("Checking to see if end: " + end + " is equal to the current node: " + nextNode);
            if (end.equals(nextNode)) {
                return curPath;
            }

            // add the next possible paths to paths
            // first get the next unvisited nodes that are next to more recent nodes
            ArrayList<Point> nextNodes = getNextNodes(grid, nextNode, visited);

            for (int i = 0; i < nextNodes.size(); i++) {
                ArrayList<Point> tempPath = new ArrayList<Point>();

                for (Point p : curPath) {
                    tempPath.add(p);
                }

                tempPath.add(nextNodes.get(i));
                paths.add(tempPath);
                //System.out.println("Adding path to check: " + tempPath);
            }
            //System.out.println();
        }

        return curPath;
    }

    public static ArrayList<Point> getNextNodes(int[][] grid, Point nextNode, ArrayList<Point> visited) {
        int curX = nextNode.x;
        int curY = nextNode.y;

        ArrayList<Point> nextNodes = new ArrayList<Point>();
        // if can check up
        if (curX > 0) {
            Point tempPoint = new Point(curX - 1, curY);
            if (grid[curX - 1][curY] == 1 && !isPointInArray(tempPoint, visited)) {
                nextNodes.add(tempPoint);
            }
        }

        // check down
        if (curX < grid.length - 1) {
            Point tempPoint = new Point(curX + 1, curY);
            if (grid[curX + 1][curY] == 1 && !isPointInArray(tempPoint, visited)) {
                nextNodes.add(tempPoint);
            }
        }

        // check left
        if (curY > 0) {
            Point tempPoint = new Point(curX, curY - 1);
            if (grid[curX][curY - 1] == 1 && !isPointInArray(tempPoint, visited)) {
                nextNodes.add(tempPoint);
            }
        }

        // check right
        if (curY < grid[0].length - 1) {
            Point tempPoint = new Point(curX, curY + 1);
            if (grid[curX][curY + 1] == 1 && !isPointInArray(tempPoint, visited)) {
                nextNodes.add(tempPoint);
            }
        }
        return nextNodes;
    }

    public static boolean isPointInArray(Point tempPoint, ArrayList<Point> visited) {
        for (Point p : visited) {
            if (p.equals(tempPoint))
                return true;
        }
        return false;
    }
}

