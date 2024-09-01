package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 2092. Find All People With Secret (hard)
 * https://leetcode.com/problems/find-all-people-with-secret
 * <p>
 * #Company: Google
 * <p>
 * You are given an integer n indicating there are n people numbered from 0 to n - 1.
 * You are also given a 0-indexed 2D integer array meetings
 * where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei.
 * A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.
 * <p>
 * Person 0 has a secret and initially shares the secret with a person firstPerson at time 0.
 * This secret is then shared every time a meeting takes place with a person that has the secret.
 * More formally, for every meeting,
 * if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.
 * <p>
 * The secrets are shared instantaneously.
 * That is, a person may receive the secret and share it with people in other meetings within the same time frame.
 * <p>
 * Return a list of all the people that have the secret after all the meetings have taken place.
 * You may return the answer in any order.
 * <p>
 * Example 1:
 * Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
 * Output: [0,1,2,3,5]
 * Explanation:
 * At time 0, person 0 shares the secret with person 1.
 * At time 5, person 1 shares the secret with person 2.
 * At time 8, person 2 shares the secret with person 3.
 * At time 10, person 1 shares the secret with person 5.
 * Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
 * <p>
 * Example 2:
 * Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
 * Output: [0,1,3]
 * Explanation:
 * At time 0, person 0 shares the secret with person 3.
 * At time 2, neither person 1 nor person 2 know the secret.
 * At time 3, person 3 shares the secret with person 0 and person 1.
 * Thus, people 0, 1, and 3 know the secret after all the meetings.
 * <p>
 * Example 3:
 * Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
 * Output: [0,1,2,3,4]
 * Explanation:
 * At time 0, person 0 shares the secret with person 1.
 * At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
 * Note that person 2 can share the secret at the same time as receiving it.
 * At time 2, person 3 shares the secret with person 4.
 * Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.
 * <p>
 * Constraints:
 * 2 <= n <= 10^5
 * 1 <= meetings.length <= 10^5
 * meetings[i].length == 3
 * 0 <= xi, yi <= n - 1
 * xi != yi
 * 1 <= timei <= 10^5
 * 1 <= firstPerson <= n - 1
 */
public class FindAllPeopleWithSecret {
    /**
     * KREVSKY SOLUTION:
     * incorrect for some cases
     * time to solve ~ 40 mins
     * time ~ O(meetings.length) - to form map. + O(meetings.length) - for BFS
     * space ~ O(meetings.length)
     * <p>
     * this solution is INCORRECT!
     * 44 / 56 testcases passed
     * Example of failed TC:
     * n = 12
     * meetings = [[10,8,6],[9,5,11],[0,5,18],[4,5,13],[11,6,17],[0,11,10],[10,11,7],[5,8,3],[7,6,16],[3,6,10],[3,11,1],[8,3,2],[5,0,7],[3,8,20],[11,0,20],[8,3,4],[1,9,4],[10,7,11],[8,10,18]]
     * firstPerson  = 9
     * <p>
     * My output: [0,1,5,6,9,11]
     * Expected: [0,1,4,5,6,9,11] - here is 4!
     */
    public static void main(String[] args) {
        int[][] meetings = {{10, 8, 6}, {9, 5, 11}, {0, 5, 18}, {4, 5, 13}, {11, 6, 17}, {0, 11, 10}, {10, 11, 7}, {5, 8, 3}, {7, 6, 16}, {3, 6, 10}, {3, 11, 1}, {8, 3, 2}, {5, 0, 7}, {3, 8, 20}, {11, 0, 20}, {8, 3, 4}, {1, 9, 4}, {10, 7, 11}, {8, 10, 18}};
        int firstPerson = 9;
        int n = 12;
        FindAllPeopleWithSecret obj = new FindAllPeopleWithSecret();
        List<Integer> res = obj.findAllPeopleKrev2(n, meetings, firstPerson);
        System.out.println("");
    }

