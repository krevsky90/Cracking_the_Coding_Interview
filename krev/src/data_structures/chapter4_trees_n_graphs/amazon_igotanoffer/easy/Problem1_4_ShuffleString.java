package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.easy;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * https://leetcode.com/problems/shuffle-string/description/
 *
 * You are given a string s and an integer array indices of the same length. The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
 * Return the shuffled string.
 *
 * Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
 * Output: "leetcode"
 * Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
 */
public class Problem1_4_ShuffleString {
    public String restoreString(String s, int[] indices) {
        int n = indices.length;
        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            arr[indices[i]] = s.charAt(i);
        }

        return new String(arr);
    }
}
