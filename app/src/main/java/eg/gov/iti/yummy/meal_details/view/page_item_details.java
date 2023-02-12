package eg.gov.iti.yummy.meal_details.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.ArrayList;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.db.ConcreteLocalSource;
import eg.gov.iti.yummy.db.UserEntity;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenter;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenterInterface;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.network.API_Client;

public class page_item_details extends AppCompatActivity implements MealViewInterface {

    YouTubePlayerView youTubePlayerView;
    ImageView mealPic;
    TextView MealName,MealOrigin,steps;
    ArrayList<Recipe> myIngredients;
    MealPresenterInterface mealPresenterInterface;
    RecyclerView recyclerView;
    IngredientsAdapter adapter;
    ConcreteLocalSource cls;
    RootMealDetail rootMealDetail;
    String fav;
    Button btnAddToFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_item_details);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        myIngredients = new ArrayList<>();

        rootMealDetail = new RootMealDetail();

        Intent intent = getIntent();
        String mealName = intent.getStringExtra("MealName");
        youTubePlayerView = findViewById(R.id.videoView);

        mealPresenterInterface = new MealPresenter(Repository.getInstance(API_Client.getInstance(getApplicationContext()),getApplicationContext()),this);
        mealPresenterInterface.getSpecificMeal(mealName);

        MealName = findViewById(R.id.mealName);
        MealOrigin = findViewById(R.id.mealOrigin);
        mealPic = findViewById(R.id.mealImage);
        steps = findViewById(R.id.step1);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.HORIZONTAL,false);
        recyclerView = findViewById(R.id.recyclerViewIngredients);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        btnAddToFav = findViewById(R.id.btnAddToFav);
        cls = ConcreteLocalSource.getInstance(getApplicationContext());
        SharedPreferences pref = getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        String shP =pref.getString("USERNAME","N/A");
        cls.getData(shP).observe(page_item_details.this, new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if(userEntity.getFavourite()!=null){
                    fav = userEntity.getFavourite();
                }
            }
        });
        btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav==null)fav = rootMealDetail.getMeals().get(0).strMeal+",";
                else fav += rootMealDetail.getMeals().get(0).strMeal+",";
                cls.updateFavourite(fav,shP);
            }
        });
    }

    @Override
    public void showSpecificItem(RootMealDetail meals) {
        rootMealDetail = meals;
        MealName.setText(meals.getMeals().get(0).strMeal);
        MealOrigin.setText(meals.getMeals().get(0).strArea);
        prepareIngredients(meals);
        adapter =new IngredientsAdapter(myIngredients,getApplicationContext());
        recyclerView.setAdapter(adapter);
        String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient1+".png";
        myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient1,meals.getMeals().get(0).strMeasure1,thumb));
        if(!meals.getMeals().get(0).strYoutube.equals("")) {
            String[] arr = meals.getMeals().get(0).strYoutube.split("=");
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    youTubePlayer.loadVideo(arr[1], 0);
                }
            });
        }
        steps.setText(meals.getMeals().get(0).strInstructions);
        Glide.with(getApplicationContext())
                .load(meals.getMeals().get(0).strMealThumb)
                .into(mealPic);
    }

    void prepareIngredients(RootMealDetail meals){
        if(!meals.getMeals().get(0).strIngredient1.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient1+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient1,meals.getMeals().get(0).strMeasure1,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient2.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient2+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient2,meals.getMeals().get(0).strMeasure2,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient3.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient3+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient3,meals.getMeals().get(0).strMeasure3,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient4.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient4+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient4,meals.getMeals().get(0).strMeasure4,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient5.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient5+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient5,meals.getMeals().get(0).strMeasure5,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient6.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient6+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient6,meals.getMeals().get(0).strMeasure6,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient7.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient7+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient7,meals.getMeals().get(0).strMeasure7,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient8.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient8+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient8,meals.getMeals().get(0).strMeasure8,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient9.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient9+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient9,meals.getMeals().get(0).strMeasure9,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient10.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient10+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient10,meals.getMeals().get(0).strMeasure10,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient11.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient11+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient11,meals.getMeals().get(0).strMeasure11,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient12.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient12+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient12,meals.getMeals().get(0).strMeasure12,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient13.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient13+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient13,meals.getMeals().get(0).strMeasure13,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient14.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient14+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient14,meals.getMeals().get(0).strMeasure14,thumb));
        }
        if(!meals.getMeals().get(0).strIngredient15.equals("")){
            String thumb = "https://www.themealdb.com/images/ingredients/"+meals.getMeals().get(0).strIngredient15+".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient15,meals.getMeals().get(0).strMeasure15,thumb));
        }

    }
}