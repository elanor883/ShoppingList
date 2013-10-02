package com.example.shoppinghistory;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

@SuppressLint("NewApi")
public class MainActivity extends SherlockFragmentActivity {
	// Declare Tab Variable
	Tab tab;

	ActionBar mActionBar;
	ViewPager mPager;
	boolean default_theme;
	static boolean dark_bkg = false;
	static boolean imported = false;
	int activePage = 1;
	ShopListViewAdapter mAdapterList;
	static boolean fr1Imp = false;
	static boolean fr2Imp = false;
	static boolean fr3Imp = false;
	Bundle mSavedInstanceState;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_main);

		Log.d("MainActivity", "oncreate");
		// Activate Navigation Mode Tabs
		mActionBar = getSupportActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);

		default_theme = true;
		mSavedInstanceState = savedInstanceState;

		// Locate ViewPager in activity_main.xml
		mPager = (ViewPager) findViewById(R.id.pager);

		// Activate Fragment Manager
		FragmentManager fm = getSupportFragmentManager();

		// Capture ViewPager page swipes
		ViewPager.SimpleOnPageChangeListener ViewPagerListener = new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				super.onPageSelected(position);
				// Find the ViewPager Position
				mActionBar.setSelectedNavigationItem(position);
			}
		};

		mPager.setOnPageChangeListener(ViewPagerListener);

		// Locate the adapter class called ViewPagerAdapter.java
		ViewPagerAdapter viewpageradapter = new ViewPagerAdapter(fm);
		// Set the View Pager Adapter into ViewPager
		mPager.setAdapter(viewpageradapter);

		// Capture tab button clicks
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// Pass the position on tab click to ViewPager
				mPager.setCurrentItem(tab.getPosition());
				activePage = tab.getPosition();
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}

		};

		// Create first Tab
		tab = mActionBar.newTab().setText("Shopping")
				.setTabListener(tabListener);
		mActionBar.addTab(tab);

		// Create second Tab
		tab = mActionBar.newTab().setText("Categories").setTabListener(tabListener);
		mActionBar.addTab(tab);

		// Create third Tab
		tab = mActionBar.newTab().setText("Statistics")
				.setTabListener(tabListener);
		mActionBar.addTab(tab);

		/*tab = mActionBar.newTab().setText("Statistics")
				.setTabListener(tabListener);
		mActionBar.addTab(tab);*/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);

	}

	/*public boolean onOptionsItemSelected(MenuItem item) {
		DatabaseHandler db;
		switch (item.getItemId()) {

		case R.id.imp_exp_btn:
			// write your code here

			// db = new DatabaseHandler(this); db.exportDB(); db.close();

			Log.d("MainActivity", "import");

			// setTheme(R.style.Theme_Sherlock_Light_DarkActionBar);
			//refreshFragments();
			// recreate();
			// setContentView(R.layout.activity_main);
			dark_bkg = false;
//mAdapterList.notifyDataSetChanged();
			return true;

		default:
			break;

		}
		return super.onOptionsItemSelected(item);
	}
/*
	public void refreshFragments() {

		FragmentTab1 fragment1 = new FragmentTab1();
		FragmentTab2 fragment2 = new FragmentTab2();
		FragmentTab3b fragment3 = new FragmentTab3b();
		FragmentTab3b2 fragment32 = new FragmentTab3b2();
		FragmentTab4b fragment4 = new FragmentTab4b();

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		fragment3.setMenuVisibility(false);
		if (activePage == 0) {
			transaction.replace(R.id.fragment1_container, fragment1);

		}
		if (activePage == 1) {
			transaction.replace(R.id.fragment2_container, fragment2);

		}
		if (activePage == 2) {
			//transaction.replace(R.id.fragment32_container, fragment3);
			//fragment3.view.setBackgroundColor(Color.WHITE);
			//transaction.replace(R.id.fragment32_detail, fragment32);
			//transaction.hide(fragment32);
			// transaction.replace(R.id.fragment32_detail, fragment32);
			//fragment3.onResume();
			transaction.remove (fragment3);
			transaction.add (R.id.fragment32_container, fragment3);

		}
		if (activePage == 3) {
			transaction.replace(R.id.scrollView1, fragment4);

		}

		if (activePage == 4) {
			transaction.replace(R.id.fragment32_detail, fragment32);

		}
		Log.d("main", "" + activePage);
		/*
		 * else if (activePage == 2) {
		 * transaction.replace(R.id.fragment1_container, fragment1);
		 * transaction.replace(R.id.fragment2_container, fragment2); //
		 * transaction.replace(R.id.fragment3_container, fragment3); }
		 * 
		 * else if (activePage == 3) {
		 * 
		 * transaction.replace(R.id.fragment2_container, fragment2);
		 * transaction.replace(R.id.fragment3_container, fragment3);
		 * transaction.replace(R.id.fragment4_container, fragment4); }
		 * 
		 * else if (activePage == 4) {
		 * transaction.replace(R.id.fragment3_container, fragment3);
		 * transaction.replace(R.id.fragment4_container, fragment4); } //
		 * transaction.replace(R.id.fragment3_container, fragment3); //
		 * transaction.replace(R.id.fragment4_container, fragment4); //
		 * transaction.addToBackStack(null);
		 */

	//	transaction.commit();

	

}