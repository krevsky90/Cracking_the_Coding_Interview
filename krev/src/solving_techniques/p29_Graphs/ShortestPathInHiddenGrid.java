package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 1778. Shortest Path in a Hidden Grid (medium)
 * https://leetcode.com/problems/shortest-path-in-a-hidden-grid
 *
 * #Company (25.01.2025) 0 - 3 months Meta 7
 *
 * This is an interactive problem.
 *
 * There is a robot in a hidden grid, and you are trying to get it from its starting cell to the target cell in this grid. The grid is of size m x n, and each cell in the grid is either empty or blocked. It is guaranteed that the starting cell and the target cell are different, and neither of them is blocked.
 *
 * You want to find the minimum distance to the target cell. However, you do not know the grid's dimensions, the starting cell, nor the target cell. You are only allowed to ask queries to the GridMaster object.
 *
 * Thr GridMaster class has the following functions:
 *
 * boolean canMove(char direction) Returns true if the robot can move in that direction. Otherwise, it returns false.
 * void move(char direction) Moves the robot in that direction. If this move would move the robot to a blocked cell or off the grid, the move will be ignored, and the robot will remain in the same position.
 * boolean isTarget() Returns true if the robot is currently on the target cell. Otherwise, it returns false.
 * Note that direction in the above functions should be a character from {'U','D','L','R'}, representing the directions up, down, left, and right, respectively.
 *
 * Return the minimum distance between the robot's initial starting cell and the target cell. If there is no valid path between the cells, return -1.
 *
 * Custom testing:
 *
 * The test input is read as a 2D matrix grid of size m x n where:
 *
 * grid[i][j] == -1 indicates that the robot is in cell (i, j) (the starting cell).
 * grid[i][j] == 0 indicates that the cell (i, j) is blocked.
 * grid[i][j] == 1 indicates that the cell (i, j) is empty.
 * grid[i][j] == 2 indicates that the cell (i, j) is the target cell.
 * There is exactly one -1 and 2 in grid. Remember that you will not have this information in your code.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,2],[-1,0]]
 * Output: 2
 * Explanation: One possible interaction is described below:
 * The robot is initially standing on cell (1, 0), denoted by the -1.
 * - master.canMove('U') returns true.
 * - master.canMove('D') returns false.
 * - master.canMove('L') returns false.
 * - master.canMove('R') returns false.
 * - master.move('U') moves the robot to the cell (0, 0).
 * - master.isTarget() returns false.
 * - master.canMove('U') returns false.
 * - master.canMove('D') returns true.
 * - master.canMove('L') returns false.
 * - master.canMove('R') returns true.
 * - master.move('R') moves the robot to the cell (0, 1).
 * - master.isTarget() returns true.
 * We now know that the target is the cell (0, 1), and the shortest path to the target cell is 2.
 * Example 2:
 *
 * Input: grid = [[0,0,-1],[1,1,1],[2,0,0]]
 * Output: 4
 * Explanation: The minimum distance between the robot and the target cell is 4.
 * Example 3:
 *
 * Input: grid = [[-1,0],[0,2]]
 * Output: -1
 * Explanation: There is no path from the robot to the target cell.
 *
 *
 * Constraints:
 *
 * 1 <= n, m <= 500
 * m == grid.length
 * n == grid[i].length
 * grid[i][j] is either -1, 0, 1, or 2.
 * There is exactly one -1 in grid.
 * There is exactly one 2 in grid.
 */
public class ShortestPathInHiddenGrid {
    /**
     * NOT SOLVED by myself!
     * info: https://www.youtube.com/watch?v=e75HDPQ4Tb8&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=142
     * BUT gives TLE! (35/36)
     *
     * idea:
     * 1) let's initial point is (0,0)
     * 2) keep mapping 'direction's letter' -> 'direction array' (like 'U' -> (-1,0))
     * 3) keep mapping direction - reverse_direction
     * 4) use DFS to find ALL available cells and store in visited Set of (x,y)
     * also find target cell coordinates (if possible)
     * 5) use BFS to find the shortest path from (0,0) to target Cell using visited set as grid data (of available cells)
     * i.e. once we reach the cell, we exclude it from visited cell imitating that we makr it as visited by this way!
     *
     * time to implement ~ 50 mins
     */
    private Map<Character, int[]> dirsMap;
    private Map<Character, Character> dirToBackDir;
    private int[] target = null;

    {
        dirsMap = new HashMap<>();
        dirsMap.put('U', new int[]{-1, 0});
        dirsMap.put('D', new int[]{1, 0});
        dirsMap.put('L', new int[]{0, -1});
        dirsMap.put('R', new int[]{0, 1});
        dirToBackDir = new HashMap<>();
        dirToBackDir.put('U', 'D');
        dirToBackDir.put('D', 'U');
        dirToBackDir.put('L', 'R');
        dirToBackDir.put('R', 'L');
    }

    public int findShortestPath(GridMaster master) {
        Set<List<Integer>> visited = new HashSet<>();

        dfs(0, 0, master, visited);
        if (target == null) return -1;     //we could not find any path from start to dest => return -1

        //by now we have coordinates of ALL available cells in visited Set!
        //will use it as grid!
        //bfs
        Queue<int[]> q = new LinkedList<>();   //[0] - x, [1] - y, [2] - dist
        q.add(new int[]{0, 0, 0});
        //NOTE: to mark cell as visited, we will remove it from the visited set as already used cell!
        visited.remove(Arrays.asList(0, 0));

        while (!q.isEmpty()) {
            int[] el = q.poll();
            if (el[0] == target[0] && el[1] == target[1]) return el[2];

            for (char dir : dirsMap.keySet()) {
                int newX = el[0] + dirsMap.get(dir)[0];
                int newY = el[1] + dirsMap.get(dir)[1];
                if (visited.contains(Arrays.asList(newX, newY))) {
                    //NOTE: to mark cell as visited, we will remove it from the visited set as already used cell!
                    visited.remove(Arrays.asList(newX, newY));
                    q.add(new int[]{newX, newY, el[2] + 1});
                }
            }
        }

        return -1;  //it should not happen
    }

    private void dfs(int x, int y, GridMaster master, Set<List<Integer>> visited) {
        if (master.isTarget()) {
            target = new int[]{x, y};
            //do NOT stop the process! we need to find all available cells in the grid and put them into visited set!
        }

        visited.add(Arrays.asList(x, y));

        for (char dir : dirsMap.keySet()) {
            int[] dirNum = dirsMap.get(dir);
            int newX = x + dirNum[0];
            int newY = y + dirNum[1];
            if (!visited.contains(Arrays.asList(newX, newY)) && master.canMove(dir)) {
                master.move(dir);
                dfs(newX, newY, master, visited);
                //backtracking:
                master.move(dirToBackDir.get(dir));
            }
        }
    }

    class GridMaster {
        boolean canMove(char direction) {
            return false;   //stub
        }

        void move(char direction) {
            //stub
        }

        boolean isTarget() {
            return false;   //stub
        }
    }
}
