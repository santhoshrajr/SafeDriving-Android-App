package com.imperium.autobeacon;

import android.*;
import android.Manifest;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText username, driverid, password, res_driverid, code, newpass, res_email;
    Button login, register, forpass, cont, cont_code, cancel, cancel1;
    String usernametxt, passwordtxt, driveridtxt, driverid_res_txt, code_txt, npass_txt, email_res_txt;
    Dialog reset;
    ServerRequest sr;
    HashMap<String, String> params;
    SharedPreferences pref;
    private static final int LOCATION_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sr = new ServerRequest();
        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,
                LOCATION_REQUEST_CODE);
        Intent alarm = new Intent(this, UnlockReceiver.class);
        boolean alarmRunning = (PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), alarm, PendingIntent.FLAG_NO_CREATE) != null);
        if(alarmRunning == false) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarm, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 40000, pendingIntent);
        }
       // requestpermission();
        //startService(new Intent(this, SpeedService.class));

        driverid = (EditText) findViewById(R.id.tLoginUsername);
        password = (EditText) findViewById(R.id.tLoginPassword);
        login = (Button) findViewById(R.id.bLogin);
        register = (Button) findViewById(R.id.bRegister);
        forpass = (Button) findViewById(R.id.bForgotPwd);
        //pref = getShareduPreferences("AppPref", MODE_PRIVATE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regactivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(regactivity);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                driveridtxt = driverid.getText().toString();
                passwordtxt = password.getText().toString();
                params = new HashMap<String, String>();
                params.put("email", driveridtxt);
                params.put("password", passwordtxt);
                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://autobeacon.herokuapp.com/api/login", params);
                if (json != null) {
                    try {
                        String jsonstr = json.getString("user");
                       /* if (json.getBoolean("res")) {*/
                        JSONObject userjson= new JSONObject(jsonstr);
                        String token = json.getString("token");
                        String adminId=userjson.getString("id");
                        String lastname=userjson.getString("firstName");
                        String firstname=userjson.getString("lastName");
                        String name=firstname+lastname;
                        Constants.adminname=name;
                            //String grav = json.getString("grav");
                            /*SharedPreferences.Editor edit pref.edit();
                            //Storing Data using SharedPreferences
                            edit.putString("token", token);
                           // edit.putString("grav", grav);
                            edit.commit();*/
                        Constants.token=token;
                        Constants.admin=adminId;
                            Intent profactivity = new Intent(MainActivity.this, TabsPage.class);

                            startActivity(profactivity);
                            finish();

                            //}

                            Toast.makeText(getApplication(), "Succesfully Logged In", Toast.LENGTH_LONG).show();
                        //}

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        forpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotintent = new Intent(MainActivity.this, ForgotActivity.class);
                startActivity(forgotintent);
            }
        });
    }

    /*private void requestpermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);

        }
    }*/
    protected void requestPermission(String permissionType, int requestCode) {
        int permission = ContextCompat.checkSelfPermission(this,
                permissionType);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{permissionType}, requestCode
            );
        }
        else{
            startService(new Intent(this, SpeedService.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {

                if (grantResults.length == 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Unable to show location - permission required", Toast.LENGTH_LONG).show();
                }
                else
                {
                    startService(new Intent(this, SpeedService.class));

                }
                return;
            }
        }
    }
}


//                res_email = (EditText)reset.findViewById(R.id.email);
//
//                cont.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        email_res_txt = res_email.getText().toString();
//                        params = new HashMap<String, String>();
//                        params.put("email", email_res_txt);
/*                        params.put("password", passwordtxt);
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("email", email_res_txt));*/

                        //  JSONObject json = sr.getJSON("http://192.168.56.1:8080/api/resetpass", params);
                       // JSONObject json = sr.getJSON("http://10.0.2.2:8080/api/resetpass", params);
/*
                        if (json != null) {
                            try {
                                String jsonstr = json.getString("response");
                                if(json.getBoolean("res")){
                                    Log.e("JSON", jsonstr);
                                    Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();
                                    reset.setContentView(R.layout.reset_pass_code);
                                    cont_code = (Button)reset.findViewById(R.id.conbtn);
                                    code = (EditText)reset.findViewById(R.id.code);
                                    //newpass = (EditText)reset.findViewById(R.id.npass);
                                    cancel1 = (Button)reset.findViewById(R.id.cancel);
                                    cancel1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            reset.dismiss();
                                        }
                                    });
                                    cont_code.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            code_txt = code.getText().toString();
                                            npass_txt = newpass.getText().toString();
                                            Log.e("Code",code_txt);
                                            Log.e("New pass",npass_txt);
                                            params = new HashMap<String, String>();
                                            params.put("email", email_res_txt);
                                            params.put("code", code_txt);
                                            params.put("newpass", npass_txt);*/
/*                                            params = new ArrayList<NameValuePair>();
                                            params.add(new BasicNameValuePair("email", email_res_txt));
                                            params.add(new BasicNameValuePair("code", code_txt));
                                            params.add(new BasicNameValuePair("newpass", npass_txt));*/

                                           // JSONObject json = sr.getJSON("http://10.0.2.2:8080/api/resetpass/chg", params);
                                            //   JSONObject json = sr.getJSON("http://192.168.56.1:8080/api/resetpass/chg", params);

                                          /*  if (json != null) {
                                                try {

                                                    String jsonstr = json.getString("response");
                                                    if(json.getBoolean("res")){
                                                        reset.dismiss();
                                                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                                                    }else{
                                                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                        }
                                    });
                                }else{

                                    Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });


                reset.show();
            }
        });
    }




}*/


       /*         res_driverid = (EditText)reset.findViewById(R.id.tLoginUsername);

                cont.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       driverid_res_txt = res_driverid.getText().toString();

                        params = new HashMap<String, String>();
                        params.put("username",driverid_res_txt);

                        //  JSONObject json = sr.getJSON("http://192.168.56.1:8080/api/resetpass", params);
                        JSONObject json = sr.getJSON("http://10.0.2.2:8081/api/resetpass", params);

                        if (json != null) {
                            try {
                                String jsonstr = json.getString("response");
                                if(json.getBoolean("res")){
                                    Log.e("JSON", jsonstr);
                                    Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();
                                    reset.setContentView(R.layout.reset_pass_code);
                                    cont_code = (Button)reset.findViewById(R.id.conbtn);
                                    code = (EditText)reset.findViewById(R.id.code);
                                    newpass = (EditText)reset.findViewById(R.id.npass);
                                    cancel1 = (Button)reset.findViewById(R.id.cancel);
                                    cancel1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            reset.dismiss();
                                        }
                                    });
                                    cont_code.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            code_txt = code.getText().toString();
                                            npass_txt = newpass.getText().toString();
                                            Log.e("Code",code_txt);
                                            Log.e("New pass",npass_txt);
                                            params = new HashMap<String, String>();
                                            params.put("driverid", driverid_res_txt);
                                            params.put("code", code_txt);
                                            params.put("newpass", npass_txt);

                                            JSONObject json = sr.getJSON("http://10.0.2.2:8081/api/resetpass/chg", params);
                                            //   JSONObject json = sr.getJSON("http://192.168.56.1:8080/api/resetpass/chg", params);

                                            if (json != null) {
                                                try {

                                                    String jsonstr = json.getString("response");
                                                    if(json.getBoolean("res")){
                                                        reset.dismiss();
                                                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                                                    }else{
                                                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                        }
                                    });
                                }else{

                                    Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
*/

   /*             reset.show();
            }
        });

    }
}*/
