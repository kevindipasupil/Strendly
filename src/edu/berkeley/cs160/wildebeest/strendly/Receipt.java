package edu.berkeley.cs160.wildebeest.strendly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import android.app.Activity;
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
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;


public class Receipt extends Activity {
private SharedPreferences preferences;	
	LocationManager manager;
	String providerName;
	Location curLoc = null;
	Date mDate;
	ListView scrollView;
	int revenue = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receipt);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
        scrollView = (ListView) findViewById(R.id.scroll);
        revenue = 0;
        populateScrollView();
        final Button button = (Button) findViewById(R.id.logEntry);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences historyLog = getApplicationContext().getSharedPreferences("historyLog", MODE_PRIVATE);
                SharedPreferences.Editor editor = historyLog.edit();
                SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
                String format = s.format(new Date());
                editor.putInt(format, revenue);
                editor.commit();
            }
        });
	}
	
	protected void populateScrollView() {
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, 0);
		ArrayList<String> listView_data = new ArrayList<String>();

		SharedPreferences prefs = getApplicationContext().getSharedPreferences("numItems", MODE_PRIVATE);
		Map<String,?> keys = prefs.getAll();

		for(Map.Entry<String,?> entry : keys.entrySet()){
		            Log.d("map values",entry.getKey() + ": " + 
		                                   entry.getValue().toString());
		            int properVal = (Integer) entry.getValue() / 2;
		            if(entry.getKey().equals("00")) {
		            	listView_data.add("Pepperoni Pizza" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("01")) {
		            	listView_data.add("Hawaiin Pizza" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("02")) {
		            	listView_data.add("Meat Lovers Pizza" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("03")) {
		            	listView_data.add("Combo Pizza" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("04")) {
		            	listView_data.add("Vegetarian Pizza" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("05")) {
		            	listView_data.add("Pineapple & Jalapeno Pizza" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("10")) {
		            	listView_data.add("Turkey Cranberry Sandwich" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("11")) {
		            	listView_data.add("Chicken Pesto Sandwich" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("12")) {
		            	listView_data.add("Veggie Sandwich" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("13")) {
		            	listView_data.add("Caprese Sandwich" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("14")) {
		            	listView_data.add("Blackened Halibut Sandwich" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("15")) {
		            	listView_data.add("Eggplant Sandwich" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("20")) {
		            	listView_data.add("Wedge Salad" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("21")) {
		            	listView_data.add("Cobb Salad" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("22")) {
		            	listView_data.add("Chinese Chicken Salad" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("23")) {
		            	listView_data.add("Mixed Greens Salad" + " (" + String.valueOf(properVal) + ")");
		            } else if(entry.getKey().equals("24")) {
		            	listView_data.add("Pasta Salad" + " (" + String.valueOf(properVal) + ")");
		            }
		            revenue = revenue + (properVal * 5); //assuming here that each item costs 5 dollars.
		            
		 }
		
		//ListView data = new ListView(getApplicationContext());	
		Log.d("TAG", "pt1");
        //scrollView.setAdapter(listAdapter);
        Log.d("TAG", "pt2");
        scrollView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , listView_data));
        
//        scrollView.addView(data);
//        Log.d("TAG", "pt3");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receipt, menu);
		
		return true;
	}

}
