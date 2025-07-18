package solving_techniques.different;

/**
 * 686. Repeated String Match (medium)
 * https://leetcode.com/problems/repeated-string-match
 *
 * #Company (18.07.2025): 0 - 3 months Meta 3 Google 2 6 months ago Bloomberg 4 Microsoft 3 Amazon 3
 * <p>
 * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it.
 * If it is impossible for b to be a substring of a after repeating it, return -1.
 * Notice: string "abc" repeated 0 times is "", repeated 1 time is "abc" and repeated 2 times is "abcabc".
 * <p>
 * Example 1:
 * Input: a = "abcd", b = "cdabcdab"
 * Output: 3
 * Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
 * <p>
 * Example 2:
 * Input: a = "a", b = "aa"
 * Output: 2
 * <p>
 * Constraints:
 * 1 <= a.length, b.length <= 10^4
 * a and b consist of lowercase English letters.
 */
public class RepeatedStringMatch {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 16 mins
     * <p>
     * time ~ O(b.length * (a.length + b.length)), since we form a*counter string,
     * and then check of each position if b is substring that starts with this position
     * <p>
     * 2 attempts:
     * - a + a is incorrect, we need to repeat the initial a string
     * <p>
     * BEATS ~ 74%
     */
    public int repeatedStringMatch(String a, String b) {
//        if (a.length() >= b.length()) {
//            return helper(a, b, 1);
//        } else {
            int counter = (int) Math.ceil(1.0 * b.length() / a.length());
            return helper(a, b, counter);
//        }
    }

    private int helper(String a, String b, int counter) {
        if (a.repeat(counter).contains(b)) return counter;

        counter++;
        if (a.repeat(counter).contains(b)) {
            return counter;
        } else {
            return -1;
        }
    }

    /**
     * Official solution (idea is the same)
     */
    public int repeatedStringMatchOfficial(String A, String B) {
        int q = 1;
        StringBuilder S = new StringBuilder(A);
        for (; S.length() < B.length(); q++) S.append(A);
        if (S.indexOf(B) >= 0) return q;
        if (S.append(A).indexOf(B) >= 0) return q + 1;
        return -1;
    }

    /**
     * Official solution #2: Rabin-Karp (Rolling Hash) [Accepted]
     */
}
