package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_strings;

import java.util.*;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * https://leetcode.com/problems/valid-parentheses/description/
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: s = "(]"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 */
public class Problem1_16_ValidParentheses {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 50 mins, 3 attempt, both 2 of them are related to typos
     *
     * Time complexity: O(n) where n = length of string.
     * Space complexity: O(n)
     */
    public boolean isValidKREV(String s) {
        //question / assumption
        if (s == null || s.isEmpty()) return true;

        Map<Character, Integer> map = new HashMap<>();
        map.put('(', 0);
        map.put('{', 0);
        map.put('[', 0);

        Map<Character, Character> mapOpenClose = new HashMap<>();
        mapOpenClose.put('(', ')');
        mapOpenClose.put('{', '}');
        mapOpenClose.put('[', ']');

        Map<Character, Character> mapCloseOpen = new HashMap<>();
        mapCloseOpen.put(')', '(');
        mapCloseOpen.put('}', '{');
        mapCloseOpen.put(']', '[');

        Deque<Character> closingBrackets = new LinkedList<>();

        // Input: s = "()[]{}"
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            Character c = arr[i];
            if (map.containsKey(c)) {
                //i.e. if c - is opening bracket
                map.put(c, map.get(c) + 1);
                closingBrackets.offerLast(mapOpenClose.get(c));
            } else {
                //i.e. if c - is closing bracket
                if (map.get(mapCloseOpen.get(c)) == 0) return false;

                Character closingBracket = closingBrackets.pollLast();

                //not sure about syntax
                if (closingBracket == null || !closingBracket.equals(c)) return false;

                Character openingBracket = mapCloseOpen.get(c);
                map.put(openingBracket, map.get(openingBracket) - 1);
            }
        }

        //check that all opening brackets have corresponding closing brackets
        for (Character c : map.keySet()) {
            if (map.get(c) != 0) return false;
        }

        return true;
    }

    /**
     * https://leetcode.com/problems/valid-parentheses/solutions/2986468/simple-java-beats-100-runtime-easy-to-understand/
     * Time complexity: O(n) where n = length of string.
     * Space complexity: O(n)
     */
    public boolean isValid(String s) {
        // Create a new stack to store the characters.
        Stack<Character> stack = new Stack<>();


        // convert string into char array and access the characters using for each loop.
        for (char ch : s.toCharArray()) {
            // check ch
            switch (ch) {
                // open bracket then push it in stack.
                // close bracket then pop the item and compare.
                case '(':
                case '{':
                case '[':
                    stack.push(ch);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') {
                        // if the stack is empty then it means string have no open bracket.
                        // hence it is invalid.
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
            }
        }


        // After the loop we have to check one more condition.
        // return true only if the stack is empty.
        // if stack is not empty that means we have unused brackets.

        return stack.isEmpty();

    }
}
