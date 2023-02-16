package eg.gov.iti.yummy.meal_details.view;

public class WeekMeals {
    private String sun;
    private String mon;
    private String thurs;
    private String wed;
    private String tues;
    private String fri;
    private String sat;

//    public WeekMeals() {
//
//    }

    public WeekMeals() {
        this.sun = "0";
        this.mon = "0";
        this.thurs =  "0";
        this.wed =  "0";
        this.tues =  "0";
        this.fri =  "0";
        this.sat =  "0";
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public void setThurs(String thurs) {
        this.thurs = thurs;
    }

    public void setWed(String wed) {
        this.wed = wed;
    }

    public void setTues(String tues) {
        this.tues = tues;
    }

    public void setFri(String fri) {
        this.fri = fri;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getSun() {
        return sun;
    }

    public String getMon() {
        return mon;
    }

    public String getThurs() {
        return thurs;
    }

    public String getWed() {
        return wed;
    }

    public String getTues() {
        return tues;
    }

    public String getFri() {
        return fri;
    }

    public String getSat() {
        return sat;
    }
}
