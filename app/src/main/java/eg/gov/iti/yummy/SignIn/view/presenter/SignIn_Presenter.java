package eg.gov.iti.yummy.SignIn.view.presenter;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.WeekPlan;
import eg.gov.iti.yummy.weeklyPlan.view.view.WeekPlanViewInterface;

public class SignIn_Presenter implements SignInPresenterInterface{
    private Repository repository;

    public SignIn_Presenter(Repository repository) {
        this.repository = repository;
    }
    @Override
    public void addToWeekPlan(WeekPlan meal) {
       repository.insertMealIntoWeek(meal);
    }

    @Override
    public void addToFav(MealDetail meal) {
        repository.insertMeal(meal);
    }
}
