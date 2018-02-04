package benchmark;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BenchmarkSorts {

    static int[] sizes = {1_000, 5_000, 10_000, 50_000, 100_000, 500_000, 1_000_000, 2_500_000, 5_000_000, 7_000_000};

    static int[] dataSet;

    static long[] countSetRecursive;
    static long[] timeSetRecursive;

    static long[] countSetIterative;
    static long[] timeSetIterative;


    static java.util.Random rn = new java.util.Random();
    //TODO: Getting divide by zero errors, could be due to dividing numbers and rounding to 100 place.

    //Warm up the JVM
    static {
        for (int i = 0; i < 4; i++) {
            results = new ArrayList<>();
            HeapSort heapSort = new HeapSort();
            dataSet = new int[sizes[i]];
            countSetRecursive = new long[50];
            timeSetRecursive = new long[50];
            countSetIterative = new long[50];
            timeSetIterative = new long[50];
            for (int runIndex = 0; runIndex < 50; runIndex++) {
                for (int j = 1; j < dataSet.length; j++) {
                    int random = rn.nextInt(100) + 1;  //Random numbers 0 - 99;
                    dataSet[j] = random;
                }
                try {
                    heapSort.recursiveSort(dataSet);
                    heapSort.iterativeSort(dataSet);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }
    }

    private BenchmarkSorts() {
    }

    private static List<String> results;

    public static void runSorts() throws UnsortedException {
        results = new ArrayList<>();
        HeapSort heapSort = new HeapSort();

        for (int i = 0; i < sizes.length; i++) {
            dataSet = new int[sizes[i]];
            countSetRecursive = new long[50];
            timeSetRecursive = new long[50];
            for (int runIndex = 0; runIndex < 50; runIndex++) {
                for (int j = 1; j < dataSet.length; j++) {
                    dataSet[j] = rn.nextInt(100) + 1;  //Random numbers 0 - 99;
                }

                heapSort.iterativeSort(dataSet);
                countSetIterative[runIndex] = heapSort.getCount();
                timeSetIterative[runIndex] = heapSort.getTime();

                heapSort.recursiveSort(dataSet);
                countSetRecursive[runIndex] = heapSort.getCount();
                timeSetRecursive[runIndex] = heapSort.getTime();
            }
            //Add iterative data
            results.add(String.valueOf(average(countSetIterative)));
            results.add(String.valueOf(coefficientOfVariance(countSetIterative)));
            results.add(String.valueOf(average(timeSetIterative)));
            results.add(String.valueOf(coefficientOfVariance(timeSetIterative)));

            //Add recursive data
            results.add(String.valueOf(average(countSetRecursive)));
            results.add(String.valueOf(coefficientOfVariance(countSetRecursive)));
            results.add(String.valueOf(average(timeSetRecursive)));
            results.add(String.valueOf(coefficientOfVariance(timeSetRecursive)));

        }
        displayReport(results);
    }

    private static void displayReport(List<String> results) {
        for (int i = 0; i < results.size(); i++) {
            BenchMarkController.getLabelList().get(i).setText(results.get(i));
        }
    }

    public static double coefficientOfVariance(long[] resultSet) {
        List<BigDecimal> resultSetBigDecimal = new ArrayList<>();

        for (int i = 0; i < resultSet.length; i++) {
            resultSetBigDecimal.add(BigDecimal.valueOf(resultSet[i]));
        }

        BigDecimal average = BigDecimal.valueOf(average(resultSet));

        resultSetBigDecimal = subtractTheMean(resultSetBigDecimal, average);

        resultSetBigDecimal = squareEachOfTheDifferences(resultSetBigDecimal);

        BigDecimal sumOfSquares = sumOfSquares(resultSetBigDecimal);

        BigDecimal sumOfSquaresDividedByNMinusOne = divideSums(sumOfSquares, resultSet.length - 1);

        BigDecimal standardDeviation = BigDecimal.valueOf(Math.sqrt(sumOfSquaresDividedByNMinusOne.doubleValue()));

        return standardDeviation.divide(average, 5, RoundingMode.HALF_UP).doubleValue();
    }

    private static List<BigDecimal> subtractTheMean(List<BigDecimal> resultSetBigDecimal, BigDecimal average) {

        for (int i = 0; i < resultSetBigDecimal.size(); i++) {
            resultSetBigDecimal.set(i, resultSetBigDecimal.get(i).subtract(average));
        }
        return resultSetBigDecimal;
    }

    private static List<BigDecimal> squareEachOfTheDifferences(List<BigDecimal> resultSetBigDecimal) {

        for (int i = 0; i < resultSetBigDecimal.size(); i++) {
            resultSetBigDecimal.set(i, resultSetBigDecimal.get(i).pow(2));
        }
        return resultSetBigDecimal;
    }

    private static BigDecimal sumOfSquares(List<BigDecimal> resultSetBigDecimal) {
        BigDecimal sumOfSquares = BigDecimal.ZERO;
        for (int i = 0; i < resultSetBigDecimal.size(); i++) {
            sumOfSquares = sumOfSquares.add(resultSetBigDecimal.get(i));
        }
        return sumOfSquares;
    }

    private static BigDecimal divideSums(BigDecimal sumOfSquares, int nMinusOne) {
        return sumOfSquares.divide(BigDecimal.valueOf(nMinusOne), 100, RoundingMode.HALF_UP);
    }

    public static double average(long[] resultSet) {
        BigDecimal resultAverage = BigDecimal.ZERO;
        for (int i = 0; i < resultSet.length; i++) {
            resultAverage = resultAverage.add(BigDecimal.valueOf(resultSet[i]));
        }
        resultAverage = resultAverage.divide(BigDecimal.valueOf(resultSet.length), 100, RoundingMode.HALF_UP);
        return resultAverage.doubleValue();
    }
}
