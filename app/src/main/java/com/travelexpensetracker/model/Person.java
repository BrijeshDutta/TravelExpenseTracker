package com.travelexpensetracker.model;

/**
 * Created by Rini Banerjee on 31-08-2017.
 */

public class Person {
    String sPersonId;
    String sTripId;
    String sPersonName;
    String sPersonMobileNo;
    String sPersonEmailId;
    String sPersonDeposit;

    public Person() {
    }

    public Person(String sPersonName, String sPersonMobileNo, String sPersonEmailId, String sPersonDeposit) {
        this.sPersonName = sPersonName;
        this.sPersonMobileNo = sPersonMobileNo;
        this.sPersonEmailId = sPersonEmailId;
        this.sPersonDeposit = sPersonDeposit;
    }

    public String getsPersonName() {
        return sPersonName;
    }

    public String getsPersonMobileNo() {
        return sPersonMobileNo;
    }

    public String getsPersonEmailId() {
        return sPersonEmailId;
    }

    public String getsPersonDeposit() {
        return sPersonDeposit;
    }

    public String getsPersonId() {
        return sPersonId;
    }

    public void setsPersonId(String sPersonId) {
        this.sPersonId = sPersonId;
    }

    public String getsTripId() {
        return sTripId;
    }

    public void setsTripId(String sTripId) {
        this.sTripId = sTripId;
    }
}
