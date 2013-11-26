package edu.berkeley.cs160.wildebeest.strendly;

import edu.berkeley.cs160.wildebeest.strendly.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//clears all entries on app start for now, we'll move this later to some sort of clear button or function.
		SharedPreferences prefs = getApplicationContext().getSharedPreferences("numItems", MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.clear();
		editor.commit();
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void logItems(View v) {
		Intent i = new Intent(this, LogItemsActivity.class);
		startActivity(i);
	}

}
