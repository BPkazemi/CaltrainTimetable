package com.bpourkazemi.caltraintimetable.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bpourkazemi.caltraintimetable.SQLite.CaltrainSQLiteHelper;
import com.bpourkazemi.caltraintimetable.models.StopNode;

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

    private Context context;

//    private ArrayList<TrainInformation> trainInformation;
    private ArrayList<StopNode> allStops;
    private HashMap<String, String> stopIdsMap;

    // Database variables
    private SQLiteDatabase database;
    private CaltrainSQLiteHelper dbHelper;
    private String[] columnTitles = {
            CaltrainSQLiteHelper.COLUMN_ID,
            CaltrainSQLiteHelper.COLUMN_NORHTBOUND_DEPARTURE,
            CaltrainSQLiteHelper.COLUMN_SOUTHBOUND_DEPARTURE
    };

    private static final String TAG = CaltrainDataHandler.class.getName();

    public CaltrainDataHandler(Context context) {
        this.context = context;
        dbHelper = new CaltrainSQLiteHelper(context);
    }

    public void setupData() {
        readData();

        try {
            save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        String[] stopTimeRows =
                FileUtils.readEntireFile( context, "stop_info.csv", true );

        Log.d(TAG, stopTimeRows.toString());
    }

    // Persist changes to SQLite
    public void save() throws SQLException {
//        database = dbHelper.getWritableDatabase();
//        // Insert each row of data into the db
//        for (TrainInformation info : trainInformation) {
//            ContentValues times = new ContentValues();
//
//            times.put(CaltrainSQLiteHelper.COLUMN_TRAIN_STOP, info.stopName);
//            times.put(CaltrainSQLiteHelper.COLUMN_NORHTBOUND_DEPARTURE, info.northboundDeparture);
//            times.put(CaltrainSQLiteHelper.COLUMN_SOUTHBOUND_DEPARTURE, info.southboundDeparture);
//
//            database.insert(CaltrainSQLiteHelper.TABLE_TRAIN_TIMES, null, times);
//        }
//        database.close();
    }

    // TODO: Update to mimic Haskell script
    private class TrainInformation {
        public String stopName,
                    northboundDeparture,
                    southboundDeparture;

        public TrainInformation(String stopName, String northboundDeparture, String southboundDeparture) {
            this.stopName = stopName;
            this.northboundDeparture = northboundDeparture;
            this.southboundDeparture = southboundDeparture;
        }
    }
}
