package eg.gov.iti.yummy.DishDetails.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

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

        youTubePlayerView = findViewById(R.id.videoView);
        getLifecycle().addObserver(youTubePlayerView);

    }
}