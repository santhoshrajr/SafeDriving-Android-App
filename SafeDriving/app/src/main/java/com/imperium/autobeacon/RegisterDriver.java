package com.imperium.autobeacon;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.lang.String;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterDriver extends Fragment {

    Button bRegDriver;
    HashMap<String, String> params;
    public RegisterDriver() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_register_driver, container, false);
        final EditText lName = (EditText) view.findViewById(R.id.etLastNameDriverReg) ;
        final EditText fName=(EditText) view.findViewById(R.id.etFirstNameDriverReg);
        final EditText email=(EditText) view.findViewById(R.id.etDriverIDDriverReg);
        final EditText age=(EditText) view.findViewById(R.id.etPwdDriverReg);
        final EditText gender=(EditText) view.findViewById(R.id.etRePwdDriverReg) ;
         populatelist();


        Button button = (Button) view.findViewById(R.id.bCreateRestriction);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String lNametxt=lName.getText().toString();
                String fNametxt=fName.getText().toString();
                String usernametxt=email.getText().toString();
                String passwordtxt=age.getText().toString();
                String gendertxt=gender.getText().toString();
                params = new HashMap<String, String>();
                params.put("email", usernametxt);
            params.put("age", passwordtxt);
                params.put("gender", gendertxt);
               params.put("accountAdminId",Constants.admin);

                params.put("lastName", lNametxt);
                params.put("firstName", fNametxt);
                Log.i("token",Constants.token.toString());
                ServerRequest sr=new ServerRequest();
                String url="http://autobeacon.herokuapp.com/api/admins/"+Constants.admin+"/drivers";
                JSONObject json = sr.getJSONdriver(url, params);
                if (json != null) {
                    try {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        ft.replace(R.id.RegisterDriver, new CreateRestrictions());
                        //ft.replace(R.id.RegisterDriver, new RestrictionList());
                        ft.addToBackStack(null);
                        ft.commit();
                       /* String jsonstr = json.getString("response");
                        if (json.getBoolean("res")) {
                            String token = json.getString("token");
                            String grav = json.getString("grav");
                            SharedPreferences.Editor edit = pref.edit();
                            //Storing Data using SharedPreferences
                            edit.putString("token", token);
                            edit.putString("grav", grav);
                            edit.commit();
                        Intent mainactivity = new Intent(RegisterDriver.this, DriversFragment.class);

                        startActivity(mainactivity);
                        finish();*/
                        //}

                        Toast.makeText(getContext(), "Succesfully registered", Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                      e.printStackTrace();
                   }
                }

//

            }
        });

        Button temp_button = (Button) view.findViewById(R.id.bTempRestrict);
        temp_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.replace(R.id.RegisterDriver, new CreateRestrictions());
                //ft.replace(R.id.RegisterDriver, new RestrictionList());
                ft.addToBackStack(null);
                ft.commit();

            }
        });


        return view;
    }

    private void populatelist() {
        ServerRequest sr=new ServerRequest();
        String url="http://autobeacon.herokuapp.com/api/admins/"+Constants.admin+"/vehicles/";
        JSONObject json = sr.getdriver(url);
        JSONObject resul=json;

    }


}
