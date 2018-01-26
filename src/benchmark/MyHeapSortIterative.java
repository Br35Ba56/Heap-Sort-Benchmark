package benchmark;

public class MyHeapSortIterative implements HeapSort {

    int[] heap;
    int size;

    public MyHeapSortIterative(int n) {
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
        int childOne = left(parent);
        int childTwo = right(parent);

        while(childOne < size - 1 || childTwo < size - 1) {
            if (heap[childOne] < heap[parent] && heap[childOne] < heap[childTwo]) {
                swap(parent, childOne);
                parent = childOne;
            } else if (heap[childTwo] <= heap[parent] && heap[childTwo] <= heap[childOne]) {
                swap(parent, childTwo);
                parent = childTwo;
            } else {
                break;
            }
            childOne = left(parent);
            childTwo = right(parent);
        }
    }

    @Override
    public int[] heapSort() {
        do {
            if(heap[0] > heap[1]) {
                break;
            }
            swap(0, size - 1);
            size--;
            heapify(0);
        } while (size > 0);
        return heap;
    }

    @Override
    public void swap(int parent, int child) {
        int temp = heap[parent];
        heap[parent] = heap[child];
        heap[child] = temp;
    }

    public static void main(String[] args) {
        int heapSize = 10000;
        MyHeapSortIterative heap = new MyHeapSortIterative(7);

        heap.add(10);
        heap.add(11);
        heap.add(12);
        heap.add(13);
        heap.add(15);
        heap.add(14);
        heap.add(18);
        //for (int i = 0; i < heapSize; i++){
        //heap.add(rn.nextInt(heapSize));
        //}
        //for (int i = 0; i < heap.size; i++) {
        //  System.out.println(heap.getElement(i));
        //}
        int[] sorted = heap.heapSort();
        System.out.println("After heapSort()");
        for (int i = 0; i < sorted.length; i++) {
            System.out.println(sorted[i]);
        }

    }
}

