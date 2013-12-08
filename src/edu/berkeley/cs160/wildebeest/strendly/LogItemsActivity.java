package edu.berkeley.cs160.wildebeest.strendly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;

public class LogItemsActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_items);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
 
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
 
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
        expListView.setIndicatorBoundsRelative(width - GetDipsFromPixel(114), width - GetDipsFromPixel(40));
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
