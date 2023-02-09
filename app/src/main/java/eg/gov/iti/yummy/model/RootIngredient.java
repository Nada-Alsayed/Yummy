package eg.gov.iti.yummy.model;

import java.util.ArrayList;

public class RootIngredient {
    public ArrayList<Ingredient> meals;

    public ArrayList<Ingredient> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Ingredient> meals) {
        this.meals = meals;
    }

    public RootIngredient(ArrayList<Ingredient> meals) {
        this.meals = meals;
    }
}
