package solving_techniques.different;

import java.util.*;

/**
 * 2013. Detect Squares (medium)
 * https://leetcode.com/problems/detect-squares
 *
 * #Company (8.04.2025): 0 - 3 months Google 2 Meta 2 6 months ago Amazon 2
 *
 * You are given a stream of points on the X-Y plane. Design an algorithm that:
 *
 * Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
 * Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
 * An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.
 *
 * Implement the DetectSquares class:
 *
 * DetectSquares() Initializes the object with an empty data structure.
 * void add(int[] point) Adds a new point point = [x, y] to the data structure.
 * int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
 *
 *
 * Example 1:
 * Input
 * ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 * [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
 * Output
 * [null, null, null, null, 1, 0, null, 2]
 *
 * Explanation
 * DetectSquares detectSquares = new DetectSquares();
 * detectSquares.add([3, 10]);
 * detectSquares.add([11, 2]);
 * detectSquares.add([3, 2]);
 * detectSquares.count([11, 10]); // return 1. You can choose:
 *                                //   - The first, second, and third points
 * detectSquares.count([14, 8]);  // return 0. The query point cannot form a square with any points in the data structure.
 * detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
 * detectSquares.count([11, 10]); // return 2. You can choose:
 *                                //   - The first, second, and third points
 *                                //   - The first, third, and fourth points
 *
 *
 * Constraints:
 * point.length == 2
 * 0 <= x, y <= 1000
 * At most 3000 calls in total will be made to add and count.
 */
public class DetectSquares {
    /**
     * Follow-up:
     * in Google this problem does not have condition like 'axis-aligned'
     * so we can use Math to calculate coordinates of 3 and 4 points
     * in cae is we consider that we have coordinates of 2 diagonal points (x1, y1) and (x2, y2),
     * then we can find middle point. then - vector from middle to, say, (x2, y2)
     * then rotate this vector, since rotated (a, b) is (-b, a)
     * then calculate
     * (x3, y3) = ((x1 + x2)/2 + (y1 - y2)/2, (y1 + y2)/2 + (x2 - x1)/2)
     * (x4, y4) = ((x1 + x2)/2 - (y1 - y2)/2, (y1 + y2)/2 - (x2 - x1)/2)
     *
     */
    public static void main(String[] args) {
        KrevskySolution detectSquares = new KrevskySolution();
        //[3,10]],[[11,2]],[[3,2]],[[11,10]],[[14,8]],[[11,2]],[[11,10]]]
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        System.out.println(detectSquares.count(new int[]{11, 10}));
        System.out.println(detectSquares.count(new int[]{14, 8}));
        detectSquares.add(new int[]{11, 2});
        System.out.println(detectSquares.count(new int[]{11, 10}));
    }

    /**
     * KREVSKY SOLUTION = Idea is like Official solution
     *
     * time to solve ~ 42 mins
     * time ~ O(n)
     * space ~ O(n)
     *
     * about 4 attempts:
     * - need to store Set of unique points, not just list
     * - need to check if pair = point (i.e. if dist > 0)
     *
     * BEATS ~ 70%     *
     */
    static class KrevskySolution {
        private Map<List<Integer>, Integer> map;
        private Map<Integer, Set<List<Integer>>> xToPoints;

        public KrevskySolution() {
            map = new HashMap<>();
            xToPoints = new HashMap<>();
        }

        public void add(int[] point) {
            List<Integer> keyList = Arrays.asList(point[0], point[1]);
            map.put(keyList, map.getOrDefault(keyList, 0) + 1);
            xToPoints.putIfAbsent(point[0], new HashSet<>());
            xToPoints.get(point[0]).add(keyList);
        }

        public int count(int[] point) {
            int x = point[0];
            int y = point[1];
            Set<List<Integer>> pairs = xToPoints.getOrDefault(x, Collections.emptySet());

            int res = 0;

            for (List<Integer> pair : pairs) {
                int dist = Math.abs(pair.get(1) - y);
                if (dist > 0) {
                    int cnt2 = map.get(pair);

                    //form square where "(x,y) - pair" is right bound
                    int cnt3 = map.getOrDefault(Arrays.asList(x - dist, Math.max(y, pair.get(1))), 0);
                    int cnt4 = map.getOrDefault(Arrays.asList(x - dist, Math.min(y, pair.get(1))), 0);

                    res += cnt2 * cnt3 * cnt4;

                    //form square where "(x,y) - pair" is left bound
                    cnt3 = map.getOrDefault(Arrays.asList(x + dist, Math.max(y, pair.get(1))), 0);
                    cnt4 = map.getOrDefault(Arrays.asList(x + dist, Math.min(y, pair.get(1))), 0);

                    res += cnt2 * cnt3 * cnt4;
                }
            }

            return res;
        }
    }

    /**
     * Solution #2: based pn diagonal, not vertical bound
     * idea: imagine that pair and point are diagonal of square. In this case deltaX = deltaY and point != pair
     * basing on that let's try to find the rest points
     *
     * BEATS ~ 51%
     */
    static class OptimalSolution {
        private Map<List<Integer>, Integer> map;
        private Set<int[]> points; //unique points

        public OptimalSolution() {
            map = new HashMap<>();
            points = new HashSet<>();
        }

        public void add(int[] point) {
            List<Integer> keyList = Arrays.asList(point[0], point[1]);
            if (!map.containsKey(keyList)) {
                points.add(point);
            }
            map.put(keyList, map.getOrDefault(keyList, 0) + 1);
        }

        public int count(int[] point) {
            int res = 0;

            for (int[] pair : points) {
                //imagine that pair and point are diagonal of square. In this case deltaX = deltaY and point != pair
                if (pair[0] != point[0] && pair[1] != point[1] && Math.abs(pair[0] - point[0]) == Math.abs(pair[1] - point[1])) {
                    //basing on that let's try to find the rest points
                    List<Integer> key2 = Arrays.asList(pair[0], pair[1]);
                    List<Integer> key3 = Arrays.asList(pair[0], point[1]);
                    List<Integer> key4 = Arrays.asList(point[0], pair[1]);
                    res += map.getOrDefault(key2, 0) * map.getOrDefault(key3, 0) * map.getOrDefault(key4, 0);
                }
            }

            return res;
        }
    }


}
