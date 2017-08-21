package com.travelexpensetracker.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.travelexpensetracker.R;
import com.travelexpensetracker.utility.Utility;

import java.util.Calendar;

public class CreateNewTripActivity extends AppCompatActivity {

    ImageButton createNewTrip;
    TextView tvTripDate;
    ImageButton ibDatePicker;

    int iUserSelectedYear,iUserSelectedMonth,iUserSelectedDayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_trip);
        initializeUiComponents();
        createNewTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CreateNewTripActivity.this,"Open add person screen",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CreateNewTripActivity.this,AddPersonToTripActivity.class));
            }
        });
        ibDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalenderDailog();
            }
        });
    }

    private void initializeUiComponents() {
        createNewTrip = (ImageButton) findViewById(R.id.createNewTrip);
        tvTripDate =(TextView) findViewById(R.id.tvTripDate);
        tvTripDate.setText(Utility.getCurrentDateForUserDisplay());
        ibDatePicker = (ImageButton) findViewById(R.id.ibDatePicker);
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
