package com.exotel.calldialer.models;

public class CallLogBean {
    private String callNumber = "";
    private String callName = "";
    private String callDate = "";
    private String callTyper = "";
    private String isCallNew = "";
    private String callDuration = "";

    public CallLogBean(String callNumber, String callName, String callDate, String callTyper, String isCallNew, String callDuration) {
        this.callNumber = callNumber;
        this.callName = callName;
        this.callDate = callDate;
        this.callTyper = callTyper;
        this.isCallNew = isCallNew;
        this.callDuration = callDuration;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getCallTyper() {
        return callTyper;
    }

    public void setCallTyper(String callTyper) {
        this.callTyper = callTyper;
    }

    public String getIsCallNew() {
        return isCallNew;
    }

    public void setIsCallNew(String isCallNew) {
        this.isCallNew = isCallNew;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }






}
