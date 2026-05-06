package gestion_restaurent;

public class plat {
	public plat(String nomp, double prixp) {
		super();
		this.nomp = nomp;
		this.prixp = prixp;
	}
	private String nomp;
	private double prixp;
	public String getNomp() {
		return nomp;
	}
	public void setNomp(String nomp) {
		this.nomp = nomp;
	}
	public double getPrixp() {
		return prixp;
	}
	public void setPrixp(double prixp) {
		this.prixp = prixp;
	}
	@Override
	public String toString() {
		return "plat [nomp=" + nomp + ", prixp=" + prixp + " DH]";//redefinition dyal l affichage//
	}
	
	

}
