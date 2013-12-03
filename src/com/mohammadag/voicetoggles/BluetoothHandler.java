package com.mohammadag.voicetoggles;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.widget.Toast;

import com.mohammadag.voicetoggles.Constants.STATE;

public class BluetoothHandler {
	public static void handleStateChange(Context context, STATE newState) {
		BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		if (adapter == null)
			return;

		boolean enabled = adapter.isEnabled();

		String speakText;

		switch (newState) {
		case TURN_OFF:
			if (enabled) {
				adapter.disable();
			}
			speakText = enabled ? "Bluetooth off" : "Bluetooth already off";
			break;
		case TURN_ON:
			if (!enabled)
				adapter.enable();

			speakText = enabled ? "Bluetooth already on" : "Bluetooth on";
			break;
		case TOGGLE:

			if (enabled)
				adapter.disable();
			else
				adapter.enable();

			speakText = String.format("Bluetooth %s", enabled ? "off" : "on");
			break;
		default:
			return;
		}

		Toast.makeText(context, speakText, Toast.LENGTH_SHORT).show();
		GoogleSearchApi.speak(context, speakText);
	}
}
