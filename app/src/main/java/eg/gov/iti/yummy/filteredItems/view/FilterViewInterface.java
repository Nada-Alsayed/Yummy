package eg.gov.iti.yummy.filteredItems.view;

import eg.gov.iti.yummy.model.RootIngredient;
import eg.gov.iti.yummy.model.RootMealDetail;

public interface FilterViewInterface {
    public void showFilteredItemsByIngredient(RootMealDetail meals);
    public void showFilteredItemsByCategory(RootMealDetail meals);
    public void showFilteredItemsByCountry(RootMealDetail meals);
}
