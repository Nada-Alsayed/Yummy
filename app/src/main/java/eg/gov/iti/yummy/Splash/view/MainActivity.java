package eg.gov.iti.yummy.Splash.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import eg.gov.iti.yummy.MainActivity2;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;

public class MainActivity extends AppCompatActivity {
    Animation animationRight, animationLeft, animationDown, animationUp, animationCornerUp, animationCornerDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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


        SharedPreferences pref = this.getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        String shP = pref.getString("USERNAME", "N/A");
        if (shP.equals("null")||shP.equals("Guest")) {
           // Toast.makeText(this, "mmmmmmmmmmmmmmmmmmm"+shP, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, Page_Sign_In.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        } else {
          //  Toast.makeText(this, ""+shP, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }
    }
}