package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_strings;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * https://leetcode.com/problems/split-a-string-in-balanced-strings/description/
 * <p>
 * Balanced strings are those that have an equal quantity of 'L' and 'R' characters.
 * <p>
 * Given a balanced string s, split it into some number of substrings such that:
 * <p>
 * Each substring is balanced.
 * Return the maximum number of balanced strings you can obtain.
 * <p>
 * Example 1:
 * Input: s = "RLRRLLRLRL"
 * Output: 4
 * Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
 * <p>
 * Example 2:
 * Input: s = "RLRRRLLRLL"
 * Output: 2
 * Explanation: s can be split into "RL", "RRRLLRLL", each substring contains same number of 'L' and 'R'.
 * Note that s cannot be split into "RL", "RR", "RL", "LR", "LL", because the 2nd and 5th substrings are not balanced.
 * <p>
 * Example 3:
 * Input: s = "LLLLRRRR"
 * Output: 1
 * Explanation: s can be split into "LLLLRRRR".
 * <p>
 * Constraints:
 * 2 <= s.length <= 1000
 * s[i] is either 'L' or 'R'.
 * s is a balanced string.
 */
public class Problem1_5_SplitStringInBalancedStrings {
    /**
     * KREVSKY SOLUTION
     * time complexity ~ O(n)
     * time to solve - 5 mins + 3 mins validation
     * 1 attempt
     */
    public int balancedStringSplit(String s) {
        //s = "LLLLRRRR"
        //c = R
        //counter = 4
        //result = 0
        int counter = 0;
        int result = 0;
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c == 'L') {
                counter++;
            } else if (c == 'R') {
                counter--;
            }

            if (counter == 0) {
                result++;
            }
        }

        return result;
    }

}
