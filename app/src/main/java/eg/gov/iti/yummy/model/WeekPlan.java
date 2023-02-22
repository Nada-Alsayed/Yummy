package eg.gov.iti.yummy.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "WeekPlan")
public class WeekPlan {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    public Integer id;
    @ColumnInfo(defaultValue = "")
    public String sat;
    @ColumnInfo(defaultValue = "")
    public String sun;
    @ColumnInfo(defaultValue = "")
    public String mon;
    @ColumnInfo(defaultValue = "")
    public String thurs;
    @ColumnInfo(defaultValue = "")
    public String wed;
    @ColumnInfo(defaultValue = "")
    public String tues;
    @ColumnInfo(defaultValue = "")
    public String fri;

    public String idMeal;
    public String strMeal;
    @Ignore
    public Object strDrinkAlternate;
    public String strCategory;
    public String strArea;
    public String strInstructions;
    public String strMealThumb;
    public String strTags;
    public String strYoutube;
    @ColumnInfo(defaultValue = "")
    public String strIngredient1;
    @ColumnInfo(defaultValue = "")
    public String strIngredient2;
    @ColumnInfo(defaultValue = "")
    public String strIngredient3;
    @ColumnInfo(defaultValue = "")
    public String strIngredient4;
    @ColumnInfo(defaultValue = "")
    public String strIngredient5;
    @ColumnInfo(defaultValue = "")
    public String strIngredient6;
    @ColumnInfo(defaultValue = "")
    public String strIngredient7;
    @ColumnInfo(defaultValue = "")
    public String strIngredient8;
    @ColumnInfo(defaultValue = "")
    public String strIngredient9;
    @ColumnInfo(defaultValue = "")
    public String strIngredient10;
    @ColumnInfo(defaultValue = "")
    public String strIngredient11;
    @ColumnInfo(defaultValue = "")
    public String strIngredient12;
    @ColumnInfo(defaultValue = "")
    public String strIngredient13;
    @ColumnInfo(defaultValue = "")
    public String strIngredient14;
    @ColumnInfo(defaultValue = "")
    public String strIngredient15;
    @ColumnInfo(defaultValue = "")
    public String strIngredient16;
    @ColumnInfo(defaultValue = "")
    public String strIngredient17;
    @ColumnInfo(defaultValue = "")
    public String strIngredient18;
    @ColumnInfo(defaultValue = "")
    public String strIngredient19;
    @ColumnInfo(defaultValue = "")
    public String strIngredient20;
    public String strMeasure1;
    public String strMeasure2;
    public String strMeasure3;
    public String strMeasure4;
    public String strMeasure5;
    public String strMeasure6;
    public String strMeasure7;
    public String strMeasure8;
    public String strMeasure9;
    public String strMeasure10;
    public String strMeasure11;
    public String strMeasure12;
    public String strMeasure13;
    public String strMeasure14;
    public String strMeasure15;
    public String strMeasure16;
    public String strMeasure17;
    public String strMeasure18;
    public String strMeasure19;
    public String strMeasure20;
    public String strSource;
    public String Fav;
    @Ignore
    public Object strImageSource;
    @Ignore
    public Object strCreativeCommonsConfirmed;
    @Ignore
    public Object dateModified;

    public WeekPlan(String idMeal, String strMeal, String strCategory, String strArea, String strInstructions, String strMealThumb,
                    String strIngredient1, String strIngredient2, String strIngredient3, String strIngredient4,
                    String strIngredient5, String strIngredient6, String strIngredient7, String strIngredient8,
                    String strIngredient9, String strIngredient10, String strIngredient11,
                    String strIngredient12,
                    String strIngredient13,
                    String strIngredient14,
                    String strIngredient15,
                    String strIngredient16,
                    String strIngredient17,
                    String strIngredient18,
                    String strIngredient19,
                    String strIngredient20) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.strIngredient12 = strIngredient12;
        this.strIngredient13 = strIngredient13;
        this.strIngredient14 = strIngredient14;
        this.strIngredient15 = strIngredient15;
        this.strIngredient16 = strIngredient16;
        this.strIngredient17 = strIngredient17;
        this.strIngredient18 = strIngredient18;
        this.strIngredient19 = strIngredient19;
        this.strIngredient20 = strIngredient20;
    }
}
