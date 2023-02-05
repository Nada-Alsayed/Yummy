package eg.gov.iti.yummy.SignUp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.View.page_sign_in;

public class page_sign_up extends AppCompatActivity {

    TextView signIn ;

    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sign_up);
        signIn = findViewById(R.id.txtViewSignInHL);
        signIn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), page_sign_in.class);
            startActivity(intent);
        });

        signUp = findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), page_sign_in.class);
                startActivity(intent);
            }
        });
    }
}