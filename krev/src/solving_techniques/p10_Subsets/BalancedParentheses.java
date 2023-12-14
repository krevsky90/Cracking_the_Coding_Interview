package solving_techniques.p10_Subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639cbb3b4374f9a7aada8ed0
 * OR
 * 22. Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/
 *
 * Problem Statement
 * For a given number ?N?, write a function to generate all combination of ?N? pairs of balanced parentheses.
 *
 * Example 1:
 * Input: N=2
 * Output: (()), ()()
 *
 * Example 2:
 * Input: N=3
 * Output: ((())), (()()), (())(), ()(()), ()()()
 *
 * NOTE! the SAME as Cracking_the_Coding_Interview\krev\src\data_structures\chapter1_arrays_n_strings\amazon_igotanoffer\medium_strings\Problem2_5_GenerateParentheses.java
 */
public class BalancedParentheses {

    //NOTE: this problem is kind of permutation problem
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis(n, result, "", 0, 0);
        return result;
    }

    private void generateParenthesis(int n, List<String> result, String temp, int open, int close) {
        if (temp.length() == 2*n) {
            result.add(temp);
            return;
        }

        if (open < n) {
            generateParenthesis(n, result, temp + "(", open + 1, close);
        }

        if (close < open) {
            generateParenthesis(n, result, temp + ")", open, close + 1);
        }
    }
}
