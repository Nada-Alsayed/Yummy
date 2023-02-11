package eg.gov.iti.yummy.meal_details.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import eg.gov.iti.yummy.R;

public class page_item_details extends AppCompatActivity {


    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_item_details);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Intent intent = getIntent();
        String mealName = intent.getStringExtra("MealName");
        youTubePlayerView = findViewById(R.id.videoView);
        getLifecycle().addObserver(youTubePlayerView);
    }
}