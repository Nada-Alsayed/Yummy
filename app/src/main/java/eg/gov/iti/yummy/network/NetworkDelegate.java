package eg.gov.iti.yummy.network;

import java.util.ArrayList;

import eg.gov.iti.yummy.model.MealDetail;

public interface NetworkDelegate {
    void onFailureResult(String message);

    public void onSuccessForYou(ArrayList<MealDetail> randomListForYou);
    public void onSuccessTrending(ArrayList<MealDetail> randomListTrending);
    public void onSuccessNewDishes(ArrayList<MealDetail> randomListNewDishes);
}
