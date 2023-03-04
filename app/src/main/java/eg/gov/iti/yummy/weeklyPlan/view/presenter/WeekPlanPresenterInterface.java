package eg.gov.iti.yummy.weeklyPlan.view.presenter;

import java.util.List;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.WeekPlan;
import io.reactivex.rxjava3.core.Observable;

public interface WeekPlanPresenterInterface {
    public void deleteMeal(WeekPlan product);
    public Observable<List<WeekPlan>>  getFridayMeal();
    public Observable<List<WeekPlan>>  getSatdayMeal();
    public Observable<List<WeekPlan>>  getSundayMeal();
    public Observable<List<WeekPlan>>  getMondayMeal();
    public Observable<List<WeekPlan>>  getTuesdayMeal();
    public Observable<List<WeekPlan>>  getThursdayMeal();
    public Observable<List<WeekPlan>>  getWeddayMeal();
}
