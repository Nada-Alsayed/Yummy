package eg.gov.iti.yummy.filteredItems.Presenter;

public interface FilterPresenterInterface {

    public void getAllMealsFilterByIngredient(String ingredient);
    public void getAllMealsFilterByCategory(String ingredient);
    public void getAllMealsFilterByCountry(String country);
}
