package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_strings;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * https://leetcode.com/problems/roman-to-integer/description/
 * <p>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 * <p>
 * Example 1:
 * Input: s = "III"
 * Output: 3
 * Explanation: III = 3.
 * <p>
 * Example 2:
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * <p>
 * Example 3:
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * <p>
 * <p>
 * Constraints:
 * 1 <= s.length <= 15
 * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
public class Problem1_17_RomanToInteger {
    /**
     * KREVSKY SOLUTION
     */
    public int romanToIntKREV(String s) {
        String temp = s;
        int result = 0;
        while (temp.length() > 0) {
            if (temp.startsWith("CD")) {
                result += 400;
                temp = temp.substring(2);
            } else if (temp.startsWith("CM")) {
                result += 900;
                temp = temp.substring(2);
            } else if (temp.startsWith("XL")) {
                result += 40;
                temp = temp.substring(2);
            } else if (temp.startsWith("XC")) {
                result += 90;
                temp = temp.substring(2);
            } else if (temp.startsWith("IV")) {
                result += 4;
                temp = temp.substring(2);
            } else if (temp.startsWith("IX")) {
                result += 9;
                temp = temp.substring(2);
            } else if (temp.startsWith("M")) {
                result += 1000;
                temp = temp.substring(1);
            } else if (temp.startsWith("D")) {
                result += 500;
                temp = temp.substring(1);
            } else if (temp.startsWith("C")) {
                result += 100;
                temp = temp.substring(1);
            } else if (temp.startsWith("L")) {
                result += 50;
                temp = temp.substring(1);
            } else if (temp.startsWith("X")) {
                result += 10;
                temp = temp.substring(1);
            } else if (temp.startsWith("V")) {
                result += 5;
                temp = temp.substring(1);
            } else if (temp.startsWith("I")) {
                result += 1;
                temp = temp.substring(1);
            }
        }

        return result;
    }

    // https://leetcode.com/problems/roman-to-integer/solutions/2632431/java-90-faster-solution/
    public int romanToInt(String s) {
        int answer = 0, number = 0, prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case 'I':
                    number = 1;
                    break;
                case 'V':
                    number = 5;
                    break;
                case 'X':
                    number = 10;
                    break;
                case 'L':
                    number = 50;
                    break;
                case 'C':
                    number = 100;
                    break;
                case 'D':
                    number = 500;
                    break;
                case 'M':
                    number = 1000;
                    break;
            }
            if (number < prev) {
                answer -= number;
            } else {
                answer += number;
            }
            prev = number;
        }
        return answer;
    }
}
