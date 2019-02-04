package com.exotel.calldialer.helpers;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

public class Utils {
    public static void showToast(Context context,String msg)
    {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    public static String timeConversion(int totalSeconds) {

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        int seconds = totalSeconds % SECONDS_IN_A_MINUTE;
        int totalMinutes = totalSeconds / SECONDS_IN_A_MINUTE;
        int minutes = totalMinutes % MINUTES_IN_AN_HOUR;
        int hours = totalMinutes / MINUTES_IN_AN_HOUR;

        return hours + " hours " + minutes + " minutes " + seconds + " seconds";
    }

    public static void makeCall(Context context,String phoneNo)
    {
        if (phoneNo.subSequence(phoneNo.length() - 1, phoneNo.length()).equals("#")) {
            phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
            String callInfo = "tel:" + phoneNo + Uri.encode("#");
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(callInfo));
            context.startActivity(callIntent);
        } else {
            String callInfo = "tel:" + phoneNo;
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(callInfo));
            context.startActivity(callIntent);
        }
    }

    public static void showDialogOK(Context context,String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .create()
                .show();
    }
}
