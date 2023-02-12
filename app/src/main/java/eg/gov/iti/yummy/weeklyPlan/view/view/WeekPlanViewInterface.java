package eg.gov.iti.yummy.weeklyPlan.view.view;

import eg.gov.iti.yummy.model.RootMealDetail;

public interface WeekPlanViewInterface {
    public void showSaturdayItem(RootMealDetail meals);
    public void showSunItem(RootMealDetail meals);
    public void showMonItem(RootMealDetail meals);
    public void showTuesdayItem(RootMealDetail meals);
    public void showWednesdayItem(RootMealDetail meals);
    public void showThursdayItem(RootMealDetail meals);
    public void showFridayItem(RootMealDetail meals);
}
