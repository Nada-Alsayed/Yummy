package eg.gov.iti.yummy.favourite.presenter;

import java.util.List;

import eg.gov.iti.yummy.model.MealDetail;
import io.reactivex.rxjava3.core.Observable;

public interface FavMealPresenterInterface {
    public Observable<List<MealDetail>> getStoredMeals();
    public void deleteMeal(MealDetail product);
}
