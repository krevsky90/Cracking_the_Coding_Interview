package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.ArrayList;
import java.util.List;

/**
 * p.147
 * 8.2 Robot in a Grid:
 * Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
 * The robot can only move in two directions, right and down, but certain cells are "off limits" such that
 * the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
 * the bottom right.
 * Hints: #331, #360, #388
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_2arr {
    public static final int[] OFFLIMITS_X = new int[]{4, 7, 6, 1, 3};
    public static final int[] OFFLIMITS_Y = new int[]{1, 1, 2, 3, 3};
    public static final int c = 7;
    public static final int r = 5;

    public static void main(String[] args) {
        List<Integer> xPath = new ArrayList<>(c);
        List<Integer> yPath = new ArrayList<>(r);

        getPath(xPath, yPath);
        System.out.println("the result path:");
        printPath(xPath, yPath);
    }

    public static void getPath(List<Integer> xPath, List<Integer> yPath) {
        int startX = 1;
        int startY = 1;
        xPath.add(startX);
        yPath.add(startY);
        doStep(xPath, yPath, startX, startY);
    }

    public static boolean doStep(List<Integer> xPath, List<Integer> yPath, int x, int y) {
        if (x == c && y == r) {
//            printPath(xPath, yPath);
            return true;
        } else {
            //try to move from left to right
            int newX = x + 1;
            if (newX <= c && isFree(newX, y)) {
                xPath.add(newX);
                yPath.add(y);
//            printPath(xPath, yPath);
                return doStep(xPath, yPath, newX, y);
            } else {
                //try to move from top to bottom
                int newY = y + 1;
                if (newY <= r && isFree(x, newY)) {
                    xPath.add(x);
                    yPath.add(newY);
//                  printPath(xPath, yPath);
                    return doStep(xPath, yPath, x, newY);
                } else {
                    //path is not built and we can't go further -> clear current path
                    System.out.println("clear path. before it was ");
//                    printPath(xPath, yPath);
                    xPath.clear();
                    yPath.clear();
                    return false;
                }
            }
        }
    }

    private static boolean isFree(int x, int y) {
        for (int i = 0; i < OFFLIMITS_X.length; i++) {
            if (OFFLIMITS_X[i] == x && OFFLIMITS_Y[i] == y) {
                return false;
            }
        }

        return true;
    }

    public static void printPath(List<Integer> xPath, List<Integer> yPath) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < xPath.size(); i++) {
            sb.append("(").append(xPath.get(i)).append(", ").append(yPath.get(i)).append(") -> ");
        }
        System.out.println(sb.toString());
    }
}