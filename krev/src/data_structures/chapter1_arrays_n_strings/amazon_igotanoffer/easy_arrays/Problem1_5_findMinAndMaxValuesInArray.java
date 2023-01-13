package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_arrays;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://www.enjoyalgorithms.com/blog/find-the-minimum-and-maximum-value-in-an-array
 * <p>
 * Find Max and Min using minimum number of comparisons
 */

public class Problem1_5_findMinAndMaxValuesInArray {
    public static void main(String[] args) {
        int[] arr1 = {4,2,0,8,20,9,2};
        int[] arr2 = {4,2,-1};
        int[] result = findMinAndMax2(arr2);
        System.out.println("");
    }
    /**
     * SOLUTION #1:
     * 2N-2 comparisons
     */
    public static int[] findMinAndMax1(int[] arr) {
        if (arr == null || arr.length == 0) return new int[]{};

        int min = arr[0];
        int max = arr[0];
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            if (min > arr[i]) {
                min = arr[i];
            } else if (max < arr[i]) {
                max = arr[i];
            }
        }

        return new int[]{min, max};
    }

    /**
     * SOLUTION #2:
     * krevsky 'divide and conquer'
     *
     * tree of calls of findMinAndMax2(int[] arr, int left, int right) has height = logN, where N = arr.length
     * BUT on each level there are different amount of calls of findMinAndMax2.
     * Each call has 2 comparisons of the elements
     * 0th level = 1 call -> 1*2 comparisons
     * 1st = 2 -> 2^2 comparisons
     * 2nd = 4 -> 2^3 comparisons
     * ...
     *(i-1)th = 2^(i-1) -> 2^i comparisons
     * i-th (leaves) = 0*2^i comparisons, 0 is because of return by condition (left >= right)
     *
     * total number of calls = 2^(i+1) = N => i = logN - 1
     * total amount of comparisons from 0-th to i-th levels:
     * 2 + 4 + ... + 2^i = (geometric series) = 2*(2^i - 1)/(2 - 1) = 2*(2^i - 1) = 2*(2^(logN - 1) - 1) = 2*(N*(2^-1) - 1) = 2*(N/2 - 1) = N - 2
     *
     **/
    public static int[] findMinAndMax2(int[] arr) {
        if (arr == null || arr.length == 0) return new int[]{};

        return findMinAndMax2(arr, 0, arr.length - 1);
    }

    public static int[] findMinAndMax2(int[] arr, int left, int right) {
        if (left >= right) return new int[]{arr[right], arr[right]};

        int mid = (left + right) / 2;
        int[] leftMinMax = findMinAndMax2(arr, left, mid);
        int[] rightMinMax = findMinAndMax2(arr, mid + 1, right);
        int min = leftMinMax[0] > rightMinMax[0] ? rightMinMax[0] : leftMinMax[0];    //comparison 1
        int max = leftMinMax[1] < rightMinMax[1] ? rightMinMax[1] : leftMinMax[1];    //comparison 2
        return new int[]{min, max};
    }

    /**
     * SOLUTION #3: loop increment by 2
     *
     * number of comparisons
     * for odd length:  3/2*(n-1)
     * for even length: 3/2*(n-2)
     */
    public static int[] findMinAndMax3(int[] arr) {
        int n = arr.length;

        int min, max, i;

        if (n % 2 == 1) {
            min = arr[0];
            max = arr[0];
            i = 1;
        } else {
            min = arr[0];
            max = arr[1];
            i = 2;
        }

        while (i < n) {
            if (arr[i] < arr[i+1]) {	//1 comparison
                if (arr[i] < min) {     //2 comparison
                    min = arr[i];
                }
                if (arr[i+1] > max) {   //3 comparison
                    max = arr[i+1];
                }
            } else {
                if (arr[i] > max) {     //2 comparison
                    max = arr[i];
                }
                if (arr[i+1] < min) {   //3 comparison
                    min = arr[i+1];
                }
            }
            i += 2;
        }

        return new int[]{min, max};
    }
}
