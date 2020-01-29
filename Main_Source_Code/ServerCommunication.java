// Team Name: Android Optimizers
// Last Day Modified: November 26, 2019
// Project Name: Autonomous Robotic Vehicle (ARV) Application
// Author(s): Jose Franco Baquera

package com.example.arvwearable;
import android.util.Log;
import com.xbw.ros.rosbridge.ROSBridgeClient;
import com.xbw.ros.Topic;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;


public class ServerCommunication {

    /* A ServerCommunication instance must have the following Topic instances.
       These instances were provided by Ahmed.
     */
    private static final ROSBridgeClient spitfire = new ROSBridgeClient("ws://spitfire.cs.nmsu.edu:9090");
    private final Topic<dPos> drone0 = new Topic<>("/uav0/ground_truth_to_tf/pose", dPos.class, spitfire);
    private final Topic<dPos> drone1 = new Topic<>("/uav1/ground_truth_to_tf/pose", dPos.class, spitfire);
    private final Topic<dPos> drone2 = new Topic<>("/uav2/ground_truth_to_tf/pose", dPos.class, spitfire);
    private final Topic<dPos> drone3 = new Topic<>("/uav3/ground_truth_to_tf/pose", dPos.class, spitfire);
    private final Topic<gScore> score = new Topic<>( "/w_player_score2", gScore.class, spitfire);
    private final Topic<hHuman> hiddenHuman = new Topic<>("/w_hccoordinates", hHuman.class, spitfire );
    private final Topic<hDrone> hiddenDrone = new Topic<>("/w_dccoordinates", hDrone.class, spitfire);
    private final Topic<hDanger> humanDanger = new Topic<>("/w_hdzcoordinates", hDanger.class, spitfire);
    private final Topic<dDanger> droneDanger = new Topic<>("/w_ddzcoordinates", dDanger.class, spitfire);

    /* Tag used for debugging. */
    private static final String TAG = MapsActivity.class.getSimpleName();

    /* The player ID will always be 46. */
    private static final int playerID = 46;

    /* Boolean variable that will keep track if there was an initial connection with Spitfire. */
    private boolean isConnected = false;

    /* Predicate types provided by Ahmed. */
    public enum PredicateType {
        TYPE_DRONE_ABOVE(0),
        TYPE_DRONE_ALIGNED(1),
        TYPE_DRONE_AT(2),
        TYPE_TOOK_COLLIDED(3),
        TYPE_DRONE_HOVERED(4),
        TYPE_DRONE_IN(5),
        TYPE_DRONE_LOW_BATTERY(6),
        TYPE_SCANNED(7);

        private int value;

        PredicateType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /* Constructor implementation. */
    public ServerCommunication ( ) {

        /* Connect to server. */
        isConnected = spitfire.connect();

    } /* end constructor */

    public boolean connectedCheck ( ){
        return isConnected;
    }

    /* Subscribes to drone 0 topic. */
    public void subscribeUAV0( ) {
        drone0.subscribe();
    }

    /* Subscribes to drone 1 topic. */
    public void subscribeUAV1( ) {
        drone1.subscribe();
    }

    /* Subscribes to drone 2 topic. */
    public void subscribeUAV2( ) {
        drone2.subscribe();
    }

    /* Subscribes to drone 3 topic. */
    public void subscribeUAV3( ) {
        drone3.subscribe();
    }

    /* Subscribes to hidden human objects topic. */
    public void subscribeHiddenHuman( ) {
        hiddenHuman.subscribe();
    }

    /* Subscribes to hidden drone objects topic. */
    public void subscribeHiddenDrone( ) {
        hiddenDrone.subscribe();
    }

    /* Subscribes to drone danger zones topic. */
    public void subscribeDangerDrone( ) {
        droneDanger.subscribe();
    }

    /* Subscribes to human danger zones topic. */
    public void subscribeDangerHuman( ) {
        humanDanger.subscribe();
    }

    /* Subscribes to game score topic. */
    public void subscribeScore( ) {
        score.subscribe();
    }

    /* Sends a drone to a particular location in the map. */
    public static void sendDrone(double x, double y, double z) {

        callLocationAddService( x, y, z, 0, 0, 0);

    }

    /* Requests a service to send a drone to a location. */
    public static void callLocationAddService(double xCoordinate, double yCoordinate, double zCoordinate, double xOrientation, double yOrientation, double zOrientation) {

        /* Create the appropriate JSON object. This was provided by Ahmed. */
        Map<String, Double> position = new HashMap<String, Double>(3);
        position.put("x", xCoordinate);
        position.put("y", yCoordinate);
        position.put("z", zCoordinate);
        Map<String, Double> orientation = new HashMap<String, Double>(3);
        orientation.put("x", xOrientation);
        orientation.put("y", yOrientation);
        orientation.put("z", zOrientation);
        Map<String, Map> poseEuler = new HashMap<String, Map>(2);
        poseEuler.put("position", position);
        poseEuler.put("orientation", orientation);
        JSONObject pose = new JSONObject();

        pose.put("pose", poseEuler);

        String argument = pose.toJSONString();

        /* Setup service request. */
        String sName = "/urs_wearable/add_location";
        String sendString = "{\"op\":\"call_service\",\"service\":\""+ sName +"\",\"args\":" + argument + "}";

        /* Send request to Spitfire. */
        spitfire.send(sendString);

    }

