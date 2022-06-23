package data_structures.chapter10_sorting_n_searching;

/**
 * p.163
 * 10.11 Peaks and Valleys:
 * In an array of integers,
 * a "peak" is an element which is greater than or equal to the adjacent integers
 * and a "valley" is an element which is less than or equal to the adjacent integers.
 * For example, in the array {5, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {S, 2} are valleys.
 * Given an array of integers, sort the array into an alternating sequence of peaks and valleys.
 * EXAMPLE
 * Input:  {5, 3, 1, 2, 3}
 * Output: {5, 1, 3, 2, 3}
 * Hints: #196, #219, #231, #253, #277, #292, #316
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem10_11 {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 8, 6, 2, 3, 4, 6};
//        sort(arr);
        sortValleyPeak(arr);
        System.out.println("");
    }

    /**
     * KREVSKY SOLUTION
     * time ~ O(n)
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 3) return;

        int i = 1;
        while (i < arr.length) {
            if (isPeak(arr, i) || isValley(arr, i)) {
                i++;    //i.e. do nothign with i-th element
            } else {
                //swap i and i+1
                swap(arr, i, i + 1);
                i += 2;
            }
        }
    }

    protected static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    protected static boolean isPeak(int[] arr, int i) {
        if (i == 0) {
            return arr[i] >= arr[i + 1];  //BUT according to sort method (where i starts from 1) it would never happened
        } else if (i == arr.length - 1) {
            return arr[i] >= arr[i - 1];
        } else {
            return arr[i - 1] <= arr[i] && arr[i] >= arr[i + 1];
        }
    }

    protected static boolean isValley(int[] arr, int i) {
        if (i == 0) {
            return arr[i] <= arr[i + 1];  //BUT according to sort method (where i starts from 1) it would never happened
        } else if (i == arr.length - 1) {
            return arr[i] <= arr[i - 1];
        } else {
            return arr[i - 1] >= arr[i] && arr[i] <= arr[i + 1];
        }
    }

    /**
     * ORIGINAL SOLUTION:
     * idea: We can fix this sequence by swapping the center element with the largest adjacent element
     * time ~ O(n)
     */
    public static void sortValleyPeak(int[] array) {
        for (int i = 1; i < array.length; i += 2) {
            int biggestIndex = maxIndex(array, i - 1, i, i + 1);
            if (i != biggestIndex) {
                swap(array, i, biggestIndex);
            }
        }
    }

    protected static int maxIndex(int[] array, int a, int b, int c) {
        int len = array.length;
        int aValue = a >= 0 && a < len ? array[a] : Integer.MIN_VALUE;
        int bValue = b >= 0 && b < len ? array[b] : Integer.MIN_VALUE;
        int cValue = c >= 0 && c < len ? array[c] : Integer.MIN_VALUE;

        int max = Math.max(aValue, Math.max(bValue, cValue));
        if (aValue == max) return a;
        else if (bValue == max) return b;
        else return c;
    }


}
