package solving_techniques.p22_Island_MatrixTraversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. Number of Islands (medium)
 * https://leetcode.com/problems/number-of-islands
 * OR
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6388cbb0765bb2154037ce84
 *
 * #Company: Adobe Affirm Alibaba Amazon AppDynamics Apple Arista Networks Atlassian Audible BlackRock Bloomberg Cisco Citadel Citrix Coursera Cruise Automation DoorDash eBay Electronic Arts Evernote Expedia Facebook Goldman Sachs Google Houzz Hulu caMorgan LinkedIn LiveRamp Lyft Mathworks Microsoft Nutanix Nvidia Oracle Palantir Technologies Paypal Qualtrics Salesforce SAP Snapchat Splunk Spotify Square Sumologic Tableau Twitch Twitter Uber Visa VMware Walmart Labs Wish Yahoo Yelp Zenefits Zulily
 *
 * Given a 2D array (i.e., a matrix) containing only 1s (land) and 0s (water), count the number of islands in it.
 *
 * An island is a connected set of 1s (land) and is surrounded by either an edge or 0s (water).
 * Each cell is considered connected to other cells horizontally or vertically (not diagonally).
 *
 * Example 1:
 * 0 1 1 1 0
 * 0 0 0 1 1
 * 0 1 1 1 0
 * 0 1 1 0 0
 * 0 0 0 0 0
 * Output: 1
 *
 * Example 2:
 * 1 1 1 0 0
 * 0 1 0 0 1
 * 0 0 1 1 0
 * 0 0 1 0 0
 * 0 0 1 0 0
 * Output: 3
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        NumberOfIslands obj = new NumberOfIslands();
        int[][] matrix1 = {{0,1,1,1,0},{0,0,0,1,1},{0,1,1,1,0},{0,1,1,0,0},{0,0,0,0,0}};    //expected 1
        int[][] matrix2 = {{1,1,1,0,0},{0,1,0,0,1},{0,0,1,1,0},{0,0,1,0,0},{0,0,1,0,0}};    //expected 3

        //NOTE: call of the method affects matrix itself => to test other methods we need to comment the previous ones

        System.out.println(obj.countIslandsDFS(matrix1));
        System.out.println(obj.countIslandsDFS(matrix2));
//
//        System.out.println(obj.countIslandsBFS(matrix1));
//        System.out.println(obj.countIslandsBFS(matrix2));

//        System.out.println(obj.countIslandsBFSWithVisitedMatrix(matrix1));
//        System.out.println(obj.countIslandsBFSWithVisitedMatrix(matrix2));
    }

    /**
     * SOLUTION #1:
     * 1) use DFS
     * 2) to mark a cell visited, we can update the given input matrix. Whenever we see a '1', we will make it '0'.
     * time to solve ~ 10-15 mins
     *
     * time ~ O(n*m)
     * space O(n*m) - worst case: matrix is filled by '1'
     *
     * 1 attempt
     */
    public int countIslandsDFS(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int result = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    result++;
                    markIslandDFS(matrix, i, j, n, m);
                }
            }
        }

        return result;
    }

    private void markIslandDFS(int[][] matrix, int i, int j, int n, int m) {
        if (i < 0 || i >= n || j < 0 || j >= m) return; //ignore not valid cell

        if (matrix[i][j] == 0) return;  //current cell is NOT piece of island OR it is already visited

        matrix[i][j] = 0;   //mark as visited

        //DFS in 4 directions
        markIslandDFS(matrix, i-1, j, n, m);
        markIslandDFS(matrix, i+1, j, n, m);
        markIslandDFS(matrix, i, j-1, n, m);
        markIslandDFS(matrix, i, j+1, n, m);
    }

    /**
     * SOLUTION #2.1:
     * 1) use BFS
     * 2) to mark a cell visited, we can update the given input matrix. Whenever we see a '1', we will make it '0'.
     * time to solve ~ 10-15 mins
     *
     * time ~ O(n*m)
     * space O(min(n*m)) - the worst case, when the matrix is completely filled with land cells, the size of the queue can grow up to min(n*m)
     *
     * 1 attempt
     */
    public int countIslandsBFS(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int result = 0;
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    result++;
                    markIslandBFS(matrix, i, j, n, m);
                }
            }
        }

        return result;
    }

    private void markIslandBFS(int[][] matrix, int i, int j, int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});

        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int row = pair[0];
            int col = pair[1];
            if (row < 0 || row >= n || col < 0 || col >= m) continue;   //ignore not valid cell

            if (matrix[row][col] == 0) continue;  //current cell is NOT piece of island OR it is already visited

            matrix[row][col] = 0;   //mark as visited

            //BFS
            q.add(new int[]{row+1,col});
            q.add(new int[]{row-1,col});
            q.add(new int[]{row,col+1});
            q.add(new int[]{row,col-1});
        }
    }

    /**
     * SOLUTION #2.2:
     * 1) use BFS
     * 2) keep separate matrix to mark a cell visited
     * time to solve ~ 10 mins
     *
     * time ~ O(n*m)
     * space O(n*m) - since we store separate 'visit' matrix that has the same size as the original matrix
     *
     * 1 attempt
     */
    public int countIslandsBFSWithVisitedMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int result = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1 && visited[i][j] == false) {
                    result++;
                    markIslandBFSWithVisitedMatrix(matrix, i, j, n, m, visited);
                }
            }
        }

        return result;
    }

    private void markIslandBFSWithVisitedMatrix(int[][] matrix, int i, int j, int n, int m, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});

        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int row = pair[0];
            int col = pair[1];
            if (row < 0 || row >= n || col < 0 || col >= m) continue;   //ignore not valid cell

            if (matrix[row][col] == 0 || visited[row][col] == true) continue;   //current cell is NOT piece of island OR it is already visited

            visited[row][col] = true;   //mark as visited

            //BFS
            q.add(new int[]{row+1,col});
            q.add(new int[]{row-1,col});
            q.add(new int[]{row,col+1});
            q.add(new int[]{row,col-1});
        }
    }
}
