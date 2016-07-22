package com.webmyne.applocker;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by vaibhavirana on 22-07-2016.
 */
public class CheckRunningApplicationReceiver extends BroadcastReceiver {
    public final String TAG = "CRAR"; // CheckRunningApplicationReceiver

    @Override
    public void onReceive(Context aContext, Intent anIntent) {

        try {

            // Using ACTIVITY_SERVICE with getSystemService(String)
            // to retrieve a ActivityManager for interacting with the global system state.

            ActivityManager am = (ActivityManager) aContext
                    .getSystemService(Context.ACTIVITY_SERVICE);


            if(Build.VERSION.SDK_INT > 20){
                String mPackageName = am.getRunningAppProcesses().get(0).processName;
                Log.e(TAG, "aTask.baseActivity: "
                        + mPackageName);
                Toast.makeText(aContext,mPackageName,Toast.LENGTH_SHORT).show();
            }
            else{
                String mpackageName = am.getRunningTasks(1).get(0).topActivity.getPackageName();
                Log.e(TAG, "aTask.baseActivity: "
                        + mpackageName);
                Toast.makeText(aContext,mpackageName,Toast.LENGTH_SHORT).show();
            }

        } catch (Throwable t) {
            Log.e(TAG, "Throwable caught: "
                    + t.getMessage(), t);
        }

    }

}
