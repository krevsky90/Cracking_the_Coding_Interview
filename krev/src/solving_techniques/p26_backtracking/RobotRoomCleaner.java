package solving_techniques.p26_backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 489 - Robot Room Cleaner (hard) (locked)
 * https://leetcode.com/problems/robot-room-cleaner
 * OR
 * https://leetcode.ca/2017-04-02-489-Robot-Room-Cleaner/
 *
 * #Company: Amazon Facebook Google Microsoft
 * <p>
 * You are controlling a robot that is located somewhere in a room.
 * The room is modeled as an m x n binary grid where 0 represents a wall and 1 represents an empty slot.
 * <p>
 * The robot starts at an unknown location in the room that is guaranteed to be empty,
 * and you do not have access to the grid, but you can move the robot using the given  API Robot.
 * <p>
 * You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room).
 * The robot with the four given  APIs can move forward, turn left, or turn right. Each turn is 90 degrees.
 * <p>
 * When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell.
 * <p>
 * Design an algorithm to clean the entire room using the following APIs:
 * <p>
 * interface Robot {
 * // returns true if next cell is open and robot moves into the cell.
 * // returns false if next cell is obstacle and robot stays on the current cell.
 * boolean move();
 * <p>
 * // Robot will stay on the same cell after calling turnLeft/turnRight.
 * // Each turn will be 90 degrees.
 * void turnLeft();
 * void turnRight();
 * <p>
 * // Clean the current cell.
 * void clean();
 * }
 * <p>
 * Note that the initial direction of the robot will be facing up. You can assume all four edges of the grid are all surrounded by a wall.
 * <p>
 * Custom testing:
 * The input is only given to initialize the room and the robot's position internally.
 * You must solve this problem "blindfolded".
 * In other words, you must control the robot using only the four mentioned APIs without knowing the room layout and the initial robot's position.
 * <p>
 * Example 1:
 * Input: room = [[1,1,1,1,1,0,1,1],[1,1,1,1,1,0,1,1],[1,0,1,1,1,1,1,1],[0,0,0,1,0,0,0,0],[1,1,1,1,1,1,1,1]], row = 1, col = 3
 * Output: Robot cleaned all rooms.
 * Explanation: All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right.
 * <p>
 * Example 2:
 * Input: room = [[1]], row = 0, col = 0
 * Output: Robot cleaned all rooms.
 * <p>
 * Constraints:
 * m == room.length
 * n == room[i].length
 * 1 <= m <= 100
 * 1 <= n <= 200
 * room[i][j] is either 0 or 1.
 * 0 <= row < m
 * 0 <= col < n
 * room[row][col] == 1
 * All the empty cells can be visited from the starting position.
 */
public class RobotRoomCleaner {
    interface Robot {
        // returns true if next cell is open and robot moves into the cell.
        // returns false if next cell is obstacle and robot stays on the current cell.
        boolean move();

        // Robot will stay on the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        void turnLeft();

        void turnRight();

        // Clean the current cell.
        void clean();
    }

    /**
     * NOT SOLVED by myself
     * NOT TESTED even locally!
     * info:
     * https://www.youtube.com/watch?v=OyaHANapsh0&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=52
     * idea:
     * 1) set directions array with strict sequence of elements: we can 'move' from one current direction to the next one by turnRight operation
     * 2) despite we don't know the real initial position, we can track visited Set of pairs (x,y), considering that we are in (0,0)
     * 3) use DFS to visit all available cells AND backtracking to back to the previous state, change direction and move to this direction
     * 3.2) to use backtracking approach we need special sequence of the actions to do 'undo' for move() action
     * 4) calculate tempDirection based on current direction + desired direction
     * <p>
     * HONESTLY do not understand it 100% (i.e. not sure that will be able to reproduce this solution in future)
     * <p>
     * time ~ O(N - M), where N - total number of cell in grid, M - number of cells-walls
     * space ~ O(N - M) - visited set
     */
    private Robot robot;
    private Set<List<Integer>> visited = new HashSet<>();
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void cleanRoom() {
        //consider we are at (0, 0)
        int initPosI = 0;
        int initPosJ = 0;
        // 0 - up
        // 1 - right
        // 2 - down
        // 3 - left
        //initially direction = 'up'
        int initDir = 0;

        cleanDfs(initPosI, initPosJ, initDir);
    }

    private void cleanDfs(int i, int j, int direction) {
        visited.add(Arrays.asList(i, j));
        robot.clean();

        for (int k = 0; k < 4; k++) {
            // see idea #4. For example, if direction = 2 (i.e. 'down') and k = 1 (i.e. 'right'),
            // then the total direction will be 2 + 1 = 3 - i.e. 'left'
            int tempDirection = (direction + k) % 4;
            int newI = i + dirs[tempDirection][0];
            int newJ = j + dirs[tempDirection][1];
            if (!visited.contains(Arrays.asList(newI, newJ)) && robot.move()) {
                cleanDfs(newI, newJ, tempDirection);
                //revert the movement (i.e. backtrack)
                //idea #3 and 3.2
                goBack();
            }

            //this is necessary since we need to change the direction by special method, we can't just move, even if we calculate newI and newJ
            //since our 'dirs' array has property: next element/direction = current direction + turnRight, then we need to call turnRight()
            robot.turnRight();
        }
    }


    // idea # 3.2:
    // to revert move(), we need:
    // a) turn, for example, to the right 2 times
    // b) move
    // c) turn, for example, to the right 2 times
    // after that we will be in the previous cell and have the same direction as we had before the movement
    private void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}

