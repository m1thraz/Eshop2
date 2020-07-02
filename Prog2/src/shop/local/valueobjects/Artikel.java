package shop.local.valueobjects;


public class Artikel {
	


	protected String bezeichnung;
	protected int artNr;
	protected int bestand;
	protected double preis;	
	protected boolean verfuegbar;
	
	public Artikel(String bezeichnung, int artNr, double preis, int bestand) {
		this.bezeichnung=bezeichnung;
		this.artNr=artNr;
		this.preis=preis;
		this.bestand=bestand;
	}
	
	
	public Artikel(String bezeichnung, int artNr, boolean verfuegbar) {
		this.bezeichnung=bezeichnung;
		this.artNr=artNr;
		this.verfuegbar=verfuegbar;
	}
	
	public Artikel(String bezeichnung,int artNr){
		this.bezeichnung=bezeichnung;
		this.artNr=artNr;
	}
	
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public int getArtNr() {
		return artNr;
	}
	public void setArtNr(int artNr) {
		this.artNr = artNr;
	}
	public int getBestand() {
		return bestand;
	}
	
	public void setBestand(int bestand) {
		this.bestand = bestand;
	}
	public boolean isVerfuegbar() {
		return (bestand > 0);
	}
	
	

	public boolean equals(Object anderesArtikel) {
		if (anderesArtikel instanceof Artikel) 
			return ((this.artNr == ((Artikel) anderesArtikel).artNr) 
					&& (this.bezeichnung.equals(((Artikel) anderesArtikel).bezeichnung)));
		else
			return false;
	}

	public String toString() {
		return ("Artikelnummer: " + artNr + "  Artikelname: " + bezeichnung + " Preis: " + preis + " Bestand: " + bestand );
	}
	


}
