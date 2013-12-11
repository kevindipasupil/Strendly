package edu.berkeley.cs160.wildebeest.strendly;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LineActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_line);
		
		Button b = (Button) findViewById(R.id.linegraph);
		b.setEnabled(false);
		
		Line l = new Line();
		LinePoint p = new LinePoint(0,5);
		p.setX(0);
		p.setY(5);
		l.addPoint(p);
		p = new LinePoint(8,8);
		p.setX(8);
		p.setY(8);
		l.addPoint(p);
		p = new LinePoint(10,4);
		p.setX(10);
		p.setY(4);
		l.addPoint(p);
		p = new LinePoint(3,6);
		l.addPoint(p);
		p = new LinePoint(1,2);
		l.addPoint(p);
		l.setColor(Color.parseColor("#FFBB33"));

		LineGraph li = (LineGraph)findViewById(R.id.graph);
		li.addLine(l);
		li.setRangeY(0, 10);
		li.setLineToFill(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.main_actions, menu);
        return true;
	}
	public void bar(View v) {
		Intent i = new Intent(this, BarActivity.class);
		startActivity(i);
	}
	public void pie(View v) {
		Intent i = new Intent(this, PieActivity.class);
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
