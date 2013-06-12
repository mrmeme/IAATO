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
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class MainActivity extends Activity {
  // La chaine de caracteres par defaut
 
	Button btn_connect;	
	EditText tv_user;
	EditText tv_passwd;
	Boolean res;
	protected ProgressDialog progress;
    final Handler progressHandler = new Handler(){
		
		public void handleMessage(Message msg){
			progress.dismiss();
			if(res){
				Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
				startActivity(intent);
    			Toast.makeText(getApplicationContext(), "Authentication succeeded", Toast.LENGTH_LONG).show();
    		}
    		else{    			
    			Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_LONG).show();
    		}
		}
	};
	
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.intro);
   
	SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);
    
	if(preferences.getInt("IAATO_login", 0)==1){
	Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
	startActivity(intent);
	}
	
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
				progress = ProgressDialog.show(MainActivity.this, null, "Authentication in progress...", true);
				new Thread( new Runnable(){

					@Override
					public void run() {
						String xml = requestSymfony();
						Log.e("DI", ""+xml.length());
						//Log.e("DI", xml);
						if(xml.length()>66){
							
							Log.e("DI", "split");
							String[] token = xml.split("</error>|</ships>|</sites>|</user>|</steps>");
							SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);
							SharedPreferences.Editor editor = preferences.edit();
						  
							editor.putString("IAATO_ships",token[1].concat("</ships>"));
							editor.putString("IAATO_sites",token[2].concat("</sites>"));
							editor.putString("IAATO_infos",token[3].concat("</user>"));
							editor.putString("IAATO_steps",token[4].concat("</steps>"));
							editor.putInt("IAATO_login", 1);
							editor.commit();	
							res=true;
							
						}
						else{
							res = false;
						}
						progressHandler.sendMessage(progressHandler.obtainMessage());	
						
						
						
					}
				}).start();
				
				/*Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
				//Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
				startActivity(intent);*/
		   }
		   /*else{
			   res = false;
			   /*Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
			   startActivity(intent);
		   }*/
		   
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
			HttpResponse response = httpclient.execute(new HttpGet("http://10.0.2.2/Iaato/web/app_dev.php/rest/"+tv_user.getText().toString()+"/"+tv_passwd.getText().toString()));
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