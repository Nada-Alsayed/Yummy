package eg.gov.iti.yummy.profile.view;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import eg.gov.iti.yummy.MainActivity2;
import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;

public class Page_Profile extends Fragment {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    FirebaseAuth firebaseAuth;
    ImageView imgFav,imgWeekPlan,profilePic,logOutImg;
    TextView txtFav,txtWeekPlan,logout,name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page__profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        logout = view.findViewById(R.id.txtlogout);
        logOutImg = view.findViewById(R.id.imglogout);
        imgWeekPlan = view.findViewById(R.id.imgcalendar);
        txtWeekPlan = view.findViewById(R.id.txtWeekPlan);
        imgFav = view.findViewById(R.id.imgfav);
        txtFav = view.findViewById(R.id.txtfav);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getContext(),gso);
        name = view.findViewById(R.id.txtName);
        profilePic = view.findViewById(R.id.imgProfile);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            Glide.with(getContext())
                    .load(acct.getPhotoUrl())
                    .into(profilePic);
        }

        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Navigation.findNavController(view).navigate(R.id.action_page_Profile_to_page_Favourite);
            }
        });
        txtFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Navigation.findNavController(view).navigate(R.id.action_page_Profile_to_page_Favourite);
            }
        });
        imgWeekPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Navigation.findNavController(view).navigate(R.id.action_page_Profile_to_page_Week_Plan);
            }
        });

        txtWeekPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Navigation.findNavController(view).navigate(R.id.action_page_Profile_to_page_Week_Plan);
            }
        });
        logOutImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }
    void signOut(){
                Intent intent = new Intent(getActivity(), Page_Sign_In.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
    }

}
