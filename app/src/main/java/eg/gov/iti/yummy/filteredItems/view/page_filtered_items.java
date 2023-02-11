package eg.gov.iti.yummy.filteredItems.view;

import static java.util.stream.Collectors.toList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.country.AdapterCountry;
import eg.gov.iti.yummy.filteredItems.Presenter.FilterPresenter;
import eg.gov.iti.yummy.filteredItems.Presenter.FilterPresenterInterface;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.network.API_Client;
import  eg.gov.iti.yummy.filteredItems.view.page_filtered_itemsDirections.ActionPageFilteredItemsToPageCountry;

public class page_filtered_items extends Fragment implements FilterViewInterface {

    TextView header;
    String pageName;
    ImageView back;
    int originFragment;
    int originList;
    EditText search;

    FilterPresenterInterface filterPresenterInterface;
    AdapterCountry adapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_filtered_items, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        header= view.findViewById(R.id.filteredListHeader);
        pageName = eg.gov.iti.yummy.filteredItems.view.page_filtered_itemsArgs.fromBundle(getArguments()).getFilterBy();
        originFragment = eg.gov.iti.yummy.filteredItems.view.page_filtered_itemsArgs.fromBundle(getArguments()).getOriginFragment();
        originList = page_filtered_itemsArgs.fromBundle(getArguments()).getFilterType();
        header.setText(pageName);

        filterPresenterInterface = new FilterPresenter(Repository.getInstance(API_Client.getInstance(getContext()),getContext()),this);
        if(originList==1)filterPresenterInterface.getAllMealsFilterByIngredient(pageName);
        else if(originList==2)filterPresenterInterface.getAllMealsFilterByCategory(pageName);
        else filterPresenterInterface.getAllMealsFilterByCountry(pageName);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView = view.findViewById(R.id.recyclerViewFilteredList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        search = view.findViewById(R.id.editTextSearchFilteredList);

        back = view.findViewById(R.id.imgBackFilteredList);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(originFragment==1)
                    Navigation.findNavController(view).navigate(R.id.action_page_filtered_items_to_page_Search);
                else{
                    if(originList==1){
                        ActionPageFilteredItemsToPageCountry action = eg.gov.iti.yummy.filteredItems.view.page_filtered_itemsDirections.actionPageFilteredItemsToPageCountry("Ingredients");
                        Navigation.findNavController(view).navigate(action);
                    }
                    else if(originList==2){
                        ActionPageFilteredItemsToPageCountry action = eg.gov.iti.yummy.filteredItems.view.page_filtered_itemsDirections.actionPageFilteredItemsToPageCountry("Categories");
                        Navigation.findNavController(view).navigate(action);
                    }
                    else{
                        ActionPageFilteredItemsToPageCountry action = page_filtered_itemsDirections.actionPageFilteredItemsToPageCountry("Countries");
                        Navigation.findNavController(view).navigate(action);
                    }
                }

            }
        });
    }
    @Override
    public void showFilteredItemsByIngredient(RootMealDetail meals) {
        adapter =new AdapterCountry(getContext(), meals.getMeals());
        recyclerView.setAdapter(adapter);
        addTextListener(meals);
    }

    @Override
    public void showFilteredItemsByCategory(RootMealDetail meals) {
        adapter =new AdapterCountry(getContext(), meals.getMeals());
        recyclerView.setAdapter(adapter);
        addTextListener(meals);
    }

    @Override
    public void showFilteredItemsByCountry(RootMealDetail meals) {
        adapter =new AdapterCountry(getContext(), meals.getMeals());
        recyclerView.setAdapter(adapter);
        addTextListener(meals);
    }

    public void addTextListener(RootMealDetail meals){
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List stream =  meals.getMeals().stream().filter(name-> name.strMeal.startsWith(s.toString())).collect(toList());
                adapter = new AdapterCountry(getContext(),stream);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}