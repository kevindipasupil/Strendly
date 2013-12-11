package edu.berkeley.cs160.wildebeest.strendly;

import java.util.ArrayList;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BarGraphFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_bar_graph, container, false);
		
		setBarGraph(rootView);

		return rootView;
	}

	private void setBarGraph(View v) {

		ArrayList<Bar> points = new ArrayList<Bar>();
		Bar d = new Bar();
		d.setColor(Color.parseColor("#99CC00"));
		d.setName("Pepperoni Pizza");
		d.setValue(20);
		Bar d2 = new Bar();
		d2.setColor(Color.parseColor("#FFBB33"));
		d2.setName("Turkey Cranberry");
		d2.setValue(10);
		points.add(d);
		points.add(d2);

		BarGraph g = (BarGraph)v.findViewById(R.id.barGraph);
		g.setBars(points);
	}
}