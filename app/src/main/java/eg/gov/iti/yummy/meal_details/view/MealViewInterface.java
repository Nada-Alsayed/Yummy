package eg.gov.iti.yummy.meal_details.view;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.model.WeekPlan;

public interface MealViewInterface {
    void addMealToFav(MealDetail meal);
    void addMealInFirebase(MealDetail mealDetail,String key);
    //public void getOffMeal(LiveData<MealDetail> meal);
    void showSpecificItem(RootMealDetail meals);
    void addMealInWeekPlanFirebase(WeekPlan mealDetail, String key);
}
