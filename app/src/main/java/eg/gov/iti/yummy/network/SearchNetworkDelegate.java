package eg.gov.iti.yummy.network;

import java.util.ArrayList;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.RootCategory;
import eg.gov.iti.yummy.model.RootCountry;
import eg.gov.iti.yummy.model.RootIngredient;
import eg.gov.iti.yummy.model.RootMealDetail;

public interface SearchNetworkDelegate {

    public void onSuccessAllIngredients(RootIngredient ingredients);
    public void onSuccessAllCategories(RootCategory category);
    public void onSuccessAllCountries(RootCountry country);

}
