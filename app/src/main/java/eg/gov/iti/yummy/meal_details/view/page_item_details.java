package eg.gov.iti.yummy.meal_details.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import eg.gov.iti.yummy.CalenderDialog;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.db.ConcreteLocalSource;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenter;
import eg.gov.iti.yummy.meal_details.presenter.MealPresenterInterface;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.RootMealDetail;
import eg.gov.iti.yummy.model.WeekPlan;
import eg.gov.iti.yummy.network.API_Client;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class page_item_details extends AppCompatActivity implements MealViewInterface, OnClick, DatePickerDialog.OnDateSetListener {

    YouTubePlayerView youTubePlayerView;
    ImageView mealPic;
    String shP;
    WeekPlan weekPlan;
    String date2;
    TextView MealName, MealOrigin, steps;
    ArrayList<Recipe> myIngredients;
    MealPresenterInterface mealPresenterInterface;
    RecyclerView recyclerView;
    IngredientsAdapter adapter;
    ConcreteLocalSource cls;
    RootMealDetail rootMealDetail;
    Button btnAddToFav, btnAddToWeekPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_item_details);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        MealName = findViewById(R.id.mealName);
        MealOrigin = findViewById(R.id.mealOrigin);
        mealPic = findViewById(R.id.mealImage);
        steps = findViewById(R.id.step1);
        myIngredients = new ArrayList<>();
        rootMealDetail = new RootMealDetail();

        Intent intent = getIntent();
        String tableName = intent.getStringExtra("tableType");
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
        shP = pref.getString("USERNAME", "N/A");

        mealPresenterInterface = new MealPresenter(Repository.getInstance(API_Client.getInstance(getApplicationContext()), ConcreteLocalSource.getInstance(getApplicationContext()), getApplicationContext()), this);

        if (isNetworkAvailable(getApplicationContext())) {
            mealPresenterInterface.getSpecificMeal(mealName);
            Toast.makeText(this, " connected", Toast.LENGTH_SHORT).show();
        } else if (tableName.equals("favourite")) {
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
            Toast.makeText(this, "not connected favourite", Toast.LENGTH_SHORT).show();
        } else {
            mealPresenterInterface.getOffMealWeek(mealName).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<WeekPlan>() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@io.reactivex.rxjava3.annotations.NonNull WeekPlan weekPlan) {
                            Log.e("TAG", "onChanged: kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk ");
                            MealName.setText(weekPlan.strMeal);
                            MealOrigin.setText(weekPlan.strArea);
                            prepareOffIngredientsWeek(weekPlan);
                            adapter = new IngredientsAdapter(myIngredients, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            String thumb = "https://www.themealdb.com/images/ingredients/" + weekPlan.strIngredient1 + ".png";
                            myIngredients.add(new Recipe(weekPlan.strIngredient1, weekPlan.strMeasure1, thumb));
                            steps.setText(weekPlan.strInstructions);
                            Glide.with(getApplicationContext())
                                    .load(weekPlan.strMealThumb)
                                    .into(mealPic);
                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                        }
                    });

            Toast.makeText(this, "not connected favourite", Toast.LENGTH_SHORT).show();
        }

        btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMealToFavOnClick(rootMealDetail.meals.get(0));
                insertMealInFirebase(rootMealDetail.meals.get(0), shP);
            }
        });

        btnAddToWeekPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment calenderDialog = new CalenderDialog();
                calenderDialog.show(getSupportFragmentManager(), "Calender");
                weekPlan = new WeekPlan(
                        rootMealDetail.meals.get(0).idMeal,
                        rootMealDetail.meals.get(0).strMeal,
                        rootMealDetail.meals.get(0).strCategory,
                        rootMealDetail.meals.get(0).strArea,
                        rootMealDetail.meals.get(0).strInstructions,
                        rootMealDetail.meals.get(0).strMealThumb,
                        rootMealDetail.meals.get(0).strIngredient1, rootMealDetail.meals.get(0).strIngredient2,
                        rootMealDetail.meals.get(0).strIngredient3, rootMealDetail.meals.get(0).strIngredient4,
                        rootMealDetail.meals.get(0).strIngredient5, rootMealDetail.meals.get(0).strIngredient6,
                        rootMealDetail.meals.get(0).strIngredient7, rootMealDetail.meals.get(0).strIngredient8,
                        rootMealDetail.meals.get(0).strIngredient9, rootMealDetail.meals.get(0).strIngredient10,
                        rootMealDetail.meals.get(0).strIngredient11, rootMealDetail.meals.get(0).strIngredient12,
                        rootMealDetail.meals.get(0).strIngredient13, rootMealDetail.meals.get(0).strIngredient14,
                        rootMealDetail.meals.get(0).strIngredient15, rootMealDetail.meals.get(0).strIngredient16,
                        rootMealDetail.meals.get(0).strIngredient17, rootMealDetail.meals.get(0).strIngredient18,
                        rootMealDetail.meals.get(0).strIngredient19, rootMealDetail.meals.get(0).strIngredient20);

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

    @Override
    public void addMealInFirebase(MealDetail mealDetail, String key) {
        mealPresenterInterface.addMealToFavFirebase(mealDetail, key);
    }

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

    @Override
    public void addMealInWeekPlanFirebase(WeekPlan mealDetail, String key) {
        mealPresenterInterface.addMealToWeekPlanFirebase(mealDetail, key);
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

    void prepareOffIngredientsWeek(WeekPlan meal) {
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
    public void addMealsToWeekPlanOnClick(WeekPlan meal) {
        addMealsToWeekPlan(meal);
    }

    @Override
    public void insertMealInFirebase(MealDetail mealDetail, String key) {
        addMealInFirebase(mealDetail, key);
    }

    @Override
    public void insertMealInWeekPlanFirebase(WeekPlan mealDetail, String key) {
        addMealInWeekPlanFirebase(mealDetail, key);
    }

    private void addMealsToWeekPlan(WeekPlan meal) {
        mealPresenterInterface.addToWeekPlan(meal);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        if (date.contains("Saturday")) {
            weekPlan.sat = "1";
            Toast.makeText(this, "" + weekPlan.sat, Toast.LENGTH_SHORT).show();
        } else {
            weekPlan.sat = "0";
        }
        if (date.contains("Sunday")) {
            weekPlan.sun = "1";
        }
        // Toast.makeText(this, ""+weekPlan.sat, Toast.LENGTH_SHORT).show();
        else {
            weekPlan.sun = "0";
        }
        if (date.contains("Monday")) {
            weekPlan.mon = "1";

        } else {
            weekPlan.mon = "0";
        }
        if (date.contains("Tuesday")) {
            weekPlan.tues = "1";
            // Toast.makeText(this, ""+weekPlan.sat, Toast.LENGTH_SHORT).show();
        } else {
            weekPlan.tues = "0";
        }
        if (date.contains("Thursday")) {
            weekPlan.thurs = "1";
            //  Toast.makeText(this, ""+weekPlan.sat, Toast.LENGTH_SHORT).show();
        } else {
            weekPlan.thurs = "0";
        }
        if (date.contains("Friday")) {
            weekPlan.fri = "1";
            //  Toast.makeText(this, ""+weekPlan.sat, Toast.LENGTH_SHORT).show();
        } else {
            weekPlan.fri = "0";
        }
        if (date.contains("Wednesday")) {
            weekPlan.wed = "1";
            //Toast.makeText(this, ""+weekPlan.sat, Toast.LENGTH_SHORT).show();

        } else {
            weekPlan.wed = "0";
        }
        addMealsToWeekPlanOnClick(weekPlan);
        insertMealInWeekPlanFirebase(weekPlan, shP);

        /*if(date.contains("Saturday")){
            mealPresenterInterface.updateSat("1", weekPlan.idMeal);
            Toast.makeText(this, ""+weekPlan.sat, Toast.LENGTH_SHORT).show();}
        else
            mealPresenterInterface.updateSat("0", weekPlan.idMeal);
        if(date.contains("Sunday"))
            mealPresenterInterface.updateSun("1", weekPlan.idMeal);
        else
            mealPresenterInterface.updateSun("0", weekPlan.idMeal);
        if(date.contains("Monday"))
            mealPresenterInterface.updateMon("1", weekPlan.idMeal);
        else
            mealPresenterInterface.updateMon("0", weekPlan.idMeal);
        if(date.contains("Tuesday")){
            mealPresenterInterface.updateTues("1", weekPlan.idMeal);
            Toast.makeText(this, ""+weekPlan.tues, Toast.LENGTH_SHORT).show();
        }
        else
            mealPresenterInterface.updateTues("0",weekPlan.idMeal);
        if(date.contains("Thursday"))
            mealPresenterInterface.updateThurs("1", weekPlan.idMeal);
        else
            mealPresenterInterface.updateThurs("0", weekPlan.idMeal);
        if(date.contains("Friday"))
            mealPresenterInterface.updateFri("1", weekPlan.idMeal);
        else
            mealPresenterInterface.updateFri("0", weekPlan.idMeal);
        if(date.contains("Wednesday"))
            mealPresenterInterface.updateWed("1", weekPlan.idMeal);
        else
            mealPresenterInterface.updateWed("0", weekPlan.idMeal);*/

    }

}