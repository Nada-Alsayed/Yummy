package eg.gov.iti.yummy.Home.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.weeklyPlan.View.WeeklyPlanAdapter;
import eg.gov.iti.yummy.Model.planListItem;

public class page_home extends AppCompatActivity {

    ViewPager viewPager;
    StackAdapter adapter;
    ArrayList<Integer> input;

    RecyclerView recyclerView,recyclerView1,recyclerView2;

    ArrayList<planListItem> data;

    homeAdapter homeAdapter;

    WeeklyPlanAdapter weeklyPlanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_home);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        viewPager = findViewById(R.id.stack_view);
        input = numberImage();
        adapter = new StackAdapter(input,this);
        viewPager.setPageTransformer(true,new ViewPagerStack());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);

        recyclerView = findViewById(R.id.myRecView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        data = addData();
        weeklyPlanAdapter = new WeeklyPlanAdapter(this, data);
        recyclerView.setAdapter(weeklyPlanAdapter);

        recyclerView1 = findViewById(R.id.myRecView1);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(weeklyPlanAdapter);

        recyclerView2 = findViewById(R.id.myRecView2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(weeklyPlanAdapter);
    }

    private ArrayList<Integer> numberImage()
    {
        ArrayList<Integer> image=new ArrayList<>();
        image.add(R.drawable.chickenalfredo);
        image.add(R.drawable.chickenalfredo);
        image.add(R.drawable.chickenalfredo);
        image.add(R.drawable.chickenalfredo);
        image.add(R.drawable.chickenalfredo);
        image.add(R.drawable.chickenalfredo);
        image.add(R.drawable.chickenalfredo);
        image.add(R.drawable.chickenalfredo);
        return image;
    }

    private ArrayList<planListItem> addData()
    {
        ArrayList<planListItem> arr=new ArrayList<>();
        arr.add(new planListItem("Chicken Alfredo",R.drawable.chickenalfredo));
        arr.add(new planListItem("Chicken Alfredo",R.drawable.chickenalfredo));
        arr.add(new planListItem("Chicken Alfredo",R.drawable.chickenalfredo));
        arr.add(new planListItem("Chicken Alfredo",R.drawable.chickenalfredo));
        arr.add(new planListItem("Chicken Alfredo",R.drawable.chickenalfredo));
        arr.add(new planListItem("Chicken Alfredo",R.drawable.chickenalfredo));
        return arr;
    }

    public class ViewPagerStack implements ViewPager.PageTransformer{

        @Override
        public void transformPage(View page, float position) {
            if(position>=0){
                page.setScaleX(.9f - .05f * position);
                page.setScaleY(.9f);
                page.setTranslationX(-page.getWidth()*position);
                page.setTranslationY(-20*position);
            }
        }
    }
}