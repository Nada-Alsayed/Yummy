package eg.gov.iti.yummy.db;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

public interface LocalSource {
    void registerUser(UserEntity userEntity);
    LiveData<UserEntity> login(String userName, String password);
    LiveData<UserEntity> getData(String username);
    LiveData<String> getSaturdayFromDB(String username);

    LiveData<String> getSundayFromDB(String username);

    LiveData<String> getMondayFromDB(String username);

    LiveData<String> getTuesdayFromDB(String username);

    LiveData<String> getWednesdayFromDB(String username);

    LiveData<String> getThursdayFromDB(String username);

    LiveData<String> getFridayFromDB(String username);
    void updateSaturday(String saturday,String username);
    void updateSunday(String sunday,String username);
    void updateMonday(String monday,String username);
    void updateTuesday(String tuesday,String username);
    void updateWednesday(String wednesday,String username);
    void updateThursday(String thursday,String username);
    void updateFriday(String friday,String username);
    void updateFavourite(String favourite,String username);
}
