package solving_techniques.p2_TwoPointers;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63dda4ad488110f74a93371d
 * see 'Similar question':
 * Problem 1: Given an unsorted array of numbers and a target ?key?, remove all instances of ?key? in-place and return the new length of the array.
 *
 * Example 1:
 * Input: [3, 2, 3, 6, 3, 10, 9, 3], Key=3
 * Output: 4
 * Explanation: The first four elements after removing every 'Key' will be [2, 6, 10, 9].
 */
public class RemoveDuplicatesInPlace {
    public static int remove(int[] arr, int key) {
        int nextElement = 0; // index of the next element which is not 'key'
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != key) {
                arr[nextElement] = arr[i];
                nextElement++;
            }
        }

        return nextElement;
    }

    //or
    public static int removeKREV(int[] arr, int key) {
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] == key) {
                continue;
            }
            arr[j] = arr[i];
            j++;
        }

        return j;
    }
}
