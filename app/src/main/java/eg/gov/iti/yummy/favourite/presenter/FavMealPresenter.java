package eg.gov.iti.yummy.favourite.presenter;

import java.util.List;

import eg.gov.iti.yummy.favourite.view.FavViewInterface;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import io.reactivex.rxjava3.core.Observable;

public class FavMealPresenter implements FavMealPresenterInterface{
    private Repository repository;
    private FavViewInterface viewInterface;

    public FavMealPresenter(FavViewInterface viewInterface, Repository repository) {
        this.viewInterface = viewInterface;
        this.repository = repository;
    }

    @Override
    public Observable<List<MealDetail>> getStoredMeals() {
        return repository.getStoredMeals();
    }

    @Override
    public void deleteMeal(MealDetail meal) {
        repository.deleteMeal(meal);
    }


    @Override
    public void insertMeal(MealDetail meal) {
        repository.insertMeal(meal);
    }

}
