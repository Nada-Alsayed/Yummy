package eg.gov.iti.yummy.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.WeekPlan;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMealToL(MealDetail meal);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMealToWeekPlan(WeekPlan week);
    @Query("Select * From MealData")
    Observable<List<MealDetail>> getAllProducts();
    @Query("Select * From MealData Where strMeal=:mealName")
    Observable<MealDetail> getOfflineMeal(String mealName);
    @Query("Select * From WeekPlan Where strMeal=:mealName")
    Observable<WeekPlan> getOfflineMealWeek(String mealName);
    @Query("DELETE FROM WeekPlan")
    void deleteMyPlan();
    @Query("DELETE FROM MealData")
    void deleteMeals();
    @Delete
    void deleteMeal(MealDetail meal);
    @Delete
    void deleteMeal(WeekPlan meal);
    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable registerUser(UserEntity userEntity);*/
    /*@Query("Select exists(Select userName from UserData where userName=:username and password=:password)")
    Observable<Boolean> login(String username, String password);

    @Query("Select exists(Select userName from UserData where userName=:username )")
    Observable<Boolean> is_Taken(String username);*/

    //insert meals in each day
    @Query("Update WeekPlan set sat=(:saturday) where idMeal=(:id)")
   // @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable updateSaturday(String saturday,String id);
    @Query("Update WeekPlan set sun=(:sunday) where idMeal=(:id)")
   //@Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable updateSunday(String sunday ,String id);

    @Query("Update weekplan set mon=(:monday) where idMeal=(:id)")
   // @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable updateMonday(String monday,String id);

    @Query("Update WeekPlan set tues=(:tuesday) where idMeal=(:id)")
   // @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable updateTuesday(String tuesday,String id);

    @Query("Update WeekPlan set wed=(:wednesday) where idMeal=(:id)")
   //@Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable updateWednesday(String wednesday,String id);

    @Query("Update WeekPlan set thurs=(:thursday)where idMeal=(:id)")
    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable updateThursday(String thursday ,String id);

   @Query("Update WeekPlan set fri=(:friday) where idMeal=(:id)")
   //@Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable updateFriday(String friday ,String id);

    //get meals by day
    @Query("Select * From WeekPlan where fri=(:friday)")
    Observable<List<WeekPlan>> getFridayMeals(String friday);
    @Query("Select * From WeekPlan where sat=(:saturday)")
    Observable<List<WeekPlan>>  getSaturdayMeals(String saturday);
    @Query("Select * From WeekPlan where sun=(:sunday)")
    Observable<List<WeekPlan>>  getSundayMeals(String sunday);
    @Query("Select * From WeekPlan where mon=(:monday)")
    Observable<List<WeekPlan>>getMondayMeals(String monday);

    @Query("Select * From WeekPlan where tues=(:tuesday)")
    Observable<List<WeekPlan>> getTuesdayMeals(String tuesday);
    @Query("Select * From WeekPlan where wed=(:wedday)")
    Observable<List<WeekPlan>> getWednesdayMeals(String wedday);

    @Query("Select * From WeekPlan where thurs=(:thursday)")
    Observable<List<WeekPlan>> getThursdayMeals(String thursday);


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
