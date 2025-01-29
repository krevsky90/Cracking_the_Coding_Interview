package solving_techniques.different;

/**
 * 186. Reverse Words in a String II (medium) (locked)
 * https://leetcode.com/problems/reverse-words-in-a-string-ii
 * <p>
 * #Company (29.01.2025): 0 - 6 months ServiceNow 3 6 months ago Microsoft 4 Amazon 4 Apple 4 Uber 2
 * <p>
 * Given a character array s, reverse the order of the words.
 * <p>
 * A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
 * <p>
 * Your code must solve the problem in-place, i.e. without allocating extra space.
 * <p>
 * Example 1:
 * Input: s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * <p>
 * Example 2:
 * Input: s = ["a"]
 * Output: ["a"]
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is an English letter (uppercase or lowercase), digit, or space ' '.
 * There is at least one word in s.
 * s does not contain leading or trailing spaces.
 * All the words in s are guaranteed to be separated by a single space.
 */
public class ReverseWordsInString2 {
    /**
     * KREVSKY SOLUTION
     * idea:
     * 1) reverse the whole string
     * 2) reverse the words separately
     * time to solve ~ 7 mins
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 46%
     */
    public void reverseWords(char[] s) {
        //reverse the whole string
        reverse(0, s.length - 1, s);

        //reverse all words separately
        int start = 0;
        for (int i = 0; i <= s.length; i++) {
            if (i == s.length) {
                reverse(start, i - 1, s);
                break;
            }

            if (s[i] == ' ') {
                reverse(start, i - 1, s);
                start = i + 1;
            }
        }
    }

    private void reverse(int i, int j, char[] s) {
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

}
