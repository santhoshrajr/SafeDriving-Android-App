package com.imperium.autobeacon;

//import android.support.v4.app.DialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import android.view.View;
//import android.widget.TimePicker;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;


public class Curfew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curfew);


        SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
        Date date = null;
        try {
            date = sdf.parse("07:00");
        } catch (ParseException e) {
            //do nothing
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        TimePicker picker = new TimePicker(getApplicationContext());

    }


    public void showTimePickerDialog_Start(View v) {
        Bundle args = new Bundle();
        args.putInt("num", 0);

        DialogFragment newFragment = new TimePreference();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showTimePickerDialog_End(View v) {
        DialogFragment newFragment = new TimePreference();
        Bundle args = new Bundle();
        args.putInt("num", 1);

        /*DialogFragment newFragment = new TimePreference();*/
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

}
