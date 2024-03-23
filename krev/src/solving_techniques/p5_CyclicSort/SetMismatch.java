package solving_techniques.p5_CyclicSort;

/**
 * 645. Set Mismatch (easy)
 *
 * You have a set of integers s, which originally contains all the numbers from 1 to n.
 * Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set,
 * which results in repetition of one number and loss of another number.
 * You are given an integer array nums representing the data status of this set after the error.
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 *
 * Example 1:
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 *
 * Example 2:
 * Input: nums = [1,1]
 * Output: [1,2]
 *
 * Constraints:
 * 2 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^4
 */
public class SetMismatch {
    // 1 2 2 4
    // start = 4
    // j = 1
    // duplicate = 2
    // i = 2
    // missed = 3

    // 1 1
    // start = 1
    // j = 0
    // duplicate = 1
    // missed = 2

    /**
     * KREVSKY SOLUTION #1: unified
     */
    public int[] findErrorNums(int[] nums) {
        //1. cyclic sort (as is)
        int start = 0;
        while (start < nums.length) {
            int correctIdx = nums[start] - 1;
            if (nums[start] == nums[correctIdx]) {
                start++;
            } else {
                //swap
                int temp = nums[start];
                nums[start] = nums[correctIdx];
                nums[correctIdx] = temp;
            }
        }

        //2. find the answer
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[]{nums[i], i + 1};
            }
        }

        return new int[]{-1, -1};   //stub
    }

    /**
     * KREVSKY SOLUITON #2:
     * idea = src/solving_techniques/p5_CyclicSort/FindDuplicateNumber.java
     *      + cycle with validation i+1 = nums[i] to find missed value in already sorted array
     * time to solve ~ 10 mins
     * 1 attempt
     */
    public int[] findErrorNums2(int[] nums) {
        int start = 0;
        int duplicate = -1;
        int missed = -1;
        while (start < nums.length) {
            if (start + 1 == nums[start]) {
                start++;
            } else {
                int j = nums[start] - 1;
                if (nums[start] == nums[j]) {
                    //find duplicate
                    duplicate = nums[start];
                    start++;    //to avoid infinite loop
                } else {
                    //swap
                    int temp = nums[start];
                    nums[start] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        //find missed element is the sorted array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                missed = i + 1;
                break;
            }
        }

        return new int[]{duplicate, missed};
    }
}
