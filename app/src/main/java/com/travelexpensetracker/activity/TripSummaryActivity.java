package com.travelexpensetracker.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.travelexpensetracker.R;
import com.travelexpensetracker.database.DatabaseValues;
import com.travelexpensetracker.model.Trip;

public class TripSummaryActivity extends AppCompatActivity {

    //get data from create new trip activity
    Bundle bundleTripDetails;
    String sTripId,sTripName,sTripDate;

    TextView tvDisplayTripName,tvDisplayTripDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(null);
        setContentView(R.layout.activity_trip_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getTripDetails();
        initializeUiCompoenets();
        getTripDetailsFromDatabase();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initializeUiCompoenets() {
        tvDisplayTripName = (TextView) findViewById(R.id.tvDisplayTripName);
        tvDisplayTripDate = (TextView) findViewById(R.id.tvDisplayTripDate);
    }

    private void getTripDetailsFromDatabase() {
        DatabaseReference databaseReferenceTripDetails = DatabaseValues.getTripDetailsReference();
        databaseReferenceTripDetails.orderByChild("sTripId").equalTo(sTripId);
        databaseReferenceTripDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot tripSnapshot : dataSnapshot.getChildren()){
                        Trip trip = tripSnapshot.getValue(Trip.class);
                        if (trip.getsTripId().equalsIgnoreCase(sTripId)) {
                            sTripName = trip.getsTripName();
                            sTripDate = trip.getsTripDate();
                            setTripDetails();
                            Toast.makeText(TripSummaryActivity.this, "From Database " + sTripName, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setTripDetails() {
        tvDisplayTripName.setText(sTripName);
        tvDisplayTripDate.setText(sTripDate);
    }

    private void getTripDetails() {
        bundleTripDetails = getIntent().getExtras();
        sTripId = bundleTripDetails.getString("tripId");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trip_summary, menu);

        // return true so that the menu pop up is opened
        return true;
    }
}
