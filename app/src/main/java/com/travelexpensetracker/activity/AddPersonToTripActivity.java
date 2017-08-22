package com.travelexpensetracker.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import com.travelexpensetracker.R;

import java.util.ArrayList;
import java.util.List;

import jxl.Image;

public class AddPersonToTripActivity extends AppCompatActivity {

    ImageButton ibEditTripDetails,ibCreateNewTrip;
    LinearLayout linerLayoutAddPerson;
    EditText personName;
    int iCountCardCreation= 0;

    ListView listViewPerson;
    List<String> personNameList = new ArrayList<>();
    ArrayAdapter<String> adapterPersonDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person_to_trip);

        initializeUiComponents();
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
                startActivity(new Intent(AddPersonToTripActivity.this,TripSummaryActivity.class));
            }
        });
    }

    private void showAddPersonInputDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        Context context = AddPersonToTripActivity.this;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(30, 20, 30, 0);

        final TextView title = new TextView(context);
        title.setText("Add person");
        layout.addView(title,layoutParams);

        LinearLayout.LayoutParams layoutParamsImageButton = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsImageButton.setMargins(30, 20, 30, 0);
        final ImageButton ibLoadFromContacts = new ImageButton(context);
        ibLoadFromContacts.setImageResource(R.drawable.ic_action_contacts);
        ibLoadFromContacts.setBackgroundColor(Color.TRANSPARENT);
        layout.addView(ibLoadFromContacts,layoutParamsImageButton);


        personName = new EditText(context);
        personName.setHint("Name");
        layout.addView(personName,layoutParams);

        final EditText personMobileNo = new EditText(context);
        personMobileNo.setHint("Mobile No");
        personMobileNo.setInputType(InputType.TYPE_CLASS_PHONE);
        layout.addView(personMobileNo,layoutParams);

        final EditText personEmailId = new EditText(context);
        personEmailId.setHint("Email Id (Optional)");
        personEmailId.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        layout.addView(personEmailId,layoutParams);

        final EditText personDepositAmount = new EditText(context);
        personDepositAmount.setHint("Deposit Amount (Optional)");
        personDepositAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(personDepositAmount,layoutParams);

        builder.setView(layout);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                createAddPersonCardView();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void createAddPersonCardView() {
        iCountCardCreation++;
        if (iCountCardCreation>1){
            personNameList.add(personName.getText().toString());
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

            personNameList.add(personName.getText().toString());
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
    }


}
