package solving_techniques.p5_CyclicSort;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6393b0a334689e585e94a29a
 * OR
 * 442. Find All Duplicates in an Array
 * https://leetcode.com/problems/find-all-duplicates-in-an-array
 * <p>
 * Problem Statement
 * We are given an unsorted array containing n numbers taken from the range 1 to n.
 * The array has some numbers appearing twice, find all these duplicate numbers using constant space.
 * <p>
 * Example 1:
 * Input: [3, 4, 4, 5, 5]
 * Output: [4, 5]
 * <p>
 * Example 2:
 * Input: [5, 4, 7, 2, 3, 5, 3]
 * Output: [3, 5]
 */
public class FindAllDuplicateNumbers {
    public static void main(String[] args) {
        FindAllDuplicateNumbers obj = new FindAllDuplicateNumbers();

        int[] arr1 = {1, 1, 2};
        obj.findDuplicates(arr1).forEach(x -> System.out.print(x + " "));

        System.out.println("");
        int[] arr2 = {5, 4, 7, 2, 3, 5, 3};
        obj.findDuplicates(arr2).forEach(x -> System.out.print(x + " "));
    }

    /**
     * KREVSKY SOLUTION
     * idea:
     * 1) use cyclic sort
     * 2) when you find duplicate, save it the to the result set, and GO to the NEXT index!
     * time to solve ~ 5-10 mins
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     *
     * BEATS = 36%
     **/
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> result = new HashSet<>();
        int start = 0;
        while (start < nums.length) {
            int correctIdx = nums[start] - 1;
            //check if the place of value is correct
            if (start == correctIdx) {
                start++;
            } else {
                int temp = nums[start];
                if (temp == nums[correctIdx]) {
                    //save duplicate
                    result.add(temp);
                    //go to next. otherwise we get infinite loop between these 2 duplicates
                    start++;
                } else {
                    nums[start] = nums[correctIdx];
                    nums[correctIdx] = temp;
                }
            }
        }

        return new ArrayList<>(result);
    }

    /**
     * SOLUTION #2
     */
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> result = new ArrayList<>();

        //[4,3,2,7,8,2,3,1]
        //1, 1, 2
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int idx = nums[i] % len;
            nums[idx] += len;
        }
        //[12, 11, 18, 23, 16, 2, 3, 9]
        //1, 7, 5

        for (int i = 0; i < len; i++) {
            if (nums[i] > 2 * len) {
                result.add(i);
            }
        }

        return result;
    }
}
