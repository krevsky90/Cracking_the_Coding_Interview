package data_structures.chapter8_recursion_and_dynamic_programming.Recursion_in_Programming_Full_Course;

public class MergeSort {
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if (end <= start) return;

        int mid = (end + start) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int[] tempArr = new int[end - start + 1];
        int k = 0;  //index for tempArr
        while (i < mid + 1 && j <= end) {
            if (arr[i] <= arr[j]) {
                tempArr[k] = arr[i];
                i++;
                k++;
            } else {
                tempArr[k] = arr[j];
                j++;
                k++;
            }
        }

        //copy the rest part of left sub-array if it is not empty
        while (i < mid + 1) {
            tempArr[k] = arr[i];
            i++;
            k++;
        }

        //copy the rest part of right sub-array if it is not empty
        while (j < end) {
            tempArr[k] = arr[j];
            j++;
            k++;
        }

        //copy values from tempArr to arr
        for (int c = start; c <= end; c++) {
            arr[c] = tempArr[c - start];
        }
    }
}
