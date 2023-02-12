package eg.gov.iti.yummy.weeklyPlan.view.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.db.ConcreteLocalSource;
import eg.gov.iti.yummy.db.UserEntity;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenter;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenterInterface;
import eg.gov.iti.yummy.meal_details.view.MealViewInterface;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.network.API_Client;

public class Page_Week_Plan extends Fragment implements MealViewInterface {
    RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5, recyclerView6, recyclerView7;
    List<planListItem> input, input2, input3, input4, input5, input6, input7;
    WeeklyPlanAdapter adapter1;
    ConcreteLocalSource cls;
    MealDetail mealDetail;
    planListItem planListItem;

    Integer Day = 1;
    String fav1, fav2, fav3, fav4, fav5, fav6, fav7;
    String[] favs1, favs2, favs3, favs4, favs5, favs6, favs7;
    MealPresenterInterface mealPresenterInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page__week__plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cls = ConcreteLocalSource.getInstance(getContext());

        SharedPreferences pref = getActivity().getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        String shP = pref.getString("USERNAME", "N/A");

        recyclerView1 = view.findViewById(R.id.recycle1);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        input = new ArrayList<>();
        adapter1 = new WeeklyPlanAdapter(getContext(), input);

        recyclerView1.setAdapter(adapter1);
        mealPresenterInterface = new MealPresenter(Repository.getInstance(API_Client.getInstance(getContext()), getContext()), Page_Week_Plan.this);
        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                fav1 = userEntity.getSaturday();
                if (fav1 != null) {
                    favs1 = fav1.split(",");
                    for (int i = 0; i < favs1.length; i++) {
                        mealPresenterInterface.getSpecificMeal(favs1[i]);
                    }
                }
            }
        });

        recyclerView2 = view.findViewById(R.id.recycle2);
        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager layoutManager2= new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager2);
        input2 = new ArrayList<>();
        recyclerView2.setAdapter(adapter1);

        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                fav2 = userEntity.getSunday();
                if (fav2 != null) {
                    favs2 = fav2.split(",");
                    for (int i = 0; i < favs2.length; i++) {
                        mealPresenterInterface.getSpecificMeal(favs2[i]);
                    }
                }
            }
        });

        recyclerView3 = view.findViewById(R.id.recycle3);
        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView3.setLayoutManager(layoutManager3);
        input3 = new ArrayList<>();
       // adapter1 = new WeeklyPlanAdapter(input3, getContext());
        recyclerView3.setAdapter(adapter1);
        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                fav3 = userEntity.getMonday();
                if (fav3 != null) {
                    favs3 = fav3.split(",");
                    for (int i = 0; i < favs3.length; i++) {
                        mealPresenterInterface.getSpecificMeal(favs3[i]);
                    }
                }
            }
        });
//
        recyclerView4 = view.findViewById(R.id.recycle4);
        recyclerView4.setHasFixedSize(true);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext());
        layoutManager4.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView4.setLayoutManager(layoutManager4);
        input4 = new ArrayList<>();

        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                fav4 = userEntity.getMonday();
                if (fav4 != null) {
                    favs4 = fav4.split(",");
                    for (int i = 0; i < favs4.length; i++) {
                        mealPresenterInterface.getSpecificMeal(favs4[i]);
                    }
                }
            }
        });

//        adapter = new WeeklyPlanAdapter(input4, getContext());
//        recyclerView4.setAdapter(adapter);

        recyclerView5 = view.findViewById(R.id.recycle5);
        recyclerView5.setHasFixedSize(true);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getContext());
        layoutManager5.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView5.setLayoutManager(layoutManager5);
        input5 = new ArrayList<>();
        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                fav5 = userEntity.getMonday();
                if (fav5 != null) {
                    favs5 = fav5.split(",");
                    for (int i = 0; i < favs5.length; i++) {
                        mealPresenterInterface.getSpecificMeal(favs5[i]);
                    }
                }
            }
        });
//        input5.add(mealDetail);
//        adapter = new WeeklyPlanAdapter(input5, getContext());
//        recyclerView5.setAdapter(adapter);
////
        recyclerView6 = view.findViewById(R.id.recycle6);
        recyclerView6.setHasFixedSize(true);
        LinearLayoutManager layoutManager6 = new LinearLayoutManager(getContext());
        layoutManager6.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView6.setLayoutManager(layoutManager6);
        input6 = new ArrayList<>();
        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                fav6 = userEntity.getMonday();
                if (fav6 != null) {
                    favs6 = fav6.split(",");
                    for (int i = 0; i < favs6.length; i++) {
                        mealPresenterInterface.getSpecificMeal(favs6[i]);
                    }
                }
            }
        });
//        adapter = new WeeklyPlanAdapter(input6, getContext());
//        recyclerView6.setAdapter(adapter);
//
        recyclerView7 = view.findViewById(R.id.recycle7);
        recyclerView7.setHasFixedSize(true);
        LinearLayoutManager layoutManager7 = new LinearLayoutManager(getContext());
        layoutManager7.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView7.setLayoutManager(layoutManager7);
        input7 = new ArrayList<>();

        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                fav7 = userEntity.getMonday();
                if (fav7 != null) {
                    favs7 = fav7.split(",");
                    for (int i = 0; i < favs7.length; i++) {
                        mealPresenterInterface.getSpecificMeal(favs7[i]);
                    }
                }
            }
        });
//
//        adapter = new WeeklyPlanAdapter(input7, getContext());
//        recyclerView7.setAdapter(adapter);
    }

    @Override
    public void showSpecificItem(RootMealDetail meals) {
        planListItem = new planListItem();
        planListItem.setTitle(meals.getMeals().get(0).strMeal);
        planListItem.setImageID(meals.getMeals().get(0).strMealThumb);
        input.add(planListItem);
        if (favs1.length == input.size()) {
            adapter1.setListData(input);
            recyclerView1.setAdapter(adapter1);
            adapter1.notifyDataSetChanged();
        }
    }


}