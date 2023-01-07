package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 *  https://www.geeksforgeeks.org/merge-two-sorted-arrays/
 */
public class Problem1_1_mergeTwoSortedArrays {
    public static void main(String[] args) {
        int[] a = {1,3,4};
        int[]b = {2,3,5,6};

        int[] result = mergeTwoSortedArrays(a, b);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        int lenA = a.length;
        int lenB = b.length;
        int[] result = new int[lenA + lenB];

        int i = 0;
        int j = 0;

        while (true) {
            if (i < lenA && j < lenB) {
                if (a[i] < b[j]) {
                    result[i + j] = a[i];
                    i++;
                } else {
                    result[i + j] = b[j];
                    j++;
                }
            } else if (i >= lenA && j < lenB) {
                result[i + j] = b[j];
                j++;
            } else if (j >= lenB && i < lenA) {
                result[i + j] = a[i];
                i++;
            } else {
                break;
            }
        }

        return result;
    }
}
