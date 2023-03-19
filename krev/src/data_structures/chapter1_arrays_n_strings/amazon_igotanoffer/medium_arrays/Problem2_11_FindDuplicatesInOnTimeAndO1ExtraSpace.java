package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_arrays;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
 *
 * Given an array of n elements that contains elements from 0 to n-1, with any of these numbers appearing any number of times.
 * Find these repeating numbers in O(n) and using only constant memory space.
 *
 * Example:
 * Input : n = 7 and array[] = {1, 2, 3, 6, 3, 6, 1}
 * Output: 1, 3, 6
 * Explanation: The numbers 1 , 3 and 6 appears more than once in the array.
 *
 * Input : n = 5 and array[] = {1, 2, 3, 4 ,3}
 * Output: 3
 * Explanation: The number 3 appears more than once in the array.
 */
public class Problem2_11_FindDuplicatesInOnTimeAndO1ExtraSpace {
    /**
     * official solution
     * idea ~ to track number of repetitions inside the given array (not to use extra space)
     */
    public static void main(String[] args) {

        //arr1 = {1, 2, 3, 6, 3, 6, 1};
        int[] arr = {1, 2, 3, 6, 3, 6, 1};
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int index = arr[i] % n;
            arr[index] += n;
        }
        //arr1 = {1, 16, 10, 20, 3, 6, 15}
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (arr[i] / n >= 2) {
                int valueToPrint = i % n;   //not arr[i]! since i-th element is index in the first loop => it causes addition of length to i-th position
                sb.append(valueToPrint).append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}