// Team Name: Android Optimizers
// Last Day Modified: November 26, 2019
// Project Name: Autonomous Robotic Vehicle (ARV) Application
// Author(s): Jose Franco Baquera

package com.example.arvwearable;
import com.xbw.ros.PublishEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import de.greenrobot.event.EventBus;

public class Drone {

    /* Each Drone instance will have a drone id. This id can be
     * be between 0 to 3. */
    private int id;

    /* y coordinate represents latitude, x represents longitude, and
     * z represents the altitude of drone. */
    private double x;
    private double y;
    private double z;

    /* x and y simulated coordinates published by Spitfire. */
    private double simulatedX;
    private double simulatedY;

    /* Use for debugging purposes. */
    static private final String TAG = Drone.class.getSimpleName();

    /* Variables that will allow us to convert Spitfire's simulated coordinates. */
    private final double originlongitude = -106.75239801428688;
    private final double originlatitude = 32.2810102009863;

    /* Drone constructor implementation. */
    public Drone( int droneID ) {

        /* Register the class to the EventBus. */
        EventBus.getDefault().register(this);

        /* Update drone ID and assign coordinate locations default values. */
        id = droneID;
        x = 0;
        y = 0;
        z = 0;

    } /* end constructor */

    /* Function that will parse all incoming messages from Spitfire. */
    public void onEvent( PublishEvent newEvent ) {

        /* Only handle the message if it pertains to the drone ID mapped
         * to the drone ID of the Drone instance. */
        if (newEvent.name.equals("/uav"+id+"/ground_truth_to_tf/pose") ) {

            try {

                JSONObject obj1 = (JSONObject) new JSONParser().parse(newEvent.msg);
                JSONObject obj2 = (JSONObject) obj1.get("pose");
                JSONObject obj3 = (JSONObject) obj2.get("position");

                /* Extract the simulated x and y coordinates. */

                simulatedX = (double) obj3.get("x");
                simulatedY = (double) obj3.get("y");

                double coef1 = simulatedY * 0.0000089;
                double coef2 = simulatedX * 0.0000089;

                /* Convert simulated coordinates to longitude and latitude. */
                y = originlatitude + coef1;
                x = originlongitude + coef2 / Math.cos(originlatitude * 0.018);
                z = (Double) obj3.get("z");

            } /* end try */

            catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } /* end catch */

        } /* end if */

    } /* end onEvent function */

    public double getX( ) {
        return x;
    }

    public double getY( ) {
        return y;
    }

    public double getZ( ) {
        return z;
    }

    public double getSimulatedX( ) {
        return simulatedX;
    }

    public double getSimulatedY( ) {
        return simulatedY;
    }

} /* end Drone class */
