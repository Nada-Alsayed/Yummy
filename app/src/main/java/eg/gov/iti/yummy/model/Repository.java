package eg.gov.iti.yummy.model;

import android.content.Context;

import eg.gov.iti.yummy.network.NetworkDelegate;
import eg.gov.iti.yummy.network.RemoteSource;

public class Repository implements RepositoryInterface{
    Context context;
    RemoteSource remoteSource;
    private static Repository repository = null;

    public Repository(RemoteSource remoteSource, Context context) {
        this.context=context;
        this.remoteSource=remoteSource;
    }

    public Repository(Context context) {
        this.context = context;
    }

    public static Repository getInstance(RemoteSource remoteSource, Context context)
    {
        if (repository == null) {
            repository = new Repository(remoteSource, context);
        }
        return repository;
    }

    @Override
    public void getForYouMeals(NetworkDelegate networkDelegate) {
        remoteSource.randomMealForYou(networkDelegate);
    }

    @Override
    public void getTrendingMeals(NetworkDelegate networkDelegate) {
        remoteSource.randomMealTrending(networkDelegate);
    }

    @Override
    public void getNewDishesMeals(NetworkDelegate networkDelegate) {
        remoteSource.randomMealNewDishes(networkDelegate);
    }

}
