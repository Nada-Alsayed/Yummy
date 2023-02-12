package eg.gov.iti.yummy.SignUp.view;

import static eg.gov.iti.yummy.SignIn.view.Page_Sign_In.PREF_NAME;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.gov.iti.yummy.MainActivity2;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.db.ConcreteLocalSource;
import eg.gov.iti.yummy.db.UserEntity;

public class Page_Sign_Up extends AppCompatActivity {
    TextView txtSignIn;
    EditText userName, password, confirmPassword;
    Button btnSignUp;
    //String confirm;
    ConcreteLocalSource concreteLocalSource;

    ImageView skip;

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

        txtSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Page_Sign_In.class);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserName(userName.getText().toString());
                userEntity.setPassword(password.getText().toString());
                String confirm = confirmPassword.getText().toString();

                if (validateUser(userEntity) && !confirm.isEmpty()) {
                    if (userEntity.getPassword().equals(confirm)) {
                            if (isValidUserName(userEntity) && isValidUserPassword(userEntity)) {
                                concreteLocalSource.registerUser(userEntity);
                                Toast.makeText(Page_Sign_Up.this, "Registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Page_Sign_In.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Page_Sign_Up.this, "Not Valid input Use chars and numbers", Toast.LENGTH_SHORT).show();
                            }
                } else {
                        Toast.makeText(Page_Sign_Up.this, "Password Not Matched Confirm Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Page_Sign_Up.this, "Fill Required Data", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public Boolean validateUser(UserEntity test) {
        if (test.getUserName().isEmpty() ||
                test.getPassword().isEmpty())
            return false;

        return true;
    }

    public static boolean isValidUserName(UserEntity user) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (user.getUserName().isEmpty()) {
            return false;
        }
        Matcher m = p.matcher(user.getUserName());
        return m.matches();
    }

    public static boolean isValidUserPassword(UserEntity user) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,15}$";
        Pattern p = Pattern.compile(regex);
        if (user.getPassword().isEmpty()) {
            return false;
        }
        Matcher m = p.matcher(user.getPassword());
        return m.matches();
    }

}