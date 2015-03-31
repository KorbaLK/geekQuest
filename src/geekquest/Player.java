package geekquest;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Player {

	Key key;
	String name;
	String charclass;
	static final int HEALTH = 80;
	String email;

	Player(String email, String name, String charclass){
		this.key = KeyFactory.createKey("character", email);
		this.name = name;
		this.charclass = charclass;
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

	public void heal(int points){

	}

	public void hart(int points){

	}


}
