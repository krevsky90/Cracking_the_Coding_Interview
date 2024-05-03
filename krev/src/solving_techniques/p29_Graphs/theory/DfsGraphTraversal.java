package solving_techniques.p29_Graphs.theory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DfsGraphTraversal {
    public static void main(String[] args) {
        int V = 5;

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Inserting edges
        insert(adjList, 0, 1);
        insert(adjList, 0, 2);
        insert(adjList, 1, 0);
        insert(adjList, 1, 2);
        insert(adjList, 2, 1);
        insert(adjList, 2, 0);
        insert(adjList, 2, 3);
        insert(adjList, 3, 3);

        dfsGraphTraversal_11(2, adjList);
        System.out.println("");
        dfsGraphTraversal_12(2, adjList);
        System.out.println("");
        int[][] adjMatrix = ConvertAdjacencyListToAdjacencyMatrix.convert(adjList);

        dfsGraphTraversal_21(2, adjMatrix);
        System.out.println("");
        dfsGraphTraversal_22(2, adjMatrix);
    }

    /**
     * SOLUTION #1.1:
     * info:
     * https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
     *
     * DFS: recursive + adjacency list
     *
     * Time complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph.
     * Auxiliary Space: O(V + E), since an extra visited array of size V is required, And stack size for iterative call to DFS function.
     */
    public static void dfsGraphTraversal_11(int v, List<List<Integer>> adjList) {
        System.out.println("DFS: recursive + adjacency list");
        boolean[] visited = new boolean[adjList.size()];
        dfsGraphTraversalHelper11(2, visited, adjList);
    }

    private static void dfsGraphTraversalHelper11(int v, boolean[] visited, List<List<Integer>> adjList) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int neighbour : adjList.get(v)) {
            if (!visited[neighbour]) {
                dfsGraphTraversalHelper11(neighbour, visited, adjList);
            }
        }
    }

    /**
     * SOLUTION #1.2:
     * info:
     * https://www.geeksforgeeks.org/iterative-depth-first-traversal
     *
     * DFS: iterative (stack) + adjacency list
     *
     */
    public static void dfsGraphTraversal_12(int v, List<List<Integer>> adjList) {
        System.out.println("DFS: iterative (stack) + adjacency list");
        boolean[] visited = new boolean[adjList.size()];

        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        while (!stack.isEmpty()) {
            int tempV = stack.pop();
            //NOTE: it is better to place 'for' loop inside if-condition
            //see here https://web.cs.unlv.edu/larmore/Courses/CSC477/bfsDfs.pdf
            if (!visited[tempV]) {
                System.out.print(tempV + " ");
                visited[tempV] = true;

                for (int neighbour : adjList.get(tempV)) {
                    if (!visited[neighbour]) {    //looks like it can just speed up the code, but not more
                        stack.push(neighbour);
                    }
                }
            }
        }
    }

    /**
     * SOLUTION #2.1:
     * info:
     * https://www.geeksforgeeks.org/implementation-of-dfs-using-adjacency-matrix/
     *
     * DFS: recursive + adjacency matrix
     *
     * time complexity ~ O(V^2), where V - is the number of vertices in the graph
     * space complexity ~ O(V^2), because we are using an adjacency matrix to represent the graph, which requires V^2 space.
     */
    public static void dfsGraphTraversal_21(int v, int[][] adjMatrix) {
        System.out.println("DFS: recursive + adjacency matrix");
        boolean[] visited = new boolean[adjMatrix.length];
        dfsGraphTraversalHelper21(v, visited, adjMatrix);
    }

    public static void dfsGraphTraversalHelper21(int v, boolean[] visited, int[][] adjMatrix) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int i = 0; i < adjMatrix.length; i++) { //or adjMatrix[v].length
            if (adjMatrix[v][i] == 1 && !visited[i]) {
                dfsGraphTraversalHelper21(i, visited, adjMatrix);
            }
        }
    }

    /**
     * SOLUTION #2.2:
     *
     * DFS: iterative (stack) + adjacency matrix
     */
    public static void dfsGraphTraversal_22(int v, int[][] adjMatrix) {
        System.out.println("DFS: iterative (stack) + adjacency matrix");

        //todo: check with internet
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adjMatrix.length];

        stack.push(v);
        while (!stack.isEmpty()) {
            int tempV = stack.pop();

            if (!visited[tempV]) {
                System.out.print(tempV + " ");
                visited[tempV] = true;

                for (int i = 0; i < adjMatrix.length; i++) {
                    if (adjMatrix[tempV][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
    }

    //
    public static void insert(List<List<Integer>> adjList, int u, int v) {
        // Insert a vertex v to vertex u
        adjList.get(u).add(v);
    }

}
