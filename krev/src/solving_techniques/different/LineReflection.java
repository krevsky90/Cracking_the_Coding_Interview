package solving_techniques.different;

import java.util.*;

/**
 *  https://leetcode.com/problems/line-reflection/ (medium) (blocked)
 *  took source from here https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0356.Line%20Reflection/README_EN.md
 *
 *  #Company: Yandex
 *
 *  Given n points on a 2D plane, find if there is such a line parallel to the y-axis that reflects the given points symmetrically.
 *  In other words, answer whether or not if there exists a line that after reflecting all points over the given line,
 *  the original points' set is the same as the reflected ones.
 *  Note that there can be repeated points.
 *
 * Example #1:
 * Input: points = [[1,1],[-1,1]]
 * Output: true
 * Explanation: We can choose the line x = 0.
 *
 * Example 2:
 * Input: points = [[1,1],[-1,-1]]
 * Output: false
 * Explanation: We can't choose a line.
 *
 * Constraints:
 * n == points.length
 * 1 <= n <= 10^4
 * -10^8 <= points[i][j] <= 10^8
 *
 * Follow up: Could you do better than O(n^2)?
 */
public class LineReflection {
    public static void main(String[] args) {
        LineReflection obj = new LineReflection();
        int[][] points1 = {{0,1},{-1,1},{1,1},{2,1}};
        System.out.println(obj.check(points1));
        System.out.println(obj.checkKrev(points1));

    }

    /**
     * ORIGINAL SOLUTION:
     * https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0356.Line%20Reflection/README_EN.md
     * idea:
     * 1) find minX, maxX
     * 2) each pair of symmetric points should have the same (x1 + x2)/2 => the same (x1 + x2) =>
     * => each (x1 + x2) = (minX + maxX) => we can calculate S = minX + maxX and then check for each point (x,y):
     * if (setOfPoints.contains(S - x, y))
     *
     * time ~ O(n)
     */
    public boolean check(int[][] points) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        Set<List<Integer>> setOfPoints = new HashSet<>();
        for (int[] p : points) {
            minX = Math.min(minX, p[0]);
            maxX = Math.max(maxX, p[0]);
            setOfPoints.add(Arrays.asList(p[0], p[1]));
        }

        int sum = minX + maxX;
        for (int[] p : points) {
            if (!setOfPoints.contains(Arrays.asList(sum - p[0], p[1]))) {
                return false;
            }
        }
        return true;
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 25 mins
     * time ~ O(n) + O(nlogn) ! O(nLogn)
     *
     * 2 attempts:
     * - forgot left++, right--
     */
    public boolean checkKrev(int[][] points) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.putIfAbsent(points[i][1], new ArrayList<>());
            map.get(points[i][1]).add(points[i][0]);
        }

        Double globalMid = null;
        for (List<Integer> tempList : map.values()) {
            //sort	n*logn
            Collections.sort(tempList);

            int left = 0;
            int right = tempList.size() - 1;
            Double midValue = null;
            while (left <= right) {
                double mid = (tempList.get(left) + tempList.get(right)) / 2.0;
                if (midValue != null) {
                    if (midValue.doubleValue() != mid) {
                        return false;
                    }
                } else {
                    midValue = mid;
                }
                left++;
                right--;
            }

            if (globalMid == null) {
                globalMid = midValue;
            } else {
                if (globalMid.doubleValue() != midValue.doubleValue()) {
                    return false;
                }
            }
        }

        return true;
    }
}
