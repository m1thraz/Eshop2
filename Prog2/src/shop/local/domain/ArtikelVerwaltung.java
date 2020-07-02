package shop.local.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import shop.local.domain.exceptions.ArtNrNichtVorhandenException;
import shop.local.domain.exceptions.ArtikelMitArtNrExistiertNichtException;
import shop.local.domain.exceptions.ArtikelNrSchonVorhandenException;
import shop.local.domain.exceptions.ArtikelSchonVorhandenException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Massengutartikel;

public class ArtikelVerwaltung {
	
	private List<Artikel> artikelBestand = new ArrayList<>();	
	private PersistenceManager pm = new FilePersistenceManager();
	
	
	
	
	public void liesDaten(String datei) throws IOException {
        // PersistenzManager für Lesevorgänge öffnen
        pm.openForReading(datei);

        Artikel einArtikel;
        do {
            // Artikel-Objekt einlesen
            einArtikel = pm.ladeArtikel();
            if (einArtikel != null) {
            	
                artikelBestand.add(einArtikel);
            }
        } while (einArtikel != null);

        // Persistenz-Schnittstelle wieder schließen
        pm.close();
    }
	
	   public void schreibeDaten(String datei) throws IOException  {
	        // PersistenzManager für Schreibvorgänge öffnen
	        pm.openForWriting(datei);

	        for (Artikel b : artikelBestand) {
	            pm.speichereArtikel(b);
	        }

	        // Persistenz-Schnittstelle wieder schließen
	        pm.close();
	    }
	
	    public void loeschen(Artikel einArtikel) {
	        // das übernimmt der Vector:
	        artikelBestand.remove(einArtikel);
	    }
	
	    public List<Artikel> getArtikelBestand() {
	        return new ArrayList<>(artikelBestand);
	    }
	
	    /**
	     * In dieser Funktion gibt fügt der Mitarbeiter ein Artikel ein
	     * wenn ein Artikel eingefügt werden soll, gibt der Mitarbeiten beim Einfügen 1 ein in Einheit
	     * wenn ein Massengutartikel eingefügt werden soll, gibt der Mitarbeiter beim Einfügen die Gewünschte Anzahl an 
	     * in Einheit in der das Massengutartikel verkauft werden soll
	     * @param titel
	     * @param artNr
	     * @param preis
	     * @param bestand
	     * @param einheit
	     * @return
	     * @throws ArtikelSchonVorhandenException
	     */
	    public Artikel einfuegen(String titel, int artNr, double preis, int bestand, int einheit) throws ArtikelNrSchonVorhandenException {
	        
	    	//Diese For-Schleife durchläuft einmal den gesamten Artikelbestand, 
	    	//ob ein Artikel schon mit der eingegeben Artikelnummer vorhanden ist
	    	//ist dies der Fall, wird eine Exception geworfen
	    	//Wenn es die eingegebene Artikelnummer nicht gibt, geht es weiter und es weiter
	    	for(Artikel artikel:artikelBestand) {
				if(artikel.getArtNr()==artNr) {
					throw new ArtikelNrSchonVorhandenException(artNr);
				}
			}
	    	
	    	//Hier wird überprüft ob die Einheit größer 1 ist oder kleiner 1 ist
	    	//Mit der Einheit ist die zu kaufende Einheit gemeint, also in welcher Menge man das Artikel kaufen kann
	    	//Ist der Wert 1 ist es ein normaler Artikel und man kann den einzeln kaufen
	    	//Ist der Wert größer 1 kann man das Artikel nur in der vorgegebenen Einheit kaufen
	    	Artikel a;
	    	if(einheit>1) {
	    		a= new Massengutartikel(titel,artNr, preis, bestand, einheit);
	        
	        }else {
	        	a= new Artikel(titel,artNr, preis, bestand);

	        }
	    	artikelBestand.add(a);
	    	return a;
	    }
 

	    public List<Artikel> sucheArtikel(int artikelnummer) {

			ArrayList<Artikel> suchErg = new ArrayList<>();


			Iterator<Artikel> iter = artikelBestand.iterator();
			while (iter.hasNext()) {
				
				Artikel a = iter.next();
				if (a.getArtNr() == (artikelnummer))
					suchErg.add(a);
			}
		
			return suchErg;
		}


	    
	    
	    public List<Artikel> sucheArtikel(String titel) {
	  	  
	        List<Artikel> suchErg = new ArrayList<>();

	       
	        Iterator<Artikel> iter = artikelBestand.iterator();
	        while (iter.hasNext()) {
	        
	            Artikel b = iter.next();
	            if (b.getBezeichnung().equals(titel))
	                suchErg.add(b);
	            
	        }
	    
	        return suchErg;
	    }
		
	 
	 	/**
	 	 * Mit dieser Methode kann man den Bestand eines Artikels ändern/erhöhen
	 	 * @param artikelNr
	 	 * @param bestand
	 	 */
		public void aendereBestandVonArtikel(int artikelNr, int bestand) {
			
			for(Artikel a:artikelBestand) {
				if(a.getArtNr()==artikelNr) {
					a.setBestand(a.getBestand()+bestand);
				}
			}
			
		}
		
		public int getBestandVonArtikelMitArtikelnummer(int artNr) throws ArtNrNichtVorhandenException {
			for(Artikel a:artikelBestand) {
				if(a.getArtNr()==artNr) {
					return a.getBestand();
				}
			}
			throw new ArtNrNichtVorhandenException(artNr);
			
		}
		
		public Artikel gibArtikelMitArtNr(int artNr) throws ArtikelMitArtNrExistiertNichtException {
			for(Artikel artikel:artikelBestand) {
				if(artikel.getArtNr()==artNr) {
					return artikel;
				}
			}
			throw new ArtikelMitArtNrExistiertNichtException(artNr);
		}
		
		/**
		 * In dieser Methode kann der Benutzer die Zahl Eins oder Zwei eingeben 
		 * bei Eingabe der Zahl 1 werden die Artikel nach dem Namen sortiert ausgegeben
		 * bei Eingabe der Zahl 2 werden die Artikel nach der Artikelnummer soertiert ausgegeben
		 * @param eingabe
		 */
		public List<Artikel> gibArtikelAus(int eingabe) {
			ArrayList<Artikel> sortierteListe=(ArrayList<Artikel>) artikelBestand;
			switch (eingabe) {
			case 1: 
				Collections.sort(sortierteListe, (a, b) -> a.getBezeichnung().compareToIgnoreCase(b.getBezeichnung()));
				break;
			case 2:
				Collections.sort(sortierteListe, (a, b) -> a.getArtNr()-b.getArtNr());
				break;
			}
			return sortierteListe;
			
		}
	}
