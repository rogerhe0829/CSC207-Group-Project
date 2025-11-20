package Uisteven;

import data_access.InMemoryItineraryDAO;
import usecase.get_previous_itineraries.GetPreviousItinerariesInteractor;

import javax.swing.*;
import java.awt.*;

public class CombinedDemoFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public CombinedDemoFrame() {
        super("TravelPath – Combined Demo");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel previousDemoPage = createPreviousItinerariesPage();
        JPanel travelPathDemoPage = new TravelPathView();

        cardPanel.add(previousDemoPage, "previous");
        cardPanel.add(travelPathDemoPage, "travelpath");

        JButton toPrevious = new JButton("Previous Itineraries");
        toPrevious.addActionListener(e -> cardLayout.show(cardPanel, "previous"));

        JButton toTravelPath = new JButton("TravelPath Demo");
        toTravelPath.addActionListener(e -> cardLayout.show(cardPanel, "travelpath"));

        JPanel topBar = new JPanel();
        topBar.add(toPrevious);
        topBar.add(toTravelPath);

        setLayout(new BorderLayout());
        add(topBar, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    /**
     * 组装 User Story 6 & 7 的 demo 页面。
     */
    private JPanel createPreviousItinerariesPage() {
        JPanel panel = new JPanel(new FlowLayout());

        // Clean Architecture 链
        InMemoryItineraryDAO dao = new InMemoryItineraryDAO();
        GetPreviousItinerariesPresenter presenter = new GetPreviousItinerariesPresenter();
        GetPreviousItinerariesInteractor interactor =
                new GetPreviousItinerariesInteractor(dao, presenter);
        GetPreviousItinerariesController controller =
                new GetPreviousItinerariesController(interactor);

        JButton button = new JButton("Get Previous Data");
        button.addActionListener(e -> controller.execute("demo-user"));

        panel.add(button);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CombinedDemoFrame frame = new CombinedDemoFrame();
            frame.setVisible(true);
        });
    }
}
