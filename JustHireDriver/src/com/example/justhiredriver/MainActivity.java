package com.example.justhiredriver;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

    ListView list,list_head;
    
    ArrayList<HashMap<String, String>> mylist, mylist_title;
    
    ListAdapter adapter_title, adapter;
    HashMap<String, String> map1, map2;
    String[] names = { "Santhosh Naik","Pruthvi Raj","Vishnu Naik","Kiran Kumar","Rudresh C.S","Harish C.B","Shiv Kumar S.R","Chetan Nik",
    		            "Vinod","Akash Patel","Madan Nayar","Gururaj Shetty","Basavaraj","Andy","Praksh Raj"};
    
    String[] adress = {"#72,Baseveshara Nilaya,Narayanapura(v) shivamogga","#12 Swamy Nilay 2nd Main 3rd cross,Gopala ","#09 Tirupati Nilaya"
    		+ "Vodeyattur Honnali","#45 Villa 1st main 7th cross basavanagudi","Villa Diamond burk view street New york","#A120 Palm green apartme"
    		+"nt Bengaluru","#D45Q Quinton's Apartment New street London","#56 villa Mansion houses Kores's Road 3rd cross Biejing","#J899 Villa Diamond burk FirstDone view street New Town",
    		"5Q Quinton's Apartment New street LosAngles","#78 Venkataramana Home, 1st main 7th cross basava Nagar","#A120 Palm green apartment",
    		"#A120 GG gareen apart ","#D45Q Quinton's Apartment New street Washington DC","#56 villa Ocean street Mansion houses Kes's Road Malaysia "};
    
    String[] mobNumber={"  7259875193","  9945621874","  8095442963","  7895845210","  9964190220","  7312458266","  8857745621","  9635685550",
    		 "  9945621874","  8095442963","  7895845210","  9964190220","  7312458266","  8857745621","  9635685550"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /***get the id's****************/
        list_head = (ListView) findViewById(R.id.listView1);
        list = (ListView) findViewById(R.id.listView2);
     
        
        showActivity();

    }

    public void showActivity() {

        mylist = new ArrayList<HashMap<String, String>>();
        mylist_title = new ArrayList<HashMap<String, String>>();
        
        /**********Display the headings************/
        
        
        map1 = new HashMap<String, String>();

         map1.put("firstCol", "CustomerName");
         map1.put("secondCol", "Adress");
         map1.put("thirdCol", "Phone.No");
         mylist_title.add(map1);

        
        
        try {
            adapter_title = new SimpleAdapter(this, mylist_title, R.layout.row,
                    new String[] { "firstCol", "secondCol", "thirdCol" }, new int[] {
                            R.id.firstCol, R.id.secondCol, R.id.thirdCol });
            list_head.setAdapter(adapter_title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        /********************************************************/
        
        
        /**********Display the contents************/
        
        for (int i = 0; i < names.length; i++) {
            map2 = new HashMap<String, String>();

            map2.put("firstCol",names[i] );
            map2.put("secondCol",adress[i] );
            map2.put("thirdCol", mobNumber[i]);
            mylist.add(map2);
        }
        
        try {
            adapter = new SimpleAdapter(this, mylist, R.layout.row,
                    new String[] { "firstCol", "secondCol", "thirdCol" }, new int[] {
                            R.id.firstCol, R.id.secondCol, R.id.thirdCol });
            list.setAdapter(adapter);
        } catch (Exception e) {
            
        }
        
        /********************************************************/

    }

}
