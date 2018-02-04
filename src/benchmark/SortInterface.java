package benchmark;

public interface SortInterface {

    void recursiveSort(int[] list) throws UnsortedException;
    void iterativeSort(int[] list) throws UnsortedException;

    long getCount();
    long getTime();

}
