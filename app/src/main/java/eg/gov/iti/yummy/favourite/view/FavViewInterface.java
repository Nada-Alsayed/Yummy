package eg.gov.iti.yummy.favourite.view;

import java.util.List;

import eg.gov.iti.yummy.model.MealDetail;

public interface FavViewInterface {
    public void showStoredData(List<MealDetail> meals);
    public void deleteProduct(MealDetail meal);
}
