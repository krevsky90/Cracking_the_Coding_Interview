package solving_techniques.different;

/**
 * https://www.tryexponent.com/practice/prepare/bracket-match
 *
 * A string of brackets is considered correctly matched if every opening bracket in the string can be paired up with a later closing bracket,
 * and vice versa. For instance, “(())()” is correctly matched, whereas “)(“ and “((” aren’t.
 * For instance, “((” could become correctly matched by adding two closing brackets at the end, so you’d return 2.
 *
 * Given a string that consists of brackets, write a function bracketMatch that takes a bracket string as an input
 *      and returns the minimum number of brackets you’d need to add to the input in order to make it correctly matched.
 *
 * Explain the correctness of your code, and analyze its time and space complexities.
 *
 * Examples:
 * input:  text = “(()”
 * output: 1
 *
 * input:  text = “(())”
 * output: 0
 *
 * input:  text = “())(”
 * output: 2
 */
public class BracketMatch {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     *
     * 1 attempt
     */
    int bracketMatch(String text) {
        int openCnt = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '(') {
                openCnt++;
            } else {
                //i.e. c = ')'
                if (openCnt > 0) {
                    openCnt--;
                }
            }
        }

        int closeCnt = 0;
        for (int i = text.length() - 1; i >= 0; i--) {
            char c = text.charAt(i);
            if (c == ')') {
                closeCnt++;
            } else {
                //i.e. c = '('
                if (closeCnt > 0) {
                    closeCnt--;
                }
            }
        }

        return openCnt + closeCnt;
    }
}
