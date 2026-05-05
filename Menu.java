package gestion_restaurent;
import java.util.ArrayList;
public class Menu {
	private ArrayList<plat> plats=new ArrayList();
	public void addplat(plat p) {
		plats.add(p);
	}
	public ArrayList<plat> getPlats() {
		
	    return new ArrayList<>(plats);
	}
		
	}
	
	


