package data_structures.chapter8_recursion_and_dynamic_programming.extra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=IPWmrjE1_MU&list=PLNmW52ef0uwsjnM06LweaYEZr-wjPKBnj&index=13
 * Byte by Byte: Permutations
 * <p>
 * Write a function that returns all permutations of a given list
 * <p>
 * Example:
 * permutations({1, 2, 3})
 * {1, 2, 3}
 * {1, 3, 2}
 * {2, 1, 3}
 * {2, 3, 1}
 * {3, 1, 2}
 * {3, 2, 1}
 */
public class Permutations {
    public static void main(String[] args) {
        List<Integer> testList = Arrays.asList(1, 2, 3);
        List<List<Integer>> result = permutations(testList);
        System.out.println("");
        String s = "abc";
        List<String> strResult = permutations(s);
        System.out.println("");
        List<String> strResultOrig = permutationsORIG(s);
        System.out.println("");

    }

    /**
     * KREVSKY SOLUTION - start (like Problem8_7)
     */
    public static List<List<Integer>> permutations(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();
        if (list == null || list.size() == 0) {
            //do nothing;
        } else if (list.size() == 1) {
            result.add(list);
        } else {
            Integer first = list.get(0);
            List<List<Integer>> tempResult = permutations(list.subList(1, list.size()));
            result = permutations(first, tempResult);
        }
        return result;
    }

    protected static List<List<Integer>> permutations(int first, List<List<Integer>> list) {
        List<List<Integer>> result = new ArrayList<>();

        for (List tempList : list) {
            for (int i = 0; i <= tempList.size(); i++) {
                List<Integer> tempResult = new ArrayList<>();
                tempResult.addAll(tempList.subList(0, i));
                tempResult.add(first);
                tempResult.addAll(tempList.subList(i, tempList.size()));
                result.add(tempResult);
            }
        }

        return result;
    }
    /**
     * KREVSKY SOLUTION - end
     */

    /**
     * KREVSKY SOLUTION - for strings - START
     *
     * permutations("abc")
     *      result += "a" + permutations("bc")
     *          result += "b" + permutations("c")
     *              "c"
     *          result += "c" + permutations("b")
     *              "b"
     *      result += "b" + permutations("ac")
     *          ...
     *      result += "c" + permutations("ab")
     *          ...
     *
     *  or
     *
     *  ("abc")
     *      "a" + ("bc")
     *          "a" + "b" + ("c")
     *              "a" + "b" + "c" = "abc"*
     *          "a" + "c" + ("b")
     *              "a" + "c" + "b" = "acb"
     *      "b" + ("ac")
     *          "b" + "a" + ("c")
     *              "b" + "a" + "c" = "bac"
     *          "b" + "c" + ("a")
     *      *       "b" + "c" + "a" = "bca"
     *      "c" + ("ab")
     *          ...
     */
    public static List<String> permutations(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            //do nothing
        } else if (s.length() == 1) {
            result.add(s);
        } else {
            for (int i = 0; i < s.length(); i++) {
                List<String> subResult = permutations(s.substring(0, i) + s.substring(i + 1));
                for (String sub : subResult) {
                    result.add(s.charAt(i) + sub);
                }
            }
        }

        return result;
    }
    /**
     * KREVSKY SOLUTION - for strings - END
     */

    /**
     * ORIGINAL SOLUTION - for strings - START
     */
    public static List<String> permutationsORIG(String s) {
        List<String> result = new ArrayList<>();
        permutations("", s, result);
        return result;
    }

    /**
     * p("", "abc")
     * 	    p("a", "bc")
     * 		    p("ab", "c")
     * 			    p("abc", "")
     * 		    p("ac", "b")
     * 			    p("acb", "")
     * 	    p("b", "ac")
     * 		    p("ba", "c")
     * 			    p("bac", "")
     * 		    p("bc", "a")
     * 			    p("bca", "")
     * 	    p("c", "ab")
     * 		...
     *
     */
    private static void permutations(String prefix, String suffix, List<String> results) {
        if (suffix.length() == 0) {
            results.add(prefix);
        } else {
            for (int i = 0; i < suffix.length(); i++) {
                permutations(prefix + suffix.charAt(i), suffix.substring(0,i) + suffix.substring(i+1), results);
            }
        }
    }
    /**
     * ORIGINAL SOLUTION - for strings - END
     */

    /**
     * ORIGINAL SOLUTION - for int[] - START
     * the idea is to change array itself and rollback the changes once the permutation is stored
     */
    public static List<int[]> permutationsORIG(int[] a) {
        List<int[]> result = new ArrayList<>();
        permutations(a, 0, result);
        return result;
    }

    private static void permutations(int[] arr, int start, List<int[]> results) {
        if (start >= arr.length) {
            results.add(arr);
        } else {
            for (int i = start; i < arr.length; i++) {
                swap(arr, start, i);
                permutations(arr, start + 1, results);
                swap(arr, i, start);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * ORIGINAL SOLUTION - for int[] - END
     */
}
