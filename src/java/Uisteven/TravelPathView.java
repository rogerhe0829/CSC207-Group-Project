package Uisteven;

import javax.swing.*;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.List;
import model.WeatherData;
import model.PackingTipGenerator;

/**
 * Minimal skeleton of TravelPathView.
 * Next step: wire it to your ViewModel, controllers, and state.
 */
public class TravelPathView extends JPanel implements ActionListener, PropertyChangeListener {

    // (你之后会把这些类型改成你自己项目里的，比如 Uisteven.TravelPathViewModel、Uisteven.SearchDestinationController 等)
    private final Object viewModel;
    private final Object searchDestinationController;
    private final Object addStopController;
    private final Object removeStopController;
    private final Object reorderStopController;
    private final Object saveItineraryController;
    private final Object updateNotesController;
    private final Object viewForecastController;
    private final Object logoutController;

    // 左侧 Past Travel
    private final DefaultListModel<String> pastTravelListModel = new DefaultListModel<>();
    private final JList<String> pastTravelList = new JList<>(pastTravelListModel);
    private final JButton loadPastTravelButton = new JButton("Load");
    private final JButton deletePastTravelButton = new JButton("Delete");

    // 中间 Itinerary + Notes
    private final DefaultListModel<TravelPathState.ItineraryStop> stopsListModel = new DefaultListModel<>();
    private final JList<TravelPathState.ItineraryStop> stopsList = new JList<>(stopsListModel);

    private final JButton addStopButton = new JButton("Add stop");
    private final JButton removeStopButton = new JButton("Remove stop");
    private final JButton moveUpButton = new JButton("Move up");
    private final JButton moveDownButton = new JButton("Move down");

    private final JTextArea notesArea = new JTextArea(5, 25);

    // 右侧 Route Input + Suggestions + Weather/Clothing/Route + Forecast
    private final JTextField originField = new JTextField(10);
    private final JTextField destinationField = new JTextField(10);
    private final JButton searchButton = new JButton("Search & Weather");

    private final JTextArea previousSuggestionArea = new JTextArea(4, 20);
    private final JTextArea currentSuggestionArea = new JTextArea(4, 20);

    private final JLabel destinationLabel = new JLabel("Destination: -");
    private final JLabel currentWeatherLabel = new JLabel("Current weather: -");

    private final JTextArea clothingSuggestionArea = new JTextArea(3, 25);

    private final JLabel routeDistanceLabel = new JLabel("Route distance: -");
    private final JLabel routeDurationLabel = new JLabel("Route duration: -");
    private final JLabel routeSummaryLabel = new JLabel("Route summary: -");

    private final JButton forecastButton = new JButton("7-day forecast");
    private final JTextArea forecastArea = new JTextArea(8, 25);

    private final JButton saveButton = new JButton("Save itinerary");
    private final JButton logoutButton = new JButton("Log out");
    private final JLabel errorLabel = new JLabel(" ");

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TravelPathView() {
        // 暂时用 null 填这些依赖，后面你再把构造函数签名改成真正的：
        // public TravelPathView(TravelPathViewModel vm, SearchDestinationController s, ...) { ... }
        this.viewModel = null;
        this.searchDestinationController = null;
        this.addStopController = null;
        this.removeStopController = null;
        this.reorderStopController = null;
        this.saveItineraryController = null;
        this.updateNotesController = null;
        this.viewForecastController = null;
        this.logoutController = null;

        setLayout(new BorderLayout());
        initComponents();
        wireActions();
    }

