package gestion_restaurent;
import java.util.ArrayList;
public class Menu {
	private ArrayList<plat> plats=new ArrayList();
	public void addplat(plat p) { // zid les plats f la liste//
		plats.add(p);
	}
	public ArrayList<plat> getPlats() { // ki rje3 la liste//
	    return new ArrayList<>(plats);
	}
		
	}
	
	


