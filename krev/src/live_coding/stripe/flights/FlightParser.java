package live_coding.stripe.flights;


import java.util.ArrayList;
import java.util.List;

import static live_coding.stripe.flights.Main.testString;

public class FlightParser {
    public static void main(String[] args) {
        FlightParser obj = new FlightParser();
        obj.runTests();
    }

    public List<Flight> parseData(String data) throws IllegalArgumentException {
        List<Flight> flights = new ArrayList<>();

        if (data == null) throw new IllegalArgumentException("Data can't be null");

        String[] split = data.split(",");
        if (split.length == 0) throw new IllegalArgumentException("No one flight is found in data");

        for (String s : split) {
            String[] flightSplit = s.split(":");
            if (flightSplit.length != 4) throw new IllegalArgumentException("Incorrect format of flight data");

            if (!validateStringParameter(flightSplit[0]))
                throw new IllegalArgumentException("Invalid source value: '" + flightSplit[0] + "'");

            if (!validateStringParameter(flightSplit[1]))
                throw new IllegalArgumentException("Invalid dest value: '" + flightSplit[1] + "'");

            if (!validateStringParameter(flightSplit[2]))
                throw new IllegalArgumentException("Invalid airline value: '" + flightSplit[2] + "'");

            try {
                int cost = Integer.valueOf(flightSplit[3]);

                flights.add(
                        new Flight(flightSplit[0],
                                flightSplit[1],
                                flightSplit[2],
                                cost
                        )
                );
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Can't convert '" + flightSplit[3] + "' to valid cost value");
            }
        }

        return flights;
    }

    private boolean validateStringParameter(String s) {
        return !(s == null || s.isEmpty() || s.charAt(0) == ' ' || s.charAt(s.length() - 1) == ' ');
    }

    public void runTests() {
        testNullData();
        testIncorrectAmountOfColumns();
        testIncorrectCostValue();
        testEmptySourceValue();
        testCorrectData();
    }

    public void testNullData() {
        try {
            parseData(null);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void testIncorrectAmountOfColumns() {
        try {
            parseData("UK:US:FedEx:5,UK:US:FedEx:4:3");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void testIncorrectCostValue() {
        try {
            parseData("UK:US:FedEx:TTT");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void testEmptySourceValue() {
        try {
            parseData(":US:FedEx:TTT");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void testCorrectData() {
        try {
            parseData(testString);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
