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

import android.util.Log;
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

public class Page_Week_Plan extends Fragment implements MealViewInterface , onWeeklyPlanClickListener {
    RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5,recyclerView6,recyclerView7;
    List<MealDetail> input1,input2,input3,input4,input5,input6,input7;
    WeeklyPlanAdapter adapter1,adapter2,adapter3,adapter4,adapter5,adapter6,adapter7;

    int day=1;

    String[] sats,suns,mons,tues,weds,thus,fris;

    String sat,sun,mon,tue,wed,thu,fri;

    ConcreteLocalSource cls;

    String shP;

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
        adapter1 = new WeeklyPlanAdapter(getContext(),this,1,input1);
        recyclerView1.setAdapter(adapter1);


        recyclerView2 = view.findViewById(R.id.recycle2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager2);
        input2 = new ArrayList<>();
        adapter2 = new WeeklyPlanAdapter(getContext(),this,2,input2);
        recyclerView2.setAdapter(adapter2);

        recyclerView3 = view.findViewById(R.id.recycle3);
        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView3.setLayoutManager(layoutManager3);
        input3=new ArrayList<>();
        adapter3 = new WeeklyPlanAdapter(getContext(),this,3,input3);
        recyclerView3.setAdapter(adapter3);

        recyclerView4 = view.findViewById(R.id.recycle4);
        recyclerView4.setHasFixedSize(true);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext());
        layoutManager4.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView4.setLayoutManager(layoutManager4);
        input4 = new ArrayList<>();
        adapter4 = new WeeklyPlanAdapter(getContext(),this,4,input4);
        recyclerView4.setAdapter(adapter4);

        recyclerView5 = view.findViewById(R.id.recycle5);
        recyclerView5.setHasFixedSize(true);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getContext());
        layoutManager5.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView5.setLayoutManager(layoutManager5);
        input5 = new ArrayList<>();
        adapter5 = new WeeklyPlanAdapter(getContext(),this,5,input5);
        recyclerView5.setAdapter(adapter5);

        recyclerView6 = view.findViewById(R.id.recycle6);
        recyclerView6.setHasFixedSize(true);
        LinearLayoutManager layoutManager6 = new LinearLayoutManager(getContext());
        layoutManager6.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView6.setLayoutManager(layoutManager6);
        input6 = new ArrayList<>();
        adapter6 = new WeeklyPlanAdapter(getContext(),this,6,input6);
        recyclerView6.setAdapter(adapter6);

        recyclerView7 = view.findViewById(R.id.recycle7);
        recyclerView7.setHasFixedSize(true);
        LinearLayoutManager layoutManager7 = new LinearLayoutManager(getContext());
        layoutManager7.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView7.setLayoutManager(layoutManager7);
        input7 = new ArrayList<>();
        adapter7 = new WeeklyPlanAdapter(getContext(),this,7,input7);
        recyclerView7.setAdapter(adapter7);

        cls = ConcreteLocalSource.getInstance(getContext());
        SharedPreferences pref = getActivity().getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        shP = pref.getString("USERNAME", "N/A");
        mealPresenterInterface = new MealPresenter(Repository.getInstance(API_Client.getInstance(getContext()), getContext()), Page_Week_Plan.this);
        cls.getSaturdayFromDB(shP).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                sat = s;
                if (s != null) {
                    sats = s.split(",");
                    for (int i = 0; i < sats.length; i++) {
                        mealPresenterInterface.getSpecificMeal(sats[i]);
                    }
                }
            }
        });
        cls.getSundayFromDB(shP).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                sun = s;
                if(s!=null){
                    suns = s.split(",");
                    for(int i=0;i< suns.length;i++){
                        mealPresenterInterface.getSpecificMeal(suns[i]);
                    }
                }
            }
        });

        cls.getMondayFromDB(shP).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mon = s;
                if(s!=null){
                    mons = s.split(",");
                    for(int i=0;i< mons.length;i++){
                        mealPresenterInterface.getSpecificMeal(mons[i]);
                    }
                }
            }
        });

        cls.getTuesdayFromDB(shP).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tue = s;
                if(s!=null){
                    tues = s.split(",");
                    for(int i=0;i< tues.length;i++){
                        mealPresenterInterface.getSpecificMeal(tues[i]);
                    }
                }
            }
        });

        cls.getWednesdayFromDB(shP).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                wed = s;
                if(s!=null){
                    weds = s.split(",");
                    for(int i=0;i< weds.length;i++){
                        mealPresenterInterface.getSpecificMeal(weds[i]);
                    }
                }
            }
        });

        cls.getThursdayFromDB(shP).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                thu = s;
                if(s!=null){
                    thus = s.split(",");
                    for(int i=0;i< thus.length;i++){
                        mealPresenterInterface.getSpecificMeal(thus[i]);
                    }
                }
            }
        });

        cls.getFridayFromDB(shP).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                fri = s;
                if(s!=null){
                    fris = s.split(",");
                    for(int i=0;i< fris.length;i++){
                        mealPresenterInterface.getSpecificMeal(fris[i]);
                    }
                }
            }
        });
    }
    @Override
    public void showSpecificItem(RootMealDetail meals) {
        if(sat.contains(meals.getMeals().get(0).strMeal)){
            boolean flag = false;
            for(int i=0 ;i<input1.size();i++){
                if(meals.getMeals().get(0).strMeal.equals(input1.get(i).strMeal)){
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                input1.add(meals.getMeals().get(0));
            }
                adapter1.setList(input1);
                recyclerView1.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
        }
        if(sun.contains(meals.getMeals().get(0).strMeal)){
            boolean flag = false;
            for(int i=0 ;i<input2.size();i++){
                if(meals.getMeals().get(0).strMeal.equals(input2.get(i).strMeal)){
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                input2.add(meals.getMeals().get(0));
            }
                adapter2.setList(input2);
                recyclerView2.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
       }
        if(mon.contains(meals.getMeals().get(0).strMeal)){
            boolean flag = false;
            for(int i=0 ;i<input3.size();i++){
                if(meals.getMeals().get(0).strMeal.equals(input3.get(i).strMeal)){
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                input3.add(meals.getMeals().get(0));
            }
                adapter3.setList(input3);
                recyclerView3.setAdapter(adapter3);
                adapter3.notifyDataSetChanged();
        }
        if(tue.contains(meals.getMeals().get(0).strMeal)){
            boolean flag = false;
            for(int i=0 ;i<input4.size();i++){
                if(meals.getMeals().get(0).strMeal.equals(input4.get(i).strMeal)){
                    flag=true;
                    break;
                }
            }
            if(!flag){
                input4.add(meals.getMeals().get(0));
            }
                adapter4.setList(input4);
                recyclerView4.setAdapter(adapter4);
                adapter4.notifyDataSetChanged();
        }

        if(wed.contains(meals.getMeals().get(0).strMeal)){
            boolean flag = false;
            for(int i=0 ;i<input5.size();i++){
                if(meals.getMeals().get(0).strMeal.equals(input5.get(i).strMeal)){
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                input5.add(meals.getMeals().get(0));
            }
                adapter5.setList(input5);
                recyclerView5.setAdapter(adapter5);
                adapter5.notifyDataSetChanged();
        }

        if(thu.contains(meals.getMeals().get(0).strMeal)){
            boolean flag = false;
            for(int i=0 ;i<input6.size();i++){
                if(meals.getMeals().get(0).strMeal.equals(input6.get(i).strMeal)){
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                input6.add(meals.getMeals().get(0));
            }
                adapter6.setList(input6);
                recyclerView6.setAdapter(adapter6);
                adapter6.notifyDataSetChanged();
        }

        if(fri.contains(meals.getMeals().get(0).strMeal)){
            boolean flag = false;
            for(int i=0 ;i<input7.size();i++){
                if(meals.getMeals().get(0).strMeal.equals(input7.get(i).strMeal)){
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                input7.add(meals.getMeals().get(0));
            }
                adapter7.setList(input7);
                recyclerView7.setAdapter(adapter7);
                adapter7.notifyDataSetChanged();
        }
    }

    @Override
    public void OnClick(MealDetail Meal , int source) {
        if (source == 1) {
            String s = sat.replaceAll(Meal.strMeal + ",", "");
            input1.clear();
            if (s != null) {
                Arrays.fill(sats, null);
                sats = s.split(",");
                for (int i = 0; i < sats.length; i++) {
                    mealPresenterInterface.getSpecificMeal(sats[i]);
                }
                adapter1.notifyDataSetChanged();
                cls.updateSaturday(s, shP);
            }
        }

        else if (source == 2) {
            String s = sun.replaceAll(Meal.strMeal + ",", "");
            input2.clear();
            if (s != null) {
                Arrays.fill(suns, null);
                suns = s.split(",");
                for (int i = 0; i < suns.length; i++) {
                    mealPresenterInterface.getSpecificMeal(suns[i]);
                }
                adapter2.notifyDataSetChanged();
                cls.updateSunday(s, shP);
            }
        }

        else if (source == 3) {
            String s = mon.replaceAll(Meal.strMeal + ",", "");
            input3.clear();
            if (s != null) {
                Arrays.fill(mons, null);
                mons = s.split(",");
                for (int i = 0; i < mons.length; i++) {
                    mealPresenterInterface.getSpecificMeal(mons[i]);
                }
                adapter3.notifyDataSetChanged();
                cls.updateMonday(s, shP);
            }
        }

        else if (source == 4) {
            String s = tue.replaceAll(Meal.strMeal + ",", "");
            input4.clear();
            if (s != null) {
                Arrays.fill(tues, null);
                tues = s.split(",");
                for (int i = 0; i < tues.length; i++) {
                    mealPresenterInterface.getSpecificMeal(tues[i]);
                }
                adapter4.notifyDataSetChanged();
                cls.updateTuesday(s, shP);
            }
        }

        else if (source == 5) {
            String s = wed.replaceAll(Meal.strMeal + ",", "");
            input5.clear();
            if (s != null) {
                Arrays.fill(weds, null);
                weds = s.split(",");
                for (int i = 0; i < weds.length; i++) {
                    mealPresenterInterface.getSpecificMeal(weds[i]);
                }
                adapter5.notifyDataSetChanged();
                cls.updateWednesday(s, shP);
            }
        }

        else if (source == 6) {
            String s = thu.replaceAll(Meal.strMeal + ",", "");
            input6.clear();
            if (s != null) {
                Arrays.fill(thus, null);
                thus = s.split(",");
                for (int i = 0; i < thus.length; i++) {
                    mealPresenterInterface.getSpecificMeal(thus[i]);
                }
                adapter6.notifyDataSetChanged();
                cls.updateThursday(s, shP);
            }
        }

        else if (source == 7) {
            String s = fri.replaceAll(Meal.strMeal + ",", "");
            input7.clear();
            if (s != null) {
                Arrays.fill(fris, null);
                fris = s.split(",");
                for (int i = 0; i < fris.length; i++) {
                    mealPresenterInterface.getSpecificMeal(fris[i]);
                }
                adapter7.notifyDataSetChanged();
                cls.updateFriday(s, shP);
            }
        }
    }
}