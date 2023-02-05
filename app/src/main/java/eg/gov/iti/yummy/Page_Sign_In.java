package eg.gov.iti.yummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Page_Sign_In extends AppCompatActivity {
    TextView signUp ;
    Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sign_in);
        signUp = findViewById(R.id.txtViewSignUpHL);
        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Page_Sign_Up.class);
            startActivity(intent);
        });

        signIn = findViewById(R.id.btnSignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}