// generated in de.wwu.md2.framework.generator.android.lollipop.controller.Actions.generateAction()
package md2.investigation.md2.controller.action;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class Camera_Camera_getCameraValue_Action extends AbstractMd2Action {

    public Camera_Camera_getCameraValue_Action() {
		super("Camera_Camera_getCameraValue_Action"); 
	}

    @Override
    public void execute() {

        // Add empty contact
        ContentValues contentValues = new ContentValues();
        Uri rawContactUri = EinkaufszettelApp.getCurrentActivity().getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
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

        EinkaufszettelApp.getCurrentActivity().getContentResolver().bulkInsert(ContactsContract.Data.CONTENT_URI, contactDetails);

        // Capture benchmark result
        EinkaufszettelApp.getBenchmarkFragment().onBenchmarkCompleted("Contact added: " + rawContactId);
    }
}
