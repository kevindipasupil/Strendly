package edu.berkeley.cs160.wildebeest.strendly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;

public class BarGraphFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_bar_graph, container, false);

		setBarGraph(rootView);

		return rootView;
	}

	private void setBarGraph(View v) {

		SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences("numItems", Context.MODE_PRIVATE);
		Map<String,?> keys = prefs.getAll();
		Log.d("BarGraph: numItems count", "The count is " + prefs.getAll().size());
		ArrayList<Map.Entry<String,?>> topFiveItems = sortTopFive(prefs.getAll());
		ArrayList<Bar> points = new ArrayList<Bar>();

		for(Map.Entry<String,?> entry : topFiveItems) {
			Bar d = new Bar();
			int properVal = (Integer) entry.getValue();
			String key = entry.getKey();
			d.setColor(Color.parseColor("#4ea0ab"));
//			d.setColor(Color.parseColor("#99CC00"));
//			d.setColor(Color.parseColor("#CC99FF"));
//			d.setColor(Color.parseColor("#FFBB33"));
//			d.setColor(Color.parseColor("#FF5533"));
			d.setName(key.replaceAll(" [^ ]+$", "")); //removes item type (last word) cuz it's too long
			Log.d("BarGraph: topFiveItems", key + " value is " + properVal);
			d.setValue(properVal);
			points.add(d);
		}
		
		BarGraph g = (BarGraph)v.findViewById(R.id.barGraph);
		g.setBars(points);
		// the library defaults to $
		g.setUnit(" ");
		g.appendUnit(true);
	}
	
//	private String removeGroupHelper(String str) {
//		str.split
//	}

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