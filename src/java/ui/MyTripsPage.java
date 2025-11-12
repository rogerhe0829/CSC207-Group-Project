package ui;

import javax.swing.*;
import java.awt.*;

public class MyTripsPage extends JPanel {
    public MyTripsPage(TravelPathAPP app) {
        setLayout(new BorderLayout());
        add(Components.createTopNav(app), BorderLayout.NORTH);

        JPanel cards = new JPanel();
        cards.setLayout(new BoxLayout(cards, BoxLayout.Y_AXIS));
        cards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cards.add(createTripCard("Summer Europe", "2025-07-01", 6));
        cards.add(Box.createVerticalStrut(10));
        cards.add(createTripCard("Weekend in Montreal", "2025-05-10", 3));

        JButton newTrip = new JButton("Create New Trip");
        newTrip.addActionListener(e -> app.showPage("itinerary"));

        add(new JScrollPane(cards), BorderLayout.CENTER);
        add(newTrip, BorderLayout.SOUTH);
    }

    private JPanel createTripCard(String title, String startDate, int stops) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createTitledBorder(title));
        card.add(new JLabel("Start date: " + startDate), BorderLayout.NORTH);
        card.add(new JLabel("Number of stops: " + stops), BorderLayout.CENTER);
        card.add(new JLabel("Map thumbnail placeholder"), BorderLayout.EAST);
        return card;
    }
}
