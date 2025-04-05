package live_coding.stripe.flights;

import java.util.List;

import static live_coding.stripe.flights.Main.testString;

public class DataRetriever {
    public static void main(String[] args) {
        DataRetriever obj = new DataRetriever();
        obj.runTests();
    }

    /**
     * @param source
     * @param dest
     * @param flights
     * @return the first result that is found
     */
    public Integer getCost(String source, String dest, List<Flight> flights) {
        for (Flight flight : flights) {
            if (flight.getSource().equals(source) && flight.getDest().equals(dest)) {
                return flight.getCost();
            }
        }

        return null;  //means there is not flight with given source and dest
    }

    public void runTests() {
        testGetCostNoFlights();
        testGetCostPositiveTest();
    }

    public void testGetCostNoFlights() {
        FlightParser parser = new FlightParser();
        List<Flight> flights = parser.parseData(testString);
        Integer cost = getCost("US", "UX", flights);
        System.out.println("cost = " + cost);
    }

    public void testGetCostPositiveTest() {
        FlightParser parser = new FlightParser();
        List<Flight> flights = parser.parseData(testString);
        Integer cost = getCost("UK", "FR", flights);
        System.out.println("cost = " + cost);
    }
}
