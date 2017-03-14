package com.example.hus.firebasedataretrieval.HomePageForAlreadyRegisteredUser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hus.firebasedataretrieval.R;

/**
 * Created by Hus on 3/10/17.
 */

public class ProfilePage extends Fragment {

    TextView nameTV, guestsTV, gymTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        nameTV = (TextView) view.findViewById(R.id.profilepage_username_TV);
        guestsTV = (TextView) view.findViewById(R.id.profilepage_guests_TV);
        gymTV = (TextView) view.findViewById(R.id.profilepage_usergym_TV);


        Bundle bundle = this.getArguments();
        String username = bundle.getString("name");
        String usergym = bundle.getString("gym");
        String userguests = bundle.getString("guests");

        nameTV.setText(username);
        guestsTV.setText(usergym);
        gymTV.setText(usergym);



        //nameTV.setText(getIntent().getExtras().getString("Name"));

    }




    }



