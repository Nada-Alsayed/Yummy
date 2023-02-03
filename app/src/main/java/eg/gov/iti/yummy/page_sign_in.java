package eg.gov.iti.yummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class page_sign_in extends AppCompatActivity {

    TextView signUp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sign_in);
        signUp = findViewById(R.id.txtViewSignUpHL);
        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),page_sign_up.class);
            startActivity(intent);
        });
    }
}