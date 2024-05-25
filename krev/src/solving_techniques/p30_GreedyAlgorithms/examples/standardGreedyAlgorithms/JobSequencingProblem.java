package solving_techniques.p30_GreedyAlgorithms.examples.standardGreedyAlgorithms;

import java.util.*;

public class JobSequencingProblem {
    public static void main(String args[]) {
        List<Job> arr = new ArrayList<Job>();
        arr.add(new Job('a', 2, 100));
        arr.add(new Job('b', 1, 19));
        arr.add(new Job('c', 2, 27));
        arr.add(new Job('d', 1, 25));
        arr.add(new Job('e', 3, 15));

        System.out.println("Following is maximum profit sequence of jobs");

        JobSequencingProblem job = new JobSequencingProblem();

        // Function call
        JobSequencingProblem.jobScheduling1(arr, new ArrayList<>(), 1, 3);
        StringBuilder sb = new StringBuilder();
        for (Job j : result) {
            sb.append(j.id + " ");
        }

        System.out.println(sb + ": sum = " + resSum);

        JobSequencingProblem.jobScheduling2(arr, 3);
    }

    static int resSum = 0;
    static List<Job> result = new ArrayList<>();

    /**
     * KREVSKY SOLUTION #1: backtracking (like 'Permutations' problem)
     * <p>
     * time ~ O(maxDeadline!) - not optimal in comparison with greedy approach
     */
    public static void jobScheduling1(List<Job> arr, List<Job> tempResult, int slot, int maxDeadline) {
        if (slot > maxDeadline) {
            int tempSum = sum(tempResult);
            if (resSum < tempSum) {
                resSum = tempSum;
                result = new ArrayList<>(tempResult);
            }
            return;
        }

        for (Job job : arr) {
            if (!tempResult.contains(job) && job.deadline >= slot) {
                tempResult.add(job);
                jobScheduling1(arr, tempResult, slot + 1, maxDeadline);
                tempResult.remove(tempResult.size() - 1);
            }
        }
    }

    /**
     * SOLUTION #2: greedy
     * info:
     * https://www.geeksforgeeks.org/job-sequencing-problem/
     * <p>
     * Sort all jobs in decreasing order of profit.
     * Iterate on jobs in decreasing order of profit.For each job , do the following :
     * 1) Find a time slot i, such that slot is empty and i < deadline and i is greatest.Put the job in
     * 2) this slot and mark this slot filled.
     * 3) If no such i exists, then ignore the job.
     * <p>
     * time ~ O(n*logn) + O(n) ~ O(nlogn)
     */
    static void jobScheduling2(List<Job> arr, int maxDeadline) {
        // Length of array
        int n = arr.size();

        // Sort all jobs according to decreasing order of profit
        Collections.sort(arr, (a, b) -> b.profit - a.profit);

        // To keep track of free time slots
        boolean result[] = new boolean[maxDeadline];

        // To store result (Sequence of jobs)
        char job[] = new char[maxDeadline];

        // Iterate through all given jobs
        for (int i = 0; i < n; i++) {
            // Find a free slot for this job (Note that we
            // start from the last possible slot)
            for (int j = Math.min(maxDeadline - 1, arr.get(i).deadline - 1); j >= 0; j--) {
                // Free slot found
                if (result[j] == false) {
                    result[j] = true;
                    job[j] = arr.get(i).id;
                    break;
                }
            }
        }

        // Print the sequence
        for (char jb : job) {
            System.out.print(jb + " ");
        }
        System.out.println();
    }

    static class Job {
        // Each job has a unique-id,profit and deadline
        char id;
        int deadline, profit;

        // Constructors
        public Job() {
        }

        public Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    private static int sum(List<Job> jobs) {
        int sum = 0;
        for (Job job : jobs) {
            sum += job.profit;
        }
        return sum;
    }
}
