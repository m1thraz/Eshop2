package shop.local.valueobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Warenkorb {

	private Artikel artikel;
	private Map<Artikel, Integer> inhalt;
	private Kunde kunde;
	
	public Warenkorb(Artikel artikel, Kunde kunde) {
        this.artikel = artikel;
        this.kunde = kunde;
    }

	public Warenkorb() {	                       
		inhalt = new HashMap<Artikel, Integer>();
		this.kunde=null;
	}

	public Map<Artikel, Integer> getInhalt() {
		return inhalt;
	}

	public void setInhalt(Map<Artikel, Integer> inhalt) {
		this.inhalt = inhalt;
	}
	
	public Kunde getKunde() {
		return this.kunde;
	}
		
	public Artikel getArtikel() {
        return artikel;
    }
		
	public void setKunde(Kunde kunde) {
		this.kunde=kunde;
	}
		
	public void warenkorbLeeren() {
		this.inhalt.clear();
	}
	
	public void artikelLoeschen(Artikel artikel, int anzahl) {
		//this.inhalt.remove(artikel, anzahl);
		
		this.inhalt.replace(artikel, inhalt.get(artikel)-anzahl);
		if(inhalt.get(artikel)<= 0){
			inhalt.remove(artikel);
			
		}
	}
	
	public void artikelHinzufuegen(Artikel artikel, int anzahl) {
		if(this.inhalt.containsKey(artikel))	{
			this.inhalt.replace(artikel, this.inhalt.get(artikel) + anzahl);
		}else { 
			this.inhalt.put(artikel, anzahl);
		}
	}
	
	public List<Artikel> toList(){
		List<Artikel> warenkorbListe = new ArrayList<>();
		for(Artikel a : inhalt.keySet()) {
			new Artikel(a.getBezeichnung(), a.getArtNr(), a.getPreis(), inhalt.get(a));
			warenkorbListe.add(new Artikel(a.getBezeichnung(), a.getArtNr(), a.getPreis(), inhalt.get(a)));
		}
		
		return warenkorbListe;
	}
	
	//TODO	in RechnungSservice verlagern
 public double rechneGesamtpreis() {
		double gesamtpreis=0;
		for(Map.Entry<Artikel, Integer> entry: this.getInhalt().entrySet()) {
			gesamtpreis+=entry.getKey().getPreis()*entry.getValue();
		}
		return gesamtpreis;
	}
	
	@Override
	public String toString() {
		return "Warenkorb: " + inhalt +"\n\n" +"Gesamtpreis: " + rechneGesamtpreis() +" €";
	}

}
