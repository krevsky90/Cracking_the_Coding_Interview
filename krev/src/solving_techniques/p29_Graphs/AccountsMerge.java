package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 721. Accounts Merge (medium)
 * https://leetcode.com/problems/accounts-merge/
 *
 * #Company: Amazon Apple Bloomberg Facebook Google Houzz LinkedIn Microsoft Oracle
 *
 * Given a list of accounts where each element accounts[i] is a list of strings,
 *      where the first element accounts[i][0] is a name,
 *      and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts.
 * Two accounts definitely belong to the same person if there is some common email to both accounts.
 * Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
 * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format:
 *      the first element of each account is the name, and the rest of the elements are emails in sorted order.
 *
 * The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input: accounts = [
 *      ["John","johnsmith@mail.com","john_newyork@mail.com"],
 *      ["John","johnsmith@mail.com","john00@mail.com"],
 *      ["Mary","mary@mail.com"],
 *      ["John","johnnybravo@mail.com"]
 * ]
 * Output: [
 *      ["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
 *      ["Mary","mary@mail.com"],
 *      ["John","johnnybravo@mail.com"]
 * ]
 *
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 *
 * Example 2:
 * Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 *
 *
 * Constraints:
 * 1 <= accounts.length <= 1000
 * 2 <= accounts[i].length <= 10
 * 1 <= accounts[i][j].length <= 30
 * accounts[i][0] consists of English letters.
 * accounts[i][j] (for j > 0) is a valid email.
 *
 */
public class AccountsMerge {
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
        accounts.add(Arrays.asList("Mary","mary@mail.com"));
        accounts.add(Arrays.asList("John","johnnybravo@mail.com"));

        AccountsMerge obj = new AccountsMerge();
        List<List<String>> result = obj.accountsMergeBFS(accounts);
        System.out.println("");
    }

    /**
     * NOT SOLVED by myself
     * info:
     * https://www.youtube.com/watch?v=f17PKE8W2p8&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=6
     * idea: see them inside the comments to code
     * time to implement ~ 36 mins
     * Let N - number of accounts, K - max amount of emails of these accounts
     * the worst case is when all N accounts have different emails
     * time ~ O(N*K*K*logK), since N*K - to build the graph. for each email (from N*K) we need to perform sorting of K emails (=> K*logK)
     *  NOTE: this is my opinion, it differs from comments with O(N*K*N*K*logK), but I don't understand why do we have extra N?
     * space ~ O(N*K)
     *
     * 5 attempts:
     *  - forgot emailToName.put(email, name);
     *  - incorrect copy paste if (visited.containsKey(email) && visited.get(email))
     *  - incorrect attempt: result.add(name);
     *  - incorrect implementation of DFR. rewrote to BFS which is simpler for me
     *
     *  BEATS ~ 28%
     *
     */
    public List<List<String>> accountsMergeBFS(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        //idea #1: consider the problem as graph problem!
        Map<String, Set<String>> graph = new HashMap<>();

        //step #1: build graph
        for (List<String> account : accounts) {
            String name = account.get(0);

            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                //idea #2:
                // NOTE: we will link all email of the account to ONLY 1st email (i.e. account.get(1))!
                //i.e. NOT all to all
                if (!graph.containsKey(email)) {
                    graph.put(email, new HashSet<>());
                }
                graph.get(email).add(account.get(1));    //forcase "email = account.get(1)" it is linking of email to itself
                graph.get(account.get(1)).add(email);

                emailToName.put(email, name);
            }
        }

        //step #2: traverse graph and form the result
        //we will use iterative BFS approach
        Map<String, Boolean> visited = new HashMap<>(); //email to isVisited

        List<List<String>> result = new ArrayList<>();
        for (String email : graph.keySet()) {
            if (visited.containsKey(email) && visited.get(email)) continue;

            List<String> subResult = new ArrayList<>();

            Queue<String> q = new LinkedList<>();
            q.add(email);
            visited.put(email, true);

            while (!q.isEmpty()) {
                String tempEmail = q.poll();
                subResult.add(tempEmail);

                for (String edge : graph.get(tempEmail)) {
                    if (visited.containsKey(edge) && visited.get(edge)) continue;

                    q.add(edge);
                    visited.put(edge, true);
                }
            }

            String name = emailToName.get(email);
            Collections.sort(subResult);
            subResult.add(0, name);
            result.add(subResult);
        }

        return result;
    }

    /**
     * SOLUTION #2:
     * the same, but using DFS
     *
     * BEATS ~ 23%
     */
    public List<List<String>> accountsMergeDFS(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        //idea #1: consider the problem as graph problem!
        Map<String, Set<String>> graph = new HashMap<>();

        //step #1: build graph
        for (List<String> account : accounts) {
            String name = account.get(0);

            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                //idea #2:
                // NOTE: we will link all email of the account to ONLY 1st email (i.e. account.get(1))!
                //i.e. NOT all to all
                if (!graph.containsKey(email)) {
                    graph.put(email, new HashSet<>());
                }
                graph.get(email).add(account.get(1));    //forcase "email = account.get(1)" it is linking of email to itself
                graph.get(account.get(1)).add(email);

                emailToName.put(email, name);
            }
        }

        //step #2: traverse graph and form the result
        //we will use iterative DFS approach
        Map<String, Boolean> visited = new HashMap<>(); //email to isVisited

        List<List<String>> result = new ArrayList<>();
        for (String email : graph.keySet()) {
            if (visited.containsKey(email) && visited.get(email)) continue;

            List<String> subResult = new ArrayList<>();

            Stack<String> stack = new Stack<>();

            stack.add(email);
            visited.put(email, true);

            while (!stack.isEmpty()) {
                String tempEmail = stack.pop();
                subResult.add(tempEmail);
                // visited.put(tempEmail, true);

                for (String edge : graph.get(tempEmail)) {
                    if (visited.containsKey(edge) && visited.get(edge)) continue;

                    stack.add(edge);
                    visited.put(edge, true);
                }
            }

            String name = emailToName.get(email);
            Collections.sort(subResult);
            subResult.add(0, name);
            result.add(subResult);
        }

        return result;
    }

    /**
     * KREVSKY SOLUTION #3 (24.06.2025): Union find
     * time ~ 29 mins
     * BEATS ~ 9%
     *
     * 2 attempts:
     * - typo like List instead of Set
     */
    public List<List<String>> accountsMergeUnionFind(List<List<String>> accounts) {
        Map<String, Set<String>> adjMap = new HashMap<>();
        Map<String, String> emailToNameMap = new HashMap<>();

        for (List<String> account : accounts) {
            String name = account.get(0);
            String email1 = account.get(1);
            adjMap.put(email1, new HashSet<>());
            emailToNameMap.put(email1, name);

            for (int i = 2; i < account.size(); i++) {
                adjMap.putIfAbsent(account.get(i), new HashSet<>());
                adjMap.get(account.get(i)).add(email1);
                adjMap.get(email1).add(account.get(i));
                emailToNameMap.put(account.get(i), name);
            }
        }

        Map<String, String> parents = new HashMap<>();
        for (String email : adjMap.keySet()) {
            parents.put(email, email);
        }

        // Set<String> visited = new HashSet<>();
        for (String key : adjMap.keySet()) {
            // if (visited)
            Set<String> list = adjMap.get(key);
            for (String e : list) {
                union(key, e, parents);
            }
        }

        //group by parents
        Map<String, List<String>> groups = new HashMap<>();
        for (String key : adjMap.keySet()) {
            String parent = findParent(key, parents);
            groups.putIfAbsent(parent, new ArrayList<>());
            groups.get(parent).add(key);
        }

        List<List<String>> result = new ArrayList<>();
        for (String parent : groups.keySet()) {
            List<String> subResult = groups.get(parent);
            Collections.sort(subResult);
            String name = emailToNameMap.get(subResult.get(0));
            subResult.add(0, name);
            result.add(subResult);
        }

        return result;
    }

    private String findParent(String email, Map<String, String> map) {
        if (email.equals(map.get(email))) {
            return email;
        } else {
            String parent = findParent(map.get(email), map);
            map.put(email, parent);
            return parent;
        }
    }

    private void union(String e1, String e2, Map<String, String> map) {
        String parent1 = findParent(e1, map);
        String parent2 = findParent(e2, map);
        if (parent1.equals(parent2)) return;

        map.put(parent1, parent2);
    }
}
