package solving_techniques.p26_backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators (hard)
 * https://leetcode.com/problems/expression-add-operators
 * <p>
 * #Company: Apple Facebook Google LinkedIn Microsoft Snapchat
 * <p>
 * Given a string num that contains only digits and an integer target,
 * return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num
 * so that the resultant expression evaluates to the target value.
 * <p>
 * Note that operands in the returned expressions should not contain leading zeros.
 * <p>
 * Example 1:
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 * <p>
 * Example 2:
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 * <p>
 * Example 3:
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 * <p>
 * Constraints:
 * 1 <= num.length <= 10
 * num consists of only digits.
 * -2^31 <= target <= 2^31 - 1
 */
public class ExpressionAddOperators {
    /**
     * NOT SOLVED by myself
     * info:
     * https://www.youtube.com/watch?v=tunRDBsP7OQ&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=83
     * idea:
     * 1) get all possible combinations and compare their evaluation to target
     * 2) to do this - use dfs + backtracking
     * 2.2) also keep precalculated eval value (curSum)
     * 3) to handle '*' case, we need to keep the latest number that was used. And do the following:
     * (the same as in BasicCalculator2: undo the prev action if current action is *)
     * curSum = curSum - prev + curNum * prev
     * BUT also keep in mind that in this case new 'prev' value is curNum * prev!
     * 4) to avoid overflow of int, use long for curNum, prev, curSum
     * 5) to avoid splitting in case when we have leading zero, add validation: if (num.charAt(idx) == '0') THEN break
     * <p>
     * time to solve ~ 60+ mins
     * <p>
     * a lot of attempts
     * <p>
     * BEATS ~ 54%
     */
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();

        dfs(num, 0, 0, "", 0, target, result);


        return result;
    }

    private void dfs(String num, int idx, long curSum, String tempResult, long prevNumber, int target, List<String> result) {
        if (idx == num.length()) {
            if (curSum == target) {
                result.add(tempResult.toString());
            }
            return;
        } else {
            for (int i = idx; i < num.length(); i++) {
                String curStr = num.substring(idx, i + 1);
                long curNum = Long.parseLong(curStr);

                if (tempResult.isEmpty()) {
                    dfs(num, i + 1, curNum, curStr, curNum, target, result);
                } else {
                    dfs(num, i + 1, curSum + curNum, tempResult + "+" + curStr, curNum, target, result);  //"+" case
                    //note: for '-' case prevNumber = -curNum (not just curNum)
                    dfs(num, i + 1, curSum - curNum, tempResult + "-" + curStr, -curNum, target, result);    //"-" case
                    dfs(num, i + 1, (curSum - prevNumber) + prevNumber * curNum, tempResult + "*" + curStr, prevNumber * curNum, target, result);  //"*" case
                }

                //handle leading zeros
                if (num.charAt(idx) == '0') {
                    //example: 051 is ok, when i = idx = 0 => it is 0 + 51
                    //but there is not sense to split when i = 1 (i.e. 05 & 1) or i = 2 (051).
                    //so need to stop the loop
                    break;
                }
            }
        }
    }

    /**
     * use StringBuilder
     * BEATS ~ 82%
     */
    public List<String> addOperators2(String num, int target) {
        List<String> result = new ArrayList<>();

        dfs2(num, 0, 0, new StringBuilder(), 0, target, result);


        return result;
    }

    private void dfs2(String num, int idx, long curSum, StringBuilder tempResult, long prevNumber, int target, List<String> result) {
        if (idx == num.length()) {
            if (curSum == target) {
                result.add(tempResult.toString());
            }
            return;
        } else {
            for (int i = idx; i < num.length(); i++) {
                String curStr = num.substring(idx, i + 1);
                long curNum = Long.parseLong(curStr);

                int lengthBefore = tempResult.length();

                if (tempResult.length() == 0) {
                    dfs2(num, i + 1, curNum, tempResult.append(curStr), curNum, target, result);
                    tempResult.setLength(lengthBefore);    //rollback
                } else {
                    dfs2(num, i + 1, curSum + curNum, tempResult.append("+").append(curStr), curNum, target, result);  //"+" case
                    tempResult.setLength(lengthBefore);    //rollback
                    //note: for '-' case prevNumber = -curNum (not just curNum)
                    dfs2(num, i + 1, curSum - curNum, tempResult.append("-").append(curStr), -curNum, target, result);    //"-" case
                    tempResult.setLength(lengthBefore);    //rollback
                    dfs2(num, i + 1, (curSum - prevNumber) + prevNumber * curNum, tempResult.append("*").append(curStr), prevNumber * curNum, target, result);  //"*" case
                    tempResult.setLength(lengthBefore);    //rollback
                }

                //handle leading zeros
                if (num.charAt(idx) == '0') {
                    //example: 051 is ok, when i = idx = 0 => it is 0 + 51
                    //but there is not sense to split when i = 1 (i.e. 05 & 1) or i = 2 (051).
                    //so need to stop the loop
                    break;
                }
            }
        }
    }
}
