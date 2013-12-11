package edu.berkeley.cs160.wildebeest.strendly;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PieActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pie);
		
		Button b = (Button) findViewById(R.id.piegraph);
		b.setEnabled(false);
		
		PieGraph pg = (PieGraph)findViewById(R.id.graph);
		PieSlice slice = new PieSlice();
		slice.setColor(Color.parseColor("#99CC00"));
		slice.setValue(2);
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#FFBB33"));
		slice.setValue(3);
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#AA66CC"));
		slice.setValue(8);
		pg.addSlice(slice);
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
