package eg.gov.iti.yummy.meal_details.presenter;

import eg.gov.iti.yummy.meal_details.view.MealViewInterface;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.network.DetailsNetworkDelegate;

public class MealPresenter implements MealPresenterInterface, DetailsNetworkDelegate {

    private Repository repository;
    private MealViewInterface mealViewInterface;

    public MealPresenter(Repository repository, MealViewInterface mealViewInterface) {
        this.repository = repository;
        this.mealViewInterface = mealViewInterface;
    }

    @Override
    public void getSpecificMeal(String meal) {
        repository.getMealFromRetrofit(this,meal);
    }

    @Override
    public void onSuccessFindingMeal(RootMealDetail meal) {
        mealViewInterface.showSpecificItem(meal);
    }
}
