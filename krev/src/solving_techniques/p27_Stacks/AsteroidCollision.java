package solving_techniques.p27_Stacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 735. Asteroid Collision (medium)
 * https://leetcode.com/problems/asteroid-collision
 * <p>
 * #Company (31.05.2025): 0 - 3 months Amazon 10 Dream11 4 Google 3 Meta 2 Bloomberg 2 Nvidia 2 PayPal 2 0 - 6 months Microsoft 2 Goldman Sachs 2 Salesforce 2 PhonePe 2 OpenAI 2 6 months ago DoorDash 17 Oracle 15 TikTok 13 Apple 9 Adobe 8 Flipkart 8 Sprinklr 7 Qualtrics 6 IMC 6 SoFi 4
 * <p>
 * We are given an array asteroids of integers representing asteroids in a row.
 * The indices of the asteroid in the array represent their relative position in space.
 * <p>
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 * <p>
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * <p>
 * Example 1:
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 * <p>
 * Example 2:
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 * <p>
 * Example 3:
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 * <p>
 * Constraints:
 * 2 <= asteroids.length <= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 */
public class AsteroidCollision {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 25 mins
     * idea: use stack
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 3 attempts:
     * - incorrect condition to add temp to stack
     * - missed 'continue' in case of annigilation
     * <p>
     * BEATS ~ 85%
     */
    public int[] asteroidCollision(int[] asteroids) {
        //time ~ O(n)
        //space ~ O(n)

        int n = asteroids.length;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int temp = asteroids[i];
            if (temp > 0) {
                stack.add(temp);
            } else {
                //temp < 0
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(temp)) {
                    stack.pop();
                }

                if (!stack.isEmpty() && stack.peek() == Math.abs(temp)) {
                    stack.pop();
                    continue;
                }

                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.add(temp);
                }
            }
        }

        // Add the asteroids from the stack to the vector in the reverse order.
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    /**
     * Official:
     */
    public int[] asteroidCollisionOfficial(int[] asteroids) {
        Stack<Integer> st = new Stack<Integer>();

        for (int asteroid : asteroids) {
            boolean flag = true;
            while (!st.isEmpty() && (st.peek() > 0 && asteroid < 0)) {
                // If the top asteroid in the stack is smaller, then it will explode.
                // Hence pop it from the stack, also continue with the next asteroid in the stack.
                if (Math.abs(st.peek()) < Math.abs(asteroid)) {
                    st.pop();
                    continue;
                }
                // If both asteroids have the same size, then both asteroids will explode.
                // Pop the asteroid from the stack; also, we won't push the current asteroid to the stack.
                else if (Math.abs(st.peek()) == Math.abs(asteroid)) {
                    st.pop();
                }

                // If we reach here, the current asteroid will be destroyed
                // Hence, we should not add it to the stack
                flag = false;
                break;
            }

            if (flag) {
                st.push(asteroid);
            }
        }

        // Add the asteroids from the stack to the vector in the reverse order.
        int[] remainingAsteroids = new int[st.size()];
        for (int i = remainingAsteroids.length - 1; i >= 0; i--) {
            remainingAsteroids[i] = st.peek();
            st.pop();
        }

        return remainingAsteroids;
    }
}