    public List<Integer> findAllPeopleKrev1(int n, int[][] meetings, int firstPerson) {
        //person1 -> list of Pairs (person2, time of meeting)
        Map<Integer, List<int[]>> map = new HashMap<>();

        //bootleg for case when meeting there are meetings 0 -> .. And no meetings firstPerson -> ..
        //alternative idea is to add meeting = [0, firstPerson, 0] to meetings array
        map.put(0, new ArrayList<>());
        map.get(0).add(new int[]{firstPerson, 0});
        map.put(firstPerson, new ArrayList<>());
        map.get(firstPerson).add(new int[]{0, 0});

        //1. time ~ O(meeting.length)
        for (int[] meeting : meetings) {
            map.putIfAbsent(meeting[0], new ArrayList<>());
            map.get(meeting[0]).add(new int[]{meeting[1], meeting[2]});

            map.putIfAbsent(meeting[1], new ArrayList<>());
            map.get(meeting[1]).add(new int[]{meeting[0], meeting[2]});
        }

        Set<Integer> hasSecret = new HashSet<>();
        hasSecret.add(0);
        // hasSecret.add(firstPerson);

        //2. use BFS and queue of Pairs (person2, time of meeting).
        // The Pair is related to the meeting with person1 who had secret
        // BUT transferred the secret only in case if person1 got it before (or in the same time) the meeting with person2
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        // q.add(new int[]{firstPerson, 0});
        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int person1 = pair[0];
            int time1 = pair[1];

            List<int[]> adjList = map.get(person1);
            for (int[] adjPair : adjList) {
                //HERE is the problem! in case if p1 and p2 had several meetings,
                // some of the meetings might be filtered by hasSecret.contains(..)
                //example: {{person=5,time=18},{person=5,time=4}..} => early meeting will be skipped => this is mistake! we need to keep the earliest meeting!
                if (adjPair[1] >= time1 && !hasSecret.contains(adjPair[0])) {
                    hasSecret.add(adjPair[0]);
                    q.add(adjPair);
                }
            }
        }

