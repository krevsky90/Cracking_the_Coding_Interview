package solving_techniques.different;

import java.util.*;

/**
 * 1424. Diagonal Traverse II (medium)
 * https://leetcode.com/problems/diagonal-traverse-ii
 * <p>
 * #Company (1.03.2025): 0 - 3 months Meta 8 6 months ago Amazon 6 Google 5 Adobe 3 Apple 3 Microsoft 2
 * <p>
 * Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.
 * <p>
 * Example 1:
 * Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,4,2,7,5,3,8,6,9]
 * <p>
 * Example 2:
 * Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 * Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i].length <= 10^5
 * 1 <= sum(nums[i].length) <= 10^5
 * 1 <= nums[i][j] <= 10^5
 */
public class DiagonalTraverse2 {
    /**
     * KREVSKY SOLUTION: gives TLE!!!
     * time to solve ~ 24 mins
     * <p>
     * space ~ O(1)
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int maxRowLen = 0;
        int numOfElements = 0;
        for (List<Integer> r : nums) {
            numOfElements += r.size();
            if (maxRowLen < r.size()) {
                maxRowLen = r.size();
            }
        }

        int[] result = new int[numOfElements];
        int cur = 0;

        for (int k = 0; k < nums.size() + maxRowLen - 1; k++) {
            int i = k >= nums.size() ? nums.size() - 1 : k;
            int j = k >= nums.size() ? k - nums.size() + 1 : 0;

            while (i >= 0) {
                if (j < nums.get(i).size()) {
                    result[cur] = nums.get(i).get(j);
                    cur++;
                }
                i--;
                j++;
            }
        }
        return result;
    }

    /**
     * Official solution #1
     * idea:
     * 1) traverse from i = nums.size() - 1 and j = 0;
     * 2) use hashHap with groups where key = i + j (diagonal)
     * 3) traverse all group from the lowest diagonal key to max key (using while)
     * <p>
     * time ~ O(n)
     * space ~ O(n), where n - total amount of numbers in all lists
     * <p>
     * BEATS ~ 48%
     */
    public int[] findDiagonalOrderOfficial(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> groups = new HashMap();
        int n = 0;
        for (int row = nums.size() - 1; row >= 0; row--) {
            for (int col = 0; col < nums.get(row).size(); col++) {
                int diagonal = row + col;
                if (!groups.containsKey(diagonal)) {
                    groups.put(diagonal, new ArrayList<Integer>());
                }

                groups.get(diagonal).add(nums.get(row).get(col));
                n++;
            }
        }

        int[] ans = new int[n];
        int i = 0;
        int curr = 0;

        while (groups.containsKey(curr)) {
            for (int num : groups.get(curr)) {
                ans[i] = num;
                i++;
            }

            curr++;
        }

        return ans;
    }

    /**
     * Official solution: use BFS
     * <p>
     * idea: traverse from (0,0) cell to the bottom left dot
     * go to right and bottom (i.e. we will keep queue that has diagonal cells)
     * <p>
     * time ~ O(n)
     * space ~ O(sqrt(n)) - since we store only diagonal => max diagonal = sqrt(n)
     */
    public int[] findDiagonalOrderOfficialBFS(List<List<Integer>> nums) {
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{0, 0});
        List<Integer> ans = new ArrayList();

        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int row = p[0];
            int col = p[1];
            ans.add(nums.get(row).get(col));

            //try to go to bottom
            if (col == 0 && row + 1 < nums.size()) {
                queue.offer(new int[]{row + 1, col});
            }

            //try to go to the right
            if (col + 1 < nums.get(row).size()) {
                queue.offer(new int[]{row, col + 1});
            }
        }

        // Java needs conversion
        int[] result = new int[ans.size()];
        int i = 0;
        for (int num : ans) {
            result[i] = num;
            i++;
        }

        return result;
    }
}
