package solving_techniques.p26_backtracking;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63d4fdb80c7ff248cdce8312
 *
 * Numbers can be regarded as the product of their factors.
 * For example, 8 = 2 x 2 x 2 = 2 x 4.
 * Given an integer n, return all possible combinations of its factors.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: n = 8
 * Output: [[2, 2, 2], [2, 4]]
 *
 * Example 2:
 * Input: n = 20
 * Output: [[2, 2, 5], [2, 10], [4, 5]]
 */
public class FactorCombinations {

    /**
     * n = 210
     *
     * 2*3*5*7 - C^4_4 = 1 combination
     * 6*5*7, 10*3*7, 14*3*5, 2*15*7, 2*5*21, 2*3*35 - C^2_4 = 6 combinations
     * 2*105, 3*70, 5*42, 7*30 - C^3_4 = 4 combinations
     *
     */
    public static void main(String[] args) {
        FactorCombinations obj = new FactorCombinations();

        int n1 = 8;
        List<List<Integer>> res1 = obj.factorCombinations(n1);
        for (List list : res1) {
            System.out.println(Arrays.toString(list.toArray()));
        }
        System.out.println("===========");
        int n2 = 20;
        List<List<Integer>> res2 = obj.factorCombinations(n2);
        for (List list : res2) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

    /**
     * NOT SOLVED by myself
     * idea: https://www.youtube.com/watch?v=uMQnEsgKKOU
     * 1) just enumerate of all potentially reasonable factors (from 2 to ...see the solution)
     * 2) calculate product and use it for stop criteria, rather than change n itself
     * 3) propagate 'initFactor' to avoid of duplicate combinations
     *
     * time ~ O(n!)
     */
    public List<List<Integer>> factorCombinations(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int product = 1;
        int initFactor = 2;
        backTracking(n, product, initFactor, temp, result);

        return result;
    }

    private void backTracking(int n, int product, int initFactor, List<Integer> temp, List<List<Integer>> result) {
        if (product > n) return;

        if (product == n) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = initFactor; i <= Math.floor(n/product); i++) {
            //need to check 'i != n' since when product = 1, i can reach n => we will get incorrect combination from the only one number: {n}
            if (n % i == 0 && i != n) {
                temp.add(i);
                backTracking(n, product*i, i, temp, result);  //good idea not to change n itself, but change the product!
                temp.remove(temp.size()  - 1);
            }
        }
    }

}