        return new ArrayList<>(hasSecret);
    }

    /**
     * tried to fix the issue from Krev1:
     * i.e. for each person to store not list but Map: person -> time of their first meeting
     * <p>
     * it helped for the issue from Krev1, but still incorrect
     * <p>
     * 42 / 56 testcases passed
     * n = 135
     * <p>
     * TOO long data => can't analyze
     * meetings = [[26,7,481],[28,120,80],[87,71,36],[11,86,164],[87,32,4],[8,104,240],[110,6,27],[120,126,209],[5,67,220],[61,47,175],[60,52,86],[88,53,453],[40,124,206],[122,22,62],[4,26,433],[46,56,214],[15,6,264],[128,31,313],[24,20,393],[7,20,282],[65,84,216],[55,132,241],[50,32,338],[61,69,459],[130,2,53],[43,106,322],[61,51,99],[43,61,64],[34,37,474],[94,116,338],[19,133,277],[82,40,148],[124,129,496],[73,82,417],[7,6,416],[61,15,98],[92,45,293],[78,109,130],[120,9,181],[84,23,390],[102,78,263],[104,130,262],[46,124,25],[125,101,112],[131,44,427],[78,71,96],[81,16,73],[58,49,18],[72,113,437],[28,119,3],[9,19,249],[113,121,58],[18,93,219],[112,117,412],[69,110,111],[100,44,377],[8,121,359],[86,98,57],[78,75,49],[104,58,276],[2,43,178],[73,64,352],[104,37,298],[14,88,184],[76,56,393],[0,90,144],[17,8,219],[31,127,455],[61,101,84],[100,87,398],[3,118,342],[98,50,272],[126,123,113],[28,52,122],[44,69,32],[127,96,473],[113,116,72],[104,46,436],[1,69,343],[51,18,338],[15,23,250],[21,118,258],[98,6,287]...
     * <p>
     * FINALLY still did not get WHY this solution is incorrect?!... see SOLUTION #2 to see to the same idea is implemented easier and it is workable
     */
    public List<Integer> findAllPeopleKrev2(int n, int[][] meetings, int firstPerson) {
        //person1 -> list of Pairs (person2, time of meeting)
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        //1. time ~ O(meeting.length)
        map.put(0, new HashMap<>());
        map.get(0).put(firstPerson, 0);
        map.put(firstPerson, new HashMap<>());
        map.get(firstPerson).put(0, 0);

        for (int[] meeting : meetings) {
            map.putIfAbsent(meeting[0], new HashMap<>());
            map.putIfAbsent(meeting[1], new HashMap<>());

            int time = map.get(meeting[0]).getOrDefault(meeting[1], Integer.MAX_VALUE);
            //set minimumTime when persons meeting[0] and meeting[1] met
            int minimumTime = Math.min(time, meeting[2]);

            map.get(meeting[0]).put(meeting[1], minimumTime);
            map.get(meeting[1]).put(meeting[0], minimumTime);
        }

        Set<Integer> hasSecret = new HashSet<>();
        hasSecret.add(0);
        // hasSecret.add(firstPerson);

        //2. use BFS and queue of Pairs (person2, time of meeting).
        // The Pair is related to the meeting with person1 who had secret
        // BUT transferred the secret only in case if person1 got it before (or in the same same) the meeting with person2
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        // q.add(new int[]{firstPerson, 0});
        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int person1 = pair[0];
            int time1 = pair[1];

            Map<Integer, Integer> adjMap = map.get(person1);
            for (Map.Entry<Integer, Integer> entry : adjMap.entrySet()) {
                if (entry.getValue() >= time1 && !hasSecret.contains(entry.getKey())) {
                    hasSecret.add(entry.getKey());
                    q.add(new int[]{entry.getKey(), entry.getValue()});
                }
            }
        }

        return new ArrayList<>(hasSecret);
    }


    /**
     * NOT SOLVED by myself
     * SOLUTION #1:
     * info:
     * https://www.youtube.com/watch?v=O80_LZJnwmo&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=47
     * <p>
     * ATTENTION! throws Time Limit Exceeded!
     * <p>
     * time ~ O(N log N + N + K), where N is the number of meetings and K is the number of people.
     * space ~ O(N + K)
     */
    public List<Integer> findAllPeople1(int n, int[][] meetings, int firstPerson) {
        //1. sort meetings by time:
        //time ~ O(N logN)
        Arrays.sort(meetings, (int[] a, int[] b) -> a[2] - b[2]);

        //2. Create Sorted (by time) Map (i.e. TreeMap): time -> list of pairs of persons that have meeting at that time
        //time ~ O(N logN)
        TreeMap<Integer, List<int[]>> timeToPairs = new TreeMap<Integer, List<int[]>>();

        for (int[] meeting : meetings) {
            timeToPairs.putIfAbsent(meeting[2], new ArrayList<>());
            timeToPairs.get(meeting[2]).add(new int[]{meeting[0], meeting[1]});
        }

        Set<Integer> hasSecret = new HashSet<>();
        hasSecret.add(0);
        hasSecret.add(firstPerson);

        for (List<int[]> pairs : timeToPairs.values()) {
            //3. for each value (i.e. list) from TreeMap we build graph
            Map<Integer, List<Integer>> adjMap = new HashMap<>();
            for (int[] pair : pairs) {
                adjMap.putIfAbsent(pair[0], new ArrayList<>());
                adjMap.get(pair[0]).add(pair[1]);

                adjMap.putIfAbsent(pair[1], new ArrayList<>());
                adjMap.get(pair[1]).add(pair[0]);
            }

            //4. traverse built graph by, for example, BFS
            //entry vertices are vertices from hasSecret set
            //i.e. the queue contains ONLY persons that has secret
            Queue<Integer> q = new LinkedList<>();
            for (int s : hasSecret) {
                q.add(s);
            }

            while (!q.isEmpty()) {
                int p = q.poll();
                List<Integer> adjList = adjMap.get(p);
                if (adjList != null) {
                    for (int neighbour : adjList) {
                        if (!hasSecret.contains(neighbour)) {
                            hasSecret.add(neighbour);
                            q.add(neighbour);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(hasSecret);
    }

    /**
     * SOLUTION #2:
     * info:
     * https://leetcode.com/problems/find-all-people-with-secret/solutions/4774403/java-solution-explained-in-hindi/
     * idea:
     * the same as in Krev2
     * BUT instead of support min time of meeting in the map, we track is in 'firstTimeToKnowSecret' array!
     * it is NOT just 'visited' flag! it contains the time when i-th person knows the secret.
     * and if we found that it was earlier than we store => update this time => it influences of our traversal through the Queue!
     *
     * Finally we are not interested in these times.We will use the fact that it is not MAX_VALUE
     *
     * BEATS ~ 61%
     *
     */
    public List<Integer> findAllPeople2(int n, int[][] meetings, int firstPerson) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] meeting : meetings) {
            int x = meeting[0], y = meeting[1], t = meeting[2];
            graph.computeIfAbsent(x, k -> new ArrayList<>()).add(new int[]{t, y});
            graph.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{t, x});
        }

        int[] firstTimeToKnowSecret = new int[n];
        Arrays.fill(firstTimeToKnowSecret, Integer.MAX_VALUE);
        firstTimeToKnowSecret[0] = 0;
        firstTimeToKnowSecret[firstPerson] = 0;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        q.offer(new int[]{firstPerson, 0});

        while (!q.isEmpty()) {
            int[] personTime = q.poll();
            int person = personTime[0];
            int time = personTime[1];

            //tips: use getOrDefault instead of adding meetings like [0,firstPerson,0]
            for (int[] nextPersonTime : graph.getOrDefault(person, new ArrayList<>())) {
                int t = nextPersonTime[0];
                int nextPerson = nextPersonTime[1];

                if (t >= time && firstTimeToKnowSecret[nextPerson] > t) {
                    firstTimeToKnowSecret[nextPerson] = t;
                    q.offer(new int[]{nextPerson, t});
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (firstTimeToKnowSecret[i] != Integer.MAX_VALUE) {
                ans.add(i);
            }
        }
        return ans;
    }


    public static void treeMapTest() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(5, "five");
        map.put(2, "two");
        map.put(4, "four");
        map.put(1, "one");

        for (int i : map.keySet()) {
            System.out.println(i);
        }

        System.out.println("--------------");

        for (String s : map.values()) {
            System.out.println(s);
        }
    }
}
