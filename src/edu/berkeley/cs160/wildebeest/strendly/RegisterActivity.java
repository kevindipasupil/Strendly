package edu.berkeley.cs160.wildebeest.strendly;

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
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private SharedPreferences preferences;	
	LocationManager manager;
	String providerName;
	Location curLoc = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		

        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
                                // Closing registration screen
				// Switching to Login Screen/closing register screen
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void login(View v) {
		SharedPreferences historyLog = getApplicationContext().getSharedPreferences("users", MODE_PRIVATE);
        SharedPreferences.Editor editor = historyLog.edit();
        
        EditText user = (EditText)findViewById(R.id.reg_email);
        EditText pass = (EditText)findViewById(R.id.reg_password);
        String u = user.getText().toString();
        String p = pass.getText().toString();
        
        editor.putString(u, p);
        editor.commit();
        
        //Toast.makeText(getApplicationContext(), u + " ::: " + p + " ::: ", 
        //		   Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, LoginActivity.class);
		startActivity(i);
	}

}
