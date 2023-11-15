package solving_techniques.p5_CyclicSort;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6393ada834689e585e94a1b9
 * OR
 * 448. Find All Numbers Disappeared in an Array
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * We are given an unsorted array containing numbers taken from the range 1 to ?n?.
 * The array can have duplicates, which means some numbers will be missing.
 * Find all those missing numbers.
 *
 * Example 1:
 * Input: [2, 3, 1, 8, 2, 3, 5, 1]
 * Output: 4, 6, 7
 * Explanation: The array should have all numbers from 1 to 8, due to duplicates 4, 6, and 7 are missing.
 *
 * Example 2:
 * Input: [2, 4, 1, 2]
 * Output: 3
 */
public class FindAllMissingNumbers {
    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 8, 2, 3, 5, 1};
        //
        findAllMissingNumbers(arr1).forEach(x -> System.out.print(x + " "));

        System.out.println("");
        int[] arr2 = {2, 4, 1, 2};
        findAllMissingNumbers(arr2).forEach(x -> System.out.print(x + " "));

        System.out.println("");
        int[] arr3 = {2, 3, 2, 1};
        findAllMissingNumbers(arr3).forEach(x -> System.out.print(x + " "));
    }

    /**
     * time to solve ~ 5-10 mins
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     *
     **/
    public static List<Integer> findAllMissingNumbers(int[] arr) {

        int start = 0;

        while (start < arr.length) {
            if (start + 1 == arr[start]) {
                start++;
            } else {
                int temp = arr[arr[start] - 1];
                if (temp == arr[start]) {
                    //find duplicates => if we try to swap these elements we will go to infinite loop => just move forward:
                    start++;
                } else {
                    //swap
                    arr[arr[start] - 1] = arr[start];
                    arr[start] = temp;
                }
            }
        }

        //2. traverse through the arr and check if i == arr[i]. if not - then i is missing number
        List<Integer> missingNumbers = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i + 1 != arr[i]) {
                missingNumbers.add(i + 1);
            }
        }

        return missingNumbers;
    }


}
