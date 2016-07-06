package com.wireless.cse5345.easyvisit;

import java.util.ArrayList;

        import java.util.*;
/**
 * Created by anushasridharan on 6/29/16.
 */
public class LocationHelper {

    //  private  String city;
//  private  String state;
//  private  String street;
//    private  PostalCode postalCode;
    private ArrayList<String> locations = new ArrayList<String>();

//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getStreet() {
//        return street;
//    }
//
//    public void setStreet(String street) {
//        this.street = street;
//    }
//
//    public void setPostalCode(PostalCode postalCode) {
//        this.postalCode = postalCode;
//    }
//
//    public ArrayList<LocationHelper> getLocationListJson() {
//        return locationListJson;
//    }

    public void setLocationListJson(ArrayList<String> locationListJson) {
        this.locations = locationListJson;
    }
}
