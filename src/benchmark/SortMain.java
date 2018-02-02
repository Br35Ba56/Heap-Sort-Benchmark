package benchmark;

import java.awt.*;

/*  Data Set Sizes:
 * 100
 * 500
 * 1000
 * 5000
 * 10000
 * 50000
 * 100000
 * 500000
 * 1000000
 * 5000000
 *
 */

public class SortMain {

    public static void main(String... args)  {
        EventQueue.invokeLater(() -> {
               BenchmarkGUI window = new BenchmarkGUI();
               window.displayFrame();

        });
    }

}
