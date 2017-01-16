package com.imperium.autobeacon;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateRestrictions extends ListFragment  {

Intent i;
    int mCurCheckPosition=0;
    public CreateRestrictions() {

    }

    String[] Restrictions = {"Passengers", "Cell Phone Use", "Seat-belt Usage", "Curfew/ Nighttime Driving", "Location Monitoring", "Speed Monitoring", "Smart Breathalyzer"};
    int[] r_images = {R.drawable.passengers_symbol, R.drawable.cellphone_symbol,R.drawable.seatbelt_symbol, R.drawable.curfew_symbol,R.drawable.location_symbol,R.drawable.reckless_symbol,R.drawable.alcohol_symbol};

    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_restriction_list, container, false);
        HashMap<String, String> map = new HashMap<String, String>();

        for (int i = 0;i < Restrictions.length; i++ )
        {
            map = new HashMap<String, String>();
            map.put("Restriction", Restrictions[i]);
            map.put("Image", Integer.toString(r_images[i]));
            data.add(map);

        }

        String[] from = {"Restriction", "Image"};

        int[] to = {R.id.restriction_name, R.id.restriction_image};

        adapter = new SimpleAdapter(getActivity(), data, R.layout.restriction_model, from, to);


        setListAdapter(adapter);



        return v;

    }

    @Override
    public void onStart(){
        super.onStart();

       /* getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), data.get(position).get("Restriction"), Toast.LENGTH_SHORT).show();
                Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
                // Then you start a new Activity via Intent
                *//*switch (position) {
                    case 0:
                        i = new Intent(getActivity(), PassengersActivity.class);
                        break;
                    case 1:
                        i = new Intent(getActivity(), CellPhoneActivity.class);
                        break;
                    case 2:
                        i = new Intent(getActivity(), SeatBeltActivity.class);
                        break;
                    case 3:
                        i = new Intent(getActivity(), Curfew.class);
                        break;
                    case 4:
                        i = new Intent(getActivity(), LocationActivity.class);
                        break;
                    case 5:
                        i = new Intent(getActivity(), SpeedReckless.class);
                        break;
                    case 6:
                        i = new Intent(getActivity(), Breathalyzer.class);
                        break;
                }
                startActivity(i);
            }*//*
                if (id == 0){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), PassengersActivity.class);
                    intent.putExtra("position", position);
                    // Or / And
                    intent.putExtra("id", id);
                    startActivity(intent);

                }
                if (id == 1){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), CellPhoneActivity.class);
                    intent.putExtra("position", position);
                    // Or / And
                    intent.putExtra("id", id);
                    startActivity(intent);

                }

                if (id == 2){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), SeatBeltActivity.class);
                    intent.putExtra("position", position);
                    // Or / And
                    intent.putExtra("id", id);
                    startActivity(intent);

                }

                if (id == 3){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), Curfew.class);
                    intent.putExtra("position", position);
                    // Or / And
                    intent.putExtra("id", id);
                    startActivity(intent);

                }

                if (id == 4){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LocationActivity.class);
                    intent.putExtra("position", position);
                    // Or / And
                    intent.putExtra("id", id);
                    startActivity(intent);

                }

                if (id == 5){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), SpeedReckless.class);
                    intent.putExtra("position", position);
                    // Or / And
                    intent.putExtra("id", id);
                    startActivity(intent);

                }

                if (id == 6){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), Breathalyzer.class);
                    intent.putExtra("position", position);
                    // Or / And
                    intent.putExtra("id", id);
                    startActivity(intent);

                }

            }

        });*/

    }

    void showDetails(int index) {
        mCurCheckPosition = index;
        if (mCurCheckPosition == 0){
            Intent intent = new Intent();
            intent.setClass(getActivity(), PassengersActivity.class);
            intent.putExtra("position", mCurCheckPosition);
            // Or / And
            intent.putExtra("id", mCurCheckPosition);
            startActivity(intent);

        }
        if (mCurCheckPosition == 1){
            Intent intent = new Intent();
            intent.setClass(getActivity(), CellPhoneActivity.class);
            intent.putExtra("position", mCurCheckPosition);
            // Or / And
            intent.putExtra("id", mCurCheckPosition);
            startActivity(intent);

        }

        if (mCurCheckPosition == 2){
            Intent intent = new Intent();
            intent.setClass(getActivity(), SeatBeltActivity.class);
            intent.putExtra("position", mCurCheckPosition);
            // Or / And
            intent.putExtra("id", mCurCheckPosition);
            startActivity(intent);

        }

        if (mCurCheckPosition == 3){
            Intent intent = new Intent();
            intent.setClass(getActivity(), Curfew.class);
            intent.putExtra("position", mCurCheckPosition);
            // Or / And
            intent.putExtra("id", mCurCheckPosition);
            startActivity(intent);

        }

        if (mCurCheckPosition == 4){
            Intent intent = new Intent();
            intent.setClass(getActivity(), LocationActivity.class);
            intent.putExtra("position", mCurCheckPosition);
            // Or / And
            intent.putExtra("id", mCurCheckPosition);
            startActivity(intent);

        }

        if (mCurCheckPosition == 5){
            Intent intent = new Intent();
            intent.setClass(getActivity(), SpeedReckless.class);
            intent.putExtra("position", mCurCheckPosition);
            // Or / And
            intent.putExtra("id", mCurCheckPosition);
            startActivity(intent);

        }

        if (mCurCheckPosition == 6){
            Intent intent = new Intent();
            intent.setClass(getActivity(), Breathalyzer.class);
            intent.putExtra("position", mCurCheckPosition);
            // Or / And
            intent.putExtra("id", mCurCheckPosition);
            startActivity(intent);

        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ViewGroup viewGroup=(ViewGroup)v;
        //super.onListItemClick(l, v, position, id);
        showDetails(position);
    }
}


