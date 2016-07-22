package com.webmyne.applocker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by vaibhavirana on 22-07-2016.
 */
public class StartupReceiver extends BroadcastReceiver {
    static final String TAG = "SR";

    final int startupID = 1111111;


    @Override
    public void onReceive(Context context, Intent intent) {

        // Create AlarmManager from System Services
        final AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        try{
            // Create pending intent for CheckRunningApplicationReceiver.class
            // it will call after each 5 seconds

            Intent i7 = new Intent(context, CheckRunningApplicationReceiver.class);
            PendingIntent ServiceManagementIntent = PendingIntent.getBroadcast(context,
                    startupID, i7, 0);
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(),
                    1000, ServiceManagementIntent);


        } catch (Exception e) {
            Log.e(TAG, "Exception : "+e);
        }

    }
}
