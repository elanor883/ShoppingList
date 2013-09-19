package com.example.shoppinghistory;

import java.io.IOException;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;


public class MainActivity extends SherlockFragmentActivity {
	// Declare Tab Variable
	Tab tab;

	ActionBar mActionBar;
	ViewPager mPager;
	boolean default_theme;
	boolean dark_bkg = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		 
		setContentView(R.layout.activity_main);

		// Activate Navigation Mode Tabs
		mActionBar = getSupportActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		default_theme = true;
		
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
		tab = mActionBar.newTab().setText("Labels").setTabListener(tabListener);
		mActionBar.addTab(tab);

		// Create third Tab
		tab = mActionBar.newTab().setText("History")
				.setTabListener(tabListener);
		mActionBar.addTab(tab);

		tab = mActionBar.newTab().setText("Statistics")
				.setTabListener(tabListener);
		mActionBar.addTab(tab);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		DatabaseHandler db;
		switch (item.getItemId()) {

		case R.id.imp_exp_btn:
			// write your code here
			db = new DatabaseHandler(this);
			db.exportDB();
			db.close();
			
			return true;

		case R.id.settings_btn:
			
			//dark_bkg = false;
			db = new DatabaseHandler(this);
			try {
				db.importDB("/sdcard/shoppingManager");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.close();
			
			return true;

		default:
			break;

		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d("FOS", "msg");
		outState.putBoolean("defaultTheme", default_theme);
	}
	

}