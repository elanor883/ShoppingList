package com.example.actionbartest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class FragmentTab2 extends SherlockFragment {

	String text = "0";
	String color = "R.drawable.btn1bg";
	int c_id = 0;
	static final String KEY_COLOR = "color";
	static final String KEY_TYPE = "category";
	ArrayList<HashMap<String, String>> labelList = new ArrayList<HashMap<String, String>>();
	int resid = 0;

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
		// Get the view from fragmenttab1.xml
		final View view = inflater.inflate(R.layout.fragmenttab2, container,
				false);
		Button plus = (Button) view.findViewById(R.id.plusCat);
		final ListView lv = (ListView) view.findViewById(R.id.cat_lv);

		DatabaseHandler db = new DatabaseHandler(getSherlockActivity());
		List<Categories> clist = db.getFullCategories();
		labelList.clear();
		for (Categories c : clist) {
			HashMap<String, String> map = new HashMap<String, String>();
			// color =
			map.put(KEY_COLOR, c.getResid());
			map.put(KEY_TYPE, c._type_name);
			labelList.add(map);
		}
		// adapter.notifyDataSetChanged();

		db.close();

		// ArrayList<HashMap<String, String>> labelList = new
		// ArrayList<HashMap<String, String>>();

		final LabelListAdapter adapter = new LabelListAdapter(getActivity(),
				labelList);

		lv.setAdapter(adapter);

		plus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.customdialog2);
				dialog.setTitle("Add new category");

				// set the custom dialog components - text, image and button
				final EditText text_cat = (EditText) dialog
						.findViewById(R.id.c_editText);

				final TextView textCheck = (TextView) dialog
						.findViewById(R.id.textCheck);
				// text.setText("Android custom dialog example!");

				Button btn1 = (Button) dialog.findViewById(R.id.button_1);
				btn1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// color="E01B5D";
						resid = R.drawable.btn1bg;
						color = "" + resid;
						c_id = 1;
					}
				});
				Button btn2 = (Button) dialog.findViewById(R.id.button_2);
				btn2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						resid = R.drawable.btn2bg;
						color = "" + resid;
						c_id = 2;
					}
				});
				Button btn3 = (Button) dialog.findViewById(R.id.button_3);
				btn3.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						resid = R.drawable.btn3bg;
						color = "" + resid;
						c_id = 3;
					}
				});
				Button btn4 = (Button) dialog.findViewById(R.id.button_4);
				btn4.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						resid = R.drawable.btn4bg;
						color = "" + resid;
						c_id = 4;
					}
				});
				Button btn5 = (Button) dialog.findViewById(R.id.button_5);
				btn5.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						resid = R.drawable.btn5bg;
						color = "" + resid;
						c_id = 5;

					}
				});
				Button btn6 = (Button) dialog.findViewById(R.id.button_6);
				btn6.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						resid = R.drawable.btn6bg;
						color = "" + resid;
						c_id = 6;
					}
				});
				Button btn7 = (Button) dialog.findViewById(R.id.button_7);
				btn7.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						resid = R.drawable.btn7bg;
						color = "" + resid;
						c_id = 7;
					}
				});
				Button btn8 = (Button) dialog.findViewById(R.id.button_8);
				btn8.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						resid = R.drawable.btn8bg;
						color = "" + resid;
						c_id = 8;
					}
				});
				Button btn9 = (Button) dialog.findViewById(R.id.button_9);
				btn9.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						resid = R.drawable.btn9bg;
						color = "" + resid;
						c_id = 9;
					}
				});
				Button btn10 = (Button) dialog.findViewById(R.id.button_10);
				btn10.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						resid = R.drawable.btn10bg;
						color = "" + resid;
						c_id = 10;
					}
				});

				Button dialogButton = (Button) dialog
						.findViewById(R.id.dialog_button2);
				// if button is clicked, close the custom dialog

				/*
				 * for(String c: cat) { Log.d("cat", c); array_spinner[i++]=c; }
				 */
				dialogButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						if (checkString(text_cat.getText().toString())) {
							textCheck.setText("Corect");
						

						text = text_cat.getText().toString();

						Categories categ = new Categories(text, color, c_id);
						Log.d("szoveg", text);
						DatabaseHandler db = new DatabaseHandler(
								getSherlockActivity());

						db.addCategory(categ);
						db.close();

						// Button btn = view.findViewById(R.id.list_button);
						HashMap<String, String> map = new HashMap<String, String>();
						// color =
						map.put(KEY_COLOR, color);
						map.put(KEY_TYPE, "" + text);
						labelList.add(map);

						adapter.notifyDataSetChanged();
						Log.d("color", color);
						dialog.dismiss();
						}
						
						else{
							
							textCheck.setText("Sorry, you can use only alphabetic characters");
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

	private boolean checkString(String s) {
		return s.matches("[a-zA-Z]+");
	}

}