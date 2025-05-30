package solving_techniques.different;

import java.util.ArrayList;
import java.util.List;

/**
 * 6. Zigzag Conversion (medium)
 * https://leetcode.com/problems/zigzag-conversion
 * <p>
 * #Company (30.05.2025): 0 - 3 months Google 9 Amazon 5 Meta 4 Oracle 2 Zopsmart 2 Salesforce 2 0 - 6 months Microsoft 3 Bloomberg 2 PayPal 2 PayPay 2 6 months ago Zoho 9 Adobe 8 Apple 7 Uber 7 ServiceNow 5 Yahoo 5 Microstrategy 5 carwale 4 ConsultAdd 4 BitGo 3
 * <p>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 * <p>
 * Input: s = "A", numRows = 1
 * Output: "A"
 * <p>
 * Constraints:
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 1 <= numRows <= 1000
 */
public class ZigzagConversion {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 18 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 3 attempts:
     * - incorrect condition p < numRows - 1 instead of p == numRows - 1
     * - missed edge case: numRows = 1
     * <p>
     * BEATS ~ 60%
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> lists = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            lists.add(new StringBuilder());
        }

        int p = 0;
        int direction = 1;
        for (int i = 0; i < s.length(); i++) {
            lists.get(p).append(s.charAt(i));
            if (p < numRows - 1 && direction == 1) {
                p += direction;
            } else if (p == numRows - 1 && direction == 1) {
                direction *= -1;
                p += direction;
            } else if (p > 0 && direction == -1) {
                p += direction;
            } else if (p == 0 && direction == -1) {
                direction *= -1;
                p += direction;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (StringBuilder tempSb : lists) {
            sb.append(tempSb);
        }
        return sb.toString();
    }

    /**
     * optimized!
     * BE CAREFUL WITH direction which should be -1 initially!
     */
    public String convertOptimized(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> lists = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            lists.add(new StringBuilder());
        }

        int p = 0;
        int direction = -1;
        for (int i = 0; i < s.length(); i++) {
            lists.get(p).append(s.charAt(i));
            if (p == 0 || p == numRows - 1) {
                direction *= -1;
            }

            p += direction;
        }

        StringBuilder sb = new StringBuilder();
        for (StringBuilder tempSb : lists) {
            sb.append(tempSb);
        }
        return sb.toString();
    }
}
