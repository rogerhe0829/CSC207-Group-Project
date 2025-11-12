package ui;

import javax.swing.*;
import java.awt.*;

public class SettingsPage extends JPanel {
    public SettingsPage(TravelPathAPP app) {
        setLayout(new BorderLayout());
        add(Components.createTopNav(app), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel unitsRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        unitsRow.add(new JLabel("Units:"));
        JRadioButton cBtn = new JRadioButton("°C", true);
        JRadioButton fBtn = new JRadioButton("°F");
        ButtonGroup group = new ButtonGroup();
        group.add(cBtn);
        group.add(fBtn);
        unitsRow.add(cBtn);
        unitsRow.add(fBtn);

        JPanel langRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        langRow.add(new JLabel("Language:"));
        JComboBox<String> langBox = new JComboBox<>(new String[]{"English", "中文", "Français"});
        langRow.add(langBox);

        JPanel notifyRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox notifyBox = new JCheckBox("Weather alert notifications");
        notifyRow.add(notifyBox);

        JPanel apiRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        apiRow.add(new JLabel("API key:"));
        JTextField apiField = new JTextField(20);
        apiRow.add(apiField);

        form.add(unitsRow);
        form.add(langRow);
        form.add(notifyRow);
        form.add(apiRow);

        add(form, BorderLayout.CENTER);
    }
}
