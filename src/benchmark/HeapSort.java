package benchmark;


import java.time.Duration;
import java.time.LocalDateTime;

public class HeapSort implements SortInterface {

    private long count;
    private long duration;

    private int left(int index) {
        return 2 * index;
    }

    private int right(int index) {
        return 2 * index + 1;
    }

    private int parent(int index) {
        return (index) / 2;
    }

    private void swap(int[] array, int parent, int child) {
        int temp = array[parent];
        array[parent] = array[child];
        array[child] = temp;
    }

    private void checkSort(int[] array) throws UnsortedException {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                throw new UnsortedException("Array Not Sorted");
            }
        }
    }

    private void buildHeapRecursive(int n, int[] array) {
        for (int i = n / 2; i >= 1; i--) {
            heapifyRecursive(array, i, n);
            count++;
        }
    }

    private void heapifyRecursive(int[] array, int i, int m) {
        int left = left(i);
        int right = right(i);

        int max = i;
        if (left <= m && array[left] > array[max]) {
            max = left;
        }
        if (right <= m && array[right] > array[max]) {
            max = right;
        }
        if (max != i) {
            swap(array, i, max);
            heapifyRecursive(array, max, m);
        }
        count++;
    }

    private void heapSortRecursive(int n, int[] list) throws UnsortedException {
        LocalDateTime startTime = LocalDateTime.now();
        buildHeapRecursive(n, list);
        int max = n;
        while (max >= 2) {
            swap(list, 1, max);
            max = max - 1;
            count++;
            heapifyRecursive(list, 1, max);
        }
        LocalDateTime endTime = LocalDateTime.now();
        duration = Duration.between(startTime, endTime).toNanos();
        checkSort(list);
    }

    private void buildHeapIterative(int n, int[] array) {
        for (int i = n / 2; i >= 1; i--) {
            heapifyIterative(array, i, n);
            count++;
        }
    }

    private void heapifyIterative(int[] array, int i, int m) {
        int index;
        int left;
        int right;

        while (i <= m) {
            index = i;

            left = left(i);
            right = right(i);

            if (left <= m && array[left] > array[index]) {
                index = left;
            }
            if (right <= m && array[right] > array[index]) {
                index = right;
            }
            if (index == i) {
                return;
            }

            swap(array, i, index);

            i = index;
            count++;
        }
    }

    private void heapSortIterative(int n, int[] list) throws UnsortedException {
        LocalDateTime startTime = LocalDateTime.now();
        buildHeapIterative(n, list);
        int max = n;
        while (max >= 2) {
            swap(list, 1, max);
            max = max - 1;
            count++;
            heapifyIterative(list, 1, max);
        }
        LocalDateTime endTime = LocalDateTime.now();
        duration = Duration.between(startTime, endTime).toNanos();
        checkSort(list);
    }

    @Override
    public void recursiveSort(int[] list) throws UnsortedException {
        count = 0;
        duration = 0;
        heapSortRecursive(list.length - 1, list);
    }

    @Override
    public void iterativeSort(int[] list) throws UnsortedException {
        count = 0;
        duration = 0;
        heapSortIterative(list.length - 1, list);
    }

    @Override
    public long getCount() {
        return count;
    }

    @Override
    public long getTime() {
        return duration;
    }

}
