package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 332. Reconstruct Itinerary (hard)
 * https://leetcode.com/problems/reconstruct-itinerary
 *
 * #Company: Amazon Apple Bloomberg Citadel Facebook Goldman Sachs Google Microsoft Qualtrics Snapchat Twilio Uber Yandex Yelp
 * <p>
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight.
 * Reconstruct the itinerary in order and return it.
 * <p>
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK".
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * <p>
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 * <p>
 * Example 1:
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 * <p>
 * Example 2:
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 * <p>
 * Constraints:
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * fromi.length == 3
 * toi.length == 3
 * fromi and toi consist of uppercase English letters.
 * fromi != toi
 */
public class ReconstructItinerary {
    /**
     * NOT SOLVED by myself!
     * <p>
     * info: https://leetcode.com/problems/reconstruct-itinerary/solutions/5903530/simple-solution-with-diagrams-in-video-javascript-c-java-python/
     * <p>
     * idea: Hierholzer's algorithm to find Eulerian path
     * В теории графов эйлерова тропа — это тропа в конечном графе, которая посещает каждое ребро ровно один раз
     * <p>
     * 1) create AdjMap
     * 2) sort list in each value of map in REVERSE lexicographical order
     * 3) use dfs:
     * - pop the latest (i.e. the smallest element from adjList of current point)
     * - launch dfs for popped node
     * - append the initial point to the result
     * <p>
     * time to spend ~ 60+ mins
     * <p>
     * BEATS ~ 72%
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();

        Map<String, List<String>> adjMap = new HashMap<>();

        for (List<String> ticket : tickets) {
            adjMap.putIfAbsent(ticket.get(0), new ArrayList<String>());
            adjMap.get(ticket.get(0)).add(ticket.get(1));
        }

        //sort adj lists in reverse lexicographical order
        for (String src : adjMap.keySet()) {
            Collections.sort(adjMap.get(src), (a, b) -> b.compareTo(a));
        }

        //use dfs

        String src = "JFK";
        dfs(src, result, adjMap);

        return result;
    }

    private void dfs(String src, List<String> result, Map<String, List<String>> adjMap) {
        List<String> adjList = adjMap.get(src);
        if (adjList != null) {
            while (adjList != null && !adjList.isEmpty()) {
                String popped = adjList.get(adjList.size() - 1);
                adjList.remove(adjList.size() - 1);
                dfs(popped, result, adjMap);
            }
        }

        result.add(0, src);
    }
}

// [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
// JFK - NRT KUL
// NRT - JFK
// JFK - NRT - JFK - KUL
