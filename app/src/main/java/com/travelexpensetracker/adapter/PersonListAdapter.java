package com.travelexpensetracker.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.travelexpensetracker.R;
import com.travelexpensetracker.activity.AddExpenseActivity;
import com.travelexpensetracker.model.Person;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Rini Banerjee on 01-09-2017.
 */

public class PersonListAdapter extends ArrayAdapter<Person> {

    private Activity context;
    private List<Person> personDetailsList;
    Person personDetails;


    public PersonListAdapter(Activity context,List<Person> expenseDetailsList){
        super(context, R.layout.card_layout_view_person_from_trip,expenseDetailsList);
        this.context = context;
        this.personDetailsList = expenseDetailsList;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        final View listViewItems = layoutInflater.inflate(R.layout.card_layout_view_person_from_trip,null,true);

        TextView tvPersonName = (TextView) listViewItems.findViewById(R.id.tvPersonName);
        TextView tvPersonMobileNo = (TextView) listViewItems.findViewById(R.id.tvPersonMobileNo);
        ImageButton ibAddExpenseToTrip = (ImageButton) listViewItems.findViewById(R.id.ibAddExpenseToTrip);

        personDetails = personDetailsList.get(position);

        tvPersonName.setText(personDetails.getsPersonName());
        tvPersonMobileNo.setText("("+personDetails.getsPersonMobileNo()+")");
        ibAddExpenseToTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context.getApplicationContext(), AddExpenseActivity.class));
            }
        });
        return listViewItems;
    }
}
