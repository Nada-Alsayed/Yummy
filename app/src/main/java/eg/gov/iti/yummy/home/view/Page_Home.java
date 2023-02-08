package eg.gov.iti.yummy.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.weeklyPlan.view.WeeklyPlanAdapter;
import eg.gov.iti.yummy.weeklyPlan.view.planListItem;

public class Page_Home extends Fragment {
    ViewPager viewPager;
    StackAdapter adapter;
    ArrayList<Integer> input;

    RecyclerView recyclerView,recyclerView1,recyclerView2;

    ArrayList<planListItem> data;

    homeAdapter homeAdapter;

    WeeklyPlanAdapter weeklyPlanAdapter;
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
        input = numberImage();
        adapter = new StackAdapter(input,getContext());
        viewPager.setPageTransformer(true,new ViewPagerStack());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);

        recyclerView = view.findViewById(R.id.myRecView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        data = addData();
        weeklyPlanAdapter = new WeeklyPlanAdapter(getContext(), data);
        recyclerView.setAdapter(weeklyPlanAdapter);

        recyclerView1 = view.findViewById(R.id.myRecView1);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(weeklyPlanAdapter);

        recyclerView2 = view.findViewById(R.id.myRecView2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
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

