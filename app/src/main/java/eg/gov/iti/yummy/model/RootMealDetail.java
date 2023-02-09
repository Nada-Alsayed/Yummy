package eg.gov.iti.yummy.model;

import java.util.ArrayList;

public class RootMealDetail {
    public ArrayList<MealDetail> meals;

    public void setMeals(ArrayList<MealDetail> meals) {
        this.meals = meals;
    }

    public ArrayList<MealDetail> getMeals() {

        return meals;
    }
}
