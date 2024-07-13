package solving_techniques.p2_TwoPointers;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/638fa28f5844e928cbefff88
 * OR
 * 844. Backspace String Compare
 * https://leetcode.com/problems/backspace-string-compare (easy)
 *
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors.
 * '#' means a backspace character.
 *
 * Note that after backspacing an empty text, the text will continue empty.
 *
 * Example 1:
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 *
 * Example 2:
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".
 *
 * Example 3:
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 *
 * Constraints:
 * 1 <= s.length, t.length <= 200
 * s and t only contain lowercase letters and '#' characters.
 *
 * Follow up: Can you solve it in O(n) time and O(1) space?
 */
public class ProblemChallenge2_ComparingStringsContainingBackspaces {
    /**
     * NOT SOLVED by myself, but the main idea was correct
     * idea: Traverse both the strings backwards.
     *      If one or more '#' are encountered, resolve and backspace the corresponding non-# characters in each string.
     *      This way we can simply compare the remaining non-# characters and if they don't match return false immediately.
     *
     * time to solve ~ 80 mins
     * a lot of attempts
     *
     * time ~ O(s.length() + t.length())
     * space ~ O(1)
     */
    public boolean backspaceCompare(String s, String t) {
        int i1 = s.length() - 1;
        int i2 = t.length() - 1;
        while (i1 >= 0 && i2 >= 0) {
            //1) find the position in 's' where i1-th symbol is not '#'
            i1 = getNonBackspacePosition(i1, s);

            //2) find the position in 't' where i2-th symbol is not '#'
            i2 = getNonBackspacePosition(i2, t);

            //3) compare
            if (i1 == -1 && i2 == -1) return true;
            if (i1 == -1 && i2 > -1 || i1 > -1 && i2 == -1) return false;
            if (s.charAt(i1) != t.charAt(i2)) return false;

            i1--;
            i2--;
        }

        //if i1 = -1 and i2 > -1, then we should check that t.substring(0,i2+1) is efficiently empty
        if (i1 == -1 && getNonBackspacePosition(i2, t) > -1) return false;
        //the same for symmetric situation
        if (i2 == -1 && getNonBackspacePosition(i1, s) > -1) return false;

        return true;
    }

    private int getNonBackspacePosition(int idx, String str) {
        int backspaceCounter = 0;
        while (idx >= 0 && str.charAt(idx) == '#' || backspaceCounter > 0) {
            if (idx >= 0 && str.charAt(idx) == '#') {
                backspaceCounter++;
            } else {
                backspaceCounter--;
            }

            idx--;
        }

        idx = Math.max(idx, -1);
        return idx;
    }
}
