package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_arrays;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://www.programmersought.com/article/8581479456/
 * <p>
 * Description
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on…
 * Find the minimum cost to paint all houses.
 * <p>
 * All costs are positive integers.
 * <p>
 * Example
 * Given costs = [[14,2,11],[11,14,5],[14,3,10]] return 10
 * <p>
 * house 0 is blue, house 1 is green, house 2 is blue, 2 + 5 + 3 = 10
 */
public class Problem2_9_Paint_House_Dynamic_programming {
    /**
     * ORIGINAL SOLUTION
     * I couldn't solve by myself
     */
    public int minCost(int[][] costs) {
        int[] tempMins = new int[3];    //stores min costs if i-1-th house has red, blue, green color correspondingly (tempMins[0], [1], [2]);

        //base case
        tempMins = costs[0];

        for (int i = 1; i < costs.length; i++) {
            int redMin = Math.min(tempMins[1], tempMins[2]) + costs[i][0];
            int blueMin = Math.min(tempMins[0], tempMins[2]) + costs[i][1];
            int greenMin = Math.min(tempMins[0], tempMins[1]) + costs[i][2];

            tempMins[0] = redMin;
            tempMins[1] = blueMin;
            tempMins[2] = greenMin;
        }

        return Math.min(tempMins[0], Math.min(tempMins[1], tempMins[2]));
    }
}