    private void initComponents() {
        // ---- 左侧 Past Travels ----
        JPanel pastTravelPanel = new JPanel(new BorderLayout());
        JLabel pastTravelTitleLabel = new JLabel("Past travels");
        pastTravelTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pastTravelPanel.add(pastTravelTitleLabel, BorderLayout.NORTH);

        pastTravelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pastTravelPanel.add(new JScrollPane(pastTravelList), BorderLayout.CENTER);

        JPanel pastButtonPanel = new JPanel();
        pastButtonPanel.add(loadPastTravelButton);
        pastButtonPanel.add(deletePastTravelButton);
        pastTravelPanel.add(pastButtonPanel, BorderLayout.SOUTH);

        // demo data
        pastTravelListModel.addElement("Trip to Tokyo");
        pastTravelListModel.addElement("Trip to Vancouver");

        add(pastTravelPanel, BorderLayout.WEST);

        // ---- 中间 Itinerary + Notes ----
        JPanel centerPanel = new JPanel(new BorderLayout());

        JPanel stopsPanel = new JPanel(new BorderLayout());
        JLabel stopsTitleLabel = new JLabel("Itinerary stops");
        stopsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stopsPanel.add(stopsTitleLabel, BorderLayout.NORTH);

        stopsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stopsListModel.addElement(new TravelPathState.ItineraryStop("Toronto", LocalDate.now(), ""));
        stopsListModel.addElement(new TravelPathState.ItineraryStop("Montreal", LocalDate.now().plusDays(1), ""));
        stopsPanel.add(new JScrollPane(stopsList), BorderLayout.CENTER);

        JPanel stopsButtonPanel = new JPanel();
        stopsButtonPanel.add(addStopButton);
        stopsButtonPanel.add(removeStopButton);
        stopsButtonPanel.add(moveUpButton);
        stopsButtonPanel.add(moveDownButton);
        stopsPanel.add(stopsButtonPanel, BorderLayout.SOUTH);

        JPanel notesPanel = new JPanel(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder("Notes for selected stop"));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        notesPanel.add(new JScrollPane(notesArea), BorderLayout.CENTER);

        JSplitPane centerSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, stopsPanel, notesPanel);
        centerSplit.setResizeWeight(0.5);
        centerPanel.add(centerSplit, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // ---- 右侧 ----
        JPanel rightPanel = new JPanel(new BorderLayout());

        // Route input
        JPanel routeInputPanel = new JPanel();
        routeInputPanel.setBorder(BorderFactory.createTitledBorder("Trip setup"));
        routeInputPanel.add(new JLabel("Origin:"));
        routeInputPanel.add(originField);
        routeInputPanel.add(new JLabel("Destination:"));
        routeInputPanel.add(destinationField);
        routeInputPanel.add(searchButton);

        rightPanel.add(routeInputPanel, BorderLayout.NORTH);

        // Suggestions + weather + forecast
        JPanel rightCenterPanel = new JPanel();
        rightCenterPanel.setLayout(new BoxLayout(rightCenterPanel, BoxLayout.Y_AXIS));

        // Suggestions
        JPanel suggestionPanel = new JPanel(new GridLayout(2, 1, 4, 4));

        previousSuggestionArea.setLineWrap(true);
        previousSuggestionArea.setWrapStyleWord(true);
        previousSuggestionArea.setEditable(false);
        previousSuggestionArea.setText("Previous suggestion will appear here.");

        currentSuggestionArea.setLineWrap(true);
        currentSuggestionArea.setWrapStyleWord(true);
        currentSuggestionArea.setEditable(false);
        currentSuggestionArea.setText("Current suggestion will appear here.");

        JPanel previousSuggestionPanel = new JPanel(new BorderLayout());
        previousSuggestionPanel.setBorder(
                BorderFactory.createTitledBorder("Previous suggestion – time needed to travel"));
        previousSuggestionPanel.add(new JScrollPane(previousSuggestionArea), BorderLayout.CENTER);

        JPanel currentSuggestionPanel = new JPanel(new BorderLayout());
        currentSuggestionPanel.setBorder(
                BorderFactory.createTitledBorder("Current suggestion – time needed to travel"));
        currentSuggestionPanel.add(new JScrollPane(currentSuggestionArea), BorderLayout.CENTER);

        suggestionPanel.add(previousSuggestionPanel);
        suggestionPanel.add(currentSuggestionPanel);

        // Weather + clothing + route
        JPanel weatherRoutePanel = new JPanel(new BorderLayout());
        JPanel weatherAndClothingPanel = new JPanel();
        weatherAndClothingPanel.setLayout(new BoxLayout(weatherAndClothingPanel, BoxLayout.Y_AXIS));

        weatherAndClothingPanel.add(destinationLabel);
        weatherAndClothingPanel.add(currentWeatherLabel);
        weatherAndClothingPanel.add(Box.createVerticalStrut(4));

        JPanel clothingPanel = new JPanel(new BorderLayout());
        clothingPanel.setBorder(BorderFactory.createTitledBorder("Clothing suggestion"));
        clothingSuggestionArea.setLineWrap(true);
        clothingSuggestionArea.setWrapStyleWord(true);
        clothingSuggestionArea.setEditable(false);
        clothingSuggestionArea.setText("Clothing suggestions based on weather will appear here.");
        clothingPanel.add(new JScrollPane(clothingSuggestionArea), BorderLayout.CENTER);

        weatherAndClothingPanel.add(clothingPanel);
        weatherAndClothingPanel.add(Box.createVerticalStrut(4));

        JPanel routeInfoPanel = new JPanel();
        routeInfoPanel.setLayout(new BoxLayout(routeInfoPanel, BoxLayout.Y_AXIS));
        routeInfoPanel.setBorder(
                BorderFactory.createTitledBorder("Optimal travel path & time needed to travel"));
        routeInfoPanel.add(routeDistanceLabel);
        routeInfoPanel.add(routeDurationLabel);
        routeInfoPanel.add(routeSummaryLabel);

        weatherAndClothingPanel.add(routeInfoPanel);

        weatherRoutePanel.add(weatherAndClothingPanel, BorderLayout.CENTER);

        // Forecast
        JPanel forecastPanel = new JPanel(new BorderLayout());
        forecastPanel.setBorder(BorderFactory.createTitledBorder("7-day forecast"));
        forecastPanel.add(forecastButton, BorderLayout.NORTH);
        forecastArea.setEditable(false);
        forecastArea.setLineWrap(true);
        forecastArea.setWrapStyleWord(true);
        forecastPanel.add(new JScrollPane(forecastArea), BorderLayout.CENTER);

        weatherRoutePanel.add(forecastPanel, BorderLayout.SOUTH);

        suggestionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        weatherRoutePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        rightCenterPanel.add(suggestionPanel);
        rightCenterPanel.add(Box.createVerticalStrut(8));
        rightCenterPanel.add(weatherRoutePanel);

        rightPanel.add(rightCenterPanel, BorderLayout.CENTER);

        add(rightPanel, BorderLayout.EAST);

        // ---- 底部 Save / Logout / Error ----
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(saveButton);
        buttonPanel.add(logoutButton);

        errorLabel.setForeground(Color.RED);

        bottomPanel.add(buttonPanel, BorderLayout.WEST);
        bottomPanel.add(errorLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void wireActions() {
        // 先不连 controller，避免你现在没有这些类导致更多报错
        // 后面你可以把下面这些 addActionListener 改成 actionPerformed 里调用真正的 controller

        searchButton.addActionListener(this);
        addStopButton.addActionListener(this);
        removeStopButton.addActionListener(this);
        moveUpButton.addActionListener(this);
        moveDownButton.addActionListener(this);
        saveButton.addActionListener(this);
        forecastButton.addActionListener(this);
        logoutButton.addActionListener(this);

        CaretListener caretListener = e ->
                actionPerformed(new ActionEvent(notesArea, ActionEvent.ACTION_PERFORMED, "updateNotes"));
        notesArea.addCaretListener(caretListener);

        stopsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    TravelPathState.ItineraryStop selected = stopsList.getSelectedValue();
                    if (selected != null) {
                        notesArea.setText(selected.getNotes());
                    } else {
                        notesArea.setText("");
                    }
                }
            }
        });

        loadPastTravelButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "TODO: Load selected past travel.",
                        "Not implemented yet",
                        JOptionPane.INFORMATION_MESSAGE));

        deletePastTravelButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "TODO: Delete selected past travel.",
                        "Not implemented yet",
                        JOptionPane.INFORMATION_MESSAGE));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // 以后你连接 ViewModel 时，在这里从 state 里刷新 UI
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "updateNotes": {
                // User Story 6: 把 notesArea 里的内容写回当前选中的 stop
                TravelPathState.ItineraryStop selected = stopsList.getSelectedValue();
                if (selected != null) {
                    selected.setNotes(notesArea.getText());
                    // 重绘列表，让 ItineraryStop.toString() 里的 📌 立刻更新
                    stopsList.repaint();
                }
                break;
            }
            case "Search & Weather": {
                // User Story 7 Demo:
                // 以后这里应该改成调用真正的 SearchDestination / Weather Use Case。
                // 现在先用一个 demo 的天气，演示根据天气生成 packing tips。
                WeatherData demoWeather = new WeatherData(2.0, "light rain", 3.0, 0.0);
                List<String> tips = PackingTipGenerator.generate(demoWeather);

                StringBuilder sb = new StringBuilder("Weather-based packing tips:\n");
                for (String tip : tips) {
                    sb.append("• ").append(tip).append("\n");
                }
                clothingSuggestionArea.setText(sb.toString());
                break;
            }
            default:
                // 其他按钮暂时还没接 Use Case，就先弹个 TODO
                JOptionPane.showMessageDialog(this,
                        "Clicked: " + cmd + "\nController not wired yet.",
                        "TODO",
                        JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
