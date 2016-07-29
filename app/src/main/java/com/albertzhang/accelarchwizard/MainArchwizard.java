package com.albertzhang.accelarchwizard;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class MainArchwizard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static NavigationView navigationView = null;
    //Toolbar toolbar = null;
    private Toolbar toolbar;





    //private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_archwizard);



        //Set initial fragment
        FragmentArchwizardMain mainarchwizard = new FragmentArchwizardMain();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, mainarchwizard);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        getMenuInflater().inflate(R.menu.main_archwizard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) { //Change to about fragment
            AboutFragment about = new AboutFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, about);
            fragmentTransaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_archwizard) {
            //set FragmentArchwizardMain
            this.setTitle("AccelArchwizard");
            FragmentArchwizardMain mainarchwizard = new FragmentArchwizardMain();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, mainarchwizard);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_displacementtime) {
            this.setTitle("Displacement/Time Graph");
            DisplacementTimeGraph distTimeGraph = new DisplacementTimeGraph();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, distTimeGraph);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_velocitytime) {
            this.setTitle("Velocity/Time Graph");
            VelocityTimeGraph veloTimeGraph = new VelocityTimeGraph();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, veloTimeGraph);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_accelerationtime) {
            this.setTitle("Acceleration/Time Graph");
            AccelerationTimeGraph accelTimeGraph = new AccelerationTimeGraph();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, accelTimeGraph);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_jerktime) {
            this.setTitle("Coming soon after exams!");

        } else if (id == R.id.nav_forcetime) {
            this.setTitle("Coming soon after exams!");

        } else if (id == R.id.nav_worktime) {
            this.setTitle("Coming soon after exams!");

        } else if (id == R.id.nav_powertime) {
            this.setTitle("Coming soon after exams!");

        } else if (id == R.id.nav_impulsetime) {
            this.setTitle("Coming soon after exams!");

        } else if (id == R.id.nav_stats) {
            this.setTitle("Coming soon after exams!");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void floatClick(View v) {
        Snackbar.make(v, "Started tracking acceleration", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        Toast toast = Toast.makeText(getApplicationContext(), "Started tracking acceleration", Toast.LENGTH_LONG);
        toast.show();
        Intent startTracking = new Intent(this, TrackerClass.class);
        startActivity(startTracking);
        //finish(); // NEVER EVER EVER CALL THIS IN YOUR MAIN ACTIVITY IT KILLS IT AFTER THE NEXT ACTIVITY COMES IN!!!
    }

    public void rotate(View view) {

        Random r = new Random(System.currentTimeMillis());

        final RotateAnimation rotateAnimFront = new RotateAnimation(0.0f, 360.0f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        final RotateAnimation rotateAnimBack = new RotateAnimation(360.0f, 0.0f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        ImageView rotateImg = (ImageView) view;

        rotateAnimFront.setDuration(300);
        rotateAnimBack.setDuration(300);

        rotateAnimFront.setFillAfter(true);
        rotateAnimBack.setFillAfter(true);

        if(r.nextInt(2) == 1)
        {
            rotateImg.startAnimation(rotateAnimFront);
        }
        else
        {
            rotateImg.startAnimation(rotateAnimBack);
        }
    }
    /*public void setActionBarTitle(String title){
        toolbar.setText(title);
    }*/


}
