package edu.berkeley.cs160.wildebeest.strendly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class Receipt extends Activity {
	private SharedPreferences preferences;	
	LocationManager manager;
	String providerName;
	Location curLoc = null;
	Date mDate;
	ListView scrollView;
	int revenue, pizzaNum, sandwhichNum, saladNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receipt);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		scrollView = (ListView) findViewById(R.id.scroll);
		revenue = 0;
		pizzaNum = 0;
		sandwhichNum = 0;
		saladNum = 0;
		populateScrollView();
		
		final Button button = (Button) findViewById(R.id.logEntry);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SharedPreferences historyLog = getApplicationContext().getSharedPreferences("historyLog", MODE_PRIVATE);
				SharedPreferences.Editor editor = historyLog.edit();
				SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
				String format = s.format(new Date());
				editor.putInt(format, revenue);
				editor.putInt(format + "pizza", pizzaNum);
				editor.putInt(format + "sandwhich", sandwhichNum);
				editor.putInt(format + "salad", saladNum);
				editor.commit();

				//goes back to the main screen
				Bundle bundle = new Bundle(); bundle.putString("msg", "The purchase was successfully logged.");
				startActivity(new Intent(v.getContext(), MainActivity.class).putExtras(bundle)); 
				finish();
			}
		});
	}

	protected void populateScrollView() {
		ArrayList<String> listView_data = new ArrayList<String>();
		
		SharedPreferences prefs = getApplicationContext().getSharedPreferences("numItems", MODE_PRIVATE);
		
		// only use per logging session, so clear after using 
		SharedPreferences justLogged = getApplicationContext().getSharedPreferences("justLoggedItems", MODE_PRIVATE);
		Map<String,?> keys = justLogged.getAll();
		SharedPreferences.Editor editor = justLogged.edit();
		editor.clear();
		editor.commit();

		for(Map.Entry<String,?> entry : keys.entrySet()){
			Log.d("Receipt: numItems values", entry.getKey() + ": " + entry.getValue().toString());
			int properVal = (Integer) entry.getValue();
			listView_data.add(entry.getKey() + " (" + String.valueOf(properVal) + ")");
			
			revenue = revenue + (properVal * 5); //assuming here that each item costs 5 dollars.
			String type = entry.getKey().substring(entry.getKey().lastIndexOf(' ') + 1);
			if (type.equals("Pizza")) {
				pizzaNum = pizzaNum + 1;
			} else if (type.equals("Sandwich")) {
				sandwhichNum = sandwhichNum + 1;
			} else {
				saladNum = saladNum + 1;
			}   
		}
		scrollView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listView_data));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receipt, menu);

		return true;
	}

}
