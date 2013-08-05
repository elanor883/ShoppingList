package com.example.actionbartest;

public class Categories {
     
    //private variables
    int _shop_id;
    String _type_name;
     
    // Empty constructor

    public Categories(){
        
    }
    // constructor
    public Categories(int shop_id, String type_name){
        this._shop_id = shop_id;
        this._type_name = type_name;

    }
     
    // constructor
    public Categories(String type_name){
        this._type_name = type_name;
    }
    // getting ID
    public int getId(){
        return this._shop_id;
    }
     
    // setting id
    public void setId(int shop_id){
        this._shop_id = shop_id;
    }
     
    // getting name
    public String getTypeName(){
        return this._type_name;
    }
     
    // setting name
    public void setTypeName(String type_name){
        this._type_name = type_name;
    }
     
  
}
