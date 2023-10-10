package neetcode.roadmap.twoPointers;

/**
 * https://leetcode.com/problems/valid-palindrome/solutions/3165353/beats-96-9-well-explained-code-in-java/
 */
public class LeetCode125_ValidPalindrome {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        boolean isPalindrome = isPalindrome(s);

        System.out.println(isPalindrome);

    }

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
                if (!("" + sArr[start]).equalsIgnoreCase(("" + sArr[end]))) {
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
