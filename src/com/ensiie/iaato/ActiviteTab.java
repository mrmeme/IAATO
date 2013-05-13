package com.ensiie.iaato;
import com.ensiie.iaato.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;

public class ActiviteTab extends Activity {
  // La cha�ne de caract�res par d�faut

	Button info;
	Button gene;
	Button trajet;
	Button modif;
	Button deco;
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home);
    
    info = (Button) this.findViewById(R.id.info);
    info.setOnClickListener(blistener);
    
    gene = (Button) this.findViewById(R.id.gene);
    gene.setOnClickListener(blistener);
    
    trajet = (Button) this.findViewById(R.id.trajet);
    trajet.setOnClickListener(blistener);
    
    modif = (Button) this.findViewById(R.id.modif);
    modif.setOnClickListener(blistener);
    
    deco = (Button) this.findViewById(R.id.deco);
    deco.setOnClickListener(blistener);
  
  }
  

  private OnClickListener blistener = new OnClickListener()
  {
	   @Override
	   public void onClick(View v) 
	   {
		   if(v.getId()==R.id.deco)
		   {
			   Intent intent = new Intent(ActiviteTab.this, MainActivity.class);
				startActivity(intent);
		   }
		   else
		   {
			Intent intent = new Intent(ActiviteTab.this, SwipeActivity.class);
			SwipeActivity.setView(v);
			startActivity(intent);
		   }
	   }
  };
  

}