package eg.gov.iti.yummy.network;

import java.util.ArrayList;

import eg.gov.iti.yummy.model.RootCategory;
import eg.gov.iti.yummy.model.RootCountry;
import eg.gov.iti.yummy.model.RootIngredient;
import eg.gov.iti.yummy.model.RootMealDetail;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Service {
    @GET("categories.php")
    Observable<RootCategory> getCategories();

    @GET("filter.php")
    Observable<RootMealDetail> getMealByIngredient(@Query("i") String ingredient);

    @GET("filter.php")
    Observable<RootMealDetail> getMealByCategory(@Query("c") String category);

    @GET("filter.php")
    Observable<RootMealDetail> getMealByCountry(@Query("a") String country);

    @GET("search.php")
    Observable<RootMealDetail> getSpecificMeal(@Query("s") String meal);

    @GET("random.php")
    Observable<RootMealDetail> getRandomMeal();

    @GET("list.php?i=list")
    Observable<RootIngredient> getAllIngredients();

    @GET("list.php?a=list")
    Observable<RootCountry> getAllCountries();


}
