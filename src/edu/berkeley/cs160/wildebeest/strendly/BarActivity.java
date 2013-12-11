package edu.berkeley.cs160.wildebeest.strendly;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.androidplot.pie.PieChart;
import com.androidplot.pie.Segment;
import com.androidplot.xy.XYPlot;
import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class BarActivity extends Activity {

	
	private XYPlot plot;
	
	
	private PieChart pie;

    private Segment s1;
    private Segment s2;
    private Segment s3;
    private Segment s4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		
		Button b = (Button) findViewById(R.id.bargraph);
		b.setEnabled(false);
		
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

		BarGraph g = (BarGraph)findViewById(R.id.graph);
		g.setBars(points);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.main_actions, menu);
		return true;
	}

	public void pie(View v) {
		Intent i = new Intent(this, PieActivity.class);
		startActivity(i);
	}
	public void line(View v) {
		Intent i = new Intent(this, LineActivity.class);
		startActivity(i);
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
