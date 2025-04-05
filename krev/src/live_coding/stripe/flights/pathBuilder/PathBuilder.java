package live_coding.stripe.flights.pathBuilder;

import live_coding.stripe.flights.Flight;
import live_coding.stripe.flights.Route;

import java.util.List;

public interface PathBuilder {
    Route path(String source, String destination, List<Flight> flightList);
}
