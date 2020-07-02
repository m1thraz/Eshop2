package shop.local.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import shop.local.domain.exceptions.MitarbeiterNichtVorhandenException;
import shop.local.valueobjects.*;




public class FilePersistenceManager implements PersistenceManager {

	private BufferedReader reader = null;
	private PrintWriter writer = null;

	public void openForReading(String datei) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(datei));
	}

	public void openForWriting(String datei) throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
	}

	private String liesZeile() throws IOException {
		if (reader != null)
			return reader.readLine();
		else
			return "";
	}

	private void schreibeZeile(String daten) {
		if (writer != null)
			writer.println(daten);
	}
	
	public boolean close() {
		if (writer != null)
			writer.close();

		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();

				return false;
			}
		}

		return true;
	}

	
	//-------------------------------------------Artikel Methoden/Funktionen------------------------------------------------------

	public Artikel ladeArtikel() throws IOException {
		
		// Titel einlesen
		String titel = liesZeile();
		if (titel == null) {			
			return null;
		}
		// Nummer einlesen ...
		String nummerString = liesZeile();
		// ... und von String in int konvertieren
		int nummer = Integer.parseInt(nummerString);
				
		// Preis einlesen
		String preisString = liesZeile();
		// ... und von String in int konvertieren
		double preis = Double.parseDouble(preisString);

		String bestandString = liesZeile();
		// ... und von String in int konvertieren
		int bestand = Integer.parseInt(bestandString);
			// keine Daten mehr vorhanden
		String einheitsString = liesZeile();
		int einheit = Integer.parseInt(einheitsString);
		if (einheit == 1) {
			return new Artikel(titel, nummer, preis, bestand);
		}
		else {
			return new Massengutartikel(titel, nummer, preis, bestand, einheit);
		
	} 
	}

	

	public boolean speichereArtikel(Artikel b) throws IOException {
		//hier wird der Arikel in der Datei gespeciher
		// 1. Bezeichnung 2.ArtNr 3. Verfuegbar 4. Preis 5.Bestand
	    schreibeZeile(b.getBezeichnung());
		schreibeZeile(b.getArtNr() + "");		
		schreibeZeile(b.getPreis() + "");
		schreibeZeile(b.getBestand()+ "");
		if(b instanceof Massengutartikel )
			schreibeZeile(((Massengutartikel) b).getEinheit() + "");
		else 
			schreibeZeile("1");
		return true;
	}
	
	//--------------------------------------------------Kunden Methoden/Funktionen--------------------------------------------
	
	public Kunde ladeKunde() throws IOException {
		String vorname = liesZeile();
		if (vorname == null) {
			// keine Daten mehr vorhanden
			return null;
		}
		String nachname = liesZeile();
		if (nachname == null) {
			// keine Daten mehr vorhanden
			return null;
		}
		String loginName = liesZeile();
		if (loginName == null) {
			return null;
		}
		String pw = liesZeile();
		if (pw == null) {			
			return null;
		}
		
		String strasse = liesZeile();
		if (strasse == null) {			
			return null;
		}
		
		
		String hausNr = liesZeile();
		if (hausNr == null) {			
			return null;
		}
		String plz = liesZeile();
		if (plz == null) {			
			return null;
		}
		String ort = liesZeile();
		if (ort == null) {			
			return null;
		}
		Adresse adresse =new Adresse( strasse, hausNr, plz, ort);
		return new Kunde(vorname, nachname, loginName, pw, adresse );
	}
	
	public boolean speichereKunde(Kunde k) throws IOException {
		// Kunde  wird in Datei gerspeichert
		// 1. Vorname 2.Nachname 3. Login 4. Passwort 5.Adresse		
		schreibeZeile(k.getVorname() + "");
		schreibeZeile(k.getNachname() + "");
		schreibeZeile(k.getLoginName() + "");
		schreibeZeile(k.getPw() + "");
		schreibeZeile(k.getAdresse().getStrasse() + "");
		schreibeZeile(k.getAdresse().getHausNr() + "");
		schreibeZeile(k.getAdresse().getPlz() + "");
		schreibeZeile(k.getAdresse().getOrt() + "");
		
		return true;
	}
	
	//---------------------------------------------Mitarbeiter Methoden/Funktionen---------------------------------------------
	
	public Mitarbeiter ladeMitarbeiter() throws Exception, MitarbeiterNichtVorhandenException {
		
		String vorname = liesZeile();
		if (vorname == null) {
		
			return null;
		}

		String nachname = liesZeile();
		if (nachname == null) {		
			return null;
		}
	
		String loginName = liesZeile();
		if (loginName == null) {
			return null;
		}
		
		String pw = liesZeile();
		if (pw == null) {			
			return null;
		}
		
		return new Mitarbeiter(vorname, nachname, loginName, pw);
	}
	
	public boolean speichereMitarbeiter(Mitarbeiter m) {
		// Kunde  wird in Datei gerspeichert
		// 1. Vorname 2.Nachname 3. Login 4. Passwort 5.Adresse		
		schreibeZeile(m.getVorname() + "");
		schreibeZeile(m.getNachname() + "");
		schreibeZeile(m.getLoginName() + "");
		schreibeZeile(m.getPw() + "");
		return true;
	}
	
	//-----------------------------------------------Warenkorb Methoden/Funktionen------------------------------------------------

	@Override
	public Warenkorb ladeWarenkorb() throws IOException {
		return null;
	}
}
