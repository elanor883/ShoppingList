package com.example.shoppinghistory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.androidplot.pie.PieChart;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;


public class FragmentTab4b extends SherlockFragment {

	 private PieChart pie;

	    private Segment s1;
	    private Segment s2;
	    private Segment s3;
	    private Segment s4;
	    
		SherlockFragmentActivity parent;
		View view;
	    
	@Override
	public SherlockFragmentActivity getSherlockActivity() {
		return super.getSherlockActivity();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if (isVisibleToUser) {
			/*
			 * parent = getSherlockActivity();
			 * 
			 * if (parent instanceof MainActivity && ((MainActivity)
			 * parent).dark_bkg == false) { // ((MainActivity)
			 * parent).activePage = 1; mView.setBackgroundColor(Color.WHITE); }
			 */
			// labelList.clear();
			// adapter.notifyDataSetChanged();
			
			if (MainActivity.dark_bkg == false && view != null) {
				// ((MainActivity) parent).activePage = 1;
				view.setBackgroundColor(Color.WHITE);
			} else if (MainActivity.dark_bkg == true && view != null) {
				view.setBackgroundColor(Color.BLACK);
			}
			

		} else {
			Log.d("fr1vis", "fos");
		}

	}
	
	@Override
	public void onStart(){
		super.onStart();

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab1.xml
		view = inflater.inflate(R.layout.stat, container, false);

		boolean dark_bkg = true;
		SherlockFragmentActivity parent = getSherlockActivity();

		if (parent instanceof MainActivity) {
			dark_bkg = ((MainActivity) parent).dark_bkg;
			Log.d("Fragment2", ""+dark_bkg);
		}

		if (!dark_bkg) {
			view.setBackgroundColor(Color.WHITE);

		} else {
			view.setBackgroundColor(Color.BLACK);
		}
		
		setHasOptionsMenu(true);
		XYPlot mySimpleXYPlot = (XYPlot) view.findViewById(R.id.mySimpleXYPlot);


		//mySimpleXYPlot.setPlotPaddingBottom(40);
		//mySimpleXYPlot.setPlotPadding(10, 10, 10, 10);
		//mySimpleXYPlot.setPlotPadding(20, 20, 20, 20);
	//	mySimpleXYPlot.setPlotMargins(20, 20, 20, 20);
		
		
		Calendar c = Calendar.getInstance();
		int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int currentMonth = c.get(Calendar.MONTH);
		int currentday = c.get(Calendar.DAY_OF_MONTH);

		Log.d("statfragment", "" + monthMaxDays + " " + currentday);

		// Integer[] timestamp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		// Integer[] series2Numbers = { 4, 6, 0, 0, 2, 10,0,2,7,0,0,1 };
		ArrayList<Integer> days = new ArrayList<Integer>();
		ArrayList<Integer> costs = new ArrayList<Integer>();

		// ArrayList<Integer> days = new ArrayList<Integer>();
		//ArrayList<ArrayList<Integer>> costs2 = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < monthMaxDays; ++i) {
			days.add(i, i + 1);
			costs.add(i, 0);
		}

		DatabaseHandler db = new DatabaseHandler(getSherlockActivity());
		List<ShopList> shoplist = db.getSumCostPerDay();
		List<ShopList> shoplist2 = db.getCostPerDayPerType();
		List<Pair<Integer, Integer>> dailyCost = new ArrayList<Pair<Integer, Integer>>();
		ArrayList<String> catMonth = db.getCategories();
		List<Pair<String, Integer>> monthlyCost = new ArrayList<Pair<String, Integer>>();

		for (ShopList cn : shoplist) {

			String date = cn.getDate();
			// int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7));
			int day = Integer.parseInt(date.substring(8, 10));

			int price = cn.getPrice();
			// Pair<Integer,Integer> tmp
			if (month == currentMonth + 1) {
				dailyCost.add(new Pair<Integer, Integer>(day, price));
			}
			// Log.d("Name: ", log);
		}
		db.close();
		int i = 0;

		for (String type_name : catMonth) {
			monthlyCost.add(i, new Pair<String, Integer>(type_name,0));
			for (ShopList item : shoplist2) {
				String date = item.getDate();
				String type = item.getTypeName();
				int month = Integer.parseInt(date.substring(5, 7));
				if (month == currentMonth + 1 && type.equals(type_name)) {
					int price = item.getPrice();
					int current_price = monthlyCost.get(i).second;
					monthlyCost.set(i, new Pair<String, Integer>(type_name, current_price+price));
				}
			}
			Log.d("pie-hoz adat", monthlyCost.get(i).first + " " + monthlyCost.get(i).second);
			i++;
			
		}


		for (int j = 0; j < dailyCost.size(); ++j) {
			int current = dailyCost.get(j).first;
			// costs.set(index, object)
			costs.set(current - 1, dailyCost.get(j).second);
			Log.d("statos", "" + current + " " + dailyCost.get(j).second);
		}


		for (int j = 0; j < monthMaxDays; ++j) {
			Log.d("length of shits", "" + days.get(j) + " " + costs.get(j));
		}
		
		
		XYSeries lastmonth = new SimpleXYSeries(days, costs, null);

		mySimpleXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 5);
		mySimpleXYPlot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 10);
		mySimpleXYPlot.setDomainValueFormat(new DecimalFormat("#"));
		mySimpleXYPlot.setRangeValueFormat(new DecimalFormat("#"));
		mySimpleXYPlot.setRangeLabel("ˆ");
		mySimpleXYPlot.setDomainLabel("day of the month");
		
		mySimpleXYPlot.addSeries(
				lastmonth,
				new LineAndPointFormatter(Color.rgb(0, 0, 200), Color.rgb(0, 0,
						100), Color.rgb(150, 150, 190), null));


		/*mySimpleXYPlot.getBackgroundPaint().setAlpha(0);
		mySimpleXYPlot.getGraphWidget().getBackgroundPaint().setAlpha(0);
		mySimpleXYPlot.getGraphWidget().getGridBackgroundPaint().setAlpha(0);*/

		
		pie = (PieChart) view.findViewById(R.id.mySimplePieChart);

		pie.setBackgroundColor(404040);
		//pie.set
		ArrayList<Segment> segments = new ArrayList<Segment>();
		
		for (int j=0; j<monthlyCost.size(); ++j){
			Segment s = new Segment(monthlyCost.get(j).first, monthlyCost.get(j).second);
			segments.add(s);
			pie.addSeries(s, new SegmentFormatter(
	                Color.rgb(120+j*8, 100-j*7, 60+j*15), Color.DKGRAY,Color.DKGRAY, Color.DKGRAY));
		}

		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		DatabaseHandler db;
		switch (item.getItemId()) {

		case R.id.settings_btn:
			// write your code here

			// db = new DatabaseHandler(this); db.exportDB(); db.close();

			Log.d("fr1", "import");
		
			
			if(MainActivity.dark_bkg==true)
			{
				MainActivity.dark_bkg = false;
				view.setBackgroundColor(Color.WHITE);
				
			}
			else
			{
				MainActivity.dark_bkg = true;
				view.setBackgroundColor(Color.BLACK);
			}
			

			return true;

		default:
			break;

		}
		return super.onOptionsItemSelected(item);
	}
}