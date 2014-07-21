package com.bpourkazemi.caltraintimetable.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bpourkazemi.caltraintimetable.SQLite.CaltrainSQLiteHelper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by babakpourkazemi on 7/20/14.
 *
 * Desc: Reads in the Caltrain GTFS file (with time information),
 *        formats it to standard time, and saves it into SQLite
 */
public class CaltrainDataHandler {
    private SQLiteDatabase database;
    private CaltrainSQLiteHelper dbHelper;
    private String[] columnTitles = {
            CaltrainSQLiteHelper.COLUMN_ID,
            CaltrainSQLiteHelper.COLUMN_NORHTBOUND_DEPARTURE,
            CaltrainSQLiteHelper.COLUMN_SOUTHBOUND_DEPARTURE
    };

    private ArrayList<TrainInformation> trainInformation;

    public CaltrainDataHandler(Context context) {
        dbHelper = new CaltrainSQLiteHelper(context);
    }

    public void formatAndSaveData() {
        try {
            formatData();
            save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void formatData() {
        trainInformation = new ArrayList<TrainInformation>();
    }

    // Persist changes to SQLite
    public void save() throws SQLException {
        database = dbHelper.getWritableDatabase();
        // Insert each row of data into the db
        for (TrainInformation info : trainInformation) {
            ContentValues times = new ContentValues();

            times.put(CaltrainSQLiteHelper.COLUMN_TRAIN_STATION, info.stationName);
            times.put(CaltrainSQLiteHelper.COLUMN_NORHTBOUND_DEPARTURE, info.northboundDeparture);
            times.put(CaltrainSQLiteHelper.COLUMN_SOUTHBOUND_DEPARTURE, info.southboundDeparture);

            database.insert(CaltrainSQLiteHelper.TABLE_TRAIN_TIMES, null, times);
        }
        database.close();
    }

    private class TrainInformation {
        public String stationName,
                    northboundDeparture,
                    southboundDeparture;

        public TrainInformation(String stationName, String northboundDeparture, String southboundDeparture) {
            this.stationName = stationName;
            this.northboundDeparture = northboundDeparture;
            this.southboundDeparture = southboundDeparture;
        }
    }
}
