package com.travelexpensetracker.model;

/**
 * Created by Rini Banerjee on 31-08-2017.
 */

public class Trip {

    String sTripId;
    String sTripName;
    String sTripDescription;
    String sTripDate;
    String sTripCurrency;

    public Trip() {
    }

    public Trip(String sTripId, String sTripName, String sTripDescription, String sTripDate, String sTripCurrency) {
        this.sTripId = sTripId;
        this.sTripName = sTripName;
        this.sTripDescription = sTripDescription;
        this.sTripDate = sTripDate;
        this.sTripCurrency = sTripCurrency;
    }

    public String getsTripCurrency() {
        return sTripCurrency;
    }

    public String getsTripId() {
        return sTripId;
    }

    public String getsTripName() {
        return sTripName;
    }

    public String getsTripDescription() {
        return sTripDescription;
    }

    public String getsTripDate() {
        return sTripDate;
    }
}
