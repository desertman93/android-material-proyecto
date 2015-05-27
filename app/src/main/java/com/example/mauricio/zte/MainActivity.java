package com.example.mauricio.zte;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import mauroxdev.activities.MapsActivity;
import mauroxdev.fragments.HomeFragment;
import mauroxdev.fragments.PlusOneFragment;
import mauroxdev.fragments.newsFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerCallbacks, HomeFragment.OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    /*private String TAG;
    private static final String STATE_TAG = "state_tag";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        if(savedInstanceState != null) {
            TAG = savedInstanceState.getString(STATE_TAG);
        }*/

        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("Mauricio Salustio", "mauro.xdev@gmail.com", BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
    }
/*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_TAG, TAG);
        super.onSaveInstanceState(outState);
    }*/

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        vista(position);
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
    }

    private void vista(int position) {
        Fragment fragment = null;
        String TAG = null;
        String title = getString(R.string.app_name);

        switch (position) {
            case 0:
                fragment = new PlusOneFragment();
                TAG = "plus";
                title = "Home xd";
                break;
            case 1:
                fragment = new HomeFragment();
                title = "klk 2";
                TAG = "home";
                break;
            case 2:
                fragment = new newsFragment();
                title = "klk 3";
                TAG = "news";
                break;
            case 3:
                startActivity(new Intent(this, MapsActivity.class));
                title = "klk 3";
                TAG = null;
                break;
            default:
                break;
        }

        Fragment mFragment = getSupportFragmentManager().findFragmentByTag(TAG);

        if(mFragment!= null){
            fragment = mFragment;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment, TAG).commit();


            // set the toolbar title
            //getSupportActionBar().setTitle(title);
        }
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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


    @Override
    public void onFragmentInteraction(String id) {

    }
}
