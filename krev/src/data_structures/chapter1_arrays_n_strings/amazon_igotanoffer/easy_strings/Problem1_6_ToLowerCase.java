package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_strings;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * https://leetcode.com/problems/to-lower-case/description/
 * <p>
 * Given a string s, return the string after replacing every uppercase letter with the same lowercase letter.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: s = "Hello"
 * Output: "hello"
 */
public class Problem1_6_ToLowerCase {
    /**
     * KREVSKY SOLUTION
     */
    public String toLowerCaseKREV(String s) {
        return s.toLowerCase();
    }

    /**
     * https://leetcode.com/problems/to-lower-case/solutions/2980573/beats-100-easy-approach/
     * idea - look at the table https://www.asciitable.com/
     */
    public static String toLowerCase(String s) {
        char[] sArr = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            //change inline
            if (sArr[i] >= 65 && sArr[i] <= 90) {
                sArr[i] = (char) (sArr[i] + 32);
            }
        }
        return new String(sArr);
    }

    public static void main(String[] args) {
       String s = "frekreAABBARKfrkrA";
        System.out.println(toLowerCase(s));
    }

}
