package solving_techniques.p11_BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/discuss/post/6804542/google-onsite-interview-question-need-n-exug0/
 * <p>
 * You have N unit tests that pass if run separately. But there are several pairs of tests that, if run in a single test set, fail the whole set(e.g. because they modify a global state in a non-hermetic db). Your task is to find any such pair.
 * <p>
 * You are given a function runTests (i1, i2, i3,.....) that takes a subset of tests, executes them in a single set and returns true if the whole set passes.
 * <p>
 * You can also assume that all minimal sets of tests that fail are made up of a pair of tests (i.e. if runTests(s) fails, with len(s) > 2, then there exists a strict subset t of s such that runTests(t) also fails)
 */
public class GoogleFindPairTests {
    public static void main(String[] args) {
        GoogleFindPairTests obj = new GoogleFindPairTests();

        int N = 5;
        List<Integer> allTests = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            allTests.add(i);
        }

        List<Integer> failingPair = obj.findPair(allTests);
        if (!failingPair.isEmpty()) {
            System.out.println("Found failing pair: [" + failingPair.get(0) + ", " + failingPair.get(1) + "]");
        } else {
            System.out.println("No failing pair found.");
        }
    }

    public static boolean runTests(List<Integer> subset) {
        return !subset.containsAll(Arrays.asList(1, 3));
    }


    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 1h
     * idea is correct, but a lot of code changes and copying of lists
     * <p>
     * time ~ O(N*logN), but not sure...
     * <p>
     * a lot of attempts
     */
    public List<Integer> findPair(List<Integer> tests) {
        if (tests.size() < 2) {
            return Collections.emptyList();
        }

        if (tests.size() == 2 && !runTests(tests)) {
            return tests;
        }

        int low = 0;
        int high = tests.size() - 1;


        int mid = low + (high - low) / 2;
        List<Integer> leftTests = tests.subList(low, mid);    //till mid - 1 inclusively
        List<Integer> rightTests = tests.subList(mid, high + 1);    //till high - 1 inclusively
        if (!runTests(leftTests)) {
            return findPair(leftTests);
        } else if (!runTests(rightTests)) {
            return findPair(rightTests);
        } else {
            //both halves are true => pair is spread across these halves
            List<Integer> left1 = leftTests.subList(0, leftTests.size() / 2);
            List<Integer> left2 = leftTests.subList(leftTests.size() / 2, leftTests.size());

            List<Integer> right1 = rightTests.subList(0, rightTests.size() / 2);
            List<Integer> right2 = rightTests.subList(rightTests.size() / 2, rightTests.size());

            List<Integer> set11 = new ArrayList<>(left1);
            set11.addAll(right1);
            List<Integer> set12 = new ArrayList<>(left1);
            set12.addAll(right2);
            List<Integer> set21 = new ArrayList<>(left2);
            set21.addAll(right1);
            List<Integer> set22 = new ArrayList<>(left2);
            set22.addAll(right2);

            if (!runTests(set11)) {
                return findPair(set11);
            } else if (!runTests(set12)) {
                return findPair(set12);
            } else if (!runTests(set21)) {
                return findPair(set21);
            } else if (!runTests(set22)) {
                return findPair(set22);
            }
        }

        return Collections.emptyList();
    }

    /**
     * Qwen solution
     */
    // Main algorithm to find a failing pair
    public int[] findFailingPair(List<Integer> tests) {
        if (tests.size() <= 1) {
            return null; // Not enough tests
        }

        if (tests.size() == 2) {
            int[] candidate = {tests.get(0), tests.get(1)};
            if (!runTests(Arrays.stream(candidate).boxed().collect(Collectors.toList()))) {
                return candidate;
            } else {
                return null;
            }
        }

        int mid = tests.size() / 2;
        List<Integer> left = tests.subList(0, mid);
        List<Integer> right = tests.subList(mid, tests.size());

        boolean leftPasses = runTests(left);
        boolean rightPasses = runTests(right);

        if (leftPasses && rightPasses) {
            // There's a failing pair across left and right
            //NOTE: in fact it might take O((n/2)^2) ~ O(n^2)
            for (int l : left) {
                for (int r : right) {
                    if (!runTests(Arrays.asList(l, r))) {
                        return new int[]{l, r};
                    }
                }
            }
        } else if (!leftPasses) {
            return findFailingPair(left);
        } else {
            return findFailingPair(right);
        }

        return null;
    }
}
