package benchmark;

public interface HeapSort {

    int left(int index);

    int right(int index);

    int parent(int index);

    int root();

    int getElement(int index);

    void resize();

    void add(int value);

    void bubbleUp(int child);

    void heapify(int parent);

    int[] heapSort();

    void swap(int parent, int child);
}
