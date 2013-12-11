package edu.berkeley.cs160.wildebeest.strendly;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class LineGraphFragment extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_line_graph, container, false);
        
        setLineGraph(rootView);
         
        return rootView;
    }
    
    private void setLineGraph(View v) {
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

		LineGraph li = (LineGraph)v.findViewById(R.id.lineGraph);
		li.addLine(l);
		li.setRangeY(0, 10);
		li.setLineToFill(0);
    }
}