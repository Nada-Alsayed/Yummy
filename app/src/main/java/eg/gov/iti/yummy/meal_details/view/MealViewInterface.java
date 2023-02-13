package eg.gov.iti.yummy.meal_details.view;

import androidx.lifecycle.LiveData;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.RootMealDetail;
import io.reactivex.rxjava3.core.Observable;

public interface MealViewInterface {
    public void addMeal(MealDetail meal);
    public void getOffMeal(LiveData<MealDetail> meal);
    public void showSpecificItem(RootMealDetail meals);
}
