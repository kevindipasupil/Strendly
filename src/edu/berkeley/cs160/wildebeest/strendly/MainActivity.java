package edu.berkeley.cs160.wildebeest.strendly;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//uses custom font in assets/fonts
//	    TextView txt = (TextView) findViewById(R.id.strendly);  
//	    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/FinalFantasyTacticsAdvance.ttf");  
//	    txt.setTypeface(font);
	    
	    //for letting the user know that the purchase was logged
	    if(this.getIntent().getExtras() != null) {
	        Toast.makeText(this, this.getIntent().getExtras().getString("msg"),Toast.LENGTH_LONG).show();
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_actions, menu);
		return true;
        //return super.onCreateOptionsMenu(menu);
	}

	public void logItems(View v) {
		Intent i = new Intent(this, LogItemsActivity.class);
		startActivity(i);
	}

	public void editItems(View v) {
		Intent i = new Intent(this, EditItemsActivity.class);
		startActivity(i);
	}
	
	public void stats(View v) {
		Intent i = new Intent(this, StatisticsActivity.class);
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
