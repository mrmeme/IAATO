package com.ensiie.iaato;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import com.ensiie.iaato.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
 
public class MainActivity extends Activity {
  // La chaine de caracteres par defaut
 
	Button btn_connect;	
	EditText tv_user;
	EditText tv_passwd;
	
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.intro);
   
	SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);
    
     tv_user = (EditText) this.findViewById(R.id.tv_user);
     if(!preferences.getString("IAATO_user", "").equals(""))
    	 tv_user.setText(preferences.getString("IAATO_user", ""));
     
     tv_passwd = (EditText) this.findViewById(R.id.tv_passwd);
     if(!preferences.getString("IAATO_pass", "").equals(""))
    	 tv_passwd.setText(preferences.getString("IAATO_pass", ""));
    
   btn_connect = (Button) this.findViewById(R.id.btn_connect);
   btn_connect.setOnClickListener(connectListener);
   //Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
   /*Intent intent = new Intent(MainActivity.this, InfoActivity.class);
	startActivity(intent);*/
  
  }
  
  private OnClickListener connectListener = new OnClickListener()
  {
	   
	   @Override
	   public void onClick(View v) 
	   {
		   String user = tv_user.getText().toString();
		   String passwd = tv_passwd.getText().toString();
		   
		   if(!user.isEmpty() && !passwd.isEmpty()){
			   SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);
				SharedPreferences.Editor editor = preferences.edit();
			  
				editor.putString("IAATO_user",user);
				editor.putString("IAATO_pass",passwd);
				editor.commit();
				new Thread( new Runnable(){

					@Override
					public void run() {
						String xml = requestSymfony();
						Log.e("DI", ""+xml.length());
						//Log.e("DI", xml);
						if(xml.length()>66){
							Log.e("DI", "split");
							String[] token = xml.split("</error>|</ships>|</sites>");
							SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);
							SharedPreferences.Editor editor = preferences.edit();
						  
							editor.putString("IAATO_ships",token[1].concat("</ships>"));
							editor.putString("IAATO_sites",token[2].concat("</sites>"));
							editor.commit();						
							
						}
						else{
							//handler error toast MainActivity
						}
							
						/*String errorValue="";
						try{	 
						 	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
						 	XmlPullParser parser = factory.newPullParser();
						 	parser.setInput(new StringReader(xml));
						 	Log.e("DI", "xml ressu");//Toast.makeText(getApplicationContext(), "Reussite XML", Toast.LENGTH_LONG).show();
						 	boolean errorTag = false;
						 	
						 	while(parser.getEventType() != XmlPullParser.END_DOCUMENT && !errorTag){
						 		if(parser.getEventType() == XmlPullParser.START_TAG){
						 			if(parser.getName().equals("error")){
						 				Log.e("DI","enter entete");
						 				parser.next();
						 				Log.e("DI",""+parser.getText());
						 				if((parser.getText())==null)
						 					errorTag=true;
						 				else 
						 					errorValue=parser.getText();
						 				//Log.e("DI",parser.getText());
						 				//Toast.makeText(getApplicationContext(), parser.getText(), Toast.LENGTH_LONG).show();			 					
						 			}
						 			parser.next();
						 		}
						 		if(parser.getEventType() == XmlPullParser.END_TAG){
						 			Log.e("DI","end_tag"+parser.getName());
						 			if(parser.getName().equals("error")){	
						 				parser.next();
						 			}
						 			parser.next();
						 		}
						 		//parser.next();
						 		parser.next();
						 		//Log.e("DI", parser.getText());
						 	}						 	
						 	//Log.e("DI", "error "+parser.getText());
						 	//parser.next();
						}
						catch(Exception e){
							Log.e("DI", "erreur = "+e.getMessage());
							Log.e("DI", "erreur = "+e.getMessage());
							e.printStackTrace();
							//Toast.makeText(getApplicationContext(), "Erreur XML", Toast.LENGTH_LONG).show();
						}
						
						if(errorValue.isEmpty()){
						Log.e("DI", "before intent");
						Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
						startActivity(intent);
						Log.e("DI", "after intent");
						}*/
						
						
					}
				}).start();
				
				Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
				//Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
				startActivity(intent);
		   }
		   else{
			   Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
			   startActivity(intent);
		   }
			   //Toast.makeText(getApplicationContext(), "Please enter your username and password", Toast.LENGTH_LONG).show();
			
	   }
  };
 
  public String requestSymfony(){
  	String responseString = null;
  	try{
  			//THREADING POUR LE FUTUR
  			//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  			//StrictMode.setThreadPolicy(policy); 
  			
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(new HttpGet("http://10.0.2.2/Iaato/web/app_dev.php/rest/capitaine/pass"));
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
/*

}*/