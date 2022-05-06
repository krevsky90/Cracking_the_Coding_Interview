package data_structures.chapter4_trees_n_graphs;

import java.util.LinkedList;

/**
 * p.121
 * Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
 * route between two nodes.
 * Hints: #127
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem4_1 {
    public static void main(String[] args) {
        Problem4_1_Node n0 = new Problem4_1_Node(0);
        Problem4_1_Node n1 = new Problem4_1_Node(1);
        Problem4_1_Node n2 = new Problem4_1_Node(2);
        Problem4_1_Node n3 = new Problem4_1_Node(3);

        Problem4_1_Node n4 = new Problem4_1_Node(4);
        Problem4_1_Node n5 = new Problem4_1_Node(5);
        Problem4_1_Node n6 = new Problem4_1_Node(6);

        n0.children.add(n1);
        n1.children.add(n2);
        n2.children.add(n0);
        n2.children.add(n3);
        n3.children.add(n2);

        n4.children.add(n6);
        n5.children.add(n4);
        n6.children.add(n5);

        //for original solution
        Problem4_1_Graph graph = new Problem4_1_Graph();
        graph.nodes = new Problem4_1_Node[7];
        graph.nodes[0] = n0;
        graph.nodes[1] = n1;
        graph.nodes[2] = n2;
        graph.nodes[3] = n3;
        graph.nodes[4] = n4;
        graph.nodes[5] = n5;
        graph.nodes[6] = n6;

        System.out.println(searchKrevsky(n4, n3));
    }

    public static boolean searchKrevsky(Problem4_1_Node x, Problem4_1_Node y) {
        if (x == y) {
            return true;
        }

        LinkedList<Problem4_1_Node> queue = new LinkedList<>();
        queue.add(x);
        Problem4_1_Node temp;
        while (!queue.isEmpty()) {
            temp = queue.remove();
            if (temp == y) {
                return true;
            }
            temp.marked = true;

            for (Problem4_1_Node n : temp.children) {
                if (!n.marked) {
                    queue.add(n);
                }
            }
        }

        return false;
    }

    /**
     * p.253 (original solution)
     */
    public static boolean search(Problem4_1_Graph graph, Problem4_1_Node start, Problem4_1_Node end) {
        if (start == end) return true;

        LinkedList<Problem4_1_Node> q = new LinkedList<>();
        for (Problem4_1_Node u : graph.nodes) {
            u.state = Problem4_1_States.Unvisited;
        }
        start.state = Problem4_1_States.Visiting;
        q.add(start);
        Problem4_1_Node u;
        while(!q.isEmpty()) {
            u = q.removeFirst();    //i.e. dequeue()
            if(u != null) {
                for (Problem4_1_Node v : u.children) {
                    if (v.state == Problem4_1_States.Unvisited) {
                        if (v == end) {
                            return true;
                        } else {
                            v.state = Problem4_1_States.Visiting;
                            q.add(v);
                        }
                    }
                }
                u.state = Problem4_1_States.Visited;
            }
        }
        return false;
    }
}
