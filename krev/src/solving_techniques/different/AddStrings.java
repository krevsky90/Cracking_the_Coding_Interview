package solving_techniques.different;

/**
 * 415. Add Strings (easy)
 * https://leetcode.com/problems/add-strings
 * <p>
 * #Company: Adobe Airbnb Alibaba Amazon Apple Bloomberg ByteDance Facebook Google Microsoft Nvidia Oracle Snapchat Square Yandex
 * <p>
 * Given two non-negative integers, num1 and num2 represented as string,
 * return the sum of num1 and num2 as a string.
 * <p>
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
 * You must also not convert the inputs to integers directly.
 * <p>
 * Example 1:
 * Input: num1 = "11", num2 = "123"
 * Output: "134"
 * <p>
 * Example 2:
 * Input: num1 = "456", num2 = "77"
 * Output: "533"
 * <p>
 * Example 3:
 * Input: num1 = "0", num2 = "0"
 * Output: "0"
 * <p>
 * Constraints:
 * 1 <= num1.length, num2.length <= 10^4
 * num1 and num2 consist of only digits.
 * num1 and num2 don't have any leading zeros except for the zero itself.
 */
public class AddStrings {
    public static void main(String[] args) {
        String res = addStrings("456", "77");
        System.out.println(res);
    }

    /**
     * SOLUTION #1:
     * My solution had the same idea and worked (time to solve ~ 13 mins, 2 attempts (because of lack of Integer.valueOf("" + ..)), but was not beauty.
     * <p>
     * info: https://leetcode.com/problems/add-strings/solutions/5354286/addition-pattern-must-read/
     * <p>
     * BEATS ~ 100%
     */
    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i1 = num1.length() - 1;
        int i2 = num2.length() - 1;

        int delta = 0;
        while (i1 >= 0 || i2 >= 0 || delta > 0) {
            int tempSum = delta;
            if (i1 >= 0) {
                // tempSum += Integer.valueOf("" + num1.charAt(i1));
                //or
                tempSum += num1.charAt(i1) - '0';
                i1--;
            }

            if (i2 >= 0) {
                // tempSum += Integer.valueOf("" + num2.charAt(i2));
                //or
                tempSum += num2.charAt(i2) - '0';
                i2--;
            }

            delta = tempSum / 10;
            sb.append(tempSum % 10);
        }

        return sb.reverse().toString();
    }
}
