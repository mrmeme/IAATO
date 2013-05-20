package com.ensiie.iaato_data;

import java.util.ArrayList;

import com.ensiie.iaato.R;
import com.ensiie.iaato_data.Site;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//import com.android.drinkiit.R;

public class ShipAdapter extends ArrayAdapter<Site>{

	public ShipAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View result = convertView;
		
		if(convertView == null){
			result = LayoutInflater.from(getContext()).inflate(R.layout.line, parent, false);
		}
		
		final Site site = getItem(position);
		TextView header = (TextView) result.findViewById(R.id.tv_line_header);
		header.setText(site.getName());
		
		TextView desc1 = (TextView) result.findViewById(R.id.tv_line_desc2);
		desc1.setText("Latitude: "+ site.getLatitude());
		
		TextView desc2 = (TextView) result.findViewById(R.id.tv_line_desc1);
		desc2.setText("Longitude: "+site.getLongitude());
		
		TextView desc3 = (TextView) result.findViewById(R.id.tv_line_desc3);
		desc3.setText("Zone: "+site.getZone());
		
		TextView desc4 = (TextView) result.findViewById(R.id.tv_line_desc4);
		desc4.setText("Subzone: "+site.getSubzone());
		
		TextView desc5 = (TextView) result.findViewById(R.id.tv_line_desc5);
		desc5.setText("Iaato: "+site.getIaato());
		
		TextView desc6 = (TextView) result.findViewById(R.id.tv_line_desc6);
		if(!site.getActivity().isEmpty())
			desc6.setText("Activity: "+site.getActivity().get(0));
		else
			desc6.setText("No activity");
		
		return result;
		
	}
	
	public void updateData(){
		this.notifyDataSetChanged();
	}

}
