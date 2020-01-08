// generated in de.wwu.md2.framework.generator.android.lollipop.controller.Actions.generateAction()
package md2.investigation.md2.controller.action;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import md2.investigation.EinkaufszettelApp;
import md2.investigation.R;

import md2.investigation.md2.controller.Controller;
import de.wwu.md2.android.md2library.controller.action.implementation.AbstractMd2Action;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderOperationAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderOperations;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderResetAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2DisplayMessageAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2FireEventAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2GoToViewAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2SynchronizeContentProviderDataMappingAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2SynchronizeWidgetDataMappingAction;
import de.wwu.md2.android.md2library.controller.action.interfaces.Md2Action;

import de.wwu.md2.android.md2library.model.type.implementation.Md2Boolean;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Date;
import de.wwu.md2.android.md2library.model.type.implementation.Md2DateTime;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Float;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Integer;
import de.wwu.md2.android.md2library.model.type.implementation.Md2String;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Time;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Sensor;
import de.wwu.md2.android.md2library.model.type.implementation.Md2List;
import de.wwu.md2.android.md2library.exception.Md2WidgetNotCreatedException;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2ContentProviderEventTypes;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnAttributeChangedHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnChangedHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnClickHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnLongClickHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnLeftSwipeHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnRightSwipeHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2WidgetEventType;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.interfaces.Md2CustomCodeTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2AttributeSetTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2BindTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2CallTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2MapTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2UnbindTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2UnmapTask;
import de.wwu.md2.android.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.wwu.md2.android.md2library.view.management.implementation.Md2ViewManager;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2TaskQueue;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderAddAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderRemoveActiveAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderGetActiveAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderResetLocalAction;

public class FileSystem_FileSystem_getFileSystemValue_Action extends AbstractMd2Action {

    private static final String FILENAME = "file_system_benchmark_2";

    public FileSystem_FileSystem_getFileSystemValue_Action() {
        super("FileSystem_FileSystem_getFileSystemValue_Action");
    }

    @Override
    public void execute() {
        String ret = "";
        InputStream inputStream = null;
        try {// Load file content to string
            inputStream = EinkaufszettelApp.getCurrentActivity().getApplicationContext().openFileInput(FILENAME);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                ret = stringBuilder.toString();
            }

            // Decode base64 string
            byte[] image = Base64.decode(ret, Base64.DEFAULT);

            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(image, 0, image.length, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, (int) EinkaufszettelApp.getCurrentActivity().getResources().getDimension(R.dimen.file_system_image_width), (int) EinkaufszettelApp.getCurrentActivity().getResources().getDimension(R.dimen.file_system_image_height));

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length, options);

            EinkaufszettelApp.getBenchmarkFragment().onBenchmarkCompleted("Image loaded");
        } catch (FileNotFoundException e) {
            EinkaufszettelApp.getBenchmarkFragment().onBenchmarkFailed("File not found!");
        } catch (IOException e) {
            EinkaufszettelApp.getBenchmarkFragment().onBenchmarkFailed("Can not read file: " + e.toString());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    EinkaufszettelApp.getBenchmarkFragment().onBenchmarkFailed("Failed closing file: " + e.toString());
                }
            }
        }
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
