package edu.berkeley.cs160.wildebeest.strendly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

public class ListViewFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);
		

		setBarGraph(rootView);


		return rootView;
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
	
	private void setBarGraph(View v) {
		ListView scrollView = (ListView) v.findViewById(R.id.scroll);
		SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences("numItems", Context.MODE_PRIVATE);
		Map<String,?> keys = prefs.getAll();
		Log.d("BarGraph: numItems count", "The count is " + prefs.getAll().size());
		ArrayList<Map.Entry<String,?>> topFiveItems = sortTopFive(prefs.getAll());
		ArrayList<Bar> points = new ArrayList<Bar>();
		int i = 0;
		int total = 0;
		ArrayList<String> listView_data = new ArrayList<String>();
		
		for(Map.Entry<String,?> entry : topFiveItems) {
			//Bar d = new Bar();
			int properVal = (Integer) entry.getValue();
			String key = entry.getKey();
			total+= properVal;
			TextView t;
			listView_data.add(key+ ": " + properVal);
			/**
			switch(i){
		    case 0 :
		    	t=(TextView)v.findViewById(R.id.t0); 
		    	t.append(key + ": " + properVal + "\n");
		    	break;
		    case 1 :
		    	t=(TextView)v.findViewById(R.id.t1); 
		    	t.append(key + ": " + properVal+ "\n");
		    	break;
		    case 2 :
		    	t=(TextView)v.findViewById(R.id.t2); 
		    	t.append(key + ": " + properVal+ "\n");
		    	break;
		    case 3 :
		    	t=(TextView)v.findViewById(R.id.t3); 
		    	t.append(key + ": " + properVal+ "\n");
		    	break;
		    case 4 :
		    	t=(TextView)v.findViewById(R.id.t4); 
		    	t.append(key + ": " + properVal+ "\n");
		    	break;
		    	
		    
			}
			*/
		
			//d.setColor(Color.parseColor("#4ea0ab"));
//			d.setColor(Color.parseColor("#99CC00"));
//			d.setColor(Color.parseColor("#CC99FF"));
//			d.setColor(Color.parseColor("#FFBB33"));
//			d.setColor(Color.parseColor("#FF5533"));
		}
		TextView t=(TextView)v.findViewById(R.id.totalSales);
		t.append(""+total);
		/**
		BarGraph g = (BarGraph)v.findViewById(R.id.barGraph);
		g.setBars(points);
		// the library defaults to $
		g.setUnit(" ");
		g.appendUnit(true);
		*/
		//listView_data.add("Total Sales: " + total);
		scrollView.setAdapter(new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, listView_data));
	}
	
	private ArrayList<Map.Entry<String,?>> sortTopFive(Map<String,?> map) {
		List<Map.Entry<String,?>> list = new LinkedList<Entry<String, ?>>(map.entrySet());
//	    Comparator<Number> c = Collections.reverseOrder();
//	    Collections.sort(list, c);

		// sort list based on comparator
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
                                       .compareTo(((Map.Entry) (o2)).getValue());
			}
		});
	    
		Collections.reverse(list);
	    ArrayList<Map.Entry<String,?>> topFive = new ArrayList<Map.Entry<String,?>>();
	    for (int i = 0; i < 5; i++) {
	    	topFive.add((Entry<String, ?>) list.get(i));
	    }
	    return topFive;
	}
}