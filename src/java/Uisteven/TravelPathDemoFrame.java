package Uisteven;

import javax.swing.*;

public class TravelPathDemoFrame extends JFrame{
    public TravelPathDemoFrame() {
        super("TravelPath – User Stories 6 & 7 Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        setContentPane(new TravelPathView());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TravelPathDemoFrame frame = new TravelPathDemoFrame();
            frame.setVisible(true);
        });
    }
}
