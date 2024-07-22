package solving_techniques.p10_SubsetsAndPermutations;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639cbb3b4374f9a7aada8ed0
 * OR
 * 22. Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/
 *
 * #Company: Yandex
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

    /**
     * BEATS ~ 55%
     */
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

    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis2(n, result, "", n, n);
        return result;
    }

    //or the same
    private void generateParenthesis2(int n, List<String> result, String temp, int open, int close) {
        if (temp.length() == 2*n) {
            result.add(temp);
            return;
        }

        if (open > 0) {
            generateParenthesis2(n, result, temp + "(", open - 1, close);
        }

        //close > open, correct, but not obvious
        if (close > open && close > 0) {
            generateParenthesis2(n, result, temp + ")", open, close - 1);
        }
    }

    /**
     *  or the same BUT using StringBuilder - to speed up
     *
     *  BEATS ~ 85%
     */
    public List<String> generateParenthesis3(int n) {
        List<String> result = new ArrayList<>();
        generate(n, 0, 0, new StringBuilder(), result);

        return result;
    }

    private void generate(int n, int open, int closed, StringBuilder temp, List<String> result) {
        if (temp.length() == 2*n) {
            result.add(temp.toString());
            return;
        }

        if (open < n) {
            temp.append("(");
            generate(n, open + 1, closed, temp, result);
            temp.deleteCharAt(temp.length() - 1);
        }

        if (closed < open) {
            temp.append(")");
            generate(n, open, closed + 1, temp, result);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

}
