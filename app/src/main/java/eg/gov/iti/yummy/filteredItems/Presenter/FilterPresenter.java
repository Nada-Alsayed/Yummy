package eg.gov.iti.yummy.filteredItems.Presenter;

import eg.gov.iti.yummy.filteredItems.view.FilterViewInterface;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.network.FilterNetworkDelegate;
import eg.gov.iti.yummy.search.view.SearchViewInterface;

public class FilterPresenter implements FilterPresenterInterface, FilterNetworkDelegate {

    private Repository repository;
    private FilterViewInterface filterViewInterface;

    public FilterPresenter(Repository repository, FilterViewInterface filterViewInterface) {
        this.repository = repository;
        this.filterViewInterface = filterViewInterface;
    }

    @Override
    public void getAllMealsFilterByIngredient(String ingredient) {
        repository.filterByIngredientFromRetrofit(this,ingredient);
    }

    @Override
    public void getAllMealsFilterByCategory(String category) {
        repository.filterByCategoryFromRetrofit(this,category);
    }

    @Override
    public void getAllMealsFilterByCountry(String country) {
        repository.filterByCountryFromRetrofit(this,country);
    }

    @Override
    public void onSuccessFilterByIngredient(RootMealDetail meals) {
        filterViewInterface.showFilteredItemsByIngredient(meals);
    }

    @Override
    public void onSuccessFilterByCategory(RootMealDetail meals) {
        filterViewInterface.showFilteredItemsByCategory(meals);
    }

    @Override
    public void onSuccessFilterByCountry(RootMealDetail meals) {
        filterViewInterface.showFilteredItemsByCountry(meals);
    }

}
