package eg.gov.iti.yummy.weeklyPlan.view.presenter;

import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.weeklyPlan.view.view.WeekPlanViewInterface;

public class WeekPlanPresenter implements WeekPlanPresenterInterface {
    private Repository repository;
    WeekPlanViewInterface weekPlanViewInterface;

    public WeekPlanPresenter(Repository repository, WeekPlanViewInterface weekPlanViewInterface) {
        this.repository = repository;
        this.weekPlanViewInterface = weekPlanViewInterface;
    }

    @Override
    public void getSaturdayMeals(String meal) {

    }

    @Override
    public void getSundayMeals(String meal) {

    }

    @Override
    public void getMondayMeals(String meal) {

    }

    @Override
    public void getTuesdayMeals(String meal) {

    }

    @Override
    public void getWednesdayMeals(String meal) {

    }

    @Override
    public void getThursdayMeals(String meal) {

    }

    @Override
    public void getFridayMeals(String meal) {

    }
}
