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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
    String fav,sat,sun,mon,tue,wed,thu,fri;
    Button btnAddToFav,btnAddToWeekPlan;
    CheckBox satBox,sunBox,monBox,tueBox,wedBox,thuBox,friBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_item_details);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        satBox = findViewById(R.id.radioButtonSat);
        sunBox = findViewById(R.id.radioButtonSun);
        monBox = findViewById(R.id.radioButtonMon);
        tueBox = findViewById(R.id.radioButtonTue);
        wedBox = findViewById(R.id.radioButtonWed);
        thuBox = findViewById(R.id.radioButtonThu);
        friBox = findViewById(R.id.radioButtonFri);

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
        btnAddToWeekPlan = findViewById(R.id.btnAddToMyPlan);
        cls = ConcreteLocalSource.getInstance(getApplicationContext());
        SharedPreferences pref = getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        String shP =pref.getString("USERNAME","N/A");
        cls.getData(shP).observe(page_item_details.this, new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if(userEntity.getFavourite()!=null){
                    fav = userEntity.getFavourite();
                }
                if(userEntity.getSaturday()!=null){
                    sat = userEntity.getSaturday();
                }
                if(userEntity.getSunday()!=null){
                    sun = userEntity.getSunday();
                }
                if(userEntity.getMonday()!=null){
                    mon = userEntity.getMonday();
                }
                if(userEntity.getTuesday()!=null){
                    tue = userEntity.getTuesday();
                }
                if(userEntity.getWednesday()!=null){
                    wed = userEntity.getWednesday();
                }
                if(userEntity.getThursday()!=null){
                    thu = userEntity.getThursday();
                }
                if(userEntity.getFriday()!=null){
                    fri = userEntity.getFriday();
                }
            }
        });
        btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if(!fav.contains(rootMealDetail.getMeals().get(0).strMeal)) {
                    if (fav == null) fav = rootMealDetail.getMeals().get(0).strMeal + ",";
                    else fav += rootMealDetail.getMeals().get(0).strMeal + ",";
                    cls.updateFavourite(fav, shP);
//                }else{
//                    Toast.makeText(getApplicationContext(),"Already in your favourites",Toast.LENGTH_LONG).show();
//                }
            }
        });

        btnAddToWeekPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(satBox.isChecked()) {
    //                if (satAdd==true) {
                        if (sat == null) sat = rootMealDetail.getMeals().get(0).strMeal + ",";
                        else sat += rootMealDetail.getMeals().get(0).strMeal + ",";
                        cls.updateSaturday(sat, shP);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Already in your Plan for Saturday", Toast.LENGTH_LONG).show();
//                    }
                }
                if(sunBox.isChecked()) {
      //              if (!sun.contains(rootMealDetail.getMeals().get(0).strMeal)) {
                        if (sun == null) sun = rootMealDetail.getMeals().get(0).strMeal + ",";
                        else sun += rootMealDetail.getMeals().get(0).strMeal + ",";
                        cls.updateSunday(sun, shP);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Already in your Plan for Sunday", Toast.LENGTH_LONG).show();
//                    }
                }
                if(monBox.isChecked()) {
//              if (!mon.contains(rootMealDetail.getMeals().get(0).strMeal)) {
                        if (mon == null) mon = rootMealDetail.getMeals().get(0).strMeal + ",";
                        else mon += rootMealDetail.getMeals().get(0).strMeal + ",";
                        cls.updateMonday(mon, shP);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Already in your Plan for Monday", Toast.LENGTH_LONG).show();
//                    }
                }
                if(tueBox.isChecked()) {
//                    if (!tue.contains(rootMealDetail.getMeals().get(0).strMeal)) {
                        if (tue == null) tue = rootMealDetail.getMeals().get(0).strMeal + ",";
                        else tue += rootMealDetail.getMeals().get(0).strMeal + ",";
                        cls.updateTuesday(tue, shP);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Already in your Plan for Tuesday", Toast.LENGTH_LONG).show();
//                    }
                }
                if(wedBox.isChecked()) {
//                    if (!wed.contains(rootMealDetail.getMeals().get(0).strMeal)) {
                        if (wed == null) wed = rootMealDetail.getMeals().get(0).strMeal + ",";
                        else wed += rootMealDetail.getMeals().get(0).strMeal + ",";
                        cls.updateWednesday(wed, shP);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Already in your Plan for Wednesday", Toast.LENGTH_LONG).show();
//                    }
                }
                if(thuBox.isChecked()) {
//                    if (!thu.contains(rootMealDetail.getMeals().get(0).strMeal)) {
                        if (thu == null) thu = rootMealDetail.getMeals().get(0).strMeal + ",";
                        else thu += rootMealDetail.getMeals().get(0).strMeal + ",";
                        cls.updateThursday(thu, shP);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Already in your Plan for Thursday", Toast.LENGTH_LONG).show();
//                    }
                }
                if(friBox.isChecked()) {
//                    if (!fri.contains(rootMealDetail.getMeals().get(0).strMeal)) {
                        if (fri == null) fri = rootMealDetail.getMeals().get(0).strMeal + ",";
                        else fri += rootMealDetail.getMeals().get(0).strMeal + ",";
                        cls.updateFriday(fri, shP);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Already in your Plan for Friday", Toast.LENGTH_LONG).show();
//                    }
                }
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