package eg.gov.iti.yummy.db;

import androidx.lifecycle.LiveData;

public interface LocalSource {
    void registerUser(UserEntity userEntity);
    LiveData<UserEntity> login(String userName, String password);
    LiveData<UserEntity> getData(String username);
    void updateSaturday(String saturday);
    void updateSunday(String sunday);
    void updateMonday(String monday);
    void updateTuesday(String tuesday);
    void updateWednesday(String wednesday);
    void updateThursday(String thursday);
    void updateFriday(String friday);
    void updateFavourite(String favourite);
}
