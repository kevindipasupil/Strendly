package edu.berkeley.cs160.wildebeest.strendly;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private SharedPreferences preferences;	
	LocationManager manager;
	String providerName;
	Location curLoc = null;
	Date mDate;
	ListView scrollView;
	int revenue, pizzaNum, sandwhichNum, saladNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void main(View v) {
		SharedPreferences historyLog = getApplicationContext().getSharedPreferences("users", MODE_PRIVATE);
   
        EditText user = (EditText)findViewById(R.id.user);
        EditText pass = (EditText)findViewById(R.id.pass);
        String u = user.getText().toString();
        String p = pass.getText().toString();
        String x = historyLog.getString(u, "o");
        
        //Toast.makeText(getApplicationContext(), u+" ::: " + p + " ::: " + x, 
        //		   Toast.LENGTH_LONG).show();
        
        if (x.equals(p)) {
        	
        	Intent i = new Intent(this, MainActivity.class);
    		startActivity(i);
        } else {
        	Toast.makeText(getApplicationContext(), "Wrong password, please try again.", 
         		   Toast.LENGTH_LONG).show();
        }
	}
	

}
