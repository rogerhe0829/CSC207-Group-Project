package ui;

import javax.swing.*;
import java.awt.*;

public class TravelPathAPP {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TravelPathAPP().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("TravelPath - Weather-Aware Trip Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(new HomePage(this), "home");
        cardPanel.add(new DestinationSearchPage(this), "search");
        cardPanel.add(new WeatherDetailsPage(this), "weather");
        cardPanel.add(new ItineraryBuilderPage(this), "itinerary");
        cardPanel.add(new RouteViewPage(this), "route");
        cardPanel.add(new DayTimelinePage(this), "day");
        cardPanel.add(new MyTripsPage(this), "trips");
        cardPanel.add(new SettingsPage(this), "settings");

        frame.setContentPane(cardPanel);
        frame.setVisible(true);

        showPage("home");
    }

    public void showPage(String name) {
        cardLayout.show(cardPanel, name);
    }
}

