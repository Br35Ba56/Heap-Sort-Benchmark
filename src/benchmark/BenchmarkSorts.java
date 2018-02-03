package benchmark;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BenchmarkSorts {

    static int[] sizes = {100, 500, 1_000, 5_000, 10_000, 50_000, 100_000, 500_000, 1_000_000, 5_000_000};

    static int[] dataSet;

    static long[] countSet;
    static long[] timeSet;


    static java.util.Random rn = new java.util.Random();
    //TODO: Getting divide by zero errors, could be due to dividing numbers and rounding to 100 place.

    //Warm up the JVM
  /*  static {
        for (int i = 0; i < 4; i++) {
            results = new ArrayList<>();
            HeapSort heapSort = new HeapSort();
            dataSet = new int[sizes[i]];
            countSet = new long[50];
            timeSet = new long[50];
            for (int runIndex = 0; runIndex < 50; runIndex++) {
                for (int j = 1; j < dataSet.length; j++) {
                    int random = rn.nextInt(100) + 1;  //Random numbers 0 - 99;
                    dataSet[j] = random;
                }
                try {
                    heapSort.recursiveSort(dataSet);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }
    }
*/
    private BenchmarkSorts() {
    }

    static List<String> results;

    static void runSorts() throws UnsortedException {
        results = new ArrayList<>();
        HeapSort heapSort = new HeapSort();
        //Each Data Set gets ran 50 times.
        for (int i = 0; i < sizes.length; i++) {
            dataSet = new int[sizes[i]];
            countSet = new long[50];
            timeSet = new long[50];
            for (int runIndex = 0; runIndex < 50; runIndex++) {
                for (int j = 1; j < dataSet.length; j++) {
                    int random = rn.nextInt(100) + 1;  //Random numbers 0 - 99;
                    dataSet[j] = random;
                }

                heapSort.recursiveSort(dataSet);
                countSet[runIndex] = heapSort.getCount();
                timeSet[runIndex] = heapSort.getTime();
                /*
                heapSort.iterativeSort(dataSet);
                countSSet[runIndex] = heapSort.getCount();
                timeSet[runIndex] = heapSort.getTime();
                 */


            }
            results.add(String.valueOf(average(countSet)));
            results.add(String.valueOf(coefficientOfVariance(countSet)));
            results.add(String.valueOf(average(timeSet)));
            results.add(String.valueOf(coefficientOfVariance(timeSet)));
        }
        displayReport(results);


    }

    static void displayReport(List<String> results) {
        BenchMarkController.getLabelList().get(0).setText(results.get(0).toString());
        BenchMarkController.getLabelList().get(1).setText(results.get(1).toString());
        BenchMarkController.getLabelList().get(2).setText(results.get(2).toString());
        BenchMarkController.getLabelList().get(3).setText(results.get(3).toString());
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

        return standardDeviation.divide(average, 15, RoundingMode.HALF_UP).doubleValue();
    }

    public static List<BigDecimal> subtractTheMean(List<BigDecimal> resultSetBigDecimal, BigDecimal average) {

        for (int i = 0; i < resultSetBigDecimal.size(); i++) {
            resultSetBigDecimal.set(i, resultSetBigDecimal.get(i).subtract(average));
        }
        return resultSetBigDecimal;
    }

    public static List<BigDecimal> squareEachOfTheDifferences(List<BigDecimal> resultSetBigDecimal) {

        for (int i = 0; i < resultSetBigDecimal.size(); i++) {
            resultSetBigDecimal.set(i, resultSetBigDecimal.get(i).pow(2));
        }
        return resultSetBigDecimal;
    }

    public static BigDecimal sumOfSquares(List<BigDecimal> resultSetBigDecimal) {
        BigDecimal sumOfSquares = BigDecimal.ZERO;
        for (int i = 0; i < resultSetBigDecimal.size(); i++) {
            sumOfSquares = sumOfSquares.add(resultSetBigDecimal.get(i));
        }
        return sumOfSquares;
    }

    public static BigDecimal divideSums(BigDecimal sumOfSquares, int nMinusOne) {
        return sumOfSquares.divide(BigDecimal.valueOf(nMinusOne), 15, RoundingMode.HALF_UP);
    }

    public static double average(long[] resultSet) {
        BigDecimal resultAverage = BigDecimal.ZERO;
        for (int i = 0; i < resultSet.length; i++) {
            resultAverage = resultAverage.add(BigDecimal.valueOf(resultSet[i]));
        }
        resultAverage = resultAverage.divide(BigDecimal.valueOf(resultSet.length), 15, RoundingMode.HALF_UP);
        return resultAverage.doubleValue();
    }
}
