package com.ensiie.iaato;

import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ensiie.iaato_data.Site;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

public class ModifActivity extends Activity {
	private Spinner s;
	private Spinner timeslot;
	private DatePicker dp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modif);
		s = (Spinner) findViewById(R.id.spinner1);
		timeslot = (Spinner) findViewById(R.id.spn_timeslot);
		dp = (DatePicker) findViewById(R.id.dpResult);
	    ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
	    /*adapter.add("plop");
	    adapter.add("coucou");*/
	    try{	 
		 	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		 	XmlPullParser parser = factory.newPullParser();
		 	
		 	SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);	
		 	String xml=preferences.getString("IAATO_sitesltd", "<site>Aitcho Islands</site><site>Alcock Island</site>");
		 	
		 	Log.e("DI", "info");
		 	parser.setInput(new StringReader(xml.replaceAll("&", "&amp;")));
		 	Log.e("DI", xml);//Toast.makeText(getApplicationContext(), "Reussite XML", Toast.LENGTH_LONG).show();
		 	boolean errorTag = false;
		 	String site = "";
		 	while(parser.getEventType() != XmlPullParser.END_DOCUMENT && !errorTag){
		 		if(parser.getEventType() == XmlPullParser.START_TAG){
		 			Log.e("DI","start_tag"+parser.getName());
		 			
		 			
		 			if(parser.getName().equals("site")){
		 				parser.next();
		 				Log.e("DI","start_tag"+parser.getText());
		 				Log.e("DI","entertag"+site);
		 				site =parser.getText();
		 				adapter.add(site);
		 				
		 			}		 			
		 		}		 		
		 		parser.next();
		 	}		
		}
		catch(Exception e){
			Log.e("DI", "erreur = "+e.getMessage());
			e.printStackTrace();
		}
	    
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    s.setAdapter(adapter);
	    
	    Button btn_add = (Button) findViewById(R.id.btn_add);
	    btn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Toast.makeText(getApplicationContext(),"date" +" spin:" +s.getSelectedItem(), Toast.LENGTH_LONG).show();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modif, menu);
		return true;
	}

}
