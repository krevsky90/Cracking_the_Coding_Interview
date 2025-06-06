package solving_techniques.p29_Graphs.from_discuss;

import java.util.*;

/**
 * info:
 * https://leetcode.com/discuss/post/6811191/coderpad-round-with-goldman-sachs-by-ano-ub41/
 * <p>
 * Given a source and destination, print all the stations in the shortest path between them.
 */
public class ShortestPathInGraph_GoldmanSachs {
    static class Graph {
        private Map<String, List<String>> adj = new HashMap<>();

        public void addEdge(String u, String v) {
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u); // Undirected
        }

        // BFS to find shortest path from source to destination
        public List<String> shortestPath(String src, String dest) {
            if (!adj.containsKey(src) || !adj.containsKey(dest)) {
                return Collections.emptyList();
            }

            Queue<String> queue = new LinkedList<>();
            Map<String, String> parent = new HashMap<>();
            Set<String> visited = new HashSet<>();

            queue.add(src);
            visited.add(src);
            parent.put(src, null);

            // BFS
            while (!queue.isEmpty()) {
                String current = queue.poll();

                if (current.equals(dest)) {
                    break;
                }

                for (String neighbor : adj.getOrDefault(current, Collections.emptyList())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        parent.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }

            // If destination not reached
            if (!parent.containsKey(dest)) {
                return Collections.emptyList();
            }

            // Reconstruct path
            List<String> path = new ArrayList<>();
            String node = dest;
            while (node != null) {
                path.add(node);
                node = parent.get(node);
            }

            Collections.reverse(path);
            return path;
        }

        public static void main(String[] args) {
            ShortestPathInGraph_GoldmanSachs obj = new ShortestPathInGraph_GoldmanSachs();
            Graph metro = new Graph();

            // Add connections (like a metro map)
            metro.addEdge("A", "B");
            metro.addEdge("A", "C");
            metro.addEdge("B", "D");
            metro.addEdge("C", "E");
            metro.addEdge("D", "E");
            metro.addEdge("D", "F");
            metro.addEdge("E", "F");

            String source = "A";
            String destination = "F";

            List<String> path = metro.shortestPath(source, destination);

            if (path.isEmpty()) {
                System.out.println("No path found from " + source + " to " + destination);
            } else {
                System.out.println("Shortest path from " + source + " to " + destination + ":");
                System.out.println(String.join(" -> ", path));
            }
        }
    }
}
