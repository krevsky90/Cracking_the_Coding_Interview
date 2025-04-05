package live_coding.stripe.flights;

import live_coding.stripe.flights.pathBuilder.CheapestPathBuilder;
import live_coding.stripe.flights.pathBuilder.DfsPathBuilder;
import live_coding.stripe.flights.pathBuilder.PathBuilder;
import live_coding.stripe.flights.pathBuilder.ShortestPathBuilder;

import java.util.List;

public class Main {
    public static final String testString = "UK:US:FedEx:4,UK:FR:Jet1:2,US:UK:RyanAir:8,CA:UK:CanadaAir:8";
    public static final String testStringForDijkstra = "US:UK:RyanAir:8,US:UK:FedEx:4,UK:FR:Jet1:2,CA:UK:CanadaAir:8";

    public static void main(String[] args) {
        FlightParser flightString = new FlightParser();
        List<Flight> flights = flightString.parseData(testStringForDijkstra);

        PathBuilder pathBuilder = new DfsPathBuilder();
        System.out.println(pathBuilder.path("US", "FR", flights));  //expected: {'route': 'US -> UK -> FR', 'method': 'RyanAir -> Jet1', 'cost': 10

        //change path builder
        pathBuilder = new ShortestPathBuilder();
        System.out.println(pathBuilder.path("US", "FR", flights));   //expected: {'route': 'US -> UK -> FR', 'method': 'RyanAir -> Jet1', 'cost': 10

        //change path builder
        pathBuilder = new CheapestPathBuilder();
        System.out.println(pathBuilder.path("US", "FR", flights));   //expected: {'route': 'US -> UK -> FR', 'method': 'FedEx -> Jet1', 'cost': 6
    }
}
