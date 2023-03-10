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
    public void deleteMeal(WeekPlan meal);
    void deleteMeals();
    void deletePlan();
    public void insertMeal(MealDetail meal);
    public void insertMealIntoWeek(WeekPlan meal);

    public Observable<List<WeekPlan>> getStoredFriMeals();
    public Observable<List<WeekPlan>> getStoredSatMeals();
    public Observable<List<WeekPlan>> getStoredSunMeals();
    public Observable<List<WeekPlan>> getStoredMonMeals();
    public Observable<List<WeekPlan>> getStoredTuesMeals();
    public Observable<List<WeekPlan>> getStoredWedMeals();
    public Observable<List<WeekPlan>> getStoredThursMeals();

    public void updateSat(String x,String id);
    public void updateSun(String x,String id);
    public void updateMon(String x,String id);
    public void updateTues(String x,String id);
    public void updateWed(String x,String id);
    public void updateThurs(String x,String id);
    public void updateFri(String x,String id);
    public Observable<MealDetail> getOfflineMeal(String name);
    public Observable<WeekPlan> getOfflineMealWeek(String name);
}
