package solving_techniques.p29_Graphs.theory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsGraphTraversal {
    public static void main(String[] args) {
        int V = 5;

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Inserting edges
        DfsGraphTraversal.insert(adjList, 0, 1);
        DfsGraphTraversal.insert(adjList, 0, 2);
        DfsGraphTraversal.insert(adjList, 1, 0);
        DfsGraphTraversal.insert(adjList, 1, 2);
        DfsGraphTraversal.insert(adjList, 1, 3);
        DfsGraphTraversal.insert(adjList, 2, 0);
        DfsGraphTraversal.insert(adjList, 2, 1);
        DfsGraphTraversal.insert(adjList, 2, 4);
        DfsGraphTraversal.insert(adjList, 3, 1);
        DfsGraphTraversal.insert(adjList, 3, 4);
        DfsGraphTraversal.insert(adjList, 4, 2);
        DfsGraphTraversal.insert(adjList, 4, 3);

        // Perform BFS traversal starting from vertex 0
        System.out.print("Breadth First Traversal starting from vertex 0: \n");

        bfsGraphTraversal_1(0, adjList);

        int[][] adjMatrix = ConvertAdjacencyListToAdjacencyMatrix.convert(adjList);
        System.out.println("");
        bfsGraphTraversal_2(0, adjMatrix);
    }
    /**
     * SOLUTION #1:
     * info:
     * https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
     *
     * BFS: iterative (queue) + adjacency list
     *
     * Time Complexity: O(V+E), where V is the number of nodes and E is the number of edges.
     * Auxiliary Space: O(V)
     */
    public static void bfsGraphTraversal_1(int v, List<List<Integer>> adjList) {
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adjList.size()];

        //NOTE: we mark the vertex BEFORE we really visit it (i.e. poll from the Queue)
        // otherwise in case 0 -> 1, 0 -> 2 and 1 -> 2 we will add "2" twice
        // (due to 0 -> 2 and 1 -> 2)
        visited[v] = true;
        queue.add(v);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            System.out.print(currentNode + " ");

            for (int neighbour : adjList.get(currentNode)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }

    /**
     * SOLUTION #2:
     * info:
     * https://www.geeksforgeeks.org/implementation-of-bfs-using-adjacency-matrix/
     *
     * BFS: iterative (queue) + adjacency matrix
     *
     * Time Complexity: O(N*N)
     * Auxiliary Space: O(N)
     */
    public static void bfsGraphTraversal_2(int v, int[][] adjMatrix) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adjMatrix.length];

        visited[v] = true;
        queue.add(v);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            System.out.print(currentNode + " ");

            for (int i = 0; i < adjMatrix.length; i++) {
                if (adjMatrix[currentNode][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
