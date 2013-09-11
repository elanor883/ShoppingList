package com.example.shoppinghistory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.actionbartest.R;

public class FragmentTab1 extends SherlockFragment {

	static final String KEY_CAT = "cat";
	static final String KEY_DATE = "date";
	static final String KEY_PRICE = "price";
	static final String KEY_TITLE = "title";
	static final String KEY_SUBTITLE = "sub";
	static final String KEY_CORNER = "corner";
	ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();
	Button dd;
	private View selected_item = null;
	private int offset_x = 0;
	private int offset_y = 0;
	int year;
	int month;
	int day;
	String cat;
	String date;
	int price;

	private static final int MY_DATE_DIALOG_ID = 3;

	@Override
	public SherlockFragmentActivity getSherlockActivity() {
		return super.getSherlockActivity();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab2.xml
		final View view = inflater.inflate(R.layout.fragmenttab1, container,
				false);

		// ViewGroup vg = (ViewGroup)findViewById(R.id.vg);

		dd = (Button) view.findViewById(R.id.add_item_button);
		final ListView lv = (ListView) view.findViewById(R.id.listitem_lv);

		DatabaseHandler db = new DatabaseHandler(getSherlockActivity());
		List<ShopList> clist = db.getLastFewItems();
		itemList.clear();
		for (ShopList s : clist) {
			HashMap<String, String> map = new HashMap<String, String>();
			// color =
			map.put(KEY_TITLE, s.getTypeName());
			map.put(KEY_SUBTITLE, s.getDate());
			map.put(KEY_CORNER, "" + s.getPrice()+ " ˆ");
			itemList.add(map);
		}
		// adapter.notifyDataSetChanged();

		db.close();

		// ArrayList<HashMap<String, String>> labelList = new
		// ArrayList<HashMap<String, String>>();

	//	final LabelListAdapter adapter = new LabelListAdapter(getActivity(),
	//			labelList);

	//	lv.setAdapter(adapter);
		
		
		final ShopListViewAdapter adapterList = new ShopListViewAdapter(getActivity(), itemList);
		lv.setAdapter(adapterList);

		dd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// custom dialog
				final Dialog dialog = new Dialog(getActivity());
				
				dialog.setContentView(R.layout.customdialog);
				dialog.setTitle("Add new item");
				
				// set the custom dialog components - text, image and button
				final EditText text_price = (EditText) dialog
						.findViewById(R.id.editText1);
				// text.setText("Android custom dialog example!");
				final DatePicker dp = (DatePicker) dialog
						.findViewById(R.id.datePicker1);
				//dp.setCalendarViewShown(false);
				int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		        if (currentapiVersion >= 11) {
		          try {
		            Method m = dp.getClass().getMethod("setCalendarViewShown", boolean.class);
		            m.invoke(dp, false);
		          }
		          catch (Exception e) {} // eat exception in our case
		        }
				Button dialogButton = (Button) dialog
						.findViewById(R.id.dialogbutton);
				// if button is clicked, close the custom dialog
				DatabaseHandler db = new DatabaseHandler(getSherlockActivity());
				List<String> list = new ArrayList<String>();
				list = db.getCategories();
				db.close();
				String[] array_spinner;
				int i = 0;
				/*
				 * for(String c: cat) { Log.d("cat", c); array_spinner[i++]=c; }
				 */
				final Spinner spin = (Spinner) dialog
						.findViewById(R.id.spinner1);
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						getActivity(),
						android.R.layout.simple_spinner_dropdown_item, list);
				spin.setAdapter(adapter);
				dialogButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						

						if(text_price.length()>0){
						
						year = dp.getYear();
						month = dp.getMonth() + 1;
						day = dp.getDayOfMonth();
						// updateDisplay();
						// Log.d("datepicker", ""+year+" "+month+" "+day);

						if (month < 10 && day < 10) {
							date = "" + year + "-0" + month + "-0" + day;
							Log.d("datepicker1", date);
						} else if (month < 10 && day >= 10) {
							date = "" + year + "-0" + month + "-" + day;
							Log.d("datepicker2", date);
						} else if (month >= 10 && day < 10) {
							date = "" + year + "-" + month + "-0" + day;
							Log.d("datepicker3", date);
						} else if (month >= 10 && day >= 10) {
							date = "" + year + "-" + month + "-" + day;
							Log.d("datepicker4", date);
						}

						cat = spin.getSelectedItem().toString();
						price = Integer.parseInt(text_price.getText()
								.toString());
						Log.d("result", "" + date + " " + cat + " " + price);

						ShopList slist = new ShopList(cat, price, date);
						DatabaseHandler db = new DatabaseHandler(
								getSherlockActivity());

						db.addContact(slist);
						db.close();

						HashMap<String, String> map = new HashMap<String, String>();
						// color =
						map.put(KEY_TITLE, cat);
						map.put(KEY_SUBTITLE, date);
						map.put(KEY_CORNER, "" + price+ " ˆ");
						itemList.add(map);
						//lv.setAdapter(adapter);
						adapterList.notifyDataSetChanged();
						
						

						dialog.dismiss();
						}
					}
				});

				dialog.show();
			}
		});

		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}

}