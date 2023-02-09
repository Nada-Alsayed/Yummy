package eg.gov.iti.yummy.model;

import java.util.ArrayList;

public class RootCountry {
    public ArrayList<Country> countries;

    public RootCountry() {

    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }
}
