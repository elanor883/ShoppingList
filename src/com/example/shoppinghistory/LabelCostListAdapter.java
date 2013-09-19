package com.example.shoppinghistory;

import java.util.ArrayList;
import java.util.HashMap;

 
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
 
public class LabelCostListAdapter extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;

 
    public LabelCostListAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
    }
 
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row_3, null);
 
        TextView title = (TextView)vi.findViewById(R.id.categoryName); // title
        TextView price = (TextView)vi.findViewById(R.id.textPrice); // title
        Button btn = (Button)vi.findViewById(R.id.categoryImg);
        //ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
 
        HashMap<String, String> mylist = new HashMap<String, String>();
        mylist = data.get(position);
 
        // Setting all values in listview
        title.setText(mylist.get(FragmentTab3b2.KEY_TITLE));
        price.setText(mylist.get(FragmentTab3b2.KEY_CORNER));
     //   btn.setText(mylist.get(FragmentTab2.KEY_T));
     //   Resources res = vi.getResources();
     //   Drawable drawable = res.getDrawable(R.drawable.btn1bg);
        btn.setBackgroundResource(Integer.parseInt((mylist.get(FragmentTab3b2.KEY_IMG))));
       // int id = vi.getResources().getIdentifier(mylist.get(FragmentTab2.KEY_COLOR), null, null);
    // Log.d("res id", ""+id);
        // imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
        return vi;
    }
}