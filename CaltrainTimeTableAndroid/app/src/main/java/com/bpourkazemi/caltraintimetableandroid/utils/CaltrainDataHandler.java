package com.bpourkazemi.caltraintimetableandroid.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by babakpourkazemi on 7/20/14.
 *
 * Desc: Reads in the Caltrain GTFS file (with time information),
 *        formats it to standard time, and saves it into SQLite
 */
public class CaltrainDataHandler {
    // TODO: Try to limit the usage of arraylists

    public CaltrainDataHandler() {
    }

//    private Context context;
//
//    private ArrayList<StopInfo> allStops;
//    private HashMap<String, String> stopIdsMap;
//
//    // Database variables
//    private SQLiteDatabase database;
//    private CaltrainSQLiteHelper dbHelper;
//    private String[] columnTitles = {
//            CaltrainSQLiteHelper.COLUMN_ID,
//            CaltrainSQLiteHelper.COLUMN_TRIP_ID,
//            CaltrainSQLiteHelper.COLUMN_STOP_ID,
//            CaltrainSQLiteHelper.COLUMN_STOP_NAME,
//            CaltrainSQLiteHelper.COLUMN_STOP_SEQUENCE,
//            CaltrainSQLiteHelper.COLUMN_BEARING,
//            CaltrainSQLiteHelper.COLUMN_ARRIVAL_TIME,
//            CaltrainSQLiteHelper.COLUMN_DEPARTURE_TIME,
//    };
//
//    private String[] stopInfoRows;
//    private ArrayList<StopInfo> allStopInfo;
//
//    private static final String TAG = CaltrainDataHandler.class.getName();
//
//
//    public CaltrainDataHandler() {
//        Log.d("CaltrainDataHandler.java", "No-arg constructor");
//    }
//
//    public CaltrainDataHandler(Context context) {
//        Log.d("CaltrainDataHandler.java", "One-arg constructor");
//        this.context = context;
//        dbHelper = new CaltrainSQLiteHelper(context);
//    }
//
//    public void init() {
//        readFile();
//
//        try {
//            saveToSql();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        createStopObjects();
//    }
//
//    public void readFile() {
//        stopInfoRows =
//                FileUtils.readEntireFile( context, "stop_info.csv", true );
//
//        Log.d(TAG, stopInfoRows.toString());
//    }
//
//    // Persist changes to SQLite
//    public void saveToSql() throws SQLException {
//        String stopId, stopName, bearing, arrivalTime, departureTime;
//        int tripId, stopSequence;
//
//        database = dbHelper.getWritableDatabase();
//
//        // Insert each row of data into the db
//        String[] tokens = null;
//        for (String row : stopInfoRows) {
//            tokens = row.split(",");
//            tripId = Integer.parseInt(tokens[0]);
//            stopId = tokens[1];
//            stopName = tokens[2];
//            stopSequence = Integer.parseInt(tokens[3]);
//            bearing = tokens[3];
//            arrivalTime = tokens[4];
//            departureTime = tokens[5];
//
//            ContentValues times = new ContentValues();
//
//            // TODO: Log the values before actually writing them :P
//            times.put(CaltrainSQLiteHelper.COLUMN_TRIP_ID, tripId);
//            times.put(CaltrainSQLiteHelper.COLUMN_STOP_ID, stopId);
//            times.put(CaltrainSQLiteHelper.COLUMN_STOP_NAME, stopName);
//            times.put(CaltrainSQLiteHelper.COLUMN_STOP_SEQUENCE, stopSequence);
//            times.put(CaltrainSQLiteHelper.COLUMN_BEARING, bearing);
//            times.put(CaltrainSQLiteHelper.COLUMN_ARRIVAL_TIME, arrivalTime);
//            times.put(CaltrainSQLiteHelper.COLUMN_DEPARTURE_TIME, departureTime);
//
//            Log.d(TAG, times.toString());
//
////            database.insert(CaltrainSQLiteHelper.TABLE_TRAIN_TIMES, null, times);
//        }
//    }
//
//    public void createStopObjects() {
////        String[] stopNames = queryForStopNames();
//        String[] stopNames = {"Dummy", "Data"};
//
//        // SQL queries = yummy data
//        ArrayList<String> northboundTimes = new ArrayList<String>();
//        ArrayList<String> southboundTimes = new ArrayList<String>();
//        for( String stopName : stopNames ) {
//            // select columnTitles from CaltrainSQLiteHelper.TABLE_TRAIN_TIMES
//            // where COLUMN_STOP_NAME = stopName AND COLUMN_BEARING = southbound order by COLUMN_ARRIVAL_TIME
//
//            // select columnTitles from CaltrainSQLiteHelper.TABLE_TRAIN_TIMES
//            // where COLUMN_STOP_NAME = stopName AND COLUMN_BEARING = northbound order by COLUMN_DEPARTURE_TIME asc <-- play around with the ordering clause. Doubt will work as-is.
//
//            // southboundTimes = toList(southboundCursor)
//            // northboundTimes = toList(northboundCursor)
//
//            // convertToStandardTime(southboundTimes)
//            // convertToStandardTime(northboundTimes)
//
//            // sort(southboundTimes)
//            // sort(northboundTimes)
//            StopInfo newStop = new StopInfo(stopName, southboundTimes, northboundTimes);
//            allStopInfo.add(newStop);
//        }
//        database.close();
//    }
//
//
//    public ArrayList<StopInfo> getStopInformation() {
//        return allStopInfo;
//    }
}
