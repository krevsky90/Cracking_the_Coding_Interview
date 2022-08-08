package data_structures.chapter4_trees_n_graphs.extra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://youtu.be/86T7ICkPr0Q?t=1273
 *
 * Find closest node to given two nodes
 *
 * You are given a directed graph of n nodes numbered from 0 to n-1, where each node has at MOST ONE outgoing edge.
 * The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i].
 * If there is no outgoing edge from i, then edges[i] = -1.
 * You are also given two integers node1 and node2.
 * Return the INDEX of the node that can be reached from both node1 and node2, such that the MAXIMUM between the distance from node1 tha that node,
 * and from node2 to that node is MINIMIZED.
 * If there are multiple answers, return the node with the SMALLEST index, and if no possible answer exists, return -1.
 * Note that edges may contain cycles.
 *
 * Example1:
 * Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
 * Output: 2
 *
 * Example2:
 * Input: [1,2,-1], node1 = 0, node2 = 2
 * Output: 2
 */
public class ClosestMeetingNode {
    public static void main(String[] args) {
//        int[] edges = {2,2,3,-1}; int node1 = 0; int node2 = 1;
        int[] edges = {2,0,0}; int node1 = 2; int node2 = 0;
        System.out.println(closestMeetingNodeKREV(edges, node1, node2));
    }

    public static int closestMeetingNodeKREV(int[] edges, int node1, int node2) {
        List<Integer> list1 = new ArrayList<>();
        int i1 = node1;
        list1.add(i1);
        boolean cycle1 = false;
        while (edges[i1] != -1) {
            i1 = edges[i1];
            list1.add(i1);
            if (i1 == node1) {
                //cycle!
                cycle1 = true;
                break;
            }
        }

        if (cycle1) {
            //find node2 in set1
            if (!list1.contains(node2)) {
                return -1;
            } else {
                //count the distance between node1 and node2
                int dist12 = countDistance(edges, node1, node2);
                int dist21 = countDistance(edges, node2, node1);
                if (dist12 < dist21) return node2;
                else if (dist12 > dist21) return node1;
                else return Math.min(node1, node2);    //this is by analogy with "if (node1Visited.contains(node2) && node2Visited.contains(node1)) return Math.min(node1, node2);"
            }
        }

        List<Integer> list2 = new ArrayList<>();
        int i2 = node2;
        list2.add(i2);
        while (edges[i2] != -1) {
            i2 = edges[i2];
            list2.add(i2);
            //don't check cycle
        }

        //move from the ends of the lists
        int it1 = list1.size() - 1;
        int it2 = list2.size() - 1;
        int tempResult = -1;
        while (it1 >= 0 && it2 >= 0) {
            if (list1.get(it1) == list2.get(it2)) {
                tempResult = list1.get(it1);
                it1--;
                it2--;
            } else {
                break;
            }
        }

        return tempResult;
    }

    private static int countDistance(int[] edges, int start, int end) {
        int result = 1;
        while (true) {
            if (edges[start] == -1) break;
            if (edges[start] == end) return result;
            result++;
            start = edges[start];
        }
        return -1;  //error
    }

    public static int closestMeetingNodeSam(int[] edges, int node1, int node2) {
        Set<Integer> node1Visited = new HashSet<>();
        node1Visited.add(node1);
        Set<Integer> node2Visited = new HashSet<>();
        node2Visited.add(node2);

        while (true) {
            if (node1Visited.contains(node2) && node2Visited.contains(node1)) return Math.min(node1, node2);

            if (node1Visited.contains(node2)) return node2;
            if (node2Visited.contains(node1)) return node1;

            if (edges[node1] == -1 && edges[node2] == -1) return -1;

            if (edges[node1] >= 0) node1 = edges[node1];
            if (edges[node2] >= 0) node2 = edges[node2];

            //I don't agree with this! since we can return the node even if we have loop
            if (node1Visited.contains(node1) && node2Visited.contains(node2)) return -1;

            node1Visited.add(node1);
            node2Visited.add(node2);
        }
    }

    /**
     * use boolean[] instead of Set
     */
    public static int closestMeetingNodeSamOptimized(int[] edges, int node1, int node2) {
        boolean[] node1Visited = new boolean[edges.length];
        node1Visited[node1] = true;
        boolean[] node2Visited = new boolean[edges.length];
        node2Visited[node2] = true;

        while (true) {
            if (node1Visited[node2] && node2Visited[node1]) return Math.min(node1, node2);

            if (node1Visited[node2]) return node2;
            if (node2Visited[node1]) return node1;

            if (edges[node1] == -1 && edges[node2] == -1) return -1;

            if (edges[node1] >= 0) node1 = edges[node1];
            if (edges[node2] >= 0) node2 = edges[node2];

            //I don't agree with this! since we can return the node even if we have loop
            if (node1Visited[node1] && node2Visited[node2]) return -1;

            node1Visited[node1] = true;
            node2Visited[node2] = true;
        }
    }
}
