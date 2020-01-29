// Team Name: Android Optimizers
// Last Day Modified: November 26, 2019
// Project Name: Autonomous Robotic Vehicle (ARV) Application
// Author(s): Jose Franco Baquera

package com.example.arvwearable;
import com.xbw.ros.message.*;

@MessageType(string = "geometry_msgs/PoseStamped")
public class dPos extends Message {
    public String dpos;
} // end class
