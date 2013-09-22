package com.example.shoppinghistory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;


public class FragmentTab3b2 extends SherlockFragment {

	static final String KEY_CAT = "cat";
	static final String KEY_DATE = "date";
	static final String KEY_PRICE = "price";
	static final String KEY_TITLE = "title";
	static final String KEY_SUBTITLE = "sub";
	static final String KEY_CORNER = "corner";
	static final String KEY_IMG = "img";
	LabelCostListAdapter adapterList;
	
	ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();
	Button dd;
	int year;
	int month;
	int day;
	String cat;
	String date;
	int price;
	View view;
	static ListView lv;

	@Override
	public SherlockFragmentActivity getSherlockActivity() {
		return super.getSherlockActivity();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		Log.d("vissza", "vissza");
		
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
			
			if (adapterList != null) {
				adapterList.notifyDataSetChanged();
			}

			else {
				Log.d("fr2", "kva anyjat enek a szarnak");
			}

		} else {
			Log.d("fr1vis", "fos");
		}

	}
	
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab2.xml
		view = inflater.inflate(R.layout.fragmenttab32port,
				container, false);

		// ViewGroup vg = (ViewGroup)findViewById(R.id.vg);

		 lv = (ListView) view
				.findViewById(R.id.listitem_lv_frag32);
		setHasOptionsMenu(true);

		Calendar c = Calendar.getInstance();

		DatabaseHandler db = new DatabaseHandler(getSherlockActivity());
		List<ShopList> shoplist2 = db.getCostPerDayPerType();
		ArrayList<String> catMonth = db.getCategories();
		List<Pair<String, Integer>> monthlyCost = new ArrayList<Pair<String, Integer>>();

		db.close();

		int i = 0;

		for (String type_name : catMonth) {
			monthlyCost.add(i, new Pair<String, Integer>(type_name, 0));
			for (ShopList item : shoplist2) {
				String date = item.getDate();
				String type = item.getTypeName();
				int month = Integer.parseInt(date.substring(5, 7));
				if (date.equals(FragmentTab3b.selected) && type.equals(type_name)) {
					int price = item.getPrice();
					int current_price = monthlyCost.get(i).second;
					monthlyCost.set(i, new Pair<String, Integer>(type_name,
							current_price + price));
				}
			}
			Log.d("details adat",
					monthlyCost.get(i).first + " " + monthlyCost.get(i).second);
			i++;

		}

		i = 0;
		adapterList = new LabelCostListAdapter(
				getActivity(), itemList);
		lv.setAdapter(adapterList);

		db = new DatabaseHandler(getSherlockActivity());
		for (Pair<String, Integer> current : monthlyCost) {
			HashMap<String, String> map = new HashMap<String, String>();
			// color =
			if (current.second != 0) {
				map.put(KEY_TITLE, current.first);
				// map.put(KEY_SUBTITLE, "vmi");
				map.put(KEY_CORNER, "" + current.second + " ˆ");
				map.put(KEY_IMG, db.getResId(current.first));
				itemList.add(map);
			}
		}
		// lv.setAdapter(adapter);
		db.close();
		adapterList.notifyDataSetChanged();

		if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE){
			
		
		

		lv.setOnItemClickListener(new OnItemClickListener() {

		    public void onItemClick(AdapterView<?> parent, View view, int position,
		            long id) {
		        Log.d("mukodik ez a szar?", "ja, megnyomtad");
		    	FragmentTab3b.pos = -1;
		        FragmentTab3b fr3 = new FragmentTab3b();
		      //  ((ShopListViewAdapter)(FragmentTab3b.lv.getAdapter())).notifyDataSetChanged();
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.detail32, fr3);
				
				//fr3.setPos(-1);
				Log.d("visszaallit", ""+FragmentTab3b.pos);
			//	ft.addToBackStack(null);
				ft.commit();
				
		    }

		});
		}
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d("FOS", "msg");
		setUserVisibleHint(true);
	}

	public void onBackPressed() {
       Log.d("kocsog", "kocsog");
	    
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
			//itemList.clear();
			
			adapterList.notifyDataSetChanged();


			return true;

		default:
			break;

		}
		return super.onOptionsItemSelected(item);
	}
}