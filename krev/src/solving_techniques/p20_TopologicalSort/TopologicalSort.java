package solving_techniques.p20_TopologicalSort;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a493063f9c1a4665882e74
 *
 * the details taken from
 * https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2016%3A%20%F0%9F%94%8E%20Topological%20Sort%20(Graph).md
 *
 * Topological Sort of a directed graph (a graph with unidirectional edges) is a linear ordering of its vertices such
 * that for every directed edge (U, V) from vertex U to vertex V, U comes before V in the ordering.
 *
 * Given a directed graph, find SOME OF (?) the topological ordering of its vertices.
 *
 * Example 1:
 * Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]
 * Output: Following are the two valid topological sorts for the given graph:
 * 1) 3, 2, 0, 1
 * 2) 3, 2, 1, 0
 *
 * Example 2:
 * Input: Vertices=5, Edges=[4, 2], [4, 3], [2, 0], [2, 1], [3, 1]
 * Output: Following are all valid topological sorts for the given graph:
 * 1) 4, 2, 3, 0, 1
 * 2) 4, 3, 2, 0, 1
 * 3) 4, 3, 2, 1, 0
 * 4) 4, 2, 3, 1, 0
 * 5) 4, 2, 0, 3, 1
 *
 * Example 3:
 * Input: Vertices=7, Edges=[6, 4], [6, 2], [5, 3], [5, 4], [3, 0], [3, 1], [3, 2], [4, 1]
 * Output: Following are all valid topological sorts for the given graph:
 * 1) 5, 6, 3, 4, 0, 1, 2
 * 2) 6, 5, 3, 4, 0, 1, 2
 * 3) 5, 6, 4, 3, 0, 2, 1
 * 4) 6, 5, 4, 3, 0, 1, 2
 * 5) 5, 6, 3, 4, 0, 2, 1
 * 6) 5, 6, 3, 4, 1, 2, 0
 */
public class TopologicalSort {
    public static void main(String[] args) {
//        System.out.println("Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]");
//        int vertices1 = 4;
//        int [][] edges1 = {{3,2},{3,0},{2,0},{2,1}};
//
//        System.out.println("result 1 DFS");
//        int[] result1dfs = topologicalSortDFS(vertices1, edges1);
//        Arrays.stream(result1dfs).forEach(x -> System.out.print(x + " "));
//        System.out.println("\nresult 1 BFS");
//        int[] result1bfs = topologicalSortBFS(vertices1, edges1);
//        Arrays.stream(result1bfs).forEach(x -> System.out.print(x + " "));
//
//        System.out.println("\n\nInput: Vertices=7, Edges=[6, 4], [6, 2], [5, 3], [5, 4], [3, 0], [3, 1], [3, 2], [4, 1]");
//        int vertices3 = 7;
//        int [][] edges3 = {{6,4},{6,2},{5,3},{5,4},{3,0},{3,1},{3,2},{4,1}};
//        System.out.println("result 3 DFS");
//        int[] result3dfs = topologicalSortDFS(vertices3, edges3);
//        Arrays.stream(result3dfs).forEach(x -> System.out.print(x + " "));
//        System.out.println("\nresult 3 BFS");
//        int[] result3bfs = topologicalSortBFS(vertices3, edges3);
//        Arrays.stream(result3bfs).forEach(x -> System.out.print(x + " "));

        System.out.println("Input: Vertices=3, Edges=[1, 2], [2, 1], [0, 1]");
        int vertices4 = 3;
        int[][] edges4 = {{1,2},{2,1},{0,1}};

        int[] result4dfs = topologicalSortDfsNeetcode(vertices4, edges4);
        System.out.println("");
    }

    /**
     * KREVSKY SOLUTION
     * based on BFS approach (#2)
     *
     * time to solve ~ 25-30 mins
     *
     * 3 attempts:
     * - did not fill vertexToCountMap for all keys in the beginning
     * - did not fill graph for all keys in the beginning
     */
    public static int[] topologicalSortBFS(int vertices, int [][] edges) {
        List<Integer> result = new ArrayList<>();

        // 1. Initialization
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> vertexToCountMap = new HashMap<>();   //vertex to amount of incoming edges
        for (int i = 0; i < vertices; i++) {
            //it is more simple to put empty list rather then struggle with NPE for nodes that don't have outgoing edges
            graph.put(i, new ArrayList<>());
            //it is necessary since source nodes will not get 0 value in any other place!
            vertexToCountMap.put(i, 0);
        }

        // 2. Build the graph and find in-degrees of all vertices
        for (int[] edge : edges) {
            //fill graph
            graph.get(edge[0]).add(edge[1]);
            //fill incoming edge map
            vertexToCountMap.put(edge[1], vertexToCountMap.getOrDefault(edge[1], 0) + 1);
        }

        // 3. Find all initial sources
        Queue<Integer> sourceQueue = new LinkedList<>();
        for (Integer key : vertexToCountMap.keySet()) {
            if (vertexToCountMap.get(key) == 0) {
                sourceQueue.add(key);
            }
        }

        // 4. Sort
        while (!sourceQueue.isEmpty()) {
            Integer tempSource = sourceQueue.poll();
            result.add(tempSource);
            List<Integer> children = graph.get(tempSource);
            //OPTIONALLY: remove tempSource vertex from graph
//            graph.remove(tempSource);
            //decrement counter of incoming edges for children of tempSource
            for (Integer child : children) {
                vertexToCountMap.put(child, vertexToCountMap.get(child) - 1);
                //check if child becomes source vertex after that
                if (vertexToCountMap.get(child) == 0) {
                    //OPTIONALLY: remove child from vertexToCountMap
//                    vertexToCountMap.remove(child);
                    sourceQueue.add(child);
                }
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Topological Sort DFS
     * info: https://www.youtube.com/watch?v=Akt3glAwyfY
     * (similar to https://www.youtube.com/watch?v=6vaSka3rwDQ)
     *
     * time ~ O(E + V)
     * space ~ O(E)
     */
    public static int[] topologicalSortDfsNeetcode(int vertices, int [][] edges) {
        //1. build graph (adjLists)
        List<List<Integer>> adjLists = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjLists.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjLists.get(edge[0]).add(edge[1]);
        }

        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> cycle = new HashSet<>();

        //2. use DFS for each not visited vertex
        for (int i = 0; i < vertices; i++) {
            if (!visited.contains(i)) {
                if (!dfsNeetcode(adjLists, result, visited, cycle, i)) {
                    return new int[0];  //return empty arr if the graph has cycle
                }
            }
        }

        int[] resArr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resArr[i] = result.get(i);
        }

        return resArr;
    }

    private static boolean dfsNeetcode(List<List<Integer>> adjLists, List<Integer> result, Set<Integer> visited, Set<Integer> cycle, int v) {
        if (cycle.contains(v)) return false;    //we've found cycle

        if (visited.contains(v)) return true;

        cycle.add(v);
        List<Integer> children = adjLists.get(v);
        for (int child : children) {
            if (!dfsNeetcode(adjLists, result, visited, cycle, child)) {
                return false;   //we've found cycle
            }
        }

        cycle.remove(v);    //like backtracking
        visited.add(v);
        result.add(v);
        return true;    //since we did not find a cycle
    }
}
