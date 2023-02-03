package eg.gov.iti.yummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class page_sign_up extends AppCompatActivity {

    TextView signIn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sign_up);
        signIn = findViewById(R.id.txtViewSignInHL);
        signIn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),page_sign_in.class);
            startActivity(intent);
        });
    }
}