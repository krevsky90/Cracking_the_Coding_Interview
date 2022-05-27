package data_structures.chapter10_sorting_n_searching;

/**
 * p.162
 * 10.6 Sort Big File:
 * Imagine you have a 20 GB file with one string per line. Explain how you would sort the file.
 * Hints: #207
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem10_6 {
    /**
     * ORIGINAL SOLUTION: external sort!
     * Since file is big, we don't want to store it in memory.
     * We'll divide the file into chunks, which are x megabytes each, where x is the amount of memory we have available.
     * Each chunk is sorted separately and then saved back to the file system.
     * Once all the chunks are sorted, we merge the chunks, one by one.
     * At the end, we have a fully sorted file
     */
}

