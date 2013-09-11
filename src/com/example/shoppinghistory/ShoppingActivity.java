package com.example.shoppinghistory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.actionbartest.R;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ListView;
import android.widget.TextView;

public class ShoppingActivity extends ListActivity {
	
	//ListView listshop = (ListView) findViewById(R.id.listView1);
	String[] menu;
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        // Get the view from fragmenttab3.xml
	        View view = inflater.inflate(R.layout.fragmenttab3, container, false);
	        return view;
	    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.list);

	        
		DatabaseHandler db = new DatabaseHandler(this);
		DatePicker dp =(DatePicker)findViewById(R.id.datePicker1);

		Calendar cal = Calendar.getInstance(); 
		
	//	dp.updateDate(year, month, day);
		dp.init(2012, 11, 1, new OnDateChangedListener() {
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			    Log.d("faszom", "szar");

			}
			});
		
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


		//	final StableArrayAdapter adapter = new StableArrayAdapter(this,
			//		android.R.layout.simple_list_item_1, list);
		//	listview.setAdapter(adapter);

		}
		

		
		 setContentView(R.layout.main);
	        
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  
    		             R.layout.list_item,  
    		             mylist);  
    		       
    		     setListAdapter(adapter);
    		     
    		     

	}
	
    @Override  
    public void onListItemClick(ListView parent, View v, int position, long id)  
    {  
    
      if(position == 0){
		//	Intent myIntent = new Intent(v.getContext(), Rajz2.class);
		//	startActivityForResult(myIntent, 0); 
    	  {
    		  Log.d("fostalicska", "megnyomva");
    		//  String date_picker_message= (dp.getMonth()+1) +"/"+dp.getDayOfMonth()+"/"+dp.getYear() ;
    		 }  
     	 
      }
      /*
      if(position == 1){
			Intent myIntent = new Intent(v.getContext(), Calc.class);
			startActivityForResult(myIntent, 0); 
       	 
        }
      
      if(position == 2){
			Intent myIntent = new Intent(v.getContext(), Puzzle.class);
			startActivityForResult(myIntent, 0); 
      	 
       }
    */
    }  
    
   
	
}