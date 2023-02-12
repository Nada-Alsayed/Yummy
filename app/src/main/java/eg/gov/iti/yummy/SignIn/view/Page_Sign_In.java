package eg.gov.iti.yummy.SignIn.view;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import eg.gov.iti.yummy.MainActivity2;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignUp.view.Page_Sign_Up;
import eg.gov.iti.yummy.db.ConcreteLocalSource;
import eg.gov.iti.yummy.db.UserEntity;
import eg.gov.iti.yummy.users;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class Page_Sign_In extends AppCompatActivity {
    private GoogleSignInClient client;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ImageView googleImg;
    TextView txtSignUp;
    Button btnSignIn;
    ConcreteLocalSource concreteLocalSource;
    EditText nameUser, passwordUser;

    public static final String PREF_NAME = "PREF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sign_in);
        concreteLocalSource = new ConcreteLocalSource(getApplicationContext());
        txtSignUp = findViewById(R.id.txtViewSignUpHL);
        nameUser = findViewById(R.id.editTxtSignInUserName);
        passwordUser = findViewById(R.id.editTextSignInPassword);

        txtSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Page_Sign_Up.class);
            startActivity(intent);
        });
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameUser.getText().toString();
                String password = passwordUser.getText().toString();
                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Page_Sign_In.this, "Fill The Required Data", Toast.LENGTH_SHORT).show();
                } else {
                    Observable<Boolean> userEntity = concreteLocalSource.login(name, password);
                    userEntity.observeOn(AndroidSchedulers.mainThread()).subscribe(
                            item->{
                                if (item==false) {
                                    Toast.makeText(Page_Sign_In.this, "Failed", Toast.LENGTH_SHORT).show();
                                } else {
                                    SharedPreferences pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("USERNAME",nameUser.getText().toString());
                                    editor.commit();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                                    startActivity(intent);
                                }
                            }
                    );

                }
            }

        });

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://yummy-7d6e4-default-rtdb.firebaseio.com/");

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this, options);
        googleImg = findViewById(R.id.googleImgSignIn);
        googleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = client.getSignInIntent();
                startActivityForResult(i, 123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                auth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            users user1 = new users();
                            assert user1 != null;
                            user1.setUserId(user.getUid());
                            user1.setUserName(user.getDisplayName());
                            user1.setProfilePic(user.getPhotoUrl().toString());
                            user1.setEmail(user.getEmail());
                            database.getReference().child("users").child(user.getUid()).setValue(user1);
                            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}