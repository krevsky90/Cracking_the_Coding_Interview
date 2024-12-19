package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 3387. Maximize Amount After Two Days of Conversions (medium)
 * https://leetcode.com/problems/maximize-amount-after-two-days-of-conversions
 * <p>
 * #Company:
 * <p>
 * You are given a string initialCurrency, and you start with 1.0 of initialCurrency.
 * <p>
 * You are also given four arrays with currency pairs (strings) and rates (real numbers):
 * <p>
 * pairs1[i] = [startCurrencyi, targetCurrencyi] denotes that you can convert from startCurrencyi to targetCurrencyi at a rate of rates1[i] on day 1.
 * pairs2[i] = [startCurrencyi, targetCurrencyi] denotes that you can convert from startCurrencyi to targetCurrencyi at a rate of rates2[i] on day 2.
 * Also, each targetCurrency can be converted back to its corresponding startCurrency at a rate of 1 / rate.
 * You can perform any number of conversions, including zero, using rates1 on day 1, followed by any number of additional conversions, including zero, using rates2 on day 2.
 * <p>
 * Return the maximum amount of initialCurrency you can have after performing any number of conversions on both days in order.
 * <p>
 * Note: Conversion rates are valid, and there will be no contradictions in the rates for either day. The rates for the days are independent of each other.
 * <p>
 * Example 1:
 * Input: initialCurrency = "EUR", pairs1 = [["EUR","USD"],["USD","JPY"]], rates1 = [2.0,3.0], pairs2 = [["JPY","USD"],["USD","CHF"],["CHF","EUR"]], rates2 = [4.0,5.0,6.0]
 * <p>
 * Output: 720.00000
 * <p>
 * Explanation:
 * <p>
 * To get the maximum amount of EUR, starting with 1.0 EUR:
 * <p>
 * On Day 1:
 * Convert EUR to USD to get 2.0 USD.
 * Convert USD to JPY to get 6.0 JPY.
 * On Day 2:
 * Convert JPY to USD to get 24.0 USD.
 * Convert USD to CHF to get 120.0 CHF.
 * Finally, convert CHF to EUR to get 720.0 EUR.
 * Example 2:
 * <p>
 * Input: initialCurrency = "NGN", pairs1 = [["NGN","EUR"]], rates1 = [9.0], pairs2 = [["NGN","EUR"]], rates2 = [6.0]
 * <p>
 * Output: 1.50000
 * <p>
 * Explanation:
 * <p>
 * Converting NGN to EUR on day 1 and EUR to NGN using the inverse rate on day 2 gives the maximum amount.
 * <p>
 * Example 3:
 * <p>
 * Input: initialCurrency = "USD", pairs1 = [["USD","EUR"]], rates1 = [1.0], pairs2 = [["EUR","JPY"]], rates2 = [10.0]
 * <p>
 * Output: 1.00000
 * <p>
 * Explanation:
 * <p>
 * In this example, there is no need to make any conversions on either day.
 * <p>
 * Constraints:
 * <p>
 * 1 <= initialCurrency.length <= 3
 * initialCurrency consists only of uppercase English letters.
 * 1 <= n == pairs1.length <= 10
 * 1 <= m == pairs2.length <= 10
 * pairs1[i] == [startCurrencyi, targetCurrencyi]
 * pairs2[i] == [startCurrencyi, targetCurrencyi]
 * 1 <= startCurrencyi.length, targetCurrencyi.length <= 3
 * startCurrencyi and targetCurrencyi consist only of uppercase English letters.
 * rates1.length == n
 * rates2.length == m
 * 1.0 <= rates1[i], rates2[i] <= 10.0
 * The input is generated such that there are no contradictions or cycles in the conversion graphs for either day.
 * The input is generated such that the output is at most 5 * 10^10.
 */
public class MaximizeAmountAfterTwoDaysOfConversions {
    public static void main(String[] args) {
        MaximizeAmountAfterTwoDaysOfConversions obj = new MaximizeAmountAfterTwoDaysOfConversions();

        String initialCurrency = "EUR";
        List<List<String>> pairs1 = Arrays.asList(Arrays.asList("EUR", "USD"), Arrays.asList("USD", "JPY"));

        double[] rates1 = {2.0, 3.0};

        List<List<String>> pairs2 = Arrays.asList(Arrays.asList("JPY", "USD"), Arrays.asList("USD", "CHF"), Arrays.asList("CHF", "EUR"));

        double[] rates2 = {4.0, 5.0, 6.0};

        obj.maxAmount(initialCurrency, pairs1, rates1, pairs2, rates2);
    }

