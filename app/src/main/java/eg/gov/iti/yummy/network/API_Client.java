package eg.gov.iti.yummy.network;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.RootCategory;
import eg.gov.iti.yummy.model.RootCountry;
import eg.gov.iti.yummy.model.RootIngredient;
import eg.gov.iti.yummy.model.RootMealDetail;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Client implements RemoteSource {

    private static final String Base_Url = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "API_Client";
    private static API_Client APIClient = null;
    private static Retrofit retrofit=null;
    private static Gson gson =null;
    API_Service APIService;

    public API_Client() {
    }

    public static API_Client getInstance() {
        if (APIClient == null) {
            APIClient = new API_Client();
            gson = new GsonBuilder().create();

             retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
            Log.e(TAG, "startCall: ++++++++++++++++++++++++++++++++++++++++++");
        }
        return APIClient;
    }

    @Override
    public void randomMealForYou(NetworkDelegate networkDelegate) {
        ArrayList<MealDetail> randomMeal2=new ArrayList<>();
        APIService = retrofit.create(API_Service.class);
        for(int i=0;i<8;i++) {
            Observable<RootMealDetail> rootSingle = APIService.getRandomMeal();
            rootSingle.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            item->{
                                randomMeal2.add(item.getMeals().get(0));
                                Log.e(TAG, "RandomMealForYou"+item.getMeals().size());
                                networkDelegate.onSuccessForYou(randomMeal2);
                            },
                            error -> error.printStackTrace()
                    );
        }

    }

    @Override
    public void randomMealTrending(NetworkDelegate networkDelegate) {
        ArrayList<MealDetail> randomMeal=new ArrayList<>();
        APIService = retrofit.create(API_Service.class);
        for(int i=0;i<10;i++) {
            Observable<RootMealDetail> rootSingle = APIService.getRandomMeal();
            rootSingle.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            item->{
                                randomMeal.add(item.getMeals().get(0));
                                Log.e(TAG, "RandomMealTrending"+item.getMeals().size());
                                networkDelegate.onSuccessTrending(randomMeal);
                            },
                            error -> error.printStackTrace()
                    );
        }
    }

    @Override
    public void randomMealNewDishes(NetworkDelegate networkDelegate) {
        ArrayList<MealDetail> randomMeal1=new ArrayList<>();
        APIService = retrofit.create(API_Service.class);
        for(int i=0;i<8;i++) {
            Observable<RootMealDetail> rootSingle = APIService.getRandomMeal();
            rootSingle.subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            item->{
                                randomMeal1.add(item.getMeals().get(0));
                                Log.e(TAG, "RandomMealNewDishes"+item.getMeals().size());
                                networkDelegate.onSuccessNewDishes(randomMeal1);
                            },
                            error -> error.printStackTrace()
                    );
        }
    }

    @Override
    public void allCategories(SearchNetworkDelegate searchNetworkDelegate) {
        APIService = retrofit.create(API_Service.class);

        Observable<RootCategory> rootSingle = APIService.getCategories();
        rootSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->{
                            Log.e(TAG, "category"+item.getCategories().size());
                            searchNetworkDelegate.onSuccessAllCategories(item);
                        },
                        error -> error.printStackTrace()
                );
    }

    @Override
    public void allCountries(SearchNetworkDelegate searchNetworkDelegate) {
        APIService = retrofit.create(API_Service.class);

        Observable<RootCountry> rootSingle = APIService.getAllCountries();
        rootSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->{
                            Log.e(TAG, "country"+item.getCountries().size());
                            searchNetworkDelegate.onSuccessAllCountries(item);
                        },
                        error -> error.printStackTrace()
                );
    }
    @Override
    public void allIngredients(SearchNetworkDelegate searchNetworkDelegate) {
        APIService = retrofit.create(API_Service.class);

        Observable<RootIngredient> rootSingle = APIService.getAllIngredients();
        rootSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->{
                            Log.e(TAG, "ingredient"+item.getMeals().size());
                            searchNetworkDelegate.onSuccessAllIngredients(item);
                        },
                        error -> error.printStackTrace()
                );
    }

    @Override
    public void filterByIngredient(FilterNetworkDelegate filterNetworkDelegate,String ingredient) {
        APIService = retrofit.create(API_Service.class);

        Observable<RootMealDetail> rootSingle = APIService.getMealByIngredient(ingredient);
        rootSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->{
                            Log.e(TAG, "ingredient"+item.getMeals().size());
                            filterNetworkDelegate.onSuccessFilterByIngredient(item);
                        },
                        error -> error.printStackTrace()
                );
    }

    @Override
    public void filterByCategory(FilterNetworkDelegate filterNetworkDelegate,String category) {
        APIService = retrofit.create(API_Service.class);

        Observable<RootMealDetail> rootSingle = APIService.getMealByCategory(category);
        rootSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->{
                            Log.e(TAG, "ingredient"+item.getMeals().size());
                            filterNetworkDelegate.onSuccessFilterByCategory(item);
                        },
                        error -> error.printStackTrace()
                );
    }

    @Override
    public void filterByCountry(FilterNetworkDelegate filterNetworkDelegate,String country) {
        APIService = retrofit.create(API_Service.class);

        Observable<RootMealDetail> rootSingle = APIService.getMealByCountry(country);
        rootSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->{
                            Log.e(TAG, "ingredient"+item.getMeals().size());
                            filterNetworkDelegate.onSuccessFilterByCountry(item);
                        },
                        error -> error.printStackTrace()
                );
    }

}
