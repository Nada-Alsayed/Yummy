package eg.gov.iti.yummy.model;

import java.util.ArrayList;

public class RootCountry {
    public ArrayList<Country> meals;

    public void setCountries(ArrayList<Country> countries) {
        this.meals = countries;
    }

    public ArrayList<Country> getCountries() {
        return meals;
    }
}
