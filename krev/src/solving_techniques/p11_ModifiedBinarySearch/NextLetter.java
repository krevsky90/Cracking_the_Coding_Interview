package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639f23b94e4f288d4a83ae0d
 *
 * Given an array of lowercase letters sorted in ascending order, find the smallest letter in the given array greater than a given ?key?.
 * Assume the given array is a circular list, which means that the last letter is assumed to be connected with the first letter.
 * This also means that the smallest letter in the given array is greater than the last letter of the array and is also the first letter of the array.
 * Write a function to return the next letter of the given ?key?.
 *
 * Example 1:
 * Input: ['a', 'c', 'f', 'h'], key = 'f'
 * Output: 'h'
 */
public class NextLetter {
    public static void main(String[] args) {
        char[] arr1 = {'a', 'c', 'f', 'h'};
        char key1 = 'f';
        System.out.println(findNextLetter(arr1, key1)); //h

        char[] arr2 = {'c','f','j'};
        char key2 = 'a';
        System.out.println(findNextLetter(arr2, key2)); //c

        char[] arr3 = {'c','f','j'};
        char key3 = 'c';
        System.out.println(findNextLetter(arr3, key3)); //f

        char[] arr4 = {'x','x','y','y'};
        char key4 = 'z';
        System.out.println(findNextLetter(arr4, key4)); //x
    }

    /**
     * I did not solve by myself correctly
     * time to solve ~ 19 mins
     * SOLUTION: https://leetcode.com/problems/find-smallest-letter-greater-than-target/solutions/3617754/java-100-beats-daily-challenge-9th-june-2023/
     * time ~ O(logN)
     */
    // c f j
    // f
    // mid = 2
    // low = 2
    // high = 1
    public static char findNextLetter(char[] arr, char key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high)/2;
            //remember that each lowercase letter is number in ASCII
            if (arr[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;  //this else includes case when arr[mid] = key
            }
        }

        return arr[low % arr.length];
    }

    /**
     * https://leetcode.com/problems/find-smallest-letter-greater-than-target/solutions/3578403/easy-java-solution-0ms-100-beats/
     * time ~ O(N)
     */
    public static char findNextLetterLinear(char[] letters, char target) {
        char ans = letters[0];
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > target) {
                ans = letters[i];
                break;
            }
        }
        return ans;
    }
}