    /**
     * NOT SOLVED by myself
     * idea:
     * 1) use 2 directed graphs, for each day separately, each rate means 2 edges with rate and 1/rate weights
     * 2) calculate max amount for each node (currency) for day 1, starting from node = initialCurrency
     * NOTE: since there are no cycles, we can use simple BFS algo with Queue, without Priority (as it is used in Dijkstra's case)
     * 3) for each node in the graph2 (for day2) start the same bfs method with max amount taken from the result of the 1st day
     * after each launch of BFS we update max amount for the initialCurrency
     * <p>
     * and finally return it
     * <p>
     * time to handle day 1 ~ O(V1 + E1), where V1 = amount of currencies, E1 - amount of rates1*2
     * time to handle day 2 ~ O(V2*(V2 + E2))
     * <p>
     * time ~ O(V1 + E1) + O(V2*(V2 + E2))
     * <p>
     * space ~ O(V1 + E1) + O(V2 + E2)
     * <p>
     * many attempts
     * - forgot 'OrDefault' part when call curToMaxAmount2Temp.getOrDefault(initialCurrency, 0.0)
     * <p>
     * BEATS ~ 35%
     */
    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        Map<String, Set<Pair>> graph1 = buildGraph(pairs1, rates1);
        Map<String, Set<Pair>> graph2 = buildGraph(pairs2, rates2);

        //find maximum amount (in the initial currency) for all other currencies in the first day
        Map<String, Double> curToMaxAmount1 = bfs(graph1, initialCurrency, 1.0);

        // use BFS for each currency and find maximum amount (in the initial currency) for all other currencies in the second day
        // and return max value for the initial currency
        double result = -1;
        for (Map.Entry<String, Double> e : curToMaxAmount1.entrySet()) {
            Map<String, Double> curToMaxAmount2Temp = bfs(graph2, e.getKey(), e.getValue());
            //need to use OrDefault since initialCurrency can be absent in the rates of the second day!
            result = Math.max(result, curToMaxAmount2Temp.getOrDefault(initialCurrency, 0.0));
        }

        return result;
    }

    private Map<String, Set<Pair>> buildGraph(List<List<String>> pairs, double[] rates) {
        Map<String, Set<Pair>> graph = new HashMap<>();
        for (int i = 0; i < pairs.size(); i++) {
            List<String> p = pairs.get(i);
            double rate = rates[i];
            graph.putIfAbsent(p.get(0), new HashSet<>());
            graph.get(p.get(0)).add(new Pair(p.get(1), rate));
            graph.putIfAbsent(p.get(1), new HashSet<>());
            graph.get(p.get(1)).add(new Pair(p.get(0), 1 / rate));
        }
        return graph;
    }

    private Map<String, Double> bfs(Map<String, Set<Pair>> graph, String initialCurr, double amount) {
        Map<String, Double> curToAmountMap = new HashMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(initialCurr, amount));
        curToAmountMap.put(initialCurr, amount);

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (Pair adjP : graph.getOrDefault(p.curr, new HashSet<>())) {
                double rate = adjP.num;
                double newAmount = p.num * rate;
                if (newAmount > curToAmountMap.getOrDefault(adjP.curr, 0.0)) {
                    curToAmountMap.put(adjP.curr, newAmount);
                    q.add(new Pair(adjP.curr, newAmount));
                }
            }
        }

        return curToAmountMap;
    }

    //will be used for pairs (currency, rate) and (currency, amount)
    class Pair {
        String curr;
        double num;

        Pair(String curr, double num) {
            this.curr = curr;
            this.num = num;
        }
    }

    /**
     * SOLUTION #2:
     * info: https://leetcode.com/problems/maximize-amount-after-two-days-of-conversions/solutions/6147854/beats-100-traditional-bfs-o-v-e-o-v-e-2-solutions/
     * idea is the same
     * BUT
     * 1) use Map<String, Double> instead of Pair class
     * 2) change bfs method and add Map<Currency, Amount> to re-use it for one pair and for list of pairs obtained after the 1st day
     *
     * BEATS ~ 63%
     */
    public double maxAmount2(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        Map<String, Set<Pair>> graph1 = buildGraph(pairs1, rates1);
        Map<String, Set<Pair>> graph2 = buildGraph(pairs2, rates2);

        //find maximum amount (in the initial currency) for all other currencies in the first day
        Map<String, Double> curToMaxAmountInitial = new HashMap<>();
        curToMaxAmountInitial.put(initialCurrency, 1.0);
        Map<String, Double> curToMaxAmount1 = bfs(graph1, curToMaxAmountInitial);

        // use BFS for all currencies and find maximum amount (in the initial currency) for all other currencies in the second day
        // and return max value for the initial currency
        Map<String, Double> curToMaxAmount2Temp = bfs(graph2, curToMaxAmount1);
        return curToMaxAmount2Temp.get(initialCurrency);
    }

    private Map<String, Double> bfs(Map<String, Set<Pair>> graph, Map<String, Double> curToMaxAmountInitial) {
        Map<String, Double> curToAmountMap = new HashMap<>();
        Queue<Pair> q = new LinkedList<>();
        for (Map.Entry<String, Double> e : curToMaxAmountInitial.entrySet()) {
            q.add(new Pair(e.getKey(), e.getValue()));
            curToAmountMap.put(e.getKey(), e.getValue());
        }

        while (!q.isEmpty()) {
            Pair p = q.poll();


            for (Pair adjP : graph.getOrDefault(p.curr, new HashSet<>())) {
                double rate = adjP.num;
                double newAmount = p.num * rate;
                if (newAmount > curToAmountMap.getOrDefault(adjP.curr, 0.0)) {
                    curToAmountMap.put(adjP.curr, newAmount);
                    q.add(new Pair(adjP.curr, newAmount));
                }
            }
        }

        return curToAmountMap;
    }
}
