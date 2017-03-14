package com.example.hus.firebasedataretrieval.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hus.firebasedataretrieval.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterationActivity extends AppCompatActivity {

    private EditText txtEmail, txtname, txtPW;
    private FirebaseAuth firebaseAuth;
    private Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        txtEmail = (EditText) findViewById(R.id.txt_email_register);
        txtname = (EditText) findViewById(R.id.txtname_register);
        txtPW = (EditText) findViewById(R.id.txtPW_register);
        btnReg = (Button) findViewById(R.id.register_btn);
        firebaseAuth = FirebaseAuth.getInstance();


        btnReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(txtEmail.getText().toString().isEmpty() || txtPW.getText().toString().isEmpty()) {
                    if (txtEmail.getText().toString().isEmpty()) {
                        txtEmail.setError("Cannot be empty");
                    }
                    if (txtPW.getText().toString().isEmpty()) {
                        txtPW.setError("Cannot be empty");
                    }


                }else {

                    Log.d("Email" , txtEmail.getText().toString());
                    Log.d("Password", txtPW.getText().toString());
                    Log.d("Name", txtname.getText().toString());

                    firebaseAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(),
                            txtPW.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {



                                    if (task.isSuccessful()) {

                                        Log.d("Auth", "success");

                                        Toast.makeText(RegisterationActivity.this, "Registeration Successfull!", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(RegisterationActivity.this, MainActivity.class);
                                        //i.putExtra("Email",firebaseAuth.getCurrentUser().getEmail());
                                        //i.putExtra("Name", txtname.getText().toString());
                                        startActivity(i);
                                       // i.putExtra("Email",firebaseAuth.getCurrentUser().getEmail());


                                    } else {
                                        Toast.makeText(RegisterationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                        Toast.makeText(RegisterationActivity.this, "Registeration UnSuccessfull!", Toast.LENGTH_LONG).show();
                                    }


                                }
                            });

                }


            }
        });


    }

}
