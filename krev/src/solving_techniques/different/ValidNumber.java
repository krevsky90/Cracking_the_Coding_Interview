package solving_techniques.different;
/**
 * 65. Valid Number (hard)
 * https://leetcode.com/problems/valid-number
 *
 * #Company (24.02.2025): 0 - 3 months Meta 33 Amazon 2 0 - 6 months Google 2 Microsoft 2 6 months ago LinkedIn 6 Apple 3 TikTok 2 Instacart 2
 *
 * Given a string s, return whether s is a valid number.
 *
 * For example, all the following are valid numbers: "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789",
 * while the following are not valid numbers: "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53".
 *
 * Formally, a valid number is defined using one of the following definitions:
 *
 * An integer number followed by an optional exponent.
 * A decimal number followed by an optional exponent.
 * An integer number is defined with an optional sign '-' or '+' followed by digits.
 *
 * A decimal number is defined with an optional sign '-' or '+' followed by one of the following definitions:
 *
 * Digits followed by a dot '.'.
 * Digits followed by a dot '.' followed by digits.
 * A dot '.' followed by digits.
 * An exponent is defined with an exponent notation 'e' or 'E' followed by an integer number.
 *
 * The digits are defined as one or more digits.
 *
 * Example 1:
 * Input: s = "0"
 * Output: true
 *
 * Example 2:
 * Input: s = "e"
 * Output: false
 *
 * Example 3:
 * Input: s = "."
 * Output: false
 *
 * Constraints:
 * 1 <= s.length <= 20
 * s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
 */
public class ValidNumber {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 65 mins
     * idea:
     * use 3 flags:
     * dot = false
     * exp = false
     * hasDigits = false
     *
     * For each char:
     * 1) +-
     *      can be at 0-s position OR right after 'e' or 'E'
     *      can not be the last char
     * 2) 'e' or 'E':
     *      can be only if at least digit exists before
     *      can face only once
     *      can not be the last char
     * 3) '.':
     *      can face only once
     *      can not be if we have already faced 'e' or 'E'
     * 4) digit:
     *      just set hasDigits = true
     * 5) any other symbol:
     *      can not be
     *
     * a lot of attempts (~ 10)
     *
     * BEATS ~ 23-100%
     */
    public boolean isNumber(String s) {
        boolean dot = false;
        boolean exp = false;
        StringBuilder sb = new StringBuilder();

        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                sb.append(arr[i]);
            } else if (arr[i] == '-' || arr[i] == '+') {
                if (i > 0 && "eE".indexOf(arr[i-1]) == -1) return false;
                if (i == arr.length - 1) return false;

            } else if (arr[i] == 'e' || arr[i] == 'E') {
                if (sb.length() == 0) return false; // || Long.valueOf(sb.toString()) == 0L)    //commented since 0e3 is correct
                if (exp) return false;
                if (i == arr.length - 1) return false;

                exp = true;
            } else if (arr[i] == '.') {
                if (dot) return false;
                if (exp) return false;

                // sb.setLength(0);     commented since 44.e5 is correct
                dot = true;
            } else {
                return false;
            }
        }

        //since it should have at least one digit
        return sb.length() > 0;
    }

    /**
     * KREVSKY #2 (optimized):
     * replace StringBuilder wi boolean hasDigits, since it is not important what value we have
     *
     * BEATS ~ 100%
     */
    public boolean isNumber2(String s) {
        boolean dot = false;
        boolean exp = false;
        boolean hasDigits = false;

        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                if (!hasDigits) hasDigits = true;
            } else if (arr[i] == '-' || arr[i] == '+') {
                //+- can be at 0-s position or right after 'e' or 'E'
                if (i > 0 && "eE".indexOf(arr[i-1]) == -1) return false;
                //+- can not be the last symbol
                if (i == arr.length - 1) return false;
            } else if (arr[i] == 'e' || arr[i] == 'E') {
                if (!hasDigits) return false;
                if (exp) return false;
                if (i == arr.length - 1) return false;
                exp = true;
            } else if (arr[i] == '.') {
                if (dot) return false;
                if (exp) return false;
                dot = true;
            } else {
                return false;
            }
        }

        //since it should have at least one digit
        return hasDigits;
    }

}
