package eg.gov.iti.yummy.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    private String strArea;

    public Country(){}

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrArea() {
        return strArea;
    }
}
