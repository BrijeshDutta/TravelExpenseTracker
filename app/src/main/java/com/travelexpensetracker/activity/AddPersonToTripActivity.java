package com.travelexpensetracker.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.travelexpensetracker.R;
import com.travelexpensetracker.database.DatabaseValues;
import com.travelexpensetracker.model.Person;
import com.travelexpensetracker.model.Trip;

import java.util.ArrayList;
import java.util.List;

import jxl.Image;

public class AddPersonToTripActivity extends AppCompatActivity {

    //UI Variables

    ImageButton ibEditTripDetails,ibCreateNewTrip;
    LinearLayout linerLayoutAddPerson;
    EditText personName;
    TextView tvTripNameDisplay,tvTripDescriptionDisplay,tvTripDate,etTripNameTitleDisplay;
    int iCountCardCreation= 0;

    ListView listViewPerson;
    List<String> personNameList = new ArrayList<>();
    ArrayAdapter<String> adapterPersonDetails;

    //Variable to get trip details from the previous activity
    String sTripName,sTripDescription,sTripDate,sTripCurrency;

    //get data from create new trip activity
    Bundle bundleTripData;

    //UI Compoenents for adding a person to trip
    AutoCompleteTextView actvPersonName,actvPersonMobileNo,actvPersonEmailId,actvPersonDeposit;

    //Variables to store user values

