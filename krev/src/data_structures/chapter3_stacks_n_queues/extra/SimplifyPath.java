package data_structures.chapter3_stacks_n_queues.extra;

import java.util.Stack;

/**
 * 71. Simplify Path (medium)
 * https://leetcode.com/problems/simplify-path
 *
 * #Company: Amazon Bloomberg ByteDance Docusign Facebook Microsoft Oracle Salesforce Yandex
 *
 * Given a string path, which is an absolute path (starting with a slash '/')
 * to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory,
 * a double period '..' refers to the directory up a level,
 * and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
 * For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 *
 * Example 1:
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 *
 * Example 2:
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 *
 * Example 3:
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 *
 * Example 4: (from https://leetcode.com/problems/simplify-path/solutions/1847526/best-explanation-ever-possible-not-a-clickbait/)
 * Input: path = "/a/./b/../../c/"
 * Output: "/c"
 *
 * Constraints:
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 */
public class SimplifyPath {
    public static void main(String[] args) {
        String s1 = "/home/";
        String s2 = "/a/./b/../../c/";
        System.out.println(simplifyPath(s1));
        System.out.println(simplifyPath2(s1));
        System.out.println(simplifyPath(s2));
        System.out.println(simplifyPath2(s2));
    }

    /**
     * NOT SOLVED
     * idea + explanation: https://leetcode.com/problems/simplify-path/solutions/1847526/best-explanation-ever-possible-not-a-clickbait/
     * implementation like https://leetcode.com/problems/simplify-path/solutions/4739848/java-easy-stack-solution/
     *
     * NOTE: to avoid duplication of code (when the last word is not saved into stack and wee add this saving after the for-loop),
     * we iterate up to arr.length inclusively!
     * BUT we have to add validations: i == arr.length and i < arr.length (see code below) to avoid ArrayIndexOutOfBoundsException
     *
     * time to implement ~ 90 mins
     * a lot of attempts
     */
    public static String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder(); //to store the name of current directory to bring it to stack later
        Stack<String> stack = new Stack<>();    //to store directories
        char[] arr = path.toCharArray();
        for (int i = 0; i < arr.length + 1; i++) {
            if ((i == arr.length || arr[i] == '/') && sb.length() > 0) {
                String dir = sb.toString();

                if ("..".equals(dir)) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (".".equals(dir)) {
                    //do nothing
                } else {
                    stack.push(dir);
                }

                sb.setLength(0);    //clean buffer
            } else if (i < arr.length && arr[i] != '/') {
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

    /**
     * info:
     * https://www.youtube.com/watch?v=4e1gVeQ0AEs&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=20
     *
     * using split method
     * time to implement ~ 10 mins
     * time ~ O(n)
     * space ~ O(n)
     *
     * 2 attempts:
     * - forgot case "".equals(dir)
     *
     * BEATS ~ 27%
     */
    public static String simplifyPath2(String path) {
        Stack<String> stack = new Stack<>();    //to store directories
        String[] dirs = path.split("/");
        for (String dir : dirs) {
            if ("..".equals(dir)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (".".equals(dir) || "".equals(dir)) {
                //do nothing
            } else {
                //usual name of dire => add to stacl
                stack.add(dir);
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
