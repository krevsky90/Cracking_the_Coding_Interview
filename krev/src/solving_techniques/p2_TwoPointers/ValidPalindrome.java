package solving_techniques.p2_TwoPointers;

/**
 * 125. Valid Palindrome (easy)
 * https://leetcode.com/problems/valid-palindrome
 *
 * #Company: Yandex
 *
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 *      and removing all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 * Example 3:
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 *
 * Constraints:
 * 1 <= s.length <= 2 * 10^5
 * s consists only of printable ASCII characters.
 *
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        boolean isPalindrome = isPalindrome(s);

        System.out.println(isPalindrome);
    }

    /**
     * info:
     * https://leetcode.com/problems/valid-palindrome/solutions/3165353/beats-96-9-well-explained-code-in-java/
     */
    public static boolean isPalindrome(String s) {
        if (s == null) return false;

        char[] sArr = s.toCharArray();
        int start = 0;
        int end = sArr.length - 1;

        while (start < end) {
            if (!isAlphanumeric(sArr[start])) {
                start++;
            } else if (!isAlphanumeric(sArr[end])) {
                end--;
            } else {
//                if (!("" + sArr[start]).equalsIgnoreCase(("" + sArr[end]))) {
                if (Character.toLowerCase(sArr[start]) != Character.toLowerCase(sArr[end])) {
                    return false;
                }

                start++;
                end--;
            }
        }

        return true;
    }

    private static boolean isAlphanumeric(char c) {
        return (c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }
}
