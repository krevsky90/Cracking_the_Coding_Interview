package data_structures.chapter8_recursion_and_dynamic_programming;

/**
 * p.147
 * 8.5 Recursive Multiply: Write a recursive function to multiply two positive integers without using the
 * * operator. You can use addition, subtraction, and bit shifting, but you should minimize the number
 * of those operations.
 * Hints: # 166, #203, #227, #234, #246, #280
 *
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_5 {
    public static void main(String[] args) {
        int a = 7;
        int b = 5;
        int result = recursiveMultiplyOrig3(a, b);
        System.out.println(result);
    }
    /**
     * KREVSKY SOLUTION:
     * переводим b в двоичный вид и суммируем число a, сдвинутое на кол-во разрядов каждой из "1" из числа b.
     * По сути очень похоже не ORIGINAL SOLUTION 3
     * чтобы было меньше итераций, можем засунуть min(a,b) во 2й аргумент сетода, а max(a,b) - в первый
     */
    public static int recursiveMultiplyKREV(int a, int b) {
        int min = a > b ? b : a;
        int max = a > b ? a : b;
        return recursiveMultiplyHelperKrev(max, min);
    }

    public static int recursiveMultiplyHelperKrev(int maxN, int minN) {
        if (minN == 0) return 0;
        int p = 0;
        int temp = minN;
        while ((temp & 1) == 0) {
            p++;
            temp = temp >> 1;
        }
        int bnew = minN - (1 << p);
        int result = maxN << p;
        result += recursiveMultiplyHelperKrev(maxN, bnew);
        return result;
    }

    /**
     * ORIGINAL SOLUTION 2:
     * a*b = ((int)a/2)*b + (a - (int)(a/2))*b
     * to avoid extra calculation, we use memo[small] = small*big
     * PROBLEM: double call of recursiveMultiplyHelperOrig2
     */
    public static int recursiveMultiplyOrig2(int a, int b) {
        int min = a > b ? b : a;
        int max = a > b ? a : b;
        int[] memo = new int[min+1];
        return recursiveMultiplyHelperOrig2(min, max, memo);
    }

    public static int recursiveMultiplyHelperOrig2(int small, int big, int[] memo) {
        if (small == 0) return 0;
        if (small == 1) return big;
        if (memo[small] != 0) return memo[small];

        int s = small >> 1; //divide by 2
        int first = recursiveMultiplyHelperOrig2(s, big, memo);
        int second = first;
        if (small % 2 == 1) {
            second = recursiveMultiplyHelperOrig2(small - s, big, memo);
        }
        memo[small] = first + second;
        return memo[small];
    }
    /**
     * ORIGINAL SOLUTION 3:
     * if (a % 2 == 0) then a*b = 2*((int)a/2)*b
     * of (a % 2 == 1) then a*b = 2*((int)a/2)*b + b
     * We avoid double call of recursiveMultiplyHelperOrig3 -> we don't need to store result in memo. It stores in callstack of recursion
     *
     * time ~ O(log(small))
     *
     */
    public static int recursiveMultiplyOrig3(int a, int b) {
        int min = a > b ? b : a;
        int max = a > b ? a : b;
        return recursiveMultiplyHelperOrig3(min, max);
    }

    public static int recursiveMultiplyHelperOrig3(int small, int big) {
        if (small == 0) return 0;
        if (small == 1) return big;

        int s = small >> 1; //divide by 2
        int first = recursiveMultiplyHelperOrig3(s, big);
        if (small % 2 == 1) {
            return first + first + big;
        } else {
            return first + first;
        }
    }
}
