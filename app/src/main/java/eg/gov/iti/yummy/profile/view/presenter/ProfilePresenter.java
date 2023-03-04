package eg.gov.iti.yummy.profile.view.presenter;

import eg.gov.iti.yummy.model.Repository;

public class ProfilePresenter implements ProfilePresenterInterface {
    private Repository repository;

    public ProfilePresenter(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteMeals() {
        repository.deleteMeals();
    }
    @Override
    public void deletePlan() {
        repository.deletePlan();
    }
}
