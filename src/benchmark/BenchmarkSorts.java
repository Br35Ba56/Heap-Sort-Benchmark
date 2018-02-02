package benchmark;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkSorts {
    static int [] sizes = {100, 500, 1_000, 5_000, 10_000, 50_000, 100_000, 500_000, 1_000_000, 5_000_000 };

    static int[] dataSet;

    static java.util.Random rn = new java.util.Random();

    private BenchmarkSorts() {


    }

    static List<Long> results;

    static void runSorts() throws UnsortedException {
        results = new ArrayList<>();
        HeapSort heapSort = new HeapSort();
        //Each Data Set gets ran 50 times.
        for (int i = 0; i < sizes.length; i++) {
            dataSet = new int[sizes[i]];
            int dataSetCountAverage = 0;
            int dataSetMilliSecAvereage = 0;
            for (int runIndex = 0; runIndex < 50; runIndex++) {
                for (int j = 1; j < dataSet.length; j++) {
                    int random = rn.nextInt(100) + 1;  //Random numbers 0 - 99;
                    dataSet[j] = random;
                }

                heapSort.recursiveSort(dataSet);
                dataSetCountAverage += heapSort.getCount();
                dataSetMilliSecAvereage += heapSort.getTime();
            }
            results.add((long)dataSetCountAverage/50);
            results.add((long)dataSetMilliSecAvereage/50);
        }
        displayReport(results);


    }

    static void displayReport(List<Long> results) {
        BenchMarkController.get().get(0).setText(results.get(0).toString());
        BenchMarkController.get().get(1).setText(results.get(1).toString());

    }
    public static void main(String... args) throws UnsortedException {
        runSorts();
    }
}
