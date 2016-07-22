package com.webmyne.applocker.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.webmyne.applocker.R;

import java.util.List;

/**
 * Created by vaibhavirana on 21-07-2016.
 */
public class ApplicationListAdapter extends RecyclerView.Adapter<ApplicationListAdapter.AppViewHolder> {

    //private final List<String> appList;
    private List<ApplicationInfo> appList = null;
    private  Context mContext;
    private PackageManager packageManager;

    public ApplicationListAdapter(Context mContext)
    {
        this.mContext=mContext;
    }
    public ApplicationListAdapter(Context mContext, List<ApplicationInfo> appList)
    {
        this.mContext=mContext;
        this.appList=appList;
        packageManager = mContext.getPackageManager();
    }
    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
        return new AppViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {

        ApplicationInfo applicationInfo = appList.get(position);
        if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
            holder.txtAppPackage.setText("System Application");
        }else
        {
            holder.txtAppPackage.setText("Third-Party Application");
        }
        holder.imgApp.setImageDrawable(applicationInfo.loadIcon(packageManager));
        holder.txtAppName.setText(applicationInfo.loadLabel(packageManager));
       // holder.txtAppPackage.setText(applicationInfo.packageName);
        //appName.setText(applicationInfo.loadLabel(packageManager));
        //packageName.setText(applicationInfo.packageName);
        //iconview.setImageDrawable(applicationInfo.loadIcon(packageManager));
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgApp;
        private TextView txtAppName,txtAppPackage;
        public AppViewHolder(View itemView) {
            super(itemView);

            imgApp=(ImageView)itemView.findViewById(R.id.imgApp);
            txtAppName=(TextView)itemView.findViewById(R.id.txtAppName);
            txtAppPackage=(TextView)itemView.findViewById(R.id.txtAppPackage);
        }
    }
}
