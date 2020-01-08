package de.wwu.performancebenchmark.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite Helper class for database benchmark
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Increment when schema changes
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DatabaseBenchmark.db";

    private static final String SQL_CREATE_SCHEMA_128 =
            "CREATE TABLE " + DatabaseTable.Benchmark128.TABLE_NAME + " (" +
                    //DatabaseTable.Benchmark128._ID + " INTEGER PRIMARY KEY," +
                    DatabaseTable.Benchmark128.COLUMN_NAME_KEY + " INTEGER PRIMARY KEY," +
                    DatabaseTable.Benchmark128.COLUMN_NAME_VALUE + " BLOB)";

    private static final String SQL_CREATE_SCHEMA_2048 =
            "CREATE TABLE " + DatabaseTable.Benchmark2048.TABLE_NAME + " (" +
                    //DatabaseTable.Benchmark2048._ID + " INTEGER PRIMARY KEY," +
                    DatabaseTable.Benchmark2048.COLUMN_NAME_KEY + " INTEGER PRIMARY KEY," +
                    DatabaseTable.Benchmark2048.COLUMN_NAME_VALUE + " BLOB)";

    private static final String SQL_DELETE_ENTRIES_128 =
            "DROP TABLE IF EXISTS " + DatabaseTable.Benchmark128.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_2048 =
            "DROP TABLE IF EXISTS " + DatabaseTable.Benchmark2048.TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SCHEMA_128);
        db.execSQL(SQL_CREATE_SCHEMA_2048);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES_128);
        db.execSQL(SQL_DELETE_ENTRIES_2048);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}


