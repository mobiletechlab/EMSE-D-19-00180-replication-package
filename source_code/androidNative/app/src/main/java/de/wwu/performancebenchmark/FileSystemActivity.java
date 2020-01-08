package de.wwu.performancebenchmark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.wwu.performancebenchmark.model.BenchmarkType;

public class FileSystemActivity extends AppCompatActivity implements BenchmarkFragment.OnBenchmarkInteractionListener {
    private static final String FILENAME = "file_system_benchmark_2";

    BenchmarkFragment benchmarkFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_system);

        // Initialize benchmark fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable(BenchmarkFragment.TYPE, BenchmarkType.FILE_SYSTEM);

        benchmarkFragment = (BenchmarkFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);
        benchmarkFragment.setArguments(bundle);

        // Setup: Copy file to file system
        FileOutputStream outputStream;

        try {
            InputStream res = this.getResources().openRawResource(R.raw.file_system_benchmark_2);
            Bitmap bm = BitmapFactory.decodeStream(res);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100 , baos);
            byte[] b = baos.toByteArray();
            String encoded = Base64.encodeToString(b, Base64.DEFAULT);

            outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(encoded.getBytes());
            outputStream.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBenchmarkStart() {
        String ret = "";
        InputStream inputStream = null;
        try {
            // Load file content to string
            inputStream = getApplicationContext().openFileInput(FILENAME);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                ret = stringBuilder.toString();
            }

            // Decode base64 string
            byte[] image = Base64.decode(ret, Base64.DEFAULT);

			benchmarkFragment.onBenchmarkCompleted("Image loaded");
			// Displaying is not part of the benchmark anymore
			
            // Naive implementation (not memory-efficient)
            // Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
            // ((ImageView) findViewById(R.id.imageOutput)).setImageBitmap(bm);

            // Better implementation using scaling according to https://developer.android.com/topic/performance/graphics/load-bitmap.html
            // First decode with inJustDecodeBounds=true to check dimensions
            /*final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(image, 0, image.length, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, (int) getResources().getDimension(R.dimen.file_system_image_width), (int) getResources().getDimension(R.dimen.file_system_image_height));

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length, options);

            // Update ImageView
            ((ImageView) findViewById(R.id.imageOutput)).setImageBitmap(bm);
            */
        } catch (FileNotFoundException e) {
            benchmarkFragment.onBenchmarkFailed("File not found!");
        } catch (IOException e) {
            benchmarkFragment.onBenchmarkFailed("Can not read file: " + e.toString());
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    benchmarkFragment.onBenchmarkFailed("Failed closing file: " + e.toString());
                }
            }
        }
    }

    @Override
    public void onBenchmarkReset() {
        // Reset image to dummy image
        /*int id = getResources().getIdentifier("@android:drawable/btn_star_big_on", null, null);
        ((ImageView) findViewById(R.id.imageOutput)).setImageDrawable(getDrawable(id));
        */
    }

    // Calculate scaling parameter to match image dimensions with target element
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
