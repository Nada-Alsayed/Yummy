package eg.gov.iti.yummy.meal_details.presenter;

import androidx.lifecycle.LiveData;

import eg.gov.iti.yummy.meal_details.view.WeekMeals;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.WeekPlan;
import io.reactivex.rxjava3.core.Observable;

public interface MealPresenterInterface {
    public void addToFav(MealDetail meal);
    void addMealToFavFirebase(MealDetail meal,String key);
    void addMealToWeekPlanFirebase(WeekPlan meal,String key);
    public void getSpecificMeal(String meal);
    public void addToWeekPlan(WeekPlan meal);
    public Observable<MealDetail> getOffMeal(String meal);
    public Observable<WeekPlan> getOffMealWeek(String meal);
}
