package com.bpourkazemi.caltraintimetableandroid.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by babakpourkazemi on 7/20/14.
 *
 * Description: To create and upgrade a SQLite database,
 *      we subclass SQLiteOpenHelper, which in turn gives
 *      us access to the database.
 */
public class CaltrainSQLiteHelper extends SQLiteOpenHelper {
    // TODO: Update columns to mimic haskell script
    // TODO: Delete the current database

    public static final String TABLE_TRAIN_TIMES = "train_times";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TRIP_ID = "trip_id";
    public static final String COLUMN_STOP_ID = "stop_id";
    public static final String COLUMN_STOP_NAME = "stop_name";
    public static final String COLUMN_STOP_SEQUENCE = "stop_sequence";
    public static final String COLUMN_BEARING = "bearing";
    public static final String COLUMN_ARRIVAL_TIME = "arrival_time";
    public static final String COLUMN_DEPARTURE_TIME = "departure_time";

    private static final String DATABASE_NAME = "train_times.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table " + TABLE_TRAIN_TIMES + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TRIP_ID + " integer not null, "
            + COLUMN_STOP_ID + " text not null, "
            + COLUMN_STOP_NAME + " text not null, "
            + COLUMN_STOP_SEQUENCE + " integer not null, "
            + COLUMN_BEARING + " text not null, "
            + COLUMN_ARRIVAL_TIME + " text not null, "
            + COLUMN_DEPARTURE_TIME + " text not null);";

    private static final String DATABASE_DESTROY =
            "drop table if exists " + TABLE_TRAIN_TIMES;

    private static final String TAG = CaltrainSQLiteHelper.class.getName();


    public CaltrainSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase database) {
        Log.w(TAG, "onCreate");
        // database.execSQL(DATABASE_CREATE);  // TODO: Delete the database, then recreate it!
    }

    @Override public void onOpen(SQLiteDatabase database) {
        Log.w(TAG, "onOpen");
        database.execSQL(DATABASE_DESTROY);
    }

    @Override public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data"
        );
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAIN_TIMES);
        onCreate(database);
    }
}
