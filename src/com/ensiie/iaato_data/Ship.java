package com.ensiie.iaato_data;

import java.util.ArrayList;

public class Ship {

	private String name;
	private String latitude;
	private String longitude;
	private String iaato;
	private ArrayList<String> activity;
	private String zone;
	private String subzone;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getIaato() {
		return iaato;
	}

	public void setIaato(String iaato) {
		this.iaato = iaato;
	}

	public ArrayList<String> getActivity() {
		return activity;
	}

	public void setActivity(ArrayList<String> activity) {
		this.activity = activity;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getSubzone() {
		return subzone;
	}

	public void setSubzone(String subzone) {
		this.subzone = subzone;
	}	
	
}
