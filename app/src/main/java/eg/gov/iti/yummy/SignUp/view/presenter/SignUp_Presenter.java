package eg.gov.iti.yummy.SignUp.view.presenter;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.WeekPlan;

public class SignUp_Presenter implements SignUpPresenterInterface {
    private Repository repository;

    public SignUp_Presenter(Repository repository) {
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
