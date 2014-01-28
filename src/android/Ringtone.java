package com.webhouding.ringtone;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;

public class ringtone extends CordovaPlugin {
	
	public static final String ACTION_OPEN_SELECT_RINGTONE = "selectRingtone";
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		try {
		    if (ACTION_OPEN_SELECT_RINGTONE.equals(action)) {
		    	
		    	callbackContext.error(">>>>>>>>>>>>>>>SUCCESS!!>>>>>>>>>>>>>>>>>>>>");
		       return true;
		    }
		    callbackContext.error("Invalid action");
		    return false;
		} catch(Exception e) {
		    System.err.println("Exception: " + e.getMessage());
		    callbackContext.error(e.getMessage());
		    return false;
		} 
	}
}
