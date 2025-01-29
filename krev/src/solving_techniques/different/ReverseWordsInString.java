package solving_techniques.different;

import java.util.*;

/**
 * 151. Reverse Words in a String (medium)
 * https://leetcode.com/problems/reverse-words-in-a-string
 * <p>
 * #Company (29.01.2025): 0 - 3 months Amazon 9 Google 6 Meta 3 Microsoft 2 Bloomberg 2 Accenture 2 0 - 6 months Apple 4 tcs 3 Nvidia 3 Infosys 2 Zoho 2 6 months ago Adobe 14 TikTok 7 Uber 5 Visa 3 Intel 3 Morgan Stanley 2 Walmart Labs 2 Goldman Sachs 2 Arista Networks 2 Yandex 2
 * <p>
 * Given an input string s, reverse the order of the words.
 * <p>
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * <p>
 * Return a string of the words in reverse order concatenated by a single space.
 * <p>
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 * <p>
 * Example 1:
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * <p>
 * Example 2:
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * <p>
 * Example 3:
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^4
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 * <p>
 * <p>
 * Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 */
public class ReverseWordsInString {
    /**
     * KREVSKY SOLUTION #1:
     * idea: 2 pointers
     * time to solve ~ 25 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 99%
     */
    public String reverseWords(String s) {
        List<String> buff = new ArrayList<>();
        int p1 = s.length() - 1;
        int p2 = s.length() - 1;

        char[] arr = s.toCharArray();
        while (p1 >= 0) {
            if (arr[p2] == ' ') {
                p1--;
                p2--;
                continue;
            }

            if (p1 == 0 || arr[p1 - 1] == ' ') {
                buff.add(s.substring(p1, p2 + 1));
                p1--;
                p2 = p1;
            } else {
                p1--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buff.size(); i++) {
            sb.append(buff.get(i));
            if (i < buff.size() - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    /**
     * KREVSKY SOLUTION #2:
     * idea: use split method
     * BUT be careful! it can create empty string elements!
     * <p>
     * time to solve ~ 5 mins
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * BEATS ~ 93%
     */
    public String reverseWords2(String s) {
        String[] buff = s.split(" ");
        int n = buff.length;
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            if (!"".equals(buff[i])) {
                sb.append(buff[i]).append(" ");
            }
        }

        return sb.substring(0, sb.length() - 1);
    }

    /**
     * Official solution like KREVSKY #2
     */
    public String reverseWords2Official(String s) {
        // remove leading spaces
        s = s.trim();
        // split by multiple spaces
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    /**
     * Approach 3: Reverse the Whole String and Then Reverse Each Word
     */

    /**
     * Approach #4: deque of words
     */
    public String reverseWords4(String s) {
        int left = 0, right = s.length() - 1;
        // remove leading spaces
        while (left <= right && s.charAt(left) == ' ') ++left;

        // remove trailing spaces
        while (left <= right && s.charAt(right) == ' ') --right;

        Deque<String> d = new ArrayDeque();
        StringBuilder word = new StringBuilder();
        // push word by word in front of deque
        while (left <= right) {
            char c = s.charAt(left);

            if ((word.length() != 0) && (c == ' ')) {
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);
    }
}
