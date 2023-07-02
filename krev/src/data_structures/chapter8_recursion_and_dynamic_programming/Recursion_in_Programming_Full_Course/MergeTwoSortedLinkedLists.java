package data_structures.chapter8_recursion_and_dynamic_programming.Recursion_in_Programming_Full_Course;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * https://youtu.be/IJDJ0kBx2LM?t=4794
 */
public class MergeTwoSortedLinkedLists {
    public LinkedListNode sortedMerge(LinkedListNode A, LinkedListNode B) {
        if (A == null) return B;
        if (B == null) return A;

        if (A.value < B.value) {
            A.next = sortedMerge(A.next, B);
            return A;
        } else {
            B.next = sortedMerge(A, B.next);
            return B;
        }
    }
}