    String sPersonName,sPersonMobileNo,sPersonEmailId,sPersonDeposit,sTripId,sPersonId;
    List<Person> personList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person_to_trip);

        initializeUiComponents();
        getValuesFromCreateNewTripActivity();
        setValuesInUiComponents();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddPersonInputDailog();
            }
        });

        ibEditTripDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ibCreateNewTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (personList.size()<=0){
                    Toast.makeText(AddPersonToTripActivity.this, R.string.addatleastoneperson,Toast.LENGTH_SHORT).show();
                }else {
                    processTripDataToDatabase();
                    sendValuesToTripSummaryActivity();
                }
            }
        });
    }
    private void sendValuesToTripSummaryActivity() {
        Intent intentViewTripSummaryActivity = new Intent(AddPersonToTripActivity.this, TripSummaryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("tripId",sTripId);
        intentViewTripSummaryActivity.putExtras(bundle);
        startActivity(intentViewTripSummaryActivity);
    }
    private void processTripDataToDatabase() {

        DatabaseReference databaseReferenceTrip = DatabaseValues.getTripDetailsReference();
        sTripId = databaseReferenceTrip.push().getKey();
        Trip trip = new Trip(sTripId,sTripName,sTripDescription,sTripDate,sTripCurrency);
        databaseReferenceTrip.child(sTripId).setValue(trip).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()){
                    for (int i = 0;i<personList.size();i++){
                        DatabaseReference databaseReferencePerson = DatabaseValues.getPersonDetailsReference();
                        sPersonId = databaseReferencePerson.push().getKey();
                        personList.get(i).setsPersonId(sPersonId);
                        personList.get(i).setsTripId(sTripId);
                        databaseReferencePerson.child(sPersonId).setValue(personList.get(i)).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isComplete()){
                                    Toast.makeText(AddPersonToTripActivity.this,"Added Person",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    Toast.makeText(AddPersonToTripActivity.this,"Added ",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void setValuesInUiComponents() {
        etTripNameTitleDisplay.setText(sTripName);
        tvTripNameDisplay.setText(sTripName);
        tvTripDescriptionDisplay.setText(sTripDescription);
        tvTripDate.setText(sTripDate);

    }

    private void getValuesFromCreateNewTripActivity() {
        bundleTripData = getIntent().getExtras();
        sTripName = bundleTripData.getString("tripName");
        sTripDescription = bundleTripData.getString("tripDescription");
        sTripDate =  bundleTripData.getString("tripDate");
        sTripCurrency = bundleTripData.getString("tripCurrency");

        Toast.makeText(AddPersonToTripActivity.this,"Trip details: Trip Name: "+ sTripName+ " Trip Description  :  " + sTripDescription + " Trip date : - " + sTripDate + " Trip Currency : -"+ sTripCurrency,Toast.LENGTH_SHORT ).show();

    }

    private void showAddPersonInputDailog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(AddPersonToTripActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dailog_add_person, null);  // this line
        builder.setView(v);
        initializeDailogUiComponents(v);

        // Set up the buttons
        builder.setPositiveButton(R.string.okdailogbutton,null);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button b = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getUserEnteredValuesForAddingPerson();
                        if(validateUserEnteredValues()){
                            addPersonDataToPersonObject();
                            createAddPersonCardView();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        dialog.show();
    }

    private void addPersonDataToPersonObject() {
        Person person = new Person(sPersonName,sPersonMobileNo,sPersonEmailId,sPersonDeposit);
        personList.add(0,person);
    }

    private boolean validateUserEnteredValues() {

        boolean isValid = true;
        View focusView = null;
        if(sPersonName.isEmpty()){
            actvPersonName.setError(getString(R.string.personnamerequired));
            isValid = false;
            focusView = actvPersonName;
        }
        else if(sPersonMobileNo.isEmpty()){
            actvPersonMobileNo.setError(getString(R.string.personmobilenorequied));
            isValid = false;
            focusView = actvPersonMobileNo;
        }
        return isValid;
    }

    private void getUserEnteredValuesForAddingPerson() {
        sPersonName = actvPersonName.getText().toString().trim();
        sPersonMobileNo = actvPersonMobileNo.getText().toString().trim();
        sPersonEmailId = actvPersonEmailId.getText().toString().trim();
        sPersonDeposit = actvPersonDeposit.getText().toString().trim();
    }

    private void initializeDailogUiComponents(View v) {
        actvPersonName = (AutoCompleteTextView) v.findViewById(R.id.actvPersonName);
        actvPersonMobileNo = (AutoCompleteTextView) v.findViewById(R.id.actvPersonMobileNo);
        actvPersonEmailId = (AutoCompleteTextView) v.findViewById(R.id.actvPersonEmailId);
        actvPersonDeposit = (AutoCompleteTextView) v.findViewById(R.id.actvPersonDeposit);
    }

    private void createAddPersonCardView() {
        iCountCardCreation++;
        if (iCountCardCreation>1){
            personNameList.add(personList.get(0).getsPersonName());
            adapterPersonDetails = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, personNameList);
            listViewPerson.setAdapter(adapterPersonDetails);

        }else {
            CardView card = new CardView(AddPersonToTripActivity.this);

            // Set the CardView layoutParams
            LayoutParams params = new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
            );
            params.setMargins(15,15,15,15);
            card.setLayoutParams(params);


            // Set cardView content padding
            card.setContentPadding(15, 15, 15, 15);

            // Set a background color for CardView

            // Set the CardView maximum elevation
            card.setMaxCardElevation(15);

            // Set CardView elevation
            card.setCardElevation(9);

            personNameList.add(personList.get(0).getsPersonName());
            adapterPersonDetails = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, personNameList);
            listViewPerson.setAdapter(adapterPersonDetails);

            // Initialize a new TextView to put in CardView
            TextView tv = new TextView(AddPersonToTripActivity.this);
            tv.setLayoutParams(params);
            tv.setText("Persons");

            // Put the TextView in CardView
            card.addView(tv,params);
            card.addView(listViewPerson,params);


            // Finally, add the CardView in root layout
            linerLayoutAddPerson.addView(card);

        }
    }

    private void initializeUiComponents() {
        ibEditTripDetails = (ImageButton) findViewById(R.id.ibEditTripDetails);
        linerLayoutAddPerson = (LinearLayout) findViewById(R.id.linerLayoutAddPerson);
        listViewPerson = new ListView(this);
        personNameList = new ArrayList<>();
        ibCreateNewTrip = (ImageButton) findViewById(R.id.ibCreateNewTrip);
        tvTripNameDisplay = (TextView) findViewById(R.id.tvTripNameDisplay);
        tvTripDescriptionDisplay = (TextView) findViewById(R.id.tvTripDescriptionDisplay);
        tvTripDate = (TextView) findViewById(R.id.tvTripDate);
        etTripNameTitleDisplay = (TextView) findViewById(R.id.etTripNameTitleDisplay);
        personList = new ArrayList<>();
        personList.clear();
    }


}
