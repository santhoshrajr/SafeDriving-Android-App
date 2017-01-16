package com.imperium.autobeacon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class PassengersActivity extends AppCompatActivity {

    Button add, subtract;
    TextView display;
    Switch underage;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengers);
        add = (Button) findViewById(R.id.addPassenger);
        add.setEnabled(false);
        subtract = (Button) findViewById(R.id.subtractPassenger);
        subtract.setEnabled(false);
        display = (TextView) findViewById(R.id.displayPassenger);
        underage = (Switch) findViewById(R.id.sUnderagePassengers);

        underage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    // do something when switched on
                    add.setEnabled(true);
                    subtract.setEnabled(true);
                    Toast.makeText(getBaseContext(), "Underage passengers allowed", Toast.LENGTH_SHORT).show();
                }
                else{
                    // do something when switched off
                    Toast.makeText(getBaseContext(), "Disallow Cell-Phone Use", Toast.LENGTH_SHORT).show();
                }

            }
        });

        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                counter += 1;
                display.setText(""+counter);
                if(counter<6) {
                    display.setText("" + counter);
                }
                else{
                    display.setText("Passenger limit exceeded");
                    counter = 6;
                }


            }
        });
        subtract.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                counter-=1;
                if(counter>0) {
                    display.setText("" + counter);
                }
                else{
                    display.setText("Reached a limit");
                    counter = 0;
                }


            }
        });
    }
}
