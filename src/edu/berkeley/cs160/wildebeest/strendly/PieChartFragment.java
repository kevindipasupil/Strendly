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

public class PieChartFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_pie_chart, container, false);
		
		setPieChart(rootView);

		return rootView;
	}

	private void setPieChart(View v) {

		PieGraph pg = (PieGraph)v.findViewById(R.id.pieGraph);
		PieSlice slice = new PieSlice();
		slice.setColor(Color.parseColor("#99CC00"));
		slice.setValue(2);
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#FFBB33"));
		slice.setValue(1);
		pg.addSlice(slice);
		//slice = new PieSlice();
		//slice.setColor(Color.parseColor("#AA66CC"));
		//slice.setValue(8);
		//pg.addSlice(slice);
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