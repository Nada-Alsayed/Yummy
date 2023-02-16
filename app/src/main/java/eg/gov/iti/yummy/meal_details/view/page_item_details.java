package eg.gov.iti.yummy.meal_details.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.db.ConcreteLocalSource;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenter;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenterInterface;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.network.API_Client;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class page_item_details extends AppCompatActivity implements MealViewInterface, OnClick {

    YouTubePlayerView youTubePlayerView;
    ImageView mealPic;
    TextView MealName, MealOrigin, steps;
    ArrayList<Recipe> myIngredients;
    MealPresenterInterface mealPresenterInterface;
    RecyclerView recyclerView;
    IngredientsAdapter adapter;
    ConcreteLocalSource cls;
    RootMealDetail rootMealDetail;
    int  sat, sun, mon, tue, wed, thu, fri;
    Button btnAddToFav, btnAddToWeekPlan;
    CheckBox satBox, sunBox, monBox, tueBox, wedBox, thuBox, friBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_item_details);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        MealName = findViewById(R.id.mealName);
        MealOrigin = findViewById(R.id.mealOrigin);
        mealPic = findViewById(R.id.mealImage);
        steps = findViewById(R.id.step1);

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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recyclerViewIngredients);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        btnAddToFav = findViewById(R.id.btnAddToFav);
        btnAddToWeekPlan = findViewById(R.id.btnAddToMyPlan);
        cls = ConcreteLocalSource.getInstance(getApplicationContext());

        SharedPreferences pref = getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        String shP = pref.getString("USERNAME", "N/A");

        mealPresenterInterface = new MealPresenter(Repository.getInstance(API_Client.getInstance(getApplicationContext()), ConcreteLocalSource.getInstance(getApplicationContext()), getApplicationContext()), this);

        if (isNetworkAvailable(getApplicationContext())) {
            mealPresenterInterface.getSpecificMeal(mealName);
            Toast.makeText(this, " connected", Toast.LENGTH_SHORT).show();
        } else {
            mealPresenterInterface.getOffMeal(mealName).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MealDetail>() {
                @Override
                public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                }

                @Override
                public void onNext(@io.reactivex.rxjava3.annotations.NonNull MealDetail mealDetail) {
                    Log.e("TAG", "onChanged: kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk ");
                    MealName.setText(mealDetail.strMeal);
                    MealOrigin.setText(mealDetail.strArea);
                    prepareOffIngredients(mealDetail);
                    adapter = new IngredientsAdapter(myIngredients, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    String thumb = "https://www.themealdb.com/images/ingredients/" + mealDetail.strIngredient1 + ".png";
                    myIngredients.add(new Recipe(mealDetail.strIngredient1, mealDetail.strMeasure1, thumb));
                    steps.setText(mealDetail.strInstructions);
                    Glide.with(getApplicationContext())
                            .load(mealDetail.strMealThumb)
                            .into(mealPic);
                }

                @Override
                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
            Toast.makeText(this, "not connected", Toast.LENGTH_SHORT).show();
        }

        btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMealToFavOnClick(rootMealDetail.meals.get(0));
            }
        });

        btnAddToWeekPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeekMeals week=new WeekMeals();
                if(satBox.isChecked())
                    week.setSat("1");
                else
                    week.setSat("0");
                if(sunBox.isChecked())
                    week.setSun("1");
                else
                    week.setSun("0");
                if(monBox.isChecked())
                    week.setMon("1");
                else
                    week.setMon("0");
                if(tueBox.isChecked())
                    week.setTues("1");
                else
                    week.setTues("0");
                if(thuBox.isChecked())
                    week.setThurs("1");
                else
                    week.setThurs("0");
                if(friBox.isChecked())
                    week.setFri("1");
                else
                    week.setFri("0");
                if(wedBox.isChecked())
                    week.setWed("1");
                else
                    week.setWed("0");
                addMealsToWeekPlanOnClick(rootMealDetail.meals.get(0),week);
            }
        });

    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void addMealToFav(MealDetail meal) {
        mealPresenterInterface.addToFav(meal);
    }

