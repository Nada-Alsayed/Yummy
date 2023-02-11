package eg.gov.iti.yummy.model;

import eg.gov.iti.yummy.network.DetailsNetworkDelegate;
import eg.gov.iti.yummy.network.FilterNetworkDelegate;
import eg.gov.iti.yummy.network.NetworkDelegate;
import eg.gov.iti.yummy.network.SearchNetworkDelegate;

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

}
