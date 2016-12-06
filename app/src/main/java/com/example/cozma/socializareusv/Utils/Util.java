package com.example.cozma.socializareusv.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import com.example.cozma.socializareusv.R;

/**
 * Created by cozma on 17.11.2016.
 */

public class Util {

    //region Activity manager Region
    public static void openActivity(Context ctx, Class c) {
        Intent intent = new Intent(ctx, c);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    public static void openActivityClosingStack(Context ctx, Class c) {
        Intent intent = new Intent(ctx, c);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    public static void openActivityClosingParent(Context ctx, Class c) {
        Intent intent = new Intent(ctx, c);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
        ((Activity) ctx).finish();
    }

    public static Boolean isLocationEnabled(final Context context) {
        LocationManager locationManager = null;
        boolean gpsIsEnabled = false, networkIsEnabled = false;
        if (locationManager == null)
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            gpsIsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        } catch (Exception ex) {
        }
        try {
            networkIsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gpsIsEnabled && !networkIsEnabled) {
            return false;
        }
        return true;
    }

    /**
     * Creet dialog permission use location
     */
    //TODO: Create only ONE alertDialog and not two
    public static void askGpsPermission(final Context context) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(context.getResources().getString(R.string.dialogEnableGps_message));
        dialog.setPositiveButton(context.getResources().getString(R.string.dialogEnableGps_buttonOk),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        settingsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(settingsIntent);
                        //get gps

                    }
                });
        dialog.setNegativeButton(context.getResources().getString(R.string.dialogEnableGps_buttonCancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                        ((Activity) context).finish();
                        ActivityCompat.finishAffinity((Activity) context);
                        System.exit(0);
                    }
                });
        dialog.setCancelable(false);
        dialog.show();
    }

    //endregion
}
