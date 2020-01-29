// Team Name: Android Optimizers
// Last Day Modified: December 5, 2019
// Project Name: Autonomous Robotic Vehicle (ARV) Application
// Author(s): Jose Franco Baquera,Atiya Kailany,Jared Peterson
package com.example.arvwearable;

// Import required libraries
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import org.json.simple.parser.JSONParser;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.BitmapFactory;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.xbw.ros.PublishEvent;
import de.greenrobot.event.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

// MapsActivity class. This class is responsible for parsing messages from Spitfire. All
// things rendered in the user interface is done by this class.
public class MapsActivity extends FragmentActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, OnMapReadyCallback, View.OnClickListener, NumberPicker.OnValueChangeListener {

    // Declare variables that will be used throughout the program.
    private GoogleMap mMap;
    static final String TAG = MapsActivity.class.getSimpleName();
    private int droneSelected = 1;
    private Drone currentDrone;
    private boolean searchFlag = false;
    private int gameScore = 0;
    private double droneHeight = 25;
    private boolean marker = false;
    private Marker drone0Marker;
    private Marker drone1Marker;
    private Marker drone2Marker;
    private Marker drone3Marker;
    private LatLng tempPosition;
    private LatLng search;
    private Drone drone0;
    private Drone drone1;
    private Drone drone2;
    private Drone drone3;
    private ServerCommunication client;
    private final double originlongitude = -106.75239801428688;
    private final double originlatitude = 32.2810102009863;
    private int loc_id = -1;
    private int loc_idLeft = -1;
    private int loc_idRight = -1;
    private int area_id = -1;
    private TextView droneheight;
    private Drawable drone1Icon;
    private Drawable drone2Icon;
    private Drawable drone3Icon;
    private Drawable drone4Icon;
    private TextView scoreDisplay;
    private boolean connectionMaintained;
    private double Ysim;
    private double Xsim;
    private double Zsim;
    private double humanDangerZoneLon;
    private double humanDangerZoneLat;
    private double droneDangerZoneLon;
    private double droneDangerZoneLat;
    private double humanHiddenObjectLon;
    private double humanHiddenObjectLat;
    private double droneHiddenObjectLon;
    private double droneHiddenObjectLat;
    private long humanHOID;
    private long droneHOID;
    ArrayList<Long> droneHOIDs = new ArrayList<Long>();
    ArrayList<Long> humanHOIDs = new ArrayList<Long>();

    /********************* On Create Method  ******************/
    // This method will be called only once.
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Get width and height values of device where application is being run on.
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        // Communicate with server.
        client = new ServerCommunication();

