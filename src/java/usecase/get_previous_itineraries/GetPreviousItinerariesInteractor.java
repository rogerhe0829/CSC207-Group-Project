package usecase.get_previous_itineraries;

import data_access.ItineraryDataAccessInterface;
import entity.SavedItinerary;

import java.util.ArrayList;
import java.util.List;

/**
 * 真正执行「获取历史行程」逻辑的 Interactor。
 */
public class GetPreviousItinerariesInteractor
        implements GetPreviousItinerariesInputBoundary {

    private final ItineraryDataAccessInterface itineraryDAO;
    private final GetPreviousItinerariesOutputBoundary presenter;

    public GetPreviousItinerariesInteractor(ItineraryDataAccessInterface itineraryDAO,
                                            GetPreviousItinerariesOutputBoundary presenter) {
        this.itineraryDAO = itineraryDAO;
        this.presenter = presenter;
    }

    @Override
    public void execute(GetPreviousItinerariesInputData inputData) {
        // 1. 从 DAO 里拿这个用户所有保存过的行程
        List<SavedItinerary> saved =
                itineraryDAO.getAllForUser(inputData.getUsername());

        if (saved.isEmpty()) {
            presenter.prepareFailView("No previous trips found.");
            return;
        }

        // 2. 把实体转换成 UI 需要的 Summary 列表
        List<PreviousItinerarySummary> summaries = new ArrayList<>();
        int index = 0;
        for (SavedItinerary it : saved) {
            summaries.add(new PreviousItinerarySummary(
                    String.valueOf(index++),
                    it.getOrigin(),
                    it.getDestination()
            ));
        }

        // 3. 打包成 OutputData 交给 Presenter
        GetPreviousItinerariesOutputData outputData =
                new GetPreviousItinerariesOutputData(summaries);
        presenter.prepareSuccessView(outputData);
    }
}
