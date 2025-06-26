package solving_techniques.bucketSort;

import java.util.ArrayList;
import java.util.List;

/**
 * 274. H-Index (medium)
 * https://leetcode.com/problems/h-index
 * <p>
 * #Company (26.06.2025): 0 - 3 months Microsoft 2 0 - 6 months Google 2 Bloomberg 2 6 months ago Amazon 18 Meta 5 Adobe 5 Nvidia 4 Apple 3 Uber 3 Yahoo 3 LinkedIn 2 ByteDance 2 Zoox 2
 * <p>
 * Given an array of integers citations where citations[i] is the number of citations a researcher
 * received for their ith paper, return the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia:
 * The h-index is defined as the maximum value of h such that the given researcher has published
 * at least h papers that have each been cited at least h times.
 * <p>
 * Example 1:
 * <p>
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
 * <p>
 * Example 2:
 * Input: citations = [1,3,1]
 * Output: 1
 * <p>
 * Constraints:
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 */
public class HIndex {
    /**
     * KRESKY SOLUTION:
     * idea - bucket sort (since size of 2D array is not so large
     * <p>
     * time to solve ~ 10 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 68%
     * <p>
     * NOTE: binary search is better - see separate file!
     */
    public int hIndex(int[] citations) {
        int max = -1;
        for (int i = 0; i < citations.length; i++) {
            if (max < citations[i]) max = citations[i];
        }

        List<List<Integer>> data = new ArrayList<>(max + 1);
        for (int r = 0; r < max + 1; r++) {
            data.add(new ArrayList<>());
        }

        for (int i = 0; i < citations.length; i++) {
            data.get(citations[i]).add(i);
        }

        int level = data.size() - 1;
        int amount = 0;
        while (level >= amount) {
            amount += data.get(level).size();
            level--;
        }

        return level + 1;
    }
}
