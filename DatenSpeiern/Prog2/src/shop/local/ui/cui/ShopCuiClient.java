package shop.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import shop.local.domain.EShop;
import shop.local.domain.WarenkorbVerwaltung;
import shop.local.domain.exceptions.ArtikelExistiertBereitsException;
import shop.local.domain.exceptions.LoginFailedException;
import shop.local.valueObjects.*;


/**
 * @author Zahid Aryobi
 *
 */

public class ShopCuiClient {
	
	private BufferedReader in;
	private EShop shop;
	private Benutzer aktuellerBenutzer;
	private Kunde eingeloggterKunde;
	private Mitarbeiter eingeloggterMitarbeiter;
	
	
	public ShopCuiClient(String daten) throws IOException, Exception, ArtikelExistiertBereitsException {
		// TODO Automatisch generierter Konstruktorstub
		shop = new EShop(daten); 
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	
   private void gibKundenMenueAus() {
		System.out.print("  \n KUNDENMENÜ: \n");
		System.out.print("  \n  ----------------------------");
        System.out.print("  \n  Artikel anzeigen:  'a'");
        System.out.print("  \n  Artikel suchen: \t  'f'");
        System.out.print(" \n   Warenkorb anzeigen:  'w'");  
//        System.out.print("  \n Waren in Warenkorb legen:  '´r'");
//        System.out.print("  \n Kaufabschließen:  'm'"); 
//       	System.out.print("  \n  Warenkorb leeren: \t  'f'");
 		System.out.print("  \n  ausloggen:\t\t  'd'");    
		System.out.print("  \n  ----------------------------");
		System.out.println(" \n  Beenden:\t          'q'");
		System.out.print("> "); 
	}
	

	
	private void gibArbeiterMenueAus() {
        System.out.print("  \n  MITARBEITERMENUE: \n");
		System.out.print("  \n  ----------------------------");
        System.out.print("  \n  Waren anzeigen:  'a'");
//      System.out.print(" \n   Warenkorb anzeigen:  'w'");         
		System.out.print("  \n  Artikel hinzufügen:\t  'e'");
		System.out.print("  \n  Artikel in Warenkorb:\t  'x'");
		System.out.print("  \n  Artikel aus dem Warenkorb entfernen:\t  'y'");
		System.out.print("  \n  Warenkorb anzeigen:\t  '#'");
		System.out.print(" 	\n  Artikel löschen:\t  'l'");
		System.out.print("  \n  Artikel suchen: \t  'f'");
		System.out.print("  \n  Artikel Bestand ändern: 'z'");
		System.out.print("  \n  Kunden anzeigen:  'b'");
		System.out.print("  \n  ausloggen:\t\t  'd'");
		System.out.print("  \n  ----------------------------");
		System.out.println("    \n  Beenden:\t          'q'");
		System.out.print("> "); 
		System.out.flush(); 
	}
	
	
	private void gibUneingeloggtesMenueAus() {
		System.out.print("  \n LOGIN: \n");
		System.out.print("  \n  ----------------------------");
        System.out.print("  \n Kunde anmelden:  'y'");
        System.out.print("  \n Mitarbeiter anmelden:  'm'");
        System.out.print("  \n Kunde registrieren:  '´r'");   
		System.out.print("  \n  ----------------------------");
		System.out.println(" \n  Beenden:\t          'q'");
		System.out.print("> "); 
	}
	
	private void verarbeiteEingabe(String input) throws IOException, ArtikelExistiertBereitsException {
          
		// Unterscheidung fuer eingaben ob Kunde eingeloggt ist.
		if (eingeloggterKunde == null) {
			verarbeiteUneingeloggteEingabe(input);
		
		} else {
			verarbeiteKundenEingabe(input);
		}
	}
	
	private void verarbeiteUneingeloggteEingabe(String input) throws IOException {

		String login;
				String passwort;
				String vorname;
				String nachname;
				Adresse adresse;
				String strasse;
				String ort;
				String hausNr;
				String plz;
				int personNr = 0;
				
			switch (input) {
			case "y":
				System.out.print("Benutzername  > ");
				login = liesEingabe();
				System.out.print("Passwort  > ");
				passwort = liesEingabe();
				eingeloggterKunde = shop.kEinloggen(login, passwort);
				System.out.println(eingeloggterKunde.getVorname() + " " + eingeloggterKunde.getNachname());
			break;
			case "m":
				System.out.print("Benutzername  > ");
				login = liesEingabe();
				System.out.print("Passwort  > ");
				passwort = liesEingabe();
				eingeloggterMitarbeiter = shop.mEinloggen(login, passwort);
				System.out.println(eingeloggterMitarbeiter.getVorname() + " " + eingeloggterMitarbeiter.getNachname());
			break;
			case "r":
				System.out.print("Vorname  > ");
				vorname = liesEingabe();
				System.out.print("Nachname  > ");
				nachname = liesEingabe();
				System.out.print("Benutzername  > ");
				login = liesEingabe();
				System.out.print("Passwort  > ");
				passwort = liesEingabe();
				System.out.print("Strasse  > ");
				strasse = liesEingabe();
				System.out.print("Hausnummer  > ");
				hausNr = liesEingabe();
				System.out.print("Poistleitzahl  > ");
				plz = liesEingabe();
				System.out.print("Ort  > ");
				ort = liesEingabe();
				adresse = new Adresse(strasse,hausNr, plz,ort);
				shop.registrieren(vorname,nachname,login,passwort,personNr, adresse);
				shop.schreibeKunden();
				
			break;
			default:
				 System.out.println("Ungültige Eingabe");
				}
			}
				
				
	
	
	private void verarbeiteMitarbeiterEingabe(String input) throws IOException, ArtikelExistiertBereitsException {

		List<Artikel> liste;
		String nummer;
		
		
		String artikelname;
		    int nr;
	        String artikel;
	        double preis = 0;
	        int bestand = 0;

	        List<Artikel> liste1;
	        List<Kunde> liste2;
		
		switch (input) {

		case "e":
            // lies die notwendigen Parameter, einzeln pro Zeile
            System.out.print("Artikelnummer> ");
            nummer = liesEingabe();
            nr = Integer.parseInt(nummer);
            System.out.print("Produkt  > ");
            artikel= liesEingabe();
            System.out.print("Preis  > ");
            nummer= liesEingabe();
            preis = Double.parseDouble(nummer);
            System.out.print("Bestand  > ");
            nummer= liesEingabe();
            bestand = Integer.parseInt(nummer);

            try {
                shop.fuegeArtikelEin(artikel, nr, preis, bestand);
                System.out.println("Einfügen ok");
            } catch (ArtikelExistiertBereitsException e) {
                // Hier Fehlerbehandlung...
                System.out.println("Fehler beim Einfügen");
                e.printStackTrace();
            }
            shop.schreibeDaten();
            break;
		
		case "a":
			liste1 = shop.gibAlleArtikel();
			gibArtikellisteAus(liste1);
			break;
			
		case "b":
			liste2 = shop.gibAlleKunden();
			gibKundenListeAus(liste2);			
			break;
			
		case "l":
			 // lies die notwendigen Parameter, einzeln pro Zeile
            System.out.print("ArtNr > ");
            nummer = liesEingabe();
            nr = Integer.parseInt(nummer);
            System.out.print("Produkt  > ");
            artikel = liesEingabe();

            shop.loescheArtikel(artikel, nr, preis, bestand);
            shop.schreibeArtikel();
            break;
            
		case "f":
			System.out.print("Artikelbezeichnung  > ");
			artikelname = liesEingabe();
			liste1 = shop.sucheArtikelBezeichnung(artikelname);
			gibArtikellisteAus(liste1);
			break;
			
		case "z":
			System.out.println("Artikelnummer?  > ");
			nummer = liesEingabe();
			int zuAendernderArtikel = Integer.parseInt(nummer);
			System.out.println("Neuer Bestand?  > ");
			String zuErhoehenderBestandStr = liesEingabe();
			int zuErhoenderBestand = Integer.parseInt(zuErhoehenderBestandStr);
			shop.aendereBestandVonArtikel(zuAendernderArtikel, zuErhoenderBestand);
			shop.schreibeDaten();
			break;
			
		case "d":
			eingeloggterKunde = null;
			eingeloggterMitarbeiter = null;
			break;
			

			
		default:
		 System.out.println("Ungültige Eingabe");
		}
	}
	
	private void verarbeiteKundenEingabe(String input) throws IOException, ArtikelExistiertBereitsException {
		List<Artikel> liste;
		String nummer;
		
		
		String artikelname;
		    List<Artikel> liste1;
	        switch (input) {
		
		case "a":
			liste1 = shop.gibAlleArtikel();
			gibArtikellisteAus(liste1);
			break;
			
		case "f":
			System.out.print("Artikelbezeichnung  > ");
			artikelname = liesEingabe();
			liste1 = shop.sucheArtikelBezeichnung(artikelname);
			gibArtikellisteAus(liste1);
			break;
		
		case "d":
			eingeloggterKunde = null;
			eingeloggterMitarbeiter = null;
			break;
			
		case "x":
			System.out.print("Artikelbezeichnung  > ");
			artikelname = liesEingabe();
			liste1 = shop.sucheArtikelBezeichnung(artikelname);
			Warenkorb warenkorb = shop.warenHinzufügen(liste1.get(0), eingeloggterKunde);			
			break;
			
		case "#":			
			break;
			
	    default: 
	    	System.out.println("Ungültige Eingabe");	
			
		
		}
	
	}
	
	
	
	
	
	
	
	private String liesEingabe() throws IOException {
		return in.readLine();
	}	
	
	private void gibArtikellisteAus(List<Artikel> liste) {
		if (liste.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			for (Artikel artikel : liste) {
				System.out.println(artikel);
			}
		}
	}
	private void gibKundenListeAus(List<Kunde> liste) {
		if (liste.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			for (Kunde kunde : liste) {
				System.out.println(kunde);
			}
		}
	}
	
	private void run() throws ArtikelExistiertBereitsException {
			String input = ""; 
		
		// Hauptschleife der Benutzungsschnittstelle
		do {
			if (eingeloggterKunde == null && eingeloggterMitarbeiter == null) {
		    gibUneingeloggtesMenueAus();
			
			try {
				input = liesEingabe();
				verarbeiteEingabe(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
			
		} else if (eingeloggterKunde == null && eingeloggterMitarbeiter != null ) {
	
			gibArbeiterMenueAus();
			try {
				input = liesEingabe();
				verarbeiteEingabe(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			gibKundenMenueAus();
			try {
				input = liesEingabe();
				verarbeiteEingabe(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}while (!input.equals("q"));

	}
		
	public static void main(String[] args) throws IOException, Exception {
		ShopCuiClient cui;
		try {
			cui = new ShopCuiClient("shop");
			cui.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}