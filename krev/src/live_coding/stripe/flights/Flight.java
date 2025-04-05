package live_coding.stripe.flights;

public class Flight {
    private final String source;
    private final String dest;
    private final String airline;
    private final int cost;

    public Flight(String source, String dest, String airline, int cost) {
        this.source = source;
        this.dest = dest;
        this.airline = airline;
        this.cost = cost;
    }

    public String getSource() {
        return source;
    }

    public String getDest() {
        return dest;
    }

    public String getAirline() {
        return airline;
    }

    public int getCost() {
        return cost;
    }
}
