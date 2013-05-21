package com.ensiie.iaato;

import java.util.ArrayList;
import java.util.List;

import com.ensiie.iaato.R;
import com.ensiie.iaato_data.Site;
import com.ensiie.iaato_data.SiteAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class MyPagerAdapter extends PagerAdapter {
        
		@Override
		public int getCount() 
		{
            return 6;
        }
		
        @Override
		public Object instantiateItem(View collection, int position) {
            LayoutInflater inflater = (LayoutInflater) collection.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            int resId = 0;
            switch (position) {
            case 0:
                resId = R.layout.info;
                break;
            case 1:
                resId = R.layout.general;
                break;
            case 2:
                resId = R.layout.trajet;
                break;
            case 3:
                resId = R.layout.modif;
                break;
            case 4:
                resId = R.layout.annuaire_bat;
                break;
            case 5:
                resId = R.layout.liste_site;
               
                break;
            }
            View view = inflater.inflate(resId, null);
            /*ListView liste = (ListView) view.findViewById(R.id.ListProd);
            TextView tv = (TextView)view.findViewById(R.id.nom);
            tv.setText("premierproduit");
            liste.addView(tv);
            //View lsview = LayoutInflater.from(view.getContext()).inflate(R.layout.line,  (ViewGroup) view.getParent(), false);
            //lsview.
            //View v = view.inflate(context, resource, root)
            //Layout tv = (Layout) view.findViewById(R.layout.line);
            //liste.addView();
            /*List<Site> ls = new ArrayList<Site>();
            Site s = new Site();
            s.setName("hello");
            ls.add(s);
            SiteAdapter sa = new SiteAdapter(collection.getContext(),ls);
            liste.setAdapter(sa);
            sa.notifyDataSetChanged();*/
            //ListView liste = (ListView) view.findViewById(R.id.ListProd);
            //TextView tv = (TextView) view.findViewById(R.id.textview1);
    		//tv.setText("coucou");
            ((ViewPager) collection).addView(view, 0);
            return view;
        }
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
        }
        @Override
        public Parcelable saveState() {
            return null;
        }
}