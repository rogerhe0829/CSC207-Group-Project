package data_access;

import entity.SavedItinerary;
import java.util.List;

public interface ItineraryDataAccessInterface {

    /**
     * 保存一个行程。
     */
    void save(SavedItinerary itinerary);

    /**
     * 根据用户名获取这个用户所有历史行程。
     */
    List<SavedItinerary> getAllForUser(String username);
}
