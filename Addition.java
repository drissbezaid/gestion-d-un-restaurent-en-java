package gestion_restaurent;

public class Addition {
	
	public double calculetotal(Commande c) {
		double total=0;
		for(plat p : c.getplatcommander()) {
			total=total+p.getPrixp();		}
		return total;
	}

}
