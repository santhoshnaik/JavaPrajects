package com.justhire.driver;
 

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.justhire.common.GPSLocationFinder;
import com.justhire.common.RestWSHandler;
import com.justhire.driver.R;
import com.justhire.driver.TodayFragment;
import com.justhire.driver.WaitListFragment;
import com.justhire.driver.adapter.NavDrawerListAdapter;
import com.justhire.driver.model.NavDrawerItem;
import com.justhire.httpclient.HttpsClientUtils;
import com.justhire.httpclient.RESTWSTask;
import com.justhire.mobile.JustHireActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


@SuppressWarnings("deprecation")

public  class MainActivity extends Activity 
{
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	
	// navigation drawer title
	private CharSequence mDrawerTitle;

	// used to store application title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	
	
	
	
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// navigation  drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding navigation drawer items to array
		
		// To day
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// wait list
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		
				
		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the navigation drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.ic_drawer,R.string.app_name,R.string.app_name )
		{
			public void onDrawerClosed(View view)
			{
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// toggle navigation drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) 
		{
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new TodayFragment();
			break;
						
		case 1:
			fragment = new WaitListFragment();
			break;
		
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}	

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	TodayFragment activeFragement;
	public void handleResponse(String response) {
		//this.activeFragement.handleResponse(response);				
	}

		public void callServices(Fragment activeFragment)
		{
		this.activeFragement=(TodayFragment)activeFragment;
	         JSONObject bookJSON = new JSONObject();
	        String un ="santhosh";                              //this.getIntent().getExtras().getString("un");
	        String pd = "santhoshJH**01";                      //this.getIntent().getExtras().getString("pd");
	        String id = "318";                                //this.getIntent().getExtras().getString("cid");
	        try {
	            bookJSON.put("uName", un);
	            bookJSON.put("passTkn", pd);
	            bookJSON.put("customerId", id);

	        } catch (JSONException e) {
	            e.printStackTrace();
	        }

	        RESTWSTask wst = new RESTWSTask(HttpsClientUtils.POST_TASK,asyncHandler,this,"Getting booking status details...");
	        wst.addJSONObject(bookJSON);
	        // the passed String is the URL we will POST to
	        wst.execute(new String[] { "https://jh-justhireapp.rhcloud.com/hireme/service/booking/mobile/bookingstatus" });
	       
		}
		
		RestWSHandler asyncHandler = new RestWSHandler()
	    {
	       
			@Override
			public void resultSuccess(Message msg) {
				// TODO Auto-generated method stub
				String response = (String) msg.obj;
				String ID = "id";
				String DATE = "date";
				String STATUS = "status";
				//SimpleDateFormat sdf = new SimpleDateFormat("DATEFORMAT", Locale.ENGLISH);

				//ListView list = (ListView) findViewById(R.id.book_statuslist);

				ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

				HashMap<String, String> map = new HashMap<String, String>();
				map.put(ID, "Book Id");
				map.put(DATE, "Date of Booking");
				map.put(STATUS, "Status");
				mylist.add(map);
				ArrayList<ListviewContactItem> contactlist = new ArrayList<ListviewContactItem>();
				try {
					JSONArray jso = new JSONArray(response);
					for (int i = 0; i < jso.length(); i++) {
						JSONObject jo = jso.getJSONObject(i);
						map = new HashMap<String, String>();
						

					    ListviewContactItem contact = new ListviewContactItem();
					    contact.SetName(jo.getString("id"));
						map.put(ID, jo.getString("id"));
						try {
							//map.put(DATE,"10/10/2015");
							map.put(DATE,jo.getString(DATE));
							contact.SetPhone(jo.getString(DATE));
									//sdf.format(new Date((jo.getLong("bookFromTs")))));
						} catch (Exception e) {

							e.printStackTrace();
						}
						map.put(STATUS, jo.getString("status"));
						
										 
								//contact.setStatus(jo.getString("status"));
								
								contactlist.add(contact);
						
						
					}

					} catch (JSONException e) {

						e.printStackTrace();
					}				
			}

			@Override
			public void resultFailed(Message msg) {
				// TODO Auto-generated method stub
				
			}
	    };
		
		
	 
}


 

