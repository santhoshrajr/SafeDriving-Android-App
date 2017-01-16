package com.imperium.autobeacon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sowmya on 8/1/2016.
 */
public class UnlockReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent intent) {

        /*Sent when the user is present after
         * device wakes up (e.g when the keyguard is gone)
         * */
        try{
        if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            arg0.startService(new Intent(arg0, SpeedService.class));
        }

        }
        catch (Exception e)
        {
            Log.i("Invoked ",""+e.toString());
            arg0.startService(new Intent(arg0, SpeedService.class));
        }

    }

}
