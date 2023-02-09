package eg.gov.iti.yummy.network;

public interface RemoteSource {
    void randomMealForYou(NetworkDelegate networkDelegate);
    void randomMealTrending(NetworkDelegate networkDelegate);
    void randomMealNewDishes(NetworkDelegate networkDelegate);

}
