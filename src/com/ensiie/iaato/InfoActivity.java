package com.ensiie.iaato;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ensiie.iaato_data.Site;
import com.ensiie.iaato_data.SiteAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

public class InfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.liste_site);
		
		SiteAdapter sa = new SiteAdapter(this, R.layout.line);
		ListView lv = (ListView) findViewById(R.id.ListProd) ;
		try{	 
		 	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		 	XmlPullParser parser = factory.newPullParser();
		 	
		 	SharedPreferences preferences = getSharedPreferences("IAATO", MODE_PRIVATE);	
		 	String xml=preferences.getString("IAATO_sites", "<sites><site><name>Aitcho Islands</name><latitide>62.24</latitide><longitude>59.47</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>English Strait. SSI</subzone></zone></site><site><name>Alcock Island</name><latitide>64.14</latitide><longitude>61.08</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Hughes Bay</subzone></zone></site><site><name>Arago Glacier</name><latitide>64.51</latitide><longitude>62.23</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Andvord Bay</subzone></zone></site><site><name>Ardley Island</name><latitide>62.13</latitide><longitude>58.56</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>KGI, SSI</subzone></zone></site><site><name>Argentine Islands</name><latitide>65.15</latitide><longitude>64.16</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Penola Strait</subzone></zone></site><site><name>Astrolabe Island</name><latitide>63.17</latitide><longitude>58.4</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Bransfield Strait</subzone></zone></site><site><name>Baily Head</name><latitide>62.58</latitide><longitude>60.3</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Deception Island, SSI</subzone></zone></site><site><name>Barcroft Islands</name><latitide>66.27</latitide><longitude>67.1</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Biscoe Islands</subzone></zone></site><site><name>Bennett Islands</name><latitide>66.56</latitide><longitude>67.4</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Hanusse Bay</subzone></zone></site><site><name>Berthelot Islands</name><latitide>65.2</latitide><longitude>64.09</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Grandidier Channel</subzone></zone></site><site><name>Blaiklock Island</name><latitide>67.33</latitide><longitude>67.04</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>NE Marguerite Bay</subzone></zone></site><site><name>Bongrain Point</name><latitide>67.43</latitide><longitude>67.48</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>W side Pourquoi Pas Island</subzone></zone></site><site><name>Brown Bluff</name><latitide>63.32</latitide><longitude>56.55</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Tabarin Peninsula</subzone></zone></site><site><name>Camp Point</name><latitide>67.58</latitide><longitude>67.19</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Eastern Marguerite Bay</subzone></zone></site><site><name>Challenger Island</name><latitide>64.21</latitide><longitude>61.35</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Gerlache Strait</subzone></zone></site><site><name>Charcot, Port</name><latitide>65.04</latitide><longitude>64</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Booth Island</subzone></zone></site><site><name>Christiania Islands</name><latitide>63.57</latitide><longitude>61.27</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Between Liege & Trinity Is.</subzone></zone></site><site><name>Christiania Islands</name><latitide>63.55</latitide><longitude>61.24</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Gerlache Strait</subzone></zone></site><site><name>Comb Ridge</name><latitide>63.55</latitide><longitude>57.28</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Northern James Ross Island</subzone></zone></site><site><name>Cormorant Island</name><latitide>64.48</latitide><longitude>63.58</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>South side of Anvers I.</subzone></zone></site><site><name>Crystal Hill</name><latitide>63.39</latitide><longitude>57.44</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Prince Gustav Channel</subzone></zone></site><site><name>Cuverville Island</name><latitide>64.41</latitide><longitude>62.38</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Errera Channel</subzone></zone></site><site><name>Damoy Point</name><latitide>64.49</latitide><longitude>63.32</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Wiencke Island (hut at Dorian Bay)</subzone></zone></site><site><name>Danco Island</name><latitide>64.44</latitide><longitude>62.37</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Errera Channel</subzone></zone></site><site><name>Danger Islands</name><latitide>63.25</latitide><longitude>54.4</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>SE of Joinville Island</subzone></zone></site><site><name>Demaria, Mount</name><latitide>64.49</latitide><longitude>63.32</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Wiencke Island (hut at Dorian Bay)</subzone></zone></site><site><name>Detaille Island</name><latitide>66.52</latitide><longitude>66.48</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Crystal Sound</subzone></zone></site><site><name>Devil Island</name><latitide>63.48</latitide><longitude>57.17</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>E. of James Ross Island</subzone></zone></site><site><name>Dorian Bay</name><latitide>64.49</latitide><longitude>63.32</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Wiencke Island (hut at Dorian Bay)</subzone></zone></site><site><name>Dubouzet, Cape</name><latitide>63.16</latitide><longitude>57.03</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>NE Trinity Peninsula</subzone></zone></site><site><name>Dundas, Cape</name><latitide>60.44</latitide><longitude>44.24</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Eastern Laurie Island, SOI</subzone></zone></site><site><name>D Urville Monument</name><latitide>63.25</latitide><longitude>56.18</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>SW Joinville Island</subzone></zone></site><site><name>Duthiers Point</name><latitide>64.48</latitide><longitude>62.49</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Gerlache Strait</subzone></zone></site><site><name>Duthoit Point</name><latitide>62.19</latitide><longitude>58.5</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Eastern Nelson Island, SSI</subzone></zone></site><site><name>Enterprise Island</name><latitide>64.32</latitide><longitude>62</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Gerlache Strait</subzone></zone></site><site><name>Evensen, Cape</name><latitide>66.09</latitide><longitude>65.44</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Crystal Sound</subzone></zone></site><site><name>Fildes Peninsula</name><latitide>62.12</latitide><longitude>58.58</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>KGI, SSI</subzone></zone></site><site><name>Fish Islands</name><latitide>66.02</latitide><longitude>65.25</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Holtedahl Bay</subzone></zone></site><site><name>Gage, Cape</name><latitide>64.1</latitide><longitude>57.05</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Eastern James Ross Island</subzone></zone></site><site><name>Gaston Islands</name><latitide>64.28</latitide><longitude>61.5</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Gerlache Strait</subzone></zone></site><site><name>Georges Point</name><latitide>64.4</latitide><longitude>62.4</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Northern Ronge Island</subzone></zone></site><site><name>Gibbs Island</name><latitide>61.28</latitide><longitude>55.34</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>SW of Elephant Island, SSI</subzone></zone></site><site><name>Gin Cove</name><latitide>64.03</latitide><longitude>58.25</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Western James Ross Island</subzone></zone></site><site><name>Girard Bay</name><latitide>65.08</latitide><longitude>64</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Lemaire Channel</subzone></zone></site><site><name>Gosling Islands</name><latitide>60.39</latitide><longitude>45.55</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>South of Coronation I., SOI</subzone></zone></site><site><name>Goudier Island</name><latitide>64.5</latitide><longitude>63.3</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Port Lockroy hut -see also Jougla Pt</subzone></zone></site><site><name>Gourdin Island</name><latitide>63.12</latitide><longitude>57.18</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>N. tip Antarctic Peninsula</subzone></zone></site><site><name>Half Moon Island</name><latitide>62.36</latitide><longitude>59.55</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>East Side of Livingston I.</subzone></zone></site><site><name>Hannah Point</name><latitide>62.39</latitide><longitude>60.37</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Livington Island, SSI</subzone></zone></site><site><name>Heim Glacier</name><latitide>67.28</latitide><longitude>66.55</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Southern Arrowsmith Peninsula</subzone></zone></site><site><name>Heroina Island</name><latitide>63.24</latitide><longitude>54.36</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>See Danger Islands</subzone></zone></site><site><name>Heywood Island</name><latitide>62.2</latitide><longitude>59.41</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Off Robert I., SSI</subzone></zone></site><site><name>Horseshoe Island</name><latitide>67.51</latitide><longitude>67.12</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>NE Marguerite Bay</subzone></zone></site><site><name>Hovgaard Island</name><latitide>65.08</latitide><longitude>64.08</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Penola Strait</subzone></zone></site><site><name>Huemul Island</name><latitide>63.4</latitide><longitude>60.5</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Northern Trinity Peninsula</subzone></zone></site><site><name>Hydrurga Rocks</name><latitide>64.08</latitide><longitude>61.37</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Gerlache Strait</subzone></zone></site><site><name>Intercurrence Island</name><latitide>63.55</latitide><longitude>61.24</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Gerlache Strait</subzone></zone></site><site><name>Jonassen Island</name><latitide>63.33</latitide><longitude>56.4</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Antarctic Sound</subzone></zone></site><site><name>Jougla Point</name><latitide>64.5</latitide><longitude>63.3</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Port Lockroy (see also Goudier Is)</subzone></zone></site><site><name>Kinnes, Cape</name><latitide>63.22</latitide><longitude>56.33</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Joinville Island (see Madder Cliffs)</subzone></zone></site><site><name>Kjellman, Cape</name><latitide>63.44</latitide><longitude>59.24</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Charcot Bay</subzone></zone></site><site><name>Lagarrigue Cove</name><latitide>64.39</latitide><longitude>62.34</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Errera Channel (near Spigot Peak)</subzone></zone></site><site><name>Lockroy, Port</name><latitide>64.5</latitide><longitude>63.3</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Port Lockroy (see also Goudier Is)</subzone></zone></site><site><name>Lookout, Cape</name><latitide>61.16</latitide><longitude>55.12</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Southern Elephant I., SSI</subzone></zone></site><site><name>Macaroni Point</name><latitide>62.54</latitide><longitude>60.32</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Deception Island, SSI</subzone></zone></site><site><name>Madder Cliffs</name><latitide>63.18</latitide><longitude>56.29</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Joinville Island</subzone></zone></site><site><name>Martin, Point</name><latitide>60.47</latitide><longitude>44.41</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>S coast Laurie Island, SOI</subzone></zone></site><site><name>Melchior Islands</name><latitide>64.19</latitide><longitude>62.57</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Dallmann Bay</subzone></zone></site><site><name>Melville, Cape</name><latitide>62.02</latitide><longitude>57.37</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Eastern KGI, SSI</subzone></zone></site><site><name>Metchnikoff Point</name><latitide>64.03</latitide><longitude>62.34</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Northern Brabant Island</subzone></zone></site><site><name>Mill, Mount</name><latitide>65.16</latitide><longitude>64.05</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Penola Strait (Mt. Mill)</subzone></zone></site><site><name>Murray Island</name><latitide>64.22</latitide><longitude>61.34</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Hughes Bay</subzone></zone></site><site><name>Neko Harbor</name><latitide>64.5</latitide><longitude>62.33</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Andvord Bay (Arg. refuge hut)</subzone></zone></site><site><name>Orne Islands</name><latitide>64.4</latitide><longitude>62.4</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Northern Ronge Island</subzone></zone></site><site><name>Palaver Point</name><latitide>64.09</latitide><longitude>61.45</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>West of Two Hummock Island</subzone></zone></site><site><name>Paulet Island</name><latitide>63.35</latitide><longitude>55.47</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>South of Dundee Island</subzone></zone></site><site><name>Pendulum Cove</name><latitide>62.56</latitide><longitude>60.36</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Deception Island - geothermal beach</subzone></zone></site><site><name>Penguin Island</name><latitide>62.06</latitide><longitude>57.54</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>King George Bay, KGI</subzone></zone></site><site><name>Penguin Point</name><latitide>64.19</latitide><longitude>56.43</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Seymour Island</subzone></zone></site><site><name>Petermann Island</name><latitide>65.1</latitide><longitude>64.1</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Penola Strait</subzone></zone></site><site><name>Pitt Islands</name><latitide>65.26</latitide><longitude>65.3</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>North of Renaud Is, Biscoe Islands</subzone></zone></site><site><name>Pitt Point</name><latitide>63.51</latitide><longitude>58.22</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Trinity Peninsula (Victory Glacier)</subzone></zone></site><site><name>Pléneau Island</name><latitide>65.06</latitide><longitude>64.04</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Penola Strait</subzone></zone></site><site><name>Portal Point</name><latitide>64.3</latitide><longitude>61.46</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Reclus Peninsula</subzone></zone></site><site><name>Port Lockroy</name><latitide>64.5</latitide><longitude>63.3</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Port Lockroy hut -see also Jougla Pt</subzone></zone></site><site><name>Prospect Point</name><latitide>66.01</latitide><longitude>65.21</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Holtedahl Bay</subzone></zone></site><site><name>Robert Point</name><latitide>62.28</latitide><longitude>59.23</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Robert Island, SSI</subzone></zone></site><site><name>Rongé Island</name><latitide>64.43</latitide><longitude>62.41</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>West side of Errera Channel</subzone></zone></site><site><name>Rosamel Island</name><latitide>63.34</latitide><longitude>56.17</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Antarctic Sound</subzone></zone></site><site><name>Rum Cove</name><latitide>64.06</latitide><longitude>58.25</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>James Ross Is</subzone></zone></site><site><name>Seymour Island</name><latitide>64.19</latitide><longitude>56.43</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Seymour Island</subzone></zone></site><site><name>Shingle Cove</name><latitide>60.39</latitide><longitude>45.34</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>South coast of Coronation I., SOI</subzone></zone></site><site><name>Skontorp Cove</name><latitide>64.54</latitide><longitude>62.52</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Paradise Harbor ,if landing</subzone></zone></site><site><name>Small Island</name><latitide>64</latitide><longitude>61.27</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Off Intercurrence Island</subzone></zone></site><site><name>Snow Hill Island</name><latitide>64.28</latitide><longitude>57.12</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Admiralty Sound</subzone></zone></site><site><name>Spigot Peak</name><latitide>64.39</latitide><longitude>62.34</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Errera Channel (near Spigot Peak)</subzone></zone></site><site><name>Spring Point</name><latitide>64.18</latitide><longitude>61.03</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Hughes Bay</subzone></zone></site><site><name>Stonington Island</name><latitide>68.11</latitide><longitude>67</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>East Marguerite Bay</subzone></zone></site><site><name>Su‚àö¬∞rez Glacier</name><latitide>64.56</latitide><longitude>62.56</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Paradise Harbor</subzone></zone></site><site><name>Telefon Bay</name><latitide>62.56</latitide><longitude>60.4</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Deception Island , walk to craters</subzone></zone></site><site><name>Torgersen Island</name><latitide>64.46</latitide><longitude>64.05</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Arthur Harbor, Anvers I.</subzone></zone></site><site><name>Turret Point</name><latitide>62.05</latitide><longitude>57.55</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>King George Island, SSI</subzone></zone></site><site><name>Tuxen, Cape</name><latitide>65.16</latitide><longitude>64.08</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Waddington Bay (Mt. Demaria)</subzone></zone></site><site><name>Useful Island</name><latitide>64.43</latitide><longitude>62.52</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>West of Ronge Island</subzone></zone></site><site><name>Valentine, Cape</name><latitide>61.06</latitide><longitude>54.39</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Eastern Elephant I., SSI</subzone></zone></site><site><name>View Point</name><latitide>63.33</latitide><longitude>57.22</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Duse Bay, Trinity Peninsula</subzone></zone></site><site><name>Waddington Bay</name><latitide>65.16</latitide><longitude>64.05</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Penola Strait (Mt. Mill)</subzone></zone></site><site><name>Wauwermans Islands</name><latitide>64.55</latitide><longitude>63.53</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>South of Anvers Island</subzone></zone></site><site><name>Whalers Bay</name><latitide>62.59</latitide><longitude>60.34</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Deception Island , old stations</subzone></zone></site><site><name>Wiggins Glacier</name><latitide>65.14</latitide><longitude>64.03</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Grandidier Channel</subzone></zone></site><site><name>Wild, Point</name><latitide>61.06</latitide><longitude>54.52</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Northern Elephant I., SSI</subzone></zone></site><site><name>Yalour Islands</name><latitide>65.14</latitide><longitude>64.1</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Penola Strait</subzone></zone></site><site><name>Yankee Harbor</name><latitide>62.32</latitide><longitude>59.47</longitude><iaato>1</iaato><activities></activities><zone>Antarctic Peninsula<subzone>Greenwich I., SSI - Spit Point</subzone></zone></site><site><name>Ushuaia</name><latitide>54.47</latitide><longitude>68.13</longitude><iaato></iaato><activities></activities><zone>Argentina<subzone>Argentina</subzone></zone></site><site><name>Bertha's beach</name><latitide>51.52</latitide><longitude>58.19</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Bleaker Island</name><latitide>52.12</latitide><longitude>58.51</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Carcass Island</name><latitide>51.18</latitide><longitude>60.34</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>George & Barren Islands</name><latitide>52.21</latitide><longitude>59.45</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Grand Jason</name><latitide>51.04</latitide><longitude>61.05</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Grave Cove</name><latitide>51.22</latitide><longitude>60.38</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Gypsy Cove</name><latitide>51.41</latitide><longitude>57.48</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Kidney Cove</name><latitide>51.38</latitide><longitude>57.46</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>New Island</name><latitide>51.43</latitide><longitude>61.18</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Pebble Island</name><latitide>51.18</latitide><longitude>59.31</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Port Howard</name><latitide>51.37</latitide><longitude>59.31</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Saunders Island</name><latitide>51.22</latitide><longitude>60.05</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Sea Lion Island</name><latitide>52.26</latitide><longitude>59.05</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Second Passage Island</name><latitide>51.35</latitide><longitude>60.47</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Steeple Jason</name><latitide>51.02</latitide><longitude>61.12</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Volunteer Point</name><latitide>51.28</latitide><longitude>57.5</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Weddell Island</name><latitide>51.54</latitide><longitude>60.54</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>West Point Island</name><latitide>51.21</latitide><longitude>60.41</longitude><iaato></iaato><activities></activities><zone>Falklands<subzone>Falklands</subzone></zone></site><site><name>Elsehul</name><latitide>54.01</latitide><longitude>37.59</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Prion Island</name><latitide>54.1</latitide><longitude>37.2</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Salisbury Plain</name><latitide>54.03</latitide><longitude>37.19</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Prince Olav Harbour</name><latitide>54.04</latitide><longitude>37.09</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Fortuna Bay</name><latitide>54.07</latitide><longitude>36.48</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Hercules Bay</name><latitide>54.08</latitide><longitude>36.4</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Leith Harbour</name><latitide>54.08</latitide><longitude>36.41</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Stromness Harbour</name><latitide>54.1</latitide><longitude>36.43</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Husvik</name><latitide>54.11</latitide><longitude>36.43</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Jason Harbour</name><latitide>54.11</latitide><longitude>36.35</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Grytviken (King Edward Point)</name><latitide>54.17</latitide><longitude>36.3</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Maiviken</name><latitide>54.15</latitide><longitude>36.31</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Cobblers Cove</name><latitide>54.16</latitide><longitude>36.18</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Godthul</name><latitide>54.17</latitide><longitude>36.18</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Ocean Harbour</name><latitide>54.2</latitide><longitude>36.16</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Saint Andrews Bay</name><latitide>54.26</latitide><longitude>36.11</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Moltke Harbour</name><latitide>54.31</latitide><longitude>36.04</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Will Point & Brisbane Point</name><latitide>54.32</latitide><longitude>36</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Gold Harbour</name><latitide>54.37</latitide><longitude>35.56</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Cooper Bay</name><latitide>54.47</latitide><longitude>35.48</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>Drygalski Fjord & Larsen Harbour</name><latitide>54.5</latitide><longitude>36.01</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site><site><name>King Haakon Bay</name><latitide>54.1</latitide><longitude>37.2</longitude><iaato></iaato><activities></activities><zone>South Georgia<subzone>South Georgia</subzone></zone></site></sites>");
		 	
		 	Log.e("DI", "info");
		 	parser.setInput(new StringReader(xml.replaceAll("&", "&amp;")));
		 	Log.e("DI", xml);//Toast.makeText(getApplicationContext(), "Reussite XML", Toast.LENGTH_LONG).show();
		 	boolean errorTag = false;
		 	Site s =new Site();
		 	while(parser.getEventType() != XmlPullParser.END_DOCUMENT && !errorTag){
		 		if(parser.getEventType() == XmlPullParser.START_TAG){
		 			Log.e("DI","start_tag"+parser.getName());
		 			if(parser.getName().equals("site"))
		 				s=new Site();
		 			else if(parser.getName().equals("name")){
		 				parser.next();
		 				s.setName(parser.getText());
		 			}
		 			else if(parser.getName().equals("latitide")){
		 				parser.next();
		 				s.setLatitude(parser.getText());
		 			}
		 			else if(parser.getName().equals("longitude")){
		 				parser.next();
		 				s.setLongitude(parser.getText());
		 			}
		 			else if(parser.getName().equals("iaato")){
		 				parser.next();
		 				s.setIaato(parser.getText());
		 			}
		 			else if(parser.getName().equals("activities")){
		 				parser.next();
		 				ArrayList<String> activities = new ArrayList<String>();
		 				//s.setZone(parser.getText());
		 				//parser.next();
		 				while(parser.getEventType() == XmlPullParser.END_TAG && parser.getName().equals("activity")){
			 				parser.next();
			 				Log.e("DI", "activity = "+parser.getText());
			 				activities.add(parser.getText());
			 			}
		 				s.setActivity(activities);
		 			}
		 			else if(parser.getName().equals("zone")){
		 				parser.next();
		 				s.setZone(parser.getText());
		 				parser.next();
		 				if(parser.getName().equals("subzone")){		 				
			 				//parser.next();
			 				s.setSubzone(parser.getText());
			 			}
		 			}
		 		}
		 		if(parser.getEventType() == XmlPullParser.END_TAG){
		 			Log.e("DI","end_tag"+parser.getName());
		 			if(parser.getName().equals("site"))
		 				sa.add(s);
		 		}
		 		parser.next();
		 	}		
		}
		catch(Exception e){
			Log.e("DI", "erreur = "+e.getMessage());
			e.printStackTrace();
		}
		Site s = new Site();
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
