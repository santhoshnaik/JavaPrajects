package info.androidhive.slidingmenu;


import java.util.ArrayList;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HomeFragment extends Fragment {
	
	public HomeFragment(){}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
	{		
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ArrayList<ListviewContactItem> listContact = GetlistContact();
        ListView lv = (ListView)rootView.findViewById(R.id.homeListView);
        lv.setAdapter(new ListviewContactAdapter(getActivity(), listContact));

        return rootView;
    }
	private ArrayList<ListviewContactItem> GetlistContact()
	{
	    ArrayList<ListviewContactItem> contactlist = new ArrayList<ListviewContactItem>();

	    ListviewContactItem contact = new ListviewContactItem();

	    contact.SetName("Santhosh Naik");
	    contact.SetPhone("72*******193");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("Prathap");
	    contact.SetPhone("89********56");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("shivu");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);


	    contact = new ListviewContactItem();
	    contact.SetName("giri");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("Manu");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("shivu");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("shivu");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("shivu");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("shivu");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("shivu");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("shivu");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("shivu");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("shivu");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);

	    contact = new ListviewContactItem();
	    contact.SetName("shivu");
	    contact.SetPhone("897*******56");
	    contactlist.add(contact);

	    return contactlist; 
   }   
}

