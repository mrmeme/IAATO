package com.example.iaato;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
 
public class MainActivity extends Activity {
  // La chaîne de caractères par défaut
 
	Button b;	
	EditText user;
	EditText passwd;
	
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.intro);
   
	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    
     user = (EditText) this.findViewById(R.id.user);
     if(!preferences.getString("IAATO_user", "").equals(""))
    	 user.setText(preferences.getString("IAATO_user", ""));
     
     passwd = (EditText) this.findViewById(R.id.passwd);
     if(!preferences.getString("IAATO_pass", "").equals(""))
    	 passwd.setText(preferences.getString("IAATO_pass", ""));
    
   b = (Button) this.findViewById(R.id.connect);
   b.setOnClickListener(connectListener);
    
  
  }
  
  private OnClickListener connectListener = new OnClickListener()
  {
	   
	   @Override
	   public void onClick(View v) 
	   {
		   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
			SharedPreferences.Editor editor = preferences.edit();
		  
			editor.putString("IAATO_user",user.getText().toString());
			editor.commit();
			
			editor.putString("IAATO_pass",passwd.getText().toString());
			editor.commit();
			
			Intent intent = new Intent(MainActivity.this, ActiviteTab.class);
			startActivity(intent);
			
	   }
  };
 
}
/*

}*/