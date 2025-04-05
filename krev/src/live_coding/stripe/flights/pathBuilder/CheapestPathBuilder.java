package live_coding.stripe.flights.pathBuilder;

import live_coding.stripe.flights.Flight;
import live_coding.stripe.flights.Route;

import java.util.*;

import static live_coding.stripe.flights.Route.NO_ROUTE;

//based on Dijkstra algorithm
public class CheapestPathBuilder extends AbstractPathBuilder {

    @Override
    public Route path(String source, String destination, Map<String, List<Flight>> adjMap) {
        // Priority queue to process nodes with the smallest cost first
        PriorityQueue<Route> pq = new PriorityQueue<>((a, b) -> a.getTotalCost() - b.getTotalCost());
        pq.add(new Route(source, new ArrayList<>()));

        // Map to track the minimum cost to reach each node
        Map<String, Integer> minCosts = new HashMap<>();
        minCosts.put(source, 0);

        while (!pq.isEmpty()) {
            Route route = pq.poll();

            // If we reach the destination, return the result
            if (route.getNode().equals(destination)) {
                return route;
            }

            // Explore neighbors
            for (Flight flight : adjMap.getOrDefault(route.getNode(), Collections.emptyList())) {
                String newSource = flight.getDest();
                int newCost = route.getTotalCost() + flight.getCost();
                //add new Route from source to newSource only if newSource is not in minCosts OR is newCost < existing cost to newSource
                if (!minCosts.containsKey(newSource) || minCosts.get(newSource) > newCost) {
                    List<Flight> newFlights = new ArrayList<>(route.getFlights());
                    newFlights.add(flight);
                    pq.add(new Route(newSource, newFlights));
                    minCosts.put(newSource, newCost);
                }
            }
        }

        return NO_ROUTE;
    }
}
