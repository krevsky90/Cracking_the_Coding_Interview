package data_structures.chapter4_trees_n_graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * p.122
 * 4.8 Build Order:
 * You are given a list of projects and a list of dependencies (which is a list of pairs of
 * projects, where the second project is dependent on the first project). All of a project's dependencies
 * must be built before the project is. Find a build order that will allow the projects to be built. If there
 * is no valid build order, return an error.
 * EXAMPLE
 * Input:
 * projects: a, b, c, d, e, f
 * dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
 * Output: f, e, a, b, d, c
 * Hints: #26, #47, #60, #85, #125, #133
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem4_7 {
    public static void main(String[] args) {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        List<Node> projects = new ArrayList<>(Arrays.asList(a, b, c, d, e, f, g));


        Pair fb = new Pair(f, b);
        Pair fc = new Pair(f, c);
        Pair fa = new Pair(f, a);
        Pair ca = new Pair(c, a);
        Pair ba = new Pair(b, a);
        Pair ae = new Pair(a, e);
        Pair be = new Pair(b, e);
        Pair dg = new Pair(d, g);

        List<Pair> dependencies = new ArrayList<>(Arrays.asList(fb, fc, fa, ca, ba, ae, be, dg));

        List<Node> order = buildOrder(projects, dependencies);
        if (order != null) {
            StringBuilder sb = new StringBuilder();
            for (Node n : order) {
                sb.append(n.toString() + " ");
            }

            System.out.println(sb.toString());
        }
    }

    public static List<Node> buildOrder(List<Node> projects, List<Pair> dependencies) {
        List<Node> result = new ArrayList<>();

        int numberOfNodes = projects.size();
        while (result.size() < numberOfNodes) {
            //add nodes without ingoing relations
            List<Node> nodesToAdd = addStartNodes(projects, dependencies);
            if (nodesToAdd.isEmpty()) {
                //we don't have nodes that can be added to the order but not all projects-nodes are used
                //it means that we have cycle -> throw the Error
                System.out.println("Error");
                return null;
            }
            result.addAll(nodesToAdd);

            //remove dependencies that have first value that belongs to nodesToAdd
            removeDependencies(dependencies, nodesToAdd);
        }

        return result;
    }

    //find the nodes that are not the second in the dependencies
    //it may it separate node (is not related to any other nodes)
    //or the node that doesn't depend on the other nodes, but some of them depend on this node
    private static List<Node> addStartNodes(List<Node> projects, List<Pair> dependencies) {
        List<Node> result = new ArrayList<>();
        result.addAll(projects);

        for (Pair p : dependencies) {
            result.remove(p.getSecond());
        }

        projects.removeAll(result);

        return result;
    }

    private static void removeDependencies(List<Pair> dependencies, List<Node> firstNodes) {
        Iterator<Pair> it = dependencies.iterator();
        while (it.hasNext()) {
            Pair p = it.next();
            for (Node first : firstNodes) {
                if (p.getFirst() == first) {
                    it.remove();
                    break;
                }
            }
        }
    }

    private static class Pair {
        private Node first;
        private Node second;

        public Pair(Node f, Node s) {
            first = f;
            second = s;
        }

        public Node getFirst() {
            return first;
        }

        public Node getSecond() {
            return second;
        }

        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    private static class Node {
        public String value;
        public Node left;
        public Node right;

        public Node(String x) {
            value = x;
        }

        public String toString() {
            return "(" + value + ")";
        }
    }
}
