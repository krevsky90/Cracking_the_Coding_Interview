package solving_techniques.different;

import java.util.*;

/**
 * 1878. Get Biggest Three Rhombus Sums in a Grid (medium)
 * https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid
 * <p>
 * #Company: (24.12.2024) 0 - 3 months Uber 2 0 - 6 months Quora 2 6 months ago Capital One 2
 * <p>
 * You are given an m x n integer matrix grid
 * A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid.
 * The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell.
 * Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:
 * <p>
 * Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.
 * Return the biggest three distinct rhombus sums in the grid in descending order.
 * If there are less than three distinct values, return all of them.
 * <p>
 * Example 1:
 * Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
 * Output: [228,216,211]
 * Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
 * - Blue: 20 + 3 + 200 + 5 = 228
 * - Red: 200 + 2 + 10 + 4 = 216
 * - Green: 5 + 200 + 4 + 2 = 211
 * <p>
 * Example 2:
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [20,9,8]
 * Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
 * - Blue: 4 + 2 + 6 + 8 = 20
 * - Red: 9 (area 0 rhombus in the bottom right corner)
 * - Green: 8 (area 0 rhombus in the bottom middle)
 * <p>
 * Example 3:
 * Input: grid = [[7,7,7]]
 * Output: [7]
 * Explanation: All three possible rhombus sums are the same, so return [7].
 * <p>
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 10^5
 */
public class GetBiggestThreeRhombusSumsInGrid {
    /**
     * NOT SOLVED by myself
     * idea:
     * 1) calculate sum for ALL possible rhombus
     * 2) store sums in PriorityQueue ONLY if PQ does not contain this sum (i.e. all values in PQ are unique)
     * 3) hack: to calculate sum of rhombus, use left and right pointers, increment and decrement them according to the fact
     * if they reach bounds of particular rhombus or not
     * info:
     * <p>
     * time to spend ~ 1.5h
     * <p>
     * time ~ O(n*m*L*L), where L = Min(n,m)/2. since cal takes O(L) and while takes O(L)
     * space ~ O(1)
     * <p>
     * BEATS ~ 41%
     */
    public int[] getBiggestThree(int[][] grid) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();  //min heap
        int m = grid.length;
        int n = grid[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                //we can just expand rhombus with center in (initR, initC) until we become out of bounds
                int left = c;
                int right = c;
                int top = r;
                int bottom = r;
                while (0 <= left && right < n && 0 <= top && bottom < m) {
                    int sum = calc(grid, left, right, top, bottom);

                    //need to keep unique elements (even if we have 7,7,7,3 we need to keep 7,3 but not 7,7,7)
                    if (!pq.contains(sum)) pq.add(sum);
                    if (pq.size() > 3) pq.poll();

                    left--;
                    right++;
                    top--;
                    bottom++;
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        while (!pq.isEmpty()) {
            set.add(pq.poll());
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list, (a, b) -> b - a);

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private int calc(int[][] grid, int left, int right, int top, int bottom) {
        int sum = 0;

        int c1 = (left + right) / 2;
        int c2 = (left + right) / 2;
        boolean reachBounds = false;    //idea #3

        for (int t = top; t <= bottom; t++) {
            if (c1 == c2) {
                sum += grid[t][c2];
            } else {
                sum += grid[t][c1] + grid[t][c2];
            }

            if (!reachBounds) {
                //swap only once
                reachBounds = c1 == left;    //for example (or r == right)
            }

            if (!reachBounds) {
                c1--;
                c2++;
            } else {
                c1++;
                c2--;
            }
        }

        return sum;
    }

    /**
     * SOLUTION #2:
     * stupid but workable approach how to calculate sum
     * https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid/solutions/1238787/java-brute-force-clean-o-n-m-l-2/?envType=company&envId=uber&favoriteSlug=uber-three-months
     */
    public int[] getBiggestThree2(int[][] grid) {
        final int n = grid.length;
        final int m = grid[0].length;
        final Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                set.add(grid[i][j]);
                for (int L = 1; L <= 25; L++) {
                    final int curr = f(grid, L, i, j, n, m);
                    if (curr != (int) 1e9) {
                        set.add(curr);
                    }
                }
            }
        }
        return set.stream().sorted(Comparator.reverseOrder()).limit(3).mapToInt(Integer::intValue).toArray();
    }

    private static int f(int[][] g, int size, int i, int j, int n, int m) {
        if (i + size >= n || i - size < 0 || (j + 2 * size) >= m) {
            return (int) 1e9;
        }
        int sum = 0;
        for (int k = 1; k < size; k++) {
            sum += g[i - k][j + k];
            sum += g[i + k][j + k];
            sum += g[i - k][j + 2 * size - k];
            sum += g[i + k][j + 2 * size - k];
        }
        sum += g[i][j];
        sum += g[i][j + 2 * size];
        sum += g[i + size][j + size];
        sum += g[i - size][j + size];
        return sum;
    }

    /**
     * part of solution #3
     * info: https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid/solutions/1240592/java-readable-faster-than-100-memory-less-than-100-submissions/?envType=company&envId=uber&favoriteSlug=uber-three-months
     */
    int getSum(int[][] grid, int i, int j, int length) {
        if (length == 0) {
            return grid[i][j];
        }

        int sum = 0;
        // edge ab
        for (int it = 0; it <= length; it++) {
            sum = sum + grid[i + it][j + it];
        }

        // edge ad
        for (int it = 1; it <= length; it++) {
            sum = sum + grid[i + it][j - it];
        }

        // edge dc
        for (int it = 1; it <= length; it++) {
            sum = sum + grid[i + length + it][j - length + it];
        }

        // edge bc
        for (int it = 1; it < length; it++) {
            sum = sum + grid[i + length + it][j + length - it];
        }

        return sum;
    }

}
