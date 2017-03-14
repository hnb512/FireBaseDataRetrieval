package com.example.hus.firebasedataretrieval;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hus.firebasedataretrieval.Login.MainActivity;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

/**
 * Created by Hus on 3/13/17.
 */

public class PracticeFacebookActivity extends AppCompatActivity {

    TextView nameTV, emailTV;
    LoginButton logoutBTN;
    ImageView propicIMG;
    private FirebaseAuth mAuth;
    private CallbackManager mcallbackManager;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practicelogin_homepage);

        mAuth = FirebaseAuth.getInstance();
        nameTV = (TextView) findViewById(R.id.name_facebook_tv);
        emailTV = (TextView) findViewById(R.id.email_facebook_tv);
        logoutBTN = (LoginButton) findViewById(R.id.fb_logout_BTN);
        propicIMG = (ImageView) findViewById(R.id.fb_propic_IMG);


        logoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signOut();
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();


                if(user != null){

                    for(UserInfo userInfo : user.getProviderData()){
                        Log.d("TAG", userInfo.getProviderId().toString());
                    }

                    nameTV.setText(user.getDisplayName());
                    emailTV.setText(user.getEmail());
                    Picasso.with(PracticeFacebookActivity.this).load(user.getPhotoUrl()).into(propicIMG);

                }
                else
                {
                    Intent i = new Intent(PracticeFacebookActivity.this, MainActivity.class);
                    i.putExtra("logout", true);
                    startActivity(i);
                    finish();
                }

            }


        };


    }
}
