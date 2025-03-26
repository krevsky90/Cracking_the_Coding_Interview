package solving_techniques.p11_ModifiedBinarySearch.binarySearch;

/**
 * 2560. House Robber IV (medium)
 * https://leetcode.com/problems/house-robber-iv/
 * <p>
 * #Company (26.03.2025): 0 - 3 months Google 8 Amazon 5 Meta 3 Microsoft 3 Bloomberg 3 6 months ago LinkedIn 3 Adobe 2 Cashfree 2
 * <p>
 * There are several consecutive houses along a street, each of which has some money inside.
 * There is also a robber, who wants to steal money from the homes, but he refuses to steal from adjacent homes.
 * <p>
 * The capability of the robber is the maximum amount of money he steals from one house of all the houses he robbed.
 * <p>
 * You are given an integer array nums representing how much money is stashed in each house.
 * More formally, the ith house from the left has nums[i] dollars.
 * <p>
 * You are also given an integer k, representing the minimum number of houses the robber will steal from. It is always possible to steal at least k houses.
 * <p>
 * Return the minimum capability of the robber out of all the possible ways to steal at least k houses.
 * <p>
 * Example 1:
 * Input: nums = [2,3,5,9], k = 2
 * Output: 5
 * Explanation:
 * There are three ways to rob at least 2 houses:
 * - Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) = 5.
 * - Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) = 9.
 * - Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) = 9.
 * Therefore, we return min(5, 9, 9) = 5.
 * <p>
 * Example 2:
 * Input: nums = [2,7,9,3,1], k = 2
 * Output: 2
 * Explanation: There are 7 ways to rob the houses. The way which leads to minimum capability is to rob the house at index 0 and 4. Return max(nums[0], nums[4]) = 2.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= (nums.length + 1)/2
 */
public class HouseRobber4 {
    /**
     * NOT SOLVED by myself
     * <p>
     * idea: binary search (of max capability)
     * + idea 2: how to check that current capability is possible: just use greedy traversal through nums and count elements that <= cap, and skip the next one
     * <p>
     * time spent ~ 55 mins
     * <p>
     * time ~ O(n*log(max(nums)))
     * space ~ O(1)
     * <p>
     * 2 attempts:
     * - incorrect condition low <= high, but need low < high (since we do not have -1 in case if isPossible = true)
     * <p>
     * BEATS ~ 86%
     */
    public int minCapability(int[] nums, int k) {
        //idea: binary search (of min capability)
        int low = 1;
        int high = -1;
        for (int n : nums) {
            if (high < n) high = n;
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isPossible(nums, k, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean isPossible(int[] nums, int k, int cap) {
        int counter = 0;    //number of robbed houses that have capacity <= cap
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= cap) {
                counter++;
                i++;    //skip the next house
            }
        }

        return counter >= k;
    }
}
