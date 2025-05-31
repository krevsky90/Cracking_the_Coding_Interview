package solving_techniques.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1931. Painting a Grid With Three Different Colors (hard)
 * https://leetcode.com/problems/painting-a-grid-with-three-different-colors
 * <p>
 * #Company (31.05.2025): 0 - 3 months Google 6 Meta 2 Amazon 2 0 - 6 months Microsoft 2 6 months ago Bloomberg 2
 * <p>
 * You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each cell red, green, or blue. All cells must be painted.
 * <p>
 * Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can be very large, return it modulo 109 + 7.
 * <p>
 * Example 1:
 * Input: m = 1, n = 1
 * Output: 3
 * Explanation: The three possible colorings are shown in the image above.
 * <p>
 * Example 2:
 * Input: m = 1, n = 2
 * Output: 6
 * Explanation: The six possible colorings are shown in the image above.
 * <p>
 * Example 3:
 * Input: m = 5, n = 5
 * Output: 580986
 * <p>
 * Constraints:
 * 1 <= m <= 5
 * 1 <= n <= 1000
 */
public class PaintingGridWith3DifferentColors {
    /**
     * NOT SOLVED by myself
     * idea:
     * https://leetcode.com/problems/painting-a-grid-with-three-different-colors/solutions/6755090/optimal-solution-full-logic-explained-si-bxjw/
     * <p>
     * time to implement ~ 80 mins
     * <p>
     * time ~ O(n*k^2)
     * space ~ O(k^2), where k = number of valid column states (≤ 3^m)
     * <p>
     * 1 attempt:
     * <p>
     * BEATS ~ 85%
     */
    private static final int MOD = 1_000_000_007;

    public int colorTheGrid(int m, int n) {
        List<List<Integer>> states = new ArrayList<>();
        generateValidStates(states, new ArrayList<>(), m);

        int size = states.size();

        //between states of adj columns
        List<List<Integer>> transitions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            transitions.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                if (isCompatible(states.get(i), states.get(j))) {
                    transitions.get(i).add(j);
                }
            }
        }

        //dp[i] - temp array, keeps number of ways of how current column may come to i-th (from all) state
        long[] dp = new long[size];
        //initially 'current' columns is 0th columns and each state can be formed in 1 way
        Arrays.fill(dp, 1);

        //look through the rest (from 1 to n - 1) columns
        //newDp[j] - temp array, keeps number of ways of how current+1-th column may come to j-th (from all) state
        //but it can be calculated by dp data and transitions from i-th to j-th state
        for (int col = 1; col < n; col++) {
            long[] newDp = new long[size];
            for (int i = 0; i < size; i++) {
                // each (i-th) state from dp can affect all possible (according to transitions) states from newDp
                for (int j : transitions.get(i)) {
                    newDp[j] = (newDp[j] + dp[i]) % MOD;
                }
            }
            //once it is done, we set dp = newDp and calculate newDp again
            dp = newDp;
        }

        //result if sum of all dp elements;
        long result = 0;
        for (long v : dp) {
            result = (result + v) % MOD;
        }

        return (int) result;
    }

    private void generateValidStates(List<List<Integer>> states, List<Integer> tempState, int m) {
        if (m == 0) {
            states.add(new ArrayList<>(tempState));
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (!tempState.isEmpty() && tempState.get(tempState.size() - 1).intValue() == i) {
                continue;
            }

            tempState.add(i);
            generateValidStates(states, tempState, m - 1);
            tempState.remove(tempState.size() - 1);
        }
    }

    private boolean isCompatible(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).equals(b.get(i))) return false;
        }
        return true;
    }
}
