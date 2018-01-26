package benchmark;

import java.util.Random;

public class HeapSortRecursive implements HeapSort {
    int[] heap;
    int size;

    public HeapSortRecursive(int n) {
        heap = new int[n];
        size = 0;
    }

    @Override
    public int left(int index) {
        return 2 * index + 1;
    }

    @Override
    public int right(int index) {
        return 2 * index + 1;
    }

    @Override
    public int parent(int index) {
        return (index - 1) / 2;
    }

    @Override
    public int root() {
        return heap[0];
    }

    @Override
    public int getElement(int index) {
        return heap[index];
    }

    @Override
    public void resize() {
        int[] temp = new int[heap.length + 20];
        for (int i = 0; i < heap.length; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    @Override
    public void add(int value) {
        if (size + 1 > heap.length) {
            resize();
        }
        int index = size;
        heap[index] = value;
        bubbleUp(index);
        size++;
    }

    @Override
    public void bubbleUp(int child) {
        int parent;
        if (child != 0) {
            parent = parent(child);
            if (heap[parent] > heap[child]) {
                swap(parent, child);
                child = parent;
                bubbleUp(child);
            }
        }
    }

    @Override
    public void heapify(int parent) {
        int left = left(parent);
        int right = right(parent);

        int max = parent;


        if (left <= size - 1 && left > max) max = left;
        if (right<= size - 1 && right > max) max = right;
        if (max != parent) {
            swap(parent, max);
            heapify(max);
        }


    }

    @Override
    public int[] heapSort() {
        while (size >= 2) {
            swap(heap[1], heap[size - 1]);
            size--;
            heapify(size - 1);
        }
        return heap;
    }

    @Override
    public void swap(int parent, int child) {
        int temp = heap[parent];
        heap[parent] = heap[child];
        heap[child] = temp;
    }

    static java.util.Random rn = new java.util.Random();
    public static void main(String[] args) {
        int heapSize = 10;
        HeapSort heap = new HeapSortRecursive(7);

        for (int i = 0; i < heapSize; i++){
        heap.add(rn.nextInt(heapSize));
        }

        int[] sorted = heap.heapSort();
        System.out.println("After heapSort()");
        for (int i = 0; i < sorted.length; i++) {
            System.out.println(sorted[i]);
        }

    }

}
