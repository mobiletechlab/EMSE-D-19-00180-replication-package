package de.wwu.performancebenchmark;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.wwu.performancebenchmark.model.BenchmarkType;

public class ContactActivity extends AppCompatActivity implements BenchmarkFragment.OnBenchmarkInteractionListener, LocationListener {
    public static final int BENCHMARK_PERMISSION_READ_CONTACTS = 1;
    public static final int BENCHMARK_PERMISSION_WRITE_CONTACTS = 2;

    BenchmarkFragment benchmarkFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Initialize benchmark fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable(BenchmarkFragment.TYPE, BenchmarkType.CONTACT);

        benchmarkFragment = (BenchmarkFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);
        benchmarkFragment.setArguments(bundle);

        // Ask for permissions upfront
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.READ_CONTACTS ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.READ_CONTACTS  },
                    BENCHMARK_PERMISSION_READ_CONTACTS );
        }
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.WRITE_CONTACTS ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.WRITE_CONTACTS  },
                    BENCHMARK_PERMISSION_WRITE_CONTACTS );
        }
    }

    @Override
    public void onBenchmarkStart() {
        // Check permissions
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.READ_CONTACTS ) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            benchmarkFragment.onBenchmarkFailed("Permissions not given for accessing contact list!");
            return;
        }

        // Add empty contact
        ContentValues contentValues = new ContentValues();
        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
        long rawContactId = ContentUris.parseId(rawContactUri);

        ContentValues[] contactDetails = new ContentValues[2];
        contentValues = new ContentValues();
        contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        contentValues.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, "mobile" + ((int)(Math.random() * 1000)));
        contactDetails[0] = contentValues;

        ContentValues contentValuesPhone = new ContentValues();
        contentValuesPhone.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        contentValuesPhone.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        contentValuesPhone.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
        contentValuesPhone.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "123456789");
        contactDetails[1] = contentValuesPhone;

        getContentResolver().bulkInsert(ContactsContract.Data.CONTENT_URI, contactDetails);

        benchmarkFragment.onBenchmarkCompleted("Contact added: " + rawContactId);
    }

    @Override
    public void onBenchmarkReset() {
        // Nothing to do
    }

    @Override
    public void onLocationChanged(Location location) {}

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {}

    @Override
    public void onProviderDisabled(String s) {}
}
