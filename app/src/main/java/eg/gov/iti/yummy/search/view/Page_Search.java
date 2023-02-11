package eg.gov.iti.yummy.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import eg.gov.iti.yummy.country.AdapterCountry;
import eg.gov.iti.yummy.search.view.Page_SearchDirections.ActionPageSearchToPageCountry;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootCategory;
import eg.gov.iti.yummy.model.RootCountry;
import eg.gov.iti.yummy.model.RootIngredient;
import eg.gov.iti.yummy.network.API_Client;
import eg.gov.iti.yummy.search.Presenter.SearchPresenter;
import eg.gov.iti.yummy.search.Presenter.SearchPresenterInterface;

public class Page_Search extends Fragment implements SearchViewInterface{
    SearchPresenterInterface searchPresenterInterface;

    TextView allIngredients,allCategories,allCountries;

    RecyclerView ingredientsRecycle,countriesRecycle,categoriesRecycle;
    AdapterCountry adapter1,adapter2,adapter3;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page__search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchPresenterInterface = new SearchPresenter(Repository.getInstance(API_Client.getInstance(), getContext()), this);
        searchPresenterInterface.getAllIngredients();
        searchPresenterInterface.getAllCategories();
        searchPresenterInterface.getAllCountries();

        GridLayoutManager gridLayoutManager1=new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);
        ingredientsRecycle = view.findViewById(R.id.recycleIngredient);
        ingredientsRecycle.setHasFixedSize(true);
        ingredientsRecycle.setLayoutManager(gridLayoutManager1);

        GridLayoutManager gridLayoutManager2=new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);
        categoriesRecycle = view.findViewById(R.id.recycleCategory);
        categoriesRecycle.setHasFixedSize(true);
        categoriesRecycle.setLayoutManager(gridLayoutManager2);

        GridLayoutManager gridLayoutManager3=new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);
        countriesRecycle = view.findViewById(R.id.recycleCountry);
        countriesRecycle.setHasFixedSize(true);
        countriesRecycle.setLayoutManager(gridLayoutManager3);


        allCountries = view.findViewById(R.id.txtCountrySeeAll);
        allCountries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionPageSearchToPageCountry action = Page_SearchDirections.actionPageSearchToPageCountry("Countries");
                Navigation.findNavController(v).navigate(action);
            }
        });
        allCategories = view.findViewById(R.id.txtCategorySeeAll);
        allCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionPageSearchToPageCountry action = Page_SearchDirections.actionPageSearchToPageCountry("Categories");
                Navigation.findNavController(v).navigate(action);
            }
        });
        allIngredients = view.findViewById(R.id.txtIngredientSeeAll);
        allIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Navigation.findNavController(view).navigate(R.id.action_page_Search_to_page_Country);
                ActionPageSearchToPageCountry action = Page_SearchDirections.actionPageSearchToPageCountry("Ingredients");
                Navigation.findNavController(v).navigate(action);
            }
        });

    }

    @Override
    public void showAllIngredients(RootIngredient products) {
        adapter1 =new AdapterCountry(getContext(), products.getMeals(),1,1);
        ingredientsRecycle.setAdapter(adapter1);
    }

    @Override
    public void showAllCategories(RootCategory category) {
        adapter2 =new AdapterCountry(2,getContext(), category.getCategories(),1);
        categoriesRecycle.setAdapter(adapter2);


    }

    @Override
    public void showAllCountries(RootCountry country) {
        adapter3 =new AdapterCountry(3, country.getCountries(),getContext(),1);
        countriesRecycle.setAdapter(adapter3);
    }
}