        // Check if communication is successful. If not, print an error message.
        if ( ! client.connectedCheck() ) {

            // Alert box that will be displayed if Spitfire is off.
            AlertDialog alertDialog = new AlertDialog.Builder(MapsActivity.this).create();
            alertDialog.setTitle("WARNING");
            alertDialog.setMessage("Spitfire server down. Please contact Ahmed Khalaf or Dr. Toups for more information.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Exit Application",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                            System.exit(0);

                        }
                    });
            alertDialog.show();

        } // end if

        else {

            /* Subscribe to all relevant game Topics. */
            client.subscribeUAV0();
            client.subscribeUAV1();
            client.subscribeUAV2();
            client.subscribeUAV3();
            client.subscribeHiddenHuman();
            client.subscribeHiddenDrone();
            client.subscribeDangerDrone();
            client.subscribeDangerHuman();
            client.subscribeScore();
            connectionMaintained = false;

            /* Create 4 separate Drone instances with different ID's. */
            drone0 = new Drone(0);
            drone1 = new Drone(1);
            drone2 = new Drone(2);
            drone3 = new Drone(3);

            /* Initialize important variables to default values. */
            currentDrone = drone0;
            tempPosition = new LatLng(32.281010200986, -106.75239801428688);
            search = new LatLng(32.281010200986, -106.75239801428688);



            // Battery bar initialization, connecting to XML and manually setting it to 70 for now.
            ProgressBar batteryBar = findViewById(R.id.batteryBar);
            batteryBar.setProgress(70, true);

            // Drone selector initialization, connecting to XML, drone options and listener
            NumberPicker numberPicker = findViewById(R.id.dronePicker);
            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(4);
            numberPicker.setOnValueChangedListener(this);

            /* Display the score on screen. */
            scoreDisplay = (TextView) findViewById(R.id.score);
            scoreDisplay.setText("Score: " + gameScore);

            /* Get images that map to hidden objects and danger zones.*/
            Drawable humanObject = getApplicationContext().getResources().getDrawable( R.drawable.human_hidden_object);
            Drawable droneObject = getApplicationContext().getResources().getDrawable( R.drawable.drone_hidden_object);
            Drawable humanDanger = getApplicationContext().getResources().getDrawable( R.drawable.human_danger_zone);
            Drawable droneDanger = getApplicationContext().getResources().getDrawable( R.drawable.drone_danger_zone);

            /* Used to display the legend. */
            TextView humanHiddenObject = (TextView) findViewById(R.id.humanHiddenObject);
            TextView droneHiddenObject = (TextView) findViewById(R.id.droneHiddenObject);
            TextView humanDangerZone = (TextView) findViewById(R.id.humanDangerZone);
            TextView droneDangerZone = (TextView) findViewById(R.id.droneDangerZone);

            humanHiddenObject.setCompoundDrawablesWithIntrinsicBounds( humanObject, null, null, null);
            droneHiddenObject.setCompoundDrawablesWithIntrinsicBounds( droneObject, null, null, null);
            humanDangerZone.setCompoundDrawablesWithIntrinsicBounds( humanDanger, null, null, null);
            droneDangerZone.setCompoundDrawablesWithIntrinsicBounds( droneDanger, null, null, null);


            /* Display the height of drone. */
            droneheight = (TextView) findViewById(R.id.droneheight);

            /* Get images that map to virtual drones.*/
            drone1Icon = getApplicationContext().getResources().getDrawable( R.drawable.drone1resized);
            drone2Icon = getApplicationContext().getResources().getDrawable( R.drawable.drone2resized);
            drone3Icon = getApplicationContext().getResources().getDrawable( R.drawable.drone3resized);
            drone4Icon = getApplicationContext().getResources().getDrawable( R.drawable.drone4resized);

            /* Create all relevant buttons and set listeners. */
            Button sendButton = findViewById(R.id.sendButton);
            sendButton.setOnClickListener(this);

            Button landButton = findViewById(R.id.landButton);
            landButton.setOnClickListener(this);

            Button searchButton = findViewById(R.id.searchButton);
            searchButton.setOnClickListener(this);

            Button ascendButton = findViewById(R.id.ascendButton);
            ascendButton.setOnClickListener(this);

            Button descendButton = findViewById(R.id.descendButton);
            descendButton.setOnClickListener(this);

            /* Register class to event bus. */
            EventBus.getDefault().register(this);

        } // end else

    } /* end OnCreate function. */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        /* Check that connection with spitfire was successful. */
        if( client.connectedCheck() ) {

            mMap = googleMap;
            mMap.getUiSettings().setMapToolbarEnabled(true);

            int width = 30;
            int height = 30;

            /* While loop that will loop until coordinates are successfully initialized from Spitfire. */
            while (drone0.getX() == 0 || drone0.getY() == 0 ||
                    drone1.getX() == 0 || drone1.getY() == 0 ||
                    drone2.getX() == 0 || drone2.getY() == 0 ||
                    drone3.getX() == 0 || drone3.getY() == 0) ;

            /* Get the starting positions of drones. */
            LatLng drone0StartPos = new LatLng(drone0.getY(), drone0.getX());
            LatLng drone1StartPos = new LatLng(drone1.getY(), drone1.getX());
            LatLng drone2StartPos = new LatLng(drone2.getY(), drone2.getX());
            LatLng drone3StartPos = new LatLng(drone3.getY(), drone3.getX());

            //Drone 1 Initialization
            Bitmap d1 = BitmapFactory.decodeResource(getResources(), R.drawable.drone1);
            Bitmap dr1 = Bitmap.createScaledBitmap(d1, width, height, false);
            BitmapDescriptor drn1 = BitmapDescriptorFactory.fromBitmap(dr1);
            drone0Marker = mMap.addMarker(new MarkerOptions().position(drone0StartPos));
            drone0Marker.setIcon(drn1);

            //Drone 2 Initialization
            Bitmap d2 = BitmapFactory.decodeResource(getResources(), R.drawable.drone2);
            Bitmap dr2 = Bitmap.createScaledBitmap(d2, width, height, false);
            BitmapDescriptor drn2 = BitmapDescriptorFactory.fromBitmap(dr2);
            drone1Marker = mMap.addMarker(new MarkerOptions().position(drone1StartPos));
            drone1Marker.setIcon(drn2);

            //Drone 3 Initialization
            Bitmap d3 = BitmapFactory.decodeResource(getResources(), R.drawable.drone3);
            Bitmap dr3 = Bitmap.createScaledBitmap(d3, width, height, false);
            BitmapDescriptor drn3 = BitmapDescriptorFactory.fromBitmap(dr3);
            drone2Marker = mMap.addMarker(new MarkerOptions().position(drone2StartPos));
            drone2Marker.setIcon(drn3);

            //Drone 4 Initialization
            Bitmap d4 = BitmapFactory.decodeResource(getResources(), R.drawable.drone4);
            Bitmap dr4 = Bitmap.createScaledBitmap(d4, width, height, false);
            BitmapDescriptor drn4 = BitmapDescriptorFactory.fromBitmap(dr4);
            drone3Marker = mMap.addMarker(new MarkerOptions().position(drone3StartPos));
            drone3Marker.setIcon(drn4);

            // Height handler thread. This will will update the height of the currently chosen drone being
            // displayed on the user interface every 1 millisecond.
            final Handler heightHandler = new Handler();
            heightHandler.post(new Runnable() {

                @Override
                public void run() {

                    // Get current altitude.
                    droneHeight = currentDrone.getZ();

                    // Display current chosen drone and its height.
                    switch (droneSelected) {

                        case 1:
                            droneheight.setText("Drone 1 Height: " + String.format("%.2f", droneHeight));
                            droneheight.setCompoundDrawablesWithIntrinsicBounds( drone1Icon, null, null, null);
                            break;
                        case 2:
                            droneheight.setText("Drone 2 Height: " + String.format("%.2f", droneHeight));
                            droneheight.setCompoundDrawablesWithIntrinsicBounds( drone2Icon, null, null, null);
                            break;
                        case 3:
                            droneheight.setText("Drone 3 Height: " + String.format("%.2f", droneHeight));
                            droneheight.setCompoundDrawablesWithIntrinsicBounds( drone3Icon, null, null, null);
                            break;
                        case 4:
                            droneheight.setText("Drone 4 Height: " + String.format("%.2f", droneHeight));
                            droneheight.setCompoundDrawablesWithIntrinsicBounds( drone4Icon, null, null, null);
                            break;

                    } // end switch

                    // Post delay handler every 1 millisecond
                    heightHandler.postDelayed(this, 1);

                } // end run

            }); // end post

            // Hidden objects and danger zones handler thread. This will will update the danger zones and hidden
            // objects being displayed on the user interface every 1 millisecond.
            final Handler dangerZoneHiddenObject = new Handler();
            dangerZoneHiddenObject.post(new Runnable() {

                @Override
                public void run() {

                    // Check if human danger zone was found.
                    if( humanDangerZoneLon != 0 && humanDangerZoneLat != 0) {
                        HumanDangerZone hDZ1 = new HumanDangerZone(humanDangerZoneLon, humanDangerZoneLat, true, mMap);
                        humanDangerZoneLon = 0;
                        humanDangerZoneLat = 0;
                     } // end if

                    // Check if drone danger found was found.
                    if( droneDangerZoneLon != 0 && droneDangerZoneLat != 0) {
                        DroneDangerZone dDZ1 = new DroneDangerZone(droneDangerZoneLon, droneDangerZoneLat, true, mMap);
                        droneDangerZoneLon = 0;
                        droneDangerZoneLat = 0;
                    } // end if

                    // Check if human hidden object was found.
                    if( humanHiddenObjectLon != 0 && humanHiddenObjectLat != 0 && !humanHOIDs.contains(humanHOID)) {
                        HumanHiddenObject hHO1 = new HumanHiddenObject(humanHiddenObjectLon, humanHiddenObjectLat, true, mMap, getApplicationContext());
                        humanHOIDs.add(humanHOID);
                        humanHiddenObjectLon = 0;
                        humanHiddenObjectLat = 0;
                        //updates score by adding 5 points when a human hidden object is found
                        gameScore += 5;
                        scoreDisplay.setText("Score: " + gameScore);
                    } // end if

                    // Check if drone hidden object was found.
                    if( droneHiddenObjectLon != 0 && droneHiddenObjectLat != 0 && !droneHOIDs.contains(droneHOID)) {
                        DroneHiddenObject dHO1 = new DroneHiddenObject(droneHiddenObjectLon, droneHiddenObjectLat, true, mMap, getApplicationContext());
                        droneHOIDs.add(droneHOID);
                        droneHiddenObjectLon = 0;
                        droneHiddenObjectLat = 0;
                        //updates score by adding 15 points when a drone hidden object is found
                        gameScore += 15;
                        scoreDisplay.setText("Score: " + gameScore);
                    } // end if

                    // Post delay handler every 1 millisecond
                    dangerZoneHiddenObject.postDelayed(this, 1);

                } // end run

            }); // end post

            // Drone 1 handler thread. This will will update the position of drone 1
            // objects being displayed on the user interface every 1 millisecond.
            final Handler drone1handler = new Handler();
            drone1handler.post(new Runnable() {

                @Override
                public void run() {
                    final LatLng tempDrone1Pos = new LatLng(drone0.getY(), drone0.getX());
                    drone0Marker.setPosition(tempDrone1Pos);
                    drone1handler.postDelayed(this, 1);
                } // end run

            }); // end post

            // Drone 2 handler thread. This will will update the position of drone 2
            // objects being displayed on the user interface every 1 millisecond.
            final Handler drone2handler = new Handler();
            drone2handler.post(new Runnable() {

                @Override
                public void run() {
                    LatLng tempDrone2Pos = new LatLng(drone1.getY(), drone1.getX());
                    drone1Marker.setPosition(tempDrone2Pos);
                    drone2handler.postDelayed(this, 1);

                } // end run

            }); // end post

            // Drone 3 handler thread. This will will update the position of drone 3
            // objects being displayed on the user interface every 1 millisecond.
            final Handler drone3handler = new Handler();
            drone3handler.post(new Runnable() {

                @Override
                public void run() {
                    final LatLng tempDrone3Pos = new LatLng(drone2.getY(), drone2.getX());
                    drone2Marker.setPosition(tempDrone3Pos);
                    drone3handler.postDelayed(this, 1);

                } // end run

            }); // end post

            // Drone 4 handler thread. This will will update the position of drone 4
            // objects being displayed on the user interface every 1 millisecond.
            final Handler drone4handler = new Handler();
            drone4handler.post(new Runnable() {

                @Override
                public void run() {
                    final LatLng tempDrone4Pos = new LatLng(drone3.getY(), drone3.getX());
                    drone3Marker.setPosition(tempDrone4Pos);
                    drone4handler.postDelayed(this, 1);

                } // end run

            }); // end post

            // Set a map click listener on the Google Map interface.
            mMap.setOnMapClickListener(new OnMapClickListener() {

                // Temporary marker.
                Marker temp;

                // Method that will be called whenever a user clicks on the Google maps.
                @Override
                public void onMapClick(LatLng latLng) {

                    // Remove old marker
                    if (marker) {
                        temp.remove();
                    } // end if

                    // Add new marker that was chosen by user.
                    temp = mMap.addMarker(new MarkerOptions().position(latLng));
                    marker = true;
                    tempPosition = latLng;

                    // For spitfire usage: search is top left (old)
                    // tempPosition is bottom right (new) of imaginary square area.
                    // This will only execute if the first coordinate was chosen by the user already.
                    if (searchFlag) {

                        // Convert user chosen coordinates to Spitfire coordinates.
                        Xsim = ((tempPosition.longitude - originlongitude) / 0.0000089 * Math.cos(originlatitude * 0.018));
                        Ysim = ((tempPosition.latitude - originlatitude) / 0.0000089);
                        Zsim = currentDrone.getZ();

                        // Altitude should always be greater than 8.5 unless it's landed.
                        if (Zsim < 8.5)
                            Zsim = 15;

                        // Request the search service. This will assume that the first coordinate request was granted.
                        ServerCommunication.sendDrone(Xsim, Ysim, Zsim);

                        // Variables that will allow us to change the color of the "imaginary square" (Search Area).
                        int red=0, green=0, blue=0, Sred=0, Sgreen=0, Sblue=0;

                        switch (droneSelected) {
                            //blue
                            case 1: Sred=0; Sgreen=0; Sblue=255;
                                    red=135; green=206; blue=250;
                                    break;
                            //red
                            case 2: Sred=255; Sgreen=0; Sblue=0;
                                    red=220; green=20; blue=60;
                                break;
                            //green
                            case 3: Sred=0; Sgreen=255; Sblue=0;
                                    red=60; green=179; blue=113;
                                break;
                            //magenta
                            case 4: Sred=75; Sgreen=0; Sblue=130;
                                    red=255; green=0; blue=255;
                                break;
                        } // end if.

                        // Display the actual search area depending on which drone is currently chosen,
                        // and with the correct corresponding color.
                        Polygon searchArea = mMap.addPolygon(new PolygonOptions()
                                .add(new LatLng(search.latitude, search.longitude),
                                        new LatLng(search.latitude, tempPosition.longitude),
                                        new LatLng(tempPosition.latitude, tempPosition.longitude),
                                        new LatLng(tempPosition.latitude, search.longitude))
                                .strokeColor(Color.argb(30, Sred, Sgreen, Sblue))
                                .fillColor(Color.argb(15, red, green, blue)));

                        // Flag
                        //searchFlag = false;

                    } // end if.

                    // Update search flag.
                    search = tempPosition;

                } // end onMapClick

            }); // end onlistener.

            // This line removes google maps labels
            mMap.setMapStyle(new MapStyleOptions(getResources().getString(R.string.style_json)));

            // Set nmsu bounds
            LatLng nmsuTopRight = new LatLng(32.285919, -106.739148);
            LatLng nmsuBottomLeft = new LatLng(32.275397, -106.754770);

            // Create bounds
            LatLngBounds NMSUBounds = new LatLngBounds(nmsuBottomLeft, nmsuTopRight);

            // Move camera to bounds
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32.281210, -106.752111), mMap.getMaxZoomLevel() - 5));

            // Set bounds for camera
            mMap.setLatLngBoundsForCameraTarget(NMSUBounds);

            // Set max zoom
            mMap.setMinZoomPreference(mMap.getMaxZoomLevel() - 4);

            // Disable marker camera centering
            mMap.setOnMarkerClickListener( new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick ( Marker marker ) {
                    // do nothing
                    return true;
                }
            });

            // Check if location of tablet can get requested.
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationButtonClickListener(this);
                mMap.setOnMyLocationClickListener(this);
            } else {
                // Show rationale and request permission.
                Toast.makeText(this, "please Enable Location Settings", Toast.LENGTH_LONG).show();
            }

            // Setup building polygons
            BuildingPolygons.setUpBuildings(mMap);

        }

    } // end onMpayReady

    // onEvent method that will parse messages from Spitfire.
    public void onEvent(PublishEvent newEvent) {

        if( client.connectedCheck() ) {

            // add_location event published for sending a drone.
            if (newEvent.name.equals("/urs_wearable/add_location") && !searchFlag) {

                JSONObject temp1 = null;
                try {

                    temp1 = new JSONObject(newEvent.msg);
                    loc_id = temp1.getInt("loc_id");
                    ServerCommunication.setGoalAt(droneSelected - 1, loc_id);

                } // end try
                catch (JSONException e) { e.printStackTrace(); }

            } // end if.

            // add_location event published for making a drone search; First coordinate.
            else if (newEvent.name.equals("/urs_wearable/add_location") && searchFlag && loc_idLeft == -1) {

                JSONObject temp1 = null;
                try {

                    temp1 = new JSONObject(newEvent.msg);
                    loc_idLeft = temp1.getInt("loc_id");

                } // end try
                catch (JSONException e) { e.printStackTrace(); }

            } // end else if.

            // add_location event published for making a drone search; Second coordinate.
            else if (newEvent.name.equals("/urs_wearable/add_location") && searchFlag) {

                JSONObject temp1 = null;
                try {

                    temp1 = new JSONObject(newEvent.msg);
                    loc_idRight = temp1.getInt("loc_id");
                    ServerCommunication.search(loc_idLeft, loc_idRight);
                    searchFlag = false;
                    loc_idLeft = -1;

                } // end try

                catch (JSONException e) { e.printStackTrace(); }

            } // end else if.

            // add_location event published for making a drone search; Actual area.
            else if (newEvent.name.equals("/urs_wearable/add_area")) {

                JSONObject temp1 = null;
                try {

                    temp1 = new JSONObject(newEvent.msg);
                    area_id = temp1.getInt("area_id");
                    ServerCommunication.searchReady(droneSelected - 1, area_id);

                } // end try.

                catch (JSONException e) { e.printStackTrace(); }

            } // end else if

            // Drone danger zones event published.
            else if (newEvent.name.equals("/w_ddzcoordinates")) {

                try {

                    Object obj = new JSONParser().parse(newEvent.msg);
                    org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;
                    String test100 = (String) ((org.json.simple.JSONObject) obj).get("data");
                    String doubleQoute = test100.replace('\'', '\"');
                    Object obj2 = new JSONParser().parse(doubleQoute);
                    org.json.simple.JSONObject jo2 = (org.json.simple.JSONObject) obj2;
                    droneDangerZoneLon = (double) jo2.get("longitude");
                    droneDangerZoneLat = (double) jo2.get("latitude");

                } // end try

                catch ( Exception e ) { Log.e(TAG, "exception ");}

            } // end else if

            // Human danger zones events published.
            else if (newEvent.name.equals("/w_hdzcoordinates")) {

                try {
                    Object obj = new JSONParser().parse(newEvent.msg);
                    org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;
                    String test100 = (String) ((org.json.simple.JSONObject) obj).get("data");
                    String doubleQoute = test100.replace('\'', '\"');
                    Object obj2 = new JSONParser().parse(doubleQoute);
                    org.json.simple.JSONObject jo2 = (org.json.simple.JSONObject) obj2;
                    humanDangerZoneLon = (double) jo2.get("longitude");
                    humanDangerZoneLat = (double) jo2.get("latitude");

                } // end try

                catch( Exception e ) { Log.e(TAG, "exception ");}

            } // end else if.

            // Human hidden objects event published.
            else if (newEvent.name.equals("/w_hccoordinates")) {

                try {
                    Object obj = new JSONParser().parse(newEvent.msg);
                    org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;
                    String test100 = (String) ((org.json.simple.JSONObject) obj).get("data");
                    String doubleQoute = test100.replace('\'', '\"');
                    Object obj2 = new JSONParser().parse(doubleQoute);
                    org.json.simple.JSONObject jo2 = (org.json.simple.JSONObject) obj2;
                    humanHiddenObjectLon = (double) jo2.get("longitude");
                    humanHiddenObjectLat = (double) jo2.get("latitude");
                    humanHOID = (long) jo2.get("drone_clue_id");

                    // Debug print statements.
                    Log.e(TAG, "Human Hidden");
                    Log.e(TAG, newEvent.msg);

                } // end try

                catch( Exception e ) { Log.e(TAG, "exception "); }

            } // end else if

            // Drone hidden objects event published.
            else if (newEvent.name.equals("/w_dccoordinates")) {

                try {

                    Object obj = new JSONParser().parse(newEvent.msg);
                    org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;
                    String test100 = (String) ((org.json.simple.JSONObject) obj).get("data");
                    String doubleQoute = test100.replace('\'', '\"');
                    Object obj2 = new JSONParser().parse(doubleQoute);
                    org.json.simple.JSONObject jo2 = (org.json.simple.JSONObject) obj2;
                    droneHiddenObjectLon = (double) jo2.get("longitude");
                    droneHiddenObjectLat = (double) jo2.get("latitude");
                    droneHOID = (long) jo2.get("drone_clue_id");

                    // Debug print statements.
                    Log.e(TAG, "Drone Hidden");
                    Log.e(TAG, newEvent.msg);

                } // end try
                catch( Exception e ) { Log.e(TAG, "exception ");}

            } // end else if

        } // end if.

    } // end onEvent

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current Location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation Button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    /*
     * Number Picker value change listener
     * A switch statement is used to distinguish which value is selected
     * and based on that value, the corresponding drone id will be sent
     * to the server.
     *
     * Based on the chosen drone, we updates a couple of global variables
     * that are being used in different places throughout this script.
     *
     */
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        // Change the current drone to the drone selected by the user.
        if ( client.connectedCheck() ) {

            switch (newVal) {

                case 1:
                    droneSelected = 1;
                    currentDrone = drone0;
                    break;

                case 2:
                    droneSelected = 2;
                    currentDrone = drone1;
                    break;

                case 3:
                    droneSelected = 3;
                    currentDrone = drone2;
                    break;

                case 4:
                    droneSelected = 4;
                    currentDrone = drone3;
                    break;

            } // end switch

        } // end if
    }

    /*
     * Button Onclick action listener
     * A switch statement is used to distinguish which button is clicked
     * and based on that action, the proper method will be called from
     * the server.
     *
     *
     */
    @Override
    public void onClick(final View v) {

        if (client.connectedCheck()) {

            switch (v.getId()) {

                case R.id.sendButton:

                    /* Check that at least one coordinate was chosen by the user. */
                    if (!marker) {
                        Toast.makeText(getApplicationContext(), "Place marker before using send functionality", Toast.LENGTH_SHORT).show();
                    } // end if

                    /* If the coordinate is chosen, continue normally. */
                    if (marker) {

                        // Display meaningful message.
                        Toast.makeText(this, "Send Button Clicked", Toast.LENGTH_SHORT).show();

                        // Convert the coordinate chosen by users to Spitfire coordinates.
                        Xsim = ((tempPosition.longitude - originlongitude) / 0.0000089 * Math.cos(originlatitude * 0.018));
                        Ysim = ((tempPosition.latitude - originlatitude) / 0.0000089);
                        Zsim = currentDrone.getZ();

                        if (Zsim < 8.5)
                            Zsim = 15;

                        // Request the service from Spitfire.
                        ServerCommunication.sendDrone(Xsim, Ysim, Zsim);
                        marker = true;

                    } // end if

                    break;

                case R.id.landButton:

                    // Display a meaningful message and request landing service.
                    Toast.makeText(this, "Land Button Clicked", Toast.LENGTH_SHORT).show();
                    ServerCommunication.droneLand(droneSelected - 1);
                    break;

                case R.id.searchButton:

                    // Check that the first marker is chosen by the user.
                    if (!marker) {
                        Toast.makeText(getApplicationContext(), "Place first marker before using search functionality", Toast.LENGTH_SHORT).show();
                    } // end if.

                    // If first marker is chosen already, continue normally.
                    if (marker) {

                        searchFlag = true;
                        Xsim = ((search.longitude - originlongitude) / 0.0000089 * Math.cos(originlatitude * 0.018));
                        Ysim = ((search.latitude - originlatitude) / 0.0000089);
                        Zsim = currentDrone.getZ();

                        if (Zsim < 8.5)
                            Zsim = 15;

                        // Request service from Spitfire.
                        ServerCommunication.sendDrone(Xsim, Ysim, Zsim);

                        Toast.makeText(getApplicationContext(), "Place second marker to highlight search area", Toast.LENGTH_LONG).show();
                        marker = true;

                    } // end if.
                    break;

                case R.id.ascendButton:

                    // Request service to make drone ascend to 25 meters.
                    Toast.makeText(this, "Ascend Button Clicked", Toast.LENGTH_SHORT).show();
                    Xsim = currentDrone.getSimulatedX();
                    Ysim = currentDrone.getSimulatedY();
                    Zsim = 25;
                    ServerCommunication.sendDrone(Xsim, Ysim, Zsim);
                    break;

                case R.id.descendButton:

                    // Request service to make drone ascend to 15 meters.
                    Toast.makeText(this, "Descend Button Clicked", Toast.LENGTH_SHORT).show();
                    Xsim = currentDrone.getSimulatedX();
                    Ysim = currentDrone.getSimulatedY();
                    Zsim = 15;
                    ServerCommunication.sendDrone(Xsim, Ysim, Zsim);
                    break;

            } // end switch

        } // end onClick function

    } // end if

} // end MapsActivity class
