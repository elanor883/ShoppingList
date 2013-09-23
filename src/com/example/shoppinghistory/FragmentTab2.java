package com.example.shoppinghistory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class FragmentTab2 extends SherlockFragment {

	String text = "0";
	String color = "R.drawable.btn1bg";
	int c_id = 0;
	static final String KEY_COLOR = "color";
	static final String KEY_TYPE = "category";
	ArrayList<HashMap<String, String>> labelList = new ArrayList<HashMap<String, String>>();
	int resid = 0;
	Button selected;

	LayoutInflater mInflater;
	ViewGroup mContainer;
	Bundle mSavedInstanceState;
	View view;
	SherlockFragmentActivity parent;
	LabelListAdapter adapter;
	ListView lv;

	@Override
	public SherlockFragmentActivity getSherlockActivity() {
		return super.getSherlockActivity();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		adapter.notifyDataSetChanged();

	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		/*
		 * try { mListener = (OnArticleSelectedListener) activity; } catch
		 * (ClassCastException e) { throw new
		 * ClassCastException(activity.toString() +
		 * " must implement OnArticleSelectedListener"); }
		 */

		Log.d("fr2", "onattach");
		// adapter.notifyDataSetChanged();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		Log.d("visiblefr2", "" + MainActivity.fr1Imp);
		if (isVisibleToUser) {
			if (MainActivity.fr2Imp == true) {
				MainActivity.fr2Imp = false;
				Log.d("fragment2", "visible import");
				refreshCurrentFragment();
			}

			if (MainActivity.dark_bkg == false && view != null) {
				// ((MainActivity) parent).activePage = 1;
				view.setBackgroundColor(Color.WHITE);
			} else if (MainActivity.dark_bkg == true && view != null) {
				view.setBackgroundColor(Color.BLACK);
			}

			if (adapter != null) {
				adapter.notifyDataSetChanged();
			}

			else {
				Log.d("fr2", "kva anyjat enek a szarnak");
			}

		} else {
			Log.d("fr1vis", "fos");
		}

	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab1.xml

		// super.onCreate(savedInstanceState);
		// if(null == savedInstanceState){
		// set you initial fragment object

		view = inflater.inflate(R.layout.fragmenttab2, container, false);

		mInflater = inflater;
		mContainer = container;
		mSavedInstanceState = savedInstanceState;

		boolean dark_bkg = true;
		SherlockFragmentActivity parent = getSherlockActivity();
		setHasOptionsMenu(true);

		if (parent instanceof MainActivity) {
			dark_bkg = ((MainActivity) parent).dark_bkg;
			Log.d("Fragment2", "" + dark_bkg);
		}

		if (!dark_bkg) {
			view.setBackgroundColor(Color.WHITE);

		} else {
			view.setBackgroundColor(Color.BLACK);
		}

		// setHasOptionsMenu(true);

		Button plus = (Button) view.findViewById(R.id.plusCat);
		lv = (ListView) view.findViewById(R.id.cat_lv);

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

		adapter = new LabelListAdapter(getActivity(), labelList);

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
				btn1.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn1bg;
						color = "" + resid;
						c_id = 1;
						btn1.setPressed(true);

						if (selected != null && selected != btn1) {
							selected.setPressed(false);

						}
						selected = btn1;
						return true;
					}

				}

				);

				final Button btn2 = (Button) dialog.findViewById(R.id.button_2);
				btn2.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn2bg;
						color = "" + resid;
						c_id = 2;

						btn2.setPressed(true);
						if (selected != null && selected != btn2) {
							selected.setPressed(false);
							selected = btn2;
						}
						selected = btn2;

						return true;
					}
				});
				final Button btn3 = (Button) dialog.findViewById(R.id.button_3);
				btn3.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn3bg;
						color = "" + resid;
						c_id = 3;

						btn3.setPressed(true);
						if (selected != null && selected != btn3) {
							selected.setPressed(false);
							selected = btn3;
						}
						selected = btn3;

						return true;
					}
				});

				final Button btn4 = (Button) dialog.findViewById(R.id.button_4);
				btn4.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn4bg;
						color = "" + resid;
						c_id = 4;

						btn4.setPressed(true);
						if (selected != null && selected != btn4) {
							selected.setPressed(false);
							selected = btn4;
						}
						selected = btn4;

						return true;
					}
				});

				final Button btn5 = (Button) dialog.findViewById(R.id.button_5);
				btn5.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn5bg;
						color = "" + resid;
						c_id = 5;

						btn5.setPressed(true);
						if (selected != null && selected != btn5) {
							selected.setPressed(false);
							selected = btn5;
						}
						selected = btn5;

						return true;
					}
				});

				final Button btn6 = (Button) dialog.findViewById(R.id.button_6);
				btn6.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn6bg;
						color = "" + resid;
						c_id = 6;

						btn6.setPressed(true);
						if (selected != null && selected != btn6) {
							selected.setPressed(false);
							selected = btn6;
						}
						selected = btn6;

						return true;
					}
				});

				final Button btn7 = (Button) dialog.findViewById(R.id.button_7);
				btn7.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn7bg;
						color = "" + resid;
						c_id = 7;

						btn7.setPressed(true);
						if (selected != null && selected != btn7) {
							selected.setPressed(false);
							selected = btn7;
						}
						selected = btn7;

						return true;
					}
				});

				final Button btn8 = (Button) dialog.findViewById(R.id.button_8);
				btn8.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn8bg;
						color = "" + resid;
						c_id = 8;

						btn8.setPressed(true);
						if (selected != null && selected != btn8) {
							selected.setPressed(false);
							selected = btn8;
						}
						selected = btn8;

						return true;
					}
				});

				final Button btn9 = (Button) dialog.findViewById(R.id.button_9);
				btn9.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn9bg;
						color = "" + resid;
						c_id = 9;

						btn9.setPressed(true);
						if (selected != null && selected != btn9) {
							selected.setPressed(false);
							selected = btn9;
						}
						selected = btn9;

						return true;
					}
				});

				final Button btn10 = (Button) dialog
						.findViewById(R.id.button_10);
				btn10.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn10bg;
						color = "" + resid;
						c_id = 10;

						btn10.setPressed(true);
						if (selected != null && selected != btn10) {
							selected.setPressed(false);
							selected = btn10;
						}
						selected = btn10;

						return true;
					}
				});

				final Button btn11 = (Button) dialog
						.findViewById(R.id.button_11);
				btn11.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn11bg;
						color = "" + resid;
						c_id = 11;

						btn11.setPressed(true);
						if (selected != null && selected != btn11) {
							selected.setPressed(false);
							selected = btn11;
						}
						selected = btn11;

						return true;
					}
				});

				final Button btn12 = (Button) dialog
						.findViewById(R.id.button_12);
				btn12.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn12bg;
						color = "" + resid;
						c_id = 12;

						btn12.setPressed(true);
						if (selected != null && selected != btn12) {
							selected.setPressed(false);
							selected = btn12;
						}
						selected = btn12;

						return true;
					}
				});

				final Button btn13 = (Button) dialog
						.findViewById(R.id.button_13);
				btn13.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn13bg;
						color = "" + resid;
						c_id = 13;

						btn13.setPressed(true);
						if (selected != null && selected != btn13) {
							selected.setPressed(false);
							selected = btn13;
						}
						selected = btn13;

						return true;
					}
				});

				final Button btn14 = (Button) dialog
						.findViewById(R.id.button_14);
				btn14.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn14bg;
						color = "" + resid;
						c_id = 14;

						btn14.setPressed(true);
						if (selected != null && selected != btn14) {
							selected.setPressed(false);
							selected = btn14;
						}
						selected = btn14;

						return true;
					}
				});

				final Button btn15 = (Button) dialog
						.findViewById(R.id.button_15);
				btn15.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN)
							return true;
						if (event.getAction() != MotionEvent.ACTION_UP)
							return false;

						resid = R.drawable.btn15bg;
						color = "" + resid;
						c_id = 15;

						btn15.setPressed(true);
						if (selected != null && selected != btn15) {
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

						else if (isTypeExist == true) {
							textCheck.setText("This category already exists");
						}

						else {

							textCheck
									.setText("Sorry, you can use only alphabetic characters");
						}
					}
				});
				dialog.show();

			}

		});

		adapter.notifyDataSetChanged();
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

	/*
	 * public boolean onMenuItemSelected(int featureId, MenuItem item) {
	 * DatabaseHandler db; switch (item.getItemId()) {
	 * 
	 * case R.id.imp_exp_btn: // write your code here
	 * 
	 * return true;
	 * 
	 * case R.id.settings_btn:
	 * 
	 * //dark_bkg = false;
	 * 
	 * return true;
	 * 
	 * default: break;
	 * 
	 * } return true; }
	 * 
	 * public boolean onOptionsItemSelected(MenuItem item) { DatabaseHandler db;
	 * switch (item.getItemId()) {
	 * 
	 * case R.id.imp_exp_btn: // write your code here Log.d("Fragment2",
	 * "IMPOOOOORT");
	 * 
	 * 
	 * //super.onCreateView(mInflater, mContainer, mSavedInstanceState); return
	 * true;
	 * 
	 * case R.id.settings_btn:
	 * 
	 * Log.d("Fragment2", "IMPOOOOORT"); view.refreshDrawableState();
	 * 
	 * return true;
	 * 
	 * default: break;
	 * 
	 * } return super.onOptionsItemSelected(item); }
	 */

	public void not() {

		adapter.notifyDataSetChanged();
	}

	public void onResume() {
		super.onResume();
		Log.d("fr2", "onresume");
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		DatabaseHandler db;
		switch (item.getItemId()) {

		case R.id.settings_btn:

			settingsMenu();

			return true;

		case R.id.exp_btn:

			db = new DatabaseHandler(getActivity());
			db.exportDB();
			db.close();

			return true;
		case R.id.imp_btn:

			importMenu();
			return true;
		default:
			break;

		}
		return super.onOptionsItemSelected(item);
	}

	public void refreshCurrentFragment() {
		FragmentTab2 fragment = new FragmentTab2();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.fragment2_container, fragment);
		ft.commit();

	}

	public void settingsMenu() {
		Log.d("fr1", "import");

		if (MainActivity.dark_bkg == true) {
			MainActivity.dark_bkg = false;
			view.setBackgroundColor(Color.WHITE);
		} else {
			MainActivity.dark_bkg = true;
			view.setBackgroundColor(Color.BLACK);
		}

		adapter.notifyDataSetChanged();

	}

	public void importMenu() {
		DatabaseHandler db = new DatabaseHandler(getActivity());
		try {
			db.importDB("/sdcard/shoppingManager");
		} catch (IOException e) {
			e.printStackTrace();
		}
		db.close();
		MainActivity.fr1Imp = true;
		MainActivity.fr2Imp = true;
		MainActivity.fr3Imp = true;
		refreshCurrentFragment();

	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {

		MenuItem item = menu.findItem(R.id.imp_btn);
		MenuItem item2 = menu.findItem(R.id.exp_btn);
		MenuItem item3 = menu.findItem(R.id.last_10);
		MenuItem item4 = menu.findItem(R.id.last_20);
		MenuItem item5 = menu.findItem(R.id.all_items);
		MenuItem item6 = menu.findItem(R.id.order_daily);
		MenuItem item7 = menu.findItem(R.id.order_weekly);
		MenuItem item8 = menu.findItem(R.id.order_monthly);
		item.setVisible(false);
		item2.setVisible(false);
		item3.setVisible(false);
		item4.setVisible(false);
		item5.setVisible(false);
		item6.setVisible(false);
		item7.setVisible(false);
		item8.setVisible(false);
		super.onPrepareOptionsMenu(menu);
	}
}