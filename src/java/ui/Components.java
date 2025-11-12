package ui;

import javax.swing.*;
import java.awt.*;

public class Components {
    public static JPanel createTopNav(TravelPathAPP app) {
        JPanel nav = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nav.add(new JLabel("TravelPath"));

        JButton homeBtn = new JButton("Home");
        JButton searchBtn = new JButton("Search");
        JButton tripsBtn = new JButton("My Trips");
        JButton settingsBtn = new JButton("Settings");

        homeBtn.addActionListener(e -> app.showPage("home"));
        searchBtn.addActionListener(e -> app.showPage("search"));
        tripsBtn.addActionListener(e -> app.showPage("trips"));
        settingsBtn.addActionListener(e -> app.showPage("settings"));

        nav.add(homeBtn);
        nav.add(searchBtn);
        nav.add(tripsBtn);
        nav.add(settingsBtn);

        return nav;
    }
}
