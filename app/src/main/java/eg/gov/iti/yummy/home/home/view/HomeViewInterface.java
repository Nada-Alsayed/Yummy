package eg.gov.iti.yummy.home.home.view;

import java.util.List;

import eg.gov.iti.yummy.model.MealDetail;

public interface HomeViewInterface {
    void addMealInFirebase(MealDetail mealDetail,String key);
    void addMealToFavHome(MealDetail meal);
    public void showDataForYou(List<MealDetail> products);
    public void showDataTrending(List<MealDetail> products);
    public void showDataNewDishes(List<MealDetail> products);
}
