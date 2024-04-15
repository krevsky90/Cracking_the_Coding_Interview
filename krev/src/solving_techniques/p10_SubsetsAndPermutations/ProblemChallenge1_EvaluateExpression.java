package solving_techniques.p10_SubsetsAndPermutations;

import java.util.ArrayList;
import java.util.List;
/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639dcb67ef27e08651fb4db6 (hard)
 * OR
 * 241. Different Ways to Add Parentheses (medium)
 * https://leetcode.com/problems/different-ways-to-add-parentheses
 * <p>
 * Given a string expression of numbers and operators,
 * return all possible results from computing all the different possible ways to group numbers and operators.
 * You may return the answer in any order.
 * <p>
 * The test cases are generated such that the output values fit in a 32-bit integer
 * and the number of different results does not exceed 10^4.
 * <p>
 * Example 1:
 * Input: expression = "2-1-1"
 * Output: [0,2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * <p>
 * Example 2:
 * Input: expression = "2*3-4*5"
 * Output: [-34,-14,-10,-10,10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * <p>
 * Constraints:
 * 1 <= expression.length <= 20
 * expression consists of digits and the operator '+', '-', and '*'.
 * All the integer values in the input expression are in the range [0, 99].
 */
public class ProblemChallenge1_EvaluateExpression {
    public static void main(String[] args) {
        String exp1 = "2-1-1";
        String exp2 = "2*3-4*5";

        ProblemChallenge1_EvaluateExpression obj = new ProblemChallenge1_EvaluateExpression();
        List<Integer> result1 = obj.diffWaysToCompute(exp1);
        List<Integer> result2 = obj.diffWaysToCompute(exp2);
        System.out.println("");
    }

    /**
     * KREVKSY SOLUTION:
     * time to solve ~ 14 mins of thinking + 20 mins of implementing + 20 mins of debugging ~ 54 min
     *
     * idea: we have n numbers. Split into 2 groups: 0,i and i+1,n-1.
     * Find all values for eah group and multiply them to each other.
     * In general the idea is the same as in
     *      src/solving_techniques/p10_SubsetsAndPermutations/ProblemChallenge2_StructurallyUniqueBSTs.java
     *      or src/solving_techniques/p10_SubsetsAndPermutations/ProblemChallenge3_CountOfStructurallyUniqueBSTs.java
     *
     * base case: start = end => return arr.get(start)
     * base case: end - start == 1 => apply start-th operator to arr.get(start) and arr.get(end)
     *
     * attempts:
     * - incorrect syntax working with Character class
     * - incorrect for-loop: "int i = 0;" - wrong. "int i = start;" - correct
     * - incorrect "rightResult = calculate(i + 1, arr.size() - 1,...". correct is "rightResult = calculate(i + 1, end,..."
     * to sum up, I lost 20 mins due to incorrect bounds
     */
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> arr = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (c == '-' || c == '+' || c == '*') {
                arr.add(Integer.valueOf(sb.toString()));
                sb.setLength(0);
                operators.add(c);
            } else {
                sb.append(c);
            }
        }
        arr.add(Integer.valueOf(sb.toString()));   //add the last number

        return new ArrayList<>(calculate(0, arr.size() - 1, arr, operators));
    }

    private List<Integer> calculate(int start, int end, List<Integer> arr, List<Character> operators) {
        List<Integer> result = new ArrayList<>();
        if (end == start) {
            result.add(arr.get(start));
            return result;
        }

        if (end - start == 1) {
            result.add(calculate(arr.get(start), arr.get(end), operators.get(start)));
            return result;
        }


        for (int i = start; i < end; i++) {
            List<Integer> leftResult = calculate(start, i, arr, operators);
            List<Integer> rightResult = calculate(i + 1, end, arr, operators);
            for (Integer left : leftResult) {
                for (Integer right : rightResult) {
                    result.add(calculate(left, right, operators.get(i)));
                }
            }
        }

        return result;
    }

    private int calculate(int a, int b, Character operator) {
        char op = operator.charValue();
        if ('*' == op) return a*b;
        if ('-' == op) return a-b;

        return a+b;
    }
}
