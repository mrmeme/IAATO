package com.ensiie.iaato;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ensiie.iaato_data.Site;
import com.ensiie.iaato_data.SiteAdapter;
import com.ensiie.iaato_data.Step;
import com.ensiie.iaato_data.StepAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);

		String format = "yyyy-MM-dd";
		
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
		
		java.util.Date date = new java.util.Date();
		
		StepAdapter sa = new StepAdapter(this, R.layout.line_step);
		ListView lv = (ListView) findViewById(R.id.ListProd);
		
		SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);
		TextView tv_name = (TextView) this.findViewById(R.id.tv_name);
		tv_name.setText(preferences.getString("IAATO_user", ""));
	    
	    
		
		try{	 
		 	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		 	XmlPullParser parser = factory.newPullParser();
		 	
		 	preferences = getSharedPreferences("IAATO", MODE_PRIVATE);	
		 	String xml=preferences.getString("IAATO_steps", "");//<step><site>Arago Glacier</site><timeslot> 2013-05-31  : morning</timeslot></step>
		 	
		 	Log.e("DI", "info");
		 	parser.setInput(new StringReader(xml.replaceAll("&", "&amp;")));
		 	Log.e("DI", xml);//Toast.makeText(getApplicationContext(), "Reussite XML", Toast.LENGTH_LONG).show();
		 	String site="";
 			String time="";
 			boolean nouveau = true;
		
		 	Step s=new Step();
		
		 	
		 	while(parser.getEventType() != XmlPullParser.END_DOCUMENT)
		 	{
		 		if(parser.getEventType() == XmlPullParser.START_TAG)
		 		{
		 			Log.e("DI","start_tag"+parser.getName());
		 			
		 			if(parser.getName().equals("site")){
		 				parser.next();
		 				Log.e("DI","site_value"+parser.getText());
		 				site = parser.getText();
		 			}
		 			else if(parser.getName().equals("timeslot"))
		 			{
		 				parser.next();
		 				time = parser.getText();

		 				if(time.substring(1,11).equals(formater.format(date)))
		 				{
		 					sa.add(s);
		 					s.setSite(site);
			 				s.setTimeslot(parser.getText());
			 				
		 				}
		 			}
		 			else{}
		 		}
		 		
		 		if(parser.getEventType() == XmlPullParser.END_TAG){
		 			Log.e("DI","end_tag"+parser.getName());
		 		
			 						 			
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
		
		Button btn_logout = (Button) findViewById(R.id.btn_logout);
		Log.e("DI", "endStepAct" ); 
		btn_logout.setOnClickListener(logoutListener);
	}

	private OnClickListener logoutListener = new OnClickListener()
	  {
		   
		   @Override
		   public void onClick(View v) 
		   {
				   SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);
					SharedPreferences.Editor editor = preferences.edit();
				  
					editor.putString("IAATO_user","");
					editor.putString("IAATO_pass","");
					editor.putInt("IAATO_login",0);
					editor.commit();
					Intent intent = new Intent(InfoActivity.this, MainActivity.class);
					startActivity(intent);
		   }
	  };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}

}
