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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import eg.gov.iti.yummy.MainActivity2;
import eg.gov.iti.yummy.R;

import eg.gov.iti.yummy.db.ConcreteLocalSource;

public class Page_Sign_In extends AppCompatActivity {
    private GoogleSignInClient client;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://yummy-app-f2567-default-rtdb.firebaseio.com/");
    ImageView googleImg;
    TextView txtSignUp;
    Button btnSignIn;
    ConcreteLocalSource concreteLocalSource;
    EditText nameUser, passwordUser;
    ImageView skip;

    public static final String PREF_NAME = "PREF";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sign_in);
        concreteLocalSource = new ConcreteLocalSource(getApplicationContext());

        txtSignUp = findViewById(R.id.txtViewSignUpHL);
        nameUser = findViewById(R.id.editTxtSignInUserName);
        passwordUser = findViewById(R.id.editTextSignInPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        skip = findViewById(R.id.signInSkip);
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
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameUser.getText().toString();
                String password = passwordUser.getText().toString();
                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Page_Sign_In.this, "Fill The Required Data", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(password)) {
                                final String fireName = snapshot.child(password).child("UserName").getValue(String.class);

                                if (fireName.equals(name)) {
                                    Toast.makeText(Page_Sign_In.this, "Logged In", Toast.LENGTH_SHORT).show();

                                    SharedPreferences pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("USERNAME", fireName);
                                    editor.commit();

                                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Page_Sign_In.this, "Wrong Data", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Page_Sign_In.this, "Wrong Data", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        txtSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),eg.gov.iti.yummy.SignUp.view.Page_Sign_Up.class);
            startActivity(intent);
        });
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
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) { Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        } );

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}
}
