package geekquest;

import java.util.ArrayList;

public class Mission {

	String description;
	boolean isAccomplished;
	ArrayList<String> missions = new ArrayList<String>();
		
	/*missions.add("Destroying Ring");
	missions.add("Kill the Orcs");
	missions.add("Save Frodo");
	missions.add("Search for Erabor");
	*/
	
	public Mission(String description, boolean isAccomplished) {
		super();
		this.description = description;
		this.isAccomplished = isAccomplished;
	}

	public void accomplish(){
		isAccomplished = true;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAccomplished() {
		return isAccomplished;
	}

	public void setAccomplished(boolean isAccomplished) {
		this.isAccomplished = isAccomplished;
	}
	
	

}
