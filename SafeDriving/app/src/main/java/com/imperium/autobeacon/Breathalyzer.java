package com.imperium.autobeacon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Breathalyzer extends AppCompatActivity {


    Spinner day,day1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathalyzer);

        day = (Spinner)findViewById(R.id.week_spinner);
        day1 = (Spinner)findViewById(R.id.week_spinner1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days_of_week, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adapter);
        day1.setAdapter(adapter);

        SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE");


        Date date = null;
        try {
            date = sdf.parse("07:00");
            date=sdf1.parse("Monday");
        }
        catch (ParseException e) {
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);



        TimePicker picker = new TimePicker(getApplicationContext());

    }



    public void showTimePickerDialog_Start(View v) {
        Bundle args = new Bundle();
        args.putInt("num", 0);

        DialogFragment newFragment = new TimeDayPreference();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void showTimePickerDialog_End(View v) {
        DialogFragment newFragment = new TimeDayPreference();
        Bundle args = new Bundle();
        args.putInt("num", 1);

        /*DialogFragment newFragment = new TimePreference();*/
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
