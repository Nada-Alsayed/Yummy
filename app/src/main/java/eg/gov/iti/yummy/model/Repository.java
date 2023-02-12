package eg.gov.iti.yummy.model;

import android.content.Context;
import android.util.Log;

import eg.gov.iti.yummy.network.DetailsNetworkDelegate;
import eg.gov.iti.yummy.network.FilterNetworkDelegate;
import eg.gov.iti.yummy.network.NetworkDelegate;
import eg.gov.iti.yummy.network.RemoteSource;
import eg.gov.iti.yummy.network.SearchNetworkDelegate;

public class Repository implements RepositoryInterface{
    Context context;
    RemoteSource remoteSource;
    private static Repository repository = null;

    public Repository(RemoteSource remoteSource, Context context) {
        this.context=context;
        this.remoteSource=remoteSource;
    }

    public Repository(Context context) {
        this.context = context;
    }

    public static Repository getInstance(RemoteSource remoteSource, Context context)
    {
        if (repository == null) {
            repository = new Repository(remoteSource, context);
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
    public void getMealFromRetrofit(DetailsNetworkDelegate detailsNetworkDelegate,String meal) {
        Log.i("Repository", "getMealFromRetrofit: "+meal);
        remoteSource.specificItem(detailsNetworkDelegate,meal);
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
    public void filterByIngredientFromRetrofit(FilterNetworkDelegate filterNetworkDelegate,String ingredient) {
        remoteSource.filterByIngredient(filterNetworkDelegate,ingredient);
    }

    @Override
    public void filterByCategoryFromRetrofit(FilterNetworkDelegate filterNetworkDelegate,String category) {
        remoteSource.filterByCategory(filterNetworkDelegate,category);
    }

    @Override
    public void filterByCountryFromRetrofit(FilterNetworkDelegate filterNetworkDelegate,String country) {
        remoteSource.filterByCountry(filterNetworkDelegate,country);
    }


}
