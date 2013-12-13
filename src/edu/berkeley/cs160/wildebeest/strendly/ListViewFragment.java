package edu.berkeley.cs160.wildebeest.strendly;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

public class ListViewFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);

		return rootView;
	}


	//	@Override
	//    public boolean onOptionsItemSelected(MenuItem item) {
	//        // Take appropriate action for each action item click
	//        switch (item.getItemId()) {
	//        case R.id.log:
	//        	Intent i = new Intent(this, LogItemsActivity.class);
	//    		startActivity(i);
	//            return true;
	//        case R.id.edit:
	//        	Intent i1 = new Intent(this, EditItemsActivity.class);
	//        	startActivity(i1);
	//            return true;
	//        case R.id.stats:
	//        	Intent i2 = new Intent(this, BarActivity.class);
	//    		startActivity(i2);
	//            return true;
	//        default:
	//            return super.onOptionsItemSelected(item);
	//        }
	//    }
}