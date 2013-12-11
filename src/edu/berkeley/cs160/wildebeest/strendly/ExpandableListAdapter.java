package edu.berkeley.cs160.wildebeest.strendly;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<String>> _listDataChild;

	public ExpandableListAdapter(Context context, List<String> listDataHeader,
			HashMap<String, List<String>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		Log.d("e", this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon));
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		String childText = (String) getChild(groupPosition, childPosition);

		LayoutInflater infalInflater;

		if (convertView == null) {
			// for LogItems accordion
			if (this._context.getClass().getSimpleName().equals("LogItemsActivity")) {
				infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_item, null);
				TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
				txtListChild.setText(childText);
			}

			// for EditItems accordion
			if (this._context.getClass().getSimpleName().equals("EditItemsActivity")) {
				infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_item_edit_items, null);
				EditText editTxtListChild = (EditText) convertView.findViewById(R.id.itemName);
				editTxtListChild.setText(childText);
			}
		}
		if (this._context.getClass().getSimpleName().equals("LogItemsActivity")) {
			TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
			txtListChild.setText(childText);
			
			Button increaseQuantityImageView = (Button)convertView.findViewById(R.id.minus);
			increaseQuantityImageView.setOnClickListener(new View.OnClickListener() {

				  @Override
				  public void onClick(View view) {
						String key = groupPosition + "" + childPosition;
						SharedPreferences prefs = _context.getSharedPreferences("numItems", Context.MODE_PRIVATE);
						int value = prefs.getInt(key, 0);
						SharedPreferences.Editor editor = prefs.edit();
						editor.putInt(key, value - 1);
						editor.commit();
						Toast.makeText(_context.getApplicationContext(), "Minus Tapped",
								   Toast.LENGTH_LONG).show();
				  }
				});
			
			Button decreaseQuantityImageView = (Button)convertView.findViewById(R.id.plus);
			decreaseQuantityImageView.setOnClickListener(new View.OnClickListener() {

				  @Override
				  public void onClick(View view) {
						String key = groupPosition + "" + childPosition;
						SharedPreferences prefs = _context.getSharedPreferences("numItems", Context.MODE_PRIVATE);
						int value = prefs.getInt(key, 0);
						SharedPreferences.Editor editor = prefs.edit();
						editor.putInt(key, value + 1);
						editor.commit();
						Toast.makeText(_context.getApplicationContext(), "Plus Tapped",
								   Toast.LENGTH_LONG).show();
				  }
				});
		}
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@SuppressLint("CommitPrefEdits")
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
