package solving_techniques.different;

/**
 * 387. First Unique Character in a String (easy)
 * https://leetcode.com/problems/first-unique-character-in-a-string
 * <p>
 * #Company: Amazon Apple Bloomberg Facebook Goldman Sachs Google Huawei caMorgan LinkedIn Microsoft Walmart Labs Yahoo Zillow Yandex
 * <p>
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 * <p>
 * Example 1:
 * Input: s = "leetcode"
 * Output: 0
 * Explanation:
 * The character 'l' at index 0 is the first character that does not occur at any other index.
 * <p>
 * Example 2:
 * Input: s = "loveleetcode"
 * Output: 2
 * <p>
 * Example 3:
 * Input: s = "aabb"
 * Output: -1
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of only lowercase English letters.
 */

public class FirstUniqueCharacterInString {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * fill array of occurrences for each letter from the word 's'
     * traverse through the letters of 's' and check if it has the only 1 occurrence. if true => return its index
     * <p>
     * time to solve ~ 5 mins
     * <p>
     * time ~ O(s.length())
     * space ~ O(26) ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 99%
     */
    public int firstUniqChar(String s) {
        int[] arr = new int[26];
        char[] sArr = s.toCharArray();
        for (char c : sArr) {
            arr[c - 'a']++;
        }

        for (int i = 0; i < sArr.length; i++) {
            if (arr[sArr[i] - 'a'] == 1) return i;
        }

        return -1;
    }
}
