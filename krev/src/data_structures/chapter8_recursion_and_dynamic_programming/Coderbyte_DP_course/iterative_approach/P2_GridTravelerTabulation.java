package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.iterative_approach;

/**
 * https://youtu.be/oBt53YbR9Kk?t=12139
 *
 * see description of P2_GridTraveler
 */
public class P2_GridTravelerTabulation {
    public static void main(String[] args) {
        System.out.println(counter(3, 4));  //10
    }

    /**
     * m - number of rows
     * n - number of columns
     *
     * time complexity ~ O(n*m)
     * space complexity ~ O(n*m)
     *
     * time to solve ~ 10 mins
     * 2 attempts
     */
    public static int counterKrev(int m, int n) {
        int[][] memo = new int[m][n];
        //base case
        memo[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int prevRowDelta = i == 0 ? 0 : memo[i-1][j];
                int prevColumnDelta = j == 0 ? 0 : memo[i][j-1];
                memo[i][j] = prevRowDelta + prevColumnDelta;
            }
        }

        return memo[m-1][n-1];
    }

    /**
     * time complexity ~ O(n*m)
     * space complexity ~ O(n*m)
     */
    public static int counter(int m, int n) {
        int[][] memo = new int[m+1][n+1];
        //base case
        memo[1][1] = 1;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i+1 <= m) memo[i+1][j] += memo[i][j];
                if (j+1 <= n) memo[i][j+1] += memo[i][j];
            }
        }

        return memo[m][n];
    }
}
