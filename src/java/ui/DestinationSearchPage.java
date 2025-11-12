package ui;

import javax.swing.*;
import java.awt.*;

public class DestinationSearchPage extends JPanel {
    public DestinationSearchPage(TravelPathAPP app) {
        setLayout(new BorderLayout());

        add(Components.createTopNav(app), BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField searchField = new JTextField("Search city...");
        JButton searchBtn = new JButton("Search");
        JPanel searchRow = new JPanel(new BorderLayout(5, 5));
        searchRow.add(searchField, BorderLayout.CENTER);
        searchRow.add(searchBtn, BorderLayout.EAST);

        JPanel cityInfo = new JPanel();
        cityInfo.setLayout(new BoxLayout(cityInfo, BoxLayout.Y_AXIS));
        cityInfo.setBorder(BorderFactory.createTitledBorder("City Info"));

        JLabel cityName = new JLabel("City: (none)");
        JLabel country = new JLabel("Country: -");
        JLabel weather = new JLabel("Weather: -");
        JButton addToItinerary = new JButton("Add to Itinerary");

        addToItinerary.addActionListener(e -> app.showPage("itinerary"));

        cityInfo.add(cityName);
        cityInfo.add(country);
        cityInfo.add(weather);
        cityInfo.add(Box.createVerticalStrut(10));
        cityInfo.add(addToItinerary);

        leftPanel.add(searchRow);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(cityInfo);

        JPanel mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createTitledBorder("Map View"));
        mapPanel.add(new JLabel("Map Placeholder (here would be Mapbox / Google Maps)"));

        add(leftPanel, BorderLayout.WEST);
        add(mapPanel, BorderLayout.CENTER);
    }
}
