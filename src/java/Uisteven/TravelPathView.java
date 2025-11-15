package Uisteven;

// src/main/java/view/TravelPathView.java
package view;

import interface_adapter.logout.LogoutController;
import interface_adapter.travelpath.TravelPathState;
import interface_adapter.travelpath.TravelPathState.ForecastDay;
import interface_adapter.travelpath.TravelPathState.ItineraryStop;
import interface_adapter.travelpath.TravelPathViewModel;
import interface_adapter.travelpath.SearchDestinationController;
import interface_adapter.travelpath.AddStopController;
import interface_adapter.travelpath.RemoveStopController;
import interface_adapter.travelpath.ReorderStopController;
import interface_adapter.travelpath.SaveItineraryController;
import interface_adapter.travelpath.UpdateNotesController;
import interface_adapter.travelpath.ViewForecastController;

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

/**
 * TravelPath 主界面。
 */
public class TravelPathView extends JPanel implements ActionListener, PropertyChangeListener {

    // action command 常量
    private static final String CMD_SEARCH = "search";
    private static final String CMD_ADD_STOP = "addStop";
    private static final String CMD_REMOVE_STOP = "removeStop";
    private static final String CMD_MOVE_UP = "moveUp";
    private static final String CMD_MOVE_DOWN = "moveDown";
    private static final String CMD_SAVE = "save";
    private static final String CMD_UPDATE_NOTES = "updateNotes";
    private static final String CMD_VIEW_FORECAST = "viewForecast";
    private static final String CMD_LOGOUT = "logout";

    private final TravelPathViewModel viewModel;

    private final SearchDestinationController searchDestinationController;
    private final AddStopController addStopController;
    private final RemoveStopController removeStopController;
    private final ReorderStopController reorderStopController;
    private final SaveItineraryController saveItineraryController;
    private final UpdateNotesController updateNotesController;
    private final ViewForecastController viewForecastController;
    private final LogoutController logoutController;

    // 顶部：搜索 & 天气 & 路线
    private final JTextField destinationField = new JTextField(20);
    private final JButton searchButton = new JButton("Search & Weather");

    private final JLabel destinationLabel = new JLabel("Destination: -");
    private final JLabel currentWeatherLabel = new JLabel("Current weather: -");

    private final JLabel routeDistanceLabel = new JLabel("Route distance: -");
    private final JLabel routeDurationLabel = new JLabel("Route duration: -");
    private final JLabel routeSummaryLabel = new JLabel("Route summary: -");

    // 左侧：行程列表
    private final DefaultListModel<String> stopsListModel = new DefaultListModel<>();
    private final JList<String> stopsList = new JList<>(stopsListModel);

    private final JButton addStopButton = new JButton("Add stop");
    private final JButton removeStopButton = new JButton("Remove stop");
    private final JButton moveUpButton = new JButton("Move up");
    private final JButton moveDownButton = new JButton("Move down");

    // 右上：notes
    private final JTextArea notesArea = new JTextArea(5, 25);

    // 右下：forecast
    private final JButton forecastButton = new JButton("7-day forecast");
    private final JTextArea forecastArea = new JTextArea(8, 25);

    // 底部
    private final JButton saveButton = new JButton("Save itinerary");
    private final JButton logoutButton = new JButton("Log out");
    private final JLabel errorLabel = new JLabel(" ");

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TravelPathView(TravelPathViewModel viewModel,
                          SearchDestinationController searchDestinationController,
                          AddStopController addStopController,
                          RemoveStopController removeStopController,
                          ReorderStopController reorderStopController,
                          SaveItineraryController saveItineraryController,
                          UpdateNotesController updateNotesController,
                          ViewForecastController viewForecastController,
                          LogoutController logoutController) {

        this.viewModel = viewModel;
        this.searchDestinationController = searchDestinationController;
        this.addStopController = addStopController;
        this.removeStopController = removeStopController;
        this.reorderStopController = reorderStopController;
        this.saveItineraryController = saveItineraryController;
        this.updateNotesController = updateNotesController;
        this.viewForecastController = viewForecastController;
        this.logoutController = logoutController;

        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        initComponents();
        wireActions();
    }

