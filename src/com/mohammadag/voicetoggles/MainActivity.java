package com.mohammadag.voicetoggles;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toast.makeText(getApplicationContext(), "Plugin installed", Toast.LENGTH_SHORT).show();
		getPackageManager().setComponentEnabledSetting(new ComponentName(this,
				getPackageName() + ".MainActivity-Alias"),
				PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
