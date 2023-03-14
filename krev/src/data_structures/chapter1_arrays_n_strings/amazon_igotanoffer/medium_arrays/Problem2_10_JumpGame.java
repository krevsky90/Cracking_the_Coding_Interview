package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_arrays;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://leetcode.com/problems/jump-game/submissions/913807078/
 *
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 * Constraints:
 * 1 <= nums.length <= 10000
 * 0 <= nums[i] <= 100000
 */
public class Problem2_10_JumpGame {
    /**
     * KREVSKY SOLUTION 1:
     * idea ~ dynamic programming
     * 1 attempt
     * not optimal (too much time)
     * time to solve ~ 5 mins
     */
    public boolean canJumpKrev1(int[] nums) {
        return canJump(nums, 0);
    }

    public boolean canJump(int[] nums, int curPos) {
        if (curPos == nums.length - 1) return true;

        for (int i = 1; i <= nums[curPos]; i++) {
            if (canJump(nums, curPos + i)) return true;
        }

        return false;
    }

    /**
     * KREVSKY SOLUTION 2:
     * idea ~ dynamic programming
     * 2 attempts
     * almost not optimal (too much time), but ACCEPTED
     * time to solve ~ 15 mins
     */
    public boolean canJumpKrev2(int[] nums) {
        boolean[] arr = new boolean[nums.length];
        arr[nums.length - 1] = true;

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + nums[i]; j >= i; j--) {
                if (j < nums.length && arr[j]) {
                    arr[i] = true;
                    break;
                }
            }
        }

        return arr[0];
    }

    /**
     * https://medium.com/@greekykhs/leetcode-java-solution-of-jump-game-problem-88596dbde66b
     */
    public boolean canJump1(int[] nums) {
        if (nums.length <= 1) return true;

        //the largest index that can be reached
        int largestIndex = nums[0];

        for (int i = 0; i < nums.length; i++) {
            //the only way when we can't reach further position is when we have nums[x] = 0 and can't jump over these zero-cells
            if (largestIndex <= i && nums[0] == 0) {
                return false;
            }

            //update largestIndex if we can reach further position
            if (nums[i] + i > largestIndex) {
                largestIndex = nums[i] + i;
            }

            //can we reach the end of nums array?
            if (largestIndex == nums.length - 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * https://medium.com/@greekykhs/leetcode-java-solution-of-jump-game-problem-88596dbde66b
     * Second Solution of Leet Code’s Jump Game problem: Last Known Position
     *
     * Algorithm:
     * 1). We will maintain a variable lastAccuratePosition from which we can reach the last position. Since we can reach the last position from the last index we initialize lastAccuratePosition with the index of last element (i.e nums.length-1).
     * 2). Now we will start iterating the input array from right (second last position) to the left.
     * 3). In each iteration we will calculate furthestJump which is the summation of index and the value at that index (i.e nums[i]+i).
     * 4). We will check if furthestJump is greater than or equal to lastAccuratePosition. If yes, then we will update the value of lastAccuratePosition with the current index.
     * 5). After the iteration we will check if lastAccuratePosition is zero return true, else return false.
     */
    public boolean canJump2(int[] nums) {
        int lastAccuratePosition = nums.length - 1;
        int furtherJump = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            furtherJump = i + nums[i];
            if (furtherJump >= lastAccuratePosition) {
                lastAccuratePosition = i;
            }
        }

        return lastAccuratePosition == 0;
    }
}
