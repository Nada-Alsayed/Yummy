package eg.gov.iti.yummy.db;

import androidx.lifecycle.LiveData;

public interface LocalSource {
    void registerUser(UserEntity userEntity);
    LiveData<UserEntity> login(String userName, String password);
    LiveData<UserEntity> getData(String username);
    void updateSaturday(String saturday,String username);
    void updateSunday(String sunday,String username);
    void updateMonday(String monday,String username);
    void updateTuesday(String tuesday,String username);
    void updateWednesday(String wednesday,String username);
    void updateThursday(String thursday,String username);
    void updateFriday(String friday,String username);
    void updateFavourite(String favourite,String username);
}
