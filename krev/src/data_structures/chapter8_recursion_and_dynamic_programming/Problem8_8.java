package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * p.147
 * 8.8 Permutations with Dups:
 * Write a method to compute all permutations of a string whose characters
 * are not necessarily unique. The list of permutations should not have duplicates.
 * Hints: # 161, #190, #222, #255
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_8 {
    public static void main(String[] args) {
        String s = "aab";
        List<String> result = getAllPerms(s);
        for (String r : result) {
            System.out.println(r);
        }
    }

    /**
     * ORIGINAL SOLUTION:
     * ideas:
     * 1) P(abc) = {a + P(bc)} + {b + P(ac)} + {c + PC(ab)}
     * 2) temporary subtract 1 from map that should not be changed, and then add 1 when recursive call has been ended
     */
    public static List<String> getAllPerms(String s) {
        List<String> result = new ArrayList<>();
        Map<Character, Integer> map = convertStringToCharMap(s);
        String prefix = "";
        getAllPerms(map, prefix, s.length(), result);
        return result;
    }

    protected static Map<Character, Integer> convertStringToCharMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        return map;
    }

    public static void getAllPerms(Map<Character, Integer> map, String prefix, int remainingLength, List<String> result) {
        //base case
        if (remainingLength == 0) {
            result.add(prefix);
            return;
        }

        for (Character c : map.keySet()) {
            int count = map.get(c);
            if (count > 0) {
                //temporary subtract 1 from the map
                map.put(c, count - 1);

                getAllPerms(map, prefix + c, remainingLength - 1, result);
                //restore original map
                map.put(c, count);
            }
        }
    }
}
