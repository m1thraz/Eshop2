package shop.local.domain;

import java.util.Map;

import shop.local.domain.exceptions.ArtikelBestandReichtNichtAusException;
import shop.local.domain.exceptions.MassengutartikelException;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Massengutartikel;
import shop.local.valueobjects.Warenkorb;


public class WarenkorbService{	
	
	
	private ArtikelVerwaltung meineArtikel;
	public WarenkorbService(ArtikelVerwaltung meineArtikel, KundenVerwaltung meineKunden, EreignisVerwaltung meineEreinisVerwaltung) {		
		this.meineArtikel = meineArtikel;
	}
	
	public Warenkorb hinzufuegen(Artikel artikel, Kunde kunde, int menge) throws MassengutartikelException, ArtikelBestandReichtNichtAusException {
		// Artikel wird dem Warenkorb hinzugefügt
		//Zuerst wird geprüft ob der Bestand ausreicht, dann ob die Packungsgröße stimmt
			
		if(artikel.getBestand()<menge) {    //Bestand wird geprüft
			throw new ArtikelBestandReichtNichtAusException(artikel);
		}else if (artikel instanceof Massengutartikel) {
			Massengutartikel mArtikel = (Massengutartikel) artikel; 
			if (menge % mArtikel.getEinheit() != 0) {
				throw new MassengutartikelException((Massengutartikel) artikel);
			}
		}
		Warenkorb neuerWarenkorb =kunde.getWarenkorb();                         
        neuerWarenkorb.artikelHinzufuegen(artikel, menge);           //Artikel wird hinzugefügt                    
		meineArtikel.aendereBestandVonArtikel(artikel.getArtNr(), - menge);  // Artikel Bestand wird geändert
		return neuerWarenkorb;
	}

	 public void loescheWarenkorb(Kunde kunde) {
		 Warenkorb warenkorb = kunde.getWarenkorb();	
			for(Map.Entry<Artikel, Integer> entry: warenkorb.getInhalt().entrySet()) {				
				int menge = entry.getValue();
			    Artikel artikel = entry.getKey();
			    entfernen(artikel, kunde, menge);	
			}
			warenkorbLeeren(warenkorb);
	 }
	 
	public Warenkorb entfernen(Artikel artikel, Kunde kunde, int menge) {
	//Artikel wird aus Warenkorb entfernt
		
		Warenkorb warenkorb = kunde.getWarenkorb();		
		warenkorb.artikelLoeschen(artikel, menge);
		meineArtikel.aendereBestandVonArtikel(artikel.getArtNr(), + menge);
		
		return warenkorb;
	}

	public Warenkorb getWarenkorb(Kunde kunde) {
		return kunde.getWarenkorb();
	}
	
	public void warenkorbLeeren(Warenkorb warenkorb) {
	//Warenkorb fuer kaufAbschließen funktion leeren 
	//Bestand wird nicht zurückgesetzt!
		warenkorb.warenkorbLeeren();
	}

	/**
	 * Mit dieser Methode werden die Artikel die sich im Warenkorb befinden eingekauft
	 * Die Anzahl von den Einzelnen Artikeln wird dem Bestand der Artikel abgezogen 
	 * Es wird ein Rechnungs Objekt erstellt mit einer Liste aller gekauften Artikel 
	 * Anschließend iwrd der Warenkorb geleert 
	 * @param kunde
	 */
	 public void kaufAbschliessen(Kunde kunde) {
		 warenkorbLeeren(kunde.getWarenkorb());		 
	 }
}
