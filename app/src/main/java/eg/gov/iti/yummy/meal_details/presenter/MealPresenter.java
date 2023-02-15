package eg.gov.iti.yummy.meal_details.presenter;

import android.util.Log;

import androidx.lifecycle.LiveData;

import eg.gov.iti.yummy.meal_details.view.MealViewInterface;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.network.DetailsNetworkDelegate;
import io.reactivex.rxjava3.core.Observable;

public class MealPresenter implements MealPresenterInterface, DetailsNetworkDelegate {
    private Repository repository;
    private MealViewInterface mealViewInterface;

    public MealPresenter(Repository repository, MealViewInterface mealViewInterface) {
        this.repository = repository;
        this.mealViewInterface = mealViewInterface;
    }

    @Override
    public void addToFav(MealDetail meal) {
        repository.insertMeal(meal);
    }

    @Override
    public void getSpecificMeal(String meal) {
        repository.getMealFromRetrofit(this,meal);
    }


    @Override
    public Observable<MealDetail> getOffMeal(String meal) {
        Log.e("TAG", "getOffMeal: meal presenter");
        //repository.getOfflineMeal(meal).observe(meal);
        return repository.getOfflineMeal(meal);

    }

    @Override
    public void onSuccessFindingMeal(RootMealDetail meal) {
        mealViewInterface.showSpecificItem(meal);
    }
}
