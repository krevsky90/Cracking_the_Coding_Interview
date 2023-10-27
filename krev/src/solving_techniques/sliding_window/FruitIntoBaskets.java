package solving_techniques.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * 904. Fruit Into Baskets
 * https://leetcode.com/problems/fruit-into-baskets/submissions/
 *
 * THE SAME as src/solving_techniques/sliding_window/LongestSubstringLengthWithKDistinctChars.java
 */
public class FruitIntoBaskets {
    //[1,2,3,2,2]
    //i = 4
    //start = 1
    //Map: {[2,3],[3,1]}
    //result = 4

    /**
     * KREV solution
     * 1 attempt
     * 13 mins to write code
     * 3 mins to debug it
     */
    public int totalFruit(int[] fruits) {
        int result = 0;
        Map<Integer, Integer> typeToCounterMap = new HashMap<>();
        int numOfBaskets = 2;
        int start = 0;

        for (int i = 0; i < fruits.length; i++) {
            int curAmount = typeToCounterMap.getOrDefault(fruits[i], 0);
            typeToCounterMap.put(fruits[i], curAmount + 1);

            while (typeToCounterMap.size() > numOfBaskets) {
                int tempVal = typeToCounterMap.get(fruits[start]);
                if (tempVal > 1) {
                    typeToCounterMap.put(fruits[start], tempVal - 1);
                } else {
                    typeToCounterMap.remove(fruits[start]);
                }
                start++;
            }

            result = Math.max(result, i - start + 1);
        }

        return result;
    }
}
