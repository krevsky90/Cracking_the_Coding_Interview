package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * p.148
 * 8.11 Coins:
 * Given an infnite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and pennies (1 cent),
 * write code to calculate the number of ways of representing n cents.
 * Hints: #300, #324, #343, #380, #394
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_11 {
    public static void main(String[] args) {
        int n = 50;
        int result = counter(n);
        System.out.println(result);
        int resultOrigSimple = makeChange(n, 0);
        System.out.println(resultOrigSimple);
    }

    /**
     * KREVSKY SOLUTION:
     * итого: я решил задачу, но не исходную, а более сложную - посчитал не количество комбинаций, а нашел сами комбинации, а потом посчитал их кол-во
     */
    public static final int[] coins = new int[]{25, 10, 5, 1};
    public static final int clen = coins.length;

    public static final int counter(int n) {
        if (n <= 0) {
            return 0;
        }

        ArrayList<ArrayList<Integer>> info = new ArrayList<>();
        info.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        int index = 0;
        fillInfo(n, info, index);
        printInfo(info);
        return info.size() - 1; //because of row 43
    }

    protected static boolean fillInfo(int n, ArrayList<ArrayList<Integer>> info, int index) {
        if (index >= clen) {
            ArrayList<Integer> newLine = new ArrayList<>(info.get(info.size() - 1));
            info.add(newLine);
            return true;
        }

        int max = n / coins[index];
        for (int i = max; i >= 0; i--) {
            int currentLine = info.size() - 1;
            ArrayList<Integer> line = info.get(currentLine);
            line.add(index, i);
            boolean isRowFilled = fillInfo(n - i * coins[index], info, index + 1);

            if (isRowFilled) break;
        }

        return false;
    }

    private static void printInfo(ArrayList<ArrayList<Integer>> info) {
        for (int i = 0; i < info.size() - 1; i++) {
            String s = "";
            for (int j = 0; j < clen; j++) {
                s += coins[j] + "*" + info.get(i).get(j) + " + ";
            }
            System.out.println(s.substring(0, s.length() - 2));
        }
    }


    /**
     * ORIGINAL SOLUTION simple, but not-optimal
     */
    public static int makeChange(int amount, int index) {
        if (index >= clen - 1) return 1;    //last denomination

        int denomAmount = coins[index];
        int ways = 0;
        for (int i = 0; i * denomAmount <= amount; i++) {
            int amountRemaining = amount - i * denomAmount;
            ways += makeChange(amountRemaining, index + 1);
        }
        return ways;
    }

    /**
     * ORIGINAL SOLUTION optimized
     */
    public static int makeChange(int n) {
        int[][] map = new int[n + 1][clen];  //precomputed values
        return makeChange(n, 0, map);
    }

    protected static int makeChange(int amount, int index, int[][] map) {
        if (map[amount][index] > 0) {
            return map[amount][index];
        }

        if (index >= clen - 1) return 1;    //one denom remaining

        int denomAmount = coins[index];
        int ways = 0;
        for (int i = 0; i * denomAmount <= amount; i++) {
            int amountRemaining = amount - i * denomAmount;
            ways += makeChange(amountRemaining, index + 1);
        }
        map[amount][index] = ways;

        return ways;
    }
}