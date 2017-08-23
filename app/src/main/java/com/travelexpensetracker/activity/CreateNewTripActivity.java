package com.travelexpensetracker.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.travelexpensetracker.R;
import com.travelexpensetracker.utility.Utility;

import java.util.Calendar;

public class CreateNewTripActivity extends AppCompatActivity {

    //Ui componenets
    ImageButton createNewTrip,ibDatePicker;
    TextView tvTripDate;
    AutoCompleteTextView actvTripName,actvTripDescription;
    Spinner spTripCurrency;

    //Variables related to dates
    int iUserSelectedYear,iUserSelectedMonth,iUserSelectedDayOfMonth;

    //Variables to get user entered values
    String sTripName,sTripDescription,sTripDate,sTripCurrency;

    //Temporary
    ArrayAdapter<String> adapterCurrencyType;
    private static final String[] CURRENCY_TYPE = new String[]{"Ruppee","USD","YEN"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_trip);
        initializeUiComponents();
        createNewTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTripDetails();
            }
        });
        ibDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalenderDailog();
            }
        });
    }

    private void getTripDetails() {
        getUserEnteredValues();
        if(validateUserEnterData()){
            //Toast.makeText(getActivity(),"Save details to database" + sExpenseAmount+ "Notes" + sExpenseNotes,Toast.LENGTH_SHORT).show();
            //if image is selected
            sendValuesToAddPersonActivity();
        }

    }

    private void sendValuesToAddPersonActivity() {
        Intent intentViewAddPersonsToTripActivity = new Intent(CreateNewTripActivity.this, AddPersonToTripActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("tripName",sTripName);
        bundle.putString("tripDescription",sTripDescription);
        bundle.putString("tripDate",sTripDate);
        bundle.putString("tripCurrency",sTripCurrency);
        intentViewAddPersonsToTripActivity.putExtras(bundle);
        startActivity(intentViewAddPersonsToTripActivity);
    }

    private boolean validateUserEnterData() {
        boolean isValid = true;
        View focusView = null;
        if(sTripName.isEmpty()){
            actvTripName.setError(getString(R.string.errormessage_trip_nameisrequired));
            isValid = false;
            focusView = actvTripName;
        }
        return isValid;
    }

    private void getUserEnteredValues() {
        sTripName = actvTripName.getText().toString().trim();
        sTripDescription = actvTripDescription.getText().toString().trim();
        sTripDate = tvTripDate.getText().toString().trim();
        sTripCurrency = spTripCurrency.getSelectedItem().toString().trim();
    }


    private void initializeUiComponents() {
        createNewTrip = (ImageButton) findViewById(R.id.createNewTrip);
        tvTripDate =(TextView) findViewById(R.id.tvTripDate);
        tvTripDate.setText(Utility.getCurrentDateForUserDisplay());
        ibDatePicker = (ImageButton) findViewById(R.id.ibDatePicker);
        actvTripName = (AutoCompleteTextView) findViewById(R.id.actvTripName);
        actvTripDescription = (AutoCompleteTextView) findViewById(R.id.actvTripDescription);
        spTripCurrency = (Spinner) findViewById(R.id.sTripCurrency);
        //temporaray code this will be loaded from database
        adapterCurrencyType = new ArrayAdapter<String>(CreateNewTripActivity.this,android.R.layout.simple_list_item_1,CURRENCY_TYPE);
        spTripCurrency.setAdapter(adapterCurrencyType);

    }

    private void showCalenderDailog(){
        tvTripDate.setInputType(InputType.TYPE_NULL);
        tvTripDate.setError(null);
        // Use the current date as the default date in the picker
        Calendar c;
        c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        //R.style.DatePicker
        DatePickerDialog datePicker = new DatePickerDialog(CreateNewTripActivity.this, new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar choosenTransactionDate = Calendar.getInstance();
                iUserSelectedYear = year;
                iUserSelectedMonth = month;
                iUserSelectedDayOfMonth = dayOfMonth;
                choosenTransactionDate.set(year,month,dayOfMonth);
                tvTripDate.setText(String.valueOf(dayOfMonth) + "-" + Utility.covertToMonth(month) + "-" + String.valueOf(year));
            }
        },year,month,day);

        datePicker.show();
    }

}
