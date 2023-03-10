package eg.gov.iti.yummy.home.home.view;

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
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.yummy.DialogLogin;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.db.ConcreteLocalSource;
import eg.gov.iti.yummy.home.home.presenter.HomePresenter;
import eg.gov.iti.yummy.home.home.presenter.HomePresenterInterface;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenterInterface;
import eg.gov.iti.yummy.meal_details.view.OnClick;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.network.API_Client;
import eg.gov.iti.yummy.weeklyPlan.view.view.WeeklyPlanAdapter;

public class Page_Home extends Fragment implements HomeViewInterface,HomeOnClick {
    ViewPager viewPager;
    String shP;
    public static final String TAG="pk";
    ForYouAdapter forYouAdapter;
    HomePresenterInterface PresenterInterface;

    RecyclerView recyclerView, recyclerView1;

    HomeAdaptor weeklyPlanAdapter,weeklyPlanAdapter1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page__home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.stack_view);
        forYouAdapter = new ForYouAdapter(new ArrayList<>(), getContext());
        PresenterInterface = new HomePresenter(Repository.getInstance(API_Client.getInstance(getContext()),ConcreteLocalSource.getInstance(getContext()),getContext()), this);
        viewPager.setPageTransformer(true, new ViewPagerStack());
        viewPager.setOffscreenPageLimit(3);
        //viewPager.setAdapter(forYouAdapter);
        PresenterInterface.getRandomMealsForYou();

       SharedPreferences pref = getContext().getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        shP = pref.getString("USERNAME", "N/A");

        recyclerView = view.findViewById(R.id.myRecView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        weeklyPlanAdapter = new HomeAdaptor(new ArrayList<>(),this,getContext());
        recyclerView.setAdapter(weeklyPlanAdapter);
        PresenterInterface.getRandomMealsTrending();

        recyclerView1 = view.findViewById(R.id.myRecView1);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        weeklyPlanAdapter1 = new HomeAdaptor(new ArrayList<>(),this,getContext());
        recyclerView1.setAdapter(weeklyPlanAdapter1);
        PresenterInterface.getRandomMealsNewDishes();
    }


    @Override
    public void addMealInFirebase(MealDetail mealDetail, String key) {
        PresenterInterface.addMealToFavFirebase(mealDetail,key);
    }

    @Override
    public void addMealToFavHome(MealDetail meal) {
        PresenterInterface.addToFavHome(meal);
    }

    @Override
    public void showDataForYou(List<MealDetail> Categories) {
        forYouAdapter.setList(Categories);
        Log.e(TAG, "showDatafor: "+Categories.get(0).idMeal);
        viewPager.setAdapter(forYouAdapter);
        forYouAdapter.notifyDataSetChanged();
    }
    @Override
    public void showDataTrending(List<MealDetail> products) {
        weeklyPlanAdapter.setList(products);
        Log.e(TAG, "showDatatrending: "+products.get(0).idMeal);
        recyclerView.setAdapter(weeklyPlanAdapter);
        weeklyPlanAdapter.notifyDataSetChanged();
    }
    @Override
    public void showDataNewDishes(List<MealDetail> products2) {
        weeklyPlanAdapter1.setList(products2);
        Log.e(TAG, "showDatanew: "+products2.get(0).idMeal);
        recyclerView1.setAdapter(weeklyPlanAdapter1);
        weeklyPlanAdapter1.notifyDataSetChanged();
    }

    @Override
    public void addToFavHome(MealDetail mealDetail) {
        /*if(shP.equals("Guest"))
        {
            login();
        }*/
//        else
//        {
            addMealToFavHome(mealDetail);
//        }

    }
    @Override
    public void addToFavFireOnClick(MealDetail mealDetail) {
        addMealInFirebase(mealDetail,shP);

    }

    public class ViewPagerStack implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            if (position >= 0) {
                page.setScaleX(.9f - .05f * position);
                page.setScaleY(.9f);
                page.setTranslationX(-page.getWidth() * position);
                page.setTranslationY(-20 * position);
            }
        }
    }
    void login() {
        DialogLogin dialogLogin = new DialogLogin();
        dialogLogin.show(getFragmentManager(), "Test");
    }
}

