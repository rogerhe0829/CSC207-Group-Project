package Uisteven;
import usecase.manage_stop.ManageStopOutputBoundary;
import usecase.manage_stop.ManageStopOutputData;
import javax.swing.*;

/**
 * Presenter for User Story 9.
 * For now, it just shows a simple dialog with the result.
 */
public class ManageStopPresenter implements ManageStopOutputBoundary {

    @Override
    public void prepareSuccessView(ManageStopOutputData outputData) {
        StringBuilder sb = new StringBuilder();
        sb.append(outputData.getMessage()).append("\n\nUpdated stops:\n");

        int i = 0;
        for (ManageStopOutputData.StopSummary s : outputData.getStops()) {
            sb.append(i++)
                    .append(". Day ")
                    .append(s.getDayIndex())
                    .append(" – ")
                    .append(s.getCityName());
            if (s.getNotes() != null && !s.getNotes().isEmpty()) {
                sb.append(" (notes)");
            }
            sb.append("\n");
        }

        JOptionPane.showMessageDialog(
                null,
                sb.toString(),
                "Manage Stops",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        JOptionPane.showMessageDialog(
                null,
                errorMessage,
                "Manage Stops",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
