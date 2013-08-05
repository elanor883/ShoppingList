package com.example.actionbartest;

 
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import com.actionbarsherlock.app.SherlockFragment;
    import com.actionbarsherlock.app.SherlockFragmentActivity;
     
    public class FragmentTab4b extends SherlockFragment {
     
        @Override
        public SherlockFragmentActivity getSherlockActivity() {
            return super.getSherlockActivity();
        }
     
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }
     
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Get the view from fragmenttab1.xml
            View view = inflater.inflate(R.layout.fragmenttab1, container, false);
            return view;
        }
     
        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            setUserVisibleHint(true);
        }
     
    
}