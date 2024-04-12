package solving_techniques.p19_LongestCommonSubstring;

import java.util.Arrays;

/**
 * 712. Minimum ASCII Delete Sum for Two Strings
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 *
 * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.
 *
 * Example 1:
 * Input: s1 = "sea", s2 = "eat"
 * Output: 231
 * Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
 * Deleting "t" from "eat" adds 116 to the sum.
 * At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
 *
 * Example 2:
 * Input: s1 = "delete", s2 = "leet"
 * Output: 403
 * Explanation: Deleting "dee" from "delete" to turn the string into "let",
 * adds 100[d] + 101[e] + 101[e] to the sum.
 * Deleting "e" from "leet" adds 101[e] to the sum.
 * At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
 * If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 *
 * Constraints:
 * 1 <= s1.length, s2.length <= 1000
 * s1 and s2 consist of lowercase English letters.
 */
public class MinimumAsciiDeleteSumForTwoStrings {
    /**
     * ATTENTION!
     * since we consider lowercase letters (i.e. decimal codes from 97 to 97+26=123),
     * then there is no chance that the sum of ASCII codes of any 2+ letters will be less then ASCII code of any other letter =>
     * => the problem is to count ASCII codes of minimal amount of deleted letters
     */
    public static void main(String[] args) {
        MinimumAsciiDeleteSumForTwoStrings obj = new MinimumAsciiDeleteSumForTwoStrings();
        String s1 = "sea";
        String s2 = "eat";
        for (char c : s1.toCharArray()) {
            System.out.print(0 + c + " ");
        }
        System.out.println("----");
        for (char c : s2.toCharArray()) {
            System.out.print(0 + c + " ");
        }

        //[231, 332, 429, 313]
        //[116, 217, 314, 198]
        //[-1,  116, 213,  97]
        //[-1,  -1,  116,  -1]
        System.out.println("----");
        System.out.println(obj.minimumDeleteSum(s1, s2));
        System.out.println(obj.minimumDeleteSumIterative(s1, s2));

    }
    /**
     * NOT SOLVED by myself
     * idea: https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/submissions/1202726087/
     * Basic recursion + memoization problem.
     * <p>
     * Start from i=0, j=0
     * Each time compare s1.charAt(i) with s2.charAt(j).
     * If they are equal, move to recurse(i+1, j+1)
     * If they are not, we have two options:
     * delete i so, ascii(s1.charAt(i)) + recurse(i+1, j)
     * delete j so, ascii(s2.charAt(j)) + recurse(i, j+1)
     * return minimum of those two.
     * It will give TLE. So, make sure to memoize it. Memo[i][j] will be helpful.
     * <p>
     * time to implement ~ 15 mins
     * <p>
     * 2 attempts:
     * - incorrect usage of memo
     *
     * BEATS = 11%
     */
    public int minimumDeleteSum(String s1, String s2) {
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        for (int[] a : memo) {
            Arrays.fill(a, -1);
        }

        int tab = 0;
        int res = recursive(s1, s2, 0, 0, memo, tab);
        return res;
    }

    private String getTabS(int tab) {
        String s = "";
        for (int i = 0; i < tab; i++) {
            s += "\t";
        }
        return s;
    }

    public int recursive(String s1, String s2, int i, int j, int[][] memo, int tab) {
        System.out.println(getTabS(tab) + "recursive(" + i + ", " + j + ")");
        if (i == s1.length()) {
            //count sum of the rest letters of s2
            int sum = 0;
            for (int k = j; k < s2.length(); k++) {
                sum += s2.charAt(k);
            }
            return memo[i][j] = sum;
        }

        if (j == s2.length()) {
            //count sum of the rest letters of s1
            int sum = 0;
            for (int k = i; k < s1.length(); k++) {
                sum += s1.charAt(k);
            }
            return memo[i][j] = sum;
        }

        if (memo[i][j] != -1) return memo[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            //forgot "memo[i][j] = "
            //that's why memo table was not filled fully (but it did not influence on the result)
            return memo[i][j] = recursive(s1, s2, i + 1, j + 1, memo, tab + 1);
        } else {
            int way1 = s1.charAt(i) + recursive(s1, s2, i + 1, j, memo, tab + 1);
            int way2 = s2.charAt(j) + recursive(s1, s2, i, j + 1, memo, tab + 1);
            return memo[i][j] = Math.min(way1, way2);
        }
    }

    /**
     * KREVSKY SOLUTION #2: bottom-up (i.e. iterative)
     * time to solve ~ 15 mins
     *
     * 2 attempts:
     * - incorrect size of memo (should be len + 1, but not len)
     *
     * BEATS = 38-49%
     */
    public int minimumDeleteSumIterative(String s1, String s2) {
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];   //note: [0][0] = 0 by default

        //fill base cases:
        //if we take just empty string as substring of s2 (i.e. j = 0) => delta = sum of ASCII codes of the letters from s1 string
        for (int i = 1; i < s1.length() + 1; i++) {
            memo[i][0] = s1.charAt(i - 1) + memo[i-1][0];
        }
        //if we take just empty string as substring of s1 (i.e. i = 0) => delta = sum of ASCII codes of the letters from s2 string
        for (int j = 1; j < s2.length() + 1; j++) {
            memo[0][j] = s2.charAt(j - 1) + memo[0][j - 1];
        }

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    memo[i][j] = memo[i-1][j-1];
                } else {
                    int way1 = s1.charAt(i-1) + memo[i-1][j];
                    int way2 = s2.charAt(j-1) + memo[i][j-1];
                    memo[i][j] = Math.min(way1, way2);
                }
            }
        }

        return memo[s1.length()][s2.length()];
    }


}
