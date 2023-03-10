package eg.gov.iti.yummy.home.home.presenter;

import eg.gov.iti.yummy.model.MealDetail;

public interface HomePresenterInterface {
    public void addToFavHome(MealDetail meal);
    void addMealToFavFirebase(MealDetail meal,String key);
    public void getRandomMealsForYou();
    public void getRandomMealsTrending();
    public void getRandomMealsNewDishes();
}
