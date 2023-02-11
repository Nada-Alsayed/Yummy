package eg.gov.iti.yummy.network;

public interface RemoteSource {
    void randomMealForYou(NetworkDelegate networkDelegate);
    void randomMealTrending(NetworkDelegate networkDelegate);
    void randomMealNewDishes(NetworkDelegate networkDelegate);
    void allIngredients(SearchNetworkDelegate searchNetworkDelegate);
    void allCategories(SearchNetworkDelegate searchNetworkDelegate);
    void allCountries(SearchNetworkDelegate searchNetworkDelegate);
    void filterByIngredient(FilterNetworkDelegate filterNetworkDelegate,String ingredient);
    void filterByCategory(FilterNetworkDelegate filterNetworkDelegate,String category);
    void filterByCountry(FilterNetworkDelegate filterNetworkDelegate,String country);

}
