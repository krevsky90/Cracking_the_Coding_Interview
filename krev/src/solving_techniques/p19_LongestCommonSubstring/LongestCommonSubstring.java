package solving_techniques.p19_LongestCommonSubstring;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f5929a842de869cd616c5
 * OR
 * (similar, but for integers)
 * 718. Maximum Length of Repeated Subarray
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/description/
 *
 */
public class LongestCommonSubstring {
    public static void main(String[] args) {
        String s11 = "abdca";
        String s12 = "cbda";
        System.out.println(longestCommonSubstring(s11, s12)); //expect "bd"

        String s21 = "passport";
        String s22 = "ppsspt";
        System.out.println(longestCommonSubstring(s21, s22)); //expect "ssp"

        int[] nums1 = {1,2,3,2,1};
        int[] nums2 = {3,2,1,4,7};
        System.out.println(findLengthSpaceOptimized(nums1, nums2)); //expect: 3 (since the repeated subarray with maximum length is [3,2,1])
    }
    /**
     * from grokking-dynamic-programming
     * Given two strings 's1' and 's2', find the length of the longest substring which is common in both the strings.
     *
     * Example 1:
     * Input: s1 = "abdca"
     *        s2 = "cbda"
     * Output: 2
     * Explanation: The longest common substring is "bd".
     *
     * Example 2:
     * Input: s1 = "passport"
     *        s2 = "ppsspt"
     * Output: 3
     * Explanation: The longest common substring is "ssp".
     */

    /**
     * implementation the idea from https://www.youtube.com/watch?v=UZRkpGk943Q
     * (described in src/solving_techniques/p19_LongestCommonSubstring/readme.txt)
     */

    //just to see the difference
    //to find largest common subsequence
    //  *       a c b d a (j)
    //  *   | 0 0 0 0 0 0
    //  * a | 0 1 1 1 1 1
    //  * d | 0 1 1 1 2 2
    //  * b | 0 1 1 2 2 2
    //  * c | 0 1 2 2 2 2
    //  * a | 0 1 2 2 2 3
    //  *(i)

    ////to find largest common substring
    //  *       a c b d a (j)
    //  *   | 0 0 0 0 0 0
    //  * a | 0 1 0 0 0 1
    //  * d | 0 0 0 0 1 0
    //  * b | 0 0 0 1 0 0
    //  * c | 0 0 1 0 0 0
    //  * a | 0 1 0 0 0 1
    //  *(i)
    public static String longestCommonSubstring(String s1, String s2) {
        if (s1 == null || s2 == null) return null;
        if (s1.isEmpty() || s2.isEmpty()) return "";

        String out = "";
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        //since 0th row and column should be filled by 0 (it means that empty string cannot have non-empty substring),
        // just start both for-loops with 1
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                //use i-1 and j-1 to avoid IndexOutOfBoundException (since max i = s1.length(), for example)
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    //DO NOT UNDERSTAND CLEARLY!
                    // but smth like this: it means that the elements [xxx, i] will not be common substring (listen here https://youtu.be/UZRkpGk943Q?t=580)
                    dp[i][j] = 0;
                }

                //update longest common substring
                if (out.length() < dp[i][j]) {
                    //for example, get substring from s1 string:
                    //BE CAREFUL WITH INDEXES!
                    //and always draw the table like in the comment above
                    out = s1.substring(i - dp[i][j], i);
                }
            }
        }

        return out;
    }

    /**
     * https://leetcode.com/problems/maximum-length-of-repeated-subarray/description/
     * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
     *
     * Constraints:
     * 1 <= nums1.length, nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 100
     *
     * KREVSKY SOLUTION #1.1:
     * the idea is the same as for longestCommonSubstring() method
     * time to solve ~ 3 mins
     * time ~ O(nums1.length * nums2.length)
     * space ~ O(nums1.length * nums2.length)
     *
     * 1 attempt
     */
    public static int findLength(int[] nums1, int[] nums2) {
        int result = 0;

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 1; i < nums1.length + 1; i++) {
            for (int j = 1; j < nums2.length + 1; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = 0;
                }

                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }

    /**
     * KREVSKY SOLUTION @1.2:
     * We can optimize space since we need only n-1-th row to calculate n-th row
     * NOTE: fill i-th row from right to the left. Otherwise dp[j-1]-th cell (that has been just filled) will affect dp[j]
     * time to solve ~ 10 mins
     *
     * time ~ O(nums1.length * nums2.length)
     * space ~ O(nums2.length)
     */
    public static int findLengthSpaceOptimized(int[] nums1, int[] nums2) {
        int result = 0;

        int[] dp = new int[nums2.length + 1];

        for (int i = 1; i < nums1.length + 1; i++) {
            //fill i-th row from right to the left. Otherwise dp[j-1]-th cell that has been just filled will affect dp[j]
            for (int j = nums2.length; j > 0; j--) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[j] = 1 + dp[j-1];
                } else {
                    dp[j] = 0;
                }

                result = Math.max(result, dp[j]);
            }
        }

        return result;
    }
}
