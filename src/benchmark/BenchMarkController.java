package benchmark;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BenchMarkController {
    private static BenchMarkController benchMarkController = new BenchMarkController();

    private BenchMarkController() {

    }

    private List<JLabel> labelList = new ArrayList<>();

    public static void add(JLabel label) {
        benchMarkController.labelList.add(label);
    }

    public static List<JLabel> getLabelList() {
        return benchMarkController.labelList;
    }
}
