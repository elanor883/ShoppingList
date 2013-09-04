package com.example.actionbartest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class FragmentTab3b extends SherlockFragment {

	static final String KEY_TITLE = "title";
	static final String KEY_SUBTITLE = "sub";
	static final String KEY_CORNER = "corner";

	@Override
	public SherlockFragmentActivity getSherlockActivity() {
		return super.getSherlockActivity();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab2.xml
		View view = inflater.inflate(R.layout.fragmenttab3, container, false);
		final ListView lv = (ListView) view.findViewById(R.id.listView1);

		DatabaseHandler db = new DatabaseHandler(getSherlockActivity());
		// db.addContact(new ShopList("Food", 21, "2013-12-17"));
		// db.addContact(new ShopList("Party", 8, "2013-07-17" ));
		// db.addContact(new ShopList("Travel", 10, "2013-07-27"));
		//db.addContact(new ShopList("Accomodation", 215, "2013-07-18"));

		ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

		// adding each child node to HashMap key =&gt; value

		/*
		 * map.put(KEY_TITLE, "2013.07.07"); map.put(KEY_ARTIST, "Monday");
		 * map.put(KEY_DURATION, "32 ˆ"); songsList.add(map); HashMap<String,
		 * String> map2 = new HashMap<String, String>(); map2.put(KEY_TITLE,
		 * "2013.07.12"); map2.put(KEY_ARTIST, "Friday"); map2.put(KEY_DURATION,
		 * "42 ˆ");
		 */

		List<ShopList> shoplist = db.getSumCostPerDay();


		for (ShopList cn : shoplist) {
			String log = "Category: " + cn.getTypeName() + " ,Price: "
					+ cn.getPrice();
			// Writing Contacts to log
			HashMap<String, String> map = new HashMap<String, String>();
			String date = cn.getDate();
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7));
			int day = Integer.parseInt(date.substring(8, 10));

			Log.d("datum", "" + year + " " + month + " " + day);
			map.put(KEY_TITLE, date);
			map.put(KEY_SUBTITLE, dayOfWeek(year, month, day));
			map.put(KEY_CORNER, "" + cn.getPrice() + " ˆ");
			songsList.add(map);
			Log.d("Name: ", log);
		}
		ArrayList<String> cat = db.getCategories();
		for (String c : cat) {
			Log.d("cat", c);
		}
		// String values[] = new String[]{"ize", "hoze"};

		/*
		 * final ArrayList<String> list = new ArrayList<String>(); for (int i =
		 * 0; i < values.length; ++i) { list.add(values[i]); }
		 */
		/*
		 * final StableArrayAdapter adapter = new
		 * StableArrayAdapter(getActivity(),
		 * android.R.layout.simple_list_item_1, list); lv.setAdapter(adapter);
		 * 
		 * lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		 * 
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, final View
		 * view, int position, long id) { final String item = (String)
		 * parent.getItemAtPosition(position);
		 * view.animate().setDuration(2000).alpha(0) .withEndAction(new
		 * Runnable() {
		 * 
		 * @Override public void run() { list.remove(item);
		 * adapter.notifyDataSetChanged(); view.setAlpha(1); } }); }
		 * 
		 * });
		 */

		db.close();

		/*
		 * View vi = inflater.inflate(R.layout.list_row, null);
		 * 
		 * TextView title = (TextView)vi.findViewById(R.id.title); // title
		 * TextView artist = (TextView)vi.findViewById(R.id.artist); // artist
		 * name TextView duration = (TextView)vi.findViewById(R.id.duration); //
		 * duration
		 * 
		 * title.setText("2"); artist.setText("3"); duration.setText("4");
		 * 
		 * ArrayAdapter<String> adapter1 = new
		 * ArrayAdapter<String>(getSherlockActivity(), R.layout.list_item,
		 * mylist); lv.setAdapter(adapter1); //lv.setAdapter(adapter);
		 * 
		 * 
		 * ListAdapter lp;
		 */
		// songsList.add(map2);
		ShopListViewAdapter adapter = new ShopListViewAdapter(getActivity(), songsList);
		lv.setAdapter(adapter);

		// Click event for single list row
		/*
		 * lv.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * public void onItemClick(AdapterView<?> parent, View view, int
		 * position, long id) {
		 * 
		 * } });
		 */
		return view;
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}

	public String dayOfWeek(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		String weekday = null;
		int i = calendar.get(Calendar.DAY_OF_WEEK);

		if (i == 2) {
			weekday = "Monday";
		} else if (i == 3) {
			weekday = "Tuesday";
		} else if (i == 4) {
			weekday = "Wednesday";
		} else if (i == 5) {
			weekday = "Thursday";
		} else if (i == 6) {
			weekday = "Friday";
		} else if (i == 7) {
			weekday = "Saturday";
		} else if (i == 1) {
			weekday = "Sunday";
		}

		return weekday;

	}

}