package data_structures.chapter10_sorting_n_searching;

import java.util.*;

/**
 * p.162
 * 10.2 Group Anagrams:
 * Write a method to sort an array of strings so that all the anagrams are next to each other.
 * Hints: #177, #182, #263, #342
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem10_2 {
    public static void main(String[] args) {
        String[] arr = new String[]{"bac", "ter", "cab", "yyy", "opt", "ert"};
        groupAnagrams(arr);
        System.out.println("");
    }

    /**
     * ORIGINAL SOLUTION:
     * the idea is bucket sorting
     */
    public static void groupAnagrams(String arr[]) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : arr) {
            String key = sortString(s);
            if (map.get(key) == null) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        //convert map values to array
        int i = 0;
        for (String key : map.keySet()) {
            List<String> temp = map.get(key);
            for (String t : temp) {
                arr[i] = t;
                i++;
            }
        }
    }

    /**
     * time ~ O(K*log(k)), where K - is length of string s
     */
    private static String sortString(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
