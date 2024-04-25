package solving_techniques.p29_Graphs.theory;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/convert-adjacency-matrix-to-adjacency-list-representation-of-graph/
 *
 * Input: arr[][] = [ [0, 0, 1], [0, 0, 1], [1, 1, 0] ]
 * Output: The adjacency list is:
 * 0 -> 2
 * 1 -> 2
 * 2 -> 0 -> 1
 *
 * Input: arr[][] = [ [0, 1, 0, 0, 1], [1, 0, 1, 1, 1], [0, 1, 0, 1, 0], [0, 1, 1, 0, 1], [1, 1, 0, 1, 0] ]
 * Output: The adjacency list is:
 * 0 -> 1 -> 4
 * 1 -> 0 -> 2 -> 3 -> 4
 * 2 -> 1 -> 3
 * 3 -> 1 -> 2 -> 4
 * 4 -> 0 -> 1 -> 3
 *
 * Adjacency List: An array of lists is used.
 * The size of the array is equal to the number of vertices.
 * Let the array be array[]. An entry array[i] represents the list of vertices adjacent to the ith vertex.
 * To convert an adjacency matrix to the adjacency list.
 * Create an array of lists and traverse the adjacency matrix.
 * If for any cell (i, j) in the matrix ?mat[i][j] != 0?,
 * it means there is an edge from i to j, so insert j in the list at i-th position in the array of lists.
 */
public class ConvertAdjacencyMatrixToAdjacencyList {
    public static void main(String[] args) {
        int[][] arr2 = {
                {0, 1, 0, 0, 1},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0}
        };

        List<List<Integer>> adjList2 = convert(arr2);
        for (int i = 0; i < adjList2.size(); i++) {
            System.out.println(i + ": " + adjList2.get(i));
        }
    }

    //NOTE: as I understand, a.length = a[0].length!
    public static List<List<Integer>> convert(int[][] a) {
        //initiation
        int numOfVertices = a.length;

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numOfVertices; i++) {
            adjList.add(new ArrayList<>());
        }

        //convert
        for (int i = 0; i < numOfVertices; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != 0) {
                    //add j vertex to the list of i-th vertex
                    adjList.get(i).add(j);
                }
            }
        }

        return adjList;
    }
}
