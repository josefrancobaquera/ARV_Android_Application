// Team Name: Android Optimizers
// Last Day Modified: November 26, 2019
// Project Name: Autonomous Robotic Vehicle (ARV) Application
// Author(s): Jared Peterson

package com.example.arvwearable;
import com.xbw.ros.message.*;

@MessageType(string = "std_msgs/String")
public class gScore extends Message {
    public String gscore;
}
