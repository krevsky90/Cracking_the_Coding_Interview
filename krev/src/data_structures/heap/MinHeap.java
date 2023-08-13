package data_structures.heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinHeap implements IHeap {
    private static int capacity = 5;    //initial size of heap
    private int[] heap;
    private int size;   //amount of elements in the heap

    public MinHeap() {
        heap = new int[capacity];
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("heap is empty");
        }

        return heap[0];
    }

    @Override
    public void insert(int item) {
        increaseCapacityIfNeeded();

        //add item to the end of heap array
        heap[size] = item;
        size++;

        //rebuild heap tree - up the inserted element if it is less than its parent
        bubbleUp();
    }

    @Override
    public int remove() {
        //remove top and rebuild heap tree
        if (isEmpty()) {
            throw new NoSuchElementException("heap is empty");
        }

        int minElement = heap[0];
        //insert the last heap element to top position
        heap[0] = heap[size - 1];
        heap[size - 1] = Integer.MIN_VALUE; //todo: no need to do this. it will be overriden when we add some new item
        size--;

        //rebuild heap tree - down the top element if it is more than its child
        bubbleDown();

        return minElement;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void increaseCapacityIfNeeded() {
        if (size == capacity) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    /**
     * Array Form: [5, 7, 6, 10, 15, 17, 12]
     * <p>
     * Complete Binary Tree Form:
     *            5(0)
     *        /         \
     *      7(1)         6(2)
     *    /    \          /  \
     * 10(3)  15(4)   17(5)   12(6)
     */
    private void bubbleUp() {
        int index = size - 1;

        while (hasParent(index) && heap[index] < heap[getParentIndex(index)]) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private void bubbleDown() {
        int index = 0;

        //just copied
//        while (hasLeftChild(index)) {
//            int smallerChildIndex = getLeftChildIndex(index);
//            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
//                smallerChildIndex = getRightChildIndex(index);
//            }
//
//            if (heap[index] < heap[smallerChildIndex]) {
//                break;
//            } else {
//                swap(index, smallerChildIndex);
//            }
//
//            index = smallerChildIndex;
//        }
    }

    private boolean hasParent(int i) {
        return i > 0;
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

}
