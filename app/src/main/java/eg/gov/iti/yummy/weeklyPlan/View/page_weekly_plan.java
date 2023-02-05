package eg.gov.iti.yummy.weeklyPlan.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.Model.planListItem;

public class page_weekly_plan extends AppCompatActivity {

    RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5,recyclerView6,recyclerView7;
    List<planListItem> input1,input2,input3,input4,input5,input6,input7;
    WeeklyPlanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_weekly_plan);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        recyclerView1 = findViewById(R.id.recycle1);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        input1 = Arrays.asList(new planListItem("Chicken Alfredo",R.drawable.chickenalfredo),
                new planListItem("Chicken Alfredo",R.drawable.chickenalfredo),
                new planListItem("Chicken Alfredo",R.drawable.chickenalfredo));
        adapter = new WeeklyPlanAdapter(this,input1);
        recyclerView1.setAdapter(adapter);


        recyclerView2 = findViewById(R.id.recycle2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager2);
        input2 = Arrays.asList(new planListItem("Chicken Alfredo",R.drawable.chickenalfredo));
        adapter = new WeeklyPlanAdapter(this,input2);
        recyclerView2.setAdapter(adapter);

        recyclerView3 = findViewById(R.id.recycle3);
        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
        layoutManager3.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView3.setLayoutManager(layoutManager3);
        input3 = Arrays.asList();
        adapter = new WeeklyPlanAdapter(this,input3);
        recyclerView3.setAdapter(adapter);

        recyclerView4 = findViewById(R.id.recycle4);
        recyclerView4.setHasFixedSize(true);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(this);
        layoutManager4.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView4.setLayoutManager(layoutManager4);
        input4 = Arrays.asList(new planListItem("Chicken Alfredo",R.drawable.chickenalfredo),
                new planListItem("Chicken Alfredo",R.drawable.chickenalfredo));
        adapter = new WeeklyPlanAdapter(this,input4);
        recyclerView4.setAdapter(adapter);

        recyclerView5 = findViewById(R.id.recycle5);
        recyclerView5.setHasFixedSize(true);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(this);
        layoutManager5.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView5.setLayoutManager(layoutManager5);
        input5 = Arrays.asList(new planListItem("Chicken Alfredo",R.drawable.chickenalfredo),
                new planListItem("Chicken Alfredo",R.drawable.chickenalfredo),
                new planListItem("Chicken Alfredo",R.drawable.chickenalfredo),
                new planListItem("Chicken Alfredo",R.drawable.chickenalfredo));
        adapter = new WeeklyPlanAdapter(this,input5);
        recyclerView5.setAdapter(adapter);

        recyclerView6 = findViewById(R.id.recycle6);
        recyclerView6.setHasFixedSize(true);
        LinearLayoutManager layoutManager6 = new LinearLayoutManager(this);
        layoutManager6.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView6.setLayoutManager(layoutManager6);
        input6 = Arrays.asList();
        adapter = new WeeklyPlanAdapter(this,input6);
        recyclerView6.setAdapter(adapter);

        recyclerView7 = findViewById(R.id.recycle7);
        recyclerView7.setHasFixedSize(true);
        LinearLayoutManager layoutManager7 = new LinearLayoutManager(this);
        layoutManager7.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView7.setLayoutManager(layoutManager7);
        input7 = Arrays.asList(new planListItem("Chicken Alfredo",R.drawable.chickenalfredo),
                new planListItem("Chicken Alfredo",R.drawable.chickenalfredo),
                new planListItem("Chicken Alfredo",R.drawable.chickenalfredo),
                new planListItem("Chicken Alfredo",R.drawable.chickenalfredo),
                new planListItem("Chicken Alfredo",R.drawable.chickenalfredo));
        adapter = new WeeklyPlanAdapter(this,input7);
        recyclerView7.setAdapter(adapter);

    }
}