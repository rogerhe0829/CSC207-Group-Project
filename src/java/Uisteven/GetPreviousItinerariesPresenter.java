package Uisteven;

import usecase.get_previous_itineraries.GetPreviousItinerariesOutputBoundary;
import usecase.get_previous_itineraries.GetPreviousItinerariesOutputData;
import usecase.get_previous_itineraries.PreviousItinerarySummary;

import javax.swing.*;
import java.util.List;

/**
 * Presenter：把用例结果转成界面要显示的东西。
 * 现在简单用弹窗展示 “Past Travel 0/1/...” 的信息。
 */
public class GetPreviousItinerariesPresenter implements GetPreviousItinerariesOutputBoundary {

    @Override
    public void prepareSuccessView(GetPreviousItinerariesOutputData outputData) {
        List<PreviousItinerarySummary> list = outputData.getItineraries();

        StringBuilder sb = new StringBuilder("Previous trips:\n\n");
        for (PreviousItinerarySummary s : list) {
            sb.append("Trip ")
                    .append(s.getId())
                    .append(": ")
                    .append(s.getOrigin())
                    .append(" → ")
                    .append(s.getDestination())
                    .append('\n');
        }

        JOptionPane.showMessageDialog(
                null,
                sb.toString(),
                "Previous Itineraries",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    public void prepareFailView(String errorMessage) {
        JOptionPane.showMessageDialog(
                null,
                errorMessage,
                "Previous Itineraries",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
