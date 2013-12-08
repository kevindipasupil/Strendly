package edu.berkeley.cs160.wildebeest.strendly;

import java.util.ArrayList;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;

public class StatisticsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		
		ArrayList<Bar> points = new ArrayList<Bar>();
		Bar d = new Bar();
		d.setColor(Color.parseColor("#99CC00"));
		d.setName("Test1");
		d.setValue(10);
		Bar d2 = new Bar();
		d2.setColor(Color.parseColor("#FFBB33"));
		d2.setName("Test2");
		d2.setValue(20);
		points.add(d);
		points.add(d2);

		BarGraph g = (BarGraph)findViewById(R.id.graph);
		g.setBars(points);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistics, menu);
		return true;
	}
	
	public void pie(View v) {
		Intent i = new Intent(this, Statistics2.class);
		startActivity(i);
	}
	

}
