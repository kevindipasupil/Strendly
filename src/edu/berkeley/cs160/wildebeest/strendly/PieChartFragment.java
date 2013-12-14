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
import android.widget.Button;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

public class PieChartFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_pie_chart, container, false);
		
		setPieChart(rootView);

		return rootView;
	}

	private void setPieChart(View v) {

		SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences("numItems", Context.MODE_PRIVATE);
		Log.d("PieChart: numItems count", "The count is " + prefs.getAll().size());
		ArrayList<Map.Entry<String,?>> topFiveItems = sortTopFive(prefs.getAll());

		PieGraph pg = (PieGraph)v.findViewById(R.id.pieGraph);
		for(Map.Entry<String,?> entry : topFiveItems) {
			PieSlice slice = new PieSlice();
			int properVal = (Integer) entry.getValue();
			String key = entry.getKey();
			slice.setColor(Color.parseColor("#4ea0ab"));
//			d.setColor(Color.parseColor("#99CC00"));
//			d.setColor(Color.parseColor("#AA66CC"));
//			d.setColor(Color.parseColor("#FFBB33"));
//			d.setColor(Color.parseColor("#FF5533"));
			Log.d("PieChart: topFiveItems", key + " value is " + properVal);
			slice.setValue(properVal);
			pg.addSlice(slice);
		}

	}

	private ArrayList<Map.Entry<String,?>> sortTopFive(Map<String,?> map) {
		List<Map.Entry<String,?>> list = new LinkedList<Entry<String, ?>>(map.entrySet());

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