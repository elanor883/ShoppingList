package com.example.shoppinghistory;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ArrayAdapter;
 
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.example.actionbartest.R;
 
public class FragmentTab4 extends SherlockFragment implements
        ActionBar.TabListener {
    private Fragment mFragment;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from fragment3.xml
        getActivity().setContentView(R.layout.main);
        DatabaseHandler db = new DatabaseHandler(getActivity());
        Log.d("tab4", "db created");
        
        
        Log.d("Insert: ", "Inserting anyad2 ..");
		db.addContact(new ShopList("Food", 21, "2013-07-07"));
		db.addContact(new ShopList("Party", 8, "2013-07-07" ));
		db.addContact(new ShopList("Travel", 10, "2013-07-07"));
		db.addContact(new ShopList("Accomodation", 215, "2013-07-07"));

		// Reading all contacts
		Log.d("Reading: ", "Reading all contacts..");

		Log.d("fos", "fooos");

		List<ShopList> shoplist = db.getAllShops();

		final ArrayList<String> mylist = new ArrayList<String>();

		for (ShopList cn : shoplist) {
			String log = "Category: "
					+ cn.getTypeName() + " ,Price: " + cn.getPrice();
			// Writing Contacts to log
			mylist.add(log);
			Log.d("Name: ", log);
			
		//	 setContentView(R.layout.main);
		        
	         ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),  
	    		             R.layout.list_item,  
	    		             mylist);  
	    		       
	        //setListAdapter(adapter);
    }
    }
 
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new FragmentTab4();
        // Attach fragment3.xml layout
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
    }
 
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        // Remove fragment3.xml layout
        ft.remove(mFragment);
    }
 
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
 
    }
 
}