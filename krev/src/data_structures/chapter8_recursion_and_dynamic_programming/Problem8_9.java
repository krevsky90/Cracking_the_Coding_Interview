package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.ArrayList;
import java.util.Stack;

/**
 * p.148
 * 8.9 Parens:
 * Implement an algorithm to print all valid (e.g., properly opened and closed) combinations of n pairs of parentheses.
 * EXAMPLE
 * Input: 3
 * Output: ((())), (()()), (())(), ()(()), ()()()
 * Hints: #138, #174, #187, #209, #243, #265, #295
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_9 {
    public static void main(String[] args) {
        int n = 3;
        printAll(n);
        System.out.println("original solution:");
        generateParents(n);
    }

    /**
     * KREVSKY SOLUTION ~ ORIGINAL SOLUTION
     */
    public static void printAll(int n) {
        printAll(n, n, "");
    }

    protected static void printAll(int openRemaining, int closeRemaining, String prefix) {
        //base case
        if(openRemaining == 0 && closeRemaining == 0) {
            System.out.println(prefix);
            return;
        }

        if (openRemaining > 0) {
            printAll(openRemaining - 1, closeRemaining, prefix + "(");
            if (closeRemaining > openRemaining) {
                printAll(openRemaining, closeRemaining - 1, prefix + ")");
            }
        } else {
            //i.e. openRemaining = 0
            printAll(openRemaining, closeRemaining - 1, prefix + ")");
        }
    }

    /**
     * ORIGINAL SOLUTION:
     * On each recursive call, we have the index for a particular character in the string.
     * We need to select either a left or a right paren.
     * When can we use a left paren, and when can we use a right paren?
     * 1. Left Paren: As long as we haven't used up all the left parentheses, we can always insert a left paren.
     * 2. Right Paren: We can insert a right paren as long as it won't lead to a syntax error.
     *      When will we get a syntax error?
     *      We will get a syntax error if there are more right parentheses than left.
     * So, we simply keep track of the number of left and right parentheses allowed. If there are left parens
     * remaining, we'll insert a left paren and recurse. If there are more right parens remaining than left (i.e., if
     * there are more left parens in use than right parens), then we'll insert a right paren and recurse.
     */
    public static ArrayList<String> generateParents(int count) {
        char[] str = new char[2*count];
        ArrayList<String> list = new ArrayList<>();
        addParen(list, count, count, str, 0);
        return list;
    }

    protected static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
        if (leftRem < 0 || rightRem < leftRem) return;  //invalid state

        if (leftRem == 0 && rightRem == 0) {
            String s = String.copyValueOf(str);
            list.add(s);
            System.out.println(s);
        } else {
            str[index] = '(';   //add left and recurse
            addParen(list, leftRem - 1, rightRem, str, index + 1);

            str[index] = ')';   //add right and recurse
            addParen(list, leftRem , rightRem - 1, str, index + 1);
        }
    }
}