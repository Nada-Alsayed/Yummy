package eg.gov.iti.yummy.db;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.yummy.model.MealDetail;
import io.reactivex.rxjava3.core.Observable;

public interface LocalSource {
    void insertMeal(MealDetail meal);
    void deleteMeal(MealDetail meal);
    Observable<MealDetail> getOfflineMeal(String mealName);
    void registerUser(UserEntity userEntity);
    Observable<List<MealDetail>> getAllStoredMeals();
    String login(String userName, String password);
    String is_Taken(String name);

//    LiveData<UserEntity> getData(String username);
//    void updateSaturday(String saturday,String username);
//    void updateSunday(String sunday,String username);
//    void updateMonday(String monday,String username);
//    void updateTuesday(String tuesday,String username);
//    void updateWednesday(String wednesday,String username);
//    void updateThursday(String thursday,String username);
//    void updateFriday(String friday,String username);
//    void updateFavourite(String favourite,String username);
}
