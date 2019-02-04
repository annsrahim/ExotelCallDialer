package com.exotel.calldialer.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.exotel.calldialer.R;
import com.exotel.calldialer.adapters.HomePagerAdapter;
import com.exotel.calldialer.helpers.Utils;
import com.exotel.calldialer.helpers.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkAndRequestPermissions())
            {
                goToHomeActivity();
            }
        }
        else
        {
            goToHomeActivity();
        }



    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkAndRequestPermissions() {
        int permissionReadContacts = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS);


        int permissionReadCallLogs = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALL_LOG);

        int permissionMakeCall = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE);


        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionReadCallLogs != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CALL_LOG);
        }
        if (permissionMakeCall != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (permissionReadContacts != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), config.REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        else
        {
            goToHomeActivity();
        }

        return true;
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == config.REQUEST_ID_MULTIPLE_PERMISSIONS){
            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                    if(shouldShowRequestPermissionRationale(permissions[i])){
                        new AlertDialog.Builder(this)
                                .setMessage("Your error message here")
                                .setPositiveButton("Allow", (dialog, which) -> checkAndRequestPermissions())
                                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                                .create()
                                .show();
                    }
                    return;
                }
            }
          checkAndRequestPermissions();
        }
    }

    }
