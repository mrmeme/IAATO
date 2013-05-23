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


public class ShipAdapter extends ArrayAdapter<Ship>{

	public ShipAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View result = convertView;

		if(convertView == null){
			result = LayoutInflater.from(getContext()).inflate(R.layout.line_ship, parent, false);
		}

		final Ship ship = getItem(position);
		TextView header = (TextView) result.findViewById(R.id.tv_line_header);
		header.setText(ship.getName());

		TextView desc2 = (TextView) result.findViewById(R.id.tv_line_desc1);
		desc2.setText("Code: "+ship.getCode());

		TextView desc3 = (TextView) result.findViewById(R.id.tv_line_desc3);
		desc3.setText("¨Passager: "+ship.getPassager());

		TextView desc4 = (TextView) result.findViewById(R.id.tv_line_desc4);
		desc4.setText("Société: "+ship.getSociete());

		TextView desc5 = (TextView) result.findViewById(R.id.tv_line_desc5);
		desc5.setText("Type: "+ship.getType());

		/*TextView desc6 = (TextView) result.findViewById(R.id.tv_line_desc6);
		if(!ship.getActivity().isEmpty())
			desc6.setText("Activity: "+ship.getActivity().get(0));
		else
			desc6.setText("No activity");*/

		return result;

	}

	public void updateData(){
		this.notifyDataSetChanged();
	}

}
