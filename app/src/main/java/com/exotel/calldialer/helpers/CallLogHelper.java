package com.exotel.calldialer.helpers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class CallLogHelper {

    public static Cursor getAllCallLogs(ContentResolver cr)
    {
        String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
        Uri callUri = Uri.parse("content://call_log/calls");

        return cr.query(callUri, null, null, null, strOrder);
    }

}
