package org.apache.cordova.videoplayer;



import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;


import android.content.Context;
import android.content.Intent;



public class VideoPlayerPlugin extends CordovaPlugin {
	
	 @Override
	    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
	        if (action.equals("play")) {
	        	final String name = args.getString(0);
	        	cordova.getActivity().runOnUiThread(new Runnable() {
	                @Override
	                public void run() {
	                    Context context = cordova.getActivity().getApplicationContext();
	                    Intent videoPlaybackActivity = new Intent(context, org.apache.cordova.videoplayer.VideoPlayer.class);
	                    int res=cordova.getActivity().getResources().getIdentifier(name, "raw", cordova.getActivity().getPackageName());
	        	        
	        	        videoPlaybackActivity.putExtra("fileRes", res );
	                    cordova.getActivity().startActivity(videoPlaybackActivity);
	                }
	            });
	            
	            return true;
	        }
	        return false;
	    }
	 


}
