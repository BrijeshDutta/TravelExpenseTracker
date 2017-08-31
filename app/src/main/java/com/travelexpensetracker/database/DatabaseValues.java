package com.travelexpensetracker.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Rini Banerjee on 31-08-2017.
 */

public class DatabaseValues {

    public static DatabaseReference getTripDetailsReference(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TripDetails");
        return databaseReference;
    }
    public static DatabaseReference getPersonDetailsReference(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PersonDetails");
        return databaseReference;
    }
}
