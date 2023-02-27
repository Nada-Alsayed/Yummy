package eg.gov.iti.yummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.favourite.view.Page_Favourite;
import eg.gov.iti.yummy.home.home.view.Page_Home;
import eg.gov.iti.yummy.profile.view.Page_Profile;
import eg.gov.iti.yummy.search.view.Page_Search;


public class MainActivity2 extends AppCompatActivity {
    ConstraintLayout layout_main;
    BottomNavigationView navView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        layout_main=findViewById(R.id.drawer_layout);
        navView=findViewById(R.id.bottom_navigation);

        SharedPreferences pref = this.getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        String shP = pref.getString("USERNAME", "N/A");

        NavController navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView,navController);


        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.page_Favourite){
                    if(shP.equals("Guest")){
                        Toast.makeText(getApplicationContext(),"You need to login To check your favourites",Toast.LENGTH_LONG).show();
                    }else{
                        navController.navigate(R.id.page_Favourite);
                    }
                }else if(item.getItemId()==R.id.page_Week_Plan) {
                    if (shP.equals("Guest")) {
                        Toast.makeText(getApplicationContext(), "You need to login To check your Week Plan", Toast.LENGTH_LONG).show();
                    } else {
                        navController.navigate(R.id.page_Week_Plan);
                    }
                }else if(item.getItemId()==R.id.page_Profile) {
                    if (shP.equals("Guest")) {
                        Toast.makeText(getApplicationContext(), "You need to login To check your Profile", Toast.LENGTH_LONG).show();
                    } else {
                        navController.navigate(R.id.page_Profile);
                    }
                }
                else if(item.getItemId()==R.id.page_Home){
                    navController.navigate(R.id.page_Home);
                }
                else if(item.getItemId()==R.id.page_Search){
                    navController.navigate(R.id.page_Search);
                }

                return true;
            }
        });
    }
}