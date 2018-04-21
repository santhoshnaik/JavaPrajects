package com.justhire.driver;
 
import com.justhire.driver.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class WaitListFragment extends Fragment {
     
    public WaitListFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_waitlist, container, false);
          
        return rootView;
    }
}