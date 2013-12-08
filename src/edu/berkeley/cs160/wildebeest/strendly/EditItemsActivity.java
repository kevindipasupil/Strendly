package edu.berkeley.cs160.wildebeest.strendly;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_items);
		
	    Button addButton = (Button)findViewById(R.id.addItem);
	    final EditText itemEdit   = (EditText)findViewById(R.id.itemName);

	    addButton.setOnClickListener(
	        new View.OnClickListener()
	        {
	            public void onClick(View view)
	            {
	                Log.v("EditText", itemEdit.getText().toString());
	            }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_items, menu);
		return true;
	}
//	
//    public boolean addItem() {
//    	//TODO: Add some sort of feedback!
//    	Log.d("ListClick", "ListClick" + groupPosition + childPosition + "");
//    	String key = groupPosition + "" + childPosition;
//    	SharedPreferences prefs = _context.getSharedPreferences("numItems", Context.MODE_PRIVATE);
//    	int value = prefs.getInt(key, 0);
//    	SharedPreferences.Editor editor = prefs.edit();
//    	editor.putInt(key, value + 1);
//    	editor.commit();
//        return true;
//    }

}
