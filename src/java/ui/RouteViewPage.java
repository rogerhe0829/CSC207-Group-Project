package ui;

import javax.swing.*;
import java.awt.*;

public class RouteViewPage extends JPanel {
    public RouteViewPage(TravelPathAPP app) {
        setLayout(new BorderLayout());
        add(Components.createTopNav(app), BorderLayout.NORTH);

        JPanel top = new JPanel(new GridLayout(2, 2, 5, 5));
        top.setBorder(BorderFactory.createTitledBorder("Route Summary"));
        top.add(new JLabel("From: Toronto"));
        top.add(new JLabel("To: Montreal"));
        top.add(new JLabel("Distance: 540 km"));
        top.add(new JLabel("Estimated time: 5h 30m (drive)"));

        JPanel listPanel = new JPanel();
        listPanel.setBorder(BorderFactory.createTitledBorder("Segments"));
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.add(new JLabel("Toronto → Kingston : 260 km / 2h 30m"));
        listPanel.add(new JLabel("Kingston → Montreal : 280 km / 3h 00m"));

        JPanel map = new JPanel();
        map.setBorder(BorderFactory.createTitledBorder("Route Map"));
        map.add(new JLabel("Full route map placeholder"));

        add(top, BorderLayout.NORTH);
        add(listPanel, BorderLayout.WEST);
        add(map, BorderLayout.CENTER);
    }
}
