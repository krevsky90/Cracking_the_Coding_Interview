package solving_techniques.different;

/**
 * 1249. Minimum Remove to Make Valid Parentheses (medium)
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses
 * <p>
 * info:
 * https://www.youtube.com/watch?v=q_nOlpy_VSo&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=37
 * <p>
 * Facebook's #1 interview question as of February 2022 !!
 * <p>
 * #Company: Amazon Bloomberg Facebook
 * <p>
 * Given a string s of '(' , ')' and lowercase English characters.
 * <p>
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 * so that the resulting parentheses string is valid and return any valid string.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * <p>
 * Example 1:
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * <p>
 * Example 2:
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * <p>
 * Example 3:
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is either '(' , ')', or lowercase English letter.
 */
public class MinimumRemovedToMakeValidParenthesis {
    public static void main(String[] args) {
        String s1 = "lee(t(c)o)de)";
        String s2 = "a)b(c)d";

        MinimumRemovedToMakeValidParenthesis obj = new MinimumRemovedToMakeValidParenthesis();
        System.out.println(obj.minRemoveToMakeValid(s1));   //expected "lee(t(c)o)de"
        System.out.println(obj.minRemoveToMakeValid(s2));   //expected "ab(c)d"
    }

    /**
     * info:
     * https://www.youtube.com/watch?v=q_nOlpy_VSo&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=37
     * idea:
     * 1) use l and r counters for opened and closed parenthesis
     * traverse through the string s and add ")" only if l > r. otherwise - skip ")"
     * 2) to handle the case when l > r, we need traverse back and remove "(", while l > r
     * <p>
     * time to implement ~ 20 mins
     * time ~ O(n)
     * space ~ O(n)
     *
     * 1 attempt:
     *
     * BEATS ~ 67%
     */
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb1 = new StringBuilder();
        int left = 0;
        int right = 0;
        char[] sArr = s.toCharArray();
        for (char c : sArr) {
            if (c == '(') {
                left++;
                sb1.append(c);
            } else if (c == ')') {
                if (left > right) {
                    sb1.append(c);
                    right++;
                }
            } else {
                sb1.append(c);
            }
        }

        if (left == right) return sb1.toString();

        //2
        // traverse back (since we can't remove elements from middle to sb1,
        // so we need to traverse and append/not append j-th element to the new sb)
        StringBuilder sb2 = new StringBuilder();
        for (int j = sb1.length() - 1; j >= 0; j--) {
            if (sb1.charAt(j) == '(' && left > right) {
                left--;
            } else {
                sb2.append(sb1.charAt(j));
            }
        }

        return sb2.reverse().toString();
    }
}
