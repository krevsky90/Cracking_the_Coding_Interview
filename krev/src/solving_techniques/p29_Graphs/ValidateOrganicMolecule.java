package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * https://leetcode.com/discuss/interview-experience/6084864/Google-L4-interview-expereince
 * <p>
 * Round 2:
 * We define an organic molecule by a list of atoms, and a list of bonds between any two of them. For example, the following formaldehyde molecule:
 * <p>
 * H
 * |
 * H-C=O
 * is represented as:
 * atoms: {1: 'C', 2: 'H', 3: 'H', 4: 'O'}
 * bonds: [[1, 2], [1, 3], [1, 4], [1, 4]]
 * The input does not have to be strictly in this format, as long as it delivers the information.
 * <p>
 * Given an input representing a molecule consisting only of Carbon, Oxygen and Hydrogen atoms, determine if it's valid.
 * A molecule is valid if every atom has the required number of bonds:
 * C atoms require exactly 4 bonds
 * O atoms require exactly 2 bonds
 * H atoms require exactly 1 bond
 * <p>
 * FOLLOW UP QUESTION:
 * <p>
 * We assume that each input represents one molecule. Multiple, disconnected molecules are considered invalid. Consider the following example:
 * <p>
 * O=C=O H-H
 * <p>
 * <p>
 * which would represented as
 * atoms: {1: 'C', 2: 'O', 3: 'O', 4: 'H', 5: 'H'}
 * bonds: [[1, 2], [1, 2], [1, 3], [1, 3], [4, 5]]
 * This input is invalid because it consists of 2 separate molecules. How would you modify your program to detect this situation?
 */
public class ValidateOrganicMolecule {
    /**
     * KREVSKY SOLUTION
     *
     * time to solve ~ 18 mins
     *
     * 2 attempt:
     * - there was incorrect condition: if (atoms.size() != bonds.length) return false
     */
    public boolean validate(Map<Integer, Character> atoms, int[][] bonds) {
        if (atoms == null || bonds == null) return true;    //as you wish
        if (atoms.size() == 0 || bonds.length == 0) return true;    //as you wish

        Map<Character, Integer> reqMap = new HashMap<>();
        reqMap.put('C', 4);
        reqMap.put('O', 2);
        reqMap.put('H', 1);

        Map<Integer, List<Integer>> adjMap = new HashMap<>();

        for (int[] bond : bonds) {
            adjMap.putIfAbsent(bond[0], new ArrayList<>());
            adjMap.putIfAbsent(bond[1], new ArrayList<>());
            adjMap.get(bond[0]).add(bond[1]);
            adjMap.get(bond[1]).add(bond[0]);
        }

        for (int key : adjMap.keySet()) {
            Character c = atoms.get(key);
            int reqNumber = reqMap.get(c);
            int actualNumber = adjMap.getOrDefault(key, new ArrayList<>()).size();
            if (reqNumber != actualNumber) return false;
        }

        //follow up:
        //do BFS traversal from some node. If the amount of visited nodes != atoms.size() => return false;
        Queue<Integer> q = new LinkedList<>();
        q.add(bonds[0][0]);
        Set<Integer> visited = new HashSet<>();
        visited.add(bonds[0][0]);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int neighbour : adjMap.getOrDefault(cur, new ArrayList<>())) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    q.add(neighbour);
                }
            }
        }

        return visited.size() == atoms.size();
    }

    public static void main(String[] args) {
        ValidateOrganicMolecule obj = new ValidateOrganicMolecule();

        Map<Integer, Character> atomsNotOk = new HashMap<>();
        atomsNotOk.put(1, 'C');
        atomsNotOk.put(2, 'O');
        atomsNotOk.put(3, 'O');
        atomsNotOk.put(4, 'H');
        atomsNotOk.put(5, 'H');
        int[][] bondsNotOk = {{1, 2}, {1, 2}, {1, 3}, {1, 3}, {4, 5}};

        Map<Integer, Character> atomsOk = new HashMap<>();
        atomsOk.put(1, 'C');
        atomsOk.put(2, 'H');
        atomsOk.put(3, 'H');
        atomsOk.put(4, 'O');
        int[][] bondsOk = {{1, 2}, {1, 3}, {1, 4}, {1, 4}};

        System.out.println(obj.validate(atomsNotOk, bondsNotOk));
        System.out.println(obj.validate(atomsOk, bondsOk));
    }
}
