package solving_techniques.p1_SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 567. Permutation in String
 * https://leetcode.com/problems/permutation-in-string/description/
 * <p>
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * <p>
 * In other words, return true if one of s1's permutations is the substring of s2.
 * <p>
 * Example 1:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <p>
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 * <p>
 * s1 and s2 consist of lowercase English letters.
 *
 */

/**
 * NOTE: this problem is THE SAME AS 438. Find All Anagrams in a String (https://leetcode.com/problems/find-all-anagrams-in-a-string/)
 */
public class PermutationInString {
    private static final PermutationInString obj = new PermutationInString();

    public static void main(String[] args) {
        String s11 = "ab", s12 = "eidbaooo";
        String s21 = "ab", s22 = "eidboaoo";
        String s31 = "abc", s32 = "dababbac";

        System.out.println(obj.checkInclusionKREV(s11, s12));
        System.out.println(obj.checkInclusionKREV(s21, s22));
        System.out.println(obj.checkInclusionKREV(s31, s32));

    }

    /**
     * https://leetcode.com/problems/permutation-in-string/solutions/3142547/simple-to-understand-java-o-n-time/
     * idea: Compare each window with the matcher string and check if it contains all its elements
     */
    public boolean checkInclusionOptimized(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] data1 = new int[26];
        for (char c : s1.toCharArray()) {
            data1[c - 'a']++;    //increment i-th element where i = c - 'a'
        }

        int[] data2 = new int[26];
        //fill data2 based on first s1.length()-th symbols of s2 string
        for (int i2 = 0; i2 < s1.length(); i2++) {
            data2[s2.charAt(i2) - 'a']++;
        }

        int width = s1.length();
        for (int i = 0; i < s2.length() - width; i++) {
            if (compareArrs(data1, data2)) return true;

            //move sliding window for data2:
            data2[s2.charAt(i + width) - 'a']++;    //expand right bound to 1 symbol
            data2[s2.charAt(i) - 'a']--;            //shrink left bound to 1 symbol
        }

        return compareArrs(data1, data2);   //not false since it will be incorrect for case: s1 = ab, s2 = cab
    }

    private boolean compareArrs(int[] data1, int[] data2) {
        for (int i = 0; i < 26; i++) {
            if (data1[i] != data2[i]) return false;
        }
        return true;
    }

    /**
     * KREV solution
     * time to solve ~ 45 mins
     * 2 attempts
     * MY PROBLEMS
     * 1) I didn't see that it is fixed length window => I tried to solve using dynamic window
     * 2) I didn't read condition that 's1 and s2 consist of lowercase English letters'!
     */
    public boolean checkInclusionKREV(String s1, String s2) {
        //convert s1 to map
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int sumValue = s1.length();   //sum of all values in the map. once it = 0, it means that all letters are used => return true

        //sliding window
        //s1 = abc
        //s2 = dababbac
        //map = a0, b0, c0
        //sumValue = 0
        //start = 5
        //end = 7
        int start = 0;
        for (int end = 0; end < s2.length(); end++) {
            char tempC = s2.charAt(end);
            if (!map.containsKey(tempC)) {
                //reset HashMap - todo: how to optimize?
                map.clear();
                for (char c : s1.toCharArray()) {
                    map.put(c, map.getOrDefault(c, 0) + 1);
                }

                //reset sumValue
                sumValue = s1.length();
                //and move start to 'end + 1'
                start = end + 1;
            } else {
                while (map.get(tempC) == 0) {
                    map.put(s2.charAt(start), map.get(s2.charAt(start)) + 1);
                    start++;
                    sumValue++;
                }

                map.put(tempC, map.get(tempC) - 1);
                sumValue--;

                if (sumValue == 0) return true;
            }
        }

        return false;
    }
}
