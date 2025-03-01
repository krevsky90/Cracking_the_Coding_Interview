package solving_techniques.different;

/**
 * 9. Palindrome Number (easy)
 * https://leetcode.com/problems/palindrome-number/
 *
 * #Company (1.03.2025): 0 - 3 months Google 37 Meta 15 Microsoft 13 BCG 12 Amazon 11 Bloomberg 8 tcs 6 Capital One 4 0 - 6 months Oracle 3 Infosys 2 Cognizant 2 6 months ago Apple 40 Adobe 36 Yahoo 16 Accenture 14 Uber 12 Yandex 6 Wipro 5 Samsung 4 Deloitte 3 EPAM Systems 3
 *
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 *
 * Example 1:
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 *
 * Example 2:
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 *
 * Example 3:
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 * Constraints:
 * -2^31 <= x <= 2^31 - 1
 *
 * Follow up: Could you solve it without converting the integer to a string?
 */
public class PalindromeNumber {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 4 mins
     * <p>
     * time ~ O(log_10(n))
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 79-100%
     */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        int revertedNumber = 0;
        int orig = x;
        while (x > 0) {
            revertedNumber = 10 * revertedNumber + (x % 10);
            x /= 10;
        }

        return orig == revertedNumber;
    }

    /**
     * Official solution:
     */
    public boolean isPalindromeOfficial(int x) {
        //edge case 1
        if (x < 0) return false;
        //edge case 2
        if (x % 10 == 0 && x != 0) return false;

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = 10 * revertedNumber + (x % 10);
            x /= 10;
        }

        return x == revertedNumber //for even length of number (example: 1221 => reversed = 12, x = 12)
                || x == revertedNumber / 10;    //for odd length of number (example: 12321 => reversed = 123, x = 12)
    }
}
