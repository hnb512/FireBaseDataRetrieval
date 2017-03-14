package com.example.hus.firebasedataretrieval.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hus.firebasedataretrieval.HomePageForAlreadyRegisteredUser.HomePageActivity;
import com.example.hus.firebasedataretrieval.R;

/**
 * Created by Hus on 3/9/17.
 */

public class GymInfoActivity extends MainActivity {

    TextView usernameTV;
    String userGym = " ";
    EditText otherGymET;
    String othergym;
    Button almostdone_BTN;
    boolean bool_othergym = false;
    int userguests;
    String username = " ";


    public void checkedGuest(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {

            case R.id.checkbox_1guest:

                if (checked) {

                    userguests = 1;
                }
                break;

            case R.id.checkbox_2guest:
                if (checked) {

                    userguests = 2;

                }
                break;
            case R.id.checkbox_noguest:
                if (checked) {

                    userguests = 0;
                }
                break;
        }
    }


    public void checkedGym(View view){

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {

            case R.id.checkBox_lifetime:

                if (checked) {
                    bool_othergym = false;
                    userGym = "Lifetime";
                }
                break;
            case R.id.checkBox_xsport:

                if (checked) {
                    bool_othergym = false;
                    userGym = "Xsport";
                }
                break;

            case R.id.checkBox_lafitness:
                if (checked) {

                    bool_othergym = false;
                    userGym = "La Fitness";
                }
                break;

            case R.id.checkBox_charterfitness:

                if (checked) {
                    bool_othergym = false;
                    userGym = "Charter Fitness";
                }
                break;

            case R.id.checkBox_planetfitness:
                if (checked) {

                    bool_othergym = false;

                    userGym = "Planet Fitness";
                }

                break;

            case R.id.checkBox_otherGym:
                if (checked) {

                    bool_othergym = true;
                    otherGymET.setVisibility(View.VISIBLE);

                    othergym = otherGymET.getText().toString();

                    userGym = othergym;
                }
                else
                    if(!checked)
                    {
                        bool_othergym = false;
                        otherGymET.setVisibility(View.INVISIBLE);
                        otherGymET.clearFocus();
                    }

                break;



        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forumpage2);

        usernameTV = (TextView) findViewById(R.id.txtNameProfile);
        otherGymET = (EditText) findViewById(R.id.otherGym_ET);
        almostdone_BTN = (Button) findViewById(R.id.btn_almostdone);




        username = getIntent().getStringExtra("Name");

        usernameTV.setText(username);


        almostdone_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               if(bool_othergym != true){

                   //do nothing

               }
               else {
                   userGym = otherGymET.getText().toString();
               }

                Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Username: " + username +"\nUser gym: " + userGym + "\nGuests : " + userguests,
                        Toast.LENGTH_LONG).show();



                Intent i = new Intent(GymInfoActivity.this, HomePageActivity.class);
                i.putExtra("Gym", userGym);
                i.putExtra("Guests", userguests);
                i.putExtra("Name", username.toString());
                startActivity(i);



            }
        });

















    }


}
