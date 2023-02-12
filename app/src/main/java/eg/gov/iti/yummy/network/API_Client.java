package eg.gov.iti.yummy.network;

import android.content.Context;
import android.net.ConnectivityManager;
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
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    public static API_Client getInstance(Context context) {
        if (APIClient == null) {
            APIClient = new API_Client();
            gson = new GsonBuilder().create();
            //source=new ConctreteRemoteSource();
            Cache cache=new Cache(context.getCacheDir(),1000*1024*1024);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
            Log.e(TAG, "startCall: ++++++++++++++++++++++++++++++++++++++++++");
        }
        return APIClient;
    }

//    //++++++++++++++++++++++++++++++++++++++++++++++++++++++
//
//    public static Cache cache=new Cache(context.getCacheDir(),10*1024*1024);
//
//    //check if device connected with network
//    public static boolean isNetworkAvailable(Context context) {
//        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
//        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
//    }
//
//    public static Interceptor onlineInterceptor=new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            okhttp3.Response response = chain.proceed(chain.request());
//            int maxAge = 60; // read from cache for 60 seconds even if there is internet connection
//            return response.newBuilder()
//                    .header("Cache-Control", "public, max-age=" + maxAge)
//                    .removeHeader("Pragma")
//                    .build();
//        }
//    };
//
//    static Interceptor offlineInterceptor= new Interceptor() {
//        @Override
//        public okhttp3.Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            if (!isNetworkAvailable(context)) {
//                int maxStale = 60 * 60 * 24 * 30; // Offline cache available for 30 days
//                request = request.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                        .removeHeader("Pragma")
//                        .build();
//            }
//            return chain.proceed(request);
//        }
//    };
//
//    static public OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            // .addInterceptor(provideHttpLoggingInterceptor()) // For HTTP request & Response data logging
//            .addInterceptor(offlineInterceptor)
//            .addNetworkInterceptor(onlineInterceptor)
//            .cache(cache)
//            .build();
//    //++++++++++++++++++++++++++++++++++++++++++++++++++++++




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

    @Override
    public void specificItem(DetailsNetworkDelegate detailsNetworkDelegate, String meal) {
        APIService = retrofit.create(API_Service.class);
        Log.i(TAG, "specificItem: "+meal);
        Observable<RootMealDetail> rootSingle = APIService.getSpecificMeal(meal);
        rootSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->{
                            Log.e(TAG, "ingredient"+item.getMeals().size());
                            detailsNetworkDelegate.onSuccessFindingMeal(item);
                        },
                        error -> error.printStackTrace()
                );
    }


}
