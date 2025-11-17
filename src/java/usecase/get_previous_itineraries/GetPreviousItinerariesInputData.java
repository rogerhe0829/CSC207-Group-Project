package usecase.get_previous_itineraries;

/**
 * 输入数据：目前只需要用户名，用来查这个用户的历史行程。
 */
public class GetPreviousItinerariesInputData {

    private final String username;

    public GetPreviousItinerariesInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
