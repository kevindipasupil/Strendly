package edu.berkeley.cs160.wildebeest.strendly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

public class EditItemsActivity extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_items);

//		Button addButton = (Button)findViewById(R.id.addItem);
//		final EditText itemEdit   = (EditText)findViewById(R.id.itemName);
//
//		addButton.setOnClickListener(
//				new View.OnClickListener()
//				{
//					public void onClick(View view)
//					{
//						Log.v("EditText", itemEdit.getText().toString());
//					}
//				});

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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_items, menu);
		return true;
	}
	//	
	//    public boolean addItem() {
	//    	//TODO: Add some sort of feedback!
	//    	Log.d("ListClick", "ListClick" + groupPosition + childPosition + "");
	//    	String key = groupPosition + "" + childPosition;
	//    	SharedPreferences prefs = _context.getSharedPreferences("numItems", Context.MODE_PRIVATE);
	//    	int value = prefs.getInt(key, 0);
	//    	SharedPreferences.Editor editor = prefs.edit();
	//    	editor.putInt(key, value + 1);
	//    	editor.commit();
	//        return true;
	//    }

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
}
