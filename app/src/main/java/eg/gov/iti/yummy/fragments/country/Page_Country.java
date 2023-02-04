package eg.gov.iti.yummy.fragments.country;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class Page_Country extends Fragment {
    RecyclerView recyclerView;
    List<FavList> input;
    AdapterCountry adapterCountry;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_country, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerViewCountry);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        input= Arrays.asList(
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng),
                new FavList(R.drawable.egyptpng)
        );
        adapterCountry=new AdapterCountry(getContext(), input);
        recyclerView.setAdapter(adapterCountry);
    }
}