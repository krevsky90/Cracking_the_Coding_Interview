package solving_techniques.p18_PalindromicSubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 132. Palindrome Partitioning II (hard)
 * https://leetcode.com/problems/palindrome-partitioning-ii
 *
 * Given a string S, partition S such that every
 * substring  of the partition is a palindrome of S
 * Return the minimum cuts needed for a palindrome partitioning of S.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 * Example 2:
 * Input: s = "a"
 * Output: 0
 *
 * Example 3: *
 * Input: s = "ab"
 * Output: 1
 *
 * Constraints:
 * 1 <= s.length <= 2000
 * s consists of lowercase English letters only.
 */
public class PalindromePartitioning2 {
    public static void main(String[] args) {
        String s1 = "aab";
        System.out.println(new PalindromePartitioning2().minCut21(s1));
    }
    /**
     * NOT SOLVED by myself:
     * unable to form DP formula
     */

    /**
     * KREVSKY SOLUTION #1:
     * gives Time Limit Exception
     * idea: https://leetcode.com/problems/palindrome-partitioning/ + min counter
     */
    public int minCut1(String s) {
        List<String> tempResult = new ArrayList<>();
        int[] res = {Integer.MAX_VALUE};
        generateAllCombinations(s, 0, tempResult, res);

        return res[0] - 1;
    }

    public void generateAllCombinations(String s, int start, List<String> tempResult, int[] res) {
        if (start == s.length()) {
            res[0] = Math.min(res[0], tempResult.size());
        }

        for (int i = start; i < s.length(); i++) {
            String subS = s.substring(start, i + 1);
            if (isPalindrome(subS)) {
                tempResult.add(subS);
                generateAllCombinations(s, i + 1, tempResult, res);
                tempResult.remove(tempResult.size() - 1);
            }
        }

    }

    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    /**
     * SOLUTION #2.1:
     * idea: https://www.youtube.com/watch?v=_H8V5hJUGd0
     * try to do forward partition:
     * i.e. to cut substring from the beginning of the string, if substring is palindrome.
     * and then solve subproblem for the rest part of the string
     * +NOTE: each separate letter is palindrome!
     *
     * so the problem is like top-down approach for the problem like "find the shortest way" etc
     *
     * time ~ O(N) * N ~ O(N^2), where N = s.length(). O(N) - N states (start = 0 ... N) and N loops with j = start and ends with N
     * space ~ O(N) for dp arr + O(N) for recursive (auxiliary) stack ~ O(N)
     *
     * BEATS 26%
     */
    public int minCut21(String s) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        return minCutHelper21(s, 0, dp) - 1;
    }

    /**
     *    012345
     *    ababc
     * dp MMMMMM
     *
     * minCutHelper21(ababc, 0)
     * 	j = 0: isPalindrome(a) = true
     * 			dp[0] = 1 + minCutHelper21(ababc, 1) = 1 + 2 = 3
     * 							j = 1: isPalindrome(b) = true
     * 									dp[1] = 1 + minCutHelper21(ababc, 2) = 1 + 3 = 4
     * 												j = 2: isPalindrome(a) = true
     * 														dp[2] = 1 + minCutHelper21(ababc, 3) = 1 + 2 = 3
     * 																	j = 3: isPalindrome(b) = true
     * 																			dp[3] = 1 + minCutHelper21(ababc, 4) = 1 + 1 = 2
     * 																						j = 4: isPalindrome(c) = true
     * 																								dp[4] = 1 + minCutHelper21(ababc, 5) = 1 + 0  = 1 !!! base case
     * 																						return dp[4] = 1
     * 																	j = 4: isPalindrome(bc) = false
     * 																	return dp[3] = 2
     * 												j = 3: isPalindrome(ab) = false
     * 												j = 4: isPalindrome(abc) = false
     * 												return dp[2] = 3
     * 							j = 2: isPalindrome(ba) = false
     * 							j = 3: isPalindrome(bab) = true
     * 									dp[1] = 1 + minCutHelper21(ababc, 4) = 2
     * 												//NOTE: dp[4] already filled => return 1
     * 							j = 4: isPalindrome(babc) = false
     * 							return dp[1] = 2
     * 	j = 1: isPalindrome(ab) = false
     * 	j = 2: isPalindrome(aba) = true
     * 			dp[0] = 1 + minCutHelper21(ababc, 3) = 1 + 2 = 3
     * 						//NOTE: dp[3] already filled => return 2
     *
     * 	j = 3: isPalindrome(abab) = false
     * 	j = 4: isPalindrome(ababc) = false
     * 	return dp[0] = 3
     */
    private int minCutHelper21(String s, int start, int[] dp) {
        if (start == s.length()) return 0;

        if (dp[start] != Integer.MAX_VALUE) {
            return dp[start];
        }

        for (int j = start; j < s.length(); j++) {
            //try to cut if it is palindrome
            if (isPalindrome(s, start, j)) {
                dp[start] = Math.min(dp[start], 1 + minCutHelper21(s, j + 1, dp));
            }
        }
        return dp[start];
    }

    /**
     * SOLUTION #2.2:
     * the same as SOLUTION #2, BUT iterative approach (tabulation)
     *
     * BEATS 44%
     */
    public int minCut22(String s) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        //base case:
        dp[s.length()] = 0;

        for (int start = s.length() - 1; start >= 0; start--) {
            //copy DP-formula from SOLUTION #2.1, but change recursive part to formula using dp table
            for (int j = start; j < s.length(); j++) {
                //try to cut if it is palindrome
                if (isPalindrome(s, start, j)) {
                    dp[start] = Math.min(dp[start], 1 + dp[j + 1]);
                }
            }
        }

        return dp[0] - 1;
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
