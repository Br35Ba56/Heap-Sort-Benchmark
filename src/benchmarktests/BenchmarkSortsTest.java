package benchmarktests;

import benchmark.BenchmarkSorts;

import static org.junit.jupiter.api.Assertions.*;

class BenchmarkSortsTest {
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void coefficientOfVariance() {
        long[] array = {33,22,45,76,23,44,77,44,33,22,33,4,55,878,909};
       double ce = BenchmarkSorts.coefficientOfVariance(array);
        assertEquals(1.97, ce);
        long[] array2 = {50, 44, 23, 67,45,34,5,6,43,34,56,33};
        ce = BenchmarkSorts.coefficientOfVariance(array2);
        assertEquals(0.51, ce);
    }

    @org.junit.jupiter.api.Test
    void average() {
        long[] array = {33,22,45,76,23,44,77,44,33,22,33,4,55,878,909};
        double arrayAverage = BenchmarkSorts.average(array);
        assertEquals(153.2, arrayAverage);

        long[] array2 = {33000,22000,450000,70430006,2300000,4000004,7000007,405400004,300003,2000002,300003,411111,522225,-87228,909222};
        arrayAverage = BenchmarkSorts.average(array2);

        assertEquals("3.29326906E7", String.valueOf(arrayAverage));
    }

}