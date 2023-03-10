package eg.gov.iti.yummy.db;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.WeekPlan;
import io.reactivex.rxjava3.core.Observable;

public interface LocalSource {
    Observable<List<WeekPlan>>getSundayMeals();
    Observable<List<WeekPlan>>getMondayMeals();
    Observable<List<WeekPlan>>getTuesdayMeals();
    Observable<List<WeekPlan>>getWeddayMeals();
    Observable<List<WeekPlan>>getThursdayMeals();
    Observable<List<WeekPlan>>getSatdayMeals();
    Observable<List<WeekPlan>>getFridayMeals();
    void insertMealToFav(MealDetail meal);
    void insertMealToWeekPlan(WeekPlan meal);
    void deleteMeal(MealDetail meal);
    void deleteMeals();
    void deletePlan();
    void deleteMeal(WeekPlan meal2);
    Observable<MealDetail> getOfflineMeal(String mealName);

    abstract Observable<WeekPlan> getOfflineMealWeek(String mealName);
    /*void registerUser(UserEntity userEntity);*/
    Observable<List<MealDetail>> getAllStoredMeals();
   /* String login(String userName, String password);
    String is_Taken(String name);*/

//  LiveData<UserEntity> getData(String username);
    void updateSaturday(String saturday,String id);
    void updateSunday(String sunday,String id);
    void updateMonday(String monday,String id);
    void updateTuesday(String tuesday,String id);
    void updateWednesday(String wednesday,String id);
    void updateThursday(String thursday,String id);
    void updateFriday(String friday,String id);
    //void updateFavourite(String favourite);
}
