package com.example.iaato;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

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
		if(v.getId()==R.id.info)
			view = 0;
		else if(v.getId()==R.id.gene)
			view = 1;
		else if(v.getId()==R.id.trajet)
			view = 2;
		else if(v.getId()==R.id.modif)
			view = 3;
		
	}
	

}

