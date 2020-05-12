package shop.local.domain;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.domain.exceptions.ArtikelExistiertBereitsException;
import shop.local.domain.exceptions.ArtikelMitArtNrExistiertNichtException;
import shop.local.domain.exceptions.KundeExistiertBereitsException;
import shop.local.domain.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueObjects.*;

public class EShop {
	 private String datei = "";
	private ArtikelVerwaltung meineArtikel;
	private KundenVerwaltung meineKunden;
	private WarenkorbVerwaltung meineWarenkorbVerwaltung;
	private MitarbeiterVerwaltung meineMitarbeiter;


	
	
	  public EShop(String datei) throws IOException, MitarbeiterExistiertBereitsException, KundeExistiertBereitsException {
        this.datei = datei;

        // Artikelbestand aus Datei einlesen
        meineArtikel = new ArtikelVerwaltung();
        try {
            meineArtikel. liesDaten(datei+"_B.txt");
        } catch (ArtikelExistiertBereitsException e) {
            e.printStackTrace();
        }

		// Kundenkartei aus Datei einlesen
		meineKunden = new KundenVerwaltung();
      	try {
			meineKunden.liesDaten(datei+"_K.txt");
		} catch (KundeExistiertBereitsException e) {

			e.printStackTrace();
		}
     // Mitarbeiterkartei aus Datei einlesen
    	meineMitarbeiter = new MitarbeiterVerwaltung();
      	try {      
      	meineMitarbeiter.liesDaten(datei+"_M.txt");
      } catch (KundeExistiertBereitsException e) {

			e.printStackTrace();
		}
      	
      	
      	
      	meineWarenkorbVerwaltung = new WarenkorbVerwaltung(meineArtikel, meineKunden);

    }
	
	
	public List<Artikel> gibAlleArtikel() {	        
	        return meineArtikel.getArtikelBestand();
	    }

	public List<Artikel> sucheNachBezeichnung(String titel) {       
        return meineArtikel.sucheArtikel(titel);
    }
	public <Kunden> List<Kunde> gibAlleKunden() {
        return meineKunden.getKundenBestand();
    }
  
		
    public Artikel fuegeArtikelEin(String titel, int artNr, double preis, int bestand)  throws ArtikelExistiertBereitsException {
        Artikel b = new Artikel(titel, artNr, preis, bestand);
        meineArtikel.einfuegen(b);
        return b;
    }
	
	public List<Artikel> sucheNachArtikelnummer(int artNr) {
		return meineArtikel.getArtikelBestand(); 
	}
	 public List<Artikel> sucheNachTitel(String titel) {
	        return meineArtikel.sucheArtikel(titel);
	    } 

	
       public void loescheArtikel(String bezeichnung, int artNr, double preis, int bestand) {
		Artikel a = new Artikel(bezeichnung, artNr, preis, bestand);
		meineArtikel.loeschen(a);
	}

	public List<Artikel> sucheArtikelBezeichnung(String artikelBezeichnung) {		
		return meineArtikel.sucheArtikel(artikelBezeichnung); 
	}
	
	public void aendereBestandVonArtikel(int artikelNr, int bestand) {
		meineArtikel.aendereBestandVonArtikel(artikelNr, bestand);
	}
	
	public int getBestandVonArtikel(int artNr) throws ArtikelMitArtNrExistiertNichtException {
		return meineArtikel.getBestandVonArtikelMitArtikelnummer(artNr);
	}
	
	public Kunde kEinloggen(String login, String passwort) {
		return meineKunden.einloggen(login, passwort);
	}
	public Mitarbeiter mEinloggen(String login, String passwort) {
		return meineMitarbeiter.einloggen(login, passwort);
	}
	
	public void registrieren(String vorname, String nachname, String loginName,  String passwort,int personNr, Adresse adresse) {
		meineKunden.registrieren( vorname, nachname, loginName, passwort,personNr, adresse);
	}
	
	public void schreibeDaten() throws IOException {
		meineArtikel.schreibeDaten(datei+"_B.txt");
	//	meineKunden.schreibeDaten(datei+"_K.txt");
	}
	public void schreibeArtikel() throws IOException {
		meineArtikel.schreibeDaten(datei+"_B.txt");
	
	}
	public void schreibeKunden() throws IOException {
		meineKunden.schreibeDaten(datei+"_K.txt");
	}
	
	
	public Warenkorb warenHinzufügen(Artikel artikel, Kunde kunde) {
		return meineWarenkorbVerwaltung.hinzufügen(artikel, kunde);		
	
	}
	
	  private void entfernen (Artikel artikel) {
		  meineWarenkorbVerwaltung.entfernen(artikel);
	  }
	
	 
}
