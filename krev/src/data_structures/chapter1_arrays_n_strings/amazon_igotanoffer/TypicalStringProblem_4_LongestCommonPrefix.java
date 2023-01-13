package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings
 * If there is no common prefix, return an empty string "".
 * <p>
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * <p>
 * Example 2:
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
public class TypicalStringProblem_4_LongestCommonPrefix {
    public String longestCommonPrefixKREV(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String s = strs[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean cExists = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != c) {
                    cExists = false;
                    break;
                }
            }

            if (cExists) {
                sb.append(c);
            } else {
                return sb.toString();
            }
        }

        return sb.toString();
    }

    //https://leetcode.com/problems/longest-common-prefix/solutions/2748936/simple-java-solution/
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }

        }
        return prefix;


    }
}
