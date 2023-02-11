package eg.gov.iti.yummy.favourite.view;

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
import eg.gov.iti.yummy.favourite.FavList;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenter;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenterInterface;
import eg.gov.iti.yummy.meal_details.view.MealViewInterface;
import eg.gov.iti.yummy.meal_details.view.page_item_details;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.network.API_Client;

public class Page_Favourite extends Fragment implements MealViewInterface {
    RecyclerView recyclerView;

    MealPresenterInterface mealPresenterInterface;
    ConcreteLocalSource cls;
    List<FavList> input;
    FavList favListItem;
    AdapterFavList adapterFavList;

    String[] favs;

    String fav;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page__favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        input = new ArrayList<>();
        cls = ConcreteLocalSource.getInstance(getContext());
        SharedPreferences pref = getActivity().getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        String shP = pref.getString("USERNAME", "N/A");
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        adapterFavList = new AdapterFavList(getContext(), input);
        recyclerView.setLayoutManager(linearLayoutManager);
        mealPresenterInterface = new MealPresenter(Repository.getInstance(API_Client.getInstance(getContext()), getContext()), Page_Favourite.this);
        cls.getData(shP).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                fav = userEntity.getFavourite();
                if (fav != null) {
                    favs = fav.split(",");
                    for (int i = 0; i < favs.length; i++) {
                        mealPresenterInterface.getSpecificMeal(favs[i]);
                    }
                }
            }
        });
    }

    @Override
    public void showSpecificItem(RootMealDetail meals) {
        favListItem = new FavList();
        favListItem.setName(meals.getMeals().get(0).strMeal);
        favListItem.setOrigin(meals.getMeals().get(0).strArea);
        favListItem.setThumbnail(meals.getMeals().get(0).strMealThumb);
        input.add(favListItem);
        if (favs.length == input.size()) {
            adapterFavList.setListData(input);
            recyclerView.setAdapter(adapterFavList);
            adapterFavList.notifyDataSetChanged();
        }
    }
}