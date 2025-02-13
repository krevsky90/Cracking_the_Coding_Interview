package streams.terminal;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class SummaryStatisticsExample {
    /**
     * src: https://www.geeksforgeeks.org/intstream-summarystatistics-java/
     *
     * to get IntSummaryStatistics{count=4, sum=22, min=4, average=5.500000, max=7}
     */
    public static void main(String[] args) {
        IntStream stream = IntStream.of(4, 5, 6, 7);

        IntSummaryStatistics stat = stream.summaryStatistics();
        System.out.println(stat);
    }
}
