package com.imperium.autobeacon;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class TempRestrict extends Fragment {


    public TempRestrict() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_temp_restrict, container, false);
        Button button = (Button) view.findViewById(R.id.bSpeedMonitoring);
        Button btnLocation = (Button) view.findViewById(R.id.bLocationSettings);
        Button btnCurfew = (Button) view.findViewById(R.id.bCurfewSettings);
        Button btnBreath = (Button) view.findViewById(R.id.bBreathalyzer);
        Button btnPassengers=(Button)view.findViewById(R.id.bPassengers);
        Button btnCellPhone=(Button) view.findViewById(R.id.bCellPhone);
        Button btnSeatbelt=(Button) view.findViewById(R.id.bSeatBelt);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                Intent myIntent = new Intent(getActivity(), SpeedReckless.class);
                //getActivity(). finish();
                getActivity().startActivity(myIntent);

            }
        });

        btnCurfew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                Intent myIntent = new Intent(getActivity(), Curfew.class);
                //getActivity(). finish();
                getActivity().startActivity(myIntent);

            }
        });
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                Intent myIntent = new Intent(getActivity(), LocationActivity.class);
                //getActivity(). finish();
                getActivity().startActivity(myIntent);

            }
        });
        btnBreath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                Intent myIntent = new Intent(getActivity(), Breathalyzer.class);
                //getActivity(). finish();
                getActivity().startActivity(myIntent);

            }
        });
        btnPassengers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                Intent myIntent = new Intent(getActivity(), PassengersActivity.class);
                //getActivity(). finish();
                getActivity().startActivity(myIntent);

            }
        });
        btnCellPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                Intent myIntent = new Intent(getActivity(), CellPhoneActivity.class);
                //getActivity(). finish();
                getActivity().startActivity(myIntent);

            }
        });
        btnSeatbelt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                Intent myIntent = new Intent(getActivity(), Breathalyzer.class);
                //getActivity(). finish();
                getActivity().startActivity(myIntent);

            }
        });
        return view;
    }

}
