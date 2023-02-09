package eg.gov.iti.yummy.network;

import eg.gov.iti.yummy.model.RootCategory;
import eg.gov.iti.yummy.model.RootMealDetail;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Service {
    @GET("categories.php")
    Observable<RootCategory> getAllCategories();

    @GET("list.php?i=list")
    Observable<RootCategory> getIngredients();

    @GET("list.php?a=list")
    Observable<RootCategory> getAllCountries();


    @GET("filter.php")
    Observable<RootMealDetail> getMealByIngredient(@Query("i") String ingredient);

    @GET("filter.php")
    Observable<RootMealDetail> getMealByCategory(@Query("c") String category);

    @GET("filter.php")
    Observable<RootMealDetail> getMealByCountry(@Query("a") String country);

    @GET("random.php")
    Observable<RootMealDetail> getRandomMeal();
}
