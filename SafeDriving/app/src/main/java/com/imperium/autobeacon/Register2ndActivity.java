package com.imperium.autobeacon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.edmunds.api.Configuration;
import com.edmunds.api.controllers.SpecVehicleMakeController;
import com.edmunds.api.http.client.APICallBack;
import com.edmunds.api.http.client.HttpContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Register2ndActivity extends AppCompatActivity {
    ArrayList<String> makearray;
    ArrayList<String> modelarray;
    ArrayList<Integer> yeararray;
    Spinner make,model,year;
    HashMap<String,String> makeandnice;
    Button final_reg;
    EditText street,city,state,zip,device,lease;
    HashMap<String, String> params;
String streettxt,citytxt,statetxt,ziptxt,devicetxt,leasetxt,maketxt,modeltxt,yeartxt;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2nd);

        final_reg = (Button) findViewById(R.id.bContinue);
        street = (EditText) findViewById(R.id.etStreetAdr);
        city = (EditText) findViewById(R.id.etCity);
        state = (EditText) findViewById(R.id.etState);
        zip = (EditText) findViewById(R.id.etDeviceSerial);
        device = (EditText) findViewById(R.id.etSerialNumber);
        lease = (EditText) findViewById(R.id.etLeaseID);
        make = (Spinner) findViewById(R.id.make);
        model = (Spinner) findViewById(R.id.model);
        year = (Spinner) findViewById(R.id.year);
        final  SpecVehicleMakeController vehicledata=new SpecVehicleMakeController();
        APICallBack< LinkedHashMap<String, Object>> result;
        //  Context mcontext=;
        if(!Constants.intializaton) {
            Configuration.initialize(getApplicationContext());
            Constants.intializaton=true;
        }
        vehicledata.getMakesAsync(" ","json","used","basic",2015, new APICallBack<LinkedHashMap<String, Object>>() {
            @Override
            public void onSuccess(HttpContext httpContext, LinkedHashMap<String, Object> stringObjectLinkedHashMap) {
                Log.i("data",stringObjectLinkedHashMap.values().toString());

                for (Map.Entry<String, Object> entry : stringObjectLinkedHashMap.entrySet()) {
                    String key = entry.getKey();
                    if(key=="makes") {
                        ArrayList<Object>  data = (ArrayList<Object>) entry.getValue();
//                        for(int x=0;x<data.size();x++)
//                        {
//                            LinkedHashMap<String,String> mmy=new LinkedHashMap<String, String>();
//                            mmy.put(data.ge)
//                        }
//
//
//                        JSONObject jsonResult = null;
                        try {
                            // jsonResult = new JSONObject(data);
                            JSONObject jsonString = new JSONObject(stringObjectLinkedHashMap);
                            JSONArray datas = jsonString.getJSONArray("makes");

                            if (datas != null) {
                                String[] make = new String[datas.length()];
                                modelarray=new ArrayList<String>();
                                makearray=new ArrayList<String>();
                                yeararray=new ArrayList<>();
                                makeandnice=new HashMap<String,String>();
                                String[] year = new String[datas.length()];
                                //String[] model = new String[datas.length()];
                                for (int i = 0; i < datas.length(); i++) {
                                    JSONObject singledata = datas.getJSONObject(i);
                                    //make[i] = singledata.getString("name");
                                    makearray.add(singledata.getString("name"));
                                    makeandnice.put(singledata.getString("name"),singledata.getString("niceName"));
                                    JSONArray models = singledata.getJSONArray("models");
                                    if(models!=null)
                                    {

                                        for (int j = 0; j <models .length(); j++) {
                                            JSONObject Singlemodeldata =models.getJSONObject(j);
                                            modelarray.add(Singlemodeldata.getString("name"));
                                            /*JSONArray years = Singlemodeldata.getJSONArray("years");
                                            if(years!=null) {
                                                for (int k = 0;k <years .length(); k++) {
                                                    JSONObject Singleyearsdata = years.getJSONObject(k);
                                                    yeararray.add(Singleyearsdata.getInt("year"));
                                                }
                                            }*/
                                            // model[j]=Singlemodeldata.getString("name");
                                        }
                                    }


                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    // now work with key and value...
                }


                Log.i("dataofmake",makearray.toString());
                populatespinners();


               /* Iterator it = stringObjectLinkedHashMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());


                    it.remove(); // avoids a ConcurrentModificationException
                }*/


            }

            @Override
            public void onFailure(HttpContext httpContext, Throwable throwable) {
                Log.i("data",throwable.toString());
            }
        });
        final String[] stringArray =getResources().getStringArray(R.array.year_data);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.year_data, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       /* ArrayAdapter<Integer> year_adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, R.array.year_data);
        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        year.setAdapter(adapter);

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                APICallBack< LinkedHashMap<String, Object>> result;
                //  Context mcontext=;
                // Configuration.initialize(getApplicationContext());
                vehicledata.getMakesAsync(" ","json","used","basic", Integer.parseInt(stringArray[position]), new APICallBack<LinkedHashMap<String, Object>>() {
                    @Override
                    public void onSuccess(HttpContext httpContext, LinkedHashMap<String, Object> stringObjectLinkedHashMap) {
                        Log.i("data",stringObjectLinkedHashMap.values().toString());

                        for (Map.Entry<String, Object> entry : stringObjectLinkedHashMap.entrySet()) {
                            String key = entry.getKey();
                            if(key=="makes") {
                                ArrayList<Object>  data = (ArrayList<Object>) entry.getValue();
//                        for(int x=0;x<data.size();x++)
//                        {
//                            LinkedHashMap<String,String> mmy=new LinkedHashMap<String, String>();
//                            mmy.put(data.ge)
//                        }
//
//
//                        JSONObject jsonResult = null;
                                try {
                                    // jsonResult = new JSONObject(data);
                                    JSONObject jsonString = new JSONObject(stringObjectLinkedHashMap);
                                    JSONArray datas = jsonString.getJSONArray("makes");

                                    if (datas != null) {
                                        String[] make = new String[datas.length()];
                                        modelarray=new ArrayList<String>();
                                        makearray=new ArrayList<String>();
                                        makeandnice=new HashMap<String,String>();
                                        yeararray=new ArrayList<>();
                                        String[] year = new String[datas.length()];
                                        //String[] model = new String[datas.length()];
                                        for (int i = 0; i < datas.length(); i++) {
                                            JSONObject singledata = datas.getJSONObject(i);
                                            //make[i] = singledata.getString("name");
                                            makearray.add(singledata.getString("name"));
                                            makeandnice.put(singledata.getString("name"),singledata.getString("niceName"));
                                            //  makenicknamearray.add(singledata.getString("niceName"));
                                            JSONArray models = singledata.getJSONArray("models");
                                            if(models!=null)
                                            {

                                                for (int j = 0; j <models .length(); j++) {
                                                    JSONObject Singlemodeldata =models.getJSONObject(j);
                                                    modelarray.add(Singlemodeldata.getString("name"));
                                            /*JSONArray years = Singlemodeldata.getJSONArray("years");
                                            if(years!=null) {
                                                for (int k = 0;k <years .length(); k++) {
                                                    JSONObject Singleyearsdata = years.getJSONObject(k);
                                                    yeararray.add(Singleyearsdata.getInt("year"));
                                                }
                                            }*/
                                                    // model[j]=Singlemodeldata.getString("name");
                                                }
                                            }


                                        }

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // now work with key and value...
                        }


                        Log.i("dataofmake",makearray.toString());
                        populatespinners();


               /* Iterator it = stringObjectLinkedHashMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());


                    it.remove(); // avoids a ConcurrentModificationException
                }*/


                    }

                    @Override
                    public void onFailure(HttpContext httpContext, Throwable throwable) {
                        Log.i("data",throwable.toString());
                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        make.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                APICallBack< LinkedHashMap<String, Object>> result;
                String selectedmakename=make.getSelectedItem().toString();
                String selectednicename=null;
                for (Map.Entry<String, String> allnicenames : makeandnice.entrySet()) {
                    String key = allnicenames.getKey();
                    if(key.toString().equals(selectedmakename))
                    {
                        selectednicename=allnicenames.getValue();
                        //  Context mcontext=;
                        // Configuration.initialize(getApplicationContext());
                        vehicledata.getV2Async(" ","json",selectednicename,"used","basic", Integer.parseInt(stringArray[position]), new APICallBack<LinkedHashMap<String, Object>>() {
                            @Override
                            public void onSuccess(HttpContext httpContext, LinkedHashMap<String, Object> stringObjectLinkedHashMap) {
                                Log.i("data",stringObjectLinkedHashMap.values().toString());

                                for (Map.Entry<String, Object> entry : stringObjectLinkedHashMap.entrySet()) {
                                    String key = entry.getKey();
                                    //  if(key=="makes") {
                                    //  ArrayList<Object>  data = (ArrayList<Object>) entry.getValue();
//                        for(int x=0;x<data.size();x++)
//                        {
//                            LinkedHashMap<String,String> mmy=new LinkedHashMap<String, String>();
//                            mmy.put(data.ge)
//                        }
//
//
//                        JSONObject jsonResult = null;
                                    try {
                                        // jsonResult = new JSONObject(data);
                                        JSONObject jsonString = new JSONObject(stringObjectLinkedHashMap);
                                        JSONArray datas = jsonString.getJSONArray("models");

                                        if (datas != null) {
                                            String[] make = new String[datas.length()];
                                            modelarray=new ArrayList<String>();
                                            // makearray=new ArrayList<String>();
                                            yeararray=new ArrayList<>();
                                            String[] year = new String[datas.length()];
                                            //String[] model = new String[datas.length()];
                                            for (int i = 0; i < datas.length(); i++) {
                                                JSONObject singledata = datas.getJSONObject(i);
                                                //make[i] = singledata.getString("name");
                                                modelarray.add(singledata.getString("name"));



                                            }

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                // now work with key and value...
                                //}


                                Log.i("dataofmake",makearray.toString());
                                populatespinnersmodel();


               /* Iterator it = stringObjectLinkedHashMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());


                    it.remove(); // avoids a ConcurrentModificationException
                }*/


                            }

                            @Override
                            public void onFailure(HttpContext httpContext, Throwable throwable) {
                                Log.i("data",throwable.toString());
                            }
                        });
                    }
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        final_reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = getIntent();

                String email = intent.getStringExtra("email");
                String password = intent.getStringExtra("password");
                String lName = intent.getStringExtra("lastName");
                String fName = intent.getStringExtra("firstName");

                streettxt = street.getText().toString();
                citytxt = city.getText().toString();
                statetxt = state.getText().toString();
                ziptxt = zip.getText().toString();
                devicetxt = device.getText().toString();
                leasetxt = lease.getText().toString();
               maketxt = make.getSelectedItem().toString();
                modeltxt = model.getSelectedItem().toString();
                yeartxt = year.getSelectedItem().toString();
                params = new HashMap<String, String>();
              /*  params.put("street", streettxt);
                params.put("city", citytxt);
                params.put("state", statetxt);
                params.put("zip", ziptxt);
                params.put("serialid", devicetxt);
                params.put("leaseid", leasetxt);*/
                params.put("make", maketxt);
                params.put("model", modeltxt);
                params.put("year", yeartxt);
                params.put("accountAdminId",Constants.admin);

               //ServerRequest sr=new ServerRequest();
               // JSONObject json = sr.getJSON("http://autobeacon.herokuapp.com/api/admins/"+Constants.admin+"/vehicles",params);
               //if(json!=null){
                    /*try{
                        String jsonstr = json.getString("response");
                        if(json.getBoolean("res")){
                            Log.e("JSON", jsonstr);


                            Intent intent = new Intent(Register2ndActivity.this, DisclaimerActivity.class);
                            startActivity(intent);
                            finish()*/;
                   Intent discactivity = new Intent(Register2ndActivity.this, DisclaimerActivity.class);

                   discactivity.putExtra("email", email);
                   discactivity.putExtra("password", password);
                   discactivity.putExtra("lastName", lName);
                   discactivity.putExtra("firstName", fName);
                   startActivity(discactivity);
                            //Toast.makeText(getApplication(),"Successfully updated", Toast.LENGTH_LONG).show();
                                                    }
                           // else {
                           // Toast.makeText(getApplication(),"Not updated vehicles list", Toast.LENGTH_LONG).show();
                        //}
                   // }







        });
    }
    private void populatespinnersmodel() {
        ArrayAdapter<String> model_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, modelarray);

        model_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        model.setAdapter(model_adapter);
    }

    private void populatespinners() {
        ArrayAdapter<String> make_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, makearray);
        make_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        make.setAdapter(make_adapter);
        ArrayAdapter<String> model_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, modelarray);

        model_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        model.setAdapter(model_adapter);




    }



    public void DiscContinue(View view) {

    }
}
