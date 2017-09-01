package com.travelexpensetracker.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.travelexpensetracker.R;

public class TripSummaryActivity extends AppCompatActivity {

    //get data from create new trip activity
    Bundle bundleTripId;
    String sTripId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getTripId();
        super.setTitle(sTripId);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getTripId() {
        bundleTripId = getIntent().getExtras();
        sTripId = bundleTripId.getString("tripId");

        Toast.makeText(TripSummaryActivity.this,"Trip ID : "+ sTripId,Toast.LENGTH_SHORT ).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trip_summary, menu);

        // return true so that the menu pop up is opened
        return true;
    }
}
