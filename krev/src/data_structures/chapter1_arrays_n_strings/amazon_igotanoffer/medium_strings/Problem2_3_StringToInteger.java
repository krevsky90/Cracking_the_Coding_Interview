package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_strings;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 *
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * Read in and ignore any leading whitespace.
 * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
 * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
 * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
 * Return the integer as the final result.
 * Note:
 *
 * Only the space character ' ' is considered a whitespace character.
 * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 *
 * Constraints:
 * 0 <= s.length <= 200
 * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 */
public class Problem2_3_StringToInteger {
    /**
     * KREVSKY SOLUTION
     */

    public int myAtoiKREV(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        int sign = 1;
        if (i < s.length() && s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (i < s.length() && s.charAt(i) == '+') {
            i++;
        }

        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }

        int start = -1;
        boolean startFlag = true;
        int end = -1;
        //ASCII '0' is 48 ... '9' - 57
        while (i < s.length() && s.charAt(i) >= 48 && s.charAt(i) <= 57) {
            //start to read number's digits
            if (startFlag) {
                start = i;
                startFlag = false;
            }
            end = i;
            i++;
        }

        if (start == -1) {
            //if s starts not from a number
            return 0;
        }

        String strResult = (sign == -1 ? "-" : "") + s.substring(start, end + 1);

        //NOTE: since s.length <= 200, then we can use double, because it is < 1.7*10^308
        double result = new Double(strResult);
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (Integer.MIN_VALUE > result) {
            return Integer.MIN_VALUE;
        } else {
            return (int) result;
        }
    }

    /**
     * OFFICIAL SOLUTION
     * https://medium.com/@harycane/string-to-integer-atoi-1e75325c5fd7
     */
    public int myAtoi(String str) {

        //remove whitespaces
        str = str.trim();

        //sanity check
        if (str == null || str.length() == 0) {
            return 0;
        }

        //declare result of type double to handle overflow cases
        double result = 0;


        //check for positive or negative sign
        boolean isNegative = false;
        int startIndex = 0;

        if (str.charAt(0) == '+' || str.charAt(0) == '-') {
            ++startIndex;
        }

        if (str.charAt(0) == '-') {
            isNegative = true;
        }

        //handle numeric case = "123"
        for (int i = startIndex; i < str.length(); i++) {
            //handle for non numeric characters
            if (str.charAt(i) < '0' || str.charAt(i) > '9') { //checking if ascii value is below or above 0 or 9
                break;
            }
            int digitValue = (int) (str.charAt(i) - '0');
            result = result * 10 + digitValue;
        }

        //toggle result in case of negative is true
        if (isNegative) {
            result = -result;
        }

        //handle underflow
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        //handle overflow
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        //return result
        return (int) result;

        //T O(n) S O(1)
    }
}
