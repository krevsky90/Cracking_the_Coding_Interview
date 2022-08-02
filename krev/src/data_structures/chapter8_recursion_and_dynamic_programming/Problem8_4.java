package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * p.147
 * 8.4 Power Set:
 * Write a method to return all subsets of a set.
 * Hints: #273, #290, #338, #354, #373
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_4 {
    public static void main(String[] args) {
        List<Integer> main = Arrays.asList(1, 2, 3);
        List<List<Integer>> allSets = getAllSubSets(main);

        ArrayList<Integer> main2 = new ArrayList<Integer>();
        main2.addAll(Arrays.asList(1, 2, 3));

//        ArrayList<ArrayList<Integer>> allSets2 = getSubSets(main2, 0);
        ArrayList<ArrayList<Integer>> allSets2 = getAllSetCombinatorial(main2);

        for (List<Integer> list : allSets) {
            String s = "";
            for (Integer i : list) {
                s += i;
            }
            System.out.println(s);
        }

        System.out.println("------------------");
        for (List<Integer> list : allSets2) {
            String s = "";
            for (Integer i : list) {
                s += i;
            }
            System.out.println(s);
        }
    }

    /**
     * KREVSKY SOLUTION BASED ON HINTS - start
     */
    public static List<List<Integer>> getAllSubSets(List<Integer> main) {
        List<List<Integer>> result = collectAllSubSets(main);
        return result;
    }

    public static List<List<Integer>> collectAllSubSets(List<Integer> main) {
        if (main.size() == 1) {
            return Arrays.asList(main);
        }

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> left = main.subList(0, main.size() - 1);
        List<List<Integer>> leftResult = collectAllSubSets(left);
        result.addAll(leftResult);

        List<Integer> right = Arrays.asList(main.get(main.size() - 1));
        result.add(right);

        //add new subsets created by concatenation right to all leftResult elements
        for (List<Integer> tempList : leftResult) {
            List<Integer> mergeList = new ArrayList<>(tempList.size() + 1);
            mergeList.addAll(tempList);
            mergeList.addAll(right);
            result.add(mergeList);
        }
        return result;
    }
    /**
     * KREVSKY SOLUTION BASED ON HINTS - end
     */

    /**
     * ORIGINAL SOLUTION
     * time ~ O(n*2^n), because we WHEN get allSubSets = getSubSets that contains 2^(n-1) elements we go through all these elements and execute addAll (that may cost up to n).
     * so we get n*2^(n-1) ~ n*2^n
     * space ~ O(n*2^n), because there are 2^n subsets each of them may contains up to n items
     * which is the best we can do.
     */
    /**
     * The idea:
     * What is the difference between the solution for n = 3 and the solution for n = 2?
     * Let's look at this more deeply:
     * P(2) = {}, {a1} {a2} {a1, a2}
     * P(3) = {}, {a1} {a2} {a3} {a1, a2} {a2, a3} {a1, a3} {a1, a2, a3}
     * The difference between these solutions is that P(2) is missing all the subsets containing a3.
     * To create P(3), we can simply clone the subsets in P(2) and add a3 to them (including empty subset {})
     */
    public static ArrayList<ArrayList<Integer>> getSubSets(ArrayList<Integer> set, int index) {
        ArrayList<ArrayList<Integer>> allSubSets;
        if (set.size() == index) {
            //base case - add empty set
            allSubSets = new ArrayList<>();
            allSubSets.add(new ArrayList<>());   //empty set
        } else {
            //like {} {a1] {a2} {a1 a2}
            allSubSets = getSubSets(set, index + 1);
            //like item = a3
            int item = set.get(index);
            ArrayList<ArrayList<Integer>> moreSubSets = new ArrayList<>();
            for (ArrayList<Integer> subSet : allSubSets) {
                ArrayList<Integer> newSubSet = new ArrayList<>();
                newSubSet.addAll(subSet);
                newSubSet.add(item);
                moreSubSets.add(newSubSet);
            }
            allSubSets.addAll(moreSubSets);
        }
        return allSubSets;
    }

    /**
     * KREVSKY COMBINATORIAL SOLUTION BASED ON ORIGINAL IDEA
     * Idea: since we know that there are 2^n combinations, we may present each of them as vector that contains 1s and 0s.
     * if i-th position = 1, then i-th element exists in this combination
     */
    public static ArrayList<ArrayList<Integer>> getAllSetCombinatorial(ArrayList<Integer> set) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int val = (1 << set.size()) - 1;    //i.e. like 1111 if set contains 4 elements (a1 a2 a3 a4)
        while (val > 0) {
            ArrayList<Integer> tempArr = new ArrayList<>(set.size());
            int temp = val;
            int i = 0;
            while (temp > 0) {
                if ((temp & 1) == 1) {
                    tempArr.add(set.get(i));
                }
                temp >>= 1;
                i++;
            }
            result.add(tempArr);
            val--;
        }
        return result;
    }

    /**
     * ORIGINAL COMBINATORIAL SOLUTION
     */
    public static ArrayList<ArrayList<Integer>> getSubsets2(ArrayList<Integer> set) {
        ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
        int max = 1 << set.size(); /* Compute 2^n */
        for (int k = 0; k < max; k++) {
            ArrayList<Integer> subset = convertIntToSet(k, set);
            allsubsets.add(subset);
        }
        return allsubsets;
    }

    protected static ArrayList<Integer> convertIntToSet(int x, ArrayList<Integer> set) {
        ArrayList<Integer> subset = new ArrayList<Integer>();
        int index = 0;
        for (int k = x; k > 0; k >>= 1) {
            if ((k & 1) == 1) {
                subset.add(set.get(index));
            }
            index++;
        }
        return subset;
    }
}
