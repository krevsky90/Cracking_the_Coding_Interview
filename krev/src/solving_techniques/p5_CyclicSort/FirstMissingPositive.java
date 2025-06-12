package solving_techniques.p5_CyclicSort;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63948c59c549a12fb2181118
 * OR
 * 41. First Missing Positive
 * https://leetcode.com/problems/first-missing-positive/
 *
 * Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 *
 * Example 1:
 * Input: nums = [1,2,0]
 * Output: 3
 * Explanation: The numbers in the range [1,2] are all in the array.
 *
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Explanation: 1 is in the array but 2 is missing.
 *
 * Example 3:
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * Explanation: The smallest positive integer 1 is missing.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums = {3,4,-1,1};
        FirstMissingPositive obj = new FirstMissingPositive();
        obj.firstMissingPositive(nums);
    }

    /**
     * SOLUTION #1
     * NOT SOLVED by myself
     * SOLUTION:
     * idea: https://www.youtube.com/watch?v=JfinxytTYFQ&t=4863s
     * i.e. sort the elements according to the logic "nums[i] = i + 1", IF this index exists in the array
     *
     * time ~ O(n)
     * space ~ O(1)
     *
     */

    //for better understanding:
    //0 1 2 == (sort) ==> 1 2 0
    //3,4,-1,1 == (sort) ==> -1 4 3 1 => -1 1 3 4 => 1 -1 3 4

    //NOTE: if we write "nums[start] == start + 1" instead of "nums[start] == nums[correctIdx]",
    //then we will get infinite loop with swapping for case nums = [1, 1],
    //since nums[1] = 1, start = 1 => nums[start] != start + 1 => we will swap nums[0] and nums[1], i.e. 1 and 1
    //i.e IDEA:
    //
    // NOTE: validation "nums[start] == nums[correctIdx]" EQUALS
    //      validation "nums[start] == start + 1"
    //      AND validation duplicates to avoid infinite loop "if (temp == nums[j])"
    public int firstMissingPositive(int[] nums) {
        int start = 0;
        while (start < nums.length) {
            //NOTE: we do this to set 1 at 0-th position. if we set 0 to 0-th position, then 0,1,2 will return ans = 3,
            // but we find missed positive (rather than not negative) value => trying to set 1 at 0-th pos
            int correctIdx = nums[start] - 1;
            if (correctIdx < 0 || correctIdx >= nums.length || nums[start] == nums[correctIdx]) {
                start++;
            } else {
                //swap
                int temp = nums[start];
                nums[start] = nums[correctIdx];
                nums[correctIdx] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        //if we reach this line, then all elements of nums are in correct places
        //then the answer is the next int value that greater than the last element of sorted nums
        //example: nums = [1, 2] => sorted array [1, 2] => answer = 3
        return nums.length + 1;
    }

    /**
     * KREVSKY SOLUTION #2:
     * idea: solving_techniques/p5_CyclicSort/FindDuplicateNumber + condition "if (j < 0 || j >= nums.length)"
     */
    public int firstMissingPositive2(int[] nums) {
        int start = 0;
        while (start < nums.length) {
            if (start + 1 == nums[start]) {
                start++;
            } else {
                int j = nums[start] - 1;
                if (j < 0 || j >= nums.length) {
                    start++;    //ignore this element
                } else {
                    //swap
                    int temp = nums[start];
                    //validate duplicates to avoid infinite loop
                    if (temp == nums[j]) {
                        start++;
                    } else {
                        nums[start] = nums[j];
                        nums[j] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        //if we reach this line, then all elements of nums are in correct places
        //then the answer is the next int value that greater than the last element of sorted nums
        //example: nums = [1, 2] => sorted array [1, 2] => answer = 3
        return nums.length + 1;
    }
}
