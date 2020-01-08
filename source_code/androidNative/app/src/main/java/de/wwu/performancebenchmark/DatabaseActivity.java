package de.wwu.performancebenchmark;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import de.wwu.performancebenchmark.model.AsyncTaskResponse;
import de.wwu.performancebenchmark.model.BenchmarkType;
import de.wwu.performancebenchmark.model.DatabaseHelper;
import de.wwu.performancebenchmark.model.DatabaseTable;

public class DatabaseActivity extends AppCompatActivity implements BenchmarkFragment.OnBenchmarkInteractionListener, AsyncTaskResponse {
    protected final int KEY_MINIMUM = 10000000;

    BenchmarkFragment benchmarkFragment = null;

    protected byte[][] dataset128;
    protected byte[][] dataset2048;
    protected boolean writeDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        // Initialize benchmark fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable(BenchmarkFragment.TYPE, BenchmarkType.DATABASE);

        benchmarkFragment = (BenchmarkFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);
        benchmarkFragment.setArguments(bundle);

        // Setup data sets
        dataset128 = new byte[getResources().getInteger(R.integer.database_128_amount)][16];
        dataset2048 = new byte[getResources().getInteger(R.integer.database_2048_amount)][16];

        SecureRandom random = new SecureRandom();
        for(int i = 0; i < getResources().getInteger(R.integer.database_128_amount); i++ ){
            random.nextBytes(dataset128[i]);
        }
        for(int i = 0; i < getResources().getInteger(R.integer.database_2048_amount); i++ ){
            random.nextBytes(dataset2048[i]);
        }
    }

    @Override
    public void onBenchmarkStart() {
        // Run write test in asynchronous task and wait for result
        WriteDataTask asyncTask = new WriteDataTask();
        asyncTask.delegate = this;
        asyncTask.execute();
    }

    @Override
    public void onBenchmarkReset() {
        // Reset database
        DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(DatabaseTable.Benchmark128.TABLE_NAME, null, null);
        db.delete(DatabaseTable.Benchmark2048.TABLE_NAME, null, null);
        db.execSQL("vacuum");
        writeDone = false;
    }

    @Override
    public void processFinish(Boolean result){
        // Result of asynchronous task
        if(!writeDone && result){
            writeDone = true;
        } else if(!writeDone && !result) {
            benchmarkFragment.onBenchmarkFailed("Database write failed!");
        } if(writeDone && !result){
            benchmarkFragment.onBenchmarkFailed("Database read failed!");
        } else {
            benchmarkFragment.onBenchmarkCompleted(getResources().getInteger(R.integer.database_128_amount) + " small + " + getResources().getInteger(R.integer.database_2048_amount) + " large values written and read");
        }
    }

    class WriteDataTask extends AsyncTask<Void, Void, Boolean>{
        public AsyncTaskResponse delegate = null;

        @Override
        protected Boolean doInBackground(Void... params) {
            // Write data
            DatabaseHelper helper = new DatabaseHelper(getApplicationContext());

            SQLiteDatabase db = helper.getWritableDatabase();

            // Using transaction improves speed ca. by factor 8
            // db.beginTransaction();

            for(int i = 0; i < getResources().getInteger(R.integer.database_128_amount); i++ ){
                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(DatabaseTable.Benchmark128.COLUMN_NAME_KEY, KEY_MINIMUM + i);
                values.put(DatabaseTable.Benchmark128.COLUMN_NAME_VALUE, dataset128[i]);

                // Insert the new row, returning the primary key value of the new row
                db.insert(DatabaseTable.Benchmark128.TABLE_NAME, null, values);
            }

            for(int i = 0; i < getResources().getInteger(R.integer.database_2048_amount); i++ ){
                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(DatabaseTable.Benchmark2048.COLUMN_NAME_KEY, KEY_MINIMUM + i);
                values.put(DatabaseTable.Benchmark2048.COLUMN_NAME_VALUE, dataset2048[i]);

                // Insert the new row, returning the primary key value of the new row
                db.insert(DatabaseTable.Benchmark2048.TABLE_NAME, null, values);
            }

            //db.setTransactionSuccessful();
            //db.endTransaction();

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            delegate.processFinish(result);
        }
    }

    class ReadDataTask extends AsyncTask<Void, Void, Boolean>{
        public AsyncTaskResponse delegate = null;

        @Override
        protected Boolean doInBackground(Void... params) {
            // Write data
            DatabaseHelper helper = new DatabaseHelper(getApplicationContext());

            SQLiteDatabase db = helper.getReadableDatabase();

            String[] projection = {
                    DatabaseTable.Benchmark128.COLUMN_NAME_VALUE
            };

            // Filter results WHERE "key" = 'My Key'
            String selection = DatabaseTable.Benchmark128.COLUMN_NAME_KEY + " = ?";

            for(int i = 0; i < getResources().getInteger(R.integer.database_128_amount); i++ ){

                String[] selectionArgs = { (KEY_MINIMUM + i)+"" };

                Cursor cursor = db.query(
                    DatabaseTable.Benchmark128.TABLE_NAME,    // The table to query
                    projection,                               // The columns to return
                    selection,                                // The columns for the WHERE clause
                    selectionArgs,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                 // The sort order
                );

                if(!cursor.moveToNext()) {
                    cursor.close();
                    return false;
                }
                cursor.close();
            }

            projection = new String[]{DatabaseTable.Benchmark2048.COLUMN_NAME_VALUE};
            selection = DatabaseTable.Benchmark2048.COLUMN_NAME_KEY + " = ?";

            for(int i = 0; i < getResources().getInteger(R.integer.database_2048_amount); i++ ){

                String[] selectionArgs = { (KEY_MINIMUM + i)+"" };

                Cursor cursor = db.query(
                        DatabaseTable.Benchmark2048.TABLE_NAME,    // The table to query
                        projection,                               // The columns to return
                        selection,                                // The columns for the WHERE clause
                        selectionArgs,                            // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        null                                 // The sort order
                );

                if(!cursor.moveToNext()) {
                    cursor.close();
                    return false;
                }
                cursor.close();
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            delegate.processFinish(result);
        }
    }
}
