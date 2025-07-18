package solving_techniques.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 118. Pascal's Triangle (easy)
 * https://leetcode.com/problems/pascals-triangle/
 * <p>
 * #Company (17.07.2025): 0 - 3 months Google 11 Bloomberg 6 Meta 5 Amazon 5 Microsoft 2 Goldman Sachs 2 Zoho 2 6 months ago Apple 22 Adobe 8 Yahoo 6 Uber 5 Accenture 4 tcs 3 Mitsogo 3 Virtusa 3 Deloitte 2 Wix 2
 * <p>
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 * <p>
 * Example 1:
 * <p>
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <p>
 * Example 2:
 * Input: numRows = 1
 * Output: [[1]]
 * <p>
 * Constraints:
 * 1 <= numRows <= 30
 */
public class PascalTriangle {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 83%
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        result.add(Arrays.asList(1));

        for (int i = 1; i < numRows; i++) {
            List<Integer> tempList = new ArrayList<>(i + 1);
            for (int j = 0; j < i + 1; j++) {
                List<Integer> prevList = result.get(i - 1);
                int prev1 = j - 1 >= 0 ? prevList.get(j - 1) : 0;
                int prev2 = j < prevList.size() ? prevList.get(j) : 0;
                tempList.add(prev1 + prev2);
            }
            result.add(tempList);
        }

        return result;
    }
}
