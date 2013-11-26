package edu.berkeley.cs160.wildebeest.strendly;

import java.util.Date;

import android.app.Activity;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ScrollView;


public class Receipt extends Activity {
private SharedPreferences preferences;
	
    private LinearLayout gallery;
	
	LocationManager manager;
	String providerName;
	Location curLoc = null;
	Date mDate;
	ScrollView scrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receipt);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		gallery = (LinearLayout) findViewById(R.id.gallery);
        scrollView = (ScrollView) findViewById(R.id.scroll);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receipt, menu);
		
		return true;
	}

}
