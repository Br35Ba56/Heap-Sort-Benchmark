package benchmark;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.ParseException;

public class BenchmarkView extends JPanel {

   Border loweredbevel = BorderFactory.createLoweredBevelBorder();
    public BenchmarkView() {
        super(new GridBagLayout());
        buildPanel();
    }

    private void buildPanel() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
        JPanel jPanel1 = new JPanel();
        jPanel1.add(new JLabel("Data Set Size n"));
        jPanel1.setBorder(loweredbevel);
        gridBagConstraints.weightx = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        this.add(jPanel1, gridBagConstraints);

        JPanel jPanel2 = new JPanel();
        jPanel2.add(new JLabel("Iterative"));
        jPanel2.setBorder(loweredbevel);
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;

        this.add(jPanel2, gridBagConstraints);

        JPanel jPanel3 = new JPanel();
        jPanel3.add(new JLabel("Recursive"));
        jPanel3.setBorder(loweredbevel);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        this.add(jPanel3, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;

        gridBagConstraints.ipady = 10;

        int sizesIndex = 0;

        for (int y = 0; y <= 11; y++) {
            for (int x = 0; x < 9; x ++){
                JPanel jPanel = new JPanel();
                if (y == 1 && x > 0) {
                    jPanel = setPanelRowY1(x, jPanel);
                }
                if (y > 1) {
                    JLabel label;
                    if (x == 0) {
                        label = new JLabel(String.valueOf(BenchmarkSorts.sizes[sizesIndex++]));
                    } else {
                        label = new JLabel("");
                        BenchMarkController.add(label);
                    }
                    jPanel.add(label);
                }
                jPanel.setBorder(loweredbevel);
                gridBagConstraints.gridx = x;
                gridBagConstraints.gridy = y;

                this.add(jPanel, gridBagConstraints);

            }
        }
    }

    private JPanel setPanelRowY1(int x, JPanel jPanel) {
        if (x%4 == 1) {
            jPanel.add(new JLabel("Average Critical Operation Count"));
        } else if (x%4 == 2) {
            jPanel.add(new JLabel("Coefficient of Variance of Count"));
        } else if (x%4 ==3) {
            jPanel.add(new JLabel("Average Execution time"));
        } else {
            jPanel.add(new JLabel("Coefficient of Variance of Time"));
        }
        return jPanel;
    }
}
