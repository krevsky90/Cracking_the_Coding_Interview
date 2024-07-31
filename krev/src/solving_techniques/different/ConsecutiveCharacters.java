package solving_techniques.different;

/**
 * 1446. Consecutive Characters (easy)
 * https://leetcode.com/problems/consecutive-characters
 * <p>
 * #Company: Yandex
 * <p>
 * The power of the string is the maximum length of a non-empty substring that contains only one unique character.
 * Given a string s, return the power of s.
 * <p>
 * Example 1:
 * Input: s = "leetcode"
 * Output: 2
 * Explanation: The substring "ee" is of length 2 with the character 'e' only.
 * <p>
 * Example 2:
 * Input: s = "abbcccddddeeeeedcba"
 * Output: 5
 * Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
 * <p>
 * Constraints:
 * 1 <= s.length <= 500
 * s consists of only lowercase English letters
 */
public class ConsecutiveCharacters {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 5 mins
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     * BEATS ~ 100%
     */
    public int maxPower(String s) {
        int res = -1;
        int tempCounter = 1;
        char prevChar = '.';
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char tempChar = chars[i];
            if (prevChar != tempChar) {
                tempCounter = 1;
                prevChar = tempChar;
            } else {
                tempCounter++;
            }
            res = Math.max(res, tempCounter);
        }
        return res;
    }

    /**
     * beauty solution
     * info: https://leetcode.com/problems/consecutive-characters/solutions/5129036/java-easy-100-solution/
     */
    public int maxPower2(final String s) {
        int max = 1, count = 1;

        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                count++;
            } else {
                count = 1;
            }
            max = Math.max(max, count);
        }

        return max;
    }
}
