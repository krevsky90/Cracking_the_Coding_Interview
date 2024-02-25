package solving_techniques.p20_TopologicalSort;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a5c70c5f272fd60104f655
 *
 *
 *
 */
public class AllTasksSchedulingOrders {
    public static void main(String[] args) {
        System.out.println("Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]");
        int vertices1 = 4;
        int [][] edges1 = {{3,2},{3,0},{2,0},{2,1}};
        List<List<Integer>> result1 = getAllTopologicalSortsBFS(vertices1, edges1);
        for (List<Integer> combination : result1) {
            combination.stream().forEach(x -> System.out.print(x + " "));
            System.out.println("");
        }

        System.out.println("Input: Vertices=7, Edges=[6, 4], [6, 2], [5, 3], [5, 4], [3, 0], [3, 1], [3, 2], [4, 1]");
        int vertices3 = 7;
        int [][] edges3 = {{6,4},{6,2},{5,3},{5,4},{3,0},{3,1},{3,2},{4,1}};
        System.out.println("result 3 DFS");
        List<List<Integer>> result3 = getAllTopologicalSortsBFS(vertices3, edges3);
        for (List<Integer> combination : result3) {
            combination.stream().forEach(x -> System.out.print(x + " "));
            System.out.println("");
        }
    }

    //info about solution: https://www.geeksforgeeks.org/all-topological-sorts-of-a-directed-acyclic-graph/


    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) get layers of source vertices using BFS
     * 2) for each layer (i.e. list of numbers) generate all possible combinations
     * 3) generate all possible combinations by taking one combination from each layer (see p.2)
     * time to solve ~ 25 mins (topological part) + 50 mins (permutation part) ~ 75 mins
     *
     * 3 attempts:
     * - incorrect condition instead of "tempResult.size() == arr.size()" in generateNumbersCombinations
     * - forgot "if (tempResult.contains(arr.get(i)))" condition in generateNumbersCombinations
     */
    public static List<List<Integer>> getAllTopologicalSortsBFS(int vertices, int [][] edges) {
        List<List<Integer>> result = new ArrayList<>();
        //initialize
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> vertexToCountMap = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            graph.put(i, new HashSet<>());
            vertexToCountMap.put(i, 0);
        }

        //build
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            vertexToCountMap.put(edge[1], vertexToCountMap.get(edge[1]) + 1);
        }

        Queue<Integer> sourceQueue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (vertexToCountMap.get(i) == 0) {
                sourceQueue.add(i);
            }
        }

        //to store groups (layers) of source vertices occured after each removing of the previous layer of source vertices
        List<List<Integer>> parts = new ArrayList<>();
        while (!sourceQueue.isEmpty()) {
            int countSources = sourceQueue.size();
            List<Integer> part = new ArrayList<>(countSources);
            int tempCount = 0;
            while (tempCount < countSources) {
                int tempSource = sourceQueue.poll();
                part.add(tempSource);
                tempCount++;

                Set<Integer> children = graph.get(tempSource);
                for (Integer child : children) {
                    vertexToCountMap.put(child, vertexToCountMap.get(child) - 1);
                    if (vertexToCountMap.get(child) == 0) {
                        sourceQueue.add(child);
                    }
                }
            }

            parts.add(part);
        }

        //optional validation: IF vertices == total amount of Integer values in parts structure

        Map<Integer, List<List<Integer>>> allParts = new HashMap<>();	//number (#) of source layer => list of all combinations of vertices of this layer
        for (int i = 0; i < parts.size(); i++) {
            List<List<Integer>> combinations = new ArrayList<>();
            generateNumbersCombinations(parts.get(i), new ArrayList<>(), combinations);

            allParts.put(i, combinations);
        }

        generatePartsCombinations(allParts, 0, new ArrayList<>(), result);

        return result;
    }

    private static void generateNumbersCombinations(List<Integer> arr, List<Integer> tempResult, List<List<Integer>> result) {
        if (tempResult.size() == arr.size()) {
            result.add(new ArrayList<>(tempResult));
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            if (tempResult.contains(arr.get(i))) continue;

            tempResult.add(arr.get(i));
            generateNumbersCombinations(arr,  tempResult, result);
            tempResult.remove(tempResult.size() - 1);
        }
    }

    private static void generatePartsCombinations(Map<Integer, List<List<Integer>>> allParts, int start, List<List<Integer>> tempResult, List<List<Integer>> result) {
        if (start == allParts.size()) {
            //create plane list of the numbers that are in tempResult
            List<Integer> numberList = new ArrayList<>();
            for (List<Integer> list : tempResult) {
                numberList.addAll(list);
            }

            result.add(numberList);
            return;
        }

        List<List<Integer>> arr = allParts.get(start);
        for (int i = 0; i < arr.size(); i++) {
            tempResult.add(arr.get(i));
            generatePartsCombinations(allParts, start + 1, tempResult, result);
            tempResult.remove(tempResult.size() - 1);
        }
    }
}
