package com.mohammadag.voicetoggles;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.net.ConnectivityManager;

import com.mohammadag.voicetoggles.Constants.STATE;

public class MobileDataHandler {
	public static void handleStateChange(Context context, STATE newState) {
		boolean enabled;
		if (newState.equals(STATE.TURN_ON))
			enabled = true;
		else if (newState.equals(STATE.TURN_OFF))
			enabled = false;
		else
			return;
		try {
		    final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    final Class conmanClass = Class.forName(conman.getClass().getName());
		    final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
		    iConnectivityManagerField.setAccessible(true);
		    final Object iConnectivityManager = iConnectivityManagerField.get(conman);
		    final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
		    final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
		    setMobileDataEnabledMethod.setAccessible(true);
	
		    setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
		} catch (Throwable t) {
			
		}
	}
}
