package ir.tapsell.sdk;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.util.Log;
import android.content.pm.PackageManager;
import android.os.Build;
import android.content.Intent;
import android.net.Uri;

import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

import ir.tapsell.sdk.TapsellCordovaListener;
import ir.tapsell.sdk.TapsellCordova;
import ir.tapsell.sdk.TapsellExtraPlatforms;
import ir.tapsell.sdk.TapsellAdActivity;


public class TapsellCordovaInterface extends CordovaPlugin implements TapsellCordovaListener {
	
	private static final String LOG_TAG = "TapsellCordova";
	public CordovaInterface cordova = null;
	
	
	private final Map<String,CallbackContext> zoneCallbacks = Collections.synchronizedMap(new WeakHashMap<String, CallbackContext>());

	private CallbackContext defaultZoneCallback=null;
	
	private CallbackContext adRewardCallback=null;
	
	@Override
	public void initialize (CordovaInterface initCordova, CordovaWebView webView) {
		 Log.e (LOG_TAG, "initialize");
		  cordova = initCordova;
		  super.initialize (cordova, webView);
		  TapsellCordova.setCordovaListener(this);
	}
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("initialize")) {
			initialize(args, callbackContext);
			return true;
		}
		else if (action.equals("requestAd")) {
	    	requestAd(args, callbackContext);
		    return true;
		}
		else if (action.equals("isAdReadyToShow")) {
			isAdReadyToShow(args, callbackContext);
		    return true;
		}
		else if (action.equals("getVersion")) {
			getVersion(args, callbackContext);
		    return true;
		}
		else if (action.equals("showAd")) {
			showAd(args, callbackContext);
		    return true;
		}
		else if  (action.equals("setRewardCallback")) {
			setRewardCallback(args,callbackContext);
			return true;
		}
		else if (action.equals("getAndroidVersion")) {
			getAndroidVersion(args, callbackContext);
			return true;
		}
		else if (action.equals("setDebugMode")) {
			setDebugMode(args, callbackContext);
			return true;
		}
		else if (action.equals("setMaxAllowedBandwidthUsage")) {
			setMaxAllowedBandwidthUsage(args, callbackContext);
			return true;
		}
		else if (action.equals("setMaxAllowedBandwidthUsagePercentage")) {
			setMaxAllowedBandwidthUsagePercentage(args, callbackContext);
			return true;
		}
		else if (action.equals("clearBandwidthUsageConstrains")) {
			clearBandwidthUsageConstrains(args, callbackContext);
			return true;
		}
		else if (action.equals("setAutoHandlePermissions")) {
			setAutoHandlePermissions(args, callbackContext);
			return true;
		}
		else if (action.equals("setAppUserId")) {
			setAppUserId(args, callbackContext);
			return true;
		}
		else if (action.equals("startIntent")) {
			startIntent(args, callbackContext);
			return true;
		}
		else if (action.equals("close")) {
			close(args, callbackContext);
			return true;
		}
		else if (action.equals("replay")) {
			replay(args, callbackContext);
			return true;
		}
	    return false;
	}
	
	private void getAndroidVersion(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		JSONObject result = new JSONObject();
		try{
			result.put("action","getAndroidVersion");
			result.put("androidVersion",Build.VERSION.SDK_INT);
			PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
			resultado.setKeepCallback(true);
			callbackContext.sendPluginResult(resultado);
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void setDebugMode(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		final boolean debug = args.getBoolean(0);
		TapsellExtraPlatforms.setDebugMode(cordova.getActivity(), debug);
		callbackContext.success();
	}
	
	private void setAppUserId(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		final String appUserId = args.getString(0);
		TapsellExtraPlatforms.setAppUserId(cordova.getActivity(), appUserId);
		callbackContext.success();
	}
	
	private void setAutoHandlePermissions(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		final boolean handle = args.getBoolean(0);
		TapsellExtraPlatforms.setAutoHandlePermissions(cordova.getActivity(), handle);
		callbackContext.success();
	}
	
	private void setMaxAllowedBandwidthUsage(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		final long maxBpsSpeed = args.getLong(0);
		TapsellExtraPlatforms.setMaxAllowedBandwidthUsage(cordova.getActivity(), maxBpsSpeed);
		callbackContext.success();
	}
	
	private void setMaxAllowedBandwidthUsagePercentage(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		final int maxPercentage = args.getInt(0);
		TapsellExtraPlatforms.setMaxAllowedBandwidthUsagePercentage(cordova.getActivity(), maxPercentage);
		callbackContext.success();
	}
	
	private void clearBandwidthUsageConstrains(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		TapsellExtraPlatforms.clearBandwidthUsageConstrains(cordova.getActivity());
		callbackContext.success();
	}
	
	private void isDebugMode(JSONArray args, CallbackContext callbackContext)
	{
		JSONObject result = new JSONObject();
		try{
			result.put("action","isDebugMode");
			result.put("debug",TapsellExtraPlatforms.isDebugMode(cordova.getActivity()));
			PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
			resultado.setKeepCallback(true);
			callbackContext.sendPluginResult(resultado);
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void initialize(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		final String appKey = args.getString(0);
		TapsellExtraPlatforms.initialize(cordova.getActivity(),appKey);
		callbackContext.success();
	}
	
	private void requestAd(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		String zoneId = args.getString(0);
		if(zoneId!=null && (zoneId.equalsIgnoreCase("null") || zoneId.equalsIgnoreCase("")))
		{
			zoneId = null;
		}
		final boolean isCached = args.getBoolean(1);
		if(zoneId!=null)
		{
			zoneCallbacks.put(zoneId,callbackContext);
		}
		else
		{
			defaultZoneCallback = callbackContext;
		}
		TapsellExtraPlatforms.requestAd(cordova.getActivity(),zoneId,isCached);
	}
	
	private void isAdReadyToShow(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		final String zoneId = args.getString(0);
		boolean isAdReady = TapsellExtraPlatforms.isAdReadyToShow(cordova.getActivity(),zoneId);
		JSONObject result = new JSONObject();
		try{
			result.put("isAdReady",isAdReady);
			PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
			resultado.setKeepCallback(true);
			callbackContext.sendPluginResult(resultado);
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void getVersion(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		String version = TapsellExtraPlatforms.getVersion();
		JSONObject result = new JSONObject();
		try{
			result.put("action","getVersion");
			result.put("version",version);
			PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
			resultado.setKeepCallback(true);
			callbackContext.sendPluginResult(resultado);
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void showAd(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		final String adId = args.getString(0);
		final boolean back_disabled = args.getBoolean(1);
		final boolean immersive_mode = args.getBoolean(2);
		final int rotation_mode = args.getInt(3);
		final boolean show_dialog = args.getBoolean(4);
		TapsellExtraPlatforms.showAd(cordova.getActivity(),adId,back_disabled,immersive_mode,rotation_mode, show_dialog);
		//adRewardCallback = callbackContext;
	}
	
	private void setRewardCallback(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		adRewardCallback = callbackContext;
	}
	
	private void startIntent(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		int REQUEST_CODE_TO_BE_IGNORED = -103;
		int MIN_PACKAGE_LENGTH = -1;
		try {
			String actionType = args.getString(0);
			String uri = args.getString(1);
			String isService = args.getString(2);
			String requestCode = args.getString(3);
			String packageName = args.getString(4);
            Intent intent = new Intent(actionType, Uri.parse(uri));
            if (packageName != null
                    && packageName.length() >= MIN_PACKAGE_LENGTH) {
                intent.setPackage(packageName);
            }
            if (Boolean.parseBoolean(isService)) {
                cordova.getActivity().startService(intent);
            } else {
                if (requestCode.equals(REQUEST_CODE_TO_BE_IGNORED)) {
                    cordova.getActivity().startActivity(intent);
                } else {
                    cordova.getActivity().startActivityForResult(intent, Integer.parseInt(requestCode));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void close(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		if(cordova.getActivity()!=null && cordova.getActivity() instanceof TapsellAdActivity)
		{
			((TapsellAdActivity)(cordova.getActivity())).onBackPressed();
		}
	}
	
	private void replay(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		if(cordova.getActivity()!=null && cordova.getActivity() instanceof TapsellAdActivity)
		{
			((TapsellAdActivity)(cordova.getActivity())).replayVideo();
		}
	}
	
	@Override
    public void onAdShowFinished(String zoneId, String adId, boolean completed, boolean rewarded) {
        JSONObject result = new JSONObject();
		Log.e("tapsell","onAdShowFinished called, zoneId = "+zoneId);
		try{
			result.put("action","onAdShowFinished");
			result.put("zoneId",zoneId);
			result.put("adId",adId);
			result.put("completed",completed);
			result.put("rewarded",rewarded);
			PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
			resultado.setKeepCallback(true);
			CallbackContext callback=adRewardCallback;
			if(callback!=null)
			{
				callback.sendPluginResult(resultado);
			}
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
		
    }

    @Override
    public void onAdAvailable(String zoneId, String adId) {
		JSONObject result = new JSONObject();
		try{
			result.put("action","onAdAvailable");
			result.put("zoneId",zoneId);
			result.put("adId",adId);
			PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
			resultado.setKeepCallback(true);
			CallbackContext callback=null;
			if(zoneId==null)
			{
				callback = defaultZoneCallback;
			}
			else
			{
				callback = zoneCallbacks.get(zoneId);
			}
			if(callback!=null)
			{
				callback.sendPluginResult(resultado);
			}
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
    }

    @Override
    public void onError(String zoneId, String error) {
		JSONObject result = new JSONObject();
		try{
			result.put("action","onError");
			result.put("zoneId",zoneId);
			result.put("error",error);
			PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
			resultado.setKeepCallback(true);
			CallbackContext callback=null;
			if(zoneId==null)
			{
				callback = defaultZoneCallback;
			}
			else
			{
				callback = zoneCallbacks.get(zoneId);
			}
			if(callback!=null)
			{
				callback.sendPluginResult(resultado);
			}

		}catch(JSONException e)
		{
			e.printStackTrace();
		}
    }

    @Override
    public void onNoAdAvailable(String zoneId) {
		JSONObject result = new JSONObject();
		try{
			result.put("action","onNoAdAvailable");
			result.put("zoneId",zoneId);
			PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
			resultado.setKeepCallback(true);
			CallbackContext callback=null;
			if(zoneId==null)
			{
				callback = defaultZoneCallback;
			}
			else
			{
				callback = zoneCallbacks.get(zoneId);
			}
			if(callback!=null)
			{
				callback.sendPluginResult(resultado);
			}

		}catch(JSONException e)
		{
			e.printStackTrace();
		}
    }

    @Override
    public void onNoNetwork(String zoneId) {
		JSONObject result = new JSONObject();
		try{
			result.put("action","onNoNetwork");
			result.put("zoneId",zoneId);
			PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
			resultado.setKeepCallback(true);
			CallbackContext callback=null;
			if(zoneId==null)
			{
				callback = defaultZoneCallback;
			}
			else
			{
				callback = zoneCallbacks.get(zoneId);
			}
			if(callback!=null)
			{
				callback.sendPluginResult(resultado);
			}
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
    }

    @Override
    public void onExpiring(String zoneId, String adId) {
		JSONObject result = new JSONObject();
		try{
			result.put("action","onExpiring");
			result.put("zoneId",zoneId);
			result.put("adId",adId);
			PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
			resultado.setKeepCallback(true);
			CallbackContext callback=null;
			if(zoneId==null)
			{
				callback = defaultZoneCallback;
			}
			else
			{
				callback = zoneCallbacks.get(zoneId);
			}
			if(callback!=null)
			{
				callback.sendPluginResult(resultado);
			}
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
    }
	

}