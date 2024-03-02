package solving_techniques.p17_FibonacciNumbers;

/**
 * 1306. Jump Game III
 * https://leetcode.com/problems/jump-game-iii
 *
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
 * When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 * Example 1:
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 *
 * Example 2:
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 *
 * Example 3:
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 *
 *
 * Constraints:
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 */
public class JumpGame3 {
    /**
     * KREVSKY SOLUTION:
     * idea: to track visited positions to avoid loops
     * use recursion each time when we decide to go back or forward
     * time to think + implement ~ 8 + 4 mins
     * time to debug ~ 8 mins
     *
     * time ~ O(n)
     * space ~ O(n)
     * 1 attempt
     */
    // 4,2,3,0,3,1,2
    // start = 0
    // T T f f T f f
    // canReach(0) = true
    //     canReach(-4) = false
    //     canReach(4) = true
    //         canReach(7) = false
    //         canReach(1) = true
    //             canReach(-1) = false
    //             canReach(3) = true

    // 3 0 2 1 2
    // start = 2
    // T f T T T
    // canReach(2) = false
    //     canReach(0) = false
    //         canReach(-3) = false
    //         canReach(3) = false
    //             canReach(2) = false, since visited[2] = T
    //             canReach(4) = false
    //                 canReach(2) = false, since visited[2] = T
    //                 canReach(6) = false
    //     canReach(4) = false, since visited[4] = T
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return canReach(arr, start, visited);
    }

    private boolean canReach(int[] arr, int idx, boolean[] visited) {
        if (idx < 0 || idx >= arr.length) return false;
        if (arr[idx] == 0) return true;
        //to avoid loops:
        if (visited[idx] == true) return false;

        visited[idx] = true;
        return canReach(arr, idx + arr[idx], visited) || canReach(arr, idx - arr[idx], visited);
    }
}
