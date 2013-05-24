package com.ensiie.iaato_data;

import java.util.ArrayList;

import com.ensiie.iaato.R;
import com.ensiie.iaato_data.Step;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//import com.android.drinkiit.R;

public class StepAdapter extends ArrayAdapter<Step>{

	public StepAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View result = convertView;
		
		if(convertView == null){
			result = LayoutInflater.from(getContext()).inflate(R.layout.line_step, parent, false);
		}
		
		final Step step = getItem(position);
		
		TextView header = (TextView) result.findViewById(R.id.tv_line_header);
		header.setText(step.getTimeslot());
		
		TextView desc1 = (TextView) result.findViewById(R.id.tv_line_desc1);
		desc1.setText("Site: "+ step.getSite());
		Log.e("DI", "endStepAdapter" );		
		return result;
		
	}
	
	public void updateData(){
		this.notifyDataSetChanged();
	}

}
