package com.justhire.driver;

import java.util.ArrayList;

import com.justhire.driver.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListviewContactAdapter extends BaseAdapter{
private static ArrayList<ListviewContactItem> listContact;

private LayoutInflater mInflater;

public ListviewContactAdapter(Context fragment, ArrayList<ListviewContactItem> results){
    listContact = results;
    mInflater = LayoutInflater.from(fragment);
}

@Override
public int getCount() {
    // TODO Auto-generated method stub
    return listContact.size();
}

@Override
public Object getItem(int arg0) {
    // TODO Auto-generated method stub
    return listContact.get(arg0);
}

@Override
public long getItemId(int arg0) {
    // TODO Auto-generated method stub
    return arg0;
}

public View getView(int position, View convertView, ViewGroup parent) {
    // TODO Auto-generated method stub
    ViewHolder holder;
    if(convertView == null){
        convertView = mInflater.inflate(R.layout.fragment_today, null);
       // convertView = mInflater.inflate(R.layout.fragment_week, null);
        holder = new ViewHolder();
        holder.txtname = (TextView) convertView.findViewById(R.id.name);          
        holder.txtphone = (TextView) convertView.findViewById(R.id.mobile);
        holder.txtaddress = (TextView) convertView.findViewById(R.id.address);
        holder.txtdate = (TextView) convertView.findViewById(R.id.todayDate);

        convertView.setTag(holder);
    }
    else 
    {
        holder = (ViewHolder) convertView.getTag();
    }

    holder.txtname.setText(listContact.get(position).getName());
    holder.txtphone.setText(listContact.get(position).getPhone());
    holder.txtaddress.setText(listContact.get(position).getPickUp());
    holder.txtdate.setText(listContact.get(position).getDate());

    return convertView;
}

static class ViewHolder
{
    TextView txtname, txtphone,txtaddress,txtdate;
}
}