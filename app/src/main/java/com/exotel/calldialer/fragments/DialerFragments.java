package com.exotel.calldialer.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.exotel.calldialer.R;
import com.exotel.calldialer.helpers.Utils;

public class DialerFragments extends Fragment implements View.OnClickListener {

    public static final String title = "Dial";
    private View rootView;
    private EditText edPhoneNumber;

    public static DialerFragments newInstance()
    {
        return new DialerFragments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView =  inflater.inflate(R.layout.fragment_dialer,container,false);
        initUI();
        return rootView;
    }


    @Override
    public void onClick(View v) {
        String phoneNo = edPhoneNumber.getText().toString();

        switch (v.getId())
        {
            case R.id.btnOne:
                phoneNo += "1";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnTwo:
                phoneNo += "2";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnThree:
                phoneNo += "3";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnFour:
                phoneNo += "4";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnFive:
                phoneNo += "5";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnSix:
                phoneNo += "6";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnSeven:
                phoneNo += "7";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnEight:
                phoneNo += "8";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnNine:
                phoneNo += "9";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnZero:
                phoneNo += "0";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnAterisk:
                phoneNo += "*";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnHash:
                phoneNo += "#";
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnClear:
                if (phoneNo != null && phoneNo.length() > 0) {
                    phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                }
                edPhoneNumber.setText(phoneNo);
                break;
            case R.id.btnCall:
                if (phoneNo.trim().equals("")) {
                    Utils.showToast(getActivity(),"Please enter a number to call on!");
                } else {
                    Boolean isHash = false;
                    Utils.makeCall(getContext(),phoneNo);

                }

                break;
        }


    }

    private void initUI() {

        edPhoneNumber = (EditText)rootView.findViewById(R.id.edtPhoneNumber);
        edPhoneNumber.setCursorVisible(false);
       edPhoneNumber.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {

               return true;
           }
       });


        Button btnOne  = (Button)rootView.findViewById(R.id.btnOne);
        Button btnTwo  = (Button)rootView.findViewById(R.id.btnTwo);
        Button btnThree  = (Button)rootView.findViewById(R.id.btnThree);
        Button btnFour  = (Button)rootView.findViewById(R.id.btnFour);
        Button btnFive  = (Button)rootView.findViewById(R.id.btnFive);
        Button btnSix  = (Button)rootView.findViewById(R.id.btnSix);
        Button btnSeven  = (Button)rootView.findViewById(R.id.btnSeven);
        Button btnEight  = (Button)rootView.findViewById(R.id.btnEight);
        Button btnNine  = (Button)rootView.findViewById(R.id.btnNine);
        Button btnZero  = (Button)rootView.findViewById(R.id.btnZero);
        Button btnAterisk  = (Button)rootView.findViewById(R.id.btnAterisk);
        Button btnHash  = (Button)rootView.findViewById(R.id.btnHash);
        Button btnCall  = (Button)rootView.findViewById(R.id.btnCall);
        Button btnClear  = (Button)rootView.findViewById(R.id.btnClear);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnAterisk.setOnClickListener(this);
        btnHash.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnClear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                edPhoneNumber.setText("");
                return false;
            }
        });

    }
}
