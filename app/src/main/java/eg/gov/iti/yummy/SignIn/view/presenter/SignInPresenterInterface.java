package eg.gov.iti.yummy.SignIn.view.presenter;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.WeekPlan;

public interface SignInPresenterInterface {
    void addToWeekPlan(WeekPlan meal);
    void addToFav(MealDetail meal);
}
