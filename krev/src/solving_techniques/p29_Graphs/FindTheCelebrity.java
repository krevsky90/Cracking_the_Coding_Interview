package solving_techniques.p29_Graphs;

/**
 * 277. Find the Celebrity (medium) (locked)
 * https://leetcode.com/problems/find-the-celebrity
 * OR
 * https://leetcode.ca/all/277.html
 * <p>
 * #Company: Amazon Apple Facebook Google LinkedIn Microsoft Palantir Technologies Pinterest Square Uber
 * <p>
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one  celebrity.
 * The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
 * Now you want to find out who the celebrity is or verify that there is not one.
 * The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?"
 * to get information of whether A knows B.
 * You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * <p>
 * You are given a helper function bool knows(a, b) which tells you whether A knows B.
 * Implement a function int findCelebrity(n).
 * There will be exactly one celebrity if he/she is in the party.
 * Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 * <p>
 * Example 1:
 * Input: graph = [
 * [1,1,0],
 * [0,1,0],
 * [1,1,1]
 * ]
 * Output: 1
 * Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j,
 * otherwise graph[i][j] = 0 means person i does not know person j.
 * The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
 * <p>
 * Example 2:
 * Input: graph = [
 * [1,0,1],
 * [1,1,0],
 * [0,1,1]
 * ]
 * Output: -1
 * Explanation: There is no celebrity.
 * <p>
 * Note:
 * The directed graph is represented as an adjacency matrix,
 * which is an n x n matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0 means the contrary.
 * Remember that you won't have direct access to the adjacency matrix.
 */
public class FindTheCelebrity {
    public static void main(String[] args) {
        for (int i = 0; i < 10 && i != 4; i++) {
            System.out.println(i);
        }
    }

    /**
     * NOT SOLVED optimally by myself
     * info:
     * https://www.youtube.com/watch?v=ZuJEDPU1iug&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=86
     *
     * time to solve ~ 30+ mins
     *
     * time ~ O(n)
     * space ~ O(1)
     *
     */
    public int findCelebrity(int n) {
        int celebrity = 0;

        for (int j = 0; j < n; j++) {
            // if someone does not know current celebrity candidate =>
            // 1) this j-th person might be celebrity
            // 2) current celebrity candidate can NOT be the real celebrity
            // 3) all people from 'celebrity' to j-th can NOT be celebrity since they know current candidate
            if (!knows(j, celebrity)) {
                celebrity = j;
            }
        }

        //after for-loop we check the final celebrity candidate if he knows everyone
        for (int i = 0; i < n; i++) {
            if (i != celebrity && (knows(celebrity, i) || !knows(i, celebrity))) {
                return -1;
            }
        }

        return celebrity;
    }

    private boolean knows(int a, int b) {
        return true;    //sta
    }

}
