package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
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
public class Problem8_2 {
    public static void main(String[] args) {
        int c = 7;
        int r = 5;
        List<Pair> offlimits = Arrays.asList(
                new Pair(4, 1),
                new Pair(7, 1),
                new Pair(6, 2),
                new Pair(1, 3),
                new Pair(3, 3)
        );

        List<Pair> path = getPath(offlimits, c, r);
        System.out.println("the result path:");
        printPath(path);

    }

    public static List<Pair> getPath(List<Pair> offlimits, int c, int r) {
        List<Pair> path = new ArrayList<>();
        int startX = 1;
        int startY = 1;
        path.add(new Pair(startX, startY));
        doStep(path, startX, startY, c, r, offlimits);
        return path;
    }

    public static boolean doStep(List<Pair> path, int x, int y, int c, int r, List<Pair> offlimits) {
        if (x == c && y == r) {
//            printPath(path);
            return true;
        } else {
            //try to move from left to right
            int newX = x + 1;
            if (newX <= c && isFree(newX, y, offlimits)) {
                path.add(new Pair(newX, y));
//                printPath(path);
                return doStep(path, newX, y, c, r, offlimits);
            } else {
                //try to move from top to bottom
                int newY = y + 1;
                if (newY <= r && isFree(x, newY, offlimits)) {
                    path.add(new Pair(x, newY));
//                    printPath(path);
                    return doStep(path, x, newY, c, r, offlimits);
                } else {
                    //path is not built and we can't go further -> clear current path
                    System.out.println("clear path. before it was ");
//                    printPath(path);
                    path.clear();
                    return false;
                }
            }
        }
    }

    private static boolean isFree(int x, int y, List<Pair> offlimits) {
        return !offlimits.contains(new Pair(x, y));    //todo: not sure
    }

    public static void printPath(List<Pair> path) {
        String res = "";
        for (Pair p : path) {
            res += p.toString() + " -> ";
        }
        System.out.println(res);
    }

    private static class Pair {
        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public boolean equals(Object p) {
            if (this == p) return true;
            if (p == null) return false;
            if (!(p instanceof Pair)) return false;
            Pair p1 = (Pair) p;

            return x == p1.x && y == p1.y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        public int hashCode() {
            return hashCode();  //todo: ?
        }
    }
}
