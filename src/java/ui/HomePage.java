package ui;

import javax.swing.*;
import java.awt.*;


public class HomePage extends JPanel {
    public HomePage(TravelPathAPP app) {
        setLayout(new BorderLayout());

        add(Components.createTopNav(app), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        JTextField cityField = new JTextField("Enter city name...");
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(e -> app.showPage("search"));

        searchPanel.add(cityField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        JPanel recentPanel = new JPanel();
        recentPanel.setLayout(new BoxLayout(recentPanel, BoxLayout.Y_AXIS));
        recentPanel.setBorder(BorderFactory.createTitledBorder("Recent Searches"));
        recentPanel.add(new JLabel("- Toronto"));
        recentPanel.add(new JLabel("- Tokyo"));
        recentPanel.add(new JLabel("- Vancouver"));

        JPanel recommendedPanel = new JPanel();
        recommendedPanel.setLayout(new BoxLayout(recommendedPanel, BoxLayout.Y_AXIS));
        recommendedPanel.setBorder(BorderFactory.createTitledBorder("Recommended Destinations"));
        recommendedPanel.add(new JLabel("• Paris (nice weather this week)"));
        recommendedPanel.add(new JLabel("• New York"));
        recommendedPanel.add(new JLabel("• Hong Kong"));

        center.add(searchPanel);
        center.add(Box.createVerticalStrut(20));
        center.add(recentPanel);
        center.add(Box.createVerticalStrut(20));
        center.add(recommendedPanel);

        add(center, BorderLayout.CENTER);
    }
}
