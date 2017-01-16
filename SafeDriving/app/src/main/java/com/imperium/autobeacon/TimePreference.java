package com.imperium.autobeacon;

import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;
import java.util.Calendar;

/**
 * Created by tpuser on 6/10/2016.
 */
public class TimePreference extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    final int DATE_PICKER_TO = 0;
    final int DATE_PICKER_FROM = 1;
    Button startbtn ,endbtn;

    TimePickerDialog.OnTimeSetListener from_timeListener,to_timeListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        int mNum = getArguments().getInt("num");
        //int DATE_PICKER_TO = 0;
        //int DATE_PICKER_FROM = 1;
       /* switch(mNum){
            case DATE_PICKER_FROM:
                return new TimePickerDialog(getActivity(), from_timeListener, hour, minute,
                        DateFormat.is24HourFormat(getActivity()));
            case DATE_PICKER_TO:
                return new TimePickerDialog(getActivity(), to_timeListener, hour, minute,
                        DateFormat.is24HourFormat(getActivity()));
        }
*/
       /* from_timeListener = new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                // Do something with the time chosen by the user
                Button startbtn=(Button) getActivity().findViewById(R.id.startbtn);
                startbtn.setText("Hour: "+view.getCurrentHour()+" Minute: "+view.getCurrentMinute());

            }
        };
        to_timeListener = new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                // Do something with the time chosen by the user
                Button startbtn=(Button) getActivity().findViewById(R.id.endbtn);
                startbtn.setText("Hour: "+view.getCurrentHour()+" Minute: "+view.getCurrentMinute());

            }
        };*/
        // Create a new instance of TimePickerDialog and return it

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

        /// return  null;

    }

    public Dialog onCreateDialog(int id)
    {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


        // Create a new instance of TimePickerDialog and return it
        return null;

    }



    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        int mNum = getArguments().getInt("num");
        String am_pm = "";

        Calendar datetime = Calendar.getInstance();
        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        datetime.set(Calendar.MINUTE, minute);

        if (datetime.get(Calendar.AM_PM) == Calendar.AM)
            am_pm = "AM";
        else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
            am_pm = "PM";

        String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ?"12":datetime.get(Calendar.HOUR)+"";


        if(mNum==0) {
            // Do something with the time chosen by the user
            startbtn = (Button) getActivity().findViewById(R.id.bStartTime);
            startbtn.setText( strHrsToShow+":"+datetime.get(Calendar.MINUTE)+" "+am_pm );
        }
        else
        {
            endbtn = (Button) getActivity().findViewById(R.id.bEndTime);
            endbtn.setText(strHrsToShow+":"+datetime.get(Calendar.MINUTE)+" "+am_pm );
        }

    }
}
