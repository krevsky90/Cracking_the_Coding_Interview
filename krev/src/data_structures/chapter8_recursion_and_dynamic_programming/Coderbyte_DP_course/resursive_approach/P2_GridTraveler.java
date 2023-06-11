package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.resursive_approach;

/**
 * https://youtu.be/oBt53YbR9Kk?t=2323
 *
 * Go from top-left corner to bottom-right corner of 2D grid. You may move only down or right
 * How many ways you can travel if the size is m*n?
 */
public class P2_GridTraveler {
    public static void main(String[] args) {
        System.out.println(new P2_GridTraveler().counterKrev1(3, 4));
    }

    /**
     * recursive memo
     */
    public int counterKrev1(int m, int n) {
        int[][] memo = new int[m][n];
        counterHelperKrev1(m - 1, n - 1, memo);
        return memo[m - 1][n - 1];
    }


    public int counterHelperKrev1(int m, int n, int[][] memo) {
        if (memo[m][n] > 0) return memo[m][n];

        if (m == 0 || n == 0) {
            memo[m][n] = 1;
        } else {
            memo[m][n] = counterHelperKrev1(m - 1, n, memo) + counterHelperKrev1(m, n - 1, memo);
        }

        return memo[m][n];
    }


}
