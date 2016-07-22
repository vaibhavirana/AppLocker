package com.webmyne.applocker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.webmyne.applocker.adapter.ApplicationListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvAppList;
    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private ApplicationListAdapter appListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* getBaseContext().getApplicationContext().sendBroadcast(
                new Intent("CheckRunningApplicationReceiver"));
        packageManager = getPackageManager();*/


        init();
    }

    private void init() {
        rvAppList = (RecyclerView) findViewById(R.id.rvAppList);
        rvAppList.setLayoutManager(new GridLayoutManager(this,2));


        new LoadApplications().execute();
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
            /*listadaptor = new ApplicationAdapter(AllAppsActivity.this,
                    R.layout.snippet_list_row, applist);*/
            appListAdapter=new ApplicationListAdapter(MainActivity.this,applist);
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            progress.dismiss();
            rvAppList.setAdapter(appListAdapter);
            appListAdapter.notifyDataSetChanged();

        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(MainActivity.this, null,
                    "Loading application info...");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(applist, new ApplicationInfo.DisplayNameComparator(packageManager));

        return applist;
    }
}
