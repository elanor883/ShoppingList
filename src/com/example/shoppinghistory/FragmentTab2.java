package com.example.shoppinghistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
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
	Button selected;

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
		
		 // super.onCreate(savedInstanceState);
	      //  if(null == savedInstanceState){
	             //set you initial fragment object 
	        

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
				setRetainInstance(true);
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.customdialog2);
				dialog.setTitle("Add new category");

				// set the custom dialog components - text, image and button
				final EditText text_cat = (EditText) dialog
						.findViewById(R.id.c_editText);

				final TextView textCheck = (TextView) dialog
						.findViewById(R.id.textCheck);
				// text.setText("Android custom dialog example!");

				final Button btn1 = (Button) dialog.findViewById(R.id.button_1);
				btn1.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;
			            
						resid = R.drawable.btn1bg;
						color = "" + resid;
						c_id = 1;
						btn1.setPressed(true);
						
						if(selected != null && selected!=btn1)
						{
							selected.setPressed(false);
							
						}
						selected = btn1;
						return true;
					}
					
					
				}
				
				);

				final Button btn2 = (Button) dialog.findViewById(R.id.button_2);
				btn2.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn2bg;
						color = "" + resid;
						c_id = 2;
						
						btn2.setPressed(true);
						if(selected != null && selected != btn2)
						{
							selected.setPressed(false);
							selected = btn2;
						}
						selected = btn2;
						
						return true;
					}
				});
				final Button btn3 = (Button) dialog.findViewById(R.id.button_3);
				btn3.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn3bg;
						color = "" + resid;
						c_id = 3;
						
						btn3.setPressed(true);
						if(selected != null && selected != btn3)
						{
							selected.setPressed(false);
							selected = btn3;
						}
						selected = btn3;
						
						return true;
					}
				});
				
				final Button btn4 = (Button) dialog.findViewById(R.id.button_4);
				btn4.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn4bg;
						color = "" + resid;
						c_id = 4;
						
						btn4.setPressed(true);
						if(selected != null && selected != btn4)
						{
							selected.setPressed(false);
							selected = btn4;
						}
						selected = btn4;
						
						return true;
					}
				});
				
				final Button btn5 = (Button) dialog.findViewById(R.id.button_5);
				btn5.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn5bg;
						color = "" + resid;
						c_id = 5;
						
						btn5.setPressed(true);
						if(selected != null && selected != btn5)
						{
							selected.setPressed(false);
							selected = btn5;
						}
						selected = btn5;
						
						return true;
					}
				});
				
				final Button btn6 = (Button) dialog.findViewById(R.id.button_6);
				btn6.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn6bg;
						color = "" + resid;
						c_id = 6;
						
						btn6.setPressed(true);
						if(selected != null && selected != btn6)
						{
							selected.setPressed(false);
							selected = btn6;
						}
						selected = btn6;
						
						return true;
					}
				});
				
				final Button btn7 = (Button) dialog.findViewById(R.id.button_7);
				btn7.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn7bg;
						color = "" + resid;
						c_id = 7;
						
						btn7.setPressed(true);
						if(selected != null && selected != btn7)
						{
							selected.setPressed(false);
							selected = btn7;
						}
						selected = btn7;
						
						return true;
					}
				});
				
				final Button btn8 = (Button) dialog.findViewById(R.id.button_8);
				btn8.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn8bg;
						color = "" + resid;
						c_id = 8;
						
						btn8.setPressed(true);
						if(selected != null && selected != btn8)
						{
							selected.setPressed(false);
							selected = btn8;
						}
						selected = btn8;
						
						return true;
					}
				});
				
				final Button btn9 = (Button) dialog.findViewById(R.id.button_9);
				btn9.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn9bg;
						color = "" + resid;
						c_id = 9;
						
						btn9.setPressed(true);
						if(selected != null && selected != btn9)
						{
							selected.setPressed(false);
							selected = btn9;
						}
						selected = btn9;
						
						return true;
					}
				});
				
				final Button btn10 = (Button) dialog.findViewById(R.id.button_10);
				btn10.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn10bg;
						color = "" + resid;
						c_id = 10;
						
						btn10.setPressed(true);
						if(selected != null && selected != btn10)
						{
							selected.setPressed(false);
							selected = btn10;
						}
						selected = btn10;
						
						return true;
					}
				});
				

				final Button btn11 = (Button) dialog.findViewById(R.id.button_11);
				btn11.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn11bg;
						color = "" + resid;
						c_id = 11;
						
						btn11.setPressed(true);
						if(selected != null && selected != btn11)
						{
							selected.setPressed(false);
							selected = btn11;
						}
						selected = btn11;
						
						return true;
					}
				});
				
				final Button btn12 = (Button) dialog.findViewById(R.id.button_12);
				btn12.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn12bg;
						color = "" + resid;
						c_id = 12;
						
						btn12.setPressed(true);
						if(selected != null && selected != btn12)
						{
							selected.setPressed(false);
							selected = btn12;
						}
						selected = btn12;
						
						return true;
					}
				});
				
				final Button btn13 = (Button) dialog.findViewById(R.id.button_13);
				btn13.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn13bg;
						color = "" + resid;
						c_id = 13;
						
						btn13.setPressed(true);
						if(selected != null && selected != btn13)
						{
							selected.setPressed(false);
							selected = btn13;
						}
						selected = btn13;
						
						return true;
					}
				});
				
				final Button btn14 = (Button) dialog.findViewById(R.id.button_14);
				btn14.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn14bg;
						color = "" + resid;
						c_id = 14;
						
						btn14.setPressed(true);
						if(selected != null && selected != btn14)
						{
							selected.setPressed(false);
							selected = btn14;
						}
						selected = btn14;
						
						return true;
					}
				});
				
				final Button btn15 = (Button) dialog.findViewById(R.id.button_15);
				btn15.setOnTouchListener(new OnTouchListener()
				{

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN) return true;
			            if(event.getAction()!=MotionEvent.ACTION_UP) return false;

						resid = R.drawable.btn15bg;
						color = "" + resid;
						c_id = 15;
						
						btn15.setPressed(true);
						if(selected != null && selected != btn15)
						{
							selected.setPressed(false);
							selected = btn15;
						}
						selected = btn15;
						
						return true;
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
						text = text_cat.getText().toString();
						DatabaseHandler db = new DatabaseHandler(
								getSherlockActivity());

						boolean isTypeExist = false;

						ArrayList<String> types = db.getCategories();
						for (String c : types) {
							if (c.equals(text) && isTypeExist == false) {
								isTypeExist = true;
							}
						}
						db.close();
						if (checkString(text) && isTypeExist == false) {
							textCheck.setText("Correct");

							Categories categ = new Categories(text, color, c_id);
							Log.d("szoveg", text);
							DatabaseHandler db1 = new DatabaseHandler(
									getSherlockActivity());
					
								db1.addCategory(categ);
								HashMap<String, String> map = new HashMap<String, String>();
								// color =
								map.put(KEY_COLOR, color);
								map.put(KEY_TYPE, "" + text);
								labelList.add(map);

								adapter.notifyDataSetChanged();
								Log.d("color", color);

							// Button btn = view.findViewById(R.id.list_button);
							db1.close();
							dialog.dismiss();
						}

						else if(isTypeExist==true){
							textCheck.setText("This category already exists");
						}
						
						else{

							textCheck
									.setText("Sorry, you can use only alphabetic characters");
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