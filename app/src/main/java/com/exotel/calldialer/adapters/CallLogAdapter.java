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

import java.util.ArrayList;

public class CallLogAdapter extends ArrayAdapter<CallLogBean> implements View.OnClickListener {

    private ArrayList<CallLogBean> dataSet;
    Context mContext;

    public CallLogAdapter(ArrayList<CallLogBean> data, Context context) {
        super(context, R.layout.call_log_item,data);
        this.dataSet = data;
        this.mContext = context;
    }

    private static class ViewHolder
    {
        TextView tvContact;
        TextView tvCallDate;
        TextView tvCallDuration;
        ImageView ivCallType;
        ImageView ivMakeCall;

    }


    private int lastPosition = -1;
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {

        final CallLogBean callLogBean = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if(convertView==null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.call_log_item,parent,false);
            viewHolder.tvContact = (TextView)convertView.findViewById(R.id.tvContact);
            viewHolder.tvCallDate = (TextView)convertView.findViewById(R.id.tvCallDate);
            viewHolder.tvCallDuration = (TextView)convertView.findViewById(R.id.tvCallDuration);
            viewHolder.ivCallType = (ImageView)convertView.findViewById(R.id.ivCallType);
            viewHolder.ivMakeCall = (ImageView)convertView.findViewById(R.id.ivMakeCall);
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

        assert callLogBean != null;
        if(callLogBean.getCallName()!=null)
             viewHolder.tvContact.setText(callLogBean.getCallName());
       else
           viewHolder.tvContact.setText(callLogBean.getCallNumber());

       viewHolder.tvCallDate.setText(callLogBean.getCallDate());
       String duration = Utils.timeConversion(Integer.parseInt(callLogBean.getCallDuration()));
       viewHolder.tvCallDuration.setText(duration);

       if(callLogBean.getCallTyper().equals("1"))
        viewHolder.ivCallType.setImageResource(android.R.drawable.sym_call_incoming);
       else if(callLogBean.getCallTyper().equals("2"))
        viewHolder.ivCallType.setImageResource(android.R.drawable.sym_call_outgoing);
       else
        viewHolder.ivCallType.setImageResource(android.R.drawable.stat_notify_missed_call);
       viewHolder.ivMakeCall.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Utils.makeCall(mContext,dataSet.get(position).getCallNumber());
           }
       });

        return convertView;
    }

    @Override
    public void onClick(View v) {

    }
}
