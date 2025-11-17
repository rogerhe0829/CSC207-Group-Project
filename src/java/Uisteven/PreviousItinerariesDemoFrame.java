package Uisteven;

import data_access.InMemoryItineraryDAO;
import usecase.get_previous_itineraries.GetPreviousItinerariesInteractor;

import javax.swing.*;
import java.awt.*;

/**
 * 一个非常简单的 Demo 界面：
 * 只有一个 “Get Previous Data” 按钮，用来演示你的 use case。
 */
public class PreviousItinerariesDemoFrame extends JFrame {

    private final GetPreviousItinerariesController controller;

    public PreviousItinerariesDemoFrame() {
        super("Previous Itineraries Demo");

        // 1. 组装 Clean Architecture 这条链
        InMemoryItineraryDAO dao = new InMemoryItineraryDAO();
        GetPreviousItinerariesPresenter presenter = new GetPreviousItinerariesPresenter();
        GetPreviousItinerariesInteractor interactor =
                new GetPreviousItinerariesInteractor(dao, presenter);
        this.controller = new GetPreviousItinerariesController(interactor);

        // 2. 简单的 UI：一个按钮
        JButton button = new JButton("Get Previous Data");
        button.addActionListener(e -> {
            // 先写死一个 demo 用户，后面可以换成真正的登录用户
            controller.execute("demo-user");
        });

        setLayout(new FlowLayout());
        add(button);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 120);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PreviousItinerariesDemoFrame frame = new PreviousItinerariesDemoFrame();
            frame.setVisible(true);
        });
    }
}
