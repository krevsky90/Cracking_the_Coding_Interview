package solving_techniques.p27_Stacks;

import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
 * <p>
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * <p>
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * <p>
 * Note that:
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 * <p>
 * Example 1:
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * <p>
 * Example 2:
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * <p>
 * Example 3:
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * <p>
 * Constraints:
 * 1 <= tokens.length <= 10^4
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */
public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

        System.out.println(new EvaluateReversePolishNotation().evalRPN(tokens1));
        System.out.println(new EvaluateReversePolishNotation().evalRPN2(tokens1));
    }

    /**
     * KREVSKY SOLUTION: NOT optimal
     * idea: put each element to stack, BUT from right to left.
     * and count amount of numbers that put one after the other. if 2 numbers are put =>
     * pop them
     * pop operator from stack
     * execute apply the operator to these numbers
     * out the result back to the stack
     * IF the 2 top elements are numbers (again) - repeat the algorithm.
     * otherwise set counter of numbers = 1
     * <p>
     * time to solve ~ 50 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - NOT "prev = stack.pop();" BUT "prev = stack.peek();"
     */
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        int counter = 0;    //counts the amount of numbers put into the stack one-by-one
        for (int i = tokens.length - 1; i >= 0; i--) {
            String s = tokens[i];
            //NOTE: believe that s is correct number since it is not operator
            if (isOperator(s)) {
                counter = 0;
            } else {
                counter++;
            }
            stack.add(s);

            while (counter == 2) {
                //calculate and push to 'numbers' stack
                String n1 = stack.pop();
                String n2 = stack.pop();
                String operation = stack.pop();
                String result = applyOperator(n1, n2, operation);

                String prev = null;
                if (!stack.isEmpty()) {
                    prev = stack.peek();
                }

                stack.add(result);
                counter = 1;

                if (prev != null && !isOperator(prev)) {
                    //it means that top element of stack is number => we can calculate again
                    counter = 2;
                }
            }
        }
        //by this time stack contains the only 1 element (number)
        return Integer.valueOf(stack.pop());
    }

    private boolean isOperator(String s) {
        return "+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s);
    }

    private String applyOperator(String n1, String n2, String s) {
        if ("+".equals(s)) return "" + (Integer.valueOf(n1) + Integer.valueOf(n2));
        if ("-".equals(s)) return "" + (Integer.valueOf(n1) - Integer.valueOf(n2));
        if ("*".equals(s)) return "" + (Integer.valueOf(n1) * Integer.valueOf(n2));
        if ("/".equals(s)) return "" + (Integer.valueOf(n1) / Integer.valueOf(n2));
        //dummy
        return "";
    }

    /**
     * Optimal
     * https://leetcode.com/problems/evaluate-reverse-polish-notation/solutions/4648953/simple-and-easy-java-code-stack-o-n/
     * idea: read array from left to right!
     * if number - put to stack
     * if operator - just pop 2 last elements from stack, calculate its result and push back to stack
     * <p>
     * idea #2: use ACSII table in isOperator method
     */
    public int evalRPN2(String[] tokens) {
        Stack<Integer> nums = new Stack<>();
        for (String i : tokens) {
            if (isOperator2(i)) {
                nums.push(applyOperator2(nums.pop(), nums.pop(), i.charAt(0)));
            } else {
                nums.push(Integer.parseInt(i));
            }
        }
        return nums.peek();
    }

    private boolean isOperator2(String s) {
        char ch = s.charAt(0);
        return s.length() == 1 && ch >= '*' && ch <= '/';
    }

    public int applyOperator2(int num2, int num1, char op) {
        int ans = 0;
        switch (op) {
            case '+':
                ans = num1 + num2;
                break;
            case '-':
                ans = num1 - num2;
                break;
            case '*':
                ans = num1 * num2;
                break;
            case '/':
                ans = num1 / num2;
                break;
            default:
                ans = 0;
                break;
        }
        return ans;
    }

}
