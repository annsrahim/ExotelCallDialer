package com.exotel.calldialer.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exotel.calldialer.R;
import com.exotel.calldialer.adapters.CallLogAdapter;
import com.exotel.calldialer.helpers.CallLogHelper;
import com.exotel.calldialer.models.CallLogBean;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class CalllogFragments extends Fragment {

    public static final String title = "Call Log";
    private View rootView;
    private ArrayList<CallLogBean> callLogBeanArrayList = new ArrayList<>();
    private static CallLogAdapter callLogAdapter;
    private ListView lvCallLog;
    public static CalllogFragments newInstance()
    {
        return new CalllogFragments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_calllogs,container,false);
        lvCallLog = (ListView)rootView.findViewById(R.id.list);
        getCallLogs();
        callLogAdapter = new CallLogAdapter(callLogBeanArrayList,getContext());
        lvCallLog.setAdapter(callLogAdapter);
        return rootView;
    }

    private void getCallLogs() {
        Cursor cur = CallLogHelper.getAllCallLogs(getActivity().getContentResolver());
        while (cur.moveToNext())
        {
            String callNumber = cur.getString(cur
                    .getColumnIndex(android.provider.CallLog.Calls.NUMBER));

            String callName = cur
                    .getString(cur
                            .getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME));

            String callDate = cur.getString(cur
                    .getColumnIndex(android.provider.CallLog.Calls.DATE));
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "dd-MMM-yyyy HH:mm");
            String dateString = formatter.format(new Date(Long
                    .parseLong(callDate)));

            String callType = cur.getString(cur
                    .getColumnIndex(android.provider.CallLog.Calls.TYPE));

            String isCallNew = cur.getString(cur
                    .getColumnIndex(android.provider.CallLog.Calls.NEW));
            String duration = cur.getString(cur
                    .getColumnIndex(android.provider.CallLog.Calls.DURATION));

            CallLogBean callLogBean = new CallLogBean(callNumber,callName,dateString,callType,isCallNew,duration);
            callLogBeanArrayList.add(callLogBean);
        }

    }
}
