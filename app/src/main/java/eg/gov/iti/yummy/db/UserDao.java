package eg.gov.iti.yummy.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    void registerUser(UserEntity userEntity);
    @Query("Select * from UserData where userName=(:username) and password=(:password)")
    LiveData<UserEntity> login(String username, String password);
    @Query("Select * from UserData where userName=(:username)")
    LiveData<UserEntity> getData(String username);
    @Query("Update UserData set saturday=(:saturday)")
    void updateSaturday(String saturday);
    @Query("Update UserData set sunday=(:sunday)")
    void updateSunday(String sunday);
    @Query("Update UserData set monday=(:monday)")
    void updateMonday(String monday);
    @Query("Update UserData set tuesday=(:tuesday)")
    void updateTuesday(String tuesday);
    @Query("Update UserData set wednesday=(:wednesday)")
    void updateWednesday(String wednesday);
    @Query("Update UserData set thursday=(:thursday)")
    void updateThursday(String thursday);
    @Query("Update UserData set friday=(:friday)")
    void updateFriday(String friday);
    @Query("Update UserData set favourite=(:favourite)")
    void updateFavourite(String favourite);

}