//    @Override
//    public void getOffMeal(LiveData<MealDetail> meal) {
//        meal.observe(new page_item_details(), new Observer<MealDetail>() {
//            @Override
//            public void onChanged(MealDetail mealDetail) {
//                Log.e("TAG", "onChanged: kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk ");
//                MealName.setText(mealDetail.strMeal);
//                MealOrigin.setText(mealDetail.strArea);
//                prepareOffIngredients(mealDetail);
//                adapter =new IngredientsAdapter(myIngredients,getApplicationContext());
//                recyclerView.setAdapter(adapter);
//                String thumb = "https://www.themealdb.com/images/ingredients/"+mealDetail.strIngredient1+".png";
//                myIngredients.add(new Recipe(mealDetail.strIngredient1,mealDetail.strMeasure1,thumb));
//                if(!mealDetail.strYoutube.equals("")) {
//                    String[] arr =mealDetail.strYoutube.split("=");
//                    youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//                        @Override
//                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                            super.onReady(youTubePlayer);
//                            youTubePlayer.loadVideo(arr[1], 0);
//                        }
//                    });
//                }
//                steps.setText(mealDetail.strInstructions);
//                Glide.with(getApplicationContext())
//                        .load(mealDetail.strMealThumb)
//                        .into(mealPic);
//
//            }
//        });
//    }


    @Override
    public void showSpecificItem(RootMealDetail meals) {
        rootMealDetail = meals;
        MealName.setText(meals.getMeals().get(0).strMeal);
        MealOrigin.setText(meals.getMeals().get(0).strArea);
        prepareIngredients(meals);
        adapter = new IngredientsAdapter(myIngredients, getApplicationContext());
        recyclerView.setAdapter(adapter);
        String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient1 + ".png";
        myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient1, meals.getMeals().get(0).strMeasure1, thumb));
        if (!meals.getMeals().get(0).strYoutube.equals("")) {
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

    void prepareIngredients(RootMealDetail meals) {
        if (!meals.getMeals().get(0).strIngredient1.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient1 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient1, meals.getMeals().get(0).strMeasure1, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient2.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient2 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient2, meals.getMeals().get(0).strMeasure2, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient3.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient3 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient3, meals.getMeals().get(0).strMeasure3, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient4.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient4 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient4, meals.getMeals().get(0).strMeasure4, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient5.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient5 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient5, meals.getMeals().get(0).strMeasure5, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient6.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient6 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient6, meals.getMeals().get(0).strMeasure6, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient7.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient7 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient7, meals.getMeals().get(0).strMeasure7, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient8.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient8 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient8, meals.getMeals().get(0).strMeasure8, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient9.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient9 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient9, meals.getMeals().get(0).strMeasure9, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient10.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient10 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient10, meals.getMeals().get(0).strMeasure10, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient11.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient11 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient11, meals.getMeals().get(0).strMeasure11, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient12.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient12 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient12, meals.getMeals().get(0).strMeasure12, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient13.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient13 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient13, meals.getMeals().get(0).strMeasure13, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient14.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient14 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient14, meals.getMeals().get(0).strMeasure14, thumb));
        }
        if (!meals.getMeals().get(0).strIngredient15.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meals.getMeals().get(0).strIngredient15 + ".png";
            myIngredients.add(new Recipe(meals.getMeals().get(0).strIngredient15, meals.getMeals().get(0).strMeasure15, thumb));
        }

    }

    void prepareOffIngredients(MealDetail meal) {
        if (!meal.strIngredient1.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient1 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient1, meal.strMeasure1, thumb));
        }
        if (!meal.strIngredient2.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient2 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient2, meal.strMeasure2, thumb));
        }
        if (!meal.strIngredient3.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient3 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient3, meal.strMeasure3, thumb));
        }
        if (!meal.strIngredient4.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient4 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient4, meal.strMeasure4, thumb));
        }
        if (!meal.strIngredient5.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient5 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient5, meal.strMeasure5, thumb));
        }
        if (!meal.strIngredient6.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient6 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient6, meal.strMeasure6, thumb));
        }
        if (!meal.strIngredient7.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient7 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient7, meal.strMeasure7, thumb));
        }
        if (!meal.strIngredient8.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient8 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient8, meal.strMeasure8, thumb));
        }
        if (!meal.strIngredient9.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient9 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient9, meal.strMeasure9, thumb));
        }
        if (!meal.strIngredient10.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient10 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient10, meal.strMeasure10, thumb));
        }
        if (!meal.strIngredient11.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient11 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient11, meal.strMeasure11, thumb));
        }
        if (!meal.strIngredient12.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient12 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient12, meal.strMeasure12, thumb));
        }
        if (!meal.strIngredient13.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient13 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient13, meal.strMeasure13, thumb));
        }
        if (!meal.strIngredient14.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient14 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient14, meal.strMeasure14, thumb));
        }
        if (!meal.strIngredient15.equals("")) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + meal.strIngredient15 + ".png";
            myIngredients.add(new Recipe(meal.strIngredient15, meal.strMeasure15, thumb));
        }

    }

    @Override
    public void addMealToFavOnClick(MealDetail meal) {
        addMealToFav(meal);
    }

    @Override
    public void addMealsToWeekPlanOnClick(MealDetail meal,WeekMeals week) {
        addMealsToWeekPlan(meal,week);
    }

    private void addMealsToWeekPlan(MealDetail meal,WeekMeals week) {
        mealPresenterInterface.addToWeekPlan(meal,week);
    }
}