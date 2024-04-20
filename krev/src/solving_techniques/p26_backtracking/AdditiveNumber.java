package solving_techniques.p26_backtracking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 306. Additive Number
 * https://leetcode.com/problems/additive-number
 *
 * An additive number is a string whose digits can form an additive sequence.
 *
 * A valid additive sequence should contain at least three numbers.
 * Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 *
 * Given a string containing only digits, return true if it is an additive number or false otherwise.
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 * Example 1:
 * Input: "112358"
 * Output: true
 * Explanation:
 * The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 * Example 2:
 * Input: "199100199"
 * Output: true
 * Explanation:
 * The additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 *
 * Constraints:
 * 1 <= num.length <= 35
 * num consists only of digits.
 *
 *
 * Follow up: How would you handle overflow for very large input integers?
 */
public class AdditiveNumber {
    public static void main(String[] args) {
        String s1 = "112358";
        String s2 = "199100199";

        System.out.println(new AdditiveNumber().isAdditiveNumber1(s1));
        System.out.println(new AdditiveNumber().isAdditiveNumber1(s2));
    }

    /**
     * SOLUTION #1: backtracking
     * NOT SOLVED by myself
     * info:
     * https://leetcode.com/problems/additive-number/solutions/4859929/java-backtracking-beats-100/
     *
     * BEATS = 86%
     */
    public boolean isAdditiveNumber1(String num) {
        return checkDfsBacktracking(num, 0, new ArrayList<>());
    }

    private boolean checkDfsBacktracking(String num, int start, List<Long> tempList) {
        //stop condition
        if (start == num.length()) {
            return tempList.size() > 2;
        }

        Long expectedValue = tempList.size() >= 2 ? (tempList.get(tempList.size() - 1) + tempList.get(tempList.size() - 2)) : null;
        long number = 0;

        for (int i = start; i < num.length(); i++) {
            if (i > start && num.charAt(start) == '0') {
                // no leading zeroes are allowed
                break;
            }

            number = 10*number + Character.getNumericValue(num.charAt(i));  //note! no convertation of type, since num.charAt(i) = '1' will be converted to 49!

            if (expectedValue == null || expectedValue == number) {
                //backtracking starts
                tempList.add(number);
                if (checkDfsBacktracking(num, i + 1, tempList)) {
                    return true;
                }
                tempList.remove((tempList.size() - 1));
                //backtracking ends
            }

            if (expectedValue != null && number > expectedValue) {
                // no point searching beyond this
                break;
            }
        }

        return false;
    }


    /**
     * SOLUTION #2:
     * NOT SOLVED by myself
     * NO backtracking! just DFS
     *
     * idea: https://www.youtube.com/watch?v=OatCe9_DF0k
     * 1) generate ALL possible combinations of num1 and num2.
     * 2) check if the rest of the string match the sum recursively
     *
     * time to solve ~ 55 mins
     *
     * 3 attempts:
     * - forgot stop condition: if (j == num.length()) return true;
     * - forgot to check like "i - start > 1'  before checking "num.charAt(start) == '0'"
     */
    public boolean isAdditiveNumber2(String num) {
        for (int i = 1; i < num.length() - 1; i++) {
            for (int j = i + 1; j < num.length(); j++) {
                if (checkDfs(num, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkDfs(String num, int start, int i, int j) {
        if (j == num.length()) return true;

        if ((i - start > 1 && num.charAt(start) == '0')
                || (j - i > 1 && num.charAt(i) == '0')) return false;

        String num1 = num.substring(start,i);   //not include i-th symbol
        String num2 = num.substring(i,j);
        String sum = new BigInteger(num1).add(new BigInteger(num2)).toString();
        boolean res = num.substring(j).startsWith(sum);

        return res == false ? false : checkDfs(num, i, j, j + sum.length());
    }

}
