package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://www.geeksforgeeks.org/counting-frequencies-of-array-elements/
 */
public class Problem1_3_CountTheFrequencyOfElementInArray {
    public static void main(String[] args) {
        int arr[] = {10, 20, 20, 10, 10, 20, 5, 20};
        countFreqKREV(arr);
    }

    /**
     * Time Complexity : O(n)
     * Auxiliary Space : O(n)
     * time to solve ~ 5 mins
     */
    public static void countFreqKREV(int arr[]) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
}
