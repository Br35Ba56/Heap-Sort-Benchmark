package benchmark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BenchmarkGUI {

    private JFrame jFrame;

    BenchmarkGUI() {
        initialize();
    }

    private void initialize()  {
        jFrame = new JFrame();
        jFrame.setResizable(true);
        jFrame.setBounds(200, 200, 1250, 500);

        jFrame.add(new BenchmarkView());
        JMenuBar menuBar = new JMenuBar();
        JButton runSorts = new JButton("Run Sorts");
        runSorts.addActionListener(new StartButtonAction());
        menuBar.add(runSorts);
        jFrame.setJMenuBar(menuBar);
    }

    public void displayFrame(){
        jFrame.setVisible(true);
        jFrame.setLayout(new FlowLayout());

    }


}

class StartButtonAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            BenchmarkSorts.runSorts();
        }catch (UnsortedException ue) {
            System.out.println(ue.getStackTrace());
        }
    }
}