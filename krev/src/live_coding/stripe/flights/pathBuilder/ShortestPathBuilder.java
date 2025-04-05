package live_coding.stripe.flights.pathBuilder;

import live_coding.stripe.flights.Flight;
import live_coding.stripe.flights.Route;

import java.util.*;

import static live_coding.stripe.flights.Route.NO_ROUTE;

/**
 * idea: use BFS
 * idea #2: keep Route objects in Queue, where Route is {source, string route, string method, cost}
 * i.e. we track the whole chain of nodes and the whole chain of methods inside once Route object
 * => we don't need to use backtracking or smth like this (as shown in DFS)
 */
public class ShortestPathBuilder extends AbstractPathBuilder {
    @Override
    public Route path(String source, String destination, Map<String, List<Flight>> adjMap) {
        Queue<Route> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Initialize the queue with the starting node
        queue.add(new Route(source, new ArrayList<>()));
        visited.add(source);

        while (!queue.isEmpty()) {
            Route current = queue.poll();

            // If we reach the destination, return the result
            if (current.getNode().equals(destination)) {
                return current;
            }

            // Explore neighbors
            for (Flight flight : adjMap.getOrDefault(current.getNode(), Collections.emptyList())) {
                String newSource = flight.getDest();
                if (!visited.contains(newSource)) {
                    visited.add(newSource);
                    List<Flight> newFlights = new ArrayList<>(current.getFlights());
                    newFlights.add(flight);
                    queue.add(new Route(newSource, newFlights));
                }
            }
        }

        return NO_ROUTE;
    }
}