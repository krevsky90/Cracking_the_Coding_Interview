package solving_techniques.p5_CyclicSort;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6393b0a334689e585e94a29a
 * OR
 * 442. Find All Duplicates in an Array
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
 *
 * Problem Statement
 * We are given an unsorted array containing n numbers taken from the range 1 to n.
 * The array has some numbers appearing twice, find all these duplicate numbers using constant space.
 *
 * Example 1:
 * Input: [3, 4, 4, 5, 5]
 * Output: [4, 5]
 *
 * Example 2:
 * Input: [5, 4, 7, 2, 3, 5, 3]
 * Output: [3, 5]
 */
public class FindAllDuplicateNumbers {
    public static void main(String[] args) {
        int[] arr1 = {1,1,2};
        findAllDuplicateNumbers(arr1).forEach(x -> System.out.print(x + " "));

        System.out.println("");
        int[] arr2 = {5, 4, 7, 2, 3, 5, 3};
        findAllDuplicateNumbers(arr2).forEach(x -> System.out.print(x + " "));
    }

    /**
     * time to solve ~ 5-10 mins
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     *
     **/
    public static List<Integer> findAllDuplicateNumbers(int[] arr) {
        Set<Integer> setRes = new HashSet<>();

        int start = 0;
        while (start < arr.length) {
            if (start + 1 == arr[start]) {
                start++;
            } else {
                int temp = arr[arr[start] - 1];
                if (temp == arr[start]) {
                    //found duplicate
                    setRes.add(temp);
                    //go to next
                    start++;
                } else {
                    arr[arr[start] - 1] = arr[start];
                    arr[start] = temp;
                }
            }
        }

        return new ArrayList<>(setRes);
    }

    /**
     * the other solution
     */
    public List<Integer> findDuplicates(int[] nums) {
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
            if (nums[i] > 2*len) {
                result.add(i);
            }
        }

        return result;
    }
}
