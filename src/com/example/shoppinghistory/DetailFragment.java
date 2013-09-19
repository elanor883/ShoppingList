package com.example.shoppinghistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;


public class DetailFragment extends SherlockFragment {
	private int mIndex = 0;

	public static DetailFragment newinstance(int index) {
		DetailFragment df = new DetailFragment();

		Bundle args = new Bundle();
		args.putInt("index", index);

		df.setArguments(args);
		return df;
	}

	public static DetailFragment newInstance(Bundle bundle) {
		int index = bundle.getInt("index", 0);
		return newinstance(index);
	}

	@Override
	public void onCreate(Bundle myBundle) {
		super.onCreate(myBundle);
		mIndex = getArguments().getInt("index", 0);
	}

	public int getShownindex() {
		return mIndex;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedinstanceState) {
		if (container == null)
			return null;
		View v = inflater.inflate(R.layout.detailsfragment, container, false);
		TextView textTodo = (TextView) v.findViewById(R.id.textTodo);
		textTodo.setText("loszar");
		return v;
	}
}
