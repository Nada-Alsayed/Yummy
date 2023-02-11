package eg.gov.iti.yummy.country;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import eg.gov.iti.yummy.SignUp.view.Page_Sign_Up;
import eg.gov.iti.yummy.country.Page_CountryDirections.ActionPageCountryToPageFilteredItems;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.meal_details.view.page_item_details;
import eg.gov.iti.yummy.model.Category;
import eg.gov.iti.yummy.model.Country;
import eg.gov.iti.yummy.model.Flags;
import eg.gov.iti.yummy.model.Ingredient;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.search.view.Page_SearchDirections;
import eg.gov.iti.yummy.search.view.Page_SearchDirections.ActionPageSearchToPageFilteredItems;

public class AdapterCountry extends RecyclerView.Adapter<AdapterCountry.Holder>{

    private List<Country> countryList;
    private List<Category> categoryList;
    private List<Ingredient> ingredientList;
    private List<MealDetail> mealDetailList;
    private Context context;

    private int originList;
    private int originFragment;

    public AdapterCountry(Context context, List<Ingredient> listData,int state,int origin) {
        this.context = context;
        ingredientList = listData;
        originList = state;
        originFragment = origin;
    }
    public AdapterCountry(int state, Context context, List<Category> listData,int origin) {
        this.context = context;
        categoryList = listData;
        originList = state;
        originFragment = origin;
    }
    public AdapterCountry(int state , List<Country> listData,Context context,int origin) {
        this.context = context;
        countryList = listData;
        originList = state;
        originFragment = origin;
    }

    public AdapterCountry(Context context, List<MealDetail> listData) {
        this.context = context;
        mealDetailList = listData;
    }


    @NonNull
    @Override
    public AdapterCountry.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.recyclerview_row_country,parent,false);
        AdapterCountry.Holder vh=new AdapterCountry.Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCountry.Holder holder,@SuppressLint("RecyclerView") int position) {

        if(originList == 1){
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/"+ingredientList.get(position).strIngredient+".png")
                    .into(holder.listImage);
            holder.listText.setText(ingredientList.get(position).strIngredient);
        }
        else if(originList == 2){
            Glide.with(context)
                    .load(categoryList.get(position).getStrCategoryThumb())
                    .into(holder.listImage);
            holder.listText.setText(categoryList.get(position).getStrCategory());
        }
        else if(originList == 3){
            Glide.with(context)
                    .load(Flags.getFlag(countryList.get(position).getStrArea()))
                    .into(holder.listImage);
            holder.listText.setText(countryList.get(position).getStrArea());
        }
        else{
            Glide.with(context)
                    .load(mealDetailList.get(position).strMealThumb)
                    .into(holder.listImage);
            holder.listText.setText(mealDetailList.get(position).strMeal);
        }
        holder.listCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(originFragment==2) {
                    ActionPageCountryToPageFilteredItems action = Page_CountryDirections.actionPageCountryToPageFilteredItems(holder.listText.getText().toString(), originList,originFragment);
                    Navigation.findNavController(v).navigate(action);
                }else if(originFragment==1){
                    ActionPageSearchToPageFilteredItems action = Page_SearchDirections.actionPageSearchToPageFilteredItems(holder.listText.getText().toString(), originList,originFragment);
                    Navigation.findNavController(v).navigate(action);
                }else{
                    Intent intent = new Intent(context, page_item_details.class);
                    intent.putExtra("MealName",holder.listText.getText().toString());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(originList ==1)return ingredientList.size();
        else if(originList ==2)return categoryList.size();
        else if(originList==3)return countryList.size();
        else return mealDetailList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView listImage;
        TextView listText;
        CardView listCard;
        public Holder(View itemView) {
            super(itemView);
            this.listImage = (ImageView) itemView.findViewById(R.id.imageSearchList);
            this.listText = (TextView) itemView.findViewById(R.id.textSearchList);
            this.listCard = (CardView) itemView.findViewById(R.id.listCard);

        }

    }
}
