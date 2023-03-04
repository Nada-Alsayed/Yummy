package eg.gov.iti.yummy.home.home.presenter;

import android.util.Log;

import java.util.ArrayList;

import eg.gov.iti.yummy.home.home.view.HomeViewInterface;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.network.NetworkDelegate;

public class HomePresenter implements HomePresenterInterface, NetworkDelegate {
    public static final String TAG="tag";
   private Repository repository;
   private HomeViewInterface homeViewInterface;

    public HomePresenter(Repository repository, HomeViewInterface homeViewInterface) {
        this.repository = repository;
        this.homeViewInterface = homeViewInterface;
    }

    @Override
    public void addToFavHome(MealDetail meal) {
        repository.insertMeal(meal);
    }

    @Override
    public void getRandomMealsForYou() {
        Log.e(TAG, "getCate:for you presenter failed");
        repository.getForYouMeals(this);
    }

    @Override
    public void getRandomMealsTrending() {
        Log.e(TAG, "getCate:trending presenter failed");
        repository.getTrendingMeals(this);
    }

    @Override
    public void getRandomMealsNewDishes() {
        Log.e(TAG, "getCate:NewDishes presenter failed");
        repository.getNewDishesMeals(this);
    }

    @Override
    public void onFailureResult(String message) {
        Log.e(TAG, "onErrorResult:for you presenter failed");
    }

    @Override
    public void onSuccessForYou(ArrayList<MealDetail> randomListForYou) {
        homeViewInterface.showDataForYou(randomListForYou);
        Log.e(TAG, "onSuccessResult:for you presenter "+ randomListForYou.size());
    }

    @Override
    public void onSuccessTrending(ArrayList<MealDetail> randomListTrending) {
        homeViewInterface.showDataTrending(randomListTrending);
        Log.e(TAG, "onSuccessResult:trending presenter "+ randomListTrending.size());
    }

    @Override
    public void onSuccessNewDishes(ArrayList<MealDetail> randomListNewDishes) {
        homeViewInterface.showDataNewDishes(randomListNewDishes);
        Log.e(TAG, "onSuccessResult:newdishes presenter "+ randomListNewDishes.size());

    }
}
