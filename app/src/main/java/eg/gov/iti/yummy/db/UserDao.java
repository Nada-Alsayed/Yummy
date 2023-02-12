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
    @Query("Select * from UserData where userName=:username and password=:password")
    LiveData<UserEntity> login(String username, String password);
    @Query("Select * from UserData where userName=:username")
    LiveData<UserEntity> getData(String username);

    @Query("Select saturday from UserData where userName=:username")
    LiveData<String> getSaturdayFromDB(String username);

    @Query("Select sunday from UserData where userName=:username")
    LiveData<String> getSundayFromDB(String username);

    @Query("Select monday from UserData where userName=:username")
    LiveData<String> getMondayFromDB(String username);

    @Query("Select tuesday from UserData where userName=:username")
    LiveData<String> getTuesdayFromDB(String username);

    @Query("Select wednesday from UserData where userName=:username")
    LiveData<String> getWednesdayFromDB(String username);

    @Query("Select thursday from UserData where userName=:username")
    LiveData<String> getThursdayFromDB(String username);

    @Query("Select friday from UserData where userName=:username")
    LiveData<String> getFridayFromDB(String username);


    @Query("Update UserData set saturday=(:saturday) where userName=(:username)")
    void updateSaturday(String saturday , String username);
    @Query("Update UserData set sunday=(:sunday) where userName=(:username)")
    void updateSunday(String sunday , String username);
    @Query("Update UserData set monday=(:monday) where userName=(:username)")
    void updateMonday(String monday , String username);
    @Query("Update UserData set tuesday=(:tuesday) where userName=(:username)")
    void updateTuesday(String tuesday , String username);
    @Query("Update UserData set wednesday=(:wednesday) where userName=(:username)")
    void updateWednesday(String wednesday , String username);
    @Query("Update UserData set thursday=(:thursday) where userName=(:username)")
    void updateThursday(String thursday , String username);
    @Query("Update UserData set friday=(:friday) where userName=(:username)")
    void updateFriday(String friday , String username);
    @Query("Update UserData set favourite=(:favourite) where userName=(:username)")
    void updateFavourite(String favourite , String username);
}
