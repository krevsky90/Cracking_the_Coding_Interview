package live_coding.stripe.flights.pathBuilder;

import live_coding.stripe.flights.Flight;
import live_coding.stripe.flights.Route;

import java.util.*;

public abstract class AbstractPathBuilder implements PathBuilder {
    public Route path(String source, String destination, List<Flight> flightList) {
        if (source == null) throw new IllegalArgumentException("Source is null");
        if (destination == null) throw new IllegalArgumentException("destination is null");
        if (flightList == null || flightList.isEmpty()) return new Route(source, Collections.emptyList());

        if (source.equals(destination)) return new Route(source, Collections.emptyList());

        Map<String, List<Flight>> adjMap = buildGraph(flightList);

        return path(source, destination, adjMap);
    }

    protected Map<String, List<Flight>> buildGraph(List<Flight> flights) {
        Map<String, List<Flight>> graph = new HashMap<>();
        for (Flight flight : flights) {
            graph.putIfAbsent(flight.getSource(), new ArrayList<>());
            graph.get(flight.getSource()).add(flight);
        }
        return graph;
    }

    abstract Route path(String source, String destination, Map<String, List<Flight>> adjMap);
}
