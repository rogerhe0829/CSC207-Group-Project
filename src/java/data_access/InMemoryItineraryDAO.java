package data_access;

import entity.SavedItinerary;
import entity.TravelSuggestion;

import java.util.*;

/**
 * 一个简单的内存版 DAO，用来存 / 取行程。
 * 现在先用 Map 当“数据库”，以后可以换成文件或真正的 API。
 */
public class InMemoryItineraryDAO implements ItineraryDataAccessInterface {

    // key: username, value: 这个用户所有的行程列表
    private final Map<String, List<SavedItinerary>> data = new HashMap<>();

    public InMemoryItineraryDAO() {
        // 先放两条 demo 数据，方便你后面测试和 demo。
        String demoUser = "demo-user";

        List<SavedItinerary> demoList = new ArrayList<>();
        demoList.add(new SavedItinerary(
                "0",
                demoUser,
                "Toronto",
                "New York",
                new TravelSuggestion(
                        "8 hours",
                        "Sunny, 20°C",
                        "Flight AC123",
                        "T-shirt + light jacket"
                )
        ));

        demoList.add(new SavedItinerary(
                "1",
                demoUser,
                "Toronto",
                "Vancouver",
                new TravelSuggestion(
                        "5 hours",
                        "Rainy, 10°C",
                        "Flight AC456",
                        "Coat + umbrella"
                )
        ));

        data.put(demoUser, demoList);
    }

    @Override
    public void save(SavedItinerary itinerary) {
        data.computeIfAbsent(itinerary.getUsername(), u -> new ArrayList<>())
                .add(itinerary);
    }

    @Override
    public List<SavedItinerary> getAllForUser(String username) {
        // 返回一个新的 list，避免外面修改内部数据
        return new ArrayList<>(data.getOrDefault(username, new ArrayList<>()));
    }
}
