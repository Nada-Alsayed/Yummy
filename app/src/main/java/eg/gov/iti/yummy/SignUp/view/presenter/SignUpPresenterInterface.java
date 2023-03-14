package eg.gov.iti.yummy.SignUp.view.presenter;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.WeekPlan;

public interface SignUpPresenterInterface {
    void addToWeekPlan(WeekPlan meal);
    void addToFav(MealDetail meal);
}
