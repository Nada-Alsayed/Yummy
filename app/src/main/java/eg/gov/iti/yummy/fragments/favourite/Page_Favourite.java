package eg.gov.iti.yummy.fragments.favourite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.fragments.favourite.AdapterFavList;
import eg.gov.iti.yummy.fragments.favourite.FavList;

public class Page_Favourite extends Fragment {
RecyclerView recyclerView;
List<FavList> input;
AdapterFavList adapterFavList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page__favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        input= Arrays.asList(new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24),
                new FavList("CHoCo Cake","happpppy",R.drawable.cook,R.drawable.baseline_delete_24)

        );
        adapterFavList=new AdapterFavList(getContext(), input);
        recyclerView.setAdapter(adapterFavList);
    }
}