package eg.gov.iti.yummy.home.home.presenter;

import eg.gov.iti.yummy.model.MealDetail;

public interface HomePresenterInterface {
    public void addToFavHome(MealDetail meal);
    public void getRandomMealsForYou();
    public void getRandomMealsTrending();
    public void getRandomMealsNewDishes();
}
