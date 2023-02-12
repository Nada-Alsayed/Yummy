package eg.gov.iti.yummy.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface UserDao {
    @Insert
    void registerUser(UserEntity userEntity);
    @Query("Select exists(Select * from UserData where userName=:username and password=:password)")
    Observable<Boolean> login(String username, String password);

    @Query("Select exists(Select * from UserData where userName=:username )")
    Observable<Boolean> is_Taken(String username);

    @Query("Select * from UserData where userName=:username")
    LiveData<UserEntity> getData(String username);
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
