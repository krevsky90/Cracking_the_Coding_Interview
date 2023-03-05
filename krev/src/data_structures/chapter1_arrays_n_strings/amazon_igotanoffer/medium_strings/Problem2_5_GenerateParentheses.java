package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_strings;

import java.util.ArrayList;
import java.util.List;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * https://leetcode.com/problems/generate-parentheses/description/
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 *
 * Constraints:
 * 1 <= n <= 8
 */
public class Problem2_5_GenerateParentheses {
    /**
     * KREVSKY SOLUTION:
     * 30 mins with debugging, 1 attempt, optimal
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis(result, n, n, "");

        return result;
    }

    //n = 3
    //result =
    //open = 3
    //close = 3
    //combination = ""

    //generateParenthesis(2, 3, "(")
    //   generateParenthesis(1, 3, "((")
    //      generateParenthesis(0, 3, "(((")
    //          generateParenthesis(-1, 3, "((((") -> return
    //          ..
    //      generateParenthesis(1, 2, "(()")
    //   generateParenthesis(2, 2, "()")
    //generateParenthesis(3, 2, ")") -> return
    private void generateParenthesis(List<String> result, int open, int close, String combination) {
        // if (open < 0) return;
        // if (open > close) return;

        if (close == 0) {
            result.add(combination);
            return;
        }

        if (open > 0) {
            generateParenthesis(result, open - 1, close, combination + "(");
        }

        if (open < close) {
            generateParenthesis(result, open, close - 1, combination + ")");
        }
    }
}
