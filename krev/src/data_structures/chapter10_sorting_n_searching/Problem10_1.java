package data_structures.chapter10_sorting_n_searching;


/**
 * p.161
 * 10.1 Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at the
 * end to hold B. Write a method to merge B into A in sorted order.
 * Hints: #332
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem10_1 {
    public static void main(String[] args) {
//        int[] A = new int[9];
//        A[0] = 1;
//        A[1] = 3;
//        A[2] = 5;
//        A[3] = 7;
//        A[4] = 9;
//        A[5] = 10;
//        int[] B = new int[]{2,6,12};

        int[] A = new int[10];
        A[0] = 3;
        A[1] = 5;
        A[2] = 8;
        int[] B = new int[]{1, 2, 6, 7, 9, 10, 12};
        mergeOrig(A, B);
        System.out.println("");
    }

    /**
     * KREVSKY SOLUTION
     */
    public static void mergeAB(int[] A, int[] B) {
        int i = A.length - B.length - 1;
        int endA = A.length - 1;
        int j = B.length - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[endA] = A[i];
                endA--;
                i--;
            } else {
                A[endA] = B[j];
                endA--;
                j--;
            }
        }
        //if (i >= 0) => j < 0 => do nothing, all element of B are already merged to A
        if (j >= 0) {
            //copy the rest elements of B to the beginning of A
            for (int jj = j; jj >= 0; jj--) {
                A[endA] = B[jj];
                endA--;
            }
        }
    }

    /**
     * ORIGINAL SOLUTION
     */
    public static void mergeOrig(int[] a, int[] b) {
        int lastA = a.length - b.length;
        int lastB = b.length;
        int indexA = lastA - 1; /* Index of last element in array a */
        int indexB = lastB - 1; /* Index of last element in array b */
        int indexMerged = lastB + lastA - 1; /* end of merged array */

        /* Merge a and b, starting from the last element in each */
        while (indexB >= 0) {
            /* end of a is > than end of b */
            if (indexA >= 0 && a[indexA] > b[indexB]) {
                a[indexMerged] = a[indexA]; // copy element
                indexA--;
            } else {
                a[indexMerged] = b[indexB]; // copy element
                indexB--;
            }
            indexMerged--; // move indices
        }
    }
}
