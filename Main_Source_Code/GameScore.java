// Team Name: Android Optimizers
// Last Day Modified: November 26, 2019
// Project Name: Autonomous Robotic Vehicle (ARV) Application
// Author(s): Jose Franco Baquera

package com.example.arvwearable;
import android.util.Log;

import com.xbw.ros.PublishEvent;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import de.greenrobot.event.EventBus;

public class GameScore {

    /* Tag used for debugging purposes. */
    static final String TAG = GameScore.class.getSimpleName();

    /* Each GameScore instance will have a game score integer. */
    private int score;

    /* Constructor */
    public GameScore ( ) {

        /* Register the class to the EventBus. */
        EventBus.getDefault().register(this);

        /* Initialize score to 0. */
        score = 0;

    } // end constructor

    public void onEvent(PublishEvent newEvent) {

        /* Check that the published message pertains to the game score of the game. */
        if (newEvent.name.equals("/w_player_score2")) {

            try {

                /* Extract the game score from the published message. */
                Object obj = new JSONParser().parse(newEvent.msg);
                org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;
                String test100 = (String) ((org.json.simple.JSONObject) obj).get("data");
                String doubleQoute = test100.replace('\'', '\"');
                Object obj2 = new JSONParser().parse(doubleQoute);
                org.json.simple.JSONObject jo2 = (org.json.simple.JSONObject) obj2;
                score =  (int) jo2.get("point");

            } catch (Exception e) { Log.e(TAG, "exception "); }

        } // end if.

    } // end onEvent

    /* Getter. */
    public int getScore( ) {
        return score;
    }

} // end class
