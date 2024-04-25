package solving_techniques.p29_Graphs.theory;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/convert-adjacency-list-to-adjacency-matrix-representation-of-a-graph/
 * <p>
 * Input: adjList[] = {{0 ?> 1 ?> 3}, {1 ?> 2}, {2 ?> 3}}
 * Output:
 * 0 1 0 1
 * 0 0 1 0
 * 0 0 0 1
 * 0 0 0 0
 * <p>
 * Follow the steps below to convert an adjacency list to an adjacency matrix:
 * <p>
 * 1) Initialize a matrix with 0s.
 * 2) Iterate over the vertices in the adjacency list
 * 3) For every j-th vertex in the adjacency list, traverse its edges.
 * 4) For each vertex i with which the j-th vertex has an edge, set matrix[i][j] = 1.
 */
public class ConvertAdjacencyListToAdjacencyMatrix {
    public static void main(String[] args) {
        int V = 5;

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Inserting edges
        insert(adjList, 0, 1);
        insert(adjList, 0, 4);
        insert(adjList, 1, 0);
        insert(adjList, 1, 2);
        insert(adjList, 1, 3);
        insert(adjList, 1, 4);
        insert(adjList, 2, 1);
        insert(adjList, 2, 3);
        insert(adjList, 3, 1);
        insert(adjList, 3, 2);
        insert(adjList, 3, 4);
        insert(adjList, 4, 0);
        insert(adjList, 4, 1);
        insert(adjList, 4, 3);

        for (int i = 0; i < adjList.size(); i++) {
            System.out.println(i + ": " + adjList.get(i));
        }

        int[][] matrix = convert(adjList);
        System.out.println("");
    }

    public static int[][] convert(List<List<Integer>> adjList) {
        //initiation
        int numOfVertices = adjList.size();
        int[][] matrix = new int[numOfVertices][numOfVertices]; //filled by 0s


        //fill
        for (int i = 0; i < numOfVertices; i++) {
            for (int j : adjList.get(i)) {
                matrix[i][j] = 1;
            }
        }

        return matrix;
    }


    static void insert(List<List<Integer>> adjList, int u, int v) {
        // Insert a vertex v to vertex u
        adjList.get(u).add(v);
    }
}
