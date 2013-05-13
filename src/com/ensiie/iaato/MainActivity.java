package com.ensiie.iaato;

import com.ensiie.iaato.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
				
				Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
				startActivity(intent);
		   }
		   else
			   Toast.makeText(getApplicationContext(), "Please enter your username and password", Toast.LENGTH_LONG).show();
			
	   }
  };
 
}
/*

}*/