package com.example.demo.locationLabel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LocationInfo {

    @Id
    @GeneratedValue
    private String locationName;
    private boolean hasSDH = false;
    private boolean hasOTN = false;

    private boolean SDHStatus = true;
    private boolean OTNStatus = true;

    public LocationInfo( String locationName, boolean hasSDH, boolean hasOTN, boolean SDHStatus, boolean OTNStatus) {
        this.locationName = locationName;
        this.hasSDH = hasSDH;
        this.hasOTN = hasOTN;
        this.SDHStatus = SDHStatus;
        this.OTNStatus = OTNStatus;
    }

    public LocationInfo() {

    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public boolean hasSDH() {
        return hasSDH;
    }

    public void setHasSDH(boolean hasSDH) {
        this.hasSDH = hasSDH;
    }

    public boolean hasOTN() {
        return hasOTN;
    }

    public void setHasOTN(boolean hasOTN) {
        this.hasOTN = hasOTN;
    }

    public boolean getSDHStatus() {
        return SDHStatus;
    }

    public void setSDHStatus(boolean SDHStatus) {
        this.SDHStatus = SDHStatus;
    }

    public boolean getOTNStatus() {
        return OTNStatus;
    }

    public void setOTNStatus(boolean OTNStatus) {
        this.OTNStatus = OTNStatus;
    }
}
