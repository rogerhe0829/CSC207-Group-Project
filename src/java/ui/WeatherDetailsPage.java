package ui;

import javax.swing.*;
import java.awt.*;

public class WeatherDetailsPage extends JPanel{
    public WeatherDetailsPage(TravelPathAPP app) {
        setLayout(new BorderLayout());
        add(Components.createTopNav(app), BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel header = new JLabel("Toronto, CA (43.7°N, 79.4°W)");
        header.setFont(header.getFont().deriveFont(Font.BOLD, 18f));
        content.add(header);

        JPanel current = new JPanel(new GridLayout(2, 2, 5, 5));
        current.setBorder(BorderFactory.createTitledBorder("Current Weather"));
        current.add(new JLabel("Temperature: 15°C"));
        current.add(new JLabel("Condition: Cloudy ☁"));
        current.add(new JLabel("Wind: 10 km/h"));
        current.add(new JLabel("Humidity: 60%"));

        JScrollPane forecastScroll = getJScrollPane();
        forecastScroll.setBorder(BorderFactory.createTitledBorder("7-Day Forecast"));

        JButton addToTrip = new JButton("Add to Itinerary");
        addToTrip.addActionListener(e -> app.showPage("itinerary"));

        content.add(Box.createVerticalStrut(10));
        content.add(current);
        content.add(Box.createVerticalStrut(10));
        content.add(forecastScroll);
        content.add(Box.createVerticalStrut(10));
        content.add(addToTrip);

        add(content, BorderLayout.CENTER);
    }

    private static JScrollPane getJScrollPane() {
        String[] cols = {"Day", "High", "Low", "Icon", "Precip"};
        Object[][] data = {
                {"Mon", "17°", "10°", "☁", "10%"},
                {"Tue", "20°", "12°", "☀", "0%"},
                {"Wed", "22°", "13°", "☀", "0%"},
                {"Thu", "18°", "11°", "🌧", "70%"},
                {"Fri", "16°", "9°", "🌧", "60%"},
                {"Sat", "15°", "8°", "☁", "20%"},
                {"Sun", "14°", "7°", "☁", "10%"}
        };
        JTable table = new JTable(data, cols);
        return new JScrollPane(table);
    }
}
