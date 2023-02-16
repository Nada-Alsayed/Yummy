package eg.gov.iti.yummy.meal_details.view;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.RootMealDetail;

public interface MealViewInterface {
    public void addMealToFav(MealDetail meal);

    //public void getOffMeal(LiveData<MealDetail> meal);
    public void showSpecificItem(RootMealDetail meals);
}
