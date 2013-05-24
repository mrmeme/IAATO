package com.ensiie.iaato;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;


import com.ensiie.iaato_data.Step;
import com.ensiie.iaato_data.StepAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

public class StepActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trajet);
		
		StepAdapter sa = new StepAdapter(this, R.layout.line_step);
		ListView lv = (ListView) findViewById(R.id.ListProd) ;
		try{	 
		 	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		 	XmlPullParser parser = factory.newPullParser();
		 	
		 	SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);	
		 	String xml=preferences.getString("IAATO_step1111s", "<steps><step><site>Alcock Island</site><timeslot> 2013-05-25  : early morning</timeslot></step><step><site>Arago Glacier</site><timeslot> 2013-05-31  : morning</timeslot></step></steps>");//<step><site>Arago Glacier</site><timeslot> 2013-05-31  : morning</timeslot></step>
		 	
		 	Log.e("DI", "info");
		 	parser.setInput(new StringReader(xml.replaceAll("&", "&amp;")));
		 	Log.e("DI", xml);//Toast.makeText(getApplicationContext(), "Reussite XML", Toast.LENGTH_LONG).show();
		 	
		 	Step s =new Step();
		 	while(parser.getEventType() != XmlPullParser.END_DOCUMENT){
		 		if(parser.getEventType() == XmlPullParser.START_TAG){
		 			Log.e("DI","start_tag"+parser.getName());
		 			if(parser.getName().equals("step"))
		 				s=new Step();
		 			else if(parser.getName().equals("site")){
		 				parser.next();
		 				Log.e("DI","site_value"+parser.getText());
		 				s.setSite(parser.getText());
		 			}
		 			else if(parser.getName().equals("timeslot")){
		 				parser.next();
		 				s.setTimeslot(parser.getText());
		 			}
		 		}
		 		if(parser.getEventType() == XmlPullParser.END_TAG){
		 			Log.e("DI","end_tag"+parser.getName());
		 			if(parser.getName().equals("step"))
		 				sa.add(s);
		 		}
		 		parser.next();
		 	}		
		}
		catch(Exception e){
			Log.e("DI", "erreur = "+e.getMessage());
			e.printStackTrace();
		}
		
		lv.setAdapter(sa);
		Log.e("DI", "endStepAct" );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}

}
