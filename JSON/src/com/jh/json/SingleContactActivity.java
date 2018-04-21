package com.jh.json;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jh.json.R;

public class SingleContactActivity  extends Activity {
	
	// JSON node keys
	private static final String TAG_NAME = "name";
	// private static final String TAG_EMAIL = "email";
//	private static final String TAG_PHONE_MOBILE = "mobile";
	private static final String TAG_ADDRESS = "address";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(TAG_NAME);
     //   String email = in.getStringExtra(TAG_EMAIL);// for now no email id
     //   String mobile = in.getStringExtra(TAG_PHONE_MOBILE);
        String address = in.getStringExtra(TAG_ADDRESS);
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblAddress = (TextView) findViewById(R.id.address_label);
       // TextView lblMobile = ( TextView) findViewById(R.id.mobile_label);
       
        
        lblName.setText(name);
        lblAddress.setText(address);
        //lblMobile.setText(mobile);
    }
}