    /* Sets the "at" predicate. */
    public static void setGoalAt(int droneID, int locationID ) {

        JSONObject pred2 = getPredicate(PredicateType.TYPE_DRONE_AT, true);
        pred2.put("at", getPredicateDroneAt(droneID, locationID));
        JSONArray goal = new JSONArray();
        goal.add(pred2);
        callSetGoalService(goal);

    }

    /* Requests to Spitfire to set the goal service. */
    public static void callSetGoalService(JSONArray goal) {

        /* Create the appropriate JSON object. This was provided by Ahmed. */
        JSONObject requestObject = new JSONObject();
        requestObject.put("player_id", playerID);
        requestObject.put("goal", goal);
        String sName = "/urs_wearable/set_goal";

        /* Setup service request. */
        String argument = requestObject.toString();
        String sendString = "{\"op\":\"call_service\",\"service\":\""+ sName +"\",\"args\":" + argument + "}";

        /* Send request to Spitfire. */
        spitfire.send(sendString);

    }

    /* Returns a newly created predicate JSON object depending on
       which predicate type is passed as a parameter.
     */
    public static JSONObject getPredicate( PredicateType type, boolean val ){

        JSONObject predicate = new JSONObject();
        predicate.put("type", type.getValue());
        predicate.put("at", getPredicateDroneAt(0, 0));
        predicate.put("hovered", getPredicateHovered(0));
        predicate.put("scanned", getPredicateScan(0, 0));
        predicate.put("truth_value", val);
        return predicate;

    }

    /* Returns a newly created AT predicate JSON object depending on
       which drone ID is passed as a parameter.
     */
    public static JSONObject getPredicateDroneAt(int droneID, int locationID) {
        Map<String, Integer> droneIDObject = new HashMap<String, Integer>(1);
        droneIDObject.put("value", droneID);
        Map<String, Integer> locationIDObject = new HashMap<String, Integer>(1);
        locationIDObject.put("value", locationID);
        JSONObject predicateDroneAt = new JSONObject();
        predicateDroneAt.put("d", droneIDObject);
        predicateDroneAt.put("l", locationIDObject);
        return predicateDroneAt;
    }

    /* Returns a newly created HOVERED predicate JSON object depending on
       which drone ID is passed as a parameter.
     */
    public static JSONObject getPredicateHovered(int droneID) {
        Map<String, Integer> droneIDObject = new HashMap<String, Integer>(1);
        droneIDObject.put("value", droneID);
        JSONObject droneHovered = new JSONObject();
        droneHovered.put("d", droneIDObject);
        return droneHovered;
    }

    /* Returns a newly created predicate SCAN JSON object depending on
       which drone and area IDs are passed as parameters.
     */
    public static JSONObject getPredicateScan(int droneID, int areaID) {
        Map<String, Integer> droneIDObject = new HashMap<String, Integer>(1);
        droneIDObject.put("value", droneID);
        Map<String, Integer> locationIDObject = new HashMap<String, Integer>(1);
        locationIDObject.put("value", areaID);
        JSONObject predicateScan = new JSONObject();
        predicateScan.put("d", droneIDObject);
        predicateScan.put("a", locationIDObject);
        return predicateScan;
    }

    /* Lands a drone. The parameter passed will be the drone that will be chosen to
       land.
     */
    public static void droneLand(int id) {
        JSONObject pred2 = getPredicate(PredicateType.TYPE_DRONE_HOVERED, false);
        pred2.put("hovered", getPredicateHovered(id));
        JSONArray goal = new JSONArray();
        goal.add(pred2);
        callSetGoalService(goal);
    }

    /* This function is responsible for requesting the search service from Spitfire. */
    public static void search (int left, int right ) {

        JSONObject area = new JSONObject();
        area.put("loc_id_left", left);
        area.put("loc_id_right", right);

        String sName = "/urs_wearable/add_area";

        /* Setup request service. */
        String argument = area.toString();
        String sendString = "{\"op\":\"call_service\",\"service\":\""+ sName +"\",\"args\":" + argument + "}";

        /* Send request to Spitfire. */
        spitfire.send(sendString);

    }

    /* Function that will handle the area ID once Spitfire publishes the relevant search ID. */
    public static void searchReady (int drone, int areaID ) {

        JSONObject pred2 = getPredicate(PredicateType.TYPE_SCANNED, true);
        pred2.put("scanned", getPredicateScan(drone, areaID));
        JSONArray goal = new JSONArray();
        goal.add(pred2);
        callSetGoalService(goal);

    }

} /* end ServerCommunication class */
