package solving_techniques.p29_Graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1340. Jump Game V (hard)
 * https://leetcode.com/problems/jump-game-v/
 * <p>
 * #Company: Facebook
 * <p>
 * Given an array of integers arr and an integer d. In one step you can jump from index i to index:
 * <p>
 * i + x where: i + x < arr.length and  0 < x <= d.
 * i - x where: i - x >= 0 and  0 < x <= d.
 * In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j
 * (More formally min(i, j) < k < max(i, j)).
 * <p>
 * You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * Example 1:
 * Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
 * Output: 4
 * Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
 * Note that if you start at index 6 you can only jump to index 7.
 * You cannot jump to index 5 because 13 > 9.
 * You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
 * Similarly You cannot jump from index 3 to index 2 or index 1.
 * <p>
 * Example 2:
 * Input: arr = [3,3,3,3,3], d = 3
 * Output: 1
 * Explanation: You can start at any index. You always cannot jump to any index.
 * <p>
 * Example 3:
 * Input: arr = [7,6,5,4,3,2,1], d = 1
 * Output: 7
 * Explanation: Start at index 0. You can visit all the indices.
 * <p>
 * Constraints:
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 10^5
 * 1 <= d <= arr.length
 */
public class JumpGameV {
    /**
     * KREVSKY SOLUTION #1:
     * idea:
     * 1) use graph-like DFS approach for each position in te array to find max distances
     * 2) find max of max distances
     * 3) use memoization not to apply DFS to the index for which we have already got the result
     * <p>
     * time to solve ~ 20-25 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 75%
     *
     * time complexity ~ O(N*d)
     * space ~ O(N)
     */
    public int maxJumps(int[] arr, int d) {
        int result = -1;
        int[] memo = new int[arr.length];
        Arrays.fill(memo, -1);

        for (int i = 0; i < arr.length; i++) {
            result = Math.max(result, dfs(arr, i, d, memo));
        }

        return result;
    }

    private int dfs(int[] arr, int curIdx, int d, int[] memo) {
        if (memo[curIdx] != -1) return memo[curIdx];

        int maxDist = 1;

        for (int i = 1; i <= d && curIdx + i < arr.length && arr[curIdx] > arr[curIdx + i]; i++) {
            maxDist = Math.max(maxDist, 1 + dfs(arr, curIdx + i, d, memo));
        }

        for (int i = 1; i <= d && curIdx - i >= 0 && arr[curIdx] > arr[curIdx - i]; i++) {
            maxDist = Math.max(maxDist, 1 + dfs(arr, curIdx - i, d, memo));
        }

        return memo[curIdx] = maxDist;
    }

    /**
     * SOLUTION #2:
     * NOTE: we also can use BFS approach,
     * BUT in this cae we can't apply memoization!!!
     */
    private int bfs(int[] arr, int startIdx, int d) {
        int maxDist = 1;
        Queue<int[]> q = new LinkedList<>();    //int[] - index of current position, distance
        q.add(new int[]{startIdx, 1});

        while (!q.isEmpty()) {
            int[] data = q.poll();
            int curIdx = data[0];
            int curDist = data[1];
            maxDist = Math.max(maxDist, curDist);

            for (int i = 1; i <= d && curIdx + i < arr.length && arr[curIdx] > arr[curIdx + i]; i++) {
                q.add(new int[]{curIdx + i, curDist + 1});
            }

            for (int i = 1; i <= d && curIdx - i >= 0 && arr[curIdx] > arr[curIdx - i]; i++) {
                q.add(new int[]{curIdx - i, curDist + 1});
            }
        }

        return maxDist;
    }
}
