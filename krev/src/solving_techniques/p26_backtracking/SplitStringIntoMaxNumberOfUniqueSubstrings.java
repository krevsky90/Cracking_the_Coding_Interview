package solving_techniques.p26_backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63d50832298d8670e11747b4
 * OR
 * 1593. Split a String Into the Max Number of Unique Substrings
 * https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings
 *
 * Given a string s, return the maximum number of unique substrings that the given string can be split into.
 *
 * You can split string s into any list of non-empty substrings,
 * where the concatenation of the substrings forms the original string.
 * However, you must split the substrings such that all of them are unique.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc'].
 * Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
 *
 * Example 2:
 * Input: s = "aba"
 * Output: 2
 * Explanation: One way to split maximally is ['a', 'ba'].
 *
 * Example 3:
 * Input: s = "aa"
 * Output: 1
 * Explanation: It is impossible to split the string any further.
 *
 * Constraints:
 * 1 <= s.length <= 16
 * s contains only lower case English letters.
 */
public class SplitStringIntoMaxNumberOfUniqueSubstrings {
    /**
     * NOT SOLVED by myself (but could, I think)
     * idea: as usual track:
     * 1) start index
     * 2) temp collection (HashSet in this case) of unique substrings
     * original https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/solutions/4101322/simple-bactracking-an-old-solution-to-new-problem/
     *
     * time to solve ~ 30 mins
     *
     * time ~ O(2^n)
     *
     * 1 attempt
     */
    public int maxUniqueSplit(String s) {
        Set<String> tempSet = new HashSet<>();
        return maxUniqueSplitHelper(s, tempSet, 0);

    }

    private int maxUniqueSplitHelper(String s, Set<String> tempSet, int start) {
        if (start == s.length()) {
            return tempSet.size();
        }

        int result = 0;
        for (int i = start; i < s.length(); i++) {
            String sub = s.substring(start, i + 1);
            if (!tempSet.contains(sub)) {
                tempSet.add(sub);
                result = Math.max(result, maxUniqueSplitHelper(s, tempSet, i + 1));
                tempSet.remove(sub);
            }
        }

        return result;
    }
}
