package eg.gov.iti.yummy.network;

import eg.gov.iti.yummy.model.RootCountry;
import eg.gov.iti.yummy.model.RootMealDetail;

public interface DetailsNetworkDelegate {

    public void onSuccessFindingMeal(RootMealDetail meal);
}
