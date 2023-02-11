package eg.gov.iti.yummy.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eg.gov.iti.yummy.db.ConcreteLocalSource;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConctreteRemoteSource {
//    private static final String Base_Url = "https://www.themealdb.com/api/json/v1/1/";
//    private static Retrofit retrofit = null;
//    private static Gson gson = null;
//    private static ConcreteLocalSource ConctreteRemoteSource=null;
//
//    public static ConcreteLocalSource getInstance(Context context){
//        if(ConctreteRemoteSource==null){
//            ConctreteRemoteSource=new ConcreteLocalSource(context);
//        }
//        return ConctreteRemoteSource;
//    }

//    public ConctreteRemoteSource(Context context) {
//        gson = new GsonBuilder().create();
//        Cache cache=new Cache(context.getCacheDir(),10*1024*1024);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .cache(cache)
//            .build();
//        retrofit = new Retrofit.Builder()
//                .baseUrl(Base_Url)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//                .build();
//    }
    public OkHttpClient fun(Context context){
        Cache cache=new Cache(context.getCacheDir(),10*1024*1024);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        return okHttpClient;
    }

}
