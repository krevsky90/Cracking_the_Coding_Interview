package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_arrays;

public class Problem1_2_RemoveDuplicatesFromArray {
    public static void main(String[] args) {
        String[] arr = {"a", "a", "b", "c", "d", "d", "e"};
        removeDuplicate(arr);
        System.out.println("");
    }

    public static void removeDuplicate(String[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        //without extra space we need to insert elements in-place using 2 indexes approach
        int insertIndex = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[insertIndex] = arr[i];
                insertIndex++;
            }
        }
    }
}