package eg.gov.iti.yummy.SignUp.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.gov.iti.yummy.MainActivity2;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.db.ConcreteLocalSource;

public class Page_Sign_Up extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://yummy-app-f2567-default-rtdb.firebaseio.com/");
    TextView txtSignIn;
    EditText userName, password, confirmPassword;
    Button btnSignUp;
    //String confirm;
    ConcreteLocalSource concreteLocalSource;
    ImageView skip;
    public static final String PREF_NAME = "PREF";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sign_up);
        concreteLocalSource = new ConcreteLocalSource(getApplicationContext());
        btnSignUp = findViewById(R.id.btnSignUp);
        txtSignIn = findViewById(R.id.txtViewSignInHL);
        userName = findViewById(R.id.editTextUserName);
        password = findViewById(R.id.editTextPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        skip = findViewById(R.id.signUpSkip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("USERNAME","Guest");
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get data from edit texts
                String name = userName.getText().toString();
                String passWord = password.getText().toString();
                String confirm = confirmPassword.getText().toString();

                // check if all data exist
                if (name.isEmpty() || passWord.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(Page_Sign_Up.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!isValidText(name) || !isValidText(passWord)) {
                    Toast.makeText(Page_Sign_Up.this, "Strong user name&password is required ", Toast.LENGTH_SHORT).show();
                } else if (!passWord.equals(confirm)) {
                    Toast.makeText(Page_Sign_Up.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(name)) {
                                Toast.makeText(Page_Sign_Up.this, "User already exist", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference.child("Users").child(name).child("UserPassword").setValue(passWord);

                                //show
                                Toast.makeText(Page_Sign_Up.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        txtSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Page_Sign_In.class);
            startActivity(intent);
        });
    }


    public static boolean isValidText(String text) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (text.isEmpty()) {
            return false;
        }
        Matcher m = p.matcher(text);
        return m.matches();
    }
}


