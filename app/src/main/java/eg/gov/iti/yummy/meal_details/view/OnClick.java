package eg.gov.iti.yummy.meal_details.view;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.WeekPlan;

public interface OnClick {
    void addMealToFavOnClick(MealDetail meal);
    void addMealsToWeekPlanOnClick(WeekPlan meals);
    void insertMealInFirebase(MealDetail mealDetail,String key);
    void insertMealInWeekPlanFirebase(WeekPlan mealDetail,String key);
}
