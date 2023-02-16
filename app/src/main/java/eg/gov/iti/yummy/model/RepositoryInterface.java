package eg.gov.iti.yummy.model;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.yummy.network.DetailsNetworkDelegate;
import eg.gov.iti.yummy.network.FilterNetworkDelegate;
import eg.gov.iti.yummy.network.NetworkDelegate;
import eg.gov.iti.yummy.network.SearchNetworkDelegate;
import io.reactivex.rxjava3.core.Observable;

public interface RepositoryInterface {
    public void getForYouMeals(NetworkDelegate networkDelegate);
    public void getTrendingMeals(NetworkDelegate networkDelegate);
    public void getNewDishesMeals(NetworkDelegate networkDelegate);
    public void getAllIngredientsFromRetrofit(SearchNetworkDelegate searchNetworkDelegate);
    public void getAllCategoriesFromRetrofit(SearchNetworkDelegate searchNetworkDelegate);
    public void getAllCountriesFromRetrofit(SearchNetworkDelegate searchNetworkDelegate);
    public void filterByIngredientFromRetrofit(FilterNetworkDelegate filterNetworkDelegate,String ingredient);
    public void filterByCategoryFromRetrofit(FilterNetworkDelegate filterNetworkDelegate,String category);
    public void filterByCountryFromRetrofit(FilterNetworkDelegate filterNetworkDelegate,String country);
    public void getMealFromRetrofit(DetailsNetworkDelegate detailsNetworkDelegate, String meal);
    public Observable<List<MealDetail>> getStoredMeals();
    public void deleteMeal(MealDetail meal);
    public void insertMeal(MealDetail meal);
    public void insertMealIntoWeek(WeekPlan meal);
    public void updateSat(String x);
    public void updateSun(String x);
    public void updateMon(String x);
    public void updateTues(String x);
    public void updateWed(String x);
    public void updateThurs(String x);
    public void updateFri(String x);
    public Observable<MealDetail> getOfflineMeal(String name);
}
