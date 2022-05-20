package data_structures.chapter10_sorting_n_searching;

/**
 * p.162
 * 10.4 Sorted Search, No Size:
 * You are given an array-like data structure Listy which lacks a size
 * method. It does, however, have an elementAt (i) method that returns the element at index i in
 * 0(1) time. If i is beyond the bounds of the data structure, it returns -1. (For this reason, the data
 * structure only supports positive integers.) Given a Listy which contains sorted, positive integers,
 * find the index at which an element x occurs. If x occurs multiple times, you may return any index.
 * Hints: #320, #337, #348
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem10_4 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 6, 8, 13, 14, 17};
        int k = 14;
        Listy listy = new Listy(arr);
        int result = search(listy, k);
        System.out.println(result);
    }

    public static class Listy {
        private int[] arr;

        public int elementAt(int i) {
            try {
                return arr[i];
            } catch (IndexOutOfBoundsException exception) {
                return -1;
            }
        }

        public Listy(int[] arr) {
            this.arr = arr;
        }
    }

    /**
     * ORIGINAL SOLUTION
     * We find the length in O( log n) time
     * and then do the search in 0 (log n) time
     */
    public static int search(Listy listy, int k) {
        if (listy.elementAt(0) == -1) return -1;

        int index = 1;
        // check 2^i-th elements of Listy structure until it is
        // 1) exists
        // 2) less than k
        while (listy.elementAt(index) != -1 && listy.elementAt(index) < k) {
            index *= 2;
        }

        int result = binarySearch(listy, index / 2, index, k);
        return result;
    }

    // time ~ O(logN), where N = arr.length = end + 1
    public static int binarySearch(Listy listy, int start, int end, int k) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        int midValue = listy.elementAt(mid);
        if (midValue > k || midValue == -1) {
            return binarySearch(listy, start, mid - 1, k);
        } else if (midValue < k) {
            return binarySearch(listy, mid + 1, end, k);
        } else {
            return mid;
        }
    }
}