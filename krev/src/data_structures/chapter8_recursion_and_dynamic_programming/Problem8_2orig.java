package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.ArrayList;
import java.util.HashSet;
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
public class Problem8_2orig {
    public static ArrayList<Point> getPath(boolean[][] maze) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) return null;

        ArrayList<Point> path = new ArrayList<>();
        if (getPath(maze,maze.length - 1, maze[0].length - 1, path)) {
            return path;
        }

        return null;
    }

    /**
     * ORIGINAL SOLUTION #1:
     * returns true, if there is a path from (0,0) to (col,row).
     * Otherwise returns false.
     * The only way to move to spot (r, c) is by moving to one of the adjacent spots:
     * (r-1, c) or (r, c-1). So, we need to find a path to either (r-1, c) or (r, c-1).
     *
     * time ~ O(2^(r+c)), since each path has r+c steps and there are two choices we can make at each step.
     */
    protected static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path) {
        if (row < 0 || col < 0 || !maze[row][col]) {
            return false;
        }

        boolean isInitialPoint = row == 0 && col == 0;

        /* If there's a path from the start to here, add my location. */
        if (isInitialPoint || getPath(maze, row-1, col, path) || getPath(maze, row, col-1, path)) {
            path.add(new Point(col, row));
            return true;
        }

        return false;
    }

    /**
     * ORIGINAL SOLUTION #2: optimized
     * Since we calculate getPath for the same point mani times, we just can store boolean value for each point after calculation.
     * For example, we mat store the points that returned 'false'
     *
     * time ~ O(r*c)
     */
    protected static boolean getPathOptimized(boolean[][] maze, int row, int col, ArrayList<Point> path, HashSet<Point> failedPoints) {
        if (row < 0 || col < 0 || !maze[row][col]) {
            return false;
        }

        Point tempPoint = new Point(row, col);

        /* If we've already visited this cell, return. */
        if (failedPoints.contains(tempPoint)) {
            return false;
        }

        boolean isInitialPoint = row == 0 && col == 0;

        /* If there's a path from the start to here, add my location. */
        if (isInitialPoint || getPath(maze, row-1, col, path) || getPath(maze, row, col-1, path)) {
            path.add(new Point(col, row));
            return true;
        }

        //cache failed point
        failedPoints.add(tempPoint);
        return false;


    }

    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
