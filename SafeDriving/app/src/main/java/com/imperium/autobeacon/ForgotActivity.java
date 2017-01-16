package com.imperium.autobeacon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class ForgotActivity extends AppCompatActivity {
EditText email;
    Button Continue,Cancel;
    HashMap<String, String> params;
    String emailtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        Continue=(Button)findViewById(R.id.bCont);
        Cancel=(Button)findViewById(R.id.bCancel);
        email=(EditText)findViewById(R.id.etEmail1);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailtxt = email.getText().toString();
                params = new HashMap<String, String>();
                params.put("username", emailtxt);
                ServerRequest sr= new ServerRequest();
                JSONObject json = sr.getJSON("http://autobeacon.herokuapp.com/forgot", params);
                if (json != null) {
                    try {
                        String jsonstr = json.getString("response");
                        /*if (json.getBoolean("res")) {
                            String token = json.getString("token");
                            String grav = json.getString("grav");
                            SharedPreferences.Editor edit = pref.edit();
                            //Storing Data using SharedPreferences
                            edit.putString("token", token);
                            edit.putString("grav", grav);
                            edit.commit();*/
                        Intent forgotactivity = new Intent(ForgotActivity.this, MainActivity.class);

                        startActivity(forgotactivity);
                        finish();
                        //}

                        Toast.makeText(getApplication(),jsonstr, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });

    }
}
