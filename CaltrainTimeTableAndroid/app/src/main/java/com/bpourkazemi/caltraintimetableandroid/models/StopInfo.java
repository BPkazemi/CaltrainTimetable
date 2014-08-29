package com.bpourkazemi.caltraintimetableandroid.models;

import java.util.ArrayList;

/**
 * Created by babakpourkazemi on 7/20/14.
 */
public class StopInfo {
    // TODO: Update to mimic Haskell script
    public String stopName;

    public ArrayList<String> northboundTimes,
            southboundTimes;

    public StopInfo() { }

    public StopInfo(String stopName, ArrayList<String> southboundTimes,
                    ArrayList<String> northboundTimes) {
        this.stopName = stopName;
        this.northboundTimes = northboundTimes;
        this.southboundTimes = southboundTimes;
    }

    public void addSouthboundTime(String time) {
        southboundTimes.add(time);
    }

    public void addNorthboundTime(String time) {
        northboundTimes.add(time);
    }

    public String toString() {
        return "["
                + "\tStop: " + stopName
                + "\tNorthbound Times: " + northboundTimes.toString()
                + "\tSouthbound Times: " + southboundTimes.toString()
                + "]";
    }
}
