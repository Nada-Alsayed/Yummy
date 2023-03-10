package eg.gov.iti.yummy.model;

import android.content.Context;
import android.util.Log;

import java.util.List;

import eg.gov.iti.yummy.db.LocalSource;
import eg.gov.iti.yummy.network.DetailsNetworkDelegate;
import eg.gov.iti.yummy.network.FilterNetworkDelegate;
import eg.gov.iti.yummy.network.NetworkDelegate;
import eg.gov.iti.yummy.network.RemoteSource;
import eg.gov.iti.yummy.network.SearchNetworkDelegate;
import io.reactivex.rxjava3.core.Observable;

public class Repository implements RepositoryInterface {
    Context context;
    RemoteSource remoteSource;
    LocalSource localSource;

    private static Repository repository = null;

    public Repository(RemoteSource remoteSource, LocalSource localSource, Context context) {
        this.context = context;
        this.localSource = localSource;
        this.remoteSource = remoteSource;
    }

    public Repository(Context context) {
        this.context = context;
    }

    public static Repository getInstance(RemoteSource remoteSource, LocalSource localSource, Context context) {
        if (repository == null) {
            repository = new Repository(remoteSource, localSource, context);
        }
        return repository;
    }

    @Override
    public void getForYouMeals(NetworkDelegate networkDelegate) {
        remoteSource.randomMealForYou(networkDelegate);
    }

    @Override
    public void getTrendingMeals(NetworkDelegate networkDelegate) {
        remoteSource.randomMealTrending(networkDelegate);
    }

    @Override
    public void getNewDishesMeals(NetworkDelegate networkDelegate) {
        remoteSource.randomMealNewDishes(networkDelegate);
    }

    @Override
    public void getAllIngredientsFromRetrofit(SearchNetworkDelegate searchNetworkDelegate) {
        remoteSource.allIngredients(searchNetworkDelegate);
    }

    @Override
    public void getMealFromRetrofit(DetailsNetworkDelegate detailsNetworkDelegate, String meal) {
        Log.i("Repository", "getMealFromRetrofit: " + meal);
        remoteSource.specificItem(detailsNetworkDelegate, meal);
    }

    @Override
    public Observable<List<MealDetail>> getStoredMeals() {
        return localSource.getAllStoredMeals();
    }

    @Override
    public void deleteMeal(MealDetail meal) {
        localSource.deleteMeal(meal);
    }

    @Override
    public void deleteMeal(WeekPlan meal2) {
        localSource.deleteMeal(meal2);
    }

    @Override
    public void deleteMeals() {
        localSource.deleteMeals();
    }

    @Override
    public void deletePlan() {
        localSource.deletePlan();
    }

    @Override
    public void insertMeal(MealDetail meal) {
        localSource.insertMealToFav(meal);
    }


    @Override
    public void insertMealIntoWeek(WeekPlan meal) {
        localSource.insertMealToWeekPlan(meal);
    }

    @Override
    public Observable<List<WeekPlan>> getStoredFriMeals() {
        return localSource.getFridayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredSatMeals() {
        return localSource.getSatdayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredSunMeals() {
        return localSource.getSundayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredMonMeals() {
        return localSource.getMondayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredTuesMeals() {
        return localSource.getTuesdayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredWedMeals() {
        return localSource.getWeddayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredThursMeals() {
        return localSource.getThursdayMeals();
    }

    @Override
    public void updateSat(String x,String id) {
        localSource.updateSaturday(x,id);
    }

    @Override
    public void updateSun(String x,String id) {localSource.updateSunday(x,id);
    }

    @Override
    public void updateMon(String x,String id) {
        localSource.updateMonday(x,id);
    }

    @Override
    public void updateTues(String x,String id) {
        localSource.updateTuesday(x,id);
    }

    @Override
    public void updateWed(String x,String id) {
        localSource.updateWednesday(x,id);
    }

    @Override
    public void updateThurs(String x,String id) {

        localSource.updateThursday(x,id);
    }

    @Override
    public void updateFri(String x,String id) {
        localSource.updateFriday(x,id);
    }

    @Override
    public Observable<MealDetail> getOfflineMeal(String name) {
        Log.e("joo", "getOfflineMeal:rebo ");
        return localSource.getOfflineMeal(name);
    }

    @Override
    public Observable<WeekPlan> getOfflineMealWeek(String name) {
        Log.e("joo", "getOfflineMeal:rebo ");
        return localSource.getOfflineMealWeek(name);
    }

    @Override
    public void getAllCategoriesFromRetrofit(SearchNetworkDelegate searchNetworkDelegate) {
        remoteSource.allCategories(searchNetworkDelegate);
    }

    @Override
    public void getAllCountriesFromRetrofit(SearchNetworkDelegate searchNetworkDelegate) {
        remoteSource.allCountries(searchNetworkDelegate);
    }

    @Override
    public void filterByIngredientFromRetrofit(FilterNetworkDelegate filterNetworkDelegate, String ingredient) {
        remoteSource.filterByIngredient(filterNetworkDelegate, ingredient);
    }

    @Override
    public void filterByCategoryFromRetrofit(FilterNetworkDelegate filterNetworkDelegate, String category) {
        remoteSource.filterByCategory(filterNetworkDelegate, category);
    }

    @Override
    public void filterByCountryFromRetrofit(FilterNetworkDelegate filterNetworkDelegate, String country) {
        remoteSource.filterByCountry(filterNetworkDelegate, country);
    }


}
