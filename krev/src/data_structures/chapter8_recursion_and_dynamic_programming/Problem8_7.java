package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.HashSet;
import java.util.Set;

/**
 * p.147
 * 8.7 Permutations without Dups:
 * Write a method to compute all permutations of a string of unique characters.
 * Hints: #150, #185, #200, #267, #278, #309, #335, #356
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_7 {
    public static void main(String[] args) {
        String originalString = "abcd";
        Set<String> allPermutations = computeAllPermutations(originalString);
        for (String s : allPermutations) {
            System.out.println(s);
        }
    }

    /**
     * KREVSKY SOLUTION = ORIGINAL SOLUTION #1
     */
    public static Set<String> computeAllPermutations(String s) {
        Set<String> result = new HashSet<>();
        if (s == null || s.isEmpty()) {
            //do nothing
        } else if (s.length() == 1) {
            result.add(s);
        } else {
            char first = s.charAt(0);
            Set<String> tempSet = computeAllPermutations(s.substring(1));
            result = permutations(first, tempSet);
        }
        return result;
    }

    protected static Set<String> permutations(char c, Set<String> tempSet) {
        Set<String> result = new HashSet<>();
        for (String s : tempSet) {
            for(int i = 0; i <= s.length(); i++) {
                String addedValue = s.substring(0,i) + c + s.substring(i);
                result.add(addedValue);
            }
        }
        return result;
    }

}
