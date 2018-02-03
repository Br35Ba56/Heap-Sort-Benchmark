package benchmark;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BenchmarkView extends JPanel {

    private Border loweredbevel = BorderFactory.createLoweredBevelBorder();

    public BenchmarkView() {
        super(new GridBagLayout());
        buildPanel();
    }

    private void buildPanel() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        JPanel jPanel1 = new JPanel();
        jPanel1.add(addTextArea("Data Set\nSize n", jPanel1.getBackground()));
        jPanel1.setBorder(loweredbevel);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        this.add(jPanel1, gridBagConstraints);
        gridBagConstraints.gridheight = 1;
        JPanel jPanel2 = new JPanel();
        jPanel2.add(addTextArea("Iterative\n", jPanel2.getBackground()));
        jPanel2.setBorder(loweredbevel);
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;

        this.add(jPanel2, gridBagConstraints);

        JPanel jPanel3 = new JPanel();
        jPanel3.add(addTextArea("Recursive\n", jPanel3.getBackground()));
        jPanel3.setBorder(loweredbevel);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        this.add(jPanel3, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;

        int sizesIndex = 0;

        for (int y = 0; y <= 11; y++) {
            for (int x = 0; x < 9; x++) {
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
        if (x % 4 == 1) {
            jPanel.add(addTextArea("Average CriticalOperation\nCount", jPanel.getBackground()));
        } else if (x % 4 == 2) {
            jPanel.add(addTextArea("Coefficient of\nVariance of\nCount", jPanel.getBackground()));
        } else if (x % 4 == 3) {
            jPanel.add(addTextArea("Average\nExecution\nTime (ns)", jPanel.getBackground()));
        } else {
            jPanel.add(addTextArea("Coefficient of\n Variance of\n Time (ns)", jPanel.getBackground()));
        }
        return jPanel;
    }

    private JTextArea addTextArea(String string, Color color) {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setBackground(color);
        textArea.setText(string);
        textArea.setEditable(false);
        return textArea;
    }
}
