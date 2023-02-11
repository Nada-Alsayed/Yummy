package eg.gov.iti.yummy.search.view;


import eg.gov.iti.yummy.model.RootCategory;
import eg.gov.iti.yummy.model.RootCountry;
import eg.gov.iti.yummy.model.RootIngredient;

public interface SearchViewInterface {

    public void showAllIngredients(RootIngredient products);
    public void showAllCategories(RootCategory category);
    public void showAllCountries(RootCountry country);

}
