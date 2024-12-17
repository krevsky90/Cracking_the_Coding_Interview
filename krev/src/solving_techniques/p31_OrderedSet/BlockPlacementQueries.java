package solving_techniques.p31_OrderedSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * 3161. Block Placement Queries (hard)
 * https://leetcode.com/problems/block-placement-queries
 *
 * #Company: 0 - 3 months Capital One 8 Uber 5 0 - 6 months Visa 2 Roblox 2 6 months ago Autodesk 2
 *
 * There exists an infinite number line, with its origin at 0 and extending towards the positive x-axis.
 *
 * You are given a 2D array queries, which contains two types of queries:
 *
 * For a query of type 1, queries[i] = [1, x]. Build an obstacle at distance x from the origin.
 * It is guaranteed that there is no obstacle at distance x when the query is asked.
 * For a query of type 2, queries[i] = [2, x, sz].
 * Check if it is possible to place a block of size sz anywhere in the range [0, x] on the line,
 *      such that the block entirely lies in the range [0, x]. A block cannot be placed if it intersects with any obstacle, but it may touch it. Note that you do not actually place the block. Queries are separate.
 * Return a boolean array results, where results[i] is true if you can place the block specified in the ith query of type 2,
 *      and false otherwise.
 *
 * Example 1:
 * Input: queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]
 * Output: [false,true,true]
 * Explanation:
 * For query 0, place an obstacle at x = 2. A block of size at most 2 can be placed before x = 3.
 *
 * Example 2:
 * Input: queries = [[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]
 * Output: [true,true,false]
 *
 * Explanation:
 * Place an obstacle at x = 7 for query 0. A block of size at most 7 can be placed before x = 7.
 * Place an obstacle at x = 2 for query 2. Now, a block of size at most 5 can be placed before x = 7, and a block of size at most 2 before x = 2.
 *
 * Constraints:
 * 1 <= queries.length <= 15 * 10^4
 * 2 <= queries[i].length <= 3
 * 1 <= queries[i][0] <= 2
 * 1 <= x, sz <= min(5 * 10^4, 3 * queries.length)
 * The input is generated such that for queries of type 1, no obstacle exists at distance x when the query is asked.
 * The input is generated such that there is at least one query of type 2.
 *
 */
public class BlockPlacementQueries {
    public static void main(String[] args) {
        BlockPlacementQueries obj = new BlockPlacementQueries();
        int[][] queries = {{1, 7}, {2, 7, 6}, {1, 2}, {2, 7, 5}, {2, 7, 6}};

        List<Boolean> res = obj.getResults(queries);
        System.out.println(res);
    }

    /**
     * NOT SOlVED by myself
     * default idea = use Segment Tree, but I don't know this approach
     * so I took this solution: https://leetcode.com/problems/block-placement-queries/solutions/5208073/java-segment-tree-beats-100/?envType=company&envId=uber&favoriteSlug=uber-thirty-days
     *
     * and finally got TLE (743/744)!
     * time to spend ~ 3h
     *
     * O(logN) to add obstacle, O(N) to check => total time ~ O(queries.length * N)
     * space ~ O(N) for both TreeSets
     *
     *
     */
    public List<Boolean> getResults(int[][] queries) {
        TreeSet<Integer> obstacles = new TreeSet<>();
        TreeSet<Interval> intervals = new TreeSet<>((a, b) -> {
            int diff = (b.end - b.start) - (a.end - a.start);
            // the longest interval is in the head ot TreeSet.
            // if length is the same => interval which has lower start position is closer to head
            // (this part does not matter in sense of sorting)
            return diff != 0 ? diff : a.start - b.start;
        });

        intervals.add(new Interval(0, Integer.MAX_VALUE));

        List<Boolean> result = new ArrayList<>();

        for (int[] q : queries) {
            if (q[0] == 1) {
                addObstacle(obstacles, intervals, q[1]);
            } else if (q[0] == 2) {
                boolean tempResult = check(obstacles, intervals, q[1], q[2]);
                result.add(tempResult);
            }
        }

        return result;
    }

    //time ~ O(N) - traversing
    private boolean check(TreeSet<Integer> obstacles, TreeSet<Interval> intervals, int x, int size) {
        // we can't find tail since we can't identify interval => just iterate through the set.
        // it is better to start from head (with the longest interval)
        Iterator<Interval> it = intervals.iterator();
        while (it.hasNext()) {
            Interval current = it.next();
            // and also check it interval's size is >= size
            // if not - stop iterating since the next intervals will be shorter => no need to check them
            if (current.end - current.start < size) return false;

            if (Math.min(current.end, x) - current.start >= size) return true;
        }

        return false;
    }

    // time ~ logN, where N - number of obstacles. btw number of intervals = N + 1
    private void addObstacle(TreeSet<Integer> obstacles, TreeSet<Interval> intervals, int x) {
        Integer lower = obstacles.lower(x);  //or floor(x)
        if (lower == null) lower = 0;

        Integer upper = obstacles.higher(x);  //or ceiling(x)
        if (upper == null) upper = Integer.MAX_VALUE;

        Interval toBeRemoved = new Interval(lower, upper);
        intervals.remove(toBeRemoved);
        //add 2 new intervals
        intervals.add(new Interval(lower, x));
        intervals.add(new Interval(x, upper));

        //add obstacle
        obstacles.add(x);
    }

    class Interval {
        int start;
        int end;

        Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }
}
