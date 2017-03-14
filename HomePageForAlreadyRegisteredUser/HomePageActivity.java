package com.example.hus.firebasedataretrieval.HomePageForAlreadyRegisteredUser;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hus.firebasedataretrieval.R;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /*Fragment settings for drawer layout*/
    Fragment currentFragment = null; //Holds fragment on selected item id
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //use this for something
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        // fab.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    public void findbuddyClick(View view) {


        currentFragment = new ProfilePage();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    TextView user_name;
    String user_guests;
    String user_gym;


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        //Initialise fragment transaction
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        currentFragment = new ProfilePage();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content_main, currentFragment);
        ft1.commit();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //user_name.setText(getIntent().getExtras().getString("Name"));

        switch (id) {

            case R.id.nav_profile: {
                //go to profile
                String gym = getIntent().getStringExtra("Gym");
                String guests = getIntent().getStringExtra("Guests");
                String user = getIntent().getStringExtra("Name");
                Toast.makeText(getApplicationContext(), user + "'s profile is clicked", Toast.LENGTH_SHORT).show();

                //make bundle and send data from gym,guests, and name over to Profile page
                currentFragment = new ProfilePage();
                Bundle bundle = new Bundle();
                bundle.putString("name", user);
                bundle.putString("gym", gym);
                bundle.putString("guests", guests);
                ProfilePage profilePage = new ProfilePage();
                profilePage.setArguments(bundle);


                break;
                //i.putExtra("Name", user_name.toString());
            }
            case R.id.nav_findbuddy: {


                Toast.makeText(getApplicationContext(), "Finding buddy ", Toast.LENGTH_SHORT).show();
                currentFragment = new FindBuddyPage();


                break;
            }


            case R.id.nav_BecomeTrainer: {

                break;

            }
            case R.id.nav_manage: {

                break;

            }
            case R.id.nav_manageaccount: {

                break;

            }
            case R.id.nav_logout: {

                break;

            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}







