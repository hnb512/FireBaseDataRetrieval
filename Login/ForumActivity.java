package com.example.hus.firebasedataretrieval.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hus.firebasedataretrieval.R;

public class ForumActivity extends MainActivity {


    TextView emailtxt, nametxt;
    EditText userage;
    CheckBox maleCB, femaleCB;
    Button continueBTN;
    Boolean MALE = null;
    Boolean FEMALE = null;
    String userGender = "";
    int userageTEXT ;
    String usernametext;
    String useragetext;


    public void selectItem(View view){

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()){

            case R.id.checkBox_male:

                if(checked)
                {
                    MALE = true;
                    userGender = "Male";

                }
                break;
            case R.id.checkBox_female:

                if(checked)
                {
                    FEMALE = true;
                    userGender = "Female";

                }
                break;



        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forumpage1);

        emailtxt = (TextView) findViewById(R.id.textView14);
        continueBTN = (Button) findViewById(R.id.continue_btn);
        maleCB = (CheckBox) findViewById(R.id.checkBox_male);
        femaleCB = (CheckBox) findViewById(R.id.checkBox_female);
        //noguestsCB = (CheckBox) findViewById(R.id.checkbox_noguest);

        //retrieving email
        emailtxt.setText(getIntent().getExtras().getString("Email"));
        nametxt = (EditText) findViewById(R.id.editText_name);
        userage = (EditText) findViewById(R.id.editText_userAge);

        //name inputted by user


        usernametext = nametxt.getText().toString();
        useragetext = userage.getText().toString();



        continueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernametext = nametxt.getText().toString();
                useragetext = userage.getText().toString();


                Toast.makeText(getApplicationContext(),"Continue clicked", Toast.LENGTH_LONG).show();

                Toast.makeText(getApplicationContext(),"Name : " + usernametext + "\nAge : " + useragetext +
                                "\nGender : " + userGender,
                        Toast.LENGTH_LONG).show();

                //change to forum_fragment_page2
                Intent i = new Intent(ForumActivity.this, GymInfoActivity.class);
                i.putExtra("Name", usernametext);
                startActivity(i);
                //myIntent.putExtra("key", value); //Optional parameters
                //i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                //startActivity(i);









            }
        });


    }
}
