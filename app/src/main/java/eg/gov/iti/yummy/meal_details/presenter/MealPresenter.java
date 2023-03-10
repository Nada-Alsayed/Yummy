package eg.gov.iti.yummy.meal_details.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import eg.gov.iti.yummy.meal_details.view.MealViewInterface;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.model.WeekPlan;
import eg.gov.iti.yummy.network.DetailsNetworkDelegate;
import io.reactivex.rxjava3.core.Observable;

public class MealPresenter implements MealPresenterInterface, DetailsNetworkDelegate {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://yummy-app-f2567-default-rtdb.firebaseio.com/");
    private Repository repository;
    private MealViewInterface mealViewInterface;

    public MealPresenter() {
    }

    public MealPresenter(Repository repository, MealViewInterface mealViewInterface) {
        this.repository = repository;
        this.mealViewInterface = mealViewInterface;
    }

    @Override
    public void updateSat(String x,String id) {
        repository.updateSat(x,id);
    }

    @Override
    public void updateSun(String x,String id) {
        repository.updateSun(x,id);
    }

    @Override
    public void updateMon(String x,String id) {
        repository.updateMon(x,id);
    }

    @Override
    public void updateTues(String x ,String id) {
        repository.updateTues(x,id);
    }

    @Override
    public void updateWed(String x,String id) {
        repository.updateWed(x,id);
    }

    @Override
    public void updateThurs(String x,String id) {
        repository.updateThurs(x,id);
    }

    @Override
    public void updateFri(String x,String id) {
        repository.updateFri(x,id);
    }

    @Override
    public void addToFav(MealDetail meal) {
        repository.insertMeal(meal);
    }

    @Override
    public void addMealToFavFirebase(MealDetail meal, String key) {
        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(key)) {
                    databaseReference.child(key).child("Favourite").child(meal.idMeal).setValue(meal);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    final int[] i = {0};
    @Override
    public void addMealToWeekPlanFirebase(WeekPlan meal, String key) {
        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(key)) {
                    databaseReference.child(key).child("WeekPlan").child(meal.idMeal+ i[0]++).setValue(meal);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void getSpecificMeal(String meal) {
        repository.getMealFromRetrofit(this, meal);
    }

    @Override
    public void addToWeekPlan(WeekPlan meal) {
        repository.insertMealIntoWeek(meal);
    }


    @Override
    public Observable<MealDetail> getOffMeal(String meal) {
        Log.e("TAG", "getOffMeal: meal presenter");
        //repository.getOfflineMeal(meal).observe(meal);
        return repository.getOfflineMeal(meal);

    }

    @Override
    public Observable<WeekPlan> getOffMealWeek(String meal) {
        Log.e("TAG", "getOffMeal: meal presenter");
        //repository.getOfflineMeal(meal).observe(meal);
        return repository.getOfflineMealWeek(meal);

    }

    @Override
    public void onSuccessFindingMeal(RootMealDetail meal) {
        mealViewInterface.showSpecificItem(meal);
    }
}
