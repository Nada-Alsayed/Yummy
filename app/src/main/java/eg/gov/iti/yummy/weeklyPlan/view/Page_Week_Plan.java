package eg.gov.iti.yummy.weeklyPlan.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.weeklyPlan.view.WeeklyPlanAdapter;
import eg.gov.iti.yummy.weeklyPlan.view.planListItem;

public class Page_Week_Plan extends Fragment {
    RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5,recyclerView6,recyclerView7;
    List<MealDetail> input1,input2,input3,input4,input5,input6,input7;
    WeeklyPlanAdapter adapter;

    MealDetail mealDetail;
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
        mealDetail = new MealDetail();
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        mealDetail.strMeal = "Chicken Alfredo";
        mealDetail.strMealThumb = "https://www.themealdb.com/images/ingredients/Lime.png";
        input1 = new ArrayList<>();
        input1.add(mealDetail);
        input1.add(mealDetail);
        input1.add(mealDetail);
        adapter = new WeeklyPlanAdapter(input1,getContext());
        recyclerView1.setAdapter(adapter);


        recyclerView2 = view.findViewById(R.id.recycle2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager2);
        input2 = new ArrayList<>();
        input2.add(mealDetail);
        input2.add(mealDetail);
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
        input4.add(mealDetail);
        input4.add(mealDetail);
        input4.add(mealDetail);
        input4.add(mealDetail);
        input4.add(mealDetail);
        adapter = new WeeklyPlanAdapter(input4,getContext());
        recyclerView4.setAdapter(adapter);

        recyclerView5 = view.findViewById(R.id.recycle5);
        recyclerView5.setHasFixedSize(true);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getContext());
        layoutManager5.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView5.setLayoutManager(layoutManager5);
        input5 = new ArrayList<>();
        input5.add(mealDetail);
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
        input7.add(mealDetail);
        input7.add(mealDetail);
        input7.add(mealDetail);
        input7.add(mealDetail);
        input7.add(mealDetail);
        adapter = new WeeklyPlanAdapter(input7,getContext());
        recyclerView7.setAdapter(adapter);
        }
    }