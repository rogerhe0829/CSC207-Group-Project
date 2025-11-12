package ui;

import javax.swing.*;
import java.awt.*;

public class AddStopDialog extends JDialog {
    public AddStopDialog(TravelPathAPP app, ItineraryBuilderPage builderPage) {
        setTitle("Add Stop");
        setModal(true);
        setSize(400, 250);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JPanel fields = new JPanel();
        fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
        JTextField cityField = new JTextField();
        JTextField notesField = new JTextField();
        JSpinner dayIndex = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

        fields.add(new JLabel("City:"));
        fields.add(cityField);
        fields.add(new JLabel("Notes: (optional)"));
        fields.add(notesField);
        fields.add(new JLabel("Day index:"));
        fields.add(dayIndex);

        JButton selectBtn = new JButton("Select");
        selectBtn.addActionListener(e -> {
            String text = "Day " + dayIndex.getValue() + ": " + cityField.getText();
            if (!notesField.getText().isEmpty()) {
                text += " (" + notesField.getText() + ")";
            }
            builderPage.addStopFromDialog(text);
            dispose();
        });

        add(fields, BorderLayout.CENTER);
        add(selectBtn, BorderLayout.SOUTH);
    }

}
