package eg.gov.iti.yummy.meal_details.presenter;

import androidx.lifecycle.LiveData;

import eg.gov.iti.yummy.model.MealDetail;
import io.reactivex.rxjava3.core.Observable;

public interface MealPresenterInterface {
    public void addToFav(MealDetail meal);
    public void getSpecificMeal(String meal);
    public Observable<MealDetail> getOffMeal(String meal);
}
