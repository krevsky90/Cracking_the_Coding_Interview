package solving_techniques.p28_MonotonicStack;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1762. Buildings With an Ocean View (medium) (locked)
 * https://leetcode.com/problems/buildings-with-an-ocean-view
 * OR
 * https://leetcode.ca/all/1762.html
 *
 * #Company (25.01.2025): 0 - 3 months Meta 46 6 months ago Amazon 4 Microsoft 2 TikTok 2
 *
 * There are n buildings in a line.
 * You are given an integer array heights of size n that represents the heights of the buildings in the line.
 *
 * The ocean is to the right of the buildings.
 * A building has an ocean view if the building can see the ocean without obstructions.
 * Formally, a building has an ocean view if all the buildings to its right have a smaller height.
 *
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 *
 * Example 1:
 * Input: heights = [4,2,3,1]
 * Output: [0,2,3]
 * Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
 *
 * Example 2:
 * Input: heights = [4,3,2,1]
 * Output: [0,1,2,3]
 * Explanation: All the buildings have an ocean view.
 *
 * Example 3:
 * Input: heights = [1,3,2,4]
 * Output: [3]
 * Explanation: Only building 3 has an ocean view.
 *
 * Example 4:
 * Input: heights = [2,2,2,2]
 * Output: [3]
 * Explanation: Buildings cannot see the ocean if there are buildings of the same height to its right.
 *
 * Constraints:
 * 1 <= heights.length <= 10^5
 * 1 <= heights[i] <= 10^9
 */
public class BuildingsWithOceanView {
    public static void main(String[] args) {
        int[] arr1 = {4,2,3,1};
        int[] arr2 = {4,3,2,1};
        int[] arr3 = {1,3,2,4};
        int[] arr4 = {2,2,2,2};

        BuildingsWithOceanView obj = new BuildingsWithOceanView();
        System.out.println(Arrays.asList(obj.findBuildings(arr1)).stream().map(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(Arrays.asList(obj.findBuildings(arr2)).stream().map(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(Arrays.asList(obj.findBuildings(arr3)).stream().map(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(Arrays.asList(obj.findBuildings(arr4)).stream().map(String::valueOf).collect(Collectors.joining(",")));

        System.out.println(Arrays.asList(obj.findBuildings2(arr1)).stream().map(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(Arrays.asList(obj.findBuildings2(arr2)).stream().map(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(Arrays.asList(obj.findBuildings2(arr3)).stream().map(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(Arrays.asList(obj.findBuildings2(arr4)).stream().map(String::valueOf).collect(Collectors.joining(",")));
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) use monotonic stack
     * 2) save (to list) indexes of the elements when stack becomes empty
     * 3) reverse this list and return
     * time to solve ~ 10 mins
     * time ~ O(n)
     * space ~ O(n)
     *
     * 1 attempt
     *
     * BEATS ~ 10%
     */
    public int[] findBuildings(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> resList = new ArrayList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                resList.add(i);
            }

            stack.add(heights[i]);
        }

        Collections.reverse(resList);
        int[] result = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            result[i] = resList.get(i);
        }

        return result;
    }

    /**
     * SOLUTION #2:
     * info:
     * https://www.youtube.com/watch?v=0UdQeXMjAFk&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=44
     * idea:
     * 1) store max height (maxH)
     * 2) if current h > maxH => add index to the result
     * time ~ O(n)
     * space ~ O(n) - since resList
     *
     * BEATS ~ 66%
     */
    public int[] findBuildings2(int[] heights) {
        int maxHeight = -1;
        List<Integer> resList = new ArrayList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > maxHeight) {
                maxHeight = heights[i];
                resList.add(i);
            }
        }

        Collections.reverse(resList);
        int[] result = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            result[i] = resList.get(i);
        }

        return result;
    }

}
