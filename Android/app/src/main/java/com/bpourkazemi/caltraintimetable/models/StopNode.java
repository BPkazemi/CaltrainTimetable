package com.bpourkazemi.caltraintimetable.models;

import java.util.ArrayList;

/**
 * Created by babakpourkazemi on 7/20/14.
 */
public class StopNode {
    // TODO: Update to mimic Haskell script

    public String stopId,
            stopName,
            bearing;

    public int tripId, stopSequence;

    public ArrayList<String> northboundTimes,
            southboundTimes;

    public StopNode() { }

    public StopNode(int tripId, String stopId, int stopSequence, String stopName, String bearing,
                    ArrayList<String> northboundTimes, ArrayList<String> southboundTimes) {
        this.tripId = tripId;
        this.stopId = stopId;
        this.stopSequence = stopSequence;
        this.stopName = stopName;
        this.bearing = bearing;
        this.northboundTimes = northboundTimes;
        this.southboundTimes = southboundTimes;
    }

    public void addNorthbound(String time) {
        northboundTimes.add(time);
    }

    public void addSouthbound(String time) {
        southboundTimes.add(time);
    }

    public String toString() {
        return "["
                + "\tStop: " + stopName
                + "\tBearing: " + bearing
                + "]";
    }
}
