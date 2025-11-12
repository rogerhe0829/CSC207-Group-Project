package ui;

import javax.swing.*;
import java.awt.*;

public class DayTimelinePage extends JPanel {
    public DayTimelinePage(TravelPathAPP app) {
        setLayout(new BorderLayout());
        add(Components.createTopNav(app), BorderLayout.NORTH);

        JPanel timelinePanel = new JPanel();
        timelinePanel.setLayout(new BoxLayout(timelinePanel, BoxLayout.Y_AXIS));
        timelinePanel.setBorder(BorderFactory.createTitledBorder("Day Timeline"));

        timelinePanel.add(new JLabel("09:00 - Hotel → ☀ 15°C"));
        timelinePanel.add(new JLabel("11:00 - Museum → ☁ 17°C"));
        timelinePanel.add(new JLabel("14:00 - Park → 🌧 16°C"));
        timelinePanel.add(new JLabel("18:00 - Restaurant → ☁ 14°C"));

        JPanel map = new JPanel();
        map.setBorder(BorderFactory.createTitledBorder("Day Route Snapshot"));
        map.add(new JLabel("Static map placeholder"));

        JPanel bottom = new JPanel();
        bottom.add(new JButton("Download Snapshot"));
        bottom.add(new JButton("Share Day Plan"));

        add(timelinePanel, BorderLayout.WEST);
        add(map, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}
