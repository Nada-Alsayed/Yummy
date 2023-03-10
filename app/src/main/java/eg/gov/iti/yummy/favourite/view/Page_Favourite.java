package eg.gov.iti.yummy.favourite.view;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.db.ConcreteLocalSource;
import eg.gov.iti.yummy.favourite.presenter.FavMealPresenter;
import eg.gov.iti.yummy.favourite.presenter.FavMealPresenterInterface;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.network.API_Client;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Page_Favourite extends Fragment implements FavViewInterface, onFavouriteClickListener {
    RecyclerView recyclerView;
    FavMealPresenterInterface favMealPresenterInterface;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://yummy-app-f2567-default-rtdb.firebaseio.com/");

    AdapterFavList adapterFavList;
    LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page__favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favMealPresenterInterface = new FavMealPresenter(Page_Favourite.this, Repository.getInstance(API_Client.getInstance(getContext()), ConcreteLocalSource.getInstance(getContext()), getContext()));
        SharedPreferences pref = getActivity().getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        String shP = pref.getString("USERNAME", "N/A");
        databaseReference.child(shP).child("Favourite").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for(DataSnapshot Data:snapshot.getChildren()){
                        MealDetail mealDetail=Data.getValue(MealDetail.class);
                        Log.e(TAG,"firebase");
                        favMealPresenterInterface.insertMeal(mealDetail);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView=view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterFavList = new AdapterFavList(new ArrayList<>(), this, getContext());


        //recyclerView.setAdapter(adapterFavList);

        favMealPresenterInterface.getStoredMeals().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<MealDetail>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("LO", "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                    }

                    @Override
                    public void onNext(@NonNull List<MealDetail> mealDetails) {
                        adapterFavList.setList(mealDetails);
                        adapterFavList.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("LO", "888888888888888888888888888888888888888888888888888888");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("LO", "7777777777777777777777777777777777777777777777");
                    }
                });
        recyclerView.setAdapter(adapterFavList);

    }

    @Override
    public void showStoredData(List<MealDetail> meals) {
        adapterFavList.setList(meals);
    }

    @Override
    public void deleteProduct(MealDetail meal) {

        favMealPresenterInterface.deleteMeal(meal);
    }

    @Override
    public void OnClick(MealDetail meal) {
        deleteProduct(meal);
    }

}