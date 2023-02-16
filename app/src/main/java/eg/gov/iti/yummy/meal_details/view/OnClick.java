package eg.gov.iti.yummy.meal_details.view;

import eg.gov.iti.yummy.model.MealDetail;

public interface OnClick {
    void addMealToFavOnClick(MealDetail meal);
    void addMealsToWeekPlanOnClick(WeekMeals week);
}
