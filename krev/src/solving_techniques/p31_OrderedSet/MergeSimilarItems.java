package solving_techniques.p31_OrderedSet;

import java.util.*;

/**
 * 2363. Merge Similar Items (easy)
 * https://leetcode.com/problems/merge-similar-items
 * <p>
 * You are given two 2D integer arrays, items1 and items2, representing two sets of items.
 * Each array items has the following properties:
 * <p>
 * items[i] = [valuei, weighti] where valuei represents the value and weighti represents the weight of the ith item.
 * The value of each item in items is unique.
 * Return a 2D integer array ret where ret[i] = [valuei, weighti],
 * with weighti being the sum of weights of all items with value valuei.
 * <p>
 * Note: ret should be returned in ascending order by value.
 * <p>
 * Example 1:
 * Input: items1 = [[1,1],[4,5],[3,8]], items2 = [[3,1],[1,5]]
 * Output: [[1,6],[3,9],[4,5]]
 * Explanation:
 * The item with value = 1 occurs in items1 with weight = 1 and in items2 with weight = 5, total weight = 1 + 5 = 6.
 * The item with value = 3 occurs in items1 with weight = 8 and in items2 with weight = 1, total weight = 8 + 1 = 9.
 * The item with value = 4 occurs in items1 with weight = 5, total weight = 5.
 * Therefore, we return [[1,6],[3,9],[4,5]].
 * <p>
 * Example 2:
 * Input: items1 = [[1,1],[3,2],[2,3]], items2 = [[2,1],[3,2],[1,3]]
 * Output: [[1,4],[2,4],[3,4]]
 * Explanation:
 * The item with value = 1 occurs in items1 with weight = 1 and in items2 with weight = 3, total weight = 1 + 3 = 4.
 * The item with value = 2 occurs in items1 with weight = 3 and in items2 with weight = 1, total weight = 3 + 1 = 4.
 * The item with value = 3 occurs in items1 with weight = 2 and in items2 with weight = 2, total weight = 2 + 2 = 4.
 * Therefore, we return [[1,4],[2,4],[3,4]].
 * <p>
 * Example 3:
 * Input: items1 = [[1,3],[2,2]], items2 = [[7,1],[2,2],[1,4]]
 * Output: [[1,7],[2,4],[7,1]]
 * Explanation:
 * The item with value = 1 occurs in items1 with weight = 3 and in items2 with weight = 4, total weight = 3 + 4 = 7.
 * The item with value = 2 occurs in items1 with weight = 2 and in items2 with weight = 2, total weight = 2 + 2 = 4.
 * The item with value = 7 occurs in items2 with weight = 1, total weight = 1.
 * Therefore, we return [[1,7],[2,4],[7,1]].
 * <p>
 * Constraints:
 * 1 <= items1.length, items2.length <= 1000
 * items1[i].length == items2[i].length == 2
 * 1 <= valuei, weighti <= 1000
 * Each valuei in items1 is unique.
 * Each valuei in items2 is unique.
 */
public class MergeSimilarItems {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        /**
         * KREVSKY SOLUTION:
         * idea: use TreeMap to use for-each loop traversing through sorted (by key) map
         *
         * time to solve ~ 8 mins
         *
         * time ~ O(n1*logn1) + O(n2*logn2) + O((n1 + n2)
         *
         * 3 attempts:
         * - syntax error
         * - put "+1" instead of "itemsX[i][1]"
         *
         * BEATS = 49%
         */
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < items1.length; i++) {
            if (map.containsKey(items1[i][0])) {
                map.put(items1[i][0], map.get(items1[i][0]) + items1[i][1]);
            } else {
                map.put(items1[i][0], items1[i][1]);
            }
        }

        for (int i = 0; i < items2.length; i++) {
            if (map.containsKey(items2[i][0])) {
                map.put(items2[i][0], map.get(items2[i][0]) + items2[i][1]);
            } else {
                map.put(items2[i][0], items2[i][1]);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            result.add(Arrays.asList(e.getKey(), e.getValue()));
        }

        return result;
    }
}
