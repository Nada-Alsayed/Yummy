package eg.gov.iti.yummy.weeklyPlan.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.db.ConcreteLocalSource;
import eg.gov.iti.yummy.db.UserEntity;
import eg.gov.iti.yummy.favourite.view.Page_Favourite;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenter;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenterInterface;
import eg.gov.iti.yummy.meal_details.view.MealViewInterface;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.network.API_Client;
import eg.gov.iti.yummy.weeklyPlan.view.WeeklyPlanAdapter;
import eg.gov.iti.yummy.weeklyPlan.view.planListItem;

public class Page_Week_Plan extends Fragment implements MealViewInterface {
    RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5,recyclerView6,recyclerView7;
    List<MealDetail> input1,input2,input3,input4,input5,input6,input7;
    WeeklyPlanAdapter adapter;

    int day=1;

    long threadSat,threadSun;

    String[] sats,suns,mons,tues,weds,thus,fris;

    String sat,sun,mon,tue,wed,thu,fri;

    ConcreteLocalSource cls;

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
        recyclerView1 = view.findViewById(R.id.recycle1);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        input1 = new ArrayList<>();
        adapter = new WeeklyPlanAdapter(input1,getContext());
        recyclerView1.setAdapter(adapter);


        recyclerView2 = view.findViewById(R.id.recycle2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager2);
        input2 = new ArrayList<>();
        adapter = new WeeklyPlanAdapter(input2,getContext());
        recyclerView2.setAdapter(adapter);

        recyclerView3 = view.findViewById(R.id.recycle3);
        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView3.setLayoutManager(layoutManager3);
        input3=new ArrayList<>();
        adapter = new WeeklyPlanAdapter(input3,getContext());
        recyclerView3.setAdapter(adapter);

        recyclerView4 = view.findViewById(R.id.recycle4);
        recyclerView4.setHasFixedSize(true);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext());
        layoutManager4.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView4.setLayoutManager(layoutManager4);
        input4 = new ArrayList<>();
        adapter = new WeeklyPlanAdapter(input4,getContext());
        recyclerView4.setAdapter(adapter);

        recyclerView5 = view.findViewById(R.id.recycle5);
        recyclerView5.setHasFixedSize(true);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getContext());
        layoutManager5.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView5.setLayoutManager(layoutManager5);
        input5 = new ArrayList<>();
        adapter = new WeeklyPlanAdapter(input5,getContext());
        recyclerView5.setAdapter(adapter);

        recyclerView6 = view.findViewById(R.id.recycle6);
        recyclerView6.setHasFixedSize(true);
        LinearLayoutManager layoutManager6 = new LinearLayoutManager(getContext());
        layoutManager6.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView6.setLayoutManager(layoutManager6);
        input6 = new ArrayList<>();
        adapter = new WeeklyPlanAdapter(input6,getContext());
        recyclerView6.setAdapter(adapter);

        recyclerView7 = view.findViewById(R.id.recycle7);
        recyclerView7.setHasFixedSize(true);
        LinearLayoutManager layoutManager7 = new LinearLayoutManager(getContext());
        layoutManager7.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView7.setLayoutManager(layoutManager7);
        input7 = new ArrayList<>();
        adapter = new WeeklyPlanAdapter(input7,getContext());
        recyclerView7.setAdapter(adapter);

        cls = ConcreteLocalSource.getInstance(getContext());
        SharedPreferences pref = getActivity().getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        String shP = pref.getString("USERNAME", "N/A");
        mealPresenterInterface = new MealPresenter(Repository.getInstance(API_Client.getInstance(getContext()), getContext()), Page_Week_Plan.this);
        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if(day==1) {
                    sat = userEntity.getSaturday();
                    if (sat != null) {
                        sats = sat.split(",");
                        for (int i = 0; i < sats.length; i++) {
                            mealPresenterInterface.getSpecificMeal(sats[i]);
                            threadSat = Thread.currentThread().getId();
                            if(i==sats.length-1)day++;
                        }
                    }
                }
            }
        });
        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if(day==2) {
                    sun = userEntity.getSunday();
                    if (sun != null) {
                        suns = sun.split(",");
                        for (int i = 0; i < suns.length; i++) {
                            mealPresenterInterface.getSpecificMeal(suns[i]);
                            threadSun = Thread.currentThread().getId();
                            if(i==suns.length-1)day++;
                        }
                    }
                }
            }
        });

        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if(day==3) {
                    mon = userEntity.getMonday();
                    if (mon != null) {
                        mons = mon.split(",");
                        for (int i = 0; i < mons.length; i++) {
                            mealPresenterInterface.getSpecificMeal(mons[i]);
                            if(i==mons.length-1)day++;
                        }
                    }
                }
            }
        });

        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if(day==4) {
                    tue = userEntity.getTuesday();
                    if (tue != null) {
                        tues = tue.split(",");
                        for (int i = 0; i < tues.length; i++) {
                            mealPresenterInterface.getSpecificMeal(tues[i]);
                            if(i==tues.length-1)day++;
                        }
                    }
                }
            }
        });

        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if(day==5) {
                    wed = userEntity.getWednesday();
                    if (wed != null) {
                        weds = wed.split(",");
                        for (int i = 0; i < weds.length; i++) {
                            mealPresenterInterface.getSpecificMeal(weds[i]);
                            if(i==weds.length-1)day++;
                        }
                    }
                }
            }
        });

        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if(day==6) {
                    thu = userEntity.getThursday();
                    if (thu != null) {
                        thus = thu.split(",");
                        for (int i = 0; i < thus.length; i++) {
                            mealPresenterInterface.getSpecificMeal(thus[i]);
                            if(i==thus.length-1)day++;
                        }
                    }
                }
            }
        });
        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if(day==7) {
                    fri = userEntity.getFriday();
                    if (fri != null) {
                        fris = fri.split(",");
                        for (int i = 0; i < fris.length; i++) {
                            mealPresenterInterface.getSpecificMeal(fris[i]);
                            if(i==fris.length-1)day++;
                        }
                    }

                }
            }
        });
    }
    @Override
    public void showSpecificItem(RootMealDetail meals) {
        System.out.println(Thread.currentThread().getId()+"============================================================="+threadSat);
        if(threadSat==Thread.currentThread().getId()){
            input1.add(meals.getMeals().get(0));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++");
            if (sats.length == input1.size()) {
                adapter.setList(input1);
                recyclerView1.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
        if(threadSun==Thread.currentThread().getId()){
            input2.add(meals.getMeals().get(0));
            if (suns!=null&&suns.length == input2.size()) {
                adapter.setList(input2);
                recyclerView2.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    //    if(day==3){
            input3.add(meals.getMeals().get(0));
            if (mons!=null&&mons.length == input3.size()) {
                adapter.setList(input3);
                recyclerView3.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
      //  }
     //   if(day==4){
            input4.add(meals.getMeals().get(0));
            if (tues!=null&&tues.length == input4.size()) {
                adapter.setList(input4);
                recyclerView4.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
      //  }
       // if(day==5){
            input5.add(meals.getMeals().get(0));
            if (weds!=null&&weds.length == input5.size()) {
                adapter.setList(input5);
                recyclerView5.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
     //   }
     //   if(day==6){
            input6.add(meals.getMeals().get(0));
            if (thus!=null&&thus.length == input6.size()) {
                adapter.setList(input6);
                recyclerView6.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
       // }
      //  if(day==7){
            input7.add(meals.getMeals().get(0));
            if (fris!=null&&fris.length == input7.size()) {
                adapter.setList(input7);
                recyclerView7.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        //}
    }
}