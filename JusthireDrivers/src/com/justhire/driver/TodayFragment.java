package com.justhire.driver;
 
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.justhire.driver.SingleContactActivity;
import com.justhire.common.RestWSHandler;
import com.justhire.driver.R;
import com.justhire.httpclient.HttpsClientUtils;
import com.justhire.httpclient.RESTWSTask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TodayFragment extends Fragment {
	
	//ArrayList<HashMap<String, String>> mylist;

	public static String CUSTOMERID = "customerId";
	public static	String DATE = "date";
	public static String STATUS = "status";
	public static String UNAME = "uname";
	public static String DATEFORMAT="DD-MM-YYYY";
	
	 private static final String TAG_NAME = "name";
	    private static final String TAG_EMAIL = "email";
	    private static final String TAG_ADDRESS = "address";
	    private static final String TAG_GENDER = "gender";
	    private static final String TAG_PHONE = "phone";
	    private static final String TAG_PHONE_MOBILE = "mobile";
	
	
	ListView listview = null;
	public TodayFragment(){}
	//TodayFragment todayFrag=new TodayFragment();
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{	
		View rootView = inflater.inflate(R.layout.fragment_today,container, false); 		    	
    	listview = (ListView)rootView.findViewById(R.id.todayListView);   	   	     	 
        return rootView;				      
    }
	 
	public void handleResponse(String response) {

		String ID = "id";
		String DATE = "date";
		String STATUS = "status";
		String NAME = "name";
		
		//ListView list = (ListView) findViewById(R.id.book_statuslist);

		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put(ID, "Book Id");
		map.put(DATE, "Date of Booking");
		map.put(STATUS, "Status");
		mylist.add(map);

		try {
			JSONArray jso = new JSONArray(response);
			for (int i = 0; i < jso.length(); i++) {
				JSONObject jo = jso.getJSONObject(i);
				map = new HashMap<String, String>();
				map.put(ID, jo.getString("id"));
				try {

					        //map.put(DATE,jo.getString(DATE));
					SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT, Locale.ENGLISH);
					sdf.format(new Date((jo.getLong("bookFromTs"))));
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				map.put(STATUS, jo.getString("status"));
				//System.out.println("response - booking data = " + map);
				mylist.add(map);

			}
                

		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

// This method is called after the parent Activity's onCreate() method has completed.
    // Accessing the view hierarchy of the parent activity must be done in the onActivityCreated.
    // At this point, it is safe to search for activity View objects by their ID, for example.
  //  @Override
	
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
 	Activity activity = getActivity();
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
    listview.setOnItemClickListener(new OnItemClickListener() 
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,int position, long id)
        {
            // getting values from selected ListItem
            String name = ((TextView) view.findViewById(R.id.name)) 
                    .getText().toString();
            String address = ((TextView) view.findViewById(R.id.address))
                    .getText().toString();
            String mobile = ((TextView) view.findViewById(R.id.mobile))
                    .getText().toString();
          //  String email = ((TextView) view.findViewById(R.id.email))
                  //  .getText().toString();
            
            
            // Starting single contact activity
            Intent in = new Intent(getActivity().getApplicationContext(),SingleContactActivity.class);
            in.putExtra(TAG_NAME, name);
            in.putExtra(TAG_ADDRESS, address);
            in.putExtra(TAG_PHONE_MOBILE, mobile);
//          in.putExtra(TAG_EMAIL, email);
            
            startActivity(in); 

        }
    }
    
    );
    RESTWSTask wst = new RESTWSTask(HttpsClientUtils.POST_TASK,asyncHandler,activity,"Getting booking status details...");
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
			
/*          String ID = "id";
			String DATE = "date";
			String STATUS = "status";
			String UNAME= "uName";
			String PICK_UP= "pickupPoint";
			//SimpleDateFormat sdf = new SimpleDateFormat("DATEFORMAT", Locale.ENGLISH);
			ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(ID, "Book Id");
			map.put(DATE, "Date of Booking");
			map.put(STATUS, "Status");
			map.put(UNAME, "uName");
			map.put(PICK_UP, "pickupPoint");
			mylist.add(map);
			//map.put(DATE,"10/10/2015"); 
			//map.put(DATE,jo.getString(DATE));												
			//contact.setDate("1/10/2015 13:45");
						 //map = new HashMap<String, String>();							  				    
					//map.put(ID, jo.getString("id"));
					 *  //map.put(STATUS, jo.getString("status"));									 
							//contact.setAddress(jo.getString("status"));	
*/
			ArrayList<ListviewContactItem> contactlist = new ArrayList<ListviewContactItem>();
			try {
				JSONArray jso = new JSONArray(response);
				for (int i = 0; i < jso.length(); i++) {
					JSONObject jo = jso.getJSONObject(i);
									
				    ListviewContactItem contact = new ListviewContactItem();
				    
				  
					try {						
						 contact.SetName("Santhosh Naik");
						contact.SetPhone("7259875193");	
						contact.setPick_up("# 14,1st main, vindya homes layout, e- city phase 2 .l/m TCS");
					 } catch (Exception e) {

						e.printStackTrace();
					}
					try{
						
						SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT, Locale.ENGLISH);
						sdf.format(new Date((jo.getLong("bookFromTs"))));
						contact.setDate(sdf.toString());
					}
					catch(Exception e){
						e.getMessage();
					}
				           					
							contactlist.add(contact);				
							listview.setAdapter(new ListviewContactAdapter(getActivity(),contactlist));			    						
			}

				} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}			
		}

		
		
		@Override
		public void resultFailed(Message msg) {
			// TODO Auto-generated method stub
			
		}
    };

	 
	}
	
	