package edu.berkeley.cs160.wildebeest.strendly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

public class EditItemsActivity extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_items);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExpEdit);

		// preparing list data
		prepareListData();

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

		final Button button = (Button) findViewById(R.id.saveChanges);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//goes back to the main screen
				Bundle bundle = new Bundle(); bundle.putString("msg", "The changes were successfully saved.");
				startActivity(new Intent(v.getContext(), MainActivity.class).putExtras(bundle)); 
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.main_actions, menu);
		return true;
	}

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

		List<String> salad = new ArrayList<String>();
		salad.add("Wedge");
		salad.add("Cobb");
		salad.add("Chinese Chicken");
		salad.add("Mixed Greens");
		salad.add("Pasta");

		listDataChild.put(listDataHeader.get(0), pizzas); // Header, Child data
		listDataChild.put(listDataHeader.get(1), sandys);
		listDataChild.put(listDataHeader.get(2), salad);
	}

	public int GetDipsFromPixel(float pixels)
	{
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}
	
//	@Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Take appropriate action for each action item click
//        switch (item.getItemId()) {
//        case R.id.log:
//        	Intent i = new Intent(this, LogItemsActivity.class);
//    		startActivity(i);
//            return true;
//        case R.id.edit:
//        	Intent i1 = new Intent(this, EditItemsActivity.class);
//        	startActivity(i1);
//            return true;
//        case R.id.stats:
//        	Intent i2 = new Intent(this, BarActivity.class);
//    		startActivity(i2);
//            return true;
//        default:
//            return super.onOptionsItemSelected(item);
//        }
//    }
}
