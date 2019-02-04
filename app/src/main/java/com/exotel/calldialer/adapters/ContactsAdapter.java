package com.exotel.calldialer.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.exotel.calldialer.R;
import com.exotel.calldialer.helpers.Utils;
import com.exotel.calldialer.models.CallLogBean;
import com.exotel.calldialer.models.ContactsBean;

import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter<ContactsBean> implements View.OnClickListener {

    private ArrayList<ContactsBean> dataSet;
    Context mContext;

    public ContactsAdapter(ArrayList<ContactsBean> data, Context context) {
        super(context, R.layout.call_log_item,data);
        this.dataSet = data;
        this.mContext = context;
    }

    private static class ViewHolder
    {
        TextView tvContactName;
        TextView tvContactNumber;

    }


    private int lastPosition = -1;
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {

        final ContactsBean contactsBean = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if(convertView==null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.contact_item,parent,false);
            viewHolder.tvContactName = (TextView)convertView.findViewById(R.id.tvContact);
            viewHolder.tvContactNumber = (TextView)convertView.findViewById(R.id.tvContactNumber);

            result =convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        /*Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;*/

        assert contactsBean != null;
        viewHolder.tvContactName.setText(contactsBean.getContactName());
        viewHolder.tvContactNumber.setText(contactsBean.getContactNumber());



        return result;
    }

    @Override
    public void onClick(View v) {

    }
}
