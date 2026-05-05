package gestion_restaurent;
import java.util.ArrayList;
public class Commande {
	private Integer numc;
	private ArrayList <plat> platcommander;
	public Commande(Integer numc) {
		this.numc=numc;
		platcommander=new ArrayList<>();
	}
	public void ajouterplat(plat pc) {
		platcommander.add(pc);
	}
	public ArrayList<plat> getplatcommander(){
		return platcommander;
	}
	

}
