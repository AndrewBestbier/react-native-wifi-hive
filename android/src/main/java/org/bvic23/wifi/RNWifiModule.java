package org.bvic23.wifi;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RNWifiModule extends ReactContextBaseJavaModule {

	WifiManager wifi;
	ReactApplicationContext context;

	//Constructor
	public RNWifiModule(ReactApplicationContext reactContext) {
		super(reactContext);
		wifi = (WifiManager)reactContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		context = getReactApplicationContext();
	}

	@Override
	public String getName() {
		return "RNWifiModule";
	}

	@ReactMethod
	public void getWifiList(final Callback callback) {
		try {
			List < ScanResult > results = wifi.getScanResults();
			JSONArray wifiArray = new JSONArray();

			for (ScanResult result: results) {
				JSONObject wifiObject = new JSONObject();
				if(!result.SSID.equals("")){
					try {
            			wifiObject.put("SSID", result.SSID);
			            wifiObject.put("BSSID", result.BSSID);
			            wifiObject.put("isSecure", !result.capabilities.equals("[ESS]"));
			            wifiObject.put("frequency", result.frequency);
            			wifiObject.put("level", result.level);
					} catch (JSONException e) {
			          	callback.invoke(e.getMessage());
					}
					wifiArray.put(wifiObject);
				}
			}

			callback.invoke(null, wifiArray.toString());
		} catch (IllegalViewOperationException e) {
			callback.invoke(e.getMessage());
		}
	}
}
