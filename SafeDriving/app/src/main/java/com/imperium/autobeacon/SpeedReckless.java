package com.imperium.autobeacon;

import android.*;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SpeedReckless extends AppCompatActivity implements   LocationProvider.LocationCallback{

    Button add, ad,subtract;

    TextView display,currentspeed;
    private LocationProvider mLocationProvider;
    private static final int LOCATION_REQUEST_CODE = 101;
    int counter=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_reckless);
        add = (Button) findViewById(R.id.add);
        subtract = (Button) findViewById(R.id.subtract);
        display = (TextView) findViewById(R.id.display);
        mLocationProvider = new LocationProvider(this, this);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                counter+=5;
                display.setText(""+counter);
                if(counter<90) {
                    display.setText("" + counter);
                }
                else{
                    display.setText("High Speed");
                    //sowmya kotha
                }


            }
        });
        subtract.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                counter-=5;
                if(counter>=5) {
                    display.setText("" + counter);
                }
                else{
                    display.setText("Low Speed");
                }


            }
        });
    }

    public void handleNewLocation(Location location) {
        // Log.i(TAG, location.toString());


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);

        }
        else{
            currentspeed=(TextView)findViewById(R.id.speed);
            float speed=location.getSpeed();
            currentspeed.setText(""+speed);
        }
        currentspeed=(TextView)findViewById(R.id.speed);
        float speed=location.getSpeed();
        currentspeed.setText(""+speed);
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {

                if (grantResults.length == 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Unable to show location - permission required", Toast.LENGTH_LONG).show();
                }
                else {

                }

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationProvider.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationProvider.disconnect();

    }

}
