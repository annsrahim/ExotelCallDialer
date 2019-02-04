package com.exotel.calldialer.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exotel.calldialer.R;
import com.exotel.calldialer.adapters.ContactsAdapter;
import com.exotel.calldialer.models.ContactsBean;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {

    public static final String title = "Contacts";
    private View rootView;
    private ArrayList<ContactsBean> contactsList = new ArrayList<>();
    private ListView list;
    private ContactsAdapter contactsAdapter;

    public static ContactsFragment newInstance()
    {
        return new ContactsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_contacts,container,false);
        list = (ListView)rootView.findViewById(R.id.list);

        new ContactsAsyncTask(getContext()).execute();

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    private  void getContactList() {
        ContentResolver cr = getContext().getContentResolver();
      /*  Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);*/
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null,
                null, ContactsContract.Contacts.DISPLAY_NAME + " ASC ");


        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                String phoneNo = "";
                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String number = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        if(number.equalsIgnoreCase(""))
                            continue;
                        else
                            phoneNo = number;


                    }
                    pCur.close();
                }
                ContactsBean contactsBean = new ContactsBean(id,name,phoneNo);
                contactsList.add(contactsBean);
            }
        }
        if(cur!=null){
            cur.close();
        }

    }

    public void displayContacts()
    {
        if(contactsList!=null)
        {
            contactsAdapter = new ContactsAdapter(contactsList,getContext());
            list.setAdapter(contactsAdapter);
        }

    }

    class ContactsAsyncTask extends AsyncTask<String,String,String>
    {
        private Context mContext;


        public ContactsAsyncTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected String doInBackground(String... strings) {
            getContactList();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            displayContacts();
            super.onPostExecute(s);
        }
    }
}
