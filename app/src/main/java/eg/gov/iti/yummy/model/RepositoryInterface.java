package eg.gov.iti.yummy.model;

import eg.gov.iti.yummy.network.NetworkDelegate;

public interface RepositoryInterface {
    public void getForYouMeals(NetworkDelegate networkDelegate);
    public void getTrendingMeals(NetworkDelegate networkDelegate);
    public void getNewDishesMeals(NetworkDelegate networkDelegate);

}
