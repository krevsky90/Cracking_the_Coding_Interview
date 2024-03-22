package solving_techniques.p5_CyclicSort;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6393a98bd8a93f4bff961b4d
 * <p>
 * We are given an array containing n objects.
 * Each object, when created, was assigned a unique number from the range 1 to n based on their creation sequence.
 * This means that the object with sequence number 3 was created just before the object with sequence number 4.
 * Write a function to sort the objects in-place on their creation sequence number in O(n) and without using any extra space.
 * For simplicity, let?s assume we are passed an integer array containing only the sequence numbers, though each number is actually an object.
 * <p>
 * SOLUTION:
 * * https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2005:%20Cyclic%20Sort.md
 */
public class CyclicSort {
    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 3, 2};
        cyclicSort(arr);
        System.out.println("");
    }

    /**
     * time to solve ~ 9 mins
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     * <p>
     * additional:
     * the worst-case scenario, the while loop will swap a total of n-1 numbers and once a number is at its correct index,
     * we will move on to the next number by incrementing i.
     * So overall, our algorithm will take O(n) + O(n-1) which is asymptotically equivalent to O(n).
     */
    public static void cyclicSort(int[] arr) {
        int start = 0;

        while (start < arr.length) {
            int j = arr[start] - 1;
            if (arr[start] == start + 1) {
                start++;
            } else {
                int temp = arr[j];
                arr[j] = arr[start];
                arr[start] = temp;
            }
        }
    }

    //it is BETTER 'if' validation!
    public void cyclicSortNotMine(int[] arr) {
        int start = 0;

        while (start < arr.length) {
            int correctIdx = arr[start] - 1;
            if (arr[start] == arr[correctIdx]) {
                start++;
            } else {
                // if the current element is not equal to the element present at the current index
                // put the element in the correct index by swapping
                int temp = arr[start];
                arr[start] = arr[correctIdx];
                arr[correctIdx] = temp;
            }
        }
    }
}
