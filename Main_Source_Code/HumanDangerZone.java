// Team Name: Android Optimizers
// Last Day Modified: November 26, 2019
// Project Name: Autonomous Robotic Vehicle (ARV) Application
// Author(s): Jared Peterson

package com.example.arvwearable;
import android.graphics.Color;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;

import java.util.Arrays;
import java.util.List;

public class HumanDangerZone {

    private final List<PatternItem> PATTERN =
            Arrays.asList(new Dash(7), new Gap(7));

    /* Each DroneDangerZone instance will have a latitude, longitude,
       boolean (true = visible, false = hidden), the GoogleMap created when
       the application starts up, and a Circle instance.
     */

    private double lat;
    private double lon;
    private boolean visibility;
    private GoogleMap map;
    private Circle circle;

    // Default constructor
    public HumanDangerZone() {
        this.lat = 0.0;
        this.lon = 0.0;
        this.visibility = false;
    }

    // Constructor
    public HumanDangerZone(double lon, double lat, boolean visibility, GoogleMap map){
        this.lat = lat;
        this.lon = lon;
        this.visibility = visibility;
        this.map = map;
        this.circle = this.map.addCircle(new CircleOptions()
                .center(new LatLng(this.lat, this.lon))
                .radius(10)
                .zIndex(10)
                .strokeWidth(5)
                .strokeColor(Color.argb(127, 255, 0, 0))
                .fillColor(Color.argb(127, 255, 0, 0))
                .strokePattern(PATTERN)
                .visible(this.visibility));
    }

    // Setters
    public void setLat(double lat){ this.lat = lat; }
    public void setLon(double lon){ this.lon = lon; }
    public void setVisibility(boolean visibility){ this.visibility = visibility; }
    public void setMap(GoogleMap map){ this.map = map; }
    public void setCircle(Circle circle){ this.circle = circle; }

    // Getters
    public double getLat(){ return this.lat; }
    public double getLon(){ return this.lon; }
    public boolean getVisibility(){ return this.visibility; }
    public GoogleMap getMap(){ return this.map; }
    public Circle getCircle(){ return this.circle; }

    // Updates already made circle on the map
    // should be called after making changes to attributes
    public void updateCircle(){
        this.circle.setCenter(new LatLng(this.lat, this.lon));
        this.circle.setVisible(this.visibility);
    }

} // end class
