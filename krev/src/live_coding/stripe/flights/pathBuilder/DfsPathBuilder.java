package live_coding.stripe.flights.pathBuilder;

import live_coding.stripe.flights.Flight;
import live_coding.stripe.flights.Route;

import java.util.*;

public class DfsPathBuilder extends AbstractPathBuilder {
    public Route path(String source, String destination, Map<String, List<Flight>> adjMap) {
        List<Flight> tempResult = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfs(source, destination, adjMap, visited, tempResult);

        return new Route(source, tempResult);
    }

    private boolean dfs(String source, String destination, Map<String, List<Flight>> adjMap, Set<String> visited, List<Flight> tempResult) {
        visited.add(source);
        if (source.equals(destination)) return true;

        for (Flight tempFlight : adjMap.getOrDefault(source, new ArrayList<>())) {
            String newSource = tempFlight.getDest();
            if (visited.contains(newSource)) continue;

            tempResult.add(tempFlight);

            if (dfs(newSource, destination, adjMap, visited, tempResult)) {
                return true;
            }

            tempResult.remove(tempResult.size() - 1);   //backtracking
        }

        return false;
    }
}