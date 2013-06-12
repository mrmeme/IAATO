package com.ensiie.iaato;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import com.ensiie.iaato_data.Site;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ModifActivity extends Activity {
	private Spinner s;
	private Spinner timeslot;
	private DatePicker dp;
	private String url;
	private Boolean res = false;
	protected ProgressDialog progress;
    final Handler progressHandler = new Handler(){
		
		public void handleMessage(Message msg){
			progress.dismiss();
			if(res){
				
    			Toast.makeText(getApplicationContext(), "Step added to your journey", Toast.LENGTH_LONG).show();
    		}
    		else{    			
    			Toast.makeText(getApplicationContext(), "Already taken", Toast.LENGTH_LONG).show();
    		}
		}
	};

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
            	//requestSymfony();$username,$password,$tsl,$date,$site
            	SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);
                
               
                url=url+preferences.getString("IAATO_user", "")+"/"+preferences.getString("IAATO_pass", "")+"/"+timeslot.getSelectedItem()+"/"+dp.getYear()+"-"+(dp.getMonth()+1)+"-"+dp.getDayOfMonth()+"/"+s.getSelectedItem();
                url = url.replaceAll(" ", "%20");
                Log.e("DI", url);
                progress = ProgressDialog.show(ModifActivity.this, null, "Trying to book the step...", true);
                new Thread( new Runnable(){

					@Override
					public void run() {
						String xml = requestSymfony();
						Log.e("DI", ""+xml.length());
						Log.e("DI", xml);
						if(xml.length()==3)
							res=true;
						else
							res=false;
						
						progressHandler.sendMessage(progressHandler.obtainMessage());	
            }
		}).start(); 
            	//Toast.makeText(getApplicationContext(),url, Toast.LENGTH_LONG).show();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modif, menu);
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
				HttpResponse response = httpclient.execute(new HttpGet("http://10.0.2.2/Iaato/web/app_dev.php/r/step/"+url));
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
