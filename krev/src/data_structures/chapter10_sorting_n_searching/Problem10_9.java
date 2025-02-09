package data_structures.chapter10_sorting_n_searching;

/**
 * p.163
 * 10.9 Sorted Matrix Search:
 * Given an M x N matrix in which each row and each column is sorted in ascending order, write a method to find an element.
 * Hints: #193, #211, #229, #251, #266, #279, #288, #291, #303, #317, #330
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem10_9 {
    public static int[][] arr = new int[4][5];
    static {
        arr[0] = new int[]{1,3,5,7,8};
        arr[1] = new int[]{2,3,6,10,11};
        arr[2] = new int[]{4,7,9,14,18};
        arr[3] = new int[]{6,8,19,20,22};
    }

    public static void main(String[] args) {
        int z = 22;
        search(arr, z);
    }

    /**
     * KREVSKY SOLUTION - START
     */
    public static int[] search(int[][] arr, int z) {
        if (arr == null) return null;
        int ymax = arr.length;
        if (ymax > 0) {
            int xmax = arr[0].length;

            //find potential row:
            int potentialRowIndex = searchPotentialRow(arr, xmax - 1, 0, ymax - 1, z);

            int columnIndex = searchNumber(arr, potentialRowIndex, 0, xmax - 1, z);
            if (columnIndex == -1) {
                System.out.println("Number " + z + " is NOT found in the matrix");
                return null;
            } else {
                System.out.println("Number " + z + " is found in the matrix on the position (" + potentialRowIndex + ", " + columnIndex + ")");
                return new int[]{potentialRowIndex, columnIndex};
            }
        } else {
            return new int[0];
        }
    }

    //binary search to find the row that may potentially contain the number 'z'
    public static int searchPotentialRow(int[][] arr, int xmax, int ymin, int ymax, int z) {
        if (ymax <= ymin) {
            return ymin;
        }

        int ymid = (ymin + ymax)/2;
        if (arr[ymid][xmax] < z) {
            ymin = ymid + 1;
        } else if (arr[ymid][xmax] == z) {
            System.out.println("Number " + z + " is found: (" + ymid + ", " + xmax + ")");
            return ymid;
        } else {
            ymax = ymid;
        }

        return searchPotentialRow(arr, xmax, ymin, ymax, z);
    }

    //usual binary search
    public static int searchNumber(int[][] arr, int rowIndex, int xmin, int xmax, int z) {
        if (xmax < xmin) {
            return -1;
        }

        int xmid = (xmin + xmax)/2;
        if (arr[rowIndex][xmid] < z) {
            xmin = xmid + 1;
        } else if (arr[rowIndex][xmid] == z) {
            System.out.println("Number " + z + " is found: (" + rowIndex + ", " + xmid + ")");
            return xmid;
        } else {
            xmax = xmid - 1;
        }

        return searchNumber(arr, rowIndex, xmin, xmax, z);
    }
    /**
     * KREVSKY SOLUTION - END
     */
}
