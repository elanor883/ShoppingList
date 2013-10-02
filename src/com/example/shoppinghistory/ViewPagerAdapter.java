package com.example.shoppinghistory;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.actionbarsherlock.app.*;
 
public class ViewPagerAdapter extends FragmentPagerAdapter {
 
    // Declare the number of ViewPager pages
    final int PAGE_COUNT = 3;
 
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
 
        // Open FragmentTab1.java
        case 0:
            FragmentTab1 fragmenttab1 = new FragmentTab1();
            return fragmenttab1;
 
        // Open FragmentTab2.java
        case 1:
            FragmentTab2 fragmenttab2 = new FragmentTab2();
            return fragmenttab2;
 
        // Open FragmentTab3.java
        case 2:
            FragmentTab3b fragmenttab3 = new FragmentTab3b();
            return fragmenttab3;
            
            
        /*case 3:
            FragmentTab4b fragmenttab4 = new FragmentTab4b();
            return fragmenttab4;
        */
        case 3:
            FragmentTab3b2 fragmenttab32 = new FragmentTab3b2();
            return fragmenttab32;

        }
        return null;
    }
 
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return PAGE_COUNT;
    }

   
 
}