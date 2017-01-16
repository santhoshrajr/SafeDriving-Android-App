package com.imperium.autobeacon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class CellPhoneActivity extends AppCompatActivity {

    Switch allow_use;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_phone);
        allow_use = (Switch) findViewById(R.id.sPermissionForCellPhoneUse);

        allow_use.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    // do something when switched on
                    Toast.makeText(getBaseContext(), "Allow Cell-Phone Use", Toast.LENGTH_SHORT).show();
                }
                else{
                    // do something when switched off
                    Toast.makeText(getBaseContext(), "Disallow Cell-Phone Use", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
