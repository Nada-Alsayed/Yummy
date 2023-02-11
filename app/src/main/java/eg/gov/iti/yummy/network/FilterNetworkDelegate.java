package eg.gov.iti.yummy.network;

import eg.gov.iti.yummy.model.RootMealDetail;

public interface FilterNetworkDelegate {

    public void onSuccessFilterByIngredient(RootMealDetail meals);
    public void onSuccessFilterByCategory(RootMealDetail meals);
    public void onSuccessFilterByCountry(RootMealDetail meals);

}
