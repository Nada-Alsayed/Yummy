package eg.gov.iti.yummy.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import eg.gov.iti.yummy.model.MealDetail;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMeal(MealDetail meal);

    @Query("Select * From MealData")
    Observable<List<MealDetail>> getAllProducts();
    @Query("Select * From MealData Where strMeal=:mealName")
    Observable<MealDetail> getOfflineMeal(String mealName);

    @Delete
    void deleteMeal(MealDetail meal);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable registerUser(UserEntity userEntity);
    @Query("Select exists(Select userName from UserData where userName=:username and password=:password)")
    Observable<Boolean> login(String username, String password);

    @Query("Select exists(Select userName from UserData where userName=:username )")
    Observable<Boolean> is_Taken(String username);
//
//    @Query("Select * from UserData where userName=:username")
//    LiveData<UserEntity> getData(String username);
//    @Query("Update UserData set saturday=(:saturday) where userName=(:username)")
//    void updateSaturday(String saturday , String username);
//    @Query("Update UserData set sunday=(:sunday) where userName=(:username)")
//    void updateSunday(String sunday , String username);
//    @Query("Update UserData set monday=(:monday) where userName=(:username)")
//    void updateMonday(String monday , String username);
//    @Query("Update UserData set tuesday=(:tuesday) where userName=(:username)")
//    void updateTuesday(String tuesday , String username);
//    @Query("Update UserData set wednesday=(:wednesday) where userName=(:username)")
//    void updateWednesday(String wednesday , String username);
//    @Query("Update UserData set thursday=(:thursday) where userName=(:username)")
//    void updateThursday(String thursday , String username);
//    @Query("Update UserData set friday=(:friday) where userName=(:username)")
//    void updateFriday(String friday , String username);
//    @Query("Update UserData set favourite=(:favourite) where userName=(:username)")
//    void updateFavourite(String favourite , String username);
}
