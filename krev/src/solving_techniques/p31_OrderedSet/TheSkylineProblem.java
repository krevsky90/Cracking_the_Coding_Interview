package solving_techniques.p31_OrderedSet;

import java.util.*;

/**
 * 218. The Skyline Problem (hard)
 * https://leetcode.com/problems/the-skyline-problem
 *
 * #Company: 0 - 3 months Uber 3 Amazon 2 0 - 6 months Google 5 Meta 3 Microsoft 2 Apple 2 X 2 Yelp 2 6 months ago TikTok 3 Salesforce 3 Goldman Sachs 2 Citadel 2
 *
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 *
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 *
 * Example 1:
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
 *
 * Example 2:
 * Input: buildings = [[0,2,3],[2,5,3]]
 * Output: [[0,3],[5,0]]
 *
 * Constraints:
 * 1 <= buildings.length <= 10^4
 * 0 <= lefti < righti <= 2^31 - 1
 * 1 <= heighti <= 2^31 - 1
 * buildings is sorted by lefti in non-decreasing order.
 *
 */
public class TheSkylineProblem {
    /**
     * NOT SOLVED by myself:
     * idea:
     * 1) store all positions which are start or end of any building
     * 2) sort stored positions and put into list
     * 3) create map: "list value -> index in the list"
     * 4) create heights array
     * 5) for each building:
     *      find start and end indices in the list (using Map)
     *      for found range of indices update heights-arr if height of current building > heights[i],
     *      BUT do NOT touch end index!
     * 6) save the answer based on heights-arr:
     *      if the last saved height != heights[i] => save pair [list.get(i), heights[i]]
     *
     * time ~ O(N^2)
     *      since O(N) - to build posSet, posToIndex,
     *      O(N*logN) - to sort list of posSets
     *      O(N*N) to traverse through all buildings and traverse between startIdx and endIdx
     *
     * space ~ O(N)
     *
     * 4 attempts:
     * - Collections.sort works only for list, NOT for Set
     * - need to compare last element of result . get(1), but not element itself
     * - need to add list of elements to result, but not as if it is key and value
     *
     * BEATS ~ 44%
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        Set<Integer> posSet = new HashSet<>();
        for (int[] b : buildings) {
            posSet.add(b[0]);
            posSet.add(b[1]);
        }

        List<Integer> list = new ArrayList<>(posSet);
        Collections.sort(list);

        Map<Integer, Integer> posToIndex = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            posToIndex.put(list.get(i), i);
        }

        int[] heights = new int[list.size()];

        //fill heights by info from each building
        //NOTE: we do NOT take into account height of the building in the position where this building ends!
        for (int[] b : buildings) {
            //traverse through the list of incides of height-array which are covered by this building
            //and change the height on these positions if it is less than height of this current building
            int start = b[0];
            int end = b[1];
            int h = b[2];
            int startIdx = posToIndex.get(start);
            int endIdx = posToIndex.get(end);

            for (int idx = startIdx; idx < endIdx; idx++) {
                heights[idx] = Math.max(heights[idx], h);
            }
        }

        //save the resuled points based on heights-array
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < heights.length; i++) {
            if (result.isEmpty() || result.get(result.size() - 1).get(1) != heights[i]) {
                result.add(Arrays.asList(list.get(i), heights[i]));
            }
        }

        return result;
    }

    //todo: can be solved in time ~ O(N*logN) if we store PriorityQueue of buildings (max heap)
    // for each position we add buildings which starts here and remove which ends
    // save heights of the highest building that is in PQ at that moment
}
