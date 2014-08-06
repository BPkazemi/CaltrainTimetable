package com.bpourkazemi.caltraintimetable.SQLite;

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

    public static final String TABLE_TRAIN_TIMES = "train_times";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TRAIN_STOP = "train_stop";
    public static final String COLUMN_NORHTBOUND_DEPARTURE = "northbound_departure";
    public static final String COLUMN_SOUTHBOUND_DEPARTURE = "southbound_departure";

    private static final String DATABASE_NAME = "train_times.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table " + TABLE_TRAIN_TIMES + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TRAIN_STOP + " text not null, "
            + COLUMN_NORHTBOUND_DEPARTURE + " text not null, "
            + COLUMN_SOUTHBOUND_DEPARTURE+ " text not null);";

    private static final String DATABASE_DESTROY =
            "drop table if exists " + TABLE_TRAIN_TIMES;


    public CaltrainSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase database) {
        Log.w(CaltrainSQLiteHelper.class.getName(), "onCreate");
        database.execSQL(DATABASE_CREATE);
    }

    @Override public void onOpen(SQLiteDatabase database) {
        Log.w(CaltrainSQLiteHelper.class.getName(), "onOpen");
        database.execSQL(DATABASE_DESTROY);
    }

    @Override public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(CaltrainSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data"
        );
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAIN_TIMES);
        onCreate(database);
    }
}
