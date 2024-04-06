package solving_techniques.p27_Stacks;

import java.util.Stack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6490189d822978cc90e2ace0
 *
 * Given a string, write a function that uses a stack to reverse the string.
 * The function should return the reversed string.
 *
 * Example 1:
 * Input: "Hello, World!"
 * Output: "!dlroW ,olleH"
 *
 * Example 2:
 * Input: "OpenAI"
 * Output: "IAnepO"
 *
 * Example 3:
 * Input: "Stacks are fun!"
 * Output: "!nuf era skcatS"
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is a printable ascii character.
 */
public class ReverseString {
    public static void main(String[] args) {
        String s1 = "Hello, World!";
        String s2 = "OpenAI";
        String s3 = "Stacks are fun!";

        System.out.println(new ReverseString().reverseString(s1));  //expected "!dlroW ,olleH"
        System.out.println(new ReverseString().reverseString(s2));  //expected "IAnepO"
        System.out.println(new ReverseString().reverseString(s3));  //expected "!nuf era skcatS"
    }

    public String reverseString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
