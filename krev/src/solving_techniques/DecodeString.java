package solving_techniques;

import java.util.Stack;

/**
 * 394. Decode String (medium)
 * https://leetcode.com/problems/decode-string
 * <p>
 * #Companies (26.04.2025): 0 - 3 months Tencent 11 Google 10 Meta 8 Bloomberg 8 Amazon 3 TikTok 3 Oracle 2 0 - 6 months Microsoft 4 Apple 3 Zoho 3 Goldman Sachs 2 Salesforce 2 Nutanix 2 6 months ago Adobe 7 Wix 5 Cisco 5 Huawei 4 Coupang 4 Mountblue 4 ByteDance 3 Walmart Labs 3 Uber 3 eBay 3
 * <p>
 * Given an encoded string, return its decoded string.
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there will not be input like 3a or 2[4].
 * <p>
 * The test cases are generated so that the length of the output will never exceed 105.
 * <p>
 * Example 1:
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * <p>
 * Example 2:
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * <p>
 * Example 3:
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * <p>
 * Constraints:
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */
public class DecodeString {
    /**
     * KREVSKY SOLUTION:
     * idea: use common iterator across recursion calls
     * <p>
     * time to solve ~ 25 mins
     * time ~ O(maxK * n), where n = s.length()
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - forgot to reset multi after joining subResult
     * <p>
     * BEATS ~ 100%
     */
    private int i = 0;

    public String decodeString(String s) {
        return buildString(s);
    }

    private String buildString(String s) {
        StringBuilder sb = new StringBuilder();
        int multi = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if ('a' <= c && c <= 'z') {
                sb.append(c);
                i++;
            } else if (Character.isDigit(c)) {
                multi = 10 * multi + (c - '0');
                i++;
            } else if (c == '[') {
                i++;
                String subResult = buildString(s);
                for (int j = 0; j < multi; j++) {
                    sb.append(subResult);
                }
                multi = 0;
            } else if (c == ']') {
                i++;
                break;
            }
        }

        return sb.toString();
    }

    /**
     * Official solution #2:
     * using Stack
     */
    public String decodeStringUsingStack(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                // push the number k to countStack
                countStack.push(k);
                // push the currentString to stringStack
                stringStack.push(currentString);
                // reset currentString and k
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = stringStack.pop();
                // decode currentK[currentString] by appending currentString k times
                for (int currentK = countStack.pop(); currentK > 0; currentK--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }
}
