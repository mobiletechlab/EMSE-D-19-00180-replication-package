package Investigation.backend;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * This class allows to define the current model version as well as all model versions that can be handled by this
 * backend module.
 */
public class Config {
	
	/**
	 * Define a string that represents the current version of the backend model.
	 */
	public final static String MODEL_VERSION = "1.0";
	
	/**
	 * Define a list of all model versions that are supported by this backend implementation
	 * to allow backward compatibility.
	 */
	public final static List<String> SUPPORTED_MODEL_VERSIONS = Lists.newArrayList("1.0");
	
	public final static HashMap<String, String[]> APP_WORKFLOWELEMENT_RELATIONSHIP = setAppWorkflowElementRelationship();

	public final static HashMap<String, HashMap<String, String>> WORKFLOWELEMENT_EVENT_SUCCESSION = setAppWorkflowElementSuccession();
	
	public final static File UPLOAD_FILE_STORAGE_PATH = null;
	

	public static final String UPLOAD_FILE_PREFIX = "upload-";
	
	/**
 * provides a hashmap for filtering workflowelements by apps
 * setAppWorkflowElementRelationship : (App) --> (Wfe)*
 * @return
 */
	private static HashMap<String,String[]> setAppWorkflowElementRelationship(){
		
		HashMap<String, String[]> map = new HashMap<String, String[]>();
		map.put("EinkaufszettelApp", new String[]{"Accelerometer","Camera","Database","FileSystem","Geolocation"});
		return map;
	}

	
	/**
	 * Given an event-throwing app and a thrown event, this map knows what workflow element has to follow
	 * setAppWorkflowElementSuccession : (Wfe x Event) --> Wfe
	 * @return
	 */
	private static HashMap<String, HashMap<String, String>> setAppWorkflowElementSuccession() {
		HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> innerMap;
		
		// Coming from Accelerometer
		innerMap = new HashMap<String, String>();
		innerMap.put("AccelerometerDone", "_terminate");
		map.put("Accelerometer", innerMap);
		
		// Coming from Camera
		innerMap = new HashMap<String, String>();
		innerMap.put("CameraDone", "_terminate");
		map.put("Camera", innerMap);
		
		// Coming from Database
		innerMap = new HashMap<String, String>();
		innerMap.put("DatabaseDone", "_terminate");
		map.put("Database", innerMap);
		
		// Coming from FileSystem
		innerMap = new HashMap<String, String>();
		innerMap.put("FileSystemDone", "_terminate");
		map.put("FileSystem", innerMap);
		
		// Coming from Geolocation
		innerMap = new HashMap<String, String>();
		innerMap.put("GeolocationDone", "_terminate");
		map.put("Geolocation", innerMap);
		
		
		
		// Coming from invokables
		innerMap = new HashMap<String, String>();
		HashMap<String, String> map__invokedByWS = new HashMap<String, String>();
		map.put("__invokedByWS", map__invokedByWS);
		
		return map;
	}
}
