package solving_techniques.p1_SlidingWindow;

/**
 * 424. Longest Repeating Character Replacement (medium)
 * https://leetcode.com/problems/longest-repeating-character-replacement
 * <p>
 * You are given a string s and an integer k.
 * You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 * <p>
 * Return the length of the longest substring containing the same letter
 * you can get after performing the above operations.
 * <p>
 * Example 1:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * <p>
 * Example 2:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 * <p>
 * Constraints:
 * 1 <= s.length <= 100000
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 */
public class LongestRepeatingCharacterReplacement {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 30 mins
     * idea:
     * 1) while ((end - start + 1) - maxAmount > k)
     * 2) recalculation of maxAmount takes O(26) since length of arr is constant
     * <p>
     * time ~ 0(26*n) ~ O(n)
     * space ~ O(26) ~ O(1)
     * <p>
     * 2 attempts:
     * -skipped idea 2
     * <p>
     * BEATS = 93%
     */
    public int characterReplacement(String s, int k) {
        int[] arr = new int[26];
        int maxAmount = 0;
        int start = 0;
        int result = -100500;

        for (int end = 0; end < s.length(); end++) {
            arr[s.charAt(end) - 'A']++;
            maxAmount = Math.max(maxAmount, arr[s.charAt(end) - 'A']);

            while ((end - start + 1) - maxAmount > k) {
                arr[s.charAt(start) - 'A']--;
                start++;
                //update maxChar ~ O(26) ~ O(1)
                //not sure 100% why we should not recalc maxAmount
//                int tempMax = 0;
//                for (int v : arr) {
//                    tempMax = Math.max(tempMax, v);
//                }
//                maxAmount = tempMax;
            }

            result = Math.max(result, end - start + 1);
        }

        return result;
    }

}
