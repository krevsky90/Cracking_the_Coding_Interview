package solving_techniques.p1_SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring (hard)
 * https://leetcode.com/problems/minimum-window-substring
 * <p>
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring
 * of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * <p>
 * Example 2:
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * <p>
 * Example 3:
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * <p>
 * Constraints:
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10^5
 * s and t consist of uppercase and lowercase English letters.
 */
public class MinimumWindowSubstring {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 23 mins
     * idea #1: use array of letter's codes
     * idea #2: use sliding window + check method, that check there is no positive elements
     * (i.e. current substring of s contains all letters from t)
     * <p>
     * time ~ O(t.length()) + O(s.length() * ('z' - 'A' + 1))
     * space ~ O('z' - 'A' + 1)
     * <p>
     * 3 attempts:
     * - did not read that we also can use UPPER case letters => we have to change 'arr':
     * according to https://www.w3resource.com/python-exercises/python-basic-exercise-86.php,
     * minimum decimal value (for 'A') = 65, maximum value (for 'z') = 122
     * so we should write " - 'A'"
     * - did not add +1 to resEnd (since end position is excluded by substring method)
     * <p>
     * BEATS = 70%
     */
    public String minWindow(String s, String t) {
        int[] arr = new int['z' - 'A' + 1];
        //O(t)
        for (char c : t.toCharArray()) {
            arr[c - 'A']++;
        }

        int resStart = -1;
        int resEnd = 1000000;    // aka MAX_VALUE
        int start = 0;
        //O(s)
        for (int end = 0; end < s.length(); end++) {
            arr[s.charAt(end) - 'A']--;

            while (check(arr)) {
                if (resEnd - resStart > end - start) {
                    resStart = start;
                    resEnd = end;
                }
                arr[s.charAt(start) - 'A']++;
                start++;
            }
        }

        return resStart == -1 ? "" : s.substring(resStart, resEnd + 1);
    }

    private boolean check(int[] arr) {
        for (int a : arr) {
            if (a > 0) return false;
        }
        return true;
    }

    /**
     * SOLUTION #2: time ~ O(s.length() + t.length())
     * info:
     * https://leetcode.com/problems/minimum-window-substring/solutions/4733752/beats-101-of-all-users/
     * <p>
     * optimization: store data from t into HashMap (instead of array) + store counter of symbols from t that we cover of uncover.
     * so we can check if counter == t.length() => it is O(1) instead of my check(..) method that has time ~ O('z' - 'A' + 1)
     */
    public String minWindowOptimization(String s, String t) {
        // Store t freq in map
        // if curr char preset in map and > 0 pick and reduce freq map and increase count
        // if count == t , compare windowSize, if start present in map, map freq inc
        // shift left window till count == t

        int n = s.length();

        if (t.length() > n) return "";

        Map<Character, Integer> map = new HashMap<>();

        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int minWindowSize = Integer.MAX_VALUE;
        int windowStart = 0;
        int count = 0;
        int minLeft = 0;

        for (int windowEnd = 0; windowEnd < n; windowEnd++) {
            if (map.containsKey(s.charAt(windowEnd))) {
                if (map.get(s.charAt(windowEnd)) > 0) {
                    count++;
                }
                map.put(s.charAt(windowEnd), map.get(s.charAt(windowEnd)) - 1);
            }

            while (count == t.length()) {
                int currWindow = windowEnd - windowStart + 1;
                if (currWindow < minWindowSize) {
                    minWindowSize = currWindow;
                    minLeft = windowStart;
                }

                if (map.containsKey(s.charAt(windowStart))) {
                    map.put(s.charAt(windowStart), map.get(s.charAt(windowStart)) + 1);

                    if (map.get(s.charAt(windowStart)) > 0) {
                        count--;
                    }
                }
                windowStart++;
            }
        }

        return minWindowSize == Integer.MAX_VALUE ? "" : s.substring(minLeft, minWindowSize + minLeft);
    }

}
