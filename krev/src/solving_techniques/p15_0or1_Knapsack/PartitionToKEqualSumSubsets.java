package solving_techniques.p15_0or1_Knapsack;

/**
 * 698. Partition to K Equal Sum Subsets
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 *
 * Given an integer array nums and an integer k,
 * return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 * Example 1:
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 * Example 2:
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 *
 * Constraints:
 * 1 <= k <= nums.length <= 16
 * 1 <= nums[i] <= 10^4
 * The frequency of each element is in the range [1, 4].
 */
public class PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        int[] nums = {1,1,2,2};
        int k = 2;
        boolean res = canPartitionKSubsets(nums, k);    //true
        System.out.println(res);
    }

    /**
     * NOT SOLVED by myself
     * idea (my): We can figure out what target each subset must sum to.
     * tip 1: Then, let's recursively search, where at each call to our function, we choose which of k subsets the next value will join.
     * time to solve ~ 50 mins
     * time to debug ~ 25 mins
     * 2 attempts:
     * - forgot "capacity[i] += n;' => a lot of time for debug
     *
     * Finally the solution is correct, BUT Time Limit Exceeded for example:
     * nums = [3,3,10,2,6,5,10,6,8,3,2,1,6,10,7,2]
     * k = 6
     *
     * idea #2: from https://leetcode.com/problems/partition-to-k-equal-sum-subsets/solutions/1772704/java-solution-with-comments-100-faster-1ms/
     * add code:
     *      if (capacity[i] == target) {
     *          break;
     *      }
     * in my understanding it checks if we have element (nums[start]) that is more than maximum capacity
     * and really speeds-up the solution!
     *
     */
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = 0;
        for (int n : nums) {
            totalSum += n;
        }

        if (totalSum % k != 0) return false;

        int target = totalSum/k;
        int[] capacity = new int[k];
        for (int i = 0; i < k; i++) {
            capacity[i] = target;
        }

        return fillSubsets2(nums, capacity, 0, target);
    }

    // Example #1 (positive):
    // Input: nums = [4,3,2,3,5,2,1], k = 4
    // fillSubsets2(5555, 0)
    // n=4
    // i=0:
    //     c[0]=5-4=1
    //     fillSubsets2(1555, 1)
    //         n=3
    //         i=0: ---
    //         i=1: c[1]=5-3=2
    //         fillSubsets2(1255, 2)
    //             n=2
    //             i=0: ---
    //             i=1: c[1]=2-2=0
    //                 fillSubsets2(1055, 3)
    //                     n=3
    //                     i=0: ---
    //                     i=1: ---
    //                     i=2: c[2]=5-3=2
    //                         fillSubsets2(1025, 4)
    //                             n=5
    //                             i=0: ---
    //                             i=1: ---
    //                             i=2: ---
    //                             i=3: c[3]=5-5=0
    //                             fillSubsets2(1020, 5)
    //                                 n=2
    //                                 i=0: ---
    //                                 i=1: ---
    //                                 i=2: c[2]=2-2=0
    //                                 fillSubsets2(1000, 6)
    //                                     n=1
    //                                     i=0: c[0]=1-1=0
    //                                     fillSubsets2(0000, 7)

    // Example #2 (negative):
    // Input: nums = [2,3,4], k = 3
    // fillSubsets2(333, 0)
    //     n=2
    //     i=0: c[0]=3-2=1
    //         fillSubsets2(133, 1)
    //             n=3
    //             i=0: ---
    //             i=1: c[1]=3-3=0
    //                 fillSubsets2(103, 2)
    //                     n=4
    //                     i=0: ---
    //                     i=1: ---
    //                     i=2: ---
    //             i=2:



    // [1,1,2,2] k=2
    // fillSubsets2(33, 0)
    //     n=1
    //     i=0: c[0]=3-1=2
    //         fillSubsets2(23, 1) = true
    //             n=1
    //             i=0: c[0]=2-1=1
    //                 fillSubsets2(13, 2) = false
    //                     n=2
    //                     i=0: ---
    //                     i=1: c[1]=3-2=1
    //                         fillSubsets2(11, 3) = false
    //             i=1: c[1]=3-1=2
    //                 fillSubsets2(22, 2) = true
    //                     n=2
    //                     i=0: c[0]=2-2=0
    //                         fillSubsets2(02, 3) = true
    //                             n=2
    //                             i=0: ---
    //                             i=1: c[1]=2-2=0
    //                                 fillSubsets2(00, 4) = true


    private static boolean fillSubsets2(int[] nums, int[] capacity, int start, int target) {
        //stop criteria
        if (start == nums.length) {
            return true;
        }

        int n = nums[start];
        // boolean result = false;
        for (int i = 0; i < capacity.length; i++) {
            if (capacity[i] >= n) {
                //backtracking: start
                capacity[i] -= n;

                if (fillSubsets2(nums, capacity, start + 1, target)) {
                    return true;
                }

                //backtracking: revert
                capacity[i] += n;
            }

            //!!! this is key idea !!!
            //https://leetcode.com/problems/partition-to-k-equal-sum-subsets/solutions/1772704/java-solution-with-comments-100-faster-1ms/
            //it really speeds-up the solution!
            //IF capacity[i] == target THEN we could not add n to empty bucket => there is no sense trying to continue, since n can't be added to any bucket
            if (capacity[i] == target) {
                break;
            }
        }

        return false;
    }
}
