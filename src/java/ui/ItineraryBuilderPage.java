package ui;

import javax.swing.*;
import java.awt.*;

public class ItineraryBuilderPage extends JPanel{
    final DefaultListModel<String> stopsModel;

    public ItineraryBuilderPage(TravelPathAPP app) {
        setLayout(new BorderLayout());
        add(Components.createTopNav(app), BorderLayout.NORTH);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField titleField = new JTextField("My Trip", 20);
        JSpinner startDate = new JSpinner(new SpinnerDateModel());
        top.add(new JLabel("Trip Title:"));
        top.add(titleField);
        top.add(new JLabel("Start Date:"));
        top.add(startDate);

        stopsModel = new DefaultListModel<>();
        JList<String> stopsList = new JList<>(stopsModel);
        JScrollPane stopScroll = new JScrollPane(stopsList);
        stopScroll.setBorder(BorderFactory.createTitledBorder("Stops"));

        JPanel buttons = getJPanel(app, stopsList);

        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createTitledBorder("Map Preview"));
        rightPanel.add(new JLabel("Route preview placeholder"));

        add(top, BorderLayout.NORTH);
        add(stopScroll, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.EAST);
    }

    private JPanel getJPanel(TravelPathAPP app, JList<String> stopsList) {
        JButton addStop = new JButton("Add Stop");
        JButton deleteStop = new JButton("Delete Selected");
        JButton saveTrip = new JButton("Save Itinerary");
        JButton viewRoute = new JButton("View Route");

        addStop.addActionListener(e -> {
            AddStopDialog dialog = new AddStopDialog(app, this);
            dialog.setVisible(true);
        });

        deleteStop.addActionListener(e -> {
            int idx = stopsList.getSelectedIndex();
            if (idx >= 0) {
                stopsModel.remove(idx);
            }
        });

        viewRoute.addActionListener(e -> app.showPage("route"));

        JPanel buttons = new JPanel();
        buttons.add(addStop);
        buttons.add(deleteStop);
        buttons.add(saveTrip);
        buttons.add(viewRoute);
        return buttons;
    }

    public void addStopFromDialog(String stopDescription) {
        stopsModel.addElement(stopDescription);
    }
}
