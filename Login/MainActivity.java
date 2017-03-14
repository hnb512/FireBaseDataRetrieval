package com.example.hus.firebasedataretrieval.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hus.firebasedataretrieval.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//import com.firebase.ui.auth.AuthUI;



public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private String TAG = "MAIN_ACTIVITY";
    private static int RC_signin = 0;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    Button newaccountBTN, loginBTN, googleBTN, signoutBTN;
    EditText usernameET, passwordET;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_KEY = "LoginPrefs";
    CheckBox cbLoggedin;
    SharedPreferences sharedPrefsLogin;



    // Boolean firstlaunch = true;
    //Boolean savedlaunch = true;
    //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Firebase.setAndroidContext(this);

        firebaseAuth = FirebaseAuth.getInstance();


        //sharedPrefsLogin = this.getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);

        usernameET = (EditText) findViewById(R.id.username_signin);
        passwordET = (EditText) findViewById(R.id.password_signin);
        newaccountBTN = (Button) findViewById(R.id.newAccnt_btn);
        firebaseAuth = FirebaseAuth.getInstance();
        loginBTN = (Button) findViewById(R.id.login_BTN);
        //googleBTN = (Button) findViewById(R.id.google_sign_in_button);
        signoutBTN = (Button) findViewById(R.id.sign_out_button);




       /* authStateListener = new FirebaseAuth.AuthStateListener(){


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null)
                {
                    Log.d("AUTH", "user logged in: " + user.getEmail());
                }
                else {
                    Log.d("AUTH", "user logged out ");
                }

                GoogleSignInOptions gso = new GoogleSignInOptions.Builder
                        (GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                googleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                .enableAutoManage(MainActivity.this, MainActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

            }

        };
        */




        signoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        newaccountBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, RegisterationActivity.class);
                startActivity(i);




            }
        });


        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* if(cbLoggedin.isChecked() == true){
                    sharedPrefsLogin.edit().putBoolean("StayLoggedIn", true).commit();
                }else{
                    sharedPrefsLogin.edit().putBoolean("StayLoggedIn", false).commit();
                }*/

                if (usernameET.getText().toString().isEmpty() || passwordET.getText().toString().isEmpty()) {
                    if (usernameET.getText().toString().isEmpty()) {
                        usernameET.setError("Cannot be empty");
                    }
                    if (passwordET.getText().toString().isEmpty()) {
                        passwordET.setError("Cannot be empty");
                    }
                } else {


                    final ProgressDialog progressDialog = ProgressDialog.show
                            (MainActivity.this, "Please wait..", "Proccessing.. ", true);
                    (firebaseAuth.signInWithEmailAndPassword
                            (usernameET.getText().toString(), passwordET.getText().toString()))
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    //if task is successfull, direct to forum page or home page
                                    //if first time user is using the app, direct to forum page
                                    progressDialog.dismiss();

                                    // savedlaunch = prefs.getBoolean("firstlaunch", true);
                                    if (task.isSuccessful()) {

                                        Log.d("Auth", "Success");


                                       // sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
                                       // if(sharedPreferences.getBoolean("StayLoggedIn", false) != false) {
                                            //Load MainActivity since prefs resulted in 'true'
                                            //Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                                           // startActivity(intent);

                                            Toast.makeText(MainActivity.this, "Log in successfull!",
                                                    Toast.LENGTH_LONG).show();
                                       // }
                                       // else
                                       // {
                                         //   if(sharedPreferences.getBoolean("StayLoggedIn", false) == false) {

                                                Intent i = new Intent(MainActivity.this, ForumActivity.class);
                                                i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                                startActivity(i);
                                          //  }

                                        //}

                                        //if(savedlaunch = true){

                                        // prefs.edit().putBoolean("firstlaunch", false).apply();
                                        //firstlaunch = false;
                                        // }
                                        //if(firstlaunch != true){

                                        //Intent i = new Intent(MainActivity.this, HomePageActivity.class);
                                        //i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                        // startActivity(i);

                                        // }


                                    } else {

                                        Log.d("Auth", "Failed");
                                        Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }


                                }
                            });


                }
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


  /*  @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.google_sign_in_button:
                signin();
                break;
            case R.id.sign_out_button:
                signout();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_signin){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()){

                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthwithGoogle(account);
            }
        }
    }

    private void firebaseAuthwithGoogle(GoogleSignInAccount account) {

        AuthCredential authCred = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(authCred).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Log.d("Auth", "SignInWithCredential: oncomplete: " + task.isSuccessful());

            }
        });

    }*/

    /*private void signout() {

        firebaseAuth.getInstance().signOut();
    }


    private void signin() {

        Intent sighinIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(sighinIntent, RC_signin);
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }*/

}







