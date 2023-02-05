package eg.gov.iti.yummy.fragments.available_dishes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.fragments.favourite.FavList;


public class Page_Available_Dishes extends Fragment {
    RecyclerView recyclerView;
    List<FavList> input;
    AdapterAvailableDishes adapterAvailableDishes;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page__available__dishes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerViewAvailableDish);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        input= Arrays.asList(
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24),
                new FavList(R.drawable.cook,R.drawable.baseline_favorite_24)
        );
        adapterAvailableDishes=new AdapterAvailableDishes(getContext(), input);
        recyclerView.setAdapter(adapterAvailableDishes);
    }
}