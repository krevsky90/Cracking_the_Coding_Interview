package live_coding.stripe.flights;

import java.util.Collections;
import java.util.List;

public class Route {
    //this is END node which was reached by set of flights
    private final String node;
    private final List<Flight> flights;
    private int totalCost;

    public static final Route NO_ROUTE = new Route("", Collections.emptyList());

    public Route(String node, List<Flight> route) {
        this.node = node;
        this.flights = route;
        this.totalCost = 0;
        for (Flight f : route) {
            totalCost += f.getCost();
        }
    }

    public String getNode() {
        return node;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public int getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        if (flights == null || flights.isEmpty()) return "";

        StringBuilder sbRoutes = new StringBuilder("{'route': '");
        StringBuilder sbMethods = new StringBuilder("'method': '");

        Flight initialFlight = flights.get(0);

        sbRoutes.append(initialFlight.getSource())
                .append(" -> ")
                .append(initialFlight.getDest());

        sbMethods.append(initialFlight.getAirline());

//        int cost = initialFlight.getCost();

        for (int i = 1; i < flights.size(); i++) {
            sbRoutes.append(" -> ").append(flights.get(i).getDest());
            sbMethods.append(" -> ").append(flights.get(i).getAirline());
//            cost += route.get(i).getCost();
        }

        sbRoutes.append("', ");
        sbMethods.append("', ");

        return sbRoutes.append(sbMethods).append("'cost': ").append(totalCost).toString();
    }
}
