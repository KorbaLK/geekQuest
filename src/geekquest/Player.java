package geekquest;

import java.util.ArrayList;
import java.util.Collections;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Player {

	Key key;
	String name;
	String charclass;
	static final int HEALTH = 10;
	String email;
	ArrayList<String> missions = new ArrayList<String>();

	Player(String email, String name, String charclass) {
		this.key = KeyFactory.createKey("character", email);
		this.name = name;
		this.charclass = charclass;
		missions.add("Destroying Ring");
		missions.add("Kill the Orcs");
		missions.add("Save Frodo");
		missions.add("Search for Erabor");
	}

	public Key getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCharclass() {
		return charclass;
	}

	public void setCharclass(String charclass) {
		this.charclass = charclass;
	}

	public void heal(int points) {

	}

	public void hart(int points) {

	}

	public String getRandomMission() {
		
		String randomMission;
	
		
		Collections.shuffle(missions);
		randomMission = missions.get(0);
		
		
		return randomMission;
	}

}
