package solving_techniques.p2_TwoPointers;

/**
 * https://www.geeksforgeeks.org/segregate-0s-and-1s-in-an-array-by-traversing-array-once/
 *
 * You are given an array of 0s and 1s in random order.
 * Segregate 0s on left side and 1s on right side of the array [Basically you have to sort the array]. Traverse array only once.
 *
 * Input array   =  [0, 1, 0, 1, 0, 0, 1, 1, 1, 0]
 * Output array =  [0, 0, 0, 0, 0, 1, 1, 1, 1, 1]
 */
public class Segregate0and1s {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1, 0, 0, 1, 1, 1, 0};
        segregate0and1_inplace(arr);
        System.out.println("");
    }

    public static void segregate0and1_inplace(int arr[]) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            while (arr[left] == 0 && left < right) {
                left++;
            }

            while (arr[right] == 1 && left < right) {
                right--;
            }

            //swap
            if (left < right) {
                arr[left] = 0;
                arr[right] = 1;
                left++;
                right--;
            }
        }
    }

    //additional solution (just for fun)
    public static void segregate0and1_recreateApproach(int arr[]) {
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                count0++;
            } else {
                count1++;
            }
        }

        int k = 0;
        while (count0 > 0) {
            arr[k] = 0;
            k++;
            count0--;
        }

        while (count1 > 0) {
            arr[k] = 1;
            k++;
            count1--;
        }
    }
}