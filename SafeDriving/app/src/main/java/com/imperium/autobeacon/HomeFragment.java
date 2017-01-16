package com.imperium.autobeacon;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Switch switchPair;
    TextView tvDevice;
    String strEnable = "Enable Device";
    String strDisable = "Disable Device";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        switchPair = (Switch) view.findViewById(R.id.stchPair);
        tvDevice = (TextView) view.findViewById(R.id.tvDeviceStatus);
        TextView textviewadmin=(TextView)view.findViewById(R.id.textViewName);
        textviewadmin.setText(Constants.adminname);

        switchPair.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                  // do something when switched on
                    Toast.makeText(getActivity(), "Device Paired", Toast.LENGTH_SHORT).show();
                    tvDevice.setText(strDisable);

                }
                else{
                    // do something when switched off
                    Toast.makeText(getActivity(), "Device unpaired", Toast.LENGTH_SHORT).show();
                    tvDevice.setText(strEnable);

                }

            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}
