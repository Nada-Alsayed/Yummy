package eg.gov.iti.yummy.SignIn.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import eg.gov.iti.yummy.SignUp.view.Page_Sign_Up;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.users;

public class Page_Sign_In extends AppCompatActivity {

    private GoogleSignInClient client;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ImageView googleImg;
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

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://yummy-7d6e4-default-rtdb.firebaseio.com/");

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this,options);
        googleImg = findViewById(R.id.googleImgSignIn);
        googleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = client.getSignInIntent();
                startActivityForResult(i,123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                auth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if(task.isSuccessful()){
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
                        }else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }catch (ApiException e){
                e.printStackTrace();
            }
        }
    }
}