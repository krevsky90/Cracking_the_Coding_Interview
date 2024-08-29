package solving_techniques.p2_TwoPointers;

/**
 * 680. Valid Palindrome II (easy)
 * https://leetcode.com/problems/valid-palindrome-ii
 *
 * #Company: Atlassian Facebook Google Microsoft Yahoo
 *
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 * Example 1:
 * Input: s = "aba"
 * Output: true
 *
 * Example 2:
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 *
 * Example 3:
 * Input: s = "abc"
 * Output: false
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 */

public class ValidPalindrome2 {
    public static void main(String[] args) {
        ValidPalindrome2 obj = new ValidPalindrome2();
        String s = "cuppucu";

        System.out.println(obj.validPalindrome(s));
        System.out.println(obj.validPalindromeWRONG(s));
    }

    /**
     * NOT SOLVED correctly by myself
     * info:
     * https://www.youtube.com/watch?v=rOvidftIU_8&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=34
     * idea:
     * if arr[i] != arr[j] then check isPalindrome(arr from i+1 to j) OR isPalindrome(arr from i to j+1)
     * ATTENTION! we need to consider BOTH ways!
     * the mistake of my solution was that I wrote else if + else if => consider only 1 of 2 ways
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 24
     */
    public boolean validPalindrome(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if (arr[i] == arr[j]) {
                i++;
                j--;
            } else {
                return isPalindrome(arr, i + 1, j) || isPalindrome(arr, i, j - 1);
            }
        }

        return true;
    }

    private boolean isPalindrome(char[] arr, int left, int right) {
        while (left < right) {
            if (arr[left] != arr[right]) return false;

            left++;
            right--;
        }
        return true;
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 8 + 4 (debug) mins
     * this is WRONG!
     * example: "cuppucu"; - this shoulr return true. since "cuppuc' is palindrome.
     * BUT my method returns false, since it executes
     * else if (arr[i + 1] == arr[j] && removed == 0)
     * => checks only "uppucu" which is NOT palindrome
     */
    public boolean validPalindromeWRONG(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        int removed = 0;
        while (j - i >= 1) {
            if (arr[i] == arr[j]) {
                i++;
                j--;
            } else if (arr[i + 1] == arr[j] && removed == 0) {
                i += 2;
                j--;
                removed++;
            } else if (arr[i] == arr[j - 1] && removed == 0) {
                i++;
                j -= 2;
                removed++;
            } else {
                return false;
            }
        }

        return true;
    }
}
