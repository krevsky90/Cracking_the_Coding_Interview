package solving_techniques.p27_Stacks;

import java.util.Stack;

/**
 * 224. Basic Calculator (hard)
 * https://leetcode.com/problems/basic-calculator
 *
 * #Company: Adobe Amazon Bloomberg Cruise Automation Facebook Google Indeed Intuit Jump Trading Karat Microsoft Paypal Pinterest Robinhood Roblox Snapchat Uber
 *
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it,
 *      and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Example 1:
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Example 2:
 * Input: s = " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Constraints:
 * 1 <= s.length <= 3 * 10^5
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 * Every number and running calculation will fit in a signed 32-bit integer.
 */
public class BasicCalculator {
    public static void main(String[] args) {
        String s1 = "1 + 1";
        String s3 = "(1+(4+5+2)-3)+(6+8)";
        BasicCalculator obj = new BasicCalculator();
        System.out.println(obj.calculate(s3));
        System.out.println(obj.calculateRecursion(s3));
    }

    /**
     * NOTE SOLVED by myself
     *
     * SOLUTION #1:
     * info:
     * https://www.youtube.com/watch?v=zsJ-J08Qgdk&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=3
     *
     * idea:
     * 1) use stack, that stores current result and previous prevSign (+/-)
     * 2) if sign '+' => store 1, else => -1. it helps to use sign*cur expression.
     *      Also it helps to store result and sign with the same (Integer) type
     *
     * time ~ O(n)
     * space ~ O(n) - worst case, when 1+(1+(1+...)))
     *
     * BEATS ~ 56%
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();   //3,-1,1,1
        int res = 0;
        int prevSign = 1;   //it is '+' by default
        int cur = 0;

        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c == '(') {
                //save result and sign and calculate the result inside the brackets
                stack.add(res);
                stack.add(prevSign);
                res = 0;
                prevSign = 1;
            } else if (c == ')') {
                res += prevSign*cur;
                prevSign = stack.pop(); //get sign
                int prevRes = stack.pop();
                res = prevRes + prevSign*res;
                cur = 0;
            } else if (Character.isDigit(c)) {
                cur = 10*cur + Character.getNumericValue(c);    //since we read char by char => number = '32' will be read as 3*10 + 2
            } else if (c == '-' || c == '+') {
                res += prevSign*cur;
                prevSign = c == '+' ? 1 : -1;
                cur = 0;
            }
        }

        return res + prevSign*cur;
    }

    /**
     * SOLUTION #2:
     * info:
     * https://leetcode.com/problems/basic-calculator/solutions/5478492/basic-calculator-beats-100-easy-to-understand/
     * idea:
     * 1) recursion
     * 2) index, which is out of recursion
     * 3) save prev res and prevSign, as for solution #1
     *
     */
    int idx;
    public int calculateRecursion(String s) {
        idx=0;
        return calc(s);
    }
    private int calc(String s) {
        int num = 0, res = 0;
        int sign = 1;
        while (idx< s.length()) {
            char c = s.charAt(idx++);
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                num = calc(s);
            } else if (c == ')') {
                return res += num * sign;
            } else if (c == '+' || c == '-') {
                res += num * sign;
                num = 0;
                sign = (c == '-') ? -1 : 1;
            }
        }
        res += num * sign;
        return res;
    }

}
