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
import android.widget.Toast;

public class ActiviteTab extends Activity {
  // La chaine de caracteres par defaut

	Button info;
	Button gene;
	Button trajet;
	Button modif;
	Button annuaire;
	Button liste;
	private Boolean res = false;
	protected ProgressDialog progress;
    final Handler progressHandler = new Handler(){
		
		public void handleMessage(Message msg){
			progress.dismiss();
			if(res){
				
    			Toast.makeText(getApplicationContext(), "Sync succeeded", Toast.LENGTH_LONG).show();
    		}
    		else{    			
    			Toast.makeText(getApplicationContext(), "Sync failed", Toast.LENGTH_LONG).show();
    		}
		}
	};

  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home);
    
    info = (Button) this.findViewById(R.id.btn_info);
    info.setOnClickListener(blistener);
    
    gene = (Button) this.findViewById(R.id.btn_gene);
    gene.setOnClickListener(blistener);
    
    trajet = (Button) this.findViewById(R.id.btn_trajet);
    trajet.setOnClickListener(blistener);
    
    modif = (Button) this.findViewById(R.id.btn_modif);
    modif.setOnClickListener(blistener);
    
    annuaire = (Button) this.findViewById(R.id.btn_annuaire);
    annuaire.setOnClickListener(blistener);
    
    liste = (Button) this.findViewById(R.id.btn_liste);
    liste.setOnClickListener(blistener);

  
  }
  

  private OnClickListener blistener = new OnClickListener()
  {
	   @Override
	   public void onClick(View v) 
	   {
		  if(v.getId()==R.id.btn_gene)
		   {
			  progress = ProgressDialog.show(ActiviteTab.this, null, "Sync in progress...", true);
			new Thread( new Runnable(){

				@Override
				public void run() {
					String xml = requestSymfony();
					Log.e("DI", ""+xml.length());
					Log.e("DI", xml);
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
						editor.commit();						
						res=true;
					}
					else
						res=false;
					
					progressHandler.sendMessage(progressHandler.obtainMessage());
        }
	}).start();
			   
		   }
		  if(v.getId()==R.id.btn_liste)
		   {
			
			  Intent intent = new Intent(ActiviteTab.this, SiteActivity.class);
				startActivity(intent);
		   }
		  else if(v.getId()==R.id.btn_modif)
		   {
			
			  Intent intent = new Intent(ActiviteTab.this, ModifActivity.class);
				startActivity(intent);
		   }
		  else if(v.getId()==R.id.btn_info)
		   {
			
			  Intent intent = new Intent(ActiviteTab.this, InfoActivity.class);
				startActivity(intent);
		   }
		  else if(v.getId()==R.id.btn_trajet)
		   {
			
			  Intent intent = new Intent(ActiviteTab.this, StepActivity.class);
				startActivity(intent);
		   }
		else  if(v.getId()==R.id.btn_annuaire)
		   {
			  Intent intent = new Intent(ActiviteTab.this, ShipActivity.class);
			  startActivity(intent);
		   } 
		   else
		   {
			/*Intent intent = new Intent(ActiviteTab.this, SwipeActivity.class);
			SwipeActivity.setView(v);			
			startActivity(intent);*/
			   //Intent intent = new Intent(ActiviteTab.this, SiteActivity.class);
				//startActivity(intent);
		   }
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
