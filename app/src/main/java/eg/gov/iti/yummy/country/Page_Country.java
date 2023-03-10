package eg.gov.iti.yummy.country;

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
import eg.gov.iti.yummy.db.ConcreteLocalSource;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootCategory;
import eg.gov.iti.yummy.model.RootCountry;
import eg.gov.iti.yummy.model.RootIngredient;
import eg.gov.iti.yummy.network.API_Client;
import eg.gov.iti.yummy.search.Presenter.SearchPresenter;
import eg.gov.iti.yummy.search.Presenter.SearchPresenterInterface;
import eg.gov.iti.yummy.search.view.SearchViewInterface;

public class Page_Country extends Fragment implements SearchViewInterface {
    RecyclerView recyclerView;
    TextView header;
    AdapterCountry adapter;
    String pageName;
    ImageView back;
    EditText search;
    SearchPresenterInterface searchPresenterInterface;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_country, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchPresenterInterface = new SearchPresenter(Repository.getInstance(API_Client.getInstance(getContext()), ConcreteLocalSource.getInstance(getContext()), getContext()), this);
        searchPresenterInterface.getAllIngredients();
        searchPresenterInterface.getAllCategories();
        searchPresenterInterface.getAllCountries();
        recyclerView=view.findViewById(R.id.recyclerViewCountry);
        recyclerView.setHasFixedSize(true);
        header= view.findViewById(R.id.allHeader);
        pageName = Page_CountryArgs.fromBundle(getArguments()).getHeader();
        header.setText(pageName);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        search = view.findViewById(R.id.editTextSearch);
        back = view.findViewById(R.id.imgback5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_page_Country_to_page_Search);
            }
        });
    }

    @Override
    public void showAllIngredients(RootIngredient products) {
        if(pageName.equals("Ingredients")){
            adapter =new AdapterCountry(getContext(), products.getMeals(),1,2);
            recyclerView.setAdapter(adapter);
            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    List stream =  products.getMeals().stream().filter(name-> name.strIngredient.startsWith(s.toString())).collect(toList());
                    adapter = new AdapterCountry(getContext(),stream,1,2);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
    @Override
    public void showAllCategories(RootCategory category) {
        if(pageName.equals("Categories")){
            adapter =new AdapterCountry(2,getContext(), category.getCategories(),2);
            recyclerView.setAdapter(adapter);
            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    List stream =  category.getCategories().stream().filter(name-> name.getStrCategory().startsWith(s.toString())).collect(toList());
                    adapter = new AdapterCountry(2,getContext(), stream,2);
                    recyclerView.setAdapter(adapter);
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    @Override
    public void showAllCountries(RootCountry country) {
        if(pageName.equals("Countries")){
            adapter =new AdapterCountry(3, country.getCountries(),getContext(),2);
            recyclerView.setAdapter(adapter);
            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    List stream =  country.getCountries().stream().filter(name-> name.getStrArea().startsWith(s.toString())).collect(toList());
                    adapter = new AdapterCountry(3, stream,getContext(),2);
                    recyclerView.setAdapter(adapter);
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
}