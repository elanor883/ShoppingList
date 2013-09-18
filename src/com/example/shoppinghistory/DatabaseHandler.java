package com.example.shoppinghistory;

import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import android.database.Cursor;
import android.util.Log;


public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "shoppingManager";

	// Contacts table name
	private static final String TABLE_SHOPLIST = "shoplist";
	private static final String TABLE_CATEGORIES = "categories";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_TYPE_NAME = "type_name";
	private static final String KEY_PRICE = "price";
	private static final String KEY_DATE = "date";
	private static final String KEY_RESID = "resid";
	private static final String KEY_C_ID = "c_id";

	public DatabaseHandler(Context fragmentTab4) {
		super(fragmentTab4, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_SHOPLIST_TABLE = "CREATE TABLE " + TABLE_SHOPLIST + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TYPE_NAME + " TEXT,"
				+ KEY_PRICE + " INTEGER, " + KEY_DATE + " TEXT"+ ")";
		Log.d("Db", CREATE_SHOPLIST_TABLE);
		db.execSQL(CREATE_SHOPLIST_TABLE);
		
		String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
		+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TYPE_NAME + " TEXT, " + KEY_RESID +" TEXT, "+ KEY_C_ID + " INTEGER "+")";
		Log.d("Db", CREATE_CATEGORIES_TABLE);
		db.execSQL(CREATE_CATEGORIES_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPLIST);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
		// Create tables again
		Log.d("Db", "fos");
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addContact(ShopList contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TYPE_NAME, contact.getTypeName()); // Contact Name
		values.put(KEY_PRICE, contact.getPrice()); // Contact Phone
		values.put(KEY_DATE, contact.getDate()); // Contact Phone
		

		// Inserting Row
		db.insert(TABLE_SHOPLIST, null, values);
		db.close(); // Closing database connection
		Log.d("databasehandler", "Row added");
	}

	void addCategory(Categories cat) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TYPE_NAME, cat.getTypeName()); // Contact Name
		values.put(KEY_RESID, cat.getResid());
		values.put(KEY_C_ID, cat.getCId());
		// Inserting Row
		db.insert(TABLE_CATEGORIES, null, values);
		db.close(); // Closing database connection
		Log.d("databasehandler", "Row added - categories");
	}
	
	// Getting single contact
	ShopList getShop(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_SHOPLIST, new String[] { KEY_ID,
				KEY_TYPE_NAME, KEY_PRICE, KEY_DATE}, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		ShopList shopping = new ShopList(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), 
				Integer.parseInt(cursor.getString(2)),
				cursor.getString(3));
				
		// return contact
		return shopping;
	}
	
	// Getting All Contacts
	public List<ShopList> getAllShops() {
		List<ShopList> shoppinglistList = new ArrayList<ShopList>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SHOPLIST;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				ShopList shop_list = new ShopList();
				shop_list.setId(Integer.parseInt(cursor.getString(0)));
				shop_list.setTypeName(cursor.getString(1));
				shop_list.setPrice(Integer.parseInt(cursor.getString(2)));
				shop_list.setDate(cursor.getString(3));
				// Adding contact to list
				shoppinglistList.add(shop_list);
			} while (cursor.moveToNext());
		}

		cursor.close();
		// return contact list
		return shoppinglistList;
	}

	// Updating single contact
	public int updateContact(ShopList shopping) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TYPE_NAME, shopping.getTypeName());
		values.put(KEY_PRICE, shopping.getPrice());
		values.put(KEY_DATE, shopping.getDate());

		// updating row
		return db.update(TABLE_SHOPLIST, values, KEY_ID + " = ?",
				new String[] { String.valueOf(shopping.getId()) });
	}

	// Deleting single contact
	public void deleteContact(ShopList contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SHOPLIST, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getId()) });
		db.close();
	}


	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_SHOPLIST;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	
	public ArrayList<String> getCategories()
	{
		String selectQuery = "SELECT "+ KEY_TYPE_NAME + " FROM "  + TABLE_CATEGORIES;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		ArrayList<String> cat = new ArrayList<String>();
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				cat.add(cursor.getString(0));
				// Adding contact to list
			} while (cursor.moveToNext());
		}
		cursor.close();
		return cat;
	}
	
	public List<Categories> getFullCategories()
	{
		String selectQuery = "SELECT * FROM "  + TABLE_CATEGORIES;
		List<Categories> catList = new ArrayList<Categories>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Categories cat = new Categories();
				cat.setId(Integer.parseInt(cursor.getString(0)));
				cat.setTypeName(cursor.getString(1));
				cat.setResid(cursor.getString(2));
				cat.setCId(Integer.parseInt(cursor.getString(3)));
				// Adding contact to list
				catList.add(cat);
			} while (cursor.moveToNext());
		}

		cursor.close();
		return catList;
	}
	
	/*
	 * select mydate, sum(price) from table1
		group by mydate; 

	 * */
	
	
	public List<ShopList> getCostPerDayPerType()
	{
		String selectQuery = "select date, type_name, sum(price) from shoplist GROUP BY date, type_name";
		
		List<ShopList> shoppinglistList = new ArrayList<ShopList>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				ShopList shop_list = new ShopList();
				shop_list.setDate(cursor.getString(0));
				shop_list.setTypeName(cursor.getString(1));
				shop_list.setPrice(Integer.parseInt(cursor.getString(2)));
				
				// Adding contact to list
				shoppinglistList.add(shop_list);
			} while (cursor.moveToNext());
		}

		cursor.close();
		// return contact list
		return shoppinglistList;
	}
	 
	public List<ShopList> getSumCostPerDay()
	{
		String selectQuery = "select date, sum(price) from shoplist GROUP BY date";
		
		List<ShopList> shoppinglistList = new ArrayList<ShopList>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				ShopList shop_list = new ShopList();
				shop_list.setDate(cursor.getString(0));
				shop_list.setPrice(Integer.parseInt(cursor.getString(1)));
				
				// Adding contact to list
				shoppinglistList.add(shop_list);
			} while (cursor.moveToNext());
		}

		cursor.close();
		// return contact list
		return shoppinglistList;
	}
	
	public List<ShopList> getLastFewItems()
	{
		String selectQuery = "select date, price, type_name from shoplist order by date desc limit 5";
		
		List<ShopList> shoppinglistList = new ArrayList<ShopList>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				ShopList shop_list = new ShopList();
				shop_list.setDate(cursor.getString(0));
				shop_list.setPrice(Integer.parseInt(cursor.getString(1)));
				shop_list.setTypeName(cursor.getString(2));
				// Adding contact to list
				shoppinglistList.add(shop_list);
			} while (cursor.moveToNext());
		}

		cursor.close();
		// return contact list
		return shoppinglistList;
	}
	
}