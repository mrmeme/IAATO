package com.ensiie.iaato;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ensiie.iaato_data.Ship;
import com.ensiie.iaato_data.ShipAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

public class ShipActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.annuaire_bat);

		ShipAdapter sa = new ShipAdapter(this, R.layout.line_ship);
		ListView lv = (ListView) findViewById(R.id.ListProd) ;
		try{	 
		 	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		 	XmlPullParser parser = factory.newPullParser();

		 	SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);	
		 	String xml=preferences.getString("IAATO_ship", "<ships><ship><name>Alexander von Humboldt</name><code>C6WC2</code><nbpassenger>200</nbpassenger><society>Club Cruise Fleet & Technical Department</society><type>Cat. 2</type><emails><email0>alexandervonhumboldt@shore2ship.net  (while in Antarctic) Otherwise: avhmaster@mvalexandervonhumboldt.com   avhchiefpurser@mvalexandervonhumboldt.com</email0></emails><phones><phone0>881 631 833 919</phone0></phones></ship><ship><name>Akademik Ioffe</name><code>UAUN</code><nbpassenger>201</nbpassenger><society>Quark Expeditions</society><type>Cat. 1</type><emails><email0>Crew600372345@marsatmail.com (Master)  iofhm@quarkatsea.com (Hotel Manager)  iofel@quarkatsea.com (Expedition Leader) </email0></emails><phones><phone0>870 763477113 (</phone0></phones></ship><ship><name>Akademik Sergey Vavilov</name><code>UAUO</code><nbpassenger>202</nbpassenger><society>Quark Expeditions</society><type>Cat. 1</type><emails><email0>Crew600372349@marsatmail.com (Master)  vavhm@quarkatsea.com (Hotel Manager)  vavel@quarkatsea.com (Expedition Leader)</email0></emails><phones><phone0>870 763477115 (</phone0></phones></ship><ship><name>Akademik Shokalskiy</name><code>UBNF</code><nbpassenger>203</nbpassenger><society>Quark Expeditions</society><type>Cat. 1</type><emails><email0>shomaster@quarkatsea.com (Master)   shohm@quarkatsea.com (Hotel Manager)   shoel@quarkatsea.com (Expedition Leader) </email0></emails><phones><phone0>870 773 180 053</phone0></phones></ship><ship><name>Aleksey Maryshev</name><code>UCRD</code><nbpassenger>204</nbpassenger><society>Oceanwide Expeditions</society><type>Cat. 1</type><emails><email0>amaryshev@skyfile.com</email0></emails><phones><phone0>881 621 443 522</phone0></phones></ship><ship><name>Andrea</name><code>ELZV7</code><nbpassenger>205</nbpassenger><society>Elegant Cruises and Tours</society><type>Cat. 1</type><emails><email0>master@msandrea.net</email0></emails><phones><phone0>Bridge +47 514 </phone0></phones></ship><ship><name>Antarctic Dream</name><code>HPSF</code><nbpassenger>206</nbpassenger><society>Antarctic Shipping S.A.</society><type>Cat. 1</type><emails><email0>master.antarctic@navalradio.cl</email0></emails><phones><phone0>8747646757</phone0></phones></ship><ship><name>Bark Europa</name><code>PDZS</code><nbpassenger>207</nbpassenger><society>Rederij Bark EUROPA B.V.</society><type>Cat. 1</type><emails><email0>europa@gmn-usa.com</email0></emails><phones><phone0>+88-163-182 96 </phone0></phones></ship><ship><name>Bremen</name><code>C6JC3</code><nbpassenger>208</nbpassenger><society>Hapag-Lloyd Kreuzfahrten</society><type>Cat. 1</type><emails><email0>captain@ms-bremen.com</email0></emails><phones><phone0>330842910</phone0></phones></ship><ship><name>Clipper Adventurer</name><code>C6PG6</code><nbpassenger>209</nbpassenger><society>Quark Expeditions</society><type>Cat. 1</type><emails><email0>bridge@mvclipperadventurer.com (Bridge)  captain@mvclipperadventurer.com (Master) cpahm@quarkatsea.com (Hotel Manager) cpael@quarkatsea.com (Expedition Leader)</email0></emails><phones><phone0>12033462191   1</phone0></phones></ship><ship><name>Corinthian II</name><code>9HCO8</code><nbpassenger>210</nbpassenger><society>Travel Dynamics International</society><type>Cat. 1</type><emails><email0>captain@corinthianii.com, purser@corinthianii.com, cruise@corinthianii.com, cheng@corinthianii.com</email0></emails><phones><phone0>871621583610 or</phone0></phones></ship><ship><name>Delphin</name><code>C6VV2</code><nbpassenger>211</nbpassenger><society>Hansa Kreuzfahrten GmbH</society><type>Cat. 2</type><emails><email0>msdelphin@delphin-cruises.com</email0></emails><phones><phone0>4 751 407 395</phone0></phones></ship><ship><name>Discovery</name><code>ZCDG2</code><nbpassenger>212</nbpassenger><society>Voyages of Discovery</society><type>Cat. 2</type><emails><email0>master@mvdiscovery.com</email0></emails><phones><phone0> +47 51407488 o</phone0></phones></ship><ship><name>Fram </name><code>LADA-7</code><nbpassenger>213</nbpassenger><society>Hurtigruten ASA</society><type>Cat. 2</type><emails><email0>master.fr@hurtigruten.com / expedition.leader.fr@hurtigruten.com</email0></emails><phones><phone0>+47 51 40 64 40</phone0></phones></ship><ship><name>Grigoriy Mikheev</name><code>UCRE</code><nbpassenger>214</nbpassenger><society>Oceanwide Expeditions</society><type>Cat. 1</type><emails><email0>gmikheev@skyfile,com</email0></emails><phones><phone0>881 631 414 262</phone0></phones></ship><ship><name>Hanseatic</name><code>C6KA9</code><nbpassenger>215</nbpassenger><society>Hapag-Lloyd Kreuzfahrten</society><type>Cat. 1</type><emails><email0>captain@ms-hanseatic.com</email0></emails><phones><phone0>763827379</phone0></phones></ship><ship><name>Hanse Explorer</name><code>V2PD</code><nbpassenger>216</nbpassenger><society>Oceanstar</society><type>Motor Yacht</type><emails><email0>hans-sky@skyfile.com</email0></emails><phones><phone0>870764640262 an</phone0></phones></ship><ship><name>Kapitan Khlebnikov</name><code>UGSE</code><nbpassenger>217</nbpassenger><society>Quark Expeditions</society><type>Cat. 1</type><emails><email0>klbel@quarkatsea.com (Master) klbhm@quarkatsea.com (Hotel Manager)  klbel@quarkatsea.com (Expedition Leader)</email0></emails><phones><phone0>+ 870 76 111 80</phone0></phones></ship><ship><name>MY Laurel</name><code>ZCOV6</code><nbpassenger>218</nbpassenger><society>High Latitudes</society><type>Motor Yacht</type><emails><email0>captain@mylaurel.net  /  chiefofficer@mylaurel.net </email0></emails><phones><phone0>Sat B: 871 764 </phone0></phones></ship><ship><name>MY Itasca</name><code>ZGHI</code><nbpassenger>219</nbpassenger><society>High Latitudes</society><type>Motor Yacht</type><emails><email0>Dale@myitasca.com</email0></emails><phones><phone0>Sat-B voice: (+</phone0></phones></ship><ship><name>Le Diamant</name><code>FMAQ86</code><nbpassenger>220</nbpassenger><society>Compagnie Des Iles Du Ponant</society><type>Cat. 1</type><emails><email0>cdt.lediamant@skyfile.com - Password : FMAQ86</email0></emails><phones><phone0>00 870 763 971 </phone0></phones></ship><ship><name>Lyubov Orlova</name><code>9HDU5</code><nbpassenger>221</nbpassenger><society>Quark Expeditions</society><type>Cat. 1</type><emails><email0>lyubovmm@skyfile.com (Master)  orlhm@quarkatsea.com (Hotel Manager)  orlhma@quarkatsea.com (Asst. Hotel Manager) orlel@quarkatsea.com (Expedition Leader) </email0></emails><phones><phone0>870 764 812 852</phone0></phones></ship><ship><name>Marco Polo</name><code>C6JZ7</code><nbpassenger>222</nbpassenger><society>Transocean Tours Touristik GmbH</society><type>Cat. 2</type><emails><email0>mp-master@globalmaritimegroup.com</email0></emails><phones><phone0>001-954 538 422</phone0></phones></ship><ship><name>Marina Svetaeva</name><code>UFKU</code><nbpassenger>223</nbpassenger><society>Aurora Expeditions</society><type>Cat. 1</type><emails><email0>svetaeva@gmn-usa.com</email0></emails><phones><phone0>8816 3162 7573 </phone0></phones></ship><ship><name>Minerva (as Category 1)</name><code>C6NP5</code><nbpassenger>224</nbpassenger><society>Abercrombie and Kent/ All Leisure Group</society><type>Cat. 1</type><emails><email0>master@mvminerva.com (Master)   radio@mvminerva.com (Radio Officer)  shorex@mvminerva.com (Expedition Leader)   staffcapt@mvminerva.com (Staff Captain)</email0></emails><phones><phone0>870 762 309 128</phone0></phones></ship><ship><name>National Geographic Endeavour</name><code>C6BE4</code><nbpassenger>225</nbpassenger><society>Lindblad Expeditions</society><type>Cat. 1</type><emails><email0>explead.endeavour@expeditions.amosconnect.com  captain.endeavour@expeditions.amosconnect.com   chmate.endeavour@expeditions.amosconnect.com</email0></emails><phones><phone0>+1 954 518 7418</phone0></phones></ship><ship><name>National Geographic Explorer</name><code>C6WR2</code><nbpassenger>226</nbpassenger><society>Lindblad Expeditions</society><type>Cat. 1</type><emails><email0>captain.explorer@expeditions.amosconnect.com and explead.explorer@expeditions.amosconnect.com</email0></emails><phones><phone0>1 954 672 2722 </phone0></phones></ship><ship><name>Ocean Nova</name><code>C6US3</code><nbpassenger>227</nbpassenger><society>Quark Expeditions</society><type>Cat. 1</type><emails><email0>ocnmaster@quarkatsea.com (Master)  ocnhm@quarkatsea.com (Hotel Manager)  ocnel@quarkatsea.com (Expedition Leader) </email0></emails><phones><phone0> + 870 76 467 6</phone0></phones></ship><ship><name>Orion</name><code>C6TE3</code><nbpassenger>228</nbpassenger><society>Orion Expedition Cruises</society><type>Cat. 1</type><emails><email0>orion-captain@tekdirect.net</email0></emails><phones><phone0>763830598 or 76</phone0></phones></ship><ship><name>Polar Pioneer</name><code>UBST</code><nbpassenger>229</nbpassenger><society>Aurora Expeditions</society><type>Cat. 1</type><emails><email0>polarpioneer@gmn-usa.com</email0></emails><phones><phone0>761 321 399 iri</phone0></phones></ship><ship><name>Polar Star</name><code>8PPK</code><nbpassenger>230</nbpassenger><society>Polar Star Expeditions</society><type>Cat. 1</type><emails><email0>polarstar@gmn-usa.com</email0></emails><phones><phone0>Iridium:881-631</phone0></phones></ship><ship><name>Prince Albert II</name><code>C6TA8</code><nbpassenger>231</nbpassenger><society>Silversea Cruises Ltd.</society><type>Cat. 1</type><emails><email0>paexpeditionldr@silverseacruises.com, pacaptain@silverseacruises.com</email0></emails><phones><phone0>870 773180105, </phone0></phones></ship><ship><name>Professor Molchanov</name><code>UAKA</code><nbpassenger>232</nbpassenger><society>Oceanwide Expeditions</society><type>Cat. 1</type><emails><email0>pmolchanov@skyfile.com</email0></emails><phones><phone0>881 621 415 524</phone0></phones></ship><ship><name>Professor Multanovskiy</name><code>UCLA</code><nbpassenger>233</nbpassenger><society>Oceanwide Expeditions</society><type>Cat. 1</type><emails><email0>pmultanovskiy@skyfile.com</email0></emails><phones><phone0>881 621 415 523</phone0></phones></ship><ship><name>Sarsen</name><code>J8B2964</code><nbpassenger>234</nbpassenger><society>Moir Holdings Australia Pty Ltd</society><type>Motor Yacht</type><emails><email0>capsar@seawave.net</email0></emails><phones><phone0>881 641 436 054</phone0></phones></ship><ship><name>Spirit of Adventure</name><code>C6UM6</code><nbpassenger>235</nbpassenger><society>Saga Shipping Company Ltd</society><type>Cat. 2</type><emails><email0>master@adventurecruising.co.uk</email0></emails><phones><phone0>441 303 774 739</phone0></phones></ship><ship><name>Spirit of Enderby</name><code>UBNR</code><nbpassenger>236</nbpassenger><society>Heritage Expeditions</society><type>Cat. 1</type><emails><email0>427320564@inmc.eik.com,  ship@heritage-expeditions.co.nz</email0></emails><phones><phone0> </phone0></phones></ship><ship><name>Ushuaia</name><code>H9KK</code><nbpassenger>237</nbpassenger><society>Antarpply Expeditions</society><type>Cat. 1</type><emails><email0>capitan.Ushuaia@skyfile.com and expeditionleader.Ushuaia@skyfile.com </email0></emails><phones><phone0>00874-335-491-6</phone0></phones></ship><ship><name>Amsterdam</name><code>PBAD</code><nbpassenger>238</nbpassenger><society>Holland America Line, Inc</society><type>Cruise only</type><emails><email0> </email0></emails><phones><phone0>1-954-538-4292</phone0></phones></ship><ship><name>Clipper Pacific</name><code> </code><nbpassenger>239</nbpassenger><society>Japan Grace Co., Ltd. / Peaceboat </society><type>Cruise only</type><emails><email0>AMDM-Master@hollandamerica.com</email0></emails><phones><phone0> </phone0></phones></ship><ship><name>Crytsal Symphony</name><code>C6MY5</code><nbpassenger>240</nbpassenger><society>Crystal Cruises, Inc.</society><type>Cruise only</type><emails><email0> </email0></emails><phones><phone0>011 (871/2/3/4)</phone0></phones></ship><ship><name>Prinsendam</name><code>PBGH</code><nbpassenger>241</nbpassenger><society>Holland America Line, Inc</society><type>Cruise only</type><emails><email0>captain.cs@crystalcruises.com</email0></emails><phones><phone0>954-538-4327</phone0></phones></ship><ship><name>Star Princess</name><code>ZCDD6</code><nbpassenger>242</nbpassenger><society>Princess Cruises</society><type>Cruise only</type><emails><email0>PRDM_Master@hollandamerica.com</email0></emails><phones><phone0>011-970-331-036</phone0></phones></ship><ship><name>S/V Australis</name><code>vkv6796</code><nbpassenger>243</nbpassenger><society>Ocean Expeditions</society><type>yacht</type><emails><email0>tpdcapt1@princesscruises.com</email0></emails><phones><phone0>881 6 3155 6577</phone0></phones></ship><ship><name>S/V Golden Fleece</name><code>ZDLN1</code><nbpassenger>244</nbpassenger><society>Golden Fleece Expeditions Ltd.</society><type>yacht</type><emails><email0> </email0></emails><phones><phone0>881 631 518 337</phone0></phones></ship><ship><name>S/V Kotick</name><code>V2YZ8</code><nbpassenger>245</nbpassenger><society>Kotick Charters Ltd.</society><type>yacht</type><emails><email0>vkv6796@sailmail.com, auswally@gmn-usa.com, roger@ocean-expeditions.com</email0></emails><phones><phone0>881 631 555 796</phone0></phones></ship><ship><name>S/V Pelagic</name><code>ZJL 5390</code><nbpassenger>246</nbpassenger><society>Pelagic Expeditions LTD</society><type>yacht</type><emails><email0> </email0></emails><phones><phone0>881 631 537 586</phone0></phones></ship><ship><name>S/V Pelagic Australis</name><code>ZJL 6951</code><nbpassenger>247</nbpassenger><society>Pelagic Expeditions LTD</society><type>yacht</type><emails><email0>kotick@gmn-usa.com or koticaradec@hotmail.com</email0></emails><phones><phone0>881 631 537 585</phone0></phones></ship><ship><name>S/V Philos</name><code>VJT6358</code><nbpassenger>248</nbpassenger><society>Ocean Expeditions</society><type>yacht</type><emails><email0>pelagic@gmn-usa.com</email0></emails><phones><phone0>881 631 557 260</phone0></phones></ship><ship><name>S/V Santa Maria Australis</name><code>CB7835</code><nbpassenger>249</nbpassenger><society>Sea & Ice & Mountains Expeditions</society><type>yacht</type><emails><email0>pelagicaust@gmn-usa.com</email0></emails><phones><phone0>881 631 557 564</phone0></phones></ship><ship><name>S/V Seal</name><code>WDB 9031</code><nbpassenger>250</nbpassenger><society>Sterna Corporation</society><type>yacht</type><emails><email0>philos@gmn-usa.com</email0></emails><phones><phone0>881 631 537 548</phone0></phones></ship><ship><name>S/V Spirit of Sydney</name><code>VNRZ</code><nbpassenger>251</nbpassenger><society>Spirit of Sydney Expeditions</society><type>yacht</type><emails><email0>santamaria@gmn-usa.com</email0></emails><phones><phone0>881 631 430 875</phone0></phones></ship><ship><name>S/V Tiama</name><code> </code><nbpassenger>252</nbpassenger><society>Tiama Research Expedition Charters</society><type>yacht</type><emails><email0>expeditionsail@yahoo.com</email0></emails><phones><phone0>881631829511,45</phone0></phones></ship><ship><name>S/V Vaïhéré</name><code>OS8477</code><nbpassenger>253</nbpassenger><society>Latitude Océane</society><type>yacht</type><emails><email0>spiritofsydney@gmn-usa.com</email0></emails><phones><phone0>881631557685</phone0></phones></ship><ship><name>S/V Xplore</name><code>HO-2847</code><nbpassenger>254</nbpassenger><society>Xplore Expeditions</society><type>yacht</type><emails><email0>tiama@clear.net.nz</email0></emails><phones><phone0>881 641 449 890</phone0></phones></ship></ships>");

		 	Log.e("DI", "info");
		 	parser.setInput(new StringReader(xml.replaceAll("&", "&amp;")));
		 	Log.e("DI", xml);//Toast.makeText(getApplicationContext(), "Reussite XML", Toast.LENGTH_LONG).show();
		 	boolean errorTag = false;
		 	Ship s =new Ship();
		 	while(parser.getEventType() != XmlPullParser.END_DOCUMENT && !errorTag){
		 		if(parser.getEventType() == XmlPullParser.START_TAG){
		 			Log.e("DI","start_tag"+parser.getName());
		 			if(parser.getName().equals("ship"))
		 				s=new Ship();
		 			else if(parser.getName().equals("name")){
		 				parser.next();
		 				s.setName(parser.getText());
		 			}
		 			else if(parser.getName().equals("code")){
		 				parser.next();
		 				s.setCode(parser.getText());
		 			}
		 			else if(parser.getName().equals("nbpassenger")){
		 				parser.next();
		 				s.setPassager(parser.getText());
		 			}
		 			else if(parser.getName().equals("society")){
		 				parser.next();
		 				s.setSociete(parser.getText());
		 			}
		 			else if(parser.getName().equals("type")){
		 				parser.next();
		 				s.setType(parser.getText());
		 			}
		 			/*else if(parser.getName().equals("phones")){
		 				parser.next();
		 				ArrayList<String> activities = new ArrayList<String>();
		 				//s.setZone(parser.getText());
		 				//parser.next();
		 				while(parser.getEventType() == XmlPullParser.END_TAG && parser.getName().equals("phone")){
			 				parser.next();
			 				Log.e("DI", "phone = "+parser.getText());
			 				activities.add(parser.getText());
			 			}
		 				s.setActivity(activities);
		 			}
		 			else if(parser.getName().equals("emails")){
		 				parser.next();
		 				ArrayList<String> activities = new ArrayList<String>();
		 				//s.setZone(parser.getText());
		 				//parser.next();
		 				while(parser.getEventType() == XmlPullParser.END_TAG && parser.getName().equals("email")){
			 				parser.next();
			 				Log.e("DI", "email = "+parser.getText());
			 				activities.add(parser.getText());
			 			}*/
		 				//s.setActivity(activities);
		 			//}
		 		}
		 		if(parser.getEventType() == XmlPullParser.END_TAG){
		 			Log.e("DI","end_tag"+parser.getName());
		 			if(parser.getName().equals("ship"))
		 				sa.add(s);
		 		}
		 		parser.next();
		 	}		
		}
		catch(Exception e){
			Log.e("DI", "erreur = "+e.getMessage());
			e.printStackTrace();
		}
		Ship s = new Ship();
		s.setName("coucou");
		sa.add(s);
		lv.setAdapter(sa);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}



}