    private void initComponents() {
        // 顶部：搜索 + 天气 + 路线
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Destination: "));
        searchPanel.add(destinationField);
        searchButton.setActionCommand(CMD_SEARCH);
        searchPanel.add(searchButton);

        JPanel weatherPanel = new JPanel();
        weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.Y_AXIS));
        weatherPanel.add(destinationLabel);
        weatherPanel.add(currentWeatherLabel);

        JPanel routePanel = new JPanel();
        routePanel.setLayout(new BoxLayout(routePanel, BoxLayout.Y_AXIS));
        routePanel.add(routeDistanceLabel);
        routePanel.add(routeDurationLabel);
        routePanel.add(routeSummaryLabel);

        topPanel.add(searchPanel);
        topPanel.add(weatherPanel);
        topPanel.add(routePanel);

        add(topPanel, BorderLayout.NORTH);

        // 左侧：stops 列表
        JPanel leftPanel = new JPanel(new BorderLayout());
        stopsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        leftPanel.add(new JScrollPane(stopsList), BorderLayout.CENTER);

        JPanel leftButtons = new JPanel(new GridLayout(2, 2));
        addStopButton.setActionCommand(CMD_ADD_STOP);
        removeStopButton.setActionCommand(CMD_REMOVE_STOP);
        moveUpButton.setActionCommand(CMD_MOVE_UP);
        moveDownButton.setActionCommand(CMD_MOVE_DOWN);
        leftButtons.add(addStopButton);
        leftButtons.add(removeStopButton);
        leftButtons.add(moveUpButton);
        leftButtons.add(moveDownButton);

        leftPanel.add(leftButtons, BorderLayout.SOUTH);

        // 右侧：notes + forecast
        JPanel rightPanel = new JPanel(new BorderLayout());

        JPanel notesPanel = new JPanel(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder("Notes for selected stop"));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        notesPanel.add(new JScrollPane(notesArea), BorderLayout.CENTER);

        JPanel forecastPanel = new JPanel(new BorderLayout());
        forecastPanel.setBorder(BorderFactory.createTitledBorder("7-day forecast"));
        forecastButton.setActionCommand(CMD_VIEW_FORECAST);
        forecastPanel.add(forecastButton, BorderLayout.NORTH);
        forecastArea.setEditable(false);
        forecastArea.setLineWrap(true);
        forecastArea.setWrapStyleWord(true);
        forecastPanel.add(new JScrollPane(forecastArea), BorderLayout.CENTER);

        rightPanel.add(notesPanel, BorderLayout.NORTH);
        rightPanel.add(forecastPanel, BorderLayout.CENTER);

        JSplitPane centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        centerSplit.setResizeWeight(0.4);
        add(centerSplit, BorderLayout.CENTER);

        // 底部
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        saveButton.setActionCommand(CMD_SAVE);
        logoutButton.setActionCommand(CMD_LOGOUT);
        buttonPanel.add(saveButton);
        buttonPanel.add(logoutButton);

        errorLabel.setForeground(Color.RED);

        bottomPanel.add(buttonPanel, BorderLayout.WEST);
        bottomPanel.add(errorLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void wireActions() {
        searchButton.addActionListener(this);
        addStopButton.addActionListener(this);
        removeStopButton.addActionListener(this);
        moveUpButton.addActionListener(this);
        moveDownButton.addActionListener(this);
        saveButton.addActionListener(this);
        forecastButton.addActionListener(this);
        logoutButton.addActionListener(this);

        // notes 改变时更新 notes（可以换成 focusLost 更省）
        CaretListener caretListener = e ->
                actionPerformed(new ActionEvent(notesArea, ActionEvent.ACTION_PERFORMED, CMD_UPDATE_NOTES));
        notesArea.addCaretListener(caretListener);

        // 选择列表项时更新 selectedStopIndex
        stopsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    TravelPathState state = viewModel.getState();
                    state.setSelectedStopIndex(stopsList.getSelectedIndex());
                    viewModel.setState(state);
                    // 不需要 firePropertyChanged，这里只更新 index；Presenter 一般触发 UI
                }
            }
        });
    }

    // ViewModel -> View
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!"state".equals(evt.getPropertyName())) {
            return;
        }
        TravelPathState state = (TravelPathState) evt.getNewValue();

        // 搜索 & 目的地
        destinationField.setText(state.getDestinationQuery());
        if (state.getResolvedDestinationName() == null || state.getResolvedDestinationName().isEmpty()) {
            destinationLabel.setText("Destination: -");
        } else {
            destinationLabel.setText("Destination: " + state.getResolvedDestinationName());
        }

        // 当前天气
        if (state.getCurrentTemperature() == null || state.getCurrentTemperature().isEmpty()) {
            currentWeatherLabel.setText("Current weather: -");
        } else {
            currentWeatherLabel.setText("Current weather: "
                    + state.getCurrentTemperature() + " | " + state.getCurrentWeatherSummary());
        }

        // 路线信息
        routeDistanceLabel.setText("Route distance: " +
                (isEmpty(state.getLastRouteDistanceText()) ? "-" : state.getLastRouteDistanceText()));
        routeDurationLabel.setText("Route duration: " +
                (isEmpty(state.getLastRouteDurationText()) ? "-" : state.getLastRouteDurationText()));
        routeSummaryLabel.setText("Route summary: " +
                (isEmpty(state.getLastRouteSummary()) ? "-" : state.getLastRouteSummary()));

        // 错误
        if (isEmpty(state.getErrorMessage())) {
            errorLabel.setText(" ");
        } else {
            errorLabel.setText(state.getErrorMessage());
        }

        // stops 列表
        rebuildStopsList(state);

        // notes
        int idx = state.getSelectedStopIndex();
        if (idx >= 0 && idx < state.getStops().size()) {
            ItineraryStop stop = state.getStops().get(idx);
            notesArea.setText(stop.getNotes() == null ? "" : stop.getNotes());
            stopsList.setSelectedIndex(idx);
        } else {
            notesArea.setText("");
            stopsList.clearSelection();
        }

        // forecast
        rebuildForecastArea(state);
    }

    private void rebuildStopsList(TravelPathState state) {
        stopsListModel.clear();
        int dayNumber = 1;
        for (ItineraryStop stop : state.getStops()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Day ").append(dayNumber).append(": ");
            if (stop.getCity() != null) {
                sb.append(stop.getCity());
            } else {
                sb.append("(no city)");
            }
            if (stop.getDay() != null) {
                sb.append(" | ").append(stop.getDay().format(dateFormatter));
            }
            stopsListModel.addElement(sb.toString());
            dayNumber++;
        }
    }

    private void rebuildForecastArea(TravelPathState state) {
        StringBuilder sb = new StringBuilder();
        for (ForecastDay day : state.getForecast()) {
            if (day.getDate() != null) {
                sb.append(day.getDate().format(dateFormatter));
            } else {
                sb.append("Unknown date");
            }
            sb.append(" - ");
            sb.append(day.getSummary() == null ? "" : day.getSummary());
            sb.append(" | High: ").append(nullToDash(day.getHighTemperature()));
            sb.append(" | Low: ").append(nullToDash(day.getLowTemperature()));
            sb.append(" | Rain: ").append(nullToDash(day.getPrecipitationChance()));
            sb.append(System.lineSeparator());
        }
        forecastArea.setText(sb.toString());
    }

    private String nullToDash(String s) {
        return (s == null || s.isEmpty()) ? "-" : s;
    }

    private boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    // View -> Controller
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        try {
            switch (cmd) {
                case CMD_SEARCH:
                    searchDestinationController.execute(destinationField.getText());
                    break;
                case CMD_ADD_STOP:
                    addStopController.execute();
                    break;
                case CMD_REMOVE_STOP:
                    removeStopController.execute();
                    break;
                case CMD_MOVE_UP:
                    reorderStopController.moveUp();
                    break;
                case CMD_MOVE_DOWN:
                    reorderStopController.moveDown();
                    break;
                case CMD_SAVE:
                    saveItineraryController.execute();
                    break;
                case CMD_UPDATE_NOTES:
                    updateNotesController.execute(notesArea.getText());
                    break;
                case CMD_VIEW_FORECAST:
                    viewForecastController.execute();
                    break;
                case CMD_LOGOUT:
                    logoutController.execute();
                    break;
                default:
                    // do nothing
            }
        } catch (Exception ex) {
            TravelPathState state = viewModel.getState();
            state.setErrorMessage("Unexpected error: " + ex.getMessage());
            viewModel.setState(state);
            viewModel.firePropertyChanged();
        }
    }
}
