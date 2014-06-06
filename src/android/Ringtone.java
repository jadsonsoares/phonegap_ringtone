package com.webhouding.ringtone;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;

public class ringtone extends CordovaPlugin {
	
	public static final String ACTION_OPEN_SELECT_RINGTONE = "selectRingtone";
	
	private static final int RINGTONE_PICKED = 1;
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		//loadData();
		
		try {
			
		    if (ACTION_OPEN_SELECT_RINGTONE.equals(action)) {
		    	System.err.println(">>>>>>>>>>>>>>>>>>>>SUCESS>>>>>>>>>>>>>>>>>");
		    	
		    	Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
		        // Allow user to pick 'Default'
		        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
		        // Show only ringtones
		        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE);
		        // Don't show 'Silent'
		        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
		        
		        Uri ringtoneUri;
		        ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this.cordova.getActivity().getBaseContext(), RingtoneManager.TYPE_RINGTONE);
//		        System.err.println(">>>>>>>>>>>>>>>>>>>>mCustomRingtone: " +ringtoneUri.toString());
		        
		        if (ringtoneUri.toString() == null){
		            // Otherwise pick default ringtone Uri so that something is selected.
		            ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		        }
		        
//		        System.err.println(">>>>>>>>>>>>>>>>>>>>RINGTONE: " +ringtoneUri);
		        
		        // Put checkmark next to the current ringtone for this contact
		        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, ringtoneUri);
		        
		        // Launch!
		        //this.cordova.setActivityResultCallback(null);
		        this.cordova.startActivityForResult(this, intent, RINGTONE_PICKED);
		        JSONObject rtn = new JSONObject("{'url':'" + ringtoneUri + "'}");
		        callbackContext.success(rtn);
		    	
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
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>onActivityResult>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
		if (uri != null)
		{
			try{
			   System.err.println(">>>>>>>>>>>>>>>>>>>>RINGTONE: " + uri);
	           //RingtoneManager.setActualDefaultRingtoneUri(this.cordova.getActivity().getApplication().getBaseContext(), RingtoneManager.TYPE_RINGTONE, uri);
	           Settings.System.putString(this.cordova.getActivity().getApplication().getContentResolver(), Settings.System.NOTIFICATION_SOUND, uri.toString());
			}catch (Throwable t) {
				System.err.println("ERROR>>>>>>" + t.getMessage());
           }
		}
    }
	
}
