package eg.gov.iti.yummy.SignIn.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import eg.gov.iti.yummy.Home.View.page_home;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignUp.View.page_sign_up;

public class page_sign_in extends AppCompatActivity {

    TextView signUp ;

    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sign_in);
        signUp = findViewById(R.id.txtViewSignUpHL);
        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), page_sign_up.class);
            startActivity(intent);
        });

        signIn = findViewById(R.id.btnSignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), page_home.class);
                startActivity(intent);
            }
        });
    }
}