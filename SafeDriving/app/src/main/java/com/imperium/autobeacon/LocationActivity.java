package com.imperium.autobeacon;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
//sowmya Kotha
public class LocationActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, LocationProvider.LocationCallback {
    private GoogleMap mMap;
    public static final String TAG = LocationActivity.class.getSimpleName();
    Switch loc;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private LocationProvider mLocationProvider;
    boolean isGPSEnabled, isNetworkEnabled, canGetLocation;
    private static final int LOCATION_REQUEST_CODE = 101;
    String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (!isGooglePlayServicesAvailable()) {
            finish();
        }*/
        setContentView(R.layout.activity_location);
       /* requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,
                LOCATION_REQUEST_CODE);*/

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mLocationProvider = new LocationProvider(this, this);


        loc = (Switch) findViewById(R.id.txtViewlocquestion);
       loc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            Intent myIntent = new Intent(LocationActivity.this, MapsActivity.class);
                            double latitude = latLng.latitude;
                            double longitude = latLng.longitude;
                            myIntent.putExtra("lat", latitude);
                            Log.i("lat", "" + latitude);
                            myIntent.putExtra("long", longitude);
                            startActivity(myIntent);
                        }
                    });

                } else {

                    // The toggle is disabled
                    mMap.setOnMapClickListener(null);
                }
            }
        });

       /* locationManager.requestLocationUpdates(provider, 400, 1, (android.location.LocationListener) this);*/
    }


    protected void requestPermission(String permissionType, int requestCode) {
        int permission = ContextCompat.checkSelfPermission(this,
                permissionType);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{permissionType}, requestCode
            );
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
                else {
                    mMap.setMyLocationEnabled(true);

                }
                return;
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                        LOCATION_REQUEST_CODE);

            }
            else {
                mMap.setMyLocationEnabled(true);
            }

        }


    }

    @Override
    public void onLocationChanged(Location location) {
        float mspped=location.getSpeed();
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng newlatlng = new LatLng(currentLatitude, currentLongitude);
        mMap.addMarker(new MarkerOptions().position(newlatlng).title("my Location"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newlatlng, 10));
    }


    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    public Location getLocation() {
        Location location = null;
        double latitude, longitude;
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                if (isNetworkEnabled) {


                   /* locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            400,
                            1, (android.location.LocationListener) this);*/
                    Log.i("Network", "Network Enabled");
                    if (locationManager != null) {
                        Log.i("in", "inside locmanager");
                        location = getLastKnownLocation(getApplicationContext());
                        if (location != null) {
                            Log.i("inside location", "in");
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
/*locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);*/
                        Log.i("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            Log.i("in", "inside locmanager");
                            location = getLastKnownLocation(getApplicationContext());
                            if (location != null) {
                                Log.i("inside location", "in");
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (SecurityException e) {
            Log.e("PERMISSION_EXCEPTION", "PERMISSION_NOT_GRANTED");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return location;
    }


    private Location getLastKnownLocation(Context context) {
        LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location best = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d("LOCATION", "PERMISSION NOT SET");
                return null;
            }
            Location loc = mLocationManager.getLastKnownLocation(provider);
            if(loc == null) {
                continue;
            }
            if(best == null || loc.getAccuracy() < best.getAccuracy()) {
                best = loc;
            }
        }
        return best;
    }


    public void handleNewLocation(Location location) {
        Log.i(TAG, location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        //mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Location"));
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("I am here!");
        mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }



}
