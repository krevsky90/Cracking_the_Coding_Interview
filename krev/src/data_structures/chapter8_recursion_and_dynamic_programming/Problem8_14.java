package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.Map;

/**
 * p.148
 * 8.14 Boolean Evaluation:
 * Given a boolean expression consisting of the symbols
 * 0 (false), 1 (true), & (AND), | (OR), ^ (XOR) and a desired boolean result value 'RESULT',
 * implement a function to count the number of ways of parenthesizing the expression
 * such that it evaluates to result.
 * EXAMPLE
 * countEval("1^0|0|1", false) -> 2
 * countEval("0&0&0&1^1|0", true) -> 10
 * Hints: #148, #168, #197, #305, #327
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_14 {
    /**
     * ORIGINAL SOLUTION
     * Idea:
     * 1) divide expression into 2 parts by parentheses using different combinations
     * 2) depending on what operator connects these 2 parts, make conclusion about possible combination of the values of left and right parts
     * do both steps recursively
     * detailed explanation see in the book (p.368)
     */
    public static int countEval(String expression, boolean value) {
        if (expression.length() == 0) return 0;
        //base case
        if (expression.length() == 1) return stringToBool(expression) == value ? 1 : 0;

        int ways = 0;
        //i - place where we set parentheses
        for (int i = 1; i < expression.length(); i += 2) {
            String left = expression.substring(0, i);
            String right = expression.substring(i + 1);
            int countLeftTrue = countEval(left, true);
            int countLeftFalse = countEval(left, false);
            int countRightTrue = countEval(right, true);
            int countRightFalse = countEval(right, false);
            int totalCount = (countLeftFalse + countLeftTrue) * (countRightFalse + countRightTrue);

            int totalTrueCount = 0;
            if (expression.charAt(i) == '&') {
                totalTrueCount = countLeftTrue * countRightTrue;
            } else if (expression.charAt(i) == '|') {
                totalTrueCount = countLeftTrue * countRightTrue + countLeftFalse * countRightTrue + countRightFalse * countLeftTrue;
            } else if (expression.charAt(i) == '^') {
                totalTrueCount = countLeftTrue * countRightFalse + countLeftFalse * countRightTrue;
            }

            int subways = value ? totalTrueCount : (totalCount - totalTrueCount);
            ways += subways;
        }

        return ways;
    }

    /**
     * ORIGINAL SOLUTION: optimized (memo)
     */
    public static int countEvalMemo(String expression, boolean value, Map<String, Integer> exprToCount) {
        if (expression.length() == 0) return 0;
        //base case
        if (expression.length() == 1) return stringToBool(expression) == value ? 1 : 0;
        //request cache (memo)
        if (exprToCount.containsKey(value + expression)) return exprToCount.get(value + expression);

        int ways = 0;
        //i - place where we set parentheses
        for (int i = 1; i < expression.length(); i += 2) {
            String left = expression.substring(0, i);
            String right = expression.substring(i + 1);
            int countLeftTrue = countEvalMemo(left, true, exprToCount);
            int countLeftFalse = countEvalMemo(left, false, exprToCount);
            int countRightTrue = countEvalMemo(right, true, exprToCount);
            int countRightFalse = countEvalMemo(right, false, exprToCount);
            int totalCount = (countLeftFalse + countLeftTrue) * (countRightFalse + countRightTrue);

            int totalTrueCount = 0;
            if (expression.charAt(i) == '&') {
                totalTrueCount = countLeftTrue * countRightTrue;
            } else if (expression.charAt(i) == '|') {
                totalTrueCount = countLeftTrue * countRightTrue + countLeftFalse * countRightTrue + countRightFalse * countLeftTrue;
            } else if (expression.charAt(i) == '^') {
                totalTrueCount = countLeftTrue * countRightFalse + countLeftFalse * countRightTrue;
            }

            int subways = value ? totalTrueCount : (totalCount - totalTrueCount);
            ways += subways;
        }

        //set to cache (memo)
        exprToCount.put(value + expression, ways);

        return ways;
    }

    private static boolean stringToBool(String s) {
        return "1".equals(s);
    }
}
