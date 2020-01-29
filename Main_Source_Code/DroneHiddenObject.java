// Team Name: Android Optimizers
// Last Day Modified: December 5, 2019
// Project Name: Autonomous Robotic Vehicle (ARV) Application
// Author(s): Jared Peterson
package com.example.arvwearable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DroneHiddenObject {

    /* List of members. Each DroneHiddenObject instance will have these members. */
    private double lat;
    private double lon;
    private boolean visibility;
    private GoogleMap map;
    private Marker marker;
    private Context context;

    // Default constructor
    public DroneHiddenObject() {

        this.lat = 0.0;
        this.lon = 0.0;
        this.visibility = false;

    } // end default constructor

    // Constructor
    public DroneHiddenObject(double lon, double lat, boolean visibility, GoogleMap map, Context context){

        this.lat = lat;
        this.lon = lon;
        this.visibility = visibility;
        this.map = map;
        this.context = context;

        Bitmap bmap1 = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.drone_hidden_object_flag);
        Bitmap bmap2 = Bitmap.createScaledBitmap(bmap1, 30, 30, false);
        BitmapDescriptor bmapDesc = BitmapDescriptorFactory.fromBitmap(bmap2);
        this.marker = this.map.addMarker(new MarkerOptions().position(new LatLng(this.lat, this.lon)));
        this.marker.setIcon(bmapDesc);
        this.marker.setVisible(this.visibility);

    } // End constructor

    // Function setters
    public void setLat(double lat){ this.lat = lat; }
    public void setLon(double lon){ this.lon = lon; }
    public void setVisibility(boolean visibility){ this.visibility = visibility; }
    public void setMap(GoogleMap map){ this.map = map; }
    public void setMarker(Marker marker){ this.marker = marker; }

    // Function getters
    public double getLat(){ return this.lat; }
    public double getLon(){ return this.lon; }
    public boolean getVisibility(){ return this.visibility; }
    public GoogleMap getMap(){ return this.map; }
    public Marker getMarker(){ return this.marker; }

    // Add danger zone to map.
    public void updateMarker(){
        this.marker.setPosition(new LatLng(this.lat, this.lon));
        this.marker.setVisible(this.visibility);
    } // end updateMarker

} // end DroneHiddenObject
