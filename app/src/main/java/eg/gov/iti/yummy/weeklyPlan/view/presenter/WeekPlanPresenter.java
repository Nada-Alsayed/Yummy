package eg.gov.iti.yummy.weeklyPlan.view.presenter;

import java.util.List;

import eg.gov.iti.yummy.meal_details.view.MealViewInterface;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.WeekPlan;
import eg.gov.iti.yummy.weeklyPlan.view.view.WeekPlanViewInterface;
import io.reactivex.rxjava3.core.Observable;

public class WeekPlanPresenter implements WeekPlanPresenterInterface{
    private Repository repository;
    private WeekPlanViewInterface weekPlanViewInterface;

    public WeekPlanPresenter(Repository repository, WeekPlanViewInterface weekPlanViewInterface) {
        this.repository = repository;
        this.weekPlanViewInterface = weekPlanViewInterface;
    }

    @Override
    public Observable<List<WeekPlan>> getFridayMeal() {
        return repository.getStoredFriMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getSatdayMeal() {
        return repository.getStoredSatMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getSundayMeal() {
        return repository.getStoredSunMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getMondayMeal() {
        return repository.getStoredMonMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getTuesdayMeal() {
        return repository.getStoredTuesMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getThursdayMeal() {
        return repository.getStoredThursMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getWeddayMeal() {
        return repository.getStoredWedMeals();
    }
}
