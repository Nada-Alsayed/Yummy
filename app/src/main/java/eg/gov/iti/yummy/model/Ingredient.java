package eg.gov.iti.yummy.model;

public class Ingredient {
    public String idIngredient;
    public String strIngredient;
    public String strDescription;
    public Object strType;

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public void setStrType(Object strType) {
        this.strType = strType;
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public Object getStrType() {
        return strType;
    }

    public Ingredient() {

    }

}