package solving_techniques.different;

import java.util.Random;

/**
 * 470. Implement Rand10() Using Rand7() (medium)
 * https://leetcode.com/problems/implement-rand10-using-rand7/
 *
 * #Company: Yandex
 *
 * Given the API rand7() that generates a uniform random integer in the range [1, 7],
 *      write a function rand10() that generates a uniform random integer in the range [1, 10].
 * You can only call the API rand7(), and you shouldn't call any other API.
 * Please do not use a language's built-in random API.
 *
 * Each test case will have one internal argument n,
 *      the number of times that your implemented function rand10() will be called while testing.
 * Note that this is not an argument passed to rand10().
 *
 * Example 1:
 * Input: n = 1
 * Output: [2]
 *
 * Example 2:
 * Input: n = 2
 * Output: [2,8]
 *
 * Example 3:
 * Input: n = 3
 * Output: [3,8,10]
 *
 * Constraints:
 * 1 <= n <= 10^5
 *
 * Follow up:
 * What is the expected value for the number of calls to rand7() function?
 * Could you minimize the number of calls to rand7()?
 */
public class ImplementRand10UsingRand7 {
    /**
     * NOT SOLVED by myself
     * info:
     * https://prepfortech.io/leetcode-solutions/implement-rand10-using-rand7
     * https://leetcode.com/problems/implement-rand10-using-rand7/solutions/3832432/java-solution-simple-approach/
     * similar problems (but with different solutions... may be they are not so generic as the below ones)
     *
     * idea #1: (rand(n) - 1)*n + rand(n) = rand(n^2) - we can generate more numbers than we need
     * idea #2: we can cut the numbers that are not interesting for us
     *
     * time to spent ~ 60 mins
     * time ~ O(1)
     * space ~ O(1)
     *
     * 1 attempt
     */

    /**
     * BEATS ~ 5%
     */
     public int rand10solution1() {
         //can generate even distribution [1 - 49]
         int res = (rand7() - 1)*7 + rand7();
         while (res > 10) {
             //just cut the values that are not in [1 - 10]
             res = (rand7() - 1)*7 + rand7();
         }
         return res;
     }

    /**
     * NOTE: the more we cut, the less our performance!
     * so ... let's improve the solution
     * BEATS ~ 80%
     */
    public int rand10() {
        //can generate even distribution [1 - 49]
        int res = (rand7() - 1)*7 + rand7();
        while (res > 40) {
            //generate res only if it is > 40,
            // since in this case we will have uneven distribution when we apply % to 41 .. 49
            // (since there are only 9 numbers, not 10)
            res = (rand7() - 1)*7 + rand7();
        }
        return (res % 10) + 1;
    }

    private int rand7() {
         return new Random().nextInt(6) + 1;
    }
}
