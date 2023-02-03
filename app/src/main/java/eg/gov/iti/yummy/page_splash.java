package eg.gov.iti.yummy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class page_splash extends AppCompatActivity {

    Animation animationRight, animationLeft , animationDown , animationUp , animationCornerUp,animationCornerDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_page_splash);

        animationRight = AnimationUtils.loadAnimation(this, R.anim.right_animation);
        animationLeft = AnimationUtils.loadAnimation(this, R.anim.left_animation);
        animationUp = AnimationUtils.loadAnimation(this, R.anim.up_animation);
        animationDown = AnimationUtils.loadAnimation(this, R.anim.down_animation);
        animationCornerUp = AnimationUtils.loadAnimation(this, R.anim.corner_up_animation);
        animationCornerDown = AnimationUtils.loadAnimation(this, R.anim.corner_down_animation);

        ImageView pasta = findViewById(R.id.img_pasta);
        ImageView sashimi = findViewById(R.id.img_sashimi);
        ImageView sushi = findViewById(R.id.img_sushi);
        ImageView cabbage = findViewById(R.id.img_cabbage);
        ImageView lettuce = findViewById(R.id.img_lettuce);
        ImageView taco = findViewById(R.id.img_taco);
        ImageView tomato = findViewById(R.id.img_tomato);
        ImageView pepper = findViewById(R.id.img_pepper);
        ImageView pancake = findViewById(R.id.img_pancake);

        tomato.startAnimation(animationCornerUp);
        pepper.startAnimation(animationCornerDown);
        taco.startAnimation(animationDown);
        pancake.startAnimation(animationUp);
        sushi.startAnimation(animationLeft);
        lettuce.startAnimation(animationLeft);
        cabbage.startAnimation(animationLeft);
        sashimi.startAnimation(animationRight);
        pasta.startAnimation(animationRight);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(page_splash.this, page_sign_up.class);
                startActivity(intent);
            }
        }, 5000);
    }


}