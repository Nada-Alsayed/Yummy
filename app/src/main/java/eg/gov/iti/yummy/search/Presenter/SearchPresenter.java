package eg.gov.iti.yummy.search.Presenter;

import java.util.ArrayList;

import eg.gov.iti.yummy.home.home.presenter.HomePresenterInterface;
import eg.gov.iti.yummy.home.home.view.HomeViewInterface;
import eg.gov.iti.yummy.model.Category;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootCategory;
import eg.gov.iti.yummy.model.RootCountry;
import eg.gov.iti.yummy.model.RootIngredient;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.network.NetworkDelegate;
import eg.gov.iti.yummy.network.SearchNetworkDelegate;
import eg.gov.iti.yummy.search.view.SearchViewInterface;

public class SearchPresenter implements SearchPresenterInterface, SearchNetworkDelegate {

    private Repository repository;
    private SearchViewInterface searchViewInterface;

    public SearchPresenter(Repository repository, SearchViewInterface searchViewInterface) {
        this.repository = repository;
        this.searchViewInterface = searchViewInterface;
    }

    @Override
    public void getAllIngredients() {
        repository.getAllIngredientsFromRetrofit(this);
    }

    @Override
    public void getAllCategories() {
        repository.getAllCategoriesFromRetrofit(this);
    }

    @Override
    public void getAllCountries() {
        repository.getAllCountriesFromRetrofit(this);
    }


    @Override
    public void onSuccessAllIngredients(RootIngredient ingredients) {
        searchViewInterface.showAllIngredients(ingredients);
    }
    @Override
    public void onSuccessAllCategories(RootCategory category) {
        searchViewInterface.showAllCategories(category);
    }

    @Override
    public void onSuccessAllCountries(RootCountry country) {
        searchViewInterface.showAllCountries(country);
    }

}

