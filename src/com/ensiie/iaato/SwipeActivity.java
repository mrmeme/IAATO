package com.ensiie.iaato;

import com.ensiie.iaato.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

public class SwipeActivity extends Activity {
 
	static int view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.swipe);
	    MyPagerAdapter adapter = new MyPagerAdapter();
	    ViewPager myPager = (ViewPager) findViewById(R.id.myfivepanelpager);	    
	    myPager.setAdapter(adapter);
	    myPager.setCurrentItem(view);
	    

	}
	
	public static void setView(View v)
	{
		if(v.getId()==R.id.btn_info){
			view = 0;			
		}
		else if(v.getId()==R.id.btn_gene)
			view = 1;
		else if(v.getId()==R.id.btn_trajet)
			view = 2;
		else if(v.getId()==R.id.btn_modif)
			view = 3;
		else if(v.getId()==R.id.btn_annuaire)
			view = 4;
		else if(v.getId()==R.id.btn_liste)
			view = 5;
		
	}
	

}

