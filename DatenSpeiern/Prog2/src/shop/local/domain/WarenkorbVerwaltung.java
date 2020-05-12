package shop.local.domain;

import java.util.List;
import java.util.Vector;

import shop.local.valueObjects.Artikel;
import shop.local.valueObjects.Kunde;
import shop.local.valueObjects.Warenkorb;

public class WarenkorbVerwaltung{
	
	
	private List<Warenkorb> warenkorbListe = new Vector<Warenkorb>();
	
	private ArtikelVerwaltung meineArtikel;
	private KundenVerwaltung meineKunden;
	
	
	
	
	public WarenkorbVerwaltung(ArtikelVerwaltung meineArtikel, KundenVerwaltung meineKunden) {		
		this.meineArtikel = meineArtikel;
		this.meineKunden = meineKunden;
	}
	
	public Warenkorb hinzufügen(Artikel artikel, Kunde kunde) {
		if(artikel.getBestand()==0) 		
		return null;
	
		Warenkorb neuerWarenkorb = new Warenkorb(artikel, kunde);		
		warenkorbListe.add(neuerWarenkorb);
		artikel.setBestand(artikel.getBestand() -1);
		return neuerWarenkorb;
		
	}
	
	
	public void  entfernen(Artikel artikel) {
		 
		Warenkorb warenkorb = sucheNachWarenkorb(artikel);

	        loescheAusListe(warenkorb);
		
		artikel.setBestand(artikel.getBestand() +1);	
			
	}
	
    private void loescheAusListe(Warenkorb warenkorb) {
        // das übernimmt der Vector:
        warenkorbListe.remove(warenkorb);
    }
    
    public Warenkorb sucheNachWarenkorb(Artikel b) {

        for (Warenkorb warenkorb : warenkorbListe) {
            if (warenkorb.getArtikel().equals(b)) {
                return warenkorb;
            }
        }

        return null;
    }
	
	
	
	

	public List<Warenkorb> getWarenkorbListe() {
		return warenkorbListe;
	}

	public void setWarenkorbListe(List<Warenkorb> warenkorbListe) {
		this.warenkorbListe = warenkorbListe;
	}
	
	
	
	

}
