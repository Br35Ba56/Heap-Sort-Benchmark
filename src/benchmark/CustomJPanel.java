package benchmark;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CustomJPanel extends JPanel {

   Border loweredbevel = BorderFactory.createLoweredBevelBorder();
    public CustomJPanel() {
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
        for (int x = 0; x < 9; x ++) {
            for (int y = 0; y <= 10; y++ ){
                JPanel jPanel = new JPanel();
                jPanel.setBorder(loweredbevel);
                gridBagConstraints.gridx = x;
                gridBagConstraints.gridy = y;
                this.add(jPanel, gridBagConstraints);

            }
        }


    }

    private void setPanelRowY2(int j) {

    }
}
