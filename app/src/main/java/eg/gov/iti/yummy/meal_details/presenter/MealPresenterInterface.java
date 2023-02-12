package eg.gov.iti.yummy.meal_details.presenter;

import eg.gov.iti.yummy.model.MealDetail;

public interface MealPresenterInterface {
    public void addToFav(MealDetail meal);
    public void getSpecificMeal(String meal);

    public void getOffMeal(String meal);
}
