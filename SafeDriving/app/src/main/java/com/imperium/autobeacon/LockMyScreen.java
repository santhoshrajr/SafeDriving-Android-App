package com.imperium.autobeacon;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public final class LockMyScreen extends Activity {

    private static final int MAX_RETRY_COUNT = 4;
    private static final int RETRY_DELAY = 100;

    private static final int REQUEST_CODE_ENABLE_ADMIN = 2;

    @Override
    protected void onCreate( Bundle aSavedInstanceState ) {
        super.onCreate( aSavedInstanceState );

        ComponentName adminComponent = new ComponentName( LockMyScreen.this, PermissionReceiver.class );
        DevicePolicyManager policyManager = ( DevicePolicyManager ) getSystemService( Context.DEVICE_POLICY_SERVICE );

        if ( policyManager.isAdminActive( adminComponent ) ) {
            lockScreen( policyManager );
        }
        else {
            requestPermission( adminComponent );
        }
        makeFullScreen();
    }

    @Override
    protected void onActivityResult( int aRequestCode, int aResultCode, Intent aData ) {
        super.onActivityResult( aRequestCode, aResultCode, aData );

        if ( aResultCode == RESULT_OK ) {
            DevicePolicyManager policyManager = ( DevicePolicyManager ) getSystemService( Context.DEVICE_POLICY_SERVICE );
            lockScreen( policyManager );
        }
        else {
            askUserToRetry();
        }
    }

    private void lockScreen( final DevicePolicyManager aPolicyManager ) {
        final PowerManager powerManager = ( PowerManager ) getSystemService( Context.POWER_SERVICE );
        final Handler handler = new Handler( getMainLooper() );
        final int[] retryCount = new int[]{ 0 };

        handler.post( new Runnable() {
            @Override
            public void run() {
                if ( powerManager.isScreenOn() && retryCount[ 0 ] <= MAX_RETRY_COUNT ) {
                    aPolicyManager.lockNow();
                    retryCount[ 0 ]++;
                    handler.postDelayed( this, RETRY_DELAY * retryCount[ 0 ] );
                }
                else {
                    finish();
                }
            }
        } );
    }

    private void requestPermission( ComponentName aAdminComponent ) {
        String explanation = getResources().getString( R.string.request_permission_explanation );

        Intent intent = new Intent( DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN );
        intent.putExtra( DevicePolicyManager.EXTRA_DEVICE_ADMIN, aAdminComponent );
        intent.putExtra( DevicePolicyManager.EXTRA_ADD_EXPLANATION, explanation );

        startActivityForResult( intent, REQUEST_CODE_ENABLE_ADMIN );
    }

    private void askUserToRetry() {
        AlertDialog.Builder builder;
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ) {
            builder = new AlertDialog.Builder( this, AlertDialog.THEME_DEVICE_DEFAULT_DARK );
        }
        else {
            builder = new AlertDialog.Builder( this );
        }

        builder.setTitle( com.imperium.autobeacon.R.string.request_permission_title );
        builder.setMessage( com.imperium.autobeacon.R.string.request_permission_message );
        builder.setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface aDialog, int aButton ) {
                ComponentName adminComponent = new ComponentName( LockMyScreen.this, PermissionReceiver.class );
                requestPermission( adminComponent );
            }
        } );

        builder.setNegativeButton( android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface aDialog, int aButton ) {
                aDialog.dismiss();
                finish();
            }
        } );

        builder.create().show();
    }

    public static final class PermissionReceiver extends DeviceAdminReceiver {

        @Override
        public void onDisabled( Context aContext, Intent aIntent ) {
            Toast.makeText( aContext, R.string.on_permission_disabled, Toast.LENGTH_LONG ).show();
        }
    }
    public void makeFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } else {
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }

    public void unlockScreen(View view) {
        //Instead of using finish(), this totally destroys the process
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}

