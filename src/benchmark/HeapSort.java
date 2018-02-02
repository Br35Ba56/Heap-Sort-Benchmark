package benchmark;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class HeapSort implements SortInterface {

    private int count;
    private long duration;

    public int left(int index) {
        return 2 * index;
    }


    public int right(int index) {
        return 2 * index + 1;
    }

    public int parent(int index) {
        return (index) / 2;
    }

    public void heapifyRecursive(int[] array, int i, int m) {
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
    }

    public void buildHeapRecursive(int n, int[] array) {
        for (int i = n / 2; i >= 1; i--) {
            heapifyRecursive(array, i, n);
            count++;
        }
    }

    public void heapSortRecursive(int n, int[] list) throws UnsortedException {
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

    public void swap(int[] array, int parent, int child) {
        int temp = array[parent];
        array[parent] = array[child];
        array[child] = temp;
    }

    public void checkSort(int[] array) throws UnsortedException {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                throw new UnsortedException("Array Not Sorted");
            }
        }
    }

    @Override
    public void recursiveSort(int[] list) throws UnsortedException {
        count = 0;
        duration = 0;
        heapSortRecursive(list.length - 1, list);
    }

    @Override
    public void iterativeSort(int[] list) {

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public long getTime() {
        return duration;
    }

}
