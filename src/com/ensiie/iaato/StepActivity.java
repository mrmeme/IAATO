package com.ensiie.iaato;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;


import com.ensiie.iaato_data.Step;
import com.ensiie.iaato_data.StepAdapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class StepActivity extends Activity {

	 AlertDialog.Builder adb;
	 StepAdapter sa;
	 View alertDialogView;
	 LayoutInflater factory_layout;
	 String url;
	  static Boolean res = false;
		protected ProgressDialog progress;
	    final Handler progressHandler = new Handler(){
			
			public void handleMessage(Message msg){
				progress.dismiss();
				if(res){
					
	    			Toast.makeText(getApplicationContext(), "Step deleted", Toast.LENGTH_LONG).show();
	    		}
	    		else{    			
	    			Toast.makeText(getApplicationContext(), "Step doesn't exist", Toast.LENGTH_LONG).show();
	    		}
			}
		};
	
   	     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trajet);
		
		sa = new StepAdapter(this, R.layout.line_step);
	
        factory_layout = LayoutInflater.from(this);
        
        adb = new AlertDialog.Builder(this);
 
        adb.setView(alertDialogView);
 
        adb.setTitle("Titre de notre boite de dialogue");
 
        adb.setIcon(android.R.drawable.ic_dialog_alert);
       
		ListView lv = (ListView) findViewById(R.id.ListProd) ;
		
		try{	 
		 	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		 	XmlPullParser parser = factory.newPullParser();
		 	
		 	SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);	
		 	String xml=preferences.getString("IAATO_steps", "");//<steps><step><site>Alcock Island</site><timeslot> 2013-05-25  : early morning</timeslot></step><step><site>Arago Glacier</site><timeslot> 2013-05-31  : morning</timeslot></step></steps><step><site>Arago Glacier</site><timeslot> 2013-05-31  : morning</timeslot></step>
		 	
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
		lv.setOnItemLongClickListener(longclicklistener);
		Log.e("DI", "endStepAct" );
	}

	private OnItemLongClickListener longclicklistener = new OnItemLongClickListener()
	  {
		   
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
			

			final Step s = (Step) arg0.getItemAtPosition(arg2);
			
			String res = s.getTimeslot().substring(15)+"/"+s.getTimeslot().substring(1, 11)+"/"+s.getSite();
			SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);
            
            
            url=preferences.getString("IAATO_user", "")+"/"+preferences.getString("IAATO_pass", "")+"/"+s.getTimeslot().substring(15)+"/"+s.getTimeslot().substring(1, 11)+"/"+s.getSite();
            url = url.replaceAll(" ", "%20");
			 alertDialogView = factory_layout.inflate(R.layout.activity_dialog, null);
			 
		        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            	sa.remove(s);
		            	
		            	progress = ProgressDialog.show(StepActivity.this, null, "Trying to delete the step...", true);
		                new Thread( new Runnable(){

							@Override
							public void run() {
								String xml = requestSymfony();
								Log.e("DI", ""+xml.length());
								Log.e("DI", xml);
								if(xml.length()==8)
									StepActivity.res=true;
								else
									StepActivity.res=false;
								
								progressHandler.sendMessage(progressHandler.obtainMessage());	
		            }
				}).start(); 
		            	
		            	
		          } });
		 
		        adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		          } });
			
			 if (alertDialogView.getParent() == null) {
				    adb.setView(alertDialogView);
				} else {
					alertDialogView = null; 
				    adb.setView(alertDialogView);
				}
			 adb.setTitle("This step will be deleted");
			 adb.show();
        	
			
			return false;
		}
	  };
	  
	 
	  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}
	
	public String requestSymfony(){
	  	String responseString = null;
	  	try{
	  			//THREADING POUR LE FUTUR
	  			//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	  			//StrictMode.setThreadPolicy(policy); 
	  			
				HttpClient httpclient = new DefaultHttpClient();
				Log.e("DI","http://10.0.2.2/Iaato/web/app_dev.php/r/step/"+url);
				HttpResponse response = httpclient.execute(new HttpGet("http://10.0.2.2/Iaato/web/app_dev.php/r/stepdel/"+url));
				StatusLine statusLine = response.getStatusLine();
			    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
			        ByteArrayOutputStream out = new ByteArrayOutputStream();
			        response.getEntity().writeTo(out);
			        out.close();
			        responseString = out.toString();
			        //..more logic
			    } else{
			        //Closes the connection.
			        response.getEntity().getContent().close();
			        throw new IOException(statusLine.getReasonPhrase());
			    }
		    }catch(Exception e){
		    	Log.e("DI", "Error in http connection " + e.toString());
		    }
	  	return responseString+"\n";
	  }



}
