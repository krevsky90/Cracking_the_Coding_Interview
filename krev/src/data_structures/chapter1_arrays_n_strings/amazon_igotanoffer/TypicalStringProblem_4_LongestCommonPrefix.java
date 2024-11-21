package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer;

/**
 * 14. Longest Common Prefix (easy)
 * https://leetcode.com/problems/longest-common-prefix
 * <p>
 * #Company: Adobe Alibaba Amazon Apple Bloomberg Cisco Citrix Meta Google IBM IXL Microsoft Oracle Pinterest Quora Snapchat Splunk Twilio Twitter Visa Yahoo Yelp
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
    /**
     * KREVSKY 21.11.2024
     * time to solve ~ 9 mins
     * <p>
     * time ~ O(N*L1) where N = number of strings, L1 = length of 1st string
     * space ~ O(L1)
     *
     * 1 attempt
     * <p>
     * BEATS ~ 66%
     */
    public String longestCommonPrefix2(String[] strs) {
        int i = 0;
        char[] initialStrArr = strs[0].toCharArray();
        while (i < initialStrArr.length) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != initialStrArr[i]) {
                    return strs[0].substring(0, i);
                }
            }
            i++;
        }
        return strs[0];
    }

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
