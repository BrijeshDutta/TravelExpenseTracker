package com.travelexpensetracker.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.travelexpensetracker.R;

public class AddPersonToTripActivity extends AppCompatActivity {

    ImageButton ibEditTripDetails;

    String m_Text;
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
                startActivity(new Intent(AddPersonToTripActivity.this,CreateNewTripActivity.class));
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

        final EditText personName = new EditText(context);
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

    private void initializeUiComponents() {
        ibEditTripDetails = (ImageButton) findViewById(R.id.ibEditTripDetails);
    }


}
