package geekquest;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Player {

	Key key;
	String name;
	String charclass;
	int health;
	String email;

	Player(String email, String name, String charclass, int health){
		this.key = KeyFactory.createKey("Charakter", email);
		this.name = name;
		this.charclass = charclass;
		this.health = health;
	}

	public Key getKey(){
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void heal(int points){

	}

	public void hart(int points){

	}


}
