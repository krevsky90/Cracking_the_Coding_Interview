package solving_techniques.p27_Stacks;

import java.util.Stack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/64902fec5d0034e2d16110aa
 * OR
 * 71. Simplify Path
 * https://leetcode.com/problems/simplify-path
 * <p>
 * Given an absolute file path in a Unix-style file system, simplify it by converting ".." to the previous directory
 * and removing any "." or multiple slashes. The resulting string should represent the shortest absolute path.
 * <p>
 * Examples:
 * 1.
 * Input: "/a//b////c/d//././/.."
 * Output: "/a/b/c"
 * <p>
 * 2.
 * Input: "/../"
 * Output: "/"
 * <p>
 * 3.
 * Input: "/home//foo/"
 * Output: "/home/foo"
 * Constraints:
 * <p>
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path
 */

//NOTE: the same as src/data_structures/chapter3_stacks_n_queues/extra/ReverseStackTest.java
public class SimplifyPath {
    public static void main(String[] args) {
        String s1 = "/a//b////c/d//././/..";
        System.out.println(new SimplifyPath().simplifyPath(s1));    // expected /a/b/c
    }

    /**
     * KREVSKY #2:
     * from 12.10.2024
     *
     */
    public String simplifyPath_12102024(String path) {
        Stack<String> stack = new Stack<>();

        char[] arr = path.toCharArray();
        String[] split = path.split("/");

        for (String s : split) {
            if (s.isEmpty() || ".".equals(s)) {
                continue;
            } else if ("..".equals(s)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.add(s);
            }
        }

        String result = "";
        while (!stack.isEmpty()) {
            result = "/" + stack.pop() + result;
        }

        return result.length() == 0 ? "/" : result;
    }

    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder(); //to store the name of current directory to bring it to stack later
        Stack<String> stack = new Stack<>();    //to store directories
        char[] arr = path.toCharArray();
        for (int i = 0; i < arr.length + 1; i++) {
            if ((i == arr.length || arr[i] == '/') && sb.length() > 0) {
                String dir = sb.toString();

                if ("..".equals(dir)) {
                    //NOTE: we can join these if-conditions by &&, since in this case we add ".." (in else block) to stack, if stack is empty!
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (".".equals(dir)) {
                    //do nothing
                } else {
                    stack.push(dir);
                }

                sb.setLength(0);    //clean buffer
            } else if (i < arr.length && arr[i] != '/') {//NOTE: do not forget "arr[i] != '/'' !
                sb.append(arr[i]);
            }
        }

        String result = "";
        while (!stack.isEmpty()) {
            //note: add 'result' in the end since stack contains reversed sequence of the folders
            result = "/" + stack.pop() + result;
        }

        return result.length() == 0 ? "/" : result;
    }
}