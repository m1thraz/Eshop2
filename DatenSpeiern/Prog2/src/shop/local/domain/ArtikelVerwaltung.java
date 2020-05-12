package shop.local.domain;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.domain.exceptions.ArtikelExistiertBereitsException;
import shop.local.domain.exceptions.ArtikelMitArtNrExistiertNichtException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueObjects.Artikel;

public class ArtikelVerwaltung {
	
	private List<Artikel> artikelBestand = new Vector<Artikel>();;	
	private PersistenceManager pm = new FilePersistenceManager();
	
	
	
	
	public void liesDaten(String datei) throws IOException, ArtikelExistiertBereitsException {
        // PersistenzManager f�r Lesevorg�nge �ffnen
        pm.openForReading(datei);

        Artikel einArtikel;
        do {
            // Artikel-Objekt einlesen
            einArtikel = pm.ladeArtikel();
            if (einArtikel != null) {
                einfuegen(einArtikel);
            }
        } while (einArtikel != null);

        // Persistenz-Schnittstelle wieder schlie�en
        pm.close();
    }
	
	
	
	
	
	   public void schreibeDaten(String datei) throws IOException  {
	        // PersistenzManager f�r Schreibvorg�nge �ffnen
	        pm.openForWriting(datei);

	        for (Artikel b : artikelBestand) {
	            pm.speichereArtikel(b);
	        }

//			// Alternative Implementierung mit Iterator:
//			Iterator<Buch> iter = buchBestand.iterator();
//			while (iter.hasNext()) {
//				Buch b = iter.next();
//				pm.speichereBuch(b);
//			}

	        // Persistenz-Schnittstelle wieder schlie�en
	        pm.close();
	    }



	
	    public void loeschen(Artikel einArtikel) {
	        // das �bernimmt der Vector:
	        artikelBestand.remove(einArtikel);
	    }

	
	
	
	    public List<Artikel> getArtikelBestand() {
	        return new Vector<Artikel>(artikelBestand);
	    }
	
	
	    public void einfuegen(Artikel einArtikel) throws ArtikelExistiertBereitsException {
	        if (artikelBestand.contains(einArtikel)) {
	            throw new ArtikelExistiertBereitsException(einArtikel, " - in 'einfuegen()'");
	        }
	        // das �bernimmt der Vector:
	        artikelBestand.add(einArtikel);
	    }

	
	
	
	
	
	
	//public Artikel artikelHinzufuegen(String bezeichnung, int artNr, double preis, int bestand) {
	//	Artikel einArtikel = new Artikel(bezeichnung, artNr , preis , bestand);
		
	//	artikelListe.add(einArtikel);
		//return einArtikel;
	//}
	   

	    public List<Artikel> sucheArtikel(int artikelnummer) {
			// auch f�r das Suchergebnis bietet sich
			// die Verwendung von Generics an
			Vector<Artikel> suchErg = new Vector<Artikel>();

			// Buchbestand durchlaufen und nach Titel suchen
			Iterator<Artikel> iter = artikelBestand.iterator();
			while (iter.hasNext()) {
				// WICHTIG: Type Cast auf 'Buch' f�r sp�teren Zugriff auf Titel
				// 		    hier nicht erforderlich wegen Verwendung von Generics
				// 			(-> Vergleiche mit Einsatz von Vector OHNE Generics)
				Artikel a = iter.next();
				if (a.getArtNr() == (artikelnummer))
					suchErg.add(a);
			}
			// Alternative Implementierung mit neuer for-Schleife:
			/*
			for (Buch buch : buchBestand) {
				if ((buch).getTitel().equals(titel))
					suchErg.add(buch);
			}
			*/

			return suchErg;
		}

	
	 public List<Artikel> sucheArtikel(String titel) {
	        // auch f�r das Suchergebnis bietet sich
	        // die Verwendung von Generics an
	        List<Artikel> suchErg = new Vector<Artikel>();

	        // Buchbestand durchlaufen und nach Titel suchen
	        Iterator<Artikel> iter = artikelBestand.iterator();
	        while (iter.hasNext()) {
	            // WICHTIG: Type Cast auf 'Buch' f�r sp�teren Zugriff auf Titel
	            // 		    hier nicht erforderlich wegen Verwendung von Generics
	            // 			(-> Vergleiche mit Einsatz von Vector OHNE Generics)
	            Artikel b = iter.next();
	            if (b.getBezeichnung().equals(titel))
	                suchErg.add(b);
	        }
	        // Alternative Implementierung mit neuer for-Schleife:
			/*
			for (Buch buch : buchBestand) {
				if ((buch).getTitel().equals(titel))
					suchErg.add(buch);
			}
			*/

	        return suchErg;
	    }
		
		public void aendereBestandVonArtikel(int artikelNr, int bestand) {
			
			for(Artikel a:artikelBestand) {
				if(a.getArtNr()==artikelNr) {
					a.setBestand(a.getBestand()+bestand);
				}
			}
			
		}
		
		public int getBestandVonArtikelMitArtikelnummer(int artNr) throws ArtikelMitArtNrExistiertNichtException {
			for(Artikel a:artikelBestand) {
				if(a.getArtNr()==artNr) {
					return a.getBestand();
				}
			}
			throw new ArtikelMitArtNrExistiertNichtException(artNr);
			
		}
	}
