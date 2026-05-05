package gestion_restaurent;

public class Tables extends Commande {
	public Tables(Integer numt,Integer numc) {
		super(numc);
		this.numt = numt;
	}

	private  Integer numt;

	public Integer getNumt() {
		return numt;
	}

	public void setNumt(Integer numt) {
		this.numt = numt;
	}
	
	
	
	
	

}
