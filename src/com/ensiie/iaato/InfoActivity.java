package com.ensiie.iaato;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ensiie.iaato_data.Site;
import com.ensiie.iaato_data.SiteAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

public class InfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		SiteAdapter sa = new SiteAdapter(this, R.layout.line);
		ListView lv = (ListView) findViewById(R.id.ListProd) ;
		try{	 
		 	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		 	XmlPullParser parser = factory.newPullParser();
		 	
		 	SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);	
		 	String xml=preferences.getString("IAATO_infos", "<user><ship><name>Alexander von Humboldt</name><code>C6WC2</code><nbpassenger>200</nbpassenger><society>Club Cruise Fleet & Technical Department</society><type>Cat. 2</type></ship><steps></steps></user>");
		 	
		 	Log.e("DI", "info");
		 	parser.setInput(new StringReader(xml.replaceAll("&", "&amp;")));
		 	Log.e("DI", xml);//Toast.makeText(getApplicationContext(), "Reussite XML", Toast.LENGTH_LONG).show();
		 	boolean errorTag = false;
		 	Site s =new Site();
		 	while(parser.getEventType() != XmlPullParser.END_DOCUMENT && !errorTag){
		 		if(parser.getEventType() == XmlPullParser.START_TAG){
		 			Log.e("DI","start_tag"+parser.getName());
		 			if(parser.getName().equals("site"))
		 				s=new Site();
		 			else if(parser.getName().equals("name")){
		 				parser.next();
		 				s.setName(parser.getText());
		 			}
		 			else if(parser.getName().equals("latitide")){
		 				parser.next();
		 				s.setLatitude(parser.getText());
		 			}
		 			else if(parser.getName().equals("longitude")){
		 				parser.next();
		 				s.setLongitude(parser.getText());
		 			}
		 			else if(parser.getName().equals("iaato")){
		 				parser.next();
		 				s.setIaato(parser.getText());
		 			}
		 			else if(parser.getName().equals("activities")){
		 				parser.next();
		 				ArrayList<String> activities = new ArrayList<String>();
		 				//s.setZone(parser.getText());
		 				//parser.next();
		 				while(parser.getEventType() == XmlPullParser.END_TAG && parser.getName().equals("activity")){
			 				parser.next();
			 				Log.e("DI", "activity = "+parser.getText());
			 				activities.add(parser.getText());
			 			}
		 				s.setActivity(activities);
		 			}
		 			else if(parser.getName().equals("zone")){
		 				parser.next();
		 				s.setZone(parser.getText());
		 				parser.next();
		 				if(parser.getName().equals("subzone")){		 				
			 				//parser.next();
			 				s.setSubzone(parser.getText());
			 			}
		 			}
		 		}
		 		if(parser.getEventType() == XmlPullParser.END_TAG){
		 			Log.e("DI","end_tag"+parser.getName());
		 			if(parser.getName().equals("site"))
		 				sa.add(s);
		 		}
		 		parser.next();
		 	}		
		}
		catch(Exception e){
			Log.e("DI", "erreur = "+e.getMessage());
			e.printStackTrace();
		}
		Site s = new Site();
		s.setName("coucou");
		sa.add(s);
		lv.setAdapter(sa);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}

}
