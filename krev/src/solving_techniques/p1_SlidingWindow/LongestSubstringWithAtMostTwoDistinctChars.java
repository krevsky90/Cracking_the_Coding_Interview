package solving_techniques.p1_SlidingWindow;

/**
 * 159. Longest Substring with At Most Two Distinct Characters (medium) (blocked)
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters
 * OR
 * https://leetcode.ca/all/159.html
 *
 * #Company: Adobe Amazon Facebook Google Microsoft Yandex
 *
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 *
 * Example 2:
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 *
 */
public class LongestSubstringWithAtMostTwoDistinctChars {
    public static void main(String[] args) {
        String s1 = "eceba";
        System.out.println(countLongestSubstringWithAtMostTwoDistinctChars(s1));    //expected 3

        String s2 = "ccaabbb";
        System.out.println(countLongestSubstringWithAtMostTwoDistinctChars(s2));    //expected 5
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 12 mins
     * idea: sliding window + arrar 'cache' with fixed length (or we can use HashMap)
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     */
    public static int countLongestSubstringWithAtMostTwoDistinctChars(String s) {
        int start = 0;
        int result = 0;
        int[] cache = new int[26];
        char[] sArr = s.toCharArray();

        for (int i = 0; i < sArr.length; i++) {
            char c = sArr[i];
            cache[c - 'a']++;

            while (!isValid(cache)) {
                cache[sArr[start] - 'a']--;
                start++;
            }

            result = Math.max(result, i - start + 1);
        }
        return result;
    }

    private static boolean isValid(int[] cache) {
        int counter = 0;
        for (int c : cache) {
            counter += c > 0 ? 1 : 0;
        }

        return counter <= 2;
    }
}
