package gestion_restaurent;

public class Addition {
	
	public double calculetotal(Commande c) {
		double total=0;
		
		for(plat p : c.getplatcommander()) {// calcule dyal  l'addition//
			
			total=total+p.getPrixp();	
			}
		return total;
	}

}
