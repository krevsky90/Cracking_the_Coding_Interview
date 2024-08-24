package solving_techniques.p27_Stacks;

import java.util.Stack;

/**
 * 227. Basic Calculator II (medium)
 * https://leetcode.com/problems/basic-calculator-ii/
 *
 * #Company: Airbnb Amazon Apple Atlassian Baidu DoorDash Facebook Google Groupon Houzz Intuit IXL Microsoft Oracle Reddit Salesforce Snapchat Tableau Uber Walmart Labs Yelp
 * <p>
 * Given a string s which represents an expression, evaluate this expression and return its value.
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-231, 231 - 1].
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * <p>
 * Example 1:
 * Input: s = "3+2*2"
 * Output: 7
 * <p>
 * Example 2:
 * Input: s = " 3/2 "
 * Output: 1
 * <p>
 * Example 3:
 * Input: s = " 3+5 / 2 "
 * Output: 5
 * <p>
 * Constraints:
 * 1 <= s.length <= 3 * 10^5
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 */
public class BasicCalculator2 {
    public static void main(String[] args) {
        String s1 = "3+2*2";
        String s4 = "33+5/2*3-2";
        BasicCalculator2 obj = new BasicCalculator2();
        System.out.println(obj.calculate11(s1));
        System.out.println(obj.calculateKrev(s1));
        System.out.println(obj.calculate11(s4));
        System.out.println(obj.calculateKrev(s4));
    }

    /**
     * SOLUTION #1:
     * info:
     * https://www.youtube.com/watch?v=W3Rg4HVSZ9k&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=5
     * <p>
     * original implementation
     * BEATS ~ 90%
     *
     * NO duplicate code!
     * idea:
     * while (i < arr.length && Character.isDigit(arr[i]))  helps to call THE SAME code in both cases (i.e. not digit or end of string)
     */
    public int calculate11(String s) {
        char[] arr = s.toCharArray();
        int res = 0;
        int prev = 0;
        int cur = 0;
        char operation = '+';

        int i = 0;
        while (i < arr.length) {
            if (Character.isDigit(arr[i])) {
                while (i < arr.length && Character.isDigit(arr[i])) {
                    cur = 10 * cur + Character.getNumericValue(arr[i]);
                    i++;
                }
                i--;    //step back to stay at the latest digit of found number

                if (operation == '+') {
                    res += cur;
                    prev = cur;
                } else if (operation == '-') {
                    res -= cur;
                    prev = -cur;
                } else if (operation == '*') {
                    //undo
                    res -= prev;
                    res += prev * cur;
                    prev = prev * cur;
                } else if (operation == '/') {
                    //undo
                    res -= prev;
                    res += prev / cur;
                    prev = prev / cur;
                }

                cur = 0;
                operation = arr[i];
            } else if (arr[i] != ' ') {
                operation = arr[i];
            }

            i++;
        }

        return res;
    }

    /**
     * SOLUTION #1 + Krevsky implementation
     * info:
     * https://www.youtube.com/watch?v=W3Rg4HVSZ9k&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=5
     * idea:
     * 1) store res, cur, prev
     * 2) initially current operation = '+'
     * 3) if operation = '*' or '/' then UNDO previous action! (i.e. res -= prev)
     * and res += prev * (or /) cur
     * <p>
     * NOTE: it works also for 2*2*2:
     * cur = res = 2
     * then we rollback => res = 0, then res += 2 * 2 = 4
     * etc
     * <p>
     * <p>
     * PROBLEM: duplicate code!
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * 2 attempts:
     * - incorrect formula (see +=): cur += 10 * cur + Character.getNumericValue(arr[i]);
     * <p>
     * BEATS ~ 96%
     */
    public int calculate12(String s) {
        char[] arr = s.toCharArray();
        int res = 0;
        int prev = 0;
        int cur = 0;
        char operation = '+';

        int i = 0;
        while (i < arr.length) {
            if (Character.isDigit(arr[i])) {
                cur = 10 * cur + Character.getNumericValue(arr[i]);
            } else if (arr[i] != ' ') {
                if (operation == '+') {
                    res += cur;
                    prev = cur;
                } else if (operation == '-') {
                    res -= cur;
                    prev = -cur;
                } else if (operation == '*') {
                    //undo
                    res -= prev;
                    res += prev * cur;
                    prev = prev * cur;
                } else if (operation == '/') {
                    //undo
                    res -= prev;
                    res += prev / cur;
                    prev = prev / cur;
                }

                cur = 0;
                operation = arr[i];
            }

            i++;
        }

        //duplicate code
        if (operation == '+') {
            res += cur;
            prev = cur;
        } else if (operation == '-') {
            res -= cur;
            prev = -cur;
        } else if (operation == '*') {
            //undo
            res -= prev;
            res += prev * cur;
            prev = prev * cur;
        } else if (operation == '/') {
            //undo
            res -= prev;
            res += prev / cur;
            prev = prev / cur;
        }

        return res;
    }

    /**
     * /**
     * KREVSKY SOLUTION:
     * time to solve ~ 35 mins
     * time ~ O(n)
     * space ~ O(n) - the worst case when 1 + 2 - 1 + 1 etc (i.e. only + and -)
     * <p>
     * 2 attempts:
     * -forgot block "if (multiDiv > 0) {" after for-loop
     * <p>
     * BEATS ~ 14%
     */
    public int calculateKrev(String s) {
        char[] arr = s.toCharArray();

        Stack<String> stack = new Stack<>();
        stack.add("+");    //hack for further part "while (!stack.isEmpty())"
        int multiDiv = 0;
        int cur = 0;
        int res = 0;
        for (char c : arr) {
            if (Character.isDigit(c)) {
                cur = 10 * cur + Character.getNumericValue(c);
            } else if (c == '+' || c == '-') {
                if (multiDiv > 0) {
                    cur = getSubResult(stack, cur);
                    multiDiv--;
                }
                stack.add("" + cur);
                cur = 0;

                stack.add("" + c);
            } else if (c == '*' || c == '/') {
                if (multiDiv > 0) {
                    cur = getSubResult(stack, cur);
                    multiDiv--;
                }

                stack.add("" + cur);
                cur = 0;

                multiDiv++;
                stack.add("" + c);

            }
        }

        if (multiDiv > 0) {
            cur = getSubResult(stack, cur);
            multiDiv--; //not necessary
        }

        stack.add("" + cur);
        //traverse through all stack, that contains only numbers and '+'/'-' operations:
        res = 0;
        while (!stack.isEmpty()) {
            int n = Integer.valueOf(stack.pop());    //we know it contains number on top of it
            String signStr = stack.pop();
            int sign = "+".equals(signStr) ? 1 : -1;
            res += sign * n;
        }

        return res;
    }

    private int getSubResult(Stack<String> stack, int cur) {
        String sign = stack.pop();
        String number = stack.pop();
        if ("/".equals(sign)) {
            return Integer.valueOf(number) / cur;
        } else if ("*".equals(sign)) {
            return Integer.valueOf(number) * cur;
        } else {
            throw new IllegalArgumentException("todo");
        }
    }
}
