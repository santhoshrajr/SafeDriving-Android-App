package com.imperium.autobeacon;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;

import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;


public class DisclaimerActivity extends AppCompatActivity {
    HashMap<String, String> params;
    Button agree;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        agree=(Button)findViewById(R.id.bAgree);
        agree.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                String email = intent.getStringExtra("email");
                String password = intent.getStringExtra("password");
                String lName = intent.getStringExtra("lastName");
                String fName = intent.getStringExtra("firstName");
                params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                params.put("lastName", lName);
                params.put("firstName", fName);

                ServerRequest sr=new ServerRequest();
                JSONObject json = sr.getJSON("http://autobeacon.herokuapp.com/api/register", params);
                if (json != null) {
                    try {
                        String jsonstr = json.getString("user");
                 //       if (json.getBoolean("res")) {
                            String token = json.getString("token");
                        Constants.token=token;
                           // String grav = json.getString("grav");
                           /* SharedPreferences.Editor edit = pref.edit();
                            //Storing Data using SharedPreferences
                            edit.putString("token", token);
                           // edit.putString("grav", grav);
                            edit.commit();*/
                        Intent mainactivity = new Intent(DisclaimerActivity.this, MainActivity.class);

                        startActivity(mainactivity);
                        finish();
                   //     }

                        Toast.makeText(getApplication(), "Succesfully registered", Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });



    }


    public void DisagreePopup(View view) {

        Intent intent = new Intent(DisclaimerActivity.this, PopActivity.class);
        startActivity(intent);

    }
    public void AgreePopup(View view) {




    }


}


