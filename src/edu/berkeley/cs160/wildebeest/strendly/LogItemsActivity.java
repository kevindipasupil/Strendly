package edu.berkeley.cs160.wildebeest.strendly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;

public class LogItemsActivity extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	private List<String> allItems = new ArrayList<String>();
	public static boolean created = false;

	@TargetApi(18)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_items);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();
		// initializes item history to 0
		if (!created) {
			initNumItemHist();
			Log.d("LogItems: onCreate", "Initialized list history to 0");
		}
		created = true;

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// for moving the group indicator to the right
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		Log.d("WIDTH", "WIDTH IS " + width);
		//this code for adjusting the group indicator into right side of the view
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
			expListView.setIndicatorBounds(width - GetDipsFromPixel(114), width - GetDipsFromPixel(40));
		} else {
			expListView.setIndicatorBoundsRelative(width - GetDipsFromPixel(114), width - GetDipsFromPixel(40));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		MenuInflater inflater = getMenuInflater();
		//inflater.inflate(R.menu.main_actions, menu);
		return true;
		//return super.onCreateOptionsMenu(menu);
	}

	public void receipt(View v) {
		Intent i = new Intent(this, Receipt.class);
		startActivity(i);
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Pizza");
		listDataHeader.add("Sandwiches");
		listDataHeader.add("Salads");

		// Adding child data
		List<String> pizzas = new ArrayList<String>();
		pizzas.add("Pepperoni");
		pizzas.add("Hawaiian");
		pizzas.add("Meat Lovers");
		pizzas.add("Combo");
		pizzas.add("Vegetarian");
		pizzas.add("Pineapple & Jalapeno");

		List<String> sandys = new ArrayList<String>();
		sandys.add("Turkey Cranberry");
		sandys.add("Chicken Pesto");
		sandys.add("Veggie");
		sandys.add("Caprese");
		sandys.add("Blackened Halibut");
		sandys.add("Eggplant");

		List<String> salads = new ArrayList<String>();
		salads.add("Wedge");
		salads.add("Cobb");
		salads.add("Chinese Chicken");
		salads.add("Mixed Greens");
		salads.add("Pasta");

		// sets all items in allItems with the correct key (item + item_group)
		ArrayList<String> pizzaList = new ArrayList<String>();
		for (String pizza : pizzas) {
			pizzaList.add(pizza + " Pizza");
		}
		allItems.addAll(pizzaList);
		ArrayList<String> sandwichList = new ArrayList<String>();
		for (String sandwich : sandys) {
			sandwichList.add(sandwich + " Sandwich");
		}
		allItems.addAll(sandwichList);
		ArrayList<String> saladList = new ArrayList<String>();
		for (String salad : salads) {
			saladList.add(salad + " Salad");
		}
		allItems.addAll(salads);

		listDataChild.put(listDataHeader.get(0), pizzas); // Header, Child data
		listDataChild.put(listDataHeader.get(1), sandys);
		listDataChild.put(listDataHeader.get(2), salads);
	}

	private void initNumItemHist() {
		for (String item : allItems) {
			String key = item;
			SharedPreferences prefs = getSharedPreferences("numItems", MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putInt(key, 0);
			editor.commit();
		}

		// ensuring that the justLoggedItems history is empty when starting the app
		SharedPreferences justLogged = getApplicationContext().getSharedPreferences("justLoggedItems", MODE_PRIVATE);
		SharedPreferences.Editor editor = justLogged.edit();
		editor.clear();
		editor.commit();
	}

	public int GetDipsFromPixel(float pixels)
	{
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.log:
			Intent i = new Intent(this, LogItemsActivity.class);
			startActivity(i);
			return true;
		case R.id.edit:
			Intent i1 = new Intent(this, EditItemsActivity.class);
			startActivity(i1);
			return true;
		case R.id.stats:
			Intent i2 = new Intent(this, BarActivity.class);
			startActivity(i2);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